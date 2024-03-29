/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package io.github.prolobjectlink.prolog.jtrolog;

import static io.github.prolobjectlink.prolog.PrologTermType.ATOM_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.CUT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.DOUBLE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FAIL_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FALSE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.FLOAT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.INTEGER_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LIST_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.LONG_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.NIL_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.TRUE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.VARIABLE_TYPE;

import java.util.Iterator;

import io.github.prolobjectlink.prolog.AbstractTerm;
import io.github.prolobjectlink.prolog.PrologNumber;
import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologTerm;
import jTrolog.engine.Prolog;
import jTrolog.terms.Double;
import jTrolog.terms.Float;
import jTrolog.terms.Int;
import jTrolog.terms.Long;
import jTrolog.terms.Number;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;
import jTrolog.terms.Term;
import jTrolog.terms.Var;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
abstract class JTrologTerm extends AbstractTerm implements PrologTerm {

	// variable index
	protected int vIndex;
	protected Term value;

	static final JTrologOperatorSet ops = new JTrologOperatorSet();

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
		return !(isEmptyList()) && (isList() || isStructure());
	}

	public final boolean isTrueType() {
		return getObject().equals(true);
	}

	public final boolean isFalseType() {
		return getObject().equals(false);
	}

	public final boolean isNullType() {
		return getObject() == null;
	}

	public final boolean isVoidType() {
		return getObject() == void.class;
	}

	public final boolean isObjectType() {
		return getType() == OBJECT_TYPE;
	}

	public final boolean isReference() {
		return isObjectType();
	}

	public PrologTerm getTerm() {
		return this;
	}

	public final boolean unify(PrologTerm term) {
		Term otherTerm = fromTerm(term, Term.class);
		return Prolog.match(value, otherTerm);
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

			float thisFloatValue = ((Number) value).floatValue();
			float otherFloatValue = ((PrologNumber) term).getFloatValue();

			if (thisFloatValue < otherFloatValue) {
				return -1;
			} else if (thisFloatValue > otherFloatValue) {
				return 1;
			}

			break;

		case LONG_TYPE:

			long thisLongValue = ((Number) value).longValue();
			long otherLongValue = ((PrologNumber) term).getLongValue();

			if (thisLongValue < otherLongValue) {
				return -1;
			} else if (thisLongValue > otherLongValue) {
				return 1;
			}

			break;

		case DOUBLE_TYPE:

			double thisDoubleValue = ((Number) value).doubleValue();
			double otherDoubleValue = ((PrologNumber) term).getDoubleValue();

			if (thisDoubleValue < otherDoubleValue) {
				return -1;
			} else if (thisDoubleValue > otherDoubleValue) {
				return 1;
			}

			break;

		case INTEGER_TYPE:

			int thisIntegerValue = ((Number) value).intValue();
			int otherIntegerValue = ((PrologNumber) term).getIntegerValue();

			if (thisIntegerValue < otherIntegerValue) {
				return -1;
			} else if (thisIntegerValue > otherIntegerValue) {
				return 1;
			}

			break;

		case LIST_TYPE:
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

	public String toString() {
		return value.toStringSmall();
	}

}
