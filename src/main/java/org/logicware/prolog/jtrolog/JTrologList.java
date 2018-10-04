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

import static org.logicware.prolog.PrologTermType.LIST_TYPE;

import java.util.Iterator;

import org.logicware.prolog.PrologList;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologTerm;

import jTrolog.terms.Struct;
import jTrolog.terms.Term;

public class JTrologList extends JTrologTerm implements PrologList {

	protected JTrologList(PrologProvider provider) {
		super(LIST_TYPE, provider, Term.emptyList);
	}

	protected JTrologList(PrologProvider provider, Term[] arguments) {
		super(LIST_TYPE, provider);
		if (arguments != null) {
			int length = arguments.length;
			if (length > 0) {
				if (arguments[length - 1].equals(Term.emptyList)) {
					value = arguments[length - 1];
					for (int i = length - 2; i >= 0; --i) {
						value = new Struct(".", new Term[] { arguments[i], value });
					}
				} else {
					value = Term.emptyList;
					for (int i = length - 1; i >= 0; --i) {
						value = new Struct(".", new Term[] { arguments[i], value });
					}
				}
			} else {
				value = Term.emptyList;
			}
		} else {
			value = Term.emptyList;
		}
	}

	protected JTrologList(PrologProvider provider, PrologTerm[] arguments) {
		super(LIST_TYPE, provider);
		if (arguments != null) {
			int length = arguments.length;
			if (length > 0) {
				if (arguments[length - 1].isEmptyList()) {
					value = unwrap(arguments[length - 1], JTrologTerm.class).value;
					for (int i = length - 2; i >= 0; --i) {
						value = new Struct(".", new Term[] { unwrap(arguments[i], JTrologTerm.class).value, value });
					}
				} else {
					value = Term.emptyList;
					for (int i = length - 1; i >= 0; --i) {
						value = new Struct(".", new Term[] { unwrap(arguments[i], JTrologTerm.class).value, value });
					}
				}
			} else {
				value = Term.emptyList;
			}
		} else {
			value = Term.emptyList;
		}
	}

	protected JTrologList(PrologProvider provider, PrologTerm head, PrologTerm tail) {
		super(LIST_TYPE, provider);
		Term h = unwrap(head, JTrologTerm.class).value;
		Term t = unwrap(tail, JTrologTerm.class).value;
		value = new Struct(".", new Term[] { h, t });
	}

	protected JTrologList(PrologProvider provider, Term[] arguments, Term tail) {
		super(LIST_TYPE, provider);
		value = tail;
		for (int i = arguments.length - 1; i >= 0; --i) {
			value = new Struct(".", new Term[] { arguments[i], value });
		}
	}

	protected JTrologList(PrologProvider provider, PrologTerm[] arguments, PrologTerm tail) {
		super(LIST_TYPE, provider);
		value = unwrap(tail, JTrologTerm.class).value;
		for (int i = arguments.length - 1; i >= 0; --i) {
			value = new Struct(".", new Term[] { unwrap(arguments[i], JTrologTerm.class).value, value });
		}
	}

	public int size() {
		int counter = 0;
		Iterator<?> i = iterator();
		while (i.hasNext()) {
			counter++;
			i.next();
		}
		return counter;
	}

	public void clear() {
		value = Term.emptyList;
	}

	public boolean isEmpty() {
		return value == Term.emptyList;
	}

	public Iterator<PrologTerm> iterator() {
		Struct list = (Struct) value;
		return new JTrologListIter(list);
	}

	public PrologTerm getHead() {
		Term head = ((Struct) value).getArg(0);
		return toTerm(head, PrologTerm.class);
	}

	public PrologTerm getTail() {
		Term tail = ((Struct) value).getArg(1);
		return toTerm(tail, PrologTerm.class);
	}

	public int getArity() {
		return ((Struct) value).arity;
	}

	public String getFunctor() {
		return ((Struct) value).name;
	}

	public String getIndicator() {
		return getFunctor() + "/" + getArity();
	}

	public boolean hasIndicator(String functor, int arity) {
		return getFunctor().equals(functor) && getArity() == arity;
	}

	public PrologTerm[] getArguments() {
		PrologTerm[] a = new PrologTerm[size()];
		Iterator<PrologTerm> i = iterator();
		for (int index = 0; i.hasNext(); index++) {
			a[index] = i.next();
		}
		return a;
	}

	private class JTrologListIter implements Iterator<PrologTerm> {

		private PrologTerm next;
		private final Iterator<?> i;

		private JTrologListIter(Struct list) {
			i = Struct.iterator(list);
			if (i.hasNext()) {
				next = toTerm(i.next(), PrologTerm.class);
			}
		}

		public boolean hasNext() {
			return next != null && !next.isEmptyList();
		}

		public PrologTerm next() {
			PrologTerm lastReturned = next;
			next = toTerm(i.next(), PrologTerm.class);
			return lastReturned;
		}

		public void remove() {
			i.remove();
		}

	}

}
