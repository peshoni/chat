package com.sirma.itt.javacourse.chatapplication.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Time stamp for events log.
 * 
 * @author Petar Ivanov
 */
public class TimeStamp {

	/**
	 * Private constructor.
	 */
	private TimeStamp() {
	}

	/**
	 * Getter method for current time.
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("[HH:mm:ss]");
		return format.format(c.getTime());
	}

	public static String formatLongMillis(long millis) {
		SimpleDateFormat format = new SimpleDateFormat("[HH:mm:ss]");
		return format.format(millis);
	}
}
