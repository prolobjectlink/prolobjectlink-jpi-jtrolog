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

/**
 * @author ivar.orstavik@hist.no
 */
public class WrapVar extends Var implements Wrapper {

	Var basis;
	int context;
	String[] nameNumbers;

	public WrapVar(Var var, int renameVarID) {
		if (var instanceof WrapVar)
			throw new RuntimeException("building a WrapVar from another WrapVar");
		basis = var;
		context = renameVarID;
	}

	public boolean equals(Object t) {
		return t instanceof WrapVar && context == ((WrapVar) t).context && basis.equals(((WrapVar) t).basis);
	}

	public int hashCode() {
		return basis.hashCode() + context * 100;
	}

	public boolean isAnonymous() {
		return basis.isAnonymous();
	}

	public String toString() {
		return basis.toString();
	}

	public String toStringSmall() {
		return basis.toStringSmall();
	}

	public int getContext() {
		return context;
	}

	public Term getBasis() {
		return basis;
	}
}
