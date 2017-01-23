package com.sirma.itt.javacourse.chatapplication.utility;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Petar Ivanov
 */
public class LogTest {
	private static final String FILE_NAME = "test.log";
	private static final String DIR_NAME = "Test";

	/**
	 * Test method for {@link com.sirma.itt.javacourse.chatapplication.utility.Log#createFile(java.lang.String)}.
	 */
	@Test
	public void testCreateFile() {
		Assert.assertTrue(Log.createFile(DIR_NAME, FILE_NAME));
	}

	/**
	 * Test method for {@link com.sirma.itt.javacourse.chatapplication.utility.Log#logEvent(java.lang.String)}.
	 */
	@Test
	public void testLogEvent() {
		Log.createFile(DIR_NAME, FILE_NAME);
		Assert.assertTrue(Log.logEvent("testEvent"));
	}

}
