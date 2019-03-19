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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.prolobjectlink.prolog.AbstractOperator;
import org.prolobjectlink.prolog.PrologOperator;

import jTrolog.engine.Prolog;
import jTrolog.terms.Int;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
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
				AbstractOperator op = new JTrologOperator(priority, specifier, name);
				operators.add(op);
			}
		}
		return operators;
	}

	private JTrologUtil() {
	}

}
