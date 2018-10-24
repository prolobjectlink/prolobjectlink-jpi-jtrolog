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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.logicware.logging.LoggerConstants;
import org.logicware.logging.LoggerUtils;
import org.logicware.prolog.AbstractEngine;
import org.logicware.prolog.AbstractQuery;
import org.logicware.prolog.PrologQuery;
import org.logicware.prolog.PrologTerm;

import jTrolog.engine.Prolog;
import jTrolog.engine.Solution;
import jTrolog.parser.Parser;
import jTrolog.terms.Struct;
import jTrolog.terms.Term;
import jTrolog.terms.Var;

public class JTrologQuery extends AbstractQuery implements PrologQuery {

	private Solution solution;
	private Prolog jtrolog;
	private List<String> variables = new ArrayList<String>();

	private void enumerateVariables(List<String> vector, Term term) {
		if (!(term instanceof Var)) {
			if (term instanceof Struct) {
				Struct struct = (Struct) term;
				Var[] vars = struct.getVarList();
				for (Var var : vars) {
					enumerateVariables(variables, var);
				}
			}
		} else if (!vector.contains(term.toString())) {
			vector.add(term.toString());
		}
	}

	JTrologQuery(AbstractEngine engine, String query) {
		super(engine);
		jtrolog = engine.unwrap(JTrologEngine.class).engine;
		enumerateVariables(variables, new Parser(query).nextTerm(false));
		try {
			this.solution = jtrolog.solve("" + query + ".");
		} catch (Throwable e) {
			// do nothing
		}
	}

	JTrologQuery(AbstractEngine engine, PrologTerm[] terms) {
		super(engine);
		if (terms != null && terms.length > 0) {
			jtrolog = engine.unwrap(JTrologEngine.class).engine;
			enumerateVariables(variables, fromTerm(terms[terms.length - 1], Term.class));
			for (int i = terms.length; i > 1; i--) {
				enumerateVariables(variables, fromTerm(terms[i - 2], Term.class));
			}
			String str = Arrays.toString(terms).substring(1);
			str = str.substring(0, str.length() - 1) + '.';
			try {
				this.solution = jtrolog.solve(str);
			} catch (Throwable e) {
				// do nothing
			}
		}
	}

	public boolean hasSolution() {
		return solution != null && solution.success();
	}

	public boolean hasMoreSolutions() {
		try {
			return jtrolog.hasOpenAlternatives();
		} catch (Throwable e) {
			// do nothing
		}
		return false;
	}

	public PrologTerm[] oneSolution() {
		int index = 0;
		Map<String, PrologTerm> solutionMap = oneVariablesSolution();
		PrologTerm[] array = new PrologTerm[solutionMap.size()];
		if (array.length > 0) {
			for (Iterator<String> i = variables.iterator(); i.hasNext();) {
				array[index++] = solutionMap.get(i.next());
			}
		}
		return array;
	}

	public Map<String, PrologTerm> oneVariablesSolution() {
		Map<String, PrologTerm> map = new HashMap<String, PrologTerm>();
		for (String vName : variables) {
			if (solution != null) {
				Term vtTerm = solution.getBinding(vName);
				if (vtTerm != null) {
					PrologTerm pTerm = toTerm(vtTerm, PrologTerm.class);
					map.put(vName, pTerm);
				}
			}
		}
		return map;
	}

	public PrologTerm[] nextSolution() {
		PrologTerm[] array = oneSolution();
		try {
			if (hasMoreSolutions()) {
				solution = jtrolog.solveNext();
				return array;
			}
		} catch (Throwable e) {
			LoggerUtils.error(getClass(), LoggerConstants.NON_SOLUTION, e);
		}
		return array;
	}

	public Map<String, PrologTerm> nextVariablesSolution() {
		Map<String, PrologTerm> map = oneVariablesSolution();
		try {

			if (hasMoreSolutions()) {
				solution = jtrolog.solveNext();
			}
			return map;
		} catch (Throwable e) {
			LoggerUtils.error(getClass(), LoggerConstants.NON_SOLUTION, e);
		}
		return new HashMap<String, PrologTerm>(0);
	}

	public PrologTerm[][] nSolutions(int n) {
		if (n > 0) {
			// m:solutionSize
			int m = 0;
			int index = 0;
			List<PrologTerm[]> all = new ArrayList<PrologTerm[]>();

			PrologTerm[] array = oneSolution();
			m = array.length > m ? array.length : m;
			index++;
			all.add(array);

			while (hasMoreSolutions() && index < n) {
				try {
					solution = jtrolog.solveNext();
					array = oneSolution();
					if (array.length > 0 && !contains(all, array)) {
						m = array.length > m ? array.length : m;
						index++;
						all.add(array);
					}
				} catch (Throwable e) {
					LoggerUtils.error(getClass(), LoggerConstants.NON_SOLUTION, e);
				}

			}

			PrologTerm[][] allSolutions = new PrologTerm[n][m];
			for (int i = 0; i < n; i++) {
				array = all.get(i);
				System.arraycopy(array, 0, allSolutions[i], 0, m);
			}
			return allSolutions;
		}
		return new PrologTerm[0][0];
	}

	public Map<String, PrologTerm>[] nVariablesSolutions(int n) {
		if (n > 0) {
			int index = 0;
			Map<String, PrologTerm>[] solutionMaps = new HashMap[n];

			Map<String, PrologTerm> solutionMap = oneVariablesSolution();
			solutionMaps[index++] = solutionMap;

			while (hasMoreSolutions() && index < n) {
				try {
					solution = jtrolog.solveNext();
					solutionMap = oneVariablesSolution();
					solutionMaps[index++] = solutionMap;
				} catch (Throwable e) {
					LoggerUtils.error(getClass(), LoggerConstants.NON_SOLUTION, e);
				}
			}
			return solutionMaps;
		}
		return new HashMap[0];
	}

	public PrologTerm[][] allSolutions() {
		// n:solutionCount, m:solutionSize
		int n = 0;
		int m = 0;
		List<PrologTerm[]> all = new ArrayList<PrologTerm[]>();

		PrologTerm[] array = oneSolution();
		if (array.length > 0) {
			m = array.length > m ? array.length : m;
			n++;
			all.add(array);
		}

		while (hasMoreSolutions()) {
			try {
				solution = jtrolog.solveNext();
				array = oneSolution();
				if (array.length > 0 && !contains(all, array)) {
					m = array.length > m ? array.length : m;
					n++;
					all.add(array);
				}
			} catch (Throwable e) {
				LoggerUtils.error(getClass(), LoggerConstants.NON_SOLUTION, e);
			}

		}

		PrologTerm[][] allSolutions = new PrologTerm[n][m];
		for (int i = 0; i < n; i++) {
			array = all.get(i);
			System.arraycopy(array, 0, allSolutions[i], 0, m);
		}
		return allSolutions;
	}

	public Map<String, PrologTerm>[] allVariablesSolutions() {
		List<Map<String, PrologTerm>> allVariables = new ArrayList<Map<String, PrologTerm>>();

		Map<String, PrologTerm> varMap = oneVariablesSolution();
		if (!varMap.isEmpty()) {
			allVariables.add(varMap);
		}

		while (hasMoreSolutions()) {
			try {
				solution = jtrolog.solveNext();
				varMap = oneVariablesSolution();
				if (!varMap.isEmpty() && !contains(allVariables, varMap)) {
					allVariables.add(varMap);
				}
			} catch (Throwable e) {
				LoggerUtils.error(getClass(), LoggerConstants.NON_SOLUTION, e);
			}
		}

		int lenght = allVariables.size();
		Map<String, PrologTerm>[] allVariablesSolution = new HashMap[lenght];
		for (int i = 0; i < lenght; i++) {
			allVariablesSolution[i] = allVariables.get(i);
		}
		return allVariablesSolution;
	}

	public List<Map<String, PrologTerm>> all() {
		List<Map<String, PrologTerm>> all = new ArrayList<Map<String, PrologTerm>>();

		Map<String, PrologTerm> varMap = oneVariablesSolution();
		if (!varMap.isEmpty()) {
			all.add(varMap);
		}

		while (hasMoreSolutions()) {
			try {
				solution = jtrolog.solveNext();
				varMap = oneVariablesSolution();
				if (!varMap.isEmpty() && !contains(all, varMap)) {
					all.add(varMap);
				}
			} catch (Throwable e) {
				LoggerUtils.error(getClass(), LoggerConstants.NON_SOLUTION, e);
			}
		}

		return all;
	}

	@Override
	public String toString() {
		return "" + solution + "";
	}

	public void dispose() {
		solution = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hashCode(solution);
		result = prime * result + Objects.hashCode(variables);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		JTrologQuery other = (JTrologQuery) obj;
		if (!Objects.equals(solution, other.solution))
			return false;
		return Objects.equals(variables, other.variables);
	}

}
