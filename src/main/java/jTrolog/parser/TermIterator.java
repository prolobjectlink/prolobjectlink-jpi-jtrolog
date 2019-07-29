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
package jTrolog.parser;

import jTrolog.terms.Term;

import java.util.NoSuchElementException;

/**
 * This class represents an iterator of terms from a string.
 * 
 * @see jTrolog.terms.Term
 */
@SuppressWarnings({ "rawtypes", "serial" })
class TermIterator implements java.util.Iterator, java.io.Serializable {

	private Parser parser;
	private boolean hasNext;
	private Term next;

	TermIterator(Parser p) {
		parser = p;
		next = parser.nextTerm(true);
		hasNext = (next != null);
	}

	public Object next() {
		if (hasNext) {
			if (next == null)
				next = parser.nextTerm(true);
			hasNext = false;
			Term temp = next;
			next = null;
			return temp;
		} else if (hasNext()) {
			hasNext = false;
			Term temp = next;
			next = null;
			return temp;
		}
		throw new NoSuchElementException();
	}

	public boolean hasNext() {
		if (hasNext)
			return hasNext;
		next = parser.nextTerm(true);
		if (next != null)
			hasNext = true;
		return hasNext;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
