package com.sirma.itt.javacourse.chatapplication.server.utils;

import java.util.regex.Pattern;

/**
 * Class that compile pattern for validate input string. The nick name must contains only numbers and letters and '_'.
 * 
 * @author Petar Ivanov
 */
public class NickNameValidator {
	private final static String PATTERN = "([a-zA-Z[\u0430-\u044F\u0410-\u042F]]([a-zA-Z[\u0430-\u044F\u0410-\u042F]0-9]+([\\_])?)+[a-zA-Z[\u0430-\u044F\u0410-\u042F]0-9]+)";
	private Pattern pattern;

	/**
	 * Constructs validator and compile their pattern.
	 */
	public NickNameValidator() {
		pattern = Pattern.compile(PATTERN);
	}

	/**
	 * Check for matches.
	 * 
	 * @param nickName
	 *            The nickname of the user.
	 * @return True if nickname matches the template.
	 */
	public boolean validateNickName(String nickname) {
		return pattern.matcher(nickname).matches();
	}
}
