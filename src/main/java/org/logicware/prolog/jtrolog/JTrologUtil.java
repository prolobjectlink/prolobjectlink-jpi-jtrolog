/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2018 Logicware Project
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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.logicware.prolog.OperatorEntry;
import org.logicware.prolog.PrologOperator;

import jTrolog.engine.Prolog;
import jTrolog.terms.Int;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;

final class JTrologUtil {

	static Set<PrologOperator> getOperatorSet(Prolog engine) {
		Set<PrologOperator> operators = new HashSet<PrologOperator>();
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
		return operators;
	}

	private JTrologUtil() {
	}

}
