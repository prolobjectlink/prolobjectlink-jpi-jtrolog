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

import io.github.prolobjectlink.prolog.PrologAtom;
import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologTerm;
import jTrolog.terms.StructAtom;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
final class JTrologAtom extends JTrologTerm implements PrologAtom {

	JTrologAtom(PrologProvider provider, String name) {
		super(ATOM_TYPE, provider, new StructAtom(name));
	}

	public String getStringValue() {
		return getFunctor();
	}

	public void setStringValue(String value) {
		this.value = new StructAtom(value);
	}

	public PrologTerm[] getArguments() {
		return new JTrologAtom[0];
	}

	public int getArity() {
		return 0;
	}

	public String getFunctor() {
		return "" + value + "";
	}

	public String getIndicator() {
		return getFunctor() + "/" + getArity();
	}

	public boolean hasIndicator(String functor, int arity) {
		return getFunctor().equals(functor) && getArity() == arity;
	}

}