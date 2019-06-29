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
package org.prolobjectlink.prolog.jtrolog;

import static org.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;

import org.prolobjectlink.prolog.PrologProvider;
import org.prolobjectlink.prolog.PrologStructure;
import org.prolobjectlink.prolog.PrologTerm;

import jTrolog.parser.Parser;
import jTrolog.terms.Struct;
import jTrolog.terms.Term;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
class JTrologStructure extends JTrologTerm implements PrologStructure {

	JTrologStructure(PrologProvider provider, String functor, PrologTerm... arguments) {
		super(STRUCTURE_TYPE, provider);
		Term[] terms = new Term[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			terms[i] = ((JTrologTerm) arguments[i]).value;
		}
		value = new Struct(functor, terms);
	}

	JTrologStructure(PrologProvider provider, String functor, Term... arguments) {
		super(STRUCTURE_TYPE, provider, new Struct(functor, arguments));
	}

	JTrologStructure(PrologProvider provider, PrologTerm left, String operator, PrologTerm right) {
		super(STRUCTURE_TYPE, provider);
		Term leftOperand = ((JTrologTerm) left).value;
		Term rightOperand = ((JTrologTerm) right).value;
		value = new Struct(operator, new Term[] { leftOperand, rightOperand });
	}

	JTrologStructure(PrologProvider provider, Term left, String functor, Term right) {
		super(STRUCTURE_TYPE, provider, new Struct(functor, new Term[] { left, right }));
	}

	public PrologTerm getArgument(int index) {
		checkIndex(index, getArity());
		return getArguments()[index];
	}

	public PrologTerm[] getArguments() {
		Struct structure = (Struct) value;
		int arity = structure.arity;
		PrologTerm[] arguments = new PrologTerm[arity];
		for (int i = 0; i < arity; i++) {
			arguments[i] = provider.toTerm(structure.getArg(i), PrologTerm.class);
		}
		return arguments;
	}

	public int getArity() {
		Struct structure = (Struct) value;
		return structure.arity;
	}

	public String getFunctor() {
		Struct structure = (Struct) value;
		if (ops.currentOp(structure.name)) {
			return structure.name;
		}
		return Parser.wrapAtom(structure.name);
	}

	public String getIndicator() {
		return getFunctor() + "/" + getArity();
	}

	public boolean hasIndicator(String functor, int arity) {
		return getFunctor().equals(functor) && getArity() == arity;
	}

	public final PrologTerm getRight() {
		return getArgument(1);
	}

	public final PrologTerm getLeft() {
		return getArgument(0);
	}

	public final String getOperator() {
		return getFunctor();
	}

}
