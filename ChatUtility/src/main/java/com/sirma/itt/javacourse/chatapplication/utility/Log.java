package com.sirma.itt.javacourse.chatapplication.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates text file in user home directory and writes lines with events.
 * 
 * @author Petar Ivanov
 */
public class Log {
	private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);
	private static final String USER_HOME = "user.home";
	private static Path pathToFile;

	/**
	 * Private constructor for utility class.
	 */
	private Log() {
	}

	/**
	 * Create text file for log events.
	 * 
	 * @param directoryName
	 * @param fileName
	 * @return True if file is been created successfully.
	 */
	synchronized public static boolean createFile(String directoryName, String fileName) {
		Path home = Paths.get(System.getProperty(USER_HOME));
		File newFile = new File(home.toString(), directoryName);
		newFile.mkdir();
		Path path = Paths.get(newFile.getAbsolutePath());
		pathToFile = Paths.get(path.toString(), fileName);
		try {
			Files.newOutputStream(pathToFile, StandardOpenOption.CREATE);
			return true;
		} catch (IOException e) {
			LOGGER.warn("Couldn't write in users directory.", e);
			return false;
		}
	}

	/**
	 * Method to add String with event in new line in created text file.
	 * 
	 * @param event
	 *            String to add.
	 * 
	 * @return True if message is been written successfully.
	 */
	synchronized public static boolean logEvent(String event) {
		try (BufferedOutputStream output = new BufferedOutputStream(
				Files.newOutputStream(pathToFile, StandardOpenOption.APPEND))) {
			output.write((event).getBytes(StandardCharsets.UTF_8));
			output.close();
			return true;
		} catch (IOException e) {
			LOGGER.warn("Couldn't write in users directory.", e);
			return false;
		}
	}
}