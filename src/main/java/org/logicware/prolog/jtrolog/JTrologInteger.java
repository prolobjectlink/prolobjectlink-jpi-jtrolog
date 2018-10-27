/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.prolog.jtrolog;

import static org.logicware.prolog.PrologTermType.INTEGER_TYPE;

import org.logicware.prolog.ArityError;
import org.logicware.prolog.FunctorError;
import org.logicware.prolog.IndicatorError;
import org.logicware.prolog.PrologDouble;
import org.logicware.prolog.PrologFloat;
import org.logicware.prolog.PrologInteger;
import org.logicware.prolog.PrologLong;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologTerm;

import jTrolog.terms.Int;

public class JTrologInteger extends JTrologTerm implements PrologInteger {

	JTrologInteger(PrologProvider provider, Number value) {
		super(INTEGER_TYPE, provider, new Int(value.intValue()));
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
		return ((Int) value).longValue();
	}

	public double getDoubleValue() {
		return ((Int) value).doubleValue();
	}

	public int getIntValue() {
		return ((Int) value).intValue();
	}

	public float getFloatValue() {
		return ((Int) value).floatValue();
	}

	public PrologTerm[] getArguments() {
		return new JTrologInteger[0];
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
