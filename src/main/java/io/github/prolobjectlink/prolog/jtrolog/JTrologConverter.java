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
import static io.github.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.TRUE_TYPE;
import static io.github.prolobjectlink.prolog.PrologTermType.VARIABLE_TYPE;

import java.util.ArrayList;
import java.util.Iterator;

import io.github.prolobjectlink.prolog.AbstractConverter;
import io.github.prolobjectlink.prolog.PrologAtom;
import io.github.prolobjectlink.prolog.PrologConverter;
import io.github.prolobjectlink.prolog.PrologDouble;
import io.github.prolobjectlink.prolog.PrologFloat;
import io.github.prolobjectlink.prolog.PrologInteger;
import io.github.prolobjectlink.prolog.PrologLong;
import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologStructure;
import io.github.prolobjectlink.prolog.PrologTerm;
import io.github.prolobjectlink.prolog.PrologVariable;
import io.github.prolobjectlink.prolog.UnknownTermError;
import jTrolog.terms.Double;
import jTrolog.terms.Float;
import jTrolog.terms.Int;
import jTrolog.terms.Long;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;
import jTrolog.terms.Term;
import jTrolog.terms.Var;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
final class JTrologConverter extends AbstractConverter<Term> implements PrologConverter<Term> {

	private static final JTrologOperatorSet OPERATORS = new JTrologOperatorSet();

	public PrologTerm toTerm(Term prologTerm) {
		if (prologTerm.equals(Term.TRUE)) {
			return new JTrologTrue(provider);
		} else if (prologTerm.equals(Term.FALSE)) {
			return new JTrologFalse(provider);
		}

		// long extend integer and double extend float
		// be careful with instance check order

		else if (prologTerm instanceof Double) { // double always before float
			return new JTrologDouble(provider, ((Double) prologTerm).doubleValue());
		} else if (prologTerm instanceof Float) { // float always after double
			return new JTrologFloat(provider, ((Float) prologTerm).floatValue());
		} else if (prologTerm instanceof Long) { // long always before integer
			return new JTrologLong(provider, ((Long) prologTerm).longValue());
		} else if (prologTerm instanceof Int) { // integer always after long
			return new JTrologInteger(provider, ((Int) prologTerm).intValue());
		} else if (prologTerm instanceof Var) {
			Var var = (Var) prologTerm;
			String name = var.toString();
			PrologVariable v = sharedVariables.get(name);
			if (v == null) {
				v = new JTrologVariable(provider, name, var.nrInStruct);
				sharedVariables.put(v.toString(), v);
			}
			return v;
		} else if (prologTerm instanceof Struct) {

			Struct struct = (Struct) prologTerm;
			int arity = struct.arity;
			String functor = struct.name;
			Term[] arguments = new Term[arity];

			if (struct == Term.emptyList) {
				return new JTrologEmpty(provider);
			}

			// atom and constants
			else if (prologTerm instanceof StructAtom) {
				if (functor.equals("nil")) {
					return new JTrologNil(provider);
				} else if (functor.equals("!")) {
					return new JTrologCut(provider);
				} else if (functor.equals("fail")) {
					return new JTrologFail(provider);
				} else {
					return new JTrologAtom(provider, functor);
				}
			}

			// list
			else if (struct.name.equals(".") && struct.arity == 2) {
				ArrayList<Term> args = new ArrayList<Term>();
				Iterator<?> i = Struct.iterator(struct);
				while (i.hasNext()) {
					Term term = (Term) i.next();
					args.add(term);
				}
				return new JTrologList(provider, args.toArray(arguments));
			}

			// expression
			else if (arity == 2 && OPERATORS.currentOp(functor)) {
				Term left = struct.getArg(0);
				Term right = struct.getArg(1);
				return new JTrologStructure(provider, left, functor, right);
			}

			// structure
			else {
				for (int i = 0; i < arity; i++) {
					arguments[i] = struct.getArg(i);
				}
				return new JTrologStructure(provider, functor, arguments);
			}

		} else {
			throw new UnknownTermError(prologTerm);
		}
	}

	public Term fromTerm(PrologTerm term) {
		switch (term.getType()) {
		case NIL_TYPE:
			return new StructAtom("nil");
		case CUT_TYPE:
			return new StructAtom("!");
		case FAIL_TYPE:
			return new StructAtom("fail");
		case TRUE_TYPE:
			return Term.TRUE;
		case FALSE_TYPE:
			return Term.FALSE;
		case ATOM_TYPE:
			return new StructAtom(removeQuoted(((PrologAtom) term).getStringValue()));
		case FLOAT_TYPE:
			return new Float(((PrologFloat) term).getFloatValue());
		case INTEGER_TYPE:
			return new Int(((PrologInteger) term).getIntegerValue());
		case DOUBLE_TYPE:
			return new Double(((PrologDouble) term).getDoubleValue());
		case LONG_TYPE:
			return new Long(((PrologLong) term).getLongValue());
		case VARIABLE_TYPE:
			PrologVariable v = (PrologVariable) term;
			String name = v.getName();
			Term variable = sharedPrologVariables.get(name);
			if (variable == null) {
				variable = new Var(name, v.getPosition());
				sharedPrologVariables.put(name, variable);
			}
			return variable;
		case LIST_TYPE:
			PrologTerm[] elements = term.getArguments();
			if (elements != null && elements.length > 0) {
				Term list = Term.emptyList;
				int offset = elements[elements.length - 1].isEmptyList() ? 2 : 1;
				for (int i = elements.length - offset; i >= 0; --i) {
					list = new Struct(".", new Term[] { fromTerm(elements[i], Term.class), list });
				}
				return list;
			}
			return Term.emptyList;
		case STRUCTURE_TYPE:
			String functor = term.getFunctor();
			if (term.getArity() < 1) {
				if (!functor.matches(SIMPLE_ATOM_REGEX)) {
					return new StructAtom("'" + functor + "'");
				} else {
					return new StructAtom(functor);
				}
			}
			Term[] arguments = fromTermArray(((PrologStructure) term).getArguments());
			return new Struct(functor, arguments);
		default:
			throw new UnknownTermError(term);
		}
	}

	public Term[] fromTermArray(PrologTerm[] terms) {
		Term[] prologTerms = new Term[terms.length];
		for (int i = 0; i < terms.length; i++) {
			prologTerms[i] = fromTerm(terms[i]);
		}
		return prologTerms;
	}

	public Term fromTerm(PrologTerm head, PrologTerm[] body) {
		Term h = fromTerm(head);
		if (body != null && body.length > 0) {
			Term b = fromTerm(body[body.length - 1]);
			for (int i = body.length - 2; i >= 0; --i) {
				b = new Struct(",", new Term[] { fromTerm(body[i]), b });
			}
			return new Struct(":-", new Term[] { h, b });
		}
		return new Struct(":-", new Term[] { h, Term.TRUE });
	}

	public PrologProvider createProvider() {
		return new JTrolog(this);
	}

	@Override
	public String toString() {
		return "JTrologConverter";
	}

}
