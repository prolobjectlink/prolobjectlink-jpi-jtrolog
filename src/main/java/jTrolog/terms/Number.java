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
 * Number abstract class represents numbers prolog data type
 */
@SuppressWarnings({ "serial" })
public abstract class Number extends EvaluableTerm {

	protected Number() {
		type = Term.NUMBER;
	}

	// two real numbers that are similiar within this margin are considered
	// equal
	public static final double MARGIN = 0.0000000000000005;

	/**
	 * Returns the value of the number as int
	 */
	public abstract int intValue();

	/**
	 * Returns the value of the number as float
	 */
	public abstract float floatValue();

	/**
	 * Returns the value of the number as long
	 */
	public abstract long longValue();

	/**
	 * Returns the value of the number as double
	 */
	public abstract double doubleValue();

	/**
	 * @return 0 if one is the same as two (within the MARGIN of error) 1 if one
	 *         is greater than two -1 if one is smaller than two
	 */
	public static int compareDoubleValues(Number one, Number two) {
		double value = one.doubleValue() - two.doubleValue();
		if (value > 0 ? value < MARGIN : -value < MARGIN)
			return 0;
		return value > 0 ? 1 : -1;
	}

	public static Number create(String s) throws NumberFormatException {
		try {
			return Int.create(s);
		} catch (NumberFormatException ee) {
			return new Double(s);
		}
	}

	public static Number getIntegerNumber(long num) {
		if (num > Integer.MIN_VALUE && num < Integer.MAX_VALUE)
			return new Int((int) num);
		return new Long(num);
	}

	public String toStringSmall() {
		return toString();
	}
}
