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

import jTrolog.terms.Term;
import jTrolog.engine.BindingsTable;

import java.util.Iterator;

/**
 * @author ivar.orstavik@hist.no
 */
public interface Wrapper {

	int getContext();

	Term getBasis();

	static class WrappedIterator implements Iterator {

		private Iterator underlyingIt;
		private int ctx;

		WrappedIterator(Iterator orig, int ctx) {
			underlyingIt = orig;
			this.ctx = ctx;
		}

		public boolean hasNext() {
			return underlyingIt.hasNext();
		}

		public Object next() {
			return BindingsTable.wrapWithID((Term) underlyingIt.next(), ctx);
		}

		public void remove() {
			throw new UnsupportedOperationException("dont remove on Prolog lists");
		}
	}
}
