package com.sirma.itt.javacourse.chatapplication.client.gui;

import org.junit.Assert;
import org.junit.Test;

import com.sirma.itt.javacourse.chatapplication.client.client.Client;
import com.sirma.itt.javacourse.chatapplication.utility.Language;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;

public class TestClient {
	@Test
	public void testSetNickName() {
		Client client = new Client();
		client.setNickName("User");
		String expected = client.getNickName();
		Assert.assertTrue(expected.equals("User"));
	}

	@Test
	public void testSetLanguage() {
		Client client = new Client();
		Language language = new Language();
		LanguageBulgarian bul = new LanguageBulgarian();
		language.setState(bul);
		client.setLanguage(language);
		Assert.assertTrue(
				LanguageBulgarian.class.getName().equals(client.getLanguage().getState().getClass().getName()));
	}

	@Test
	public void testGetIp() {
		Client client = new Client();
		client.setIp("127.0.0.1");
		String expected = "127.0.0.1";
		Assert.assertTrue(expected.equals(client.getIp()));
	}

	@Test
	public void testGetPort() {
		Client client = new Client();
		client.setPort(9999);
		int expected = 9999;
		Assert.assertTrue(expected == client.getPort());
	}
}
