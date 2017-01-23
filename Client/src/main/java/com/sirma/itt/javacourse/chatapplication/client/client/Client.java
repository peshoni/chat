package com.sirma.itt.javacourse.chatapplication.client.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.itt.javacourse.chatapplication.client.gui.Controller;
import com.sirma.itt.javacourse.chatapplication.client.statelanguage.Messages;
import com.sirma.itt.javacourse.chatapplication.client.utils.Sockets;
import com.sirma.itt.javacourse.chatapplication.utility.BuildMessage;
import com.sirma.itt.javacourse.chatapplication.utility.Language;
import com.sirma.itt.javacourse.chatapplication.utility.Log;
import com.sirma.itt.javacourse.chatapplication.utility.MESSAGE_TYPES;
import com.sirma.itt.javacourse.chatapplication.utility.Message;
import com.sirma.itt.javacourse.chatapplication.utility.TimeStamp;

import javafx.scene.control.TextArea;

/**
 * Chat client.
 * 
 * @author Petar Ivanov
 */
public class Client implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
	private static final String SEPARATOR = System.lineSeparator();
	private static final String SEPARATOR_CLIENTS = String.valueOf("\u00A5");
	private static final String DIR_NAME = "ClientEvents";
	private static final String FILE_NAME = "chatClientLog.log";
	private Controller controller;
	private Socket socket;
	private ObjectOutputStream objectOutput = null;
	private ObjectInputStream objectInput = null;
	private String ip;
	private int port;
	private TextArea screen;
	private String nickName;
	private volatile boolean run;
	private int hashCode;
	private TextArea userList;
	private Language language;
	private Message message;

	/**
	 * Constructs client.
	 * 
	 */
	public Client() {
		message = new Message();
		createLogFile();
	}

	/**
	 * Setter method for screen.
	 * 
	 * @param screen
	 *            {@link TextArea} to set.
	 */
	public void setScreen(TextArea screen) {
		this.screen = screen;
	}

	/**
	 * Setter method for list.
	 *
	 * @param list
	 *            {@link TextArea} to set.
	 */
	public void setAreaWithOnlineUsers(TextArea list) {
		this.userList = list;
	}

	/**
	 * Connect to the server, reads "Welcome" message and start listening for the reversed messages.
	 */
	public void connect() {
		run = true;
		try {
			showEvents("Waiting for server response...");
			socket = Sockets.getSocket(ip, port);
			objectOutput = new ObjectOutputStream(socket.getOutputStream());
			objectInput = new ObjectInputStream(socket.getInputStream());
			showEvents(String.format("%s%s", "Connected on port:", socket.getPort()));
			sendMessage(
					new BuildMessage().time(TimeStamp.getCurrentTime()).messageType(MESSAGE_TYPES.CONNECTION_REQUEST)
							.from(nickName).message("hello").recipient("server").language(language).build());
			readMessages(objectInput);
		} catch (UnknownHostException e) {
			LOGGER.warn("Unknown host");
			run = false;
		} catch (IOException e) {
			showEvents(String.format("%s:%s", TimeStamp.getCurrentTime(),
					"Address is invalid on local machine, or port is not valid on remote machine."));
			controller.showNoConnectionWindow();
			LOGGER.warn("Socket is clossed");
		}
	}

	/**
	 * 
	 * @param input
	 */
	public void readMessages(ObjectInputStream input) {
		while (run) {
			try {
				message = (Message) input.readObject();
				if (message == null) {
					showEvents("No available server");
					break;
				}
				switch (message.getType()) {
				case FAILURE_TO_CONNECT:
					showEvents(String.format("%s%s%s", message.getTime(), message.getSender(), message.getMessage()));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					}
					controller.doSomething();
					break;
				case SERVER_SHUTDOWN:
					showEvents(String.format("%s%s%s", message.getTime(), message.getSender(),
							Messages.DISCONNECTED.getMessageText(language.getState())));
					run = false;
					break;
				case STANDARD_MESSAGE:
					showUsers(message.getUsers());
					showEvents(String.format("%s%s:%s", message.getTime(), message.getSender(), message.getMessage()));
					break;
				default:
					break;
				}
			} catch (ClassNotFoundException cnfe) {
				LOGGER.error("Class not found.", cnfe);
			} catch (IOException ioex) {
				LOGGER.error("Stream error", ioex);
				showEvents(String.format("%s%s", TimeStamp.getCurrentTime(),
						Messages.NO_CONNECTION.getMessageText(language.getState())));
				break;
			}
			message = null;
		}

	}

	/**
	 * Send message to the server.
	 * 
	 * @param message
	 *            Message to send.
	 */
	public void sendMessage(String message) {
		if (run) {
			sendMessage(new BuildMessage().messageType(MESSAGE_TYPES.STANDARD_MESSAGE).from(nickName)
					.time(TimeStamp.getCurrentTime()).message(message).language(language).build());
		} else {
			showEvents(String.format("%s:%s", TimeStamp.getCurrentTime(),
					Messages.NOT_LOGGED.getMessageText(language.getState())));
		}
	}

	/**
	 * Send messages to the server
	 * 
	 * @param message
	 *            The message that will be send.
	 */
	private void sendMessage(Message message) {
		if (run) {
			try {
				objectOutput.reset();
				objectOutput.writeObject(message);
				objectOutput.flush();
			} catch (IOException e) {
				userList.clear();
				showEvents(String.format("%s%s", TimeStamp.getCurrentTime(),
						Messages.NO_CONNECTION.getMessageText(language.getState())));
			}
		} else {
			showEvents(String.format("%s:%s", TimeStamp.getCurrentTime(), "You are not logged in."));
		}
	}

	/**
	 * Close the client socket.
	 */
	public void stopClient() {
		sendMessage(new BuildMessage().messageType(MESSAGE_TYPES.REQUEST_FOR_DISCONNECTION).from(nickName)
				.time(TimeStamp.getCurrentTime()).message("Bye-bye").language(language).build());
		run = false;
		{
			try {
				if (socket != null)
					objectInput.close();
				objectOutput.close();
				socket.close();

			} catch (IOException ioe) {
				LOGGER.error("Error closing ...");
			}
		}
	}

	/**
	 * Visualizing system messages on the screen.
	 * 
	 * @param status
	 *            Message to set.
	 */
	public synchronized boolean showEvents(String status) {
		if (screen != null) {
			String stringToLog = String.format("%s%s", status, SEPARATOR);
			screen.appendText(stringToLog);
			logEvent(stringToLog);
			return true;
		}
		return false;
	}

	public synchronized boolean showUsers(String users) {
		String[] online = users.split(SEPARATOR_CLIENTS);
		if (userList != null && online != null) {
			userList.clear();
			for (String string : online) {
				userList.appendText(String.format("%s%s", string, SEPARATOR));
			}
			return true;
		}
		return false;
	}

	/**
	 * Creates a folder and file which will be registered significant events.
	 * 
	 * @return True if file is been created successfully.
	 */
	private boolean createLogFile() {
		return Log.createFile(DIR_NAME, FILE_NAME);
	}

	/**
	 * Log occurred event in text file.
	 * 
	 * @param event
	 *            String value of event to set.
	 * @return True if message is been written successfully.
	 */
	private synchronized boolean logEvent(String event) {
		return Log.logEvent(event);
	}

	/**
	 * Getter method for nickName.
	 *
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Setter method for nickName.
	 *
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public void run() {
		connect();
	}

	/**
	 * Setter method for language.
	 * 
	 * @param language
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * Getter method for language.
	 * 
	 * @return
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Getter method for ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Setter method for ip.
	 *
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Getter method for port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Setter method for port.
	 *
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Getter method for hashCode.
	 *
	 * @return the hashCode
	 */
	public int getHashCode() {
		return hashCode;
	}

	/**
	 * Setter method for hashCode.
	 *
	 * @param hashCode
	 *            the hashCode to set
	 */
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	/**
	 * Getter method for controller.
	 *
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Setter method for controller.
	 *
	 * @param controller
	 *            the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Getter method for run.
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return run;
	}
}
