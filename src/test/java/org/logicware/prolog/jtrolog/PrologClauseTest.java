/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.logicware.prolog.PrologClause;
import org.logicware.prolog.PrologEngine;
import org.logicware.prolog.PrologIndicator;
import org.logicware.prolog.PrologTerm;

public class PrologClauseTest extends PrologBaseTest {

	PrologEngine engine = provider.newEngine();
	PrologEngine e = provider.newEngine();

	@Test
	public void testHashCode() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		e.assertz(provider.newStructure(parent, tom, bob));
		e.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.getProgramClauses().iterator();
		Iterator<PrologClause> ie = e.getProgramClauses().iterator();

		assertEquals(iengine.next().hashCode(), ie.next().hashCode());
		assertEquals(iengine.next().hashCode(), ie.next().hashCode());

	}

	@Test
	public void testGetTerm() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertEquals(
				provider.newStructure(":-", provider.newStructure(grandparent, x, z),
						provider.newStructure(",", provider.newStructure(parent, x, y),
								provider.newStructure(parent, y, z))),
				engine.getProgramClauses().iterator().next().getTerm());

	}

	@Test
	public void testGetHead() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals(provider.newStructure(grandparent, x, z), engine.getProgramClauses().iterator().next().getHead());
	}

	@Test
	public void testGetBody() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals(
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z)),
				engine.getProgramClauses().iterator().next().getBody());
	}

	@Test
	public void testGetBodyArray() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertArrayEquals(new PrologTerm[] { provider.newStructure(parent, x, y), provider.newStructure(parent, y, z) },
				engine.getProgramClauses().iterator().next().getBodyArray());
	}

	@Test
	public void testGetBodyIterator() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		Iterator<PrologClause> iterator = engine.getProgramClauses().iterator();

		assertTrue(iterator.hasNext());
		assertNotNull(iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testGetFunctor() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals("grandparent", engine.getProgramClauses().iterator().next().getFunctor());
	}

	@Test
	public void testGetArity() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals(2, engine.getProgramClauses().iterator().next().getArity());
	}

	@Test
	public void testGetIndicator() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals("grandparent/2", engine.getProgramClauses().iterator().next().getIndicator());
	}

	@Test
	public void testIsDirective() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertFalse(engine.getProgramClauses().iterator().next().isDirective());
	}

	@Test
	public void testIsFact() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.getProgramClauses().iterator();

		PrologClause fact = iengine.next();
		PrologClause rule = iengine.next();

		assertTrue(fact.isFact());
		assertFalse(rule.isFact());
	}

	@Test
	public void testIsRule() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.getProgramClauses().iterator();

		PrologClause fact = iengine.next();
		PrologClause rule = iengine.next();

		assertFalse(fact.isRule());
		assertTrue(rule.isRule());

	}

	@Test
	public void testUnify() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		PrologEngine e = provider.newEngine();
		e.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertTrue(engine.getProgramClauses().iterator().next().unify(e.getProgramClauses().iterator().next()));

	}

	@Test
	public void testIsDynamic() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertFalse(engine.getProgramClauses().iterator().next().isDynamic());

	}

	@Test
	public void testIsMultifile() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertFalse(engine.getProgramClauses().iterator().next().isMultifile());

	}

	@Test
	public void testIsDiscontiguous() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertFalse(engine.getProgramClauses().iterator().next().isDiscontiguous());

	}

	@Test
	public void testGetPrologIndicator() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		PrologIndicator i = engine.getProgramClauses().iterator().next().getPrologIndicator();
		assertEquals("grandparent", i.getFunctor());
		assertEquals(2, i.getArity());
	}

	@Test
	public void testEqualsObject() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		e.assertz(provider.newStructure(parent, tom, bob));
		e.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.getProgramClauses().iterator();
		Iterator<PrologClause> ie = e.getProgramClauses().iterator();

		assertEquals(iengine.next(), ie.next());
		assertEquals(iengine.next(), ie.next());

		assertTrue(engine.getProgramClauses().iterator().next().equals(e.getProgramClauses().iterator().next()));
		assertFalse(engine.getProgramClauses().iterator().next().equals(new Object()));
		assertFalse(engine.getProgramClauses().iterator().next().equals(null));
	}

	@Test
	public void testToString() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals("grandparent(X, Z):-\n\tparent(X, Y),\n\tparent(Y, Z).",
				engine.getProgramClauses().iterator().next().toString());
	}

}
