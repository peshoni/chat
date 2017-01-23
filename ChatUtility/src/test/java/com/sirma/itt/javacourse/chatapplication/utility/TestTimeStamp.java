package com.sirma.itt.javacourse.chatapplication.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class TestTimeStamp {

	@Test
	public void test() {
		String string = "\\[\\d{2}\\:\\d{2}:\\d{2}\\]";
		Pattern pattern = Pattern.compile(string);
		Matcher matcher = pattern.matcher(TimeStamp.getCurrentTime());
		Assert.assertTrue(matcher.matches());
	}

	@Test
	public void testCurrentMills() {
		String string = "\\[\\d{2}\\:\\d{2}:\\d{2}\\]";
		Pattern pattern = Pattern.compile(string);
		Matcher matcher = pattern.matcher(TimeStamp.formatLongMillis(System.currentTimeMillis()));
		Assert.assertTrue(matcher.matches());
	}
}
