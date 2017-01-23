package com.sirma.itt.javacourse.chatapplication.utility;

import java.io.Serializable;

/**
 * State design pattern. English state of Language.
 * 
 * @author Petar Ivanov
 */
public class LanguageEnglish implements LanguageState, Serializable {
	/**
	 * Comment for serialVersionUID.
	 */
	private static final long serialVersionUID = 8465800945936746472L;
	private String state = "english";

	/**
	 * {@inheritDoc}
	 */
	public String getState(Language language) {
		language.setState(this);
		return state;
	}
}
