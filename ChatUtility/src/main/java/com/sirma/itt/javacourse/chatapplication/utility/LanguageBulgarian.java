package com.sirma.itt.javacourse.chatapplication.utility;

import java.io.Serializable;

/**
 * State design pattern. Bulgarian state of language.
 * 
 * @author Petar Ivanov
 */
public class LanguageBulgarian implements LanguageState, Serializable {
	/**
	 * Comment for serialVersionUID.
	 */
	private static final long serialVersionUID = -3455389850274062246L;
	private String state = "bulgarian";

	/**
	 * {@inheritDoc}
	 */
	public String getState(Language language) {
		language.setState(this);
		return state;
	}
}
