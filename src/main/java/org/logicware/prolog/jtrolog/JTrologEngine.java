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

import static org.logicware.logging.LoggerConstants.DONT_WORRY;
import static org.logicware.logging.LoggerConstants.FILE_NOT_FOUND;
import static org.logicware.logging.LoggerConstants.INDICATOR_NOT_FOUND;
import static org.logicware.logging.LoggerConstants.IO;
import static org.logicware.logging.LoggerConstants.RUNTIME_ERROR;
import static org.logicware.logging.LoggerConstants.SYNTAX_ERROR;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.logicware.Licenses;
import org.logicware.logging.LoggerConstants;
import org.logicware.logging.LoggerUtils;
import org.logicware.prolog.AbstractEngine;
import org.logicware.prolog.OperatorEntry;
import org.logicware.prolog.PredicateIndicator;
import org.logicware.prolog.PrologClause;
import org.logicware.prolog.PrologEngine;
import org.logicware.prolog.PrologIndicator;
import org.logicware.prolog.PrologOperator;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologQuery;
import org.logicware.prolog.PrologTerm;

import jTrolog.engine.Prolog;
import jTrolog.errors.PrologException;
import jTrolog.lib.BuiltIn;
import jTrolog.lib.IOLibrary;
import jTrolog.lib.Library;
import jTrolog.parser.Parser;
import jTrolog.terms.Clause;
import jTrolog.terms.Int;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;
import jTrolog.terms.Term;

public final class JTrologEngine extends AbstractEngine implements PrologEngine {

	final Prolog engine;

	protected JTrologEngine(PrologProvider provider) {
		this(provider, new Prolog());
	}

	protected JTrologEngine(PrologProvider provider, Prolog engine) {
		super(provider);
		this.engine = engine;
	}

	public void consult(String path) {
		engine.clearTheory();
		include(path);
	}

	public void persist(String path) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(path);
			writer.write(engine.getTheory());
		} catch (IOException e) {
			LoggerUtils.warn(getClass(), IO + path, e);
			LoggerUtils.info(getClass(), DONT_WORRY + path);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LoggerUtils.warn(getClass(), IO + path, e);
				}
			}
		}
	}

	public void include(String path) {
		try {
			InputStream is = new FileInputStream(path);
			engine.addTheory(IOLibrary.readStream(is));
		} catch (FileNotFoundException e) {
			LoggerUtils.warn(getClass(), FILE_NOT_FOUND + path, e);
		} catch (PrologException e) {
			LoggerUtils.error(getClass(), SYNTAX_ERROR + path, e);
		} catch (IOException e) {
			LoggerUtils.warn(getClass(), IO + path, e);
		}
	}

	public void abolish(String functor, int arity) {
		String pi = functor + "/" + arity;
		try {
			engine.abolish(pi);
		} catch (PrologException e) {
			LoggerUtils.error(getClass(), INDICATOR_NOT_FOUND, e);
		}
	}

	private boolean exist(Clause clause) {
		String name = clause.head.name;
		StructAtom functor = new StructAtom(name);
		String key = functor + "/" + clause.head.arity;
		Iterator<?> i = engine.dynamicPredicateIndicators();
		while (i.hasNext()) {
			String predIndicator = (String) i.next();
			if (predIndicator.equals(key)) {
				try {
					List<?> list = engine.find(predIndicator);
					for (Object object : list) {
						if (object instanceof Clause) {
							Clause iclause = (Clause) object;
							if (iclause.head.equals(clause.head)) {

								Struct[] xclausetail = iclause.tail.length > 0 ? iclause.tail
										: new Struct[] { (Struct) Term.TRUE };
								Struct[] yclausetail = clause.tail.length > 0 ? clause.tail
										: new Struct[] { (Struct) Term.TRUE };

								if (xclausetail.length != yclausetail.length) {
									return false;
								}

								for (int j = 0; j < yclausetail.length; j++) {
									if (!xclausetail[j].equals(yclausetail[j])) {
										return false;
									}

								}

								return true;
							}
						}
					}
				} catch (PrologException e) {
					LoggerUtils.error(getClass(), INDICATOR_NOT_FOUND, e);
				}
			}
		}
		return false;
	}

	public void asserta(String stringClause) {
		try {
			Term term = new Parser(stringClause).nextTerm(false);
			asserta(BuiltIn.convertTermToClause(term));
		} catch (PrologException e) {
			LoggerUtils.error(getClass(), SYNTAX_ERROR + stringClause, e);
		}
	}

	public void asserta(PrologTerm head, PrologTerm... body) {
		Struct h = fromTerm(head, Struct.class);
		Struct[] b = new Struct[body.length];
		for (int i = 0; i < body.length; i++) {
			b[i] = fromTerm(body[i], Struct.class);
		}
		Struct o = fromTerm(head, body, Struct.class);
		asserta(new Clause(b, h, o));
	}

	private void asserta(Clause clause) {
		if (!exist(clause)) {
			try {
				engine.assertA(clause);
			} catch (PrologException e) {
				LoggerUtils.error(getClass(), RUNTIME_ERROR, e);
			}
		}
	}

	public void assertz(String stringClause) {
		try {
			Term term = new Parser(stringClause).nextTerm(false);
			assertz(BuiltIn.convertTermToClause(term));
		} catch (PrologException e) {
			LoggerUtils.error(getClass(), SYNTAX_ERROR + stringClause, e);
		}
	}

	public void assertz(PrologTerm head, PrologTerm... body) {
		Struct h = fromTerm(head, Struct.class);
		Struct[] b = new Struct[body.length];
		for (int i = 0; i < body.length; i++) {
			b[i] = fromTerm(body[i], Struct.class);
		}
		Struct o = fromTerm(head, body, Struct.class);
		assertz(new Clause(b, h, o));
	}

	private void assertz(Clause clause) {
		if (!exist(clause)) {
			try {
				engine.assertZ(clause);
			} catch (PrologException e) {
				LoggerUtils.error(getClass(), RUNTIME_ERROR, e);
			}
		}
	}

	public boolean clause(String stringClause) {
		try {
			Term term = new Parser(stringClause).nextTerm(false);
			return clause(BuiltIn.convertTermToClause(term));
		} catch (PrologException e) {
			LoggerUtils.error(getClass(), SYNTAX_ERROR + stringClause, e);
		}
		return false;
	}

	public boolean clause(PrologTerm head, PrologTerm... body) {
		Struct h = fromTerm(head, Struct.class);
		Struct[] b = new Struct[body.length];
		for (int i = 0; i < body.length; i++) {
			b[i] = fromTerm(body[i], Struct.class);
		}
		Struct o = fromTerm(head, body, Struct.class);
		return clause(new Clause(b, h, o));
	}

	private boolean clause(Clause clause) {
		String key = clause.head.name + "/" + clause.head.arity;
		Iterator<?> i = engine.dynamicPredicateIndicators();
		while (i.hasNext()) {
			String predIndicator = (String) i.next();
			if (predIndicator.equals(key)) {
				try {
					List<?> list = engine.find(predIndicator);
					for (Object object : list) {
						if (object instanceof Clause) {
							Clause c = (Clause) object;
							if (Prolog.match(c.original, clause.original)) {
								return true;
							}
						}
					}
				} catch (PrologException e) {
					LoggerUtils.error(getClass(), INDICATOR_NOT_FOUND + predIndicator, e);
				}
			}
		}
		return false;
	}

	public void retract(String stringClause) {
		try {
			Term term = new Parser(stringClause).nextTerm(false);
			retract(BuiltIn.convertTermToClause(term));
		} catch (PrologException e) {
			LoggerUtils.error(getClass(), SYNTAX_ERROR + stringClause, e);
		}
	}

	public void retract(PrologTerm head, PrologTerm... body) {
		Struct h = fromTerm(head, Struct.class);
		Struct[] b = new Struct[body.length];
		for (int i = 0; i < body.length; i++) {
			b[i] = fromTerm(body[i], Struct.class);
		}
		Struct o = fromTerm(head, body, Struct.class);
		retract(new Clause(b, h, o));
	}

	private void retract(Clause clause) {
		try {
			engine.retract(clause.original);
		} catch (PrologException e) {
			LoggerUtils.error(getClass(), RUNTIME_ERROR, e);
		}
	}

	public PrologQuery query(String stringQuery) {
		return new JTrologQuery(this, stringQuery);
	}

	public PrologQuery query(PrologTerm... terms) {
		return new JTrologQuery(this, terms);
	}

	public void operator(int priority, String specifier, String operator) {
		engine.opNew(operator, specifier, priority);
	}

	public boolean currentPredicate(String functor, int arity) {
		String key = Parser.wrapAtom(functor) + "/" + arity;

		// supported built-ins
		boolean isBuiltin = engine.hasPrimitive(key) || engine.hasPrimitiveExp(key);

		// user defined predicates
		if (!isBuiltin) {
			try {
				if (!engine.find(key).isEmpty()) {
					return true;
				}
			} catch (PrologException e) {
				LoggerUtils.error(getClass(), LoggerConstants.INDICATOR_NOT_FOUND + key, e);
			}
		}

		// not defined
		return isBuiltin;
	}

	public boolean currentOperator(int priority, String specifier, String operator) {
		return currentOperators().contains(new OperatorEntry(priority, specifier, operator));
	}

	public Set<PrologIndicator> currentPredicates() {

		// built-ins on libraries
		Iterator<?> libraries = engine.getCurrentLibraries();
		Set<PrologIndicator> builtins = new HashSet<PrologIndicator>();
		while (libraries.hasNext()) {
			Object object = libraries.next();
			if (object instanceof Library) {
				Library library = (Library) object;
				Class<? extends Library> c = library.getClass();
				Method[] methods = c.getDeclaredMethods();
				String regex = "\\.|\\?|#|[a-z][A-Za-z0-9_]*_[0-9]+";
                            for (Method method1 : methods) {
                                String method = method1.getName();
                                if (method.matches(regex)) {
                                    int j = method.lastIndexOf('_');
                                    String f = method.substring(0, j);
                                    int a = Integer.parseInt(method.substring(j + 1));
                                    builtins.add(new PredicateIndicator(f, a));
                                }
                            }
			}
		}

		// user defined predicates
		Iterator<?> i = engine.dynamicPredicateIndicators();
		while (i.hasNext()) {
			String predIndicator = (String) i.next();
			try {
				List<?> list = engine.find(predIndicator);
                            for (Object object : list) {
                                if (object instanceof Clause) {
                                    Clause clause = (Clause) object;
                                    String functor = clause.head.name;
                                    int arity = clause.head.arity;
                                    PredicateIndicator p = new PredicateIndicator(functor, arity);
                                    builtins.add(p);
                                }
                            }
			} catch (PrologException e) {
				LoggerUtils.error(getClass(), LoggerConstants.INDICATOR_NOT_FOUND + predIndicator, e);
			}
		}
		return builtins;
	}

	public Set<PrologOperator> currentOperators() {
		Set<PrologOperator> operators = new HashSet<PrologOperator>();
		Iterator<?> i = engine.getCurrentOperators();
		while (i.hasNext()) {
			Object object = i.next();
			if (object instanceof Struct) {
				Struct o = (Struct) object;
				String name = ((StructAtom) o.getArg(2)).name;
				int priority = ((Int) o.getArg(0)).intValue();
				String specifier = ((StructAtom) o.getArg(1)).name;
				OperatorEntry op = new OperatorEntry(priority, specifier, name);
				operators.add(op);
			}
		}
		return operators;
	}

	public Iterator<PrologClause> iterator() {
		Collection<PrologClause> cls = new LinkedList<PrologClause>();
		Parser parser = new Parser(engine.getTheory());
		for (Iterator<?> iterator = parser.iterator(); iterator.hasNext();) {
			Term term = (Term) iterator.next();
			if (term instanceof Struct) {
				Struct struct = (Struct) term;
				if (struct.name.equals(":-") && struct.arity == 2) {
					PrologTerm head = toTerm(struct.getArg(0), PrologTerm.class);
					PrologTerm body = toTerm(struct.getArg(1), PrologTerm.class);
					cls.add(new JTrologClause(provider, head, body, false, false, false));
				} else {
					PrologTerm head = toTerm(struct, PrologTerm.class);
					cls.add(new JTrologClause(provider, head, false, false, false));
				}
			}
		}
		return new PrologProgramIterator(cls);
	}

	public int getProgramSize() {
		int counter = 0;
		Iterator<?> i = engine.dynamicPredicateIndicators();
		while (i.hasNext()) {
			String predIndicator = (String) i.next();
			try {
				List<?> list = engine.find(predIndicator);
				counter += list.size();
			} catch (PrologException e) {
				LoggerUtils.error(getClass(), LoggerConstants.INDICATOR_NOT_FOUND + predIndicator, e);
			}
		}
		return counter;
	}

	public String getLicense() {
		return Licenses.NO_SPECIFIED;
	}

	public String getVersion() {
		return Prolog.VERSION;
	}

	public String getName() {
		return "jTrolog";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
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
		JTrologEngine other = (JTrologEngine) obj;
		return engine != null && other.engine != null;
	}

	public void dispose() {
		if (engine != null) {
			engine.clearTheory();
		}
	}

}
