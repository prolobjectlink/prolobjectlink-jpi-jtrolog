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

import static io.github.prolobjectlink.prolog.PrologTermType.VARIABLE_TYPE;

import io.github.prolobjectlink.prolog.ArityError;
import io.github.prolobjectlink.prolog.FunctorError;
import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologTerm;
import io.github.prolobjectlink.prolog.PrologVariable;
import jTrolog.terms.Term;
import jTrolog.terms.Var;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
class JTrologVariable extends JTrologTerm implements PrologVariable {

	private String name;

	JTrologVariable(PrologProvider provider, int n) {
		this(provider, "_", n);
		this.name = "_";
	}

	JTrologVariable(PrologProvider provider, String name, int n) {
		super(VARIABLE_TYPE, provider, name, n);
		this.name = name;
	}

	JTrologVariable(int type, PrologProvider provider) {
		super(type, provider);
	}

	JTrologVariable(int type, PrologProvider provider, String name, int n) {
		super(type, provider, name, n);
		this.name = name;
	}

	JTrologVariable(int type, PrologProvider provider, Term var) {
		super(type, provider, var);
	}

	public boolean isAnonymous() {
		return ((Var) value).isAnonymous();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PrologTerm[] getArguments() {
		return new JTrologVariable[0];
	}

	public int getArity() {
		throw new ArityError(this);
	}

	public String getFunctor() {
		throw new FunctorError(this);
	}

	public int getPosition() {
		return vIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JTrologVariable other = (JTrologVariable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
