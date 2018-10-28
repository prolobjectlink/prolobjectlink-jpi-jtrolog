/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
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

import java.util.ArrayList;
import java.util.List;

import org.logicware.prolog.AbstractProvider;
import org.logicware.prolog.PrologAtom;
import org.logicware.prolog.PrologConverter;
import org.logicware.prolog.PrologDouble;
import org.logicware.prolog.PrologEngine;
import org.logicware.prolog.PrologFloat;
import org.logicware.prolog.PrologInteger;
import org.logicware.prolog.PrologList;
import org.logicware.prolog.PrologLong;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologStructure;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologVariable;

import jTrolog.parser.Parser;
import jTrolog.terms.Struct;
import jTrolog.terms.Term;

public class JTrolog extends AbstractProvider implements PrologProvider {

	public JTrolog() {
		super(new JTrologConverter());
	}

	public JTrolog(PrologConverter<Term> converter) {
		super(converter);
	}

	public boolean isCompliant() {
		return false;
	}

	public PrologTerm prologNil() {
		return new JTrologNil(this);
	}

	public PrologTerm prologCut() {
		return new JTrologCut(this);
	}

	public PrologTerm prologFail() {
		return new JTrologFail(this);
	}

	public PrologTerm prologTrue() {
		return new JTrologTrue(this);
	}

	public PrologTerm prologFalse() {
		return new JTrologFalse(this);
	}

	public PrologTerm prologEmpty() {
		return new JTrologEmpty(this);
	}

	// engine

	public PrologEngine newEngine() {
		return new JTrologEngine(this);
	}

	// parser helpers

	public PrologTerm parsePrologTerm(String term) {
		return toTerm(new Parser(term).nextTerm(false), PrologTerm.class);
	}

	public PrologTerm[] parsePrologTerms(String stringTerms) {
		List<PrologTerm> list = new ArrayList<PrologTerm>();
		Parser parser = new Parser(stringTerms);
		Term term = parser.nextTerm(false);
		while (term instanceof Struct) {
			Struct struct = (Struct) term;
			if (struct.name.equals(",") && struct.arity == 2) {
				list.add(toTerm(struct.getArg(0), PrologTerm.class));
				term = struct.getArg(1);
			} else {
				list.add(toTerm(term, PrologTerm.class));
				term = parser.nextTerm(false);
			}
		}
		return list.toArray(new PrologTerm[0]);
	}

	// terms

	public PrologAtom newAtom(String functor) {
		return new JTrologAtom(this, functor);
	}

	public PrologFloat newFloat(Number value) {
		return new JTrologFloat(this, value);
	}

	public PrologDouble newDouble(Number value) {
		return new JTrologDouble(this, value);
	}

	public PrologInteger newInteger(Number value) {
		return new JTrologInteger(this, value);
	}

	public PrologLong newLong(Number value) {
		return new JTrologLong(this, value);
	}

	public PrologVariable newVariable(int position) {
		if (position < 0) {
			throw new IllegalArgumentException("Not allowed negative position");
		}
		return new JTrologVariable(this, position + 1);
	}

	public PrologVariable newVariable(String name, int position) {
		if (position < 0) {
			throw new IllegalArgumentException("Not allowed negative position");
		}
		return new JTrologVariable(this, name, position + 1);
	}

	public PrologList newList() {
		return new JTrologEmpty(this);
	}

	public PrologList newList(PrologTerm[] arguments) {
		if (arguments != null && arguments.length > 0) {
			return new JTrologList(this, arguments);
		}
		return new JTrologEmpty(this);
	}

	public PrologList newList(PrologTerm head, PrologTerm tail) {
		return new JTrologList(this, head, tail);
	}

	public PrologList newList(PrologTerm[] arguments, PrologTerm tail) {
		return new JTrologList(this, arguments, tail);
	}

	public PrologStructure newStructure(String functor, PrologTerm... arguments) {
		return new JTrologStructure(this, functor, arguments);
	}

	public PrologTerm newStructure(PrologTerm left, String operator, PrologTerm right) {
		return new JTrologStructure(this, left, operator, right);
	}

	@Override
	public String toString() {
		return "TuPrologProvider [converter=" + converter + "]";
	}

}
