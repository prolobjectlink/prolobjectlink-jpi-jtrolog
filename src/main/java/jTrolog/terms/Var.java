/*
 * #%L
 * prolobjectlink-jtrolog
 * %%
 * Copyright (C) 2012 - 2018 WorkLogic Project
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package jTrolog.terms;

import jTrolog.errors.InvalidTermException;

/**
 * This class represents a variable term. Variables are identified by a name
 * (which must starts with an upper case letter) or the anonymous ('_') name.
 * 
 * @see Term
 */
public class Var extends Term {

	public final static String ANY = "_".intern();

	private String name;
	public final int nrInStruct;

	/**
	 * needed to implement Wrapper Objects. Do not use to stringToStructList ANY
	 * Vars
	 */
	Var() {
		type = Term.VAR;
		nrInStruct = 0;
	}

	/**
	 * Creates a variable identified by a name.
	 * 
	 * @param n
	 *            varaible name if n is "_" the variable is anonymous.
	 * @param nr
	 *            its variable number of its root parent Struct.
	 */
	public Var(String n, int nr) throws InvalidTermException {
		type = Term.VAR;
		name = n.intern();
		nrInStruct = nr;
	}

	public boolean equals(Object t) {
		return t instanceof Var && nrInStruct == ((Var) t).nrInStruct;
	}

	public int hashCode() {
		return nrInStruct;
	}

	/**
	 * Tests if this variable is ANY
	 */
	public boolean isAnonymous() {
		return name == ANY;
	}

	/**
	 * Gets the string representation of this variable.
	 */
	public String toString() {
		return name;
	}

	public String toStringSmall() {
		return name;
	}

}
