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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.prolobjectlink.prolog.AbstractProvider;
import io.github.prolobjectlink.prolog.PrologAtom;
import io.github.prolobjectlink.prolog.PrologConverter;
import io.github.prolobjectlink.prolog.PrologDouble;
import io.github.prolobjectlink.prolog.PrologEngine;
import io.github.prolobjectlink.prolog.PrologFloat;
import io.github.prolobjectlink.prolog.PrologInteger;
import io.github.prolobjectlink.prolog.PrologJavaConverter;
import io.github.prolobjectlink.prolog.PrologList;
import io.github.prolobjectlink.prolog.PrologLogger;
import io.github.prolobjectlink.prolog.PrologLong;
import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologStructure;
import io.github.prolobjectlink.prolog.PrologTerm;
import io.github.prolobjectlink.prolog.PrologVariable;
import jTrolog.engine.Prolog;
import jTrolog.parser.Parser;
import jTrolog.terms.Struct;
import jTrolog.terms.Term;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public class JTrolog extends AbstractProvider implements PrologProvider {

	private static final PrologLogger logger = new JTrologLogger();
	static final String VERSION = Prolog.VERSION;
	static final String NAME = "jTrolog";

	public JTrolog() {
		super(new JTrologConverter());
	}

	JTrolog(PrologConverter<Term> converter) {
		super(converter);
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

	public PrologTerm prologInclude(String file) {
		return newStructure("include", newAtom(file));
	}

	// engine

	public PrologEngine newEngine() {
		Prolog prolog = new Prolog();
		return new JTrologEngine(this, prolog);
	}

	public PrologEngine newEngine(String path) {
		PrologEngine engine = newEngine();
		engine.consult(path);
		return engine;
	}

	// parser helpers

	public PrologTerm parseTerm(String term) {
		return toTerm(new Parser(term).nextTerm(false), PrologTerm.class);
	}

	public PrologTerm[] parseTerms(String stringTerms) {
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

	public PrologTerm newReference(Object reference) {
		return new JTrologReference(this, reference);
	}

	public final PrologTerm newEntry(PrologTerm key, PrologTerm value) {
		return new JTrologEntry(this, key, value);
	}

	public final PrologTerm newEntry(Object key, Object value) {
		PrologJavaConverter transformer = getJavaConverter();
		PrologTerm keyTerm = transformer.toTerm(key);
		PrologTerm valueTerm = transformer.toTerm(value);
		return new JTrologEntry(this, keyTerm, valueTerm);
	}

	public final PrologTerm newMap(Map<PrologTerm, PrologTerm> map) {
		return new JTrologMap(this, map);
	}

	public final PrologTerm newMap(int initialCapacity) {
		return new JTrologMap(this, initialCapacity);
	}

	public final PrologTerm newMap() {
		return new JTrologMap(this);
	}

	public PrologTerm falseReference() {
		return newReference(false);
	}

	public PrologTerm trueReference() {
		return newReference(true);
	}

	public PrologTerm nullReference() {
		return newReference(null);
	}

	public PrologTerm voidReference() {
		return newReference(void.class);
	}

	public PrologJavaConverter getJavaConverter() {
		return new JTrologJavaConverter(this);
	}

	public PrologLogger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		return "JTrolog [converter=" + converter + "]";
	}

}
