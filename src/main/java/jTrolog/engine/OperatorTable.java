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

import jTrolog.terms.Int;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;
import jTrolog.terms.Term;

import java.util.*;

/**
 * Map of Prolog operators. A LinkedHashMap is set up to store each registered
 * operator as: 'name type' -> priority
 * 
 * @author ivar.orstavik@hist.no
 */
@SuppressWarnings({ "rawtypes", "unchecked","serial" })
class OperatorTable implements java.io.Serializable {

	/** current known operators */
	private HashMap[] operatorMap = new HashMap[] { new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap() };

	/**
	 * Creates a new operator. If the operator is already provided, it replaces
	 * it with the new one
	 */
	protected void addOperator(String name, int type, int prio) {
		if (prio >= Prolog.OP_LOW && prio <= Prolog.OP_HIGH)
			operatorMap[type].put(name, new Integer(prio));
	}

	/**
	 * @return the priority of an operator (0 if the operator is not defined).
	 */
	int getOperatorPriority(String name, int type) {
		Integer prio = (Integer) operatorMap[type].get(name);
		return (prio == null) ? 0 : prio.intValue();
	}

	/**
	 * @return a list of Struct Objects representing the operators currently
	 *         defined, ordered by insertionOrder
	 */
	Iterator getAllOperators() {
		return new OperatorIterator(operatorMap);
	}

	private static class OperatorIterator implements Iterator {
		Iterator underlyingIT;
		Map[] underlyingMap;
		int pos = 0;

		public OperatorIterator(Map[] map) {
			this.underlyingIT = map[pos].keySet().iterator();
			this.underlyingMap = map;
		}

		public boolean hasNext() {
			boolean b = underlyingIT.hasNext();
			if (b)
				return true;
			pos++;
			if (pos < underlyingMap.length) {
				underlyingIT = underlyingMap[pos].keySet().iterator();
				return hasNext();
			}
			return false;
		}

		public Object next() {
			if (!hasNext())
				throw new RuntimeException("check hasNext before calling next on OperatorIterator.");
			String name = (String) underlyingIT.next();
			int prio = ((Integer) underlyingMap[pos].get(name)).intValue();
			String type;
			switch (pos) {
			case Prolog.FX:
				type = "fx";
				break;
			case Prolog.FY:
				type = "fy";
				break;
			case Prolog.XFX:
				type = "xfx";
				break;
			case Prolog.YFX:
				type = "yfx";
				break;
			case Prolog.XFY:
				type = "xfy";
				break;
			case Prolog.XF:
				type = "xf";
				break;
			default:
				type = "yf";
			}
			return new Struct("op", new Term[] { new Int(prio), new StructAtom(type), new StructAtom(name) });
		}

		public void remove() {
			throw new UnsupportedOperationException("can't delete on the operator iterator");
		}
	}

}
