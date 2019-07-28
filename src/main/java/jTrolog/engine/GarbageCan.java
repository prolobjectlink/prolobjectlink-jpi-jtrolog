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
 * GarbageCan listing which Variables bound per executionCtx number. Each
 * variable is identified by two numbers, vNr (its position in the Struct) and
 * vCtx (separating the same Variable from the same rule used by the Engine at
 * different times). The execCtx is a Number that the Engine controls that can
 * be used to remember at what point in the execution of a query a binding was
 * made (so that it can be garbage collected).
 * 
 * The GarbageCan keeps track of how many bindings it has registered using the
 * depthCounter. For every depth there is an entry for every possible execCtx in
 * both the map for vNrs and the map for vCtxs. Neither of the two maps should
 * ever be empty.
 * 
 * The maps automatically expand in depth, width is updated by the Engine.
 * 
 * @author eivbsmed@stud.hist.no
 * @author ivar.orstavik@hist.no
 */
class GarbageCan {

	private int depth = 8;
	private int width = Engine.STARTUP_STACK_SIZE;

	private int[][] trashCanVarNr = new int[depth][width];
	private int[][] trashCanVarCtx = new int[depth][width];

	private int[] depthCounter = new int[width];

	private LinkTable links;

	GarbageCan(LinkTable links) {
		this.links = links;
		for (int i = 0; i < depth; i++) {
			trashCanVarNr[i] = new int[width];
			trashCanVarCtx[i] = new int[width];
		}
	}

	void addToTrashCan(int vNr, int vCtx, int execCtx) {
		// get and update the depth counter
		int currentDepth = depthCounter[execCtx];
		depthCounter[execCtx] = currentDepth + 1;

		// check depth and expand map if needed
		if (currentDepth == depth)
			doubleTrashCanDepth(depth * 2);

		// mark vNr and vCtx under the given execCtx
		trashCanVarNr[currentDepth][execCtx] = vNr;
		trashCanVarCtx[currentDepth][execCtx] = vCtx;
	}

	private void doubleTrashCanDepth(final int newSize) {
		trashCanVarNr = LinkTable.expandMap(trashCanVarNr, newSize);
		trashCanVarCtx = LinkTable.expandMap(trashCanVarCtx, newSize);
		for (int i = depth; i < newSize; i++) {
			trashCanVarNr[i] = new int[width];
			trashCanVarCtx[i] = new int[width];
		}
		depth = newSize;
	}

	void doubleTrashCanWidth(final int newSize) {
		for (int i = 0; i < depth; i++) {
			trashCanVarNr[i] = LinkTable.expandArray(trashCanVarNr[i], newSize);
			trashCanVarCtx[i] = LinkTable.expandArray(trashCanVarCtx[i], newSize);
		}
		depthCounter = LinkTable.expandArray(depthCounter, newSize);
		width *= 2;
	}

	void collectGarbageLinks(int owner) {
		int currentDepth = depthCounter[owner];
		depthCounter[owner] = 0;

		for (int i = 0; i < currentDepth; i++) {
			int trashThisLink = trashCanVarNr[i][owner];
			int trashThisCtx = trashCanVarCtx[i][owner];
			links.reset(trashThisLink, trashThisCtx);
		}
	}

	public int[][] getGarbageLinks(int owner) {
		int[][] ints = new int[depth][2];
		for (int i = 0; i < depthCounter[owner]; i++) {
			int varNr = trashCanVarNr[i][owner];
			int varCtx = trashCanVarCtx[i][owner];
			ints[i] = new int[] { varNr, varCtx };
		}
		return ints;
	}
}
