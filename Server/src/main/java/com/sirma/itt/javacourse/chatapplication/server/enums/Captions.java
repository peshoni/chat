package com.sirma.itt.javacourse.chatapplication.server.enums;

import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageState;

/**
 * Server GUI captions.
 * 
 * @author Petar Ivanov
 */
public enum Captions {
	START("start", "\u0441\u0442\u0430\u0440\u0442"), STOP("stop", "\u0441\u0442\u043E\u043F"), LANGUAGE("language",
			"\u0435\u0437\u0438\u043A"), SETTINGS("settings",
					"\u043D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438"), ENGLISH("English",
							"\u0410\u043D\u0433\u043B\u0438\u0439\u0441\u043A\u0438"), BULGARIAN("Bulgarian",
									"\u0411\u044A\u043B\u0433\u0430\u0440\u0441\u043A\u0438"), USERS("users",
											"\u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0438"), ONLINE_USERS(
													"online", "\u043E\u043D\u043B\u0430\u0439\u043D"), EVENTS("events",
															"\u0441\u044A\u0431\u0438\u0442\u0438\u044F"), SERVER(
																	"server",
																	"\u0441\u044A\u0440\u0432\u044A\u0440"), SAVE_IP_SETTINGS(
																			"save",
																			"\u0437\u0430\u043F\u0430\u0437\u0438"), CLEAR_IP_SETTINGS(
																					"delete",
																					"\u0438\u0437\u0442\u0440\u0438\u0439");
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
	private Captions(String en, String bg) {
		this.english = en;
		this.bulgarian = bg;
	}

	/**
	 * Getter method for caption by Language state.
	 * 
	 * @param language
	 *            Given state of Language object.
	 * @return String with cyrillic or latin letters according to the state.
	 */
	public String getCaption(LanguageState language) {
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
