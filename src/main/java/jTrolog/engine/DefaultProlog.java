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
package jTrolog.engine;

/**
 * @author ivar.orstavik@hist.no
 */
class DefaultProlog extends Prolog {

	DefaultProlog() {
		super(new String[0]);
		opNew(":-", XFX, 1200);
		opNew("-->", XFX, 1200);
		opNew(":-", FX, 1200);
		opNew("?-", FX, 1200);
		opNew(";", XFY, 1100);
		opNew("->", XFY, 1050);
		opNew(",", XFY, 1000);
		opNew("\\+", FY, 900);
		opNew("not", FY, 900);
		opNew("=", XFX, 700);
		opNew("\\=", XFX, 700);
		opNew("==", XFX, 700);
		opNew("\\==", XFX, 700);
		// opNew("@==",OperatorTable.XFX,700);
		// opNew("@\\==",OperatorTable.XFX,700);
		opNew("@>", XFX, 700);
		opNew("@<", XFX, 700);
		opNew("@=<", XFX, 700);
		opNew("@>=", XFX, 700);
		opNew("=:=", XFX, 700);
		opNew("=\\=", XFX, 700);
		opNew(">", XFX, 700);
		opNew("<", XFX, 700);
		opNew("=<", XFX, 700);
		opNew(">=", XFX, 700);
		opNew("is", XFX, 700);
		opNew("==..", XFX, 700);
		// opNew("?",OperatorTable.XFX,600);
		// opNew("@",OperatorTable.XFX,550);
		opNew("+", YFX, 500);
		opNew("-", YFX, 500);
		opNew("/\\", YFX, 500);
		opNew("\\/", YFX, 500);
		opNew("*", YFX, 400);
		opNew("/", YFX, 400);
		opNew("//", YFX, 400);
		opNew(">>", YFX, 400);
		opNew("<<", YFX, 400);
		opNew("rem", YFX, 400);
		opNew("mod", YFX, 400);
		opNew("**", XFX, 200);
		opNew("^", XFY, 200);
		opNew("\\", FX, 200);
		opNew("-", FY, 200);
	}
}
