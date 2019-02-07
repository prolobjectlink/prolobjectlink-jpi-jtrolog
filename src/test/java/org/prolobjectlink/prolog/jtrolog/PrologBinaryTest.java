/*
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
package org.prolobjectlink.prolog.jtrolog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.prolobjectlink.logging.LoggerConstants.FILE_NOT_FOUND;
import static org.prolobjectlink.logging.LoggerConstants.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.prolobjectlink.logging.LoggerUtils;

public class PrologBinaryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() throws FileNotFoundException, IOException {

		// files existence
		File bat = new File("bin/pllink.bat");
		File sh = new File("bin/pllink.sh");
		File src = new File("src/build/filters/bin.properties");
		assertTrue(bat.exists());
		assertTrue(sh.exists());
		assertTrue(src.exists());

		// build properties test
		Properties bin = new Properties();
		bin.load(new FileReader(src));
		String script = bin.getProperty("Main.FileName");
		assertEquals("pllink", script);
		String main = bin.getProperty("Main.Class");
		assertEquals(JTrologConsole.class.getName(), main);

		//
		String line = null;
		StringBuilder b = null;
		FileReader reader = null;
		BufferedReader buffer = null;

		try {
			reader = new FileReader(bat);
			buffer = new BufferedReader(reader);
			line = buffer.readLine();
			b = new StringBuilder();
			while (line != null) {
				b.append(line);
				line = buffer.readLine();
			}

			assertTrue(b.toString().contains(JTrologConsole.class.getName()));

			reader = new FileReader(sh);
			buffer = new BufferedReader(reader);
			line = buffer.readLine();
			b = new StringBuilder();
			while (line != null) {
				b.append(line);
				line = buffer.readLine();
			}

			assertTrue(b.toString().contains(JTrologConsole.class.getName()));

		} catch (FileNotFoundException e) {
			LoggerUtils.error(getClass(), FILE_NOT_FOUND, e);
		} catch (IOException e) {
			LoggerUtils.error(getClass(), IO, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LoggerUtils.error(getClass(), IO, e);
				}
			}
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					LoggerUtils.error(getClass(), IO, e);
				}
			}
		}

	}

}
