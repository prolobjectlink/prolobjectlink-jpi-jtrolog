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

/**
 * Wrapper object to store both the original Struct and its converted clause in
 * the same object
 * 
 * @author ivar.orstavik@hist.no
 */
public class Clause {

	public final Struct[] tail;
	public final Struct head;
	public final Struct original;

	public Clause(Struct[] tail, Struct head, Struct original) {
		this.tail = tail;
		this.head = head;
		this.original = original;
	}
}
