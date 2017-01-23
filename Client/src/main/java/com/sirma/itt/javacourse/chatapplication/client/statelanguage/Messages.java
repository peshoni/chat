package com.sirma.itt.javacourse.chatapplication.client.statelanguage;

import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageState;

/**
 * Expressions with messages for the client part.
 * 
 * @author Petar Ivanov
 */
public enum Messages {
	DISCONNECTED("Server is shutdown.",
			"\u0421\u044A\u0440\u0432\u044A\u0440\u044A\u0442 \u0435 \u0441\u043F\u0440\u044F\u043D."), NO_CONNECTION(
					"No connection with server.",
					"\u041D\u044F\u043C\u0430\u0442\u0435\u0020\u0432\u0440\u044A\u0437\u043A\u0430\u0020\u0441\u044A\u0441\u0020\u0441\u044A\u0440\u0432\u044A\u0440\u0430\u002E"), END_OF_SESSION(
							"end of session",
							"\u043A\u0440\u0430\u0439\u0020\u043D\u0430\u0020\u0441\u0435\u0441\u0438\u044F\u0442\u0430"), WRITE(
									"write message",
									"\u043D\u0430\u043F\u0438\u0448\u0435\u0442\u0435\u0020\u0441\u044A\u043E\u0431\u0449\u0435\u043D\u0438\u0435"), NOT_LOGGED(
											"You are not logged in.", "Не сте въвели прякор.");
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
	private Messages(String en, String bg) {
		this.english = en;
		this.bulgarian = bg;
	}

	/**
	 * Getter method for message by Language state.
	 * 
	 * @param language
	 *            Given state of Language object.
	 * @return String with cyrillic or latin letters according to the state.
	 */
	public String getMessageText(LanguageState language) {
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
