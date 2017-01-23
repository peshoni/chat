package com.sirma.itt.javacourse.chatapplication.utility;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Tests for state of object {@link Language}
 * 
 * @author Petar Ivanov
 */
public class LanguageTest {
	/**
	 * Test BG state of language.
	 */
	@Test
	public void testBG() {
		Language language = new Language();
		LanguageBulgarian bg = new LanguageBulgarian();
		language.setState(bg);
		Assert.assertEquals(language.getState().getClass().getSimpleName(), bg.getClass().getSimpleName());
	}

	/**
	 * Test EN state of language.
	 */
	@Test
	public void testEN() {
		Language language = new Language();
		LanguageEnglish en = new LanguageEnglish();
		language.setState(en);
		Assert.assertEquals(language.getState().getClass().getSimpleName(), en.getClass().getSimpleName());
	}
}
