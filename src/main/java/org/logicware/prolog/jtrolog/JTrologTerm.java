/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2017 WorkLogic Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.prolog.jtrolog;

import static org.logicware.prolog.PrologTermType.ATOM_TYPE;
import static org.logicware.prolog.PrologTermType.CUT_TYPE;
import static org.logicware.prolog.PrologTermType.DOUBLE_TYPE;
import static org.logicware.prolog.PrologTermType.EMPTY_TYPE;
import static org.logicware.prolog.PrologTermType.FAIL_TYPE;
import static org.logicware.prolog.PrologTermType.FALSE_TYPE;
import static org.logicware.prolog.PrologTermType.FLOAT_TYPE;
import static org.logicware.prolog.PrologTermType.INTEGER_TYPE;
import static org.logicware.prolog.PrologTermType.LIST_TYPE;
import static org.logicware.prolog.PrologTermType.LONG_TYPE;
import static org.logicware.prolog.PrologTermType.NIL_TYPE;
import static org.logicware.prolog.PrologTermType.STRUCTURE_TYPE;
import static org.logicware.prolog.PrologTermType.TRUE_TYPE;
import static org.logicware.prolog.PrologTermType.VARIABLE_TYPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.logicware.prolog.AbstractTerm;
import org.logicware.prolog.PrologNumber;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologTerm;

import jTrolog.engine.Prolog;
import jTrolog.engine.Solution;
import jTrolog.parser.Parser;
import jTrolog.terms.Double;
import jTrolog.terms.Float;
import jTrolog.terms.Int;
import jTrolog.terms.Long;
import jTrolog.terms.Number;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;
import jTrolog.terms.Term;
import jTrolog.terms.Var;

/** @author Jose Zalacain @since 1.0 */
public abstract class JTrologTerm extends AbstractTerm implements PrologTerm {

	// variable index
	protected int vIndex;
	protected Term value;

	static final JTrologOperatorSet ops = new JTrologOperatorSet();
	static final String SIMPLE_ATOM_REGEX = "\\.|\\?|#|[a-z][A-Za-z0-9_]*";

	protected JTrologTerm(int type, PrologProvider provider) {
		super(type, provider);
	}

	protected JTrologTerm(int type, PrologProvider provider, Term value) {
		super(type, provider);
		this.value = value;
	}

	/**
	 * Variable constructor
	 * 
	 * @param type
	 * @param provider
	 * @param n
	 */
	protected JTrologTerm(int type, PrologProvider provider, String name, int n) {
		this(type, provider, new Var(name, n));
		this.vIndex = n;
	}

	private void enumerateVariables(List<String> vector, Term term) {
		if (!(term instanceof Var)) {
			if (term instanceof Struct) {
				Struct struct = (Struct) term;
				Var[] vars = struct.getVarList();
				for (Var var : vars) {
					enumerateVariables(vector, var);
				}
			}
		} else if (!vector.contains(term.toString())) {
			vector.add(term.toString());
		}
	}

	public final boolean isAtom() {
		return value instanceof StructAtom;
	}

	public final boolean isNumber() {
		return value instanceof Number;
	}

	public final boolean isFloat() {
		return value instanceof Float && !isDouble();
	}

	public final boolean isDouble() {
		return value instanceof Double;
	}

	public final boolean isInteger() {
		return value instanceof Int && !isLong();
	}

	public final boolean isLong() {
		return value instanceof Long;
	}

	public final boolean isVariable() {
		return value instanceof Var;
	}

	public final boolean isList() {
		if (value == Term.emptyList) {
			return true;
		} else if (value instanceof Struct) {
			Struct s = (Struct) value;
			return (s.name.equals(".") && s.arity == 2);
		}
		return false;
	}

	public final boolean isStructure() {
		if (!isAtom() && !isList()) {
			return value instanceof Struct;
		}
		return false;
	}

	public final boolean isNil() {
		if (!isVariable() && !isNumber()) {
			return hasIndicator("nil", 0);
		}
		return false;
	}

	public final boolean isEmptyList() {
		return value == Term.emptyList;
	}

	public final boolean isEvaluable() {
		Prolog prolog = new Prolog();
		Iterator<?> i = prolog.getCurrentOperators();
		while (i.hasNext()) {
			Object object = i.next();
			boolean valueIsStrct = (value instanceof Struct);
			boolean objectIsStrct = (object instanceof Struct);
			if (valueIsStrct && objectIsStrct) {
				Struct op = (Struct) object;
				Struct vop = (Struct) value;
				if (op.arity == 3 && ((StructAtom) op.getArg(2)).name.equals(vop.name)) {
					return true;
				}
			}
		}
		return false;
	}

	public final boolean isAtomic() {
		return !isCompound();
	}

	public final boolean isCompound() {
		return isList() || isStructure();
	}

	public final PrologTerm getTerm() {
		return this;
	}

	public final boolean unify(PrologTerm term) {
		Term otherTerm = fromTerm(term, Term.class);
		return Prolog.match(value, otherTerm);
	}

	public final Map<String, PrologTerm> match(PrologTerm term) {
		Map<String, PrologTerm> map = new HashMap<String, PrologTerm>();
		try {
			List<String> vector = new ArrayList<String>();
			String q = "unify(" + value + "," + term + ").";
			enumerateVariables(vector, new Parser(q).nextTerm(false));
			Solution solution = new Prolog().solve(q);
			for (String vName : vector) {
				if (solution != null) {
					Term vtTerm = solution.getBinding(vName);
					if (vtTerm != null) {
						PrologTerm pTerm = toTerm(vtTerm, PrologTerm.class);
						map.put(vName, pTerm);
					}
				}
			}
		} catch (Throwable e) {
			// do nothing
		}
		return map;
	}

	public final int compareTo(PrologTerm term) {
		int termType = term.getType();

		if ((type >> 8) < (termType >> 8)) {
			return -1;
		} else if ((type >> 8) > (termType >> 8)) {
			return 1;
		}

		switch (type) {
		case NIL_TYPE:
		case CUT_TYPE:
		case FAIL_TYPE:
		case TRUE_TYPE:
		case FALSE_TYPE:
		case ATOM_TYPE:

			// alphabetic functor comparison
			StructAtom atom = (StructAtom) value;
			int result = atom.name.compareTo(term.getFunctor());
			if (result < 0) {
				return -1;
			} else if (result > 0) {
				return 1;
			}
			break;

		case FLOAT_TYPE:

			checkNumberType(term);
			float thisFloatValue = ((Number) value).floatValue();
			float otherFloatValue = ((PrologNumber) term).getFloatValue();

			if (thisFloatValue < otherFloatValue) {
				return -1;
			} else if (thisFloatValue > otherFloatValue) {
				return 1;
			}

			break;

		case LONG_TYPE:

			checkNumberType(term);
			long thisLongValue = ((Number) value).longValue();
			long otherLongValue = ((PrologNumber) term).getLongValue();

			if (thisLongValue < otherLongValue) {
				return -1;
			} else if (thisLongValue > otherLongValue) {
				return 1;
			}

			break;

		case DOUBLE_TYPE:

			checkNumberType(term);
			double thisDoubleValue = ((Number) value).doubleValue();
			double otherDoubleValue = ((PrologNumber) term).getDoubleValue();

			if (thisDoubleValue < otherDoubleValue) {
				return -1;
			} else if (thisDoubleValue > otherDoubleValue) {
				return 1;
			}

			break;

		case INTEGER_TYPE:

			checkNumberType(term);
			int thisIntegerValue = ((Number) value).intValue();
			int otherIntegerValue = ((PrologNumber) term).getIntValue();

			if (thisIntegerValue < otherIntegerValue) {
				return -1;
			} else if (thisIntegerValue > otherIntegerValue) {
				return 1;
			}

			break;

		case LIST_TYPE:
		case EMPTY_TYPE:
		case STRUCTURE_TYPE:

			PrologTerm thisCompound = this;
			PrologTerm otherCompound = term;

			// comparison by arity
			if (thisCompound.getArity() < otherCompound.getArity()) {
				return -1;
			} else if (thisCompound.getArity() > otherCompound.getArity()) {
				return 1;
			}

			// alphabetic functor comparison
			result = thisCompound.getFunctor().compareTo(otherCompound.getFunctor());
			if (result < 0) {
				return -1;
			} else if (result > 0) {
				return 1;
			}

			// arguments comparison
			PrologTerm[] thisArguments = thisCompound.getArguments();
			PrologTerm[] otherArguments = otherCompound.getArguments();

			for (int i = 0; i < thisArguments.length; i++) {
				PrologTerm thisArgument = thisArguments[i];
				PrologTerm otherArgument = otherArguments[i];
				if (thisArgument != null && otherArgument != null) {
					result = thisArgument.compareTo(otherArgument);
					if (result != 0) {
						return result;
					}
				}
			}
			break;

		case VARIABLE_TYPE:

			PrologTerm thisVariable = this;
			PrologTerm otherVariable = term;
			if (thisVariable.hashCode() < otherVariable.hashCode()) {
				return -1;
			} else if (thisVariable.hashCode() > otherVariable.hashCode()) {
				return 1;
			}
			break;

		default:
			return 0;

		}

		return 0;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type;
		result = prime * result + ((value == null) ? 0 : value.toString().hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof JTrologTerm))
			return false;
		JTrologTerm other = (JTrologTerm) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (value.toString().equals(other.value.toString())) {
			return true;
		} else if (!Prolog.match(value, other.value)) {
			return false;
		}
		return true;
	}

	public final String toString() {
		return value.toStringSmall();
	}

}
