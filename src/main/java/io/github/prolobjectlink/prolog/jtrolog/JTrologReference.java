/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2021 Prolobjectlink Project
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

import static io.github.prolobjectlink.prolog.PrologTermType.OBJECT_TYPE;

import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.PrologReference;
import io.github.prolobjectlink.prolog.PrologTerm;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;
import jTrolog.terms.Term;

public final class JTrologReference extends JTrologTerm implements PrologReference {

	protected final Object reference;

	protected JTrologReference(PrologProvider provider, Object reference) {
		super(OBJECT_TYPE, provider, new Struct("'@'", new Term[] { new StructAtom("'" + reference + "'") }));
		this.reference = reference;
	}

	@Override
	public int getArity() {
		return 1;
	}

	@Override
	public String getFunctor() {
		return "@";
	}

	@Override
	public PrologTerm[] getArguments() {
		String string = reference.toString();
		PrologTerm tag = provider.newAtom(string);
		return new PrologTerm[] { tag };
	}

	@Override
	public PrologTerm getTerm() {
		String string = reference.toString();
		PrologTerm tag = provider.newAtom(string);
		return provider.newStructure(getFunctor(), tag);
	}

	@Override
	public Class<?> getReferenceType() {
		return reference.getClass();
	}

	public Object getObject() {
		return reference;
	}

	@Override
	public String toString() {
		return "" + getTerm() + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		JTrologReference other = (JTrologReference) obj;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference)) {
			return false;
		}
		return true;
	}
}
