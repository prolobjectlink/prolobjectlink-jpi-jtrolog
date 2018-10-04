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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.logicware.prolog.PrologAtom;
import org.logicware.prolog.PrologDouble;
import org.logicware.prolog.PrologFloat;
import org.logicware.prolog.PrologInteger;
import org.logicware.prolog.PrologList;
import org.logicware.prolog.PrologLong;
import org.logicware.prolog.PrologStructure;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologVariable;

public class PrologCutTest extends PrologBaseTest {

	private PrologTerm cut = provider.prologCut();

	@Test
	public void testGetArguments() {
		assertArrayEquals(new PrologTerm[0], cut.getArguments());
	}

	@Test
	public void testGetArity() {
		assertEquals(0, cut.getArity());
	}

	@Test
	public void testGetFunctor() {
		assertEquals("!", cut.getFunctor());
	}

	@Test
	public void testGetIndicator() {
		assertEquals("!/0", cut.getIndicator());
	}

	@Test
	public void testHasIndicator() {
		assertTrue(cut.hasIndicator("!", 0));
	}

	@Test
	public void testHashCode() {
		assertEquals(provider.prologCut().hashCode(), cut.hashCode());
	}

	@Test
	public void testIsAtom() {
		assertTrue(cut.isAtom());
	}

	@Test
	public void testIsNumber() {
		assertFalse(cut.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertFalse(cut.isFloat());
	}

	@Test
	public final void testIsDouble() {
		assertFalse(cut.isDouble());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(cut.isInteger());
	}

	@Test
	public final void testIsLong() {
		assertFalse(cut.isLong());
	}

	@Test
	public final void testIsVariable() {
		assertFalse(cut.isVariable());
	}

	@Test
	public final void testIsList() {
		assertFalse(cut.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(cut.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertFalse(cut.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(cut.isEmptyList());
	}

	@Test
	public final void testIsEvaluable() {
		assertFalse(cut.isEvaluable());
	}

	@Test
	public void testIsAtomic() {
		assertTrue(cut.isAtomic());
	}

	@Test
	public void testIsCompound() {
		assertFalse(cut.isCompound());
	}

	@Test
	public final void testUnify() {

		// with atom
		PrologTerm cut = provider.prologCut();
		PrologAtom atom = provider.newAtom("John Doe");
		assertFalse(cut.unify(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertFalse(cut.unify(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertFalse(cut.unify(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertFalse(cut.unify(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertFalse(cut.unify(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		// true. case [] and variable
		assertTrue(cut.unify(variable));

		// with predicate
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertFalse(cut.unify(structure));

		// with list
		PrologList list = provider.parsePrologList("[a,b,c]");
		assertFalse(cut.unify(list));
		assertTrue(cut.unify(cut));

		// with expression
		PrologTerm expression = provider.parsePrologTerm("58+93*10");
		assertFalse(cut.unify(expression));

	}

	@Test
	public final void testCompareTo() {

		// with atom
		PrologTerm cut = provider.prologCut();
		PrologAtom atom = provider.newAtom("John Doe");
		assertEquals(cut.compareTo(atom), -1);

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertEquals(cut.compareTo(iValue), 1);

		// with long
		PrologLong lValue = provider.newLong(28);
		assertEquals(cut.compareTo(lValue), 1);

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertEquals(cut.compareTo(fValue), 1);

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertEquals(cut.compareTo(dValue), 1);

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		// true. case [] and variable
		assertEquals(cut.compareTo(variable), 1);

		// with predicate
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertEquals(cut.compareTo(structure), -1);

		// with list
		PrologList list = provider.parsePrologList("[a,b,c]");
		assertEquals(cut.compareTo(list), -1);
		assertEquals(cut.compareTo(cut), 0);

		// with expression
		PrologTerm expression = provider.parsePrologTerm("58+93*10");
		assertEquals(cut.compareTo(expression), -1);

	}

	@Test
	public void testEqualsObject() {
		assertTrue(cut.equals(provider.prologCut()));
	}

	@Test
	public void testToString() {
		assertEquals("!", cut.toString());
	}

}
