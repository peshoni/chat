package com.sirma.itt.javacourse.chatapplication.utility;

/**
 * State design pattern.
 * 
 * @author Petar Ivanov
 */
public interface LanguageState {

	/**
	 * Setter method for state of {@link Language}
	 * 
	 * @param language
	 *            Object that will tune.
	 * @return Internal state of the object.
	 */
	String getState(Language language);
}
