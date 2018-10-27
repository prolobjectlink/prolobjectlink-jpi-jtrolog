package org.logicware.prolog.jtrolog;

import org.logicware.prolog.AbstractOperator;
import org.logicware.prolog.PrologOperator;

public final class JTrologOperator extends AbstractOperator implements PrologOperator {

	public JTrologOperator(int priority, String specifier, String operator) {
		super(priority, specifier, operator);
	}

}
