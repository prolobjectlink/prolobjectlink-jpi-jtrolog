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

import static org.prolobjectlink.prolog.PrologTermType.DOUBLE_TYPE;

import org.prolobjectlink.prolog.ArityError;
import org.prolobjectlink.prolog.FunctorError;
import org.prolobjectlink.prolog.IndicatorError;
import org.prolobjectlink.prolog.PrologDouble;
import org.prolobjectlink.prolog.PrologFloat;
import org.prolobjectlink.prolog.PrologInteger;
import org.prolobjectlink.prolog.PrologLong;
import org.prolobjectlink.prolog.PrologProvider;
import org.prolobjectlink.prolog.PrologTerm;

import jTrolog.terms.Double;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public final class JTrologDouble extends JTrologTerm implements PrologDouble {

	JTrologDouble(PrologProvider provider, Number value) {
		super(DOUBLE_TYPE, provider, new Double(value.doubleValue()));
	}

	public PrologInteger getPrologInteger() {
		return new JTrologInteger(provider, getIntegerValue());
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
		return ((Double) value).longValue();
	}

	public double getDoubleValue() {
		return ((Double) value).doubleValue();
	}

	public int getIntegerValue() {
		return ((Double) value).intValue();
	}

	public float getFloatValue() {
		return ((Double) value).floatValue();
	}

	public PrologTerm[] getArguments() {
		return new JTrologDouble[0];
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
