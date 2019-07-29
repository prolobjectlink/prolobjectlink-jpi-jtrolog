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
 * Double class represents the double prolog data type
 */
@SuppressWarnings("serial")
public class Double extends jTrolog.terms.Float {

	private double value;

	public Double(double v) {
		value = v;
	}

	public Double(String v) throws NumberFormatException {
		value = java.lang.Double.parseDouble(v);
	}

	/**
	 * Returns the value of the Double as int
	 */
	final public int intValue() {
		return (int) value;
	}

	/**
	 * Returns the value of the Double as float
	 */
	final public float floatValue() {
		return (float) value;
	}

	/**
	 * Returns the value of the Double as double
	 */
	final public double doubleValue() {
		return value;
	}

	/**
	 * Returns the value of the Double as long
	 */
	final public long longValue() {
		return (long) value;
	}

	public String toString() {
		return java.lang.Double.toString(value);
	}
}
