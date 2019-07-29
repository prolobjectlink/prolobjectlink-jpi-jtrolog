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

import java.util.Iterator;

import jTrolog.engine.Prolog;
import jTrolog.parser.Parser;
@SuppressWarnings({ "serial" })
public class StructAtom extends Struct {

	public StructAtom(String name) {
		super(name, new Term[0]);
		type = Term.ATOM;
	}

	public boolean equals(Object t) {
		return t instanceof StructAtom && name == ((StructAtom) t).name;
	}

	public String toString() {
		if (name.isEmpty()) {
			return "''";
		} else if (!Parser.isAtom(name) && !isOperator(name) && !name.equals("[]")) {
			return "'" + name + "'";
		}
		return name;
	}

	public String toStringSmall() {
		return toString();
	}

	private boolean isOperator(String name) {
		Prolog engine = Prolog.defaultMachine;
		Iterator<?> i = engine.getCurrentOperators();
		while (i.hasNext()) {
			Object object = i.next();
			if (object instanceof Struct) {
				Struct o = (Struct) object;
				String n = ((StructAtom) o.getArg(2)).name;
				if (name.equals(n)) {
					return true;
				}
			}
		}
		return false;
	}

}
