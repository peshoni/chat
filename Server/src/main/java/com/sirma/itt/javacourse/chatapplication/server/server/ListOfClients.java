package com.sirma.itt.javacourse.chatapplication.server.server;

import java.util.Hashtable;

import com.sirma.itt.javacourse.chatapplication.utility.Message;
import com.sirma.itt.javacourse.chatapplication.utility.TimeStamp;

/**
 * Maintain a list of {@link ClientConnection}.
 * 
 * @author Petar Ivanov
 */
public class ListOfClients {
	private static final String CLIENT_SEPARATOR = String.valueOf("\u00A5");
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private Hashtable<String, ClientConnection> table;

	/**
	 * Constructs list of clients.
	 */
	public ListOfClients() {
		table = new Hashtable<String, ClientConnection>();
	}

	/**
	 * Adds clients to the list.
	 * 
	 * @param client
	 *            {@link ClientConnection} that will be add.
	 * @return
	 */
	synchronized public boolean add(ClientConnection client) {
		if (!client.equals(null)) {
			table.put(client.getName(), client);
			return true;
		} else
			return false;
	}

	/**
	 * Remove client from table.
	 * 
	 * @param client
	 *            {@link ClientConnection} that needs to be removed.
	 * @return True if client is removed from table and false otherwise.
	 */
	synchronized public boolean remove(ClientConnection client) {
		if (!client.equals(null)) {
			table.remove(client.getName());
			return true;
		}
		return false;
	}

	/**
	 * Send message to all objects presents in the table on their language.
	 * 
	 * @param message
	 *            object {@link Message} that will be send.
	 */
	synchronized public void notifyAll(Message message) {
		table.forEach((nickName, client) -> {
			message.setLanguage(client.getLanguage());
			client.sendMessage(message);
		});
	}

	/**
	 * Creates string with users names, separated by special symbol("\u00A5"- Japanese yen).
	 * 
	 * @return String with online users.
	 */
	synchronized public String getListOfUsers() {
		StringBuilder builder = new StringBuilder("");
		table.forEach((k, v) -> {
			builder.append(String.format("%s%s", k, CLIENT_SEPARATOR));
		});
		return builder.toString();
	}

	/**
	 * 
	 * @return String with formated text with users, their InetAddress and time of connection.
	 */
	synchronized public String getStatistic() {
		StringBuilder builder = new StringBuilder("");
		table.forEach((k, v) -> {
			builder.append(String.format("User:%s, Inet: %s, time to connect:%s.%s", k, v.getAddress(),
					TimeStamp.formatLongMillis(v.getTimeToConnection()), LINE_SEPARATOR));
		});
		return builder.toString();
	}

	synchronized public void clearListOfOnlineClients() {
		table.clear();
	}

	/**
	 * Tests if the specified object is a key in this table with clients.
	 * 
	 * @param nickname
	 *            Nickname to check.
	 * @return true if and only if the specified object is a key in this hashtable, as determined by the equals method;
	 *         false otherwise.
	 */
	synchronized public boolean containsNickname(String nickname) {
		return table.containsKey(nickname);
	}

	/**
	 * Getter method for onlineUsers.
	 *
	 * @return the onlineUsers
	 */
	public int getOnlineUsers() {
		return table.size();
	}
}
