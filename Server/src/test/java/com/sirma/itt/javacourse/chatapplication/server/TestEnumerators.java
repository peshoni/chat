package com.sirma.itt.javacourse.chatapplication.server;

import org.junit.Assert;
import org.junit.Test;

import com.sirma.itt.javacourse.chatapplication.server.enums.Captions;
import com.sirma.itt.javacourse.chatapplication.server.enums.Messages;
import com.sirma.itt.javacourse.chatapplication.utility.Language;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageEnglish;

public class TestEnumerators {
	@Test
	public void testGetMessageTextBulgarian() {
		Language lang = new Language();
		lang.setState(new LanguageBulgarian());
		String expected = "\u0435 \u043E\u043D\u043B\u0430\u0439\u043D.";
		Assert.assertEquals(expected, Messages.IS_ONLINE.getMessageText(lang.getState()));
	}

	@Test
	public void testGetMessageTextEnglish() {
		Language lang = new Language();
		lang.setState(new LanguageEnglish());
		String expected = "is online.";
		Assert.assertEquals(expected, Messages.IS_ONLINE.getMessageText(lang.getState()));
	}

	@Test
	public void testGetCaptionTextBulgarian() {
		Language lang = new Language();
		lang.setState(new LanguageBulgarian());
		String expected = "\u0441\u0442\u0430\u0440\u0442";
		Assert.assertEquals(expected, Captions.START.getCaption(lang.getState()));
	}

	@Test
	public void testGetCaptionTextEnglish() {
		Language lang = new Language();
		lang.setState(new LanguageEnglish());
		String expected = "start";
		Assert.assertEquals(expected, Captions.START.getCaption(lang.getState()));
	}
}
