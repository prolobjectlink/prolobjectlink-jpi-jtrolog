/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2018 WorkLogic Project
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
package jTrolog.terms;

import jTrolog.parser.Parser;

import java.util.Iterator;

/**
 * @author ivar.orstavik@hist.no
 */
@SuppressWarnings({ "rawtypes" })
class ListIterator implements Iterator {

	Term hereIAmBaby;

	public ListIterator(Struct origin) {
		hereIAmBaby = origin;
	}

	public boolean hasNext() {
		return hereIAmBaby != null;
	}

	public Object next() {
		if (hereIAmBaby == null)
			throw new IndexOutOfBoundsException("iterating out of list");
		if (hereIAmBaby.equals(Term.emptyList)) {
			hereIAmBaby = null;
			return Term.emptyList;
		}
		if (hereIAmBaby instanceof Struct && ((Struct) hereIAmBaby).predicateIndicator == Parser.listSignature) {
			Term timeToDeliver = ((Struct) hereIAmBaby).getArg(0);
			hereIAmBaby = ((Struct) hereIAmBaby).getArg(1);
			return timeToDeliver;
		}
		Term ImYours = hereIAmBaby;
		hereIAmBaby = null;
		return ImYours;
	}

	public void remove() {
		throw new UnsupportedOperationException("don't delete on List iteration");
	}
}
