package com.sirma.itt.javacourse.chatapplication.server;

import org.junit.Assert;
import org.junit.Test;

import com.sirma.itt.javacourse.chatapplication.server.utils.NickNameValidator;

public class TestNickname {

	@Test
	public void testValidateNickNameWithValidNickname() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "petar_ivanov";
		Assert.assertTrue(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithValidNicknameTwo() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "petar_ivanov_02";
		Assert.assertTrue(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithValidNicknameThree() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "00000000000";
		Assert.assertFalse(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithValidNicknameFour() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "_000_";
		Assert.assertFalse(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithInvalidNickname() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "[pet]ar_ivanov]";
		Assert.assertFalse(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithInvalidNicknameTwo() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "_petar_ivanov_02_";
		Assert.assertFalse(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithValidNicknameWithNumbers() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "petar2336";
		Assert.assertTrue(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithValidNicknameWithOneSymbol() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "p9";
		Assert.assertFalse(validate.validateNickName(nickname));
	}

	@Test
	public void testValidateNickNameWithInvalidNicknameWithNumbersAndSpecialSymbols() {
		NickNameValidator validate = new NickNameValidator();
		String nickname = "petar+*-2336";
		Assert.assertFalse(validate.validateNickName(nickname));
	}

}
