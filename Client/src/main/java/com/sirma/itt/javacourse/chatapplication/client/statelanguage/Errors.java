package com.sirma.itt.javacourse.chatapplication.client.statelanguage;

import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageState;

/**
 * Expressions with error messages on client.
 * 
 * @author Petar Ivanov
 */
public enum Errors {
	UNKNOWN_HOST("Unknown host.",
			"\u041D\u0435\u043F\u043E\u0437\u043D\u0430\u0442\u043E\u0020\u0438\u043C\u0435\u0020\u043D\u0430\u0020\u0445\u043E\u0441\u0442");
	private String english;
	private String bulgarian;

	/**
	 * Private constructor.
	 * 
	 * @param english
	 *            String with text in Latin letters.
	 * @param bulgarian
	 *            String with text letters of the Cyrillic alphabet.
	 */
	private Errors(String english, String bulgarian) {
		this.english = english;
		this.bulgarian = bulgarian;
	}

	/**
	 * Getter method for error text by Language state.
	 * 
	 * @param language
	 *            Given state of Language object.
	 * @return String with cyrillic or latin letters according to the state.
	 */
	public String getErrorText(LanguageState language) {
		if (language.getClass().equals(LanguageBulgarian.class)) {
			return getBulgarian();
		} else
			return getEnglish();
	}

	/**
	 * Getter method for English.
	 * 
	 * @return Expression in latin.
	 */
	private String getEnglish() {
		return english;
	}

	/**
	 * Getter method for Bulgarian.
	 *
	 * @return Expression in cyrillic.
	 */
	private String getBulgarian() {
		return bulgarian;
	}
}
