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
package jTrolog.parser;

import java.io.Serializable;

/**
 * This class represents a token read by the prolog term tokenizer
 */
@SuppressWarnings({ "serial" })
class Token implements Serializable {
	// token textual representation
	String seq;
	// token type and attribute
	int type;

	static final int ATOM = 'A';
	static final int SQ_SEQUENCE = 'S';
	static final int DQ_SEQUENCE = 'D';
	static final int OPERATOR = 'O';
	static final int FUNCTOR = 'F';

	static final int ATOM_OPERATOR = 'B';
	static final int ATOM_FUNCTOR = 'a';
	static final int OPERATOR_FUNCTOR = 'p';
	static final int SQ_FUNCTOR = 's';

	static final int VARIABLE = 'v';
	static final int EOF = 'e';
	static final int INTEGER = 'i';
	static final int FLOAT = 'f';

	public Token(String s, int t) {
		seq = s.intern();
		type = t;
	}

	public String getValue() {
		return seq;
	}

	public boolean isOperator(boolean commaIsEndMarker) {
		if (commaIsEndMarker && ",".equals(seq))
			return false;
		return type == OPERATOR || type == ATOM_OPERATOR || type == OPERATOR_FUNCTOR;
	}

	public boolean isFunctor() {
		return type == FUNCTOR || type == ATOM_FUNCTOR || type == SQ_FUNCTOR || type == OPERATOR_FUNCTOR;
	}

	public boolean isNumber() {
		return type == INTEGER || type == FLOAT;
	}

	boolean isEOF() {
		return type == EOF;
	}

	boolean isType(int type) {
		return this.type == type;
	}

	boolean isAtom() {
		return type == ATOM || type == ATOM_OPERATOR || type == ATOM_FUNCTOR || type == SQ_FUNCTOR || type == SQ_SEQUENCE || type == DQ_SEQUENCE;
	}
}
