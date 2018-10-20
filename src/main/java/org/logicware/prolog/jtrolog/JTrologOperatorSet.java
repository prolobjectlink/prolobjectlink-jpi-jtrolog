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

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.logicware.prolog.OperatorEntry;
import org.logicware.prolog.PrologOperatorSet;

import jTrolog.engine.Prolog;
import jTrolog.terms.Int;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;

final class JTrologOperatorSet extends AbstractSet<OperatorEntry> implements PrologOperatorSet {

	protected final Set<OperatorEntry> operators;

	public JTrologOperatorSet() {
		Prolog engine = Prolog.defaultMachine;
		operators = new HashSet<OperatorEntry>();
		Iterator<?> i = engine.getCurrentOperators();
		while (i.hasNext()) {
			Object object = i.next();
			if (object instanceof Struct) {
				Struct o = (Struct) object;
				String name = ((StructAtom) o.getArg(2)).name;
				int priority = ((Int) o.getArg(0)).intValue();
				String specifier = ((StructAtom) o.getArg(1)).name;
				OperatorEntry op = new OperatorEntry(priority, specifier, name);
				operators.add(op);
			}
		}
	}

	public boolean currentOp(String opreator) {
		for (OperatorEntry operatorEntry : operators) {
			if (operatorEntry.getOperator().equals(opreator)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<OperatorEntry> iterator() {
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
