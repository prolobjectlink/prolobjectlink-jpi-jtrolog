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

import jTrolog.terms.Struct;
import jTrolog.terms.Term;
import jTrolog.terms.Wrapper;
import jTrolog.engine.BindingsTable;


/**
 * @author ivar.orstavik@hist.no
 */
@SuppressWarnings({ "serial" })
public class WrapStruct extends Struct implements Wrapper {

	public final int context;
	private Struct basis;

	public WrapStruct(Struct struct, int renameVarID) {
		super(struct.name, struct.arity, struct.predicateIndicator);
		basis = struct;
		context = renameVarID;
	}

	public int getOperatorType() {
		return basis.getOperatorType();
	}

	public Term getArg(int i) {
		return BindingsTable.wrapWithID(basis.getArg(i), context);
	}

	public Var[] getVarList() {
		return basis.getVarList();
	}

	public int getContext() {
		return context;
	}

	public Term getBasis() {
		return basis;
	}

	// public boolean equals(Object t) {
	// return (t instanceof WrapStruct && ((WrapStruct) t).getContext() ==
	// context && ((WrapStruct) t).getBasis().equals(basis));
	// }

	public String toString() {
		return basis.toString();
	}
}
