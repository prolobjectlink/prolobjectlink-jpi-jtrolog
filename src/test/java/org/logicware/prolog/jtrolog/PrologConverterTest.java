/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
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
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.logicware.prolog.PrologAtom;
import org.logicware.prolog.PrologConverter;
import org.logicware.prolog.PrologConverterFactory;
import org.logicware.prolog.PrologDouble;
import org.logicware.prolog.PrologFloat;
import org.logicware.prolog.PrologInteger;
import org.logicware.prolog.PrologList;
import org.logicware.prolog.PrologLong;
import org.logicware.prolog.PrologStructure;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologVariable;

import jTrolog.terms.Double;
import jTrolog.terms.Float;
import jTrolog.terms.Int;
import jTrolog.terms.Long;
import jTrolog.terms.Struct;
import jTrolog.terms.StructAtom;
import jTrolog.terms.Term;
import jTrolog.terms.Var;

public class PrologConverterTest extends PrologBaseTest {

	private Term[][] termTable = new Term[7][5];
	private Map<String, Term> termMap = new HashMap<String, Term>();
	private PrologConverter<Term> converter = PrologConverterFactory.createPrologConverter(JTrologConverter.class);

	@Before
	public void setUp() throws Exception {

		solution[0][0] = mcardon;
		solution[0][1] = one;
		solution[0][2] = five;
		solution[0][3] = board;
		solution[0][4] = threeThousand;

		solution[1][0] = treeman;
		solution[1][1] = two;
		solution[1][2] = three;
		solution[1][3] = human_resources;
		solution[1][4] = twoThousand;

		solution[2][0] = chapman;
		solution[2][1] = one;
		solution[2][2] = two;
		solution[2][3] = board;
		solution[2][4] = thousandFiveHundred;

		solution[3][0] = claessen;
		solution[3][1] = four;
		solution[3][2] = one;
		solution[3][3] = technical_services;
		solution[3][4] = thousand;

		solution[4][0] = petersen;
		solution[4][1] = five;
		solution[4][2] = eight;
		solution[4][3] = administration;
		solution[4][4] = fourThousandFiveHundred;

		solution[5][0] = cohn;
		solution[5][1] = one;
		solution[5][2] = seven;
		solution[5][3] = board;
		solution[5][4] = fourThousand;

		solution[6][0] = duffy;
		solution[6][1] = one;
		solution[6][2] = nine;
		solution[6][3] = board;
		solution[6][4] = fiveThousand;

		//

		termTable[0][0] = new StructAtom("mcardon");
		termTable[0][1] = new Int(1);
		termTable[0][2] = new Int(5);
		termTable[0][3] = new StructAtom("board");
		termTable[0][4] = new Int(3000);

		termTable[1][0] = new StructAtom("treeman");
		termTable[1][1] = new Int(2);
		termTable[1][2] = new Int(3);
		termTable[1][3] = new StructAtom("human_resources");
		termTable[1][4] = new Int(2000);

		termTable[2][0] = new StructAtom("chapman");
		termTable[2][1] = new Int(1);
		termTable[2][2] = new Int(2);
		termTable[2][3] = new StructAtom("board");
		termTable[2][4] = new Int(1500);

		termTable[3][0] = new StructAtom("claessen");
		termTable[3][1] = new Int(4);
		termTable[3][2] = new Int(1);
		termTable[3][3] = new StructAtom("technical_services");
		termTable[3][4] = new Int(1000);

		termTable[4][0] = new StructAtom("petersen");
		termTable[4][1] = new Int(5);
		termTable[4][2] = new Int(8);
		termTable[4][3] = new StructAtom("administration");
		termTable[4][4] = new Int(4500);

		termTable[5][0] = new StructAtom("cohn");
		termTable[5][1] = new Int(1);
		termTable[5][2] = new Int(7);
		termTable[5][3] = new StructAtom("board");
		termTable[5][4] = new Int(4000);

		termTable[6][0] = new StructAtom("duffy");
		termTable[6][1] = new Int(1);
		termTable[6][2] = new Int(9);
		termTable[6][3] = new StructAtom("board");
		termTable[6][4] = new Int(5000);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testToTermT() {
		assertEquals(six, converter.toTerm(new Int(6)));
		assertEquals(x, converter.toTerm(new Var("X", 1)));
		assertEquals(cat, converter.toTerm(new StructAtom("cat")));
		assertEquals(pi, converter.toTerm(new Double(3.14159)));
		assertEquals(euler, converter.toTerm(new Float(2.71828F)));
		assertEquals(provider.prologEmpty(), converter.toTerm(Term.emptyList));
		assertEquals(provider.newLong(1000000000), converter.toTerm(new Long(1000000000)));
		assertEquals(provider.newStructure(salary, one, thousand),
				converter.toTerm(new Struct(salary, new Term[] { new Int(1), new Int(1000) })));
		assertEquals(provider.newList(expecteds0), converter.toTerm(

				new Struct(".", new Term[] { new StructAtom("mcardon"),

						new Struct(".", new Term[] { new Int(1),

								new Struct(".", new Term[] { new Int(5),

										new Struct(".", new Term[] { new StructAtom("board"),

												new Struct(".", new Term[] { new Int(3000), Term.emptyList })

										}) }) }) })));

	}

	@Test
	public final void testToTermTArray() {
		assertArrayEquals(expecteds0, converter.toTermArray(new Term[] { new StructAtom("mcardon"), new Int(1),
				new Int(5), new StructAtom("board"), new Int(3000) }));
	}

	@Test
	public final void testToTermTArrayArray() {
		assertArrayEquals(solution, converter.toTermMatrix(termTable));
	}

	@Test
	public final void testToTermMapOfStringT() {

		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", pam);
		solutionMap.put("Y", bob);

		//

		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("pam"));
		termMap.put("Y", new StructAtom("bob"));

		assertEquals(solutionMap, converter.toTermMap(termMap));

	}

	@Test
	public final void testToTermMapOfStringTArray() {

		famillyAllSolutionMap = new Map[6];
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", pam);
		solutionMap.put("Y", bob);
		famillyAllSolutionMap[0] = solutionMap;
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", tom);
		solutionMap.put("Y", bob);
		famillyAllSolutionMap[1] = solutionMap;

		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", tom);
		solutionMap.put("Y", liz);
		famillyAllSolutionMap[2] = solutionMap;
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", bob);
		solutionMap.put("Y", ann);
		famillyAllSolutionMap[3] = solutionMap;

		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", bob);
		solutionMap.put("Y", pat);
		famillyAllSolutionMap[4] = solutionMap;
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", pat);
		solutionMap.put("Y", jim);
		famillyAllSolutionMap[5] = solutionMap;

		//

		Map<String, Term>[] termMapArray = new Map[6];
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("pam"));
		termMap.put("Y", new StructAtom("bob"));
		termMapArray[0] = termMap;
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("tom"));
		termMap.put("Y", new StructAtom("bob"));
		termMapArray[1] = termMap;

		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("tom"));
		termMap.put("Y", new StructAtom("liz"));
		termMapArray[2] = termMap;
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("bob"));
		termMap.put("Y", new StructAtom("ann"));
		termMapArray[3] = termMap;

		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("bob"));
		termMap.put("Y", new StructAtom("pat"));
		termMapArray[4] = termMap;
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("pat"));
		termMap.put("Y", new StructAtom("jim"));
		termMapArray[5] = termMap;

		assertArrayEquals(famillyAllSolutionMap, converter.toTermMapArray(termMapArray));

	}

	@Test
	public final void testToTermTClassOfK() {

		// from concrete term
		assertEquals(six, converter.toTerm(new Int(6), PrologInteger.class));
		assertEquals(x, converter.toTerm(new Var("X", 1), PrologVariable.class));
		assertEquals(cat, converter.toTerm(new StructAtom("cat"), PrologAtom.class));
		assertEquals(pi, converter.toTerm(new Double(3.14159), PrologDouble.class));
		assertEquals(euler, converter.toTerm(new Float(2.71828F), PrologFloat.class));
		assertEquals(provider.prologEmpty(), converter.toTerm(Term.emptyList, PrologTerm.class));
		assertEquals(provider.newLong(1000000000), converter.toTerm(new Long(1000000000), PrologLong.class));
		assertEquals(provider.newStructure(salary, one, thousand),
				converter.toTerm(new Struct(salary, new Term[] { new Int(1), new Int(1000) }), PrologStructure.class));
		assertEquals(provider.newList(expecteds0), converter.toTerm(

				new Struct(".", new Term[] { new StructAtom("mcardon"),

						new Struct(".", new Term[] { new Int(1),

								new Struct(".", new Term[] { new Int(5),

										new Struct(".", new Term[] { new StructAtom("board"),

												new Struct(".", new Term[] { new Int(3000), Term.emptyList })

										}) }) }) }),

				PrologList.class));

		// from ancestor term class
		assertEquals(six, converter.toTerm(new Int(6), PrologTerm.class));
		assertEquals(x, converter.toTerm(new Var("X", 1), PrologTerm.class));
		assertEquals(cat, converter.toTerm(new StructAtom("cat"), PrologTerm.class));
		assertEquals(pi, converter.toTerm(new Double(3.14159), PrologTerm.class));
		assertEquals(euler, converter.toTerm(new Float(2.71828F), PrologTerm.class));
		assertEquals(provider.prologEmpty(), converter.toTerm(Term.emptyList, PrologTerm.class));
		assertEquals(provider.newLong(1000000000), converter.toTerm(new Long(1000000000), PrologTerm.class));
		assertEquals(provider.newStructure(salary, one, thousand),
				converter.toTerm(new Struct(salary, new Term[] { new Int(1), new Int(1000) }), PrologTerm.class));
		assertEquals(provider.newList(expecteds0), converter.toTerm(

				new Struct(".", new Term[] { new StructAtom("mcardon"),

						new Struct(".", new Term[] { new Int(1),

								new Struct(".", new Term[] { new Int(5),

										new Struct(".", new Term[] { new StructAtom("board"),

												new Struct(".", new Term[] { new Int(3000), Term.emptyList })

										}) }) }) }),

				PrologTerm.class));

	}

	@Test
	public final void testToTermTArrayClassOfK() {
		assertArrayEquals(expecteds0, converter.toTermArray(new Term[] { new StructAtom("mcardon"), new Int(1),
				new Int(5), new StructAtom("board"), new Int(3000) }, PrologTerm[].class));
	}

	@Test
	public final void testToTermTArrayArrayClassOfK() {
		assertArrayEquals(solution, converter.toTermMatrix(termTable, PrologTerm[][].class));
	}

	@Test
	public final void testToTermMapOfStringTClassOfK() {

		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", pam);
		solutionMap.put("Y", bob);

		//

		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("pam"));
		termMap.put("Y", new StructAtom("bob"));

		assertEquals(solutionMap, converter.toTermMap(termMap, PrologTerm.class));

	}

	@Test
	public final void testToTermMapOfStringTArrayClassOfK() {

		famillyAllSolutionMap = new Map[6];
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", pam);
		solutionMap.put("Y", bob);
		famillyAllSolutionMap[0] = solutionMap;
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", tom);
		solutionMap.put("Y", bob);
		famillyAllSolutionMap[1] = solutionMap;

		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", tom);
		solutionMap.put("Y", liz);
		famillyAllSolutionMap[2] = solutionMap;
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", bob);
		solutionMap.put("Y", ann);
		famillyAllSolutionMap[3] = solutionMap;

		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", bob);
		solutionMap.put("Y", pat);
		famillyAllSolutionMap[4] = solutionMap;
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("X", pat);
		solutionMap.put("Y", jim);
		famillyAllSolutionMap[5] = solutionMap;

		//

		Map<String, Term>[] termMapArray = new Map[6];
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("pam"));
		termMap.put("Y", new StructAtom("bob"));
		termMapArray[0] = termMap;
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("tom"));
		termMap.put("Y", new StructAtom("bob"));
		termMapArray[1] = termMap;

		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("tom"));
		termMap.put("Y", new StructAtom("liz"));
		termMapArray[2] = termMap;
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("bob"));
		termMap.put("Y", new StructAtom("ann"));
		termMapArray[3] = termMap;

		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("bob"));
		termMap.put("Y", new StructAtom("pat"));
		termMapArray[4] = termMap;
		termMap = new HashMap<String, Term>();
		termMap.put("X", new StructAtom("pat"));
		termMap.put("Y", new StructAtom("jim"));
		termMapArray[5] = termMap;

		assertArrayEquals(famillyAllSolutionMap, converter.toTermMapArray(termMapArray, PrologTerm.class));

	}

	@Test
	public final void testFromTermPrologTerm() {

		assertEquals(new Int(6), converter.fromTerm(six));
		assertEquals(new Var("X", 1), converter.fromTerm(x));
		assertEquals(new StructAtom("cat"), converter.fromTerm(cat));
		assertEquals(new Double(3.14159), converter.fromTerm(pi));
		assertEquals(new Float(2.71828F), converter.fromTerm(euler));
		assertEquals(Term.emptyList, converter.fromTerm(provider.prologEmpty()));
		assertEquals(new Long(1000000000), converter.fromTerm(provider.newLong(1000000000)));
		assertEquals(new Struct(salary, new Term[] { new Int(1), new Int(1000) }),
				converter.fromTerm(provider.newStructure(salary, one, thousand)));
		assertEquals(new Struct(".", new Term[] { new StructAtom("mcardon"),

				new Struct(".", new Term[] { new Int(1),

						new Struct(".", new Term[] { new Int(5),

								new Struct(".", new Term[] { new StructAtom("board"),

										new Struct(".", new Term[] { new Int(3000), Term.emptyList })

								}) }) }) }),

				converter.fromTerm(provider.newList(expecteds0)));
	}

	@Test
	public final void testFromTermPrologTermArray() {
		assertArrayEquals(new Term[] { new StructAtom("mcardon"), new Int(1), new Int(5), new StructAtom("board"),
				new Int(3000) }, converter.fromTermArray(expecteds0));
	}

	@Test
	public final void testFromTermPrologTermPrologTermArray() {
		assertEquals(
				new Struct(":-",
						new Term[] { new StructAtom("mcardon"), new Struct(",",
								new Term[] { new StructAtom("mcardon"), new Struct(",", new Term[] { new Int(1),
										new Struct(",", new Term[] { new Int(5), new Struct(",",
												new Term[] { new StructAtom("board"), new Int(3000) }) }) }) }) }),
				converter.fromTerm(mcardon, expecteds0));
		assertEquals(new Struct(":-", new Term[] { new StructAtom("mcardon"), Term.TRUE }),
				converter.fromTerm(mcardon, (PrologTerm[]) null));
		assertEquals(new Struct(":-", new Term[] { new StructAtom("mcardon"), Term.TRUE }),
				converter.fromTerm(mcardon, new PrologTerm[0]));
	}

	@Test
	public final void testFromTermPrologTermClassOfK() {

		// from concrete term
		assertEquals(new Int(6), converter.fromTerm(six, Int.class));
		assertEquals(new Var("X", 1), converter.fromTerm(x, Var.class));
		assertEquals(new StructAtom("cat"), converter.fromTerm(cat, StructAtom.class));
		assertEquals(new Double(3.14159), converter.fromTerm(pi, Double.class));
		assertEquals(new Float(2.71828F), converter.fromTerm(euler, Float.class));
		assertEquals(Term.emptyList, converter.fromTerm(provider.prologEmpty(), Term.class));
		assertEquals(new Long(1000000000), converter.fromTerm(provider.newLong(1000000000), Long.class));
		assertEquals(new Struct(salary, new Term[] { new Int(1), new Int(1000) }),
				converter.fromTerm(provider.newStructure(salary, one, thousand), Struct.class));
		assertEquals(new Struct(".", new Term[] { new StructAtom("mcardon"),

				new Struct(".", new Term[] { new Int(1),

						new Struct(".", new Term[] { new Int(5),

								new Struct(".", new Term[] { new StructAtom("board"),

										new Struct(".", new Term[] { new Int(3000), Term.emptyList })

								}) }) }) }), converter.fromTerm(provider.newList(expecteds0), Struct.class));

		// from ancestor term class
		assertEquals(new Int(6), converter.fromTerm(six, Term.class));
		assertEquals(new Var("X", 1), converter.fromTerm(x, Term.class));
		assertEquals(new StructAtom("cat"), converter.fromTerm(cat, Term.class));
		assertEquals(new Double(3.14159), converter.fromTerm(pi, Term.class));
		assertEquals(new Float(2.71828F), converter.fromTerm(euler, Term.class));
		assertEquals(Term.emptyList, converter.fromTerm(provider.prologEmpty(), Term.class));
		assertEquals(new Long(1000000000), converter.fromTerm(provider.newLong(1000000000), Term.class));
		assertEquals(new Struct(salary, new Term[] { new Int(1), new Int(1000) }),
				converter.fromTerm(provider.newStructure(salary, one, thousand), Term.class));
		assertEquals(new Struct(".", new Term[] { new StructAtom("mcardon"),

				new Struct(".", new Term[] { new Int(1),

						new Struct(".", new Term[] { new Int(5),

								new Struct(".", new Term[] { new StructAtom("board"),

										new Struct(".", new Term[] { new Int(3000), Term.emptyList })

								}) }) }) }), converter.fromTerm(provider.newList(expecteds0), Term.class));

	}

	@Test
	public final void testFromTermPrologTermArrayClassOfK() {
		assertArrayEquals(new Term[] { new StructAtom("mcardon"), new Int(1), new Int(5), new StructAtom("board"),
				new Int(3000) }, converter.fromTermArray(expecteds0, Term[].class));
	}

	@Test
	public final void testFromTermPrologTermPrologTermArrayClassOfK() {
		assertEquals(
				new Struct(":-",
						new Term[] { new StructAtom("mcardon"), new Struct(",",
								new Term[] { new StructAtom("mcardon"), new Struct(",", new Term[] { new Int(1),
										new Struct(",", new Term[] { new Int(5), new Struct(",",
												new Term[] { new StructAtom("board"), new Int(3000) }) }) }) }) }),
				converter.fromTerm(mcardon, expecteds0, Term.class));
	}

	@Test
	public final void testCreateProvider() {
		assertNotNull(converter.createProvider());
	}

	@Test
	public final void testToString() {
		assertEquals("JTrologConverter", converter.toString());
	}

}
