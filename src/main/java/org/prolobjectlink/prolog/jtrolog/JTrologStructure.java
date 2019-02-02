/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
package org.prolobjectlink.prolog.jtrolog;

import static org.prolobjectlink.prolog.PrologTermType.STRUCTURE_TYPE;

import org.prolobjectlink.prolog.PrologProvider;
import org.prolobjectlink.prolog.PrologStructure;
import org.prolobjectlink.prolog.PrologTerm;

import jTrolog.parser.Parser;
import jTrolog.terms.Struct;
import jTrolog.terms.Term;

public class JTrologStructure extends JTrologTerm implements PrologStructure {

	protected JTrologStructure(PrologProvider provider, String functor, PrologTerm... arguments) {
		super(STRUCTURE_TYPE, provider);
		Term[] terms = new Term[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			terms[i] = unwrap(arguments[i], JTrologTerm.class).value;
		}
		value = new Struct(functor, terms);
	}

	protected JTrologStructure(PrologProvider provider, String functor, Term... arguments) {
		super(STRUCTURE_TYPE, provider, new Struct(functor, arguments));
	}

	protected JTrologStructure(PrologProvider provider, PrologTerm left, String operator, PrologTerm right) {
		super(STRUCTURE_TYPE, provider);
		Term leftOperand = left.unwrap(JTrologTerm.class).value;
		Term rightOperand = right.unwrap(JTrologTerm.class).value;
		value = new Struct(operator, new Term[] { leftOperand, rightOperand });
	}

	protected JTrologStructure(PrologProvider provider, Term left, String functor, Term right) {
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

}
