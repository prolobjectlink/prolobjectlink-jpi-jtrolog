package org.prolobjectlink.prolog.jtrolog;
/*-
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.prolobjectlink.prolog.PredicateIndicator;

public class PrologIndicatorTest extends PrologBaseTest {

	private final PredicateIndicator pi = new PredicateIndicator("functor", 3);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetArity() {
		assertEquals(3, pi.getArity());
	}

	@Test
	public final void testGetFunctor() {
		assertEquals("functor", pi.getFunctor());
	}

	@Test
	public final void testGetIndicator() {
		assertEquals("functor/3", pi.getIndicator());
	}

	@Test
	public final void testToString() {
		assertEquals("functor/3", pi.toString());
	}

	@Test
	public final void testHashCode() {
		assertEquals(new PredicateIndicator("functor", 3).hashCode(), pi.hashCode());
	}

	@Test
	public final void testEquals() {
		assertEquals(new PredicateIndicator("functor", 3), pi);
	}

}
