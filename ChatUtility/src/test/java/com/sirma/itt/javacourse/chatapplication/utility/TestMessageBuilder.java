package com.sirma.itt.javacourse.chatapplication.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.Assert;

public class TestMessageBuilder {

	@Test
	public void testBuildMessage() {
		Message message = new BuildMessage().build();
		Assert.assertNotNull(message);
	}

	@Test
	public void testRequest() {
		Message message = new BuildMessage().messageType(MESSAGE_TYPES.CONNECTION_REQUEST).build();
		Assert.assertTrue(message.getType().equals(MESSAGE_TYPES.CONNECTION_REQUEST));
	}

	@Test
	public void testValidateNickName() {
		Message message = new BuildMessage().messageType(MESSAGE_TYPES.APPROVAL_FOR_INCLUSION).build();
		Assert.assertTrue(message.getType().equals(MESSAGE_TYPES.APPROVAL_FOR_INCLUSION));
	}

	@Test
	public void testStandardMessage() {
		Message message = new BuildMessage().messageType(MESSAGE_TYPES.STANDARD_MESSAGE).build();
		Assert.assertTrue(message.getType().equals(MESSAGE_TYPES.STANDARD_MESSAGE));
	}

	@Test
	public void testFailureToConnection() {
		Message message = new BuildMessage().messageType(MESSAGE_TYPES.FAILURE_TO_CONNECT).build();
		Assert.assertTrue(message.getType().equals(MESSAGE_TYPES.FAILURE_TO_CONNECT));
	}

	@Test
	public void testFrom() {
		Message message = new BuildMessage().from("Petar").build();
		String expected = "Petar";
		Assert.assertTrue(expected.equals(message.getSender()));
	}

	@Test
	public void testFromSecond() {
		Message message = new BuildMessage().from(null).build();
		Assert.assertTrue(message.getSender().isEmpty());
	}

	@Test
	public void testTime() {
		final String PATTERN = "\\[\\d{2}:\\d{2}:\\d{2}\\]";
		Message message = new BuildMessage().time(TimeStamp.getCurrentTime()).build();
		Matcher matcher = Pattern.compile(PATTERN).matcher(message.getTime());
		Assert.assertTrue(matcher.matches());
	}

	@Test
	public void testMessage() {
		Message message = new BuildMessage().message("Hello.").build();
		String expected = "Hello.";
		Assert.assertTrue(expected.equals(message.getMessage()));
	}

	@Test
	public void testUsers() {
		Message message = new BuildMessage().users("Petar").build();
		String expectedUsers = "Petar";
		Assert.assertTrue(expectedUsers.equals(message.getUsers()));
	}

	@Test
	public void testRecipient() {
		Message message = new BuildMessage().recipient("someone").build();
		String expected = "someone";
		Assert.assertTrue(expected.equals(message.getRecipient()));
	}

	@Test
	public void testNotificationOfDisconnection() {
		Message message = new BuildMessage().messageType(MESSAGE_TYPES.REQUEST_FOR_DISCONNECTION).build();
		Assert.assertTrue(message.getType().equals(MESSAGE_TYPES.REQUEST_FOR_DISCONNECTION));
	}

	@Test
	public void testLanguage() {
		LanguageBulgarian bul = new LanguageBulgarian();
		Language language = new Language();
		bul.getState(language);
		Message message = new BuildMessage().language(language).build();
		Assert.assertTrue(language.getState().getClass().equals(message.getLanguage().getState().getClass()));
	}

	@Test
	public void testBuild() {
		String expected = "class com.sirma.itt.javacourse.chatapplication.utility.Message";
		Assert.assertTrue(expected.equals(new BuildMessage().build().getClass().toString()));
	}
}
