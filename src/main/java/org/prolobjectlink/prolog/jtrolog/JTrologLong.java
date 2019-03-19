/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
package org.prolobjectlink.prolog.jtrolog;

import static org.prolobjectlink.prolog.PrologTermType.LONG_TYPE;

import org.prolobjectlink.prolog.ArityError;
import org.prolobjectlink.prolog.FunctorError;
import org.prolobjectlink.prolog.IndicatorError;
import org.prolobjectlink.prolog.PrologDouble;
import org.prolobjectlink.prolog.PrologFloat;
import org.prolobjectlink.prolog.PrologInteger;
import org.prolobjectlink.prolog.PrologLong;
import org.prolobjectlink.prolog.PrologProvider;
import org.prolobjectlink.prolog.PrologTerm;

import jTrolog.terms.Long;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public class JTrologLong extends JTrologTerm implements PrologLong {

	JTrologLong(PrologProvider provider, Number value) {
		super(LONG_TYPE, provider, new Long(value.longValue()));
	}

	public PrologInteger getPrologInteger() {
		return new JTrologInteger(provider, getIntValue());
	}

	public PrologFloat getPrologFloat() {
		return new JTrologFloat(provider, getFloatValue());
	}

	public PrologDouble getPrologDouble() {
		return new JTrologDouble(provider, getDoubleValue());
	}

	public PrologLong getPrologLong() {
		return new JTrologLong(provider, getLongValue());
	}

	public long getLongValue() {
		return ((Long) value).longValue();
	}

	public double getDoubleValue() {
		return ((Long) value).doubleValue();
	}

	public int getIntValue() {
		return ((Long) value).intValue();
	}

	public float getFloatValue() {
		return ((Long) value).floatValue();
	}

	public PrologTerm[] getArguments() {
		return new JTrologLong[0];
	}

	public int getArity() {
		throw new ArityError(this);
	}

	public String getFunctor() {
		throw new FunctorError(this);
	}

	public String getIndicator() {
		throw new IndicatorError(this);
	}

	public boolean hasIndicator(String functor, int arity) {
		return false;
	}

}
