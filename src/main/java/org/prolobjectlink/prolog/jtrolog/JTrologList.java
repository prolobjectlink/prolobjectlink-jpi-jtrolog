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
package org.prolobjectlink.prolog.jtrolog;

import static org.prolobjectlink.prolog.PrologTermType.LIST_TYPE;

import java.util.Iterator;

import org.prolobjectlink.prolog.AbstractIterator;
import org.prolobjectlink.prolog.PrologList;
import org.prolobjectlink.prolog.PrologProvider;
import org.prolobjectlink.prolog.PrologTerm;

import jTrolog.terms.Struct;
import jTrolog.terms.Term;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
class JTrologList extends JTrologTerm implements PrologList {

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
					value = ((JTrologTerm) arguments[length - 1]).value;
					for (int i = length - 2; i >= 0; --i) {
						value = new Struct(".", new Term[] { ((JTrologTerm) arguments[i]).value, value });
					}
				} else {
					value = Term.emptyList;
					for (int i = length - 1; i >= 0; --i) {
						value = new Struct(".", new Term[] { ((JTrologTerm) arguments[i]).value, value });
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
		Term h = ((JTrologTerm) head).value;
		Term t = ((JTrologTerm) tail).value;
		value = new Struct(".", new Term[] { h, t });
	}

	protected JTrologList(PrologProvider provider, PrologTerm[] arguments, PrologTerm tail) {
		super(LIST_TYPE, provider);
		value = ((JTrologTerm) tail).value;
		for (int i = arguments.length - 1; i >= 0; --i) {
			value = new Struct(".", new Term[] { ((JTrologTerm) arguments[i]).value, value });
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

	private class JTrologListIter extends AbstractIterator<PrologTerm> implements Iterator<PrologTerm> {

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

	}

}
