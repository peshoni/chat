package com.sirma.itt.javacourse.chatapplication.server.enums;

import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageState;

/**
 * Expressions with messages for the server part.
 * 
 * @author Petar Ivanov
 */
public enum Messages {
	NICK_NEW(
			"Access denied, the name already exists or contains prohibited characters. "
					+ "Allowed characters: letters and numbers interval between them.",
			"\u0414\u043E\u0441\u0442\u044A\u043F \u043E\u0442\u043A\u0430\u0437\u0430"
					+ "\u043D, \u0438\u043C\u0435\u0442\u043E \u0432\u0435\u0447\u0435 \u0441"
					+ "\u044A\u0449\u0435\u0441\u0442\u0432\u0443\u0432\u0430 \u0438\u043B\u0438"
					+ " \u0441\u044A\u0434\u044A\u0440\u0436\u0430 \u0437\u0430\u0431\u0440\u0430"
					+ "\u043D\u0435\u043D\u0438 \u0441\u0438\u043C\u0432\u043E\u043B\u0438. \u041F"
					+ "\u043E\u0437\u0432\u043E\u043B\u0435\u043D\u0438 \u0441\u0438\u043C\u0432"
					+ "\u043E\u043B\u0438: \u041F\u043E\u043D\u0435 \u0442\u0440\u0438 \u0431\u0443"
					+ "\u043A\u0432\u0438, \u0446\u0438\u0444\u0440\u0438 \u0438 \u0434\u043E\u043B"
					+ "\u043D\u0430 \u0447\u0435\u0440\u0442\u0430 \u043F\u043E\u043C\u0435\u0436"
					+ "\u0434\u0443 \u0438\u043C."), DISCONNECTED(
							"Disconnected from server.",
							"\u0418\u0437\u043A\u043B\u044E\u0447\u0435\u043D\u0438 \u0441"
									+ "\u0442\u0435 \u043E\u0442 \u0441\u044A\u0440\u0432\u044A"
									+ "\u0440\u0430."), NO_CONNECTION(
											"No connection with server.",
											"\u041D\u044F\u043C\u0430\u0442\u0435 \u0432\u0440"
													+ "\u044A\u0437\u043A\u0430 \u0441\u044A\u0441 \u0441"
													+ "\u044A\u0440\u0432\u044A\u0440\u0430."), STOP_SERVER(
															"Server is stoped.",
															"\u0421\u044A\u0440\u0432\u044A\u0440\u0430 "
																	+ "\u0435 \u0441\u043F\u0440\u044F\u043D."), LOG_CREATED(
																			"Log file is created.",
																			"\u0424\u0430\u0439\u043B\u044A\u0442"
																					+ " \u0437\u0430 \u0440\u0435\u0433\u0438"
																					+ "\u0441\u0442\u0440\u0438\u0440\u0430"
																					+ "\u043D\u0435 \u0435 \u0441\u044A"
																					+ "\u0437\u0434\u0430\u0434\u0435"
																					+ "\u043D."), START_SERVER(
																							"Started. Waiting for clients...",
																							"\u0421\u0442\u0430\u0440\u0442"
																									+ "\u0438\u0440\u0430\u043D."
																									+ " \u041E\u0447\u0430\u043A\u0432"
																									+ "\u0430 \u043F\u043E\u0442\u0440"
																									+ "\u0435\u0431\u0438\u0442\u0435"
																									+ "\u043B\u0438..."), WELCOME(
																											"Welcome!",
																											"\u0414\u043E\u0431\u0440"
																													+ "\u0435 \u0434\u043E\u0448"
																													+ "\u043B\u0438!"), IS_ONLINE(
																															"is online.",
																															"\u0435 \u043E\u043D"
																																	+ "\u043B\u0430\u0439\u043D.");
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
