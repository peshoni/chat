package com.sirma.itt.javacourse.chatapplication.server.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.itt.javacourse.chatapplication.utility.Language;
import com.sirma.itt.javacourse.chatapplication.utility.Message;

/**
 * Client class that handle connections with server. Sends and receive {@link Message} on the server using
 * {@link ObjectOutputStream} and {@link ObjectInputStream}.
 * 
 * @author Petar Ivanov
 */
public class ClientConnection extends Thread {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientConnection.class);
	private Socket socket = null;
	private Server server = null;
	private Language language;
	private ObjectOutputStream objectOutput = null;
	private ObjectInputStream objectInput = null;
	private InetAddress address;
	private volatile boolean isRunning;
	private long timeToConnection;

	/**
	 * Constructs object that handling socket connection with server.
	 * 
	 * @param socket
	 * @throws IOException
	 */
	public ClientConnection() {
		this.isRunning = true;
		this.timeToConnection = System.currentTimeMillis();
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void openStreams() throws IOException {
		this.address = socket.getInetAddress();
		this.objectOutput = new ObjectOutputStream(socket.getOutputStream());
		this.objectInput = new ObjectInputStream(socket.getInputStream());
	}

	/**
	 * Sends {@link Message} to the client.
	 * 
	 * @param message
	 *            The message that will be send.
	 */
	public void sendMessage(Message message) {
		try {
			objectOutput.reset();
			objectOutput.writeObject(message);
			objectOutput.flush();
		} catch (IOException e) {
			LOGGER.error("Error stream occurs.", e);
		}
	}

	/**
	 * Reads message from clients.
	 * 
	 * @return
	 */
	public Message receiveMessage() {
		Message mes = null;
		try {
			mes = (Message) objectInput.readObject();
			if (!(mes == null)) {
				server.notifyOtherClients(mes);
				language = mes.getLanguage();
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error("Class not found.");
		} catch (IOException e) {
			LOGGER.info("Socket is closed.");
			isRunning = false;
			server.dissconnectClient(this);
			server.showStatistic();
			try {
				objectInput.close();
				objectOutput.close();
				// socket.close();
			} catch (IOException e1) {
				LOGGER.error("Close streams error", e1);
			}
		}
		return mes;
	}

	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void run() {
		while (isRunning) {
			receiveMessage();
		}
	}

	/**
	 * Getter method for language.
	 *
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Setter method for language.
	 *
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * Getter method for address.
	 *
	 * @return the address
	 */
	public InetAddress getAddress() {
		return address;
	}

	/**
	 * Getter method for timeToConnection.
	 *
	 * @return the timeToConnection
	 */
	public long getTimeToConnection() {
		return timeToConnection;
	}

	/**
	 * Getter method for server.
	 *
	 * @return the server
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * Setter method for server.
	 *
	 * @param server
	 *            the server to set
	 */
	public void setServer(Server server) {
		this.server = server;
	}

	/**
	 * Getter method for socket.
	 *
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * Setter method for socket.
	 *
	 * @param socket
	 *            the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
