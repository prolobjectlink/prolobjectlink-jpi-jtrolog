package org.logicware.prolog.jtrolog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.logicware.prolog.PrologEngine;
import org.logicware.prolog.PrologFactory;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologQuery;

public class PrologEmptyQueryTest {

	@Test
	public final void test() {
		PrologProvider provider = PrologFactory.newProvider(JTrologProvider.class);
		PrologEngine engine = provider.newEngine("company.pl");
		PrologQuery query = engine.query();
		assertEquals(0, query.one().size());
		assertFalse(query.hasSolution());
	}

}
