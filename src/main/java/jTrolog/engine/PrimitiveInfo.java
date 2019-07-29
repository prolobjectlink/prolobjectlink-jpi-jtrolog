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
package jTrolog.engine;

import jTrolog.parser.Parser;
import jTrolog.lib.Library;

import java.lang.reflect.Method;

/**
 * Wrapper for primitive library methods
 * 
 * @author ivar.orstavik@hist.no
 */
class PrimitiveInfo {

	final Method method;
	final Library source;
	final String key;

	PrimitiveInfo(Library lib, Method m, String functor, int arity) {
		key = Parser.wrapAtom(functor) + "/" + arity;
		source = lib;
		method = m;
	}

	public String toString() {
		return "[ primitive: method " + method.getName() + " - " + source.getClass().getName() + " ]\n";
	}
}
