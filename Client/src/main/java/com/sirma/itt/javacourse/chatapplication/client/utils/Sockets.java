package com.sirma.itt.javacourse.chatapplication.client.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that finds available port in given range.
 * 
 * @author Petar Ivanov
 */
public class Sockets {
	/**
	 * Private constructor for utility class.
	 */
	private Sockets() {
	}

	/**
	 * Search port in given range.
	 * 
	 * @return Socket or null if it wasn't available.
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static Socket getSocket(String ip, int port) throws UnknownHostException, IOException {
		if (!(ip == null) || !(port >= 0)) {
			return new Socket(ip, port);
		}
		return null;
	}
}