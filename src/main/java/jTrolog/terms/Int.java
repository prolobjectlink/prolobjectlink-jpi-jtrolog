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

/**
 * Int class represents the integer prolog data type
 */
@SuppressWarnings({ "serial" })
public class Int extends jTrolog.terms.Number {

	private int value;

	public Int() {

	}

	public Int(String v) throws NumberFormatException {
		value = java.lang.Integer.parseInt(v);
	}

	public Int(int v) {
		value = v;
	}

	/**
	 * Returns the value of the Integer as int
	 */
	public int intValue() {
		return value;
	}

	/**
	 * Returns the value of the Integer as float
	 */
	public float floatValue() {
		return (float) value;
	}

	/**
	 * Returns the value of the Integer as double
	 */
	public double doubleValue() {
		return (double) value;
	}

	/**
	 * Returns the value of the Integer as long
	 */
	public long longValue() {
		return value;
	}

	public String toString() {
		return java.lang.Integer.toString(value);
	}

	public static Number create(String s) throws NumberFormatException {
		try {
			return new Int(s);
		} catch (NumberFormatException e) {
			return new Long(s);
		}
	}

	public boolean equals(Object n) {
		if (n instanceof Int)
			return longValue() == ((Int) n).longValue();
		return false;
	}
}
