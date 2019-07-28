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
package jTrolog.engine;

import jTrolog.terms.Term;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author ivar.orstavik@hist.no
 */
public class Solution {

	private static final HashMap emptyMap = new HashMap();

	HashMap bindings;
	Term solution;

	public Solution(Term solution) {
		this.bindings = emptyMap;
		this.solution = solution;
	}

	public Solution(HashMap bindings, Term solution) {
		this.bindings = bindings;
		this.solution = solution;
	}

	/**
	 * @return true if the query was a success, false otherwise
	 */
	public boolean success() {
		return solution != null;
	}

	/**
	 * @return the solution to the query as a Term
	 */
	public Term getSolution() {
		return solution;
	}

	/**
	 * @return the link of the Variable corresponding to varName, if no Var was
	 *         named varName in the query, null is returned if the Var was
	 *         linked to an any Var, that any Var is returned
	 */
	public Term getBinding(String varName) {
		return (Term) bindings.get(varName);
	}

	public String toString() {
		return solution == null ? "no" : solution.toString();
	}

	public String bindingsToString() {
		StringBuffer buffy = new StringBuffer();
		for (Iterator it = bindings.keySet().iterator(); it.hasNext();) {
			String variable = (String) it.next();
			Term binding = (Term) bindings.get(variable);
			buffy.append(variable).append(": ").append(binding).append("\n");
		}
		return buffy.toString();
	}
}
