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
 * Float class represents the float prolog data type
 */
public class Float extends Number {

	private float value;

	public Float() {
	}

	public Float(float v) {
		value = v;
	}

	public Float(String v) {
		value = java.lang.Float.parseFloat(v);
	}

	/**
	 * Returns the value of the Float as int
	 */
	public int intValue() {
		return (int) value;
	}

	/**
	 * Returns the value of the Float as float
	 */
	public float floatValue() {
		return value;
	}

	/**
	 * Returns the value of the Float as double
	 */
	public double doubleValue() {
		return value;
	}

	/**
	 * Returns the value of the Float as long
	 */
	public long longValue() {
		return (long) value;
	}

	public String toString() {
		return java.lang.Float.toString(value);
	}

	public boolean equals(Object n) {
		if (n instanceof Float)
			return Number.compareDoubleValues(this, (Number) n) == 0;
		return false;
	}
}
