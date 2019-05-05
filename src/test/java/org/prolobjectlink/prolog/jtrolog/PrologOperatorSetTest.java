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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.prolobjectlink.prolog.PrologOperatorSet;

public class PrologOperatorSetTest {

	private PrologOperatorSet s = new JTrologOperatorSet();

	@Test
	public void testCurrentOp() {

		assertTrue(s.currentOp("is"));
		assertTrue(s.currentOp("not"));
		assertTrue(s.currentOp("**"));
//		assertTrue(s.currentOp("=.."));
		assertTrue(s.currentOp("<<"));
		assertTrue(s.currentOp("/"));
		assertTrue(s.currentOp("mod"));
		assertTrue(s.currentOp("+"));
//		assertTrue(s.currentOp("exp"));
//		assertTrue(s.currentOp("cos"));
		assertTrue(s.currentOp("*"));
		assertTrue(s.currentOp("->"));
		assertTrue(s.currentOp(">"));
		assertTrue(s.currentOp("\\="));
//		assertTrue(s.currentOp("as"));
		assertTrue(s.currentOp("^"));
//		assertTrue(s.currentOp("div"));
		assertTrue(s.currentOp("@<"));
		assertTrue(s.currentOp(";"));
		assertTrue(s.currentOp("=<"));
		assertTrue(s.currentOp(":-"));
//		assertTrue(s.currentOp("."));
		assertTrue(s.currentOp("=\\="));
		assertTrue(s.currentOp("-"));
//		assertTrue(s.currentOp("sin"));
		assertTrue(s.currentOp("="));
//		assertTrue(s.currentOp("<-"));
//		assertTrue(s.currentOp("log"));
//		assertTrue(s.currentOp("returns"));
		assertTrue(s.currentOp(">="));
		assertTrue(s.currentOp("@=<"));
		assertTrue(s.currentOp(">>"));
		assertTrue(s.currentOp("-"));
		assertTrue(s.currentOp("?-"));
		assertTrue(s.currentOp("=="));
		assertTrue(s.currentOp("\\=="));
		assertTrue(s.currentOp("<"));
		assertTrue(s.currentOp("=:="));
		assertTrue(s.currentOp("\\+"));
//		assertTrue(s.currentOp("sqrt"));
		assertTrue(s.currentOp(","));
		assertTrue(s.currentOp("@>"));
		assertTrue(s.currentOp("\\"));
		assertTrue(s.currentOp("//"));
		assertTrue(s.currentOp("@>="));
		assertTrue(s.currentOp("rem"));
//		assertTrue(s.currentOp("atan"));
		assertTrue(s.currentOp(":-"));
		assertTrue(s.currentOp("\\/"));
		assertTrue(s.currentOp("/\\"));

	}

	@Test
	public void testIterator() {
		assertNotNull(s.iterator());
	}

	@Test
	public void testSize() {
		assertTrue(s.size() > 0);
	}

	@Test
	public void testEquals() {
		assertTrue(s.equals(new JTrologOperatorSet()));
		assertFalse(s.equals(new Object()));
		assertFalse(s.equals(null));
		assertTrue(s.equals(s));
	}

	@Test
	public void testHashCode() {
		assertTrue(s.hashCode() == new JTrologOperatorSet().hashCode());
		// assertTrue(s.hashCode() == new String().hashCode());
		// assertTrue(s.hashCode() == ((String) null).hashCode());
	}

}
