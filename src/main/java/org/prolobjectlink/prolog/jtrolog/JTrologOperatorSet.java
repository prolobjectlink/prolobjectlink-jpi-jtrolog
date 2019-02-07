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

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

import org.prolobjectlink.prolog.PrologOperator;
import org.prolobjectlink.prolog.PrologOperatorSet;

import jTrolog.engine.Prolog;

final class JTrologOperatorSet extends AbstractSet<PrologOperator> implements PrologOperatorSet {

	protected final Set<PrologOperator> operators;

	public JTrologOperatorSet() {
		Prolog engine = Prolog.defaultMachine;
		operators = JTrologUtil.getOperatorSet(engine);
	}

	public boolean currentOp(String opreator) {
		for (PrologOperator operatorEntry : operators) {
			if (operatorEntry.getOperator().equals(opreator)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<PrologOperator> iterator() {
		return operators.iterator();
	}

	@Override
	public int size() {
		return operators.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((operators == null) ? 0 : operators.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		JTrologOperatorSet other = (JTrologOperatorSet) obj;
		if (operators == null) {
			if (other.operators != null)
				return false;
		} else if (!operators.equals(other.operators)) {
			return false;
		}
		return true;
	}

}
