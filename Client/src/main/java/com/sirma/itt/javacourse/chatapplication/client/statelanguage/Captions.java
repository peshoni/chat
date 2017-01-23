package com.sirma.itt.javacourse.chatapplication.client.statelanguage;

import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageState;

/**
 * Client GUI captions.
 * 
 * @author Petar Ivanov
 */
public enum Captions {
	CONNECT("connect", "\u0441\u0432\u044A\u0440\u0437\u0432\u0430\u043D\u0435"), DISCONNECT("disconnect",
			"\u043A\u0440\u0430\u0439\u0020\u043D\u0430\u0020\u0441\u0435\u0441\u0438\u044F"), LANGUAGE("language",
					"\u0435\u0437\u0438\u043A"), SETTINGS("settings",
							"\u043D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438"), ENGLISH("English",
									"\u0410\u043D\u0433\u043B\u0438\u0439\u0441\u043A\u0438"), BULGARIAN("Bulgarian",
											"\u0411\u044A\u043B\u0433\u0430\u0440\u0441\u043A\u0438"), SERVER("server",
													"\u0441\u044A\u0440\u0432\u044A\u0440"), LOGIN("login",
															"\u0432\u043B\u0438\u0437\u0430\u043D\u0435\u0020\u0441\u0020\u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0441\u043A\u043E\u0020\u0438\u043C\u0435"), CHANGE_LANGUAGE(
																	"change language",
																	"\u0441\u043C\u044F\u043D\u0430\u0020\u043D\u0430\u0020\u0435\u0437\u0438\u043A"), SEND(
																			"send",
																			"\u0438\u0437\u043F\u0440\u0430\u0442\u0438"), WRITE_MESSAGE(
																					"write message",
																					"\u043D\u0430\u043F\u0438\u0448\u0438\u0020\u0441\u044A\u043E\u0431\u0449\u0435\u043D\u0438\u0435"), ONLINE_USERS(
																							"online",
																							"\u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0438\u0020\u043D\u0430\u0020\u043B\u0438\u043D\u0438\u044F"), LOGGED_AS(
																									"logged as:",
																									"\u0432\u043B\u0435\u0437\u043B\u0438\u0020\u0441\u0442\u0435\u0020\u043A\u0430\u0442\u043E\u003A"), SAVE_IP_SETTINGS(
																											"save",
																											"\u0437\u0430\u043F\u0430\u0437\u0438"), CLEAR_IP_SETTINGS(
																													"delete",
																													"\u0438\u0437\u0442\u0440\u0438\u0439"), CHAT(
																															"chat",
																															"\u0447\u0430\u0442"), HELP(
																																	"help",
																																	"\u043F\u043E\u043C\u043E\u0449"), ABOUT(
																																			"about",
																																			"\u043E\u0442\u043D\u043E\u0441\u043D\u043E\u0020\u0430\u043F\u043B\u0438\u043A\u0430\u0446\u0438\u044F\u0442\u0430"), NOT_LOGGED(
																																					"not signed",
																																					"\u043D\u0435\u0020\u0435\u0020\u0432\u044A\u0432\u0435\u0434\u0435\u043D\u043E"), INFO(
																																							"information",
																																							"\u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F"), NICKNAME(
																																									"nickname",
																																									"\u043F\u0440\u044F\u043A\u043E\u0440");
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
	private Captions(String english, String bulgarian) {
		this.english = english;
		this.bulgarian = bulgarian;
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
