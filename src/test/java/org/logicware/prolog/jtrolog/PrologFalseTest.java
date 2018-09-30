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
import static org.junit.Assert.fail;

import org.junit.Test;
import org.logicware.pdb.prolog.PrologAtom;
import org.logicware.pdb.prolog.PrologDouble;
import org.logicware.pdb.prolog.PrologFloat;
import org.logicware.pdb.prolog.PrologInteger;
import org.logicware.pdb.prolog.PrologList;
import org.logicware.pdb.prolog.PrologLong;
import org.logicware.pdb.prolog.PrologStructure;
import org.logicware.pdb.prolog.PrologTerm;
import org.logicware.pdb.prolog.PrologVariable;

public class PrologFalseTest extends PrologBaseTest {

	private PrologTerm f = provider.prologFalse();

	@Test
	public void testGetArguments() {
		assertArrayEquals(new PrologTerm[0], f.getArguments());
	}

	@Test
	public void testGetArity() {
		assertEquals(0, f.getArity());
	}

	@Test
	public void testGetFunctor() {
		assertEquals("false", f.getFunctor());
	}

	@Test
	public void testGetIndicator() {
		assertEquals("false/0", f.getIndicator());
	}

	@Test
	public void testHasIndicator() {
		assertTrue(f.hasIndicator("false", 0));
	}

	@Test
	public void testHashCode() {
		assertFalse(provider.prologCut().hashCode() == f.hashCode());
		assertFalse(provider.prologFail().hashCode() == f.hashCode());
		assertEquals(provider.prologFalse().hashCode(), f.hashCode());
	}

	@Test
	public void testIsAtom() {
		assertTrue(f.isAtom());
	}

	@Test
	public void testIsNumber() {
		assertFalse(f.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertFalse(f.isFloat());
	}

	@Test
	public final void testIsDouble() {
		assertFalse(f.isDouble());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(f.isInteger());
	}

	@Test
	public final void testIsLong() {
		assertFalse(f.isLong());
	}

	@Test
	public final void testIsVariable() {
		assertFalse(f.isVariable());
	}

	@Test
	public final void testIsList() {
		assertFalse(f.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(f.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertFalse(f.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(f.isEmptyList());
	}

	@Test
	public final void testIsEvaluable() {
		assertFalse(f.isEvaluable());
	}

	@Test
	public void testIsAtomic() {
		assertTrue(f.isAtomic());
	}

	@Test
	public void testIsCompound() {
		assertFalse(f.isCompound());
	}

	@Test
	public final void testUnify() {

		// with atom
		PrologTerm f = provider.prologFalse();
		PrologAtom atom = provider.newAtom("John Doe");
		assertFalse(f.unify(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertFalse(f.unify(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertFalse(f.unify(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertFalse(f.unify(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertFalse(f.unify(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		// true. case [] and variable
		assertTrue(f.unify(variable));

		// with predicate
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertFalse(f.unify(structure));

		// with list
		PrologList list = provider.parsePrologList("[a,b,c]");
		assertFalse(f.unify(list));
		assertTrue(f.unify(f));

		// with expression
		PrologTerm expression = provider.parsePrologTerm("58+93*10");
		assertFalse(f.unify(expression));

	}

	@Test
	public final void testCompareTo() {

		// with atom
		PrologTerm f = provider.prologFalse();
		PrologAtom atom = provider.newAtom("John Doe");
		assertEquals(f.compareTo(atom), 1);

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertEquals(f.compareTo(iValue), 1);

		// with long
		PrologLong lValue = provider.newLong(28);
		assertEquals(f.compareTo(lValue), 1);

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertEquals(f.compareTo(fValue), 1);

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertEquals(f.compareTo(dValue), 1);

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		// true. case [] and variable
		assertEquals(f.compareTo(variable), 1);

		// with predicate
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertEquals(f.compareTo(structure), -1);

		// with list
		PrologList list = provider.parsePrologList("[a,b,c]");
		assertEquals(f.compareTo(list), -1);
		assertEquals(f.compareTo(f), 0);

		// with expression
		PrologTerm expression = provider.parsePrologTerm("58+93*10");
		assertEquals(f.compareTo(expression), -1);

	}

	@Test
	public void testEqualsObject() {
		assertFalse(f.equals(provider.prologCut()));
		assertFalse(f.equals(provider.prologFail()));
		assertTrue(f.equals(provider.prologFalse()));
	}

	@Test
	public void testToString() {
		assertEquals("false", f.toString());
	}

}
