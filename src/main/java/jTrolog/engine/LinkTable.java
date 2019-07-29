/*
 * #%L
 * prolobjectlink-jpi-jtrolog
 * %%
 * Copyright (C) 2012 - 2018 WorkLogic Project
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
package jTrolog.engine;

import jTrolog.terms.Term;

/**
 * @author ivar.orstavik@hist.no
 */
class LinkTable {

	private int depth = 8;
	private int width = Engine.STARTUP_STACK_SIZE;

	// Links are stored in a set of two dimensional arrays.
	// The terms and their ctx id are stored as individual dimensions.
	// The negative duplicates are used for terms with ctx id that are generated
	// by library methods.
	private Term[][] linkToTerm = new Term[depth][width];
	private int[][] linkToCtx = new int[depth][width];

	LinkTable() {
		for (int i = 0; i < depth; i++) {
			linkToTerm[i] = new Term[width];
			linkToCtx[i] = new int[width];
		}
	}

	final Term getTerm(final int vNr, final int vCtx) {
		return vNr >= depth || vCtx >= width ? null : linkToTerm[vNr][vCtx];
	}

	final int getCtx(final int vNr, final int vCtx) {
		return linkToCtx[vNr][vCtx];
	}

	final void put(final int vNr, int vCtx, final Term link, final int linkCtx) {
		if (vNr >= depth)
			expandLinkTableDepth(vNr * 2);

		// mark vNr and vCtx under the given execCtx
		linkToTerm[vNr][vCtx] = link;
		linkToCtx[vNr][vCtx] = linkCtx;
	}

	private void expandLinkTableDepth(final int newSize) {
		linkToTerm = LinkTable.expandMap(linkToTerm, newSize);
		linkToCtx = LinkTable.expandMap(linkToCtx, newSize);
		for (int i = depth; i < newSize; i++) {
			linkToTerm[i] = new Term[width];
			linkToCtx[i] = new int[width];
		}
		depth = newSize;
	}

	final void reset(int vNr, int vCtx) {
		linkToTerm[vNr][vCtx] = null;
		linkToCtx[vNr][vCtx] = 0;
	}

	final void doubleWidth(final int newSize) {
		for (int i = 0; i < depth; i++) {
			linkToTerm[i] = LinkTable.expandArray(linkToTerm[i], newSize);
			linkToCtx[i] = LinkTable.expandArray(linkToCtx[i], newSize);
		}
		width = newSize;
	}

	public int getWidth() {
		return width;
	}

	static int[][] expandMap(final int[][] array, final int newSize) {
		int[][] newArray = new int[newSize][];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	static int[] expandArray(int[] array, final int newSize) {
		int[] newArray = new int[newSize];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	static Term[][] expandMap(final Term[][] array, final int newSize) {
		Term[][] newArray = new Term[newSize][];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}

	static Term[] expandArray(Term[] array, final int newSize) {
		Term[] newArray = new Term[newSize];
		System.arraycopy(array, 0, newArray, 0, array.length);
		return newArray;
	}
}
