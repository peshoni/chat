package com.sirma.itt.javacourse.chatapplication.utility;

import java.io.Serializable;

/**
 * State design pattern. Class that allows his object to behave different ways based upon his internal state.
 * 
 * @author Petar Ivanov
 */
public class Language implements Serializable {
	/**
	 * Comment for serialVersionUID.
	 */
	private static final long serialVersionUID = -5064775729465719427L;
	private LanguageState state;

	/**
	 * Constructs object that may different internal state .
	 */
	public Language() {
		state = null;
	}

	/**
	 * Setter method for state.
	 * 
	 * @param state
	 */
	public void setState(LanguageState state) {
		this.state = state;
	}

	/**
	 * Getter state method.
	 * 
	 * @return state
	 */
	public LanguageState getState() {
		return state;
	}
}
