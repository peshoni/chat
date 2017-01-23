package com.sirma.itt.javacourse.chatapplication.server.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.itt.javacourse.chatapplication.server.dbconnect.DBReadWriter;
import com.sirma.itt.javacourse.chatapplication.server.dbconnect.Record;
import com.sirma.itt.javacourse.chatapplication.server.enums.Messages;
import com.sirma.itt.javacourse.chatapplication.server.utils.NickNameValidator;
import com.sirma.itt.javacourse.chatapplication.utility.BuildMessage;
import com.sirma.itt.javacourse.chatapplication.utility.Language;
import com.sirma.itt.javacourse.chatapplication.utility.Log;
import com.sirma.itt.javacourse.chatapplication.utility.MESSAGE_TYPES;
import com.sirma.itt.javacourse.chatapplication.utility.Message;
import com.sirma.itt.javacourse.chatapplication.utility.TimeStamp;

import javafx.scene.control.TextArea;

/**
 * Chat server.
 * 
 * @author Petar Ivanov
 */
public class Server implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	private static final String NL = System.lineSeparator();
	private static final String DIR_NAME = "ServerEvents";
	private static final String FILE_NAME = "chatServerLog.log";
	private volatile boolean run;
	private NickNameValidator validator;
	private Language language;
	private ServerSocket serverSocket;
	private DBReadWriter dbReadWriter;

	private InetAddress ip;
	private int port;
	private int backlog;

	private ListOfClients list;
	private TextArea screen;
	private TextArea statisticArea;
	private TextArea dbArea;

	/**
	 * Creates server that provides clients information.
	 * 
	 * @param screen
	 *            Field coverage of events occurring.
	 */
	public Server() {
		list = new ListOfClients();
		validator = new NickNameValidator();
		setRun(false);
		createLogFile();
		dbReadWriter = new DBReadWriter();
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
	public synchronized boolean logEvent(String event) {
		if (event != null) {
			return Log.logEvent(event);
		} else
			return false;
	}

	/**
	 * Setter method for screen.
	 * 
	 * @param screen{@link
	 * 			{@link TextArea}
	 */
	public void setStatisticArea(TextArea statisticArea) {
		this.statisticArea = statisticArea;
	}

	/**
	 * Runs the server.
	 * 
	 * @throws UnknownHostException
	 */
	private void runServer() throws UnknownHostException {
		run = true;
		showEvent(String.format("%s:%s", "Host address", InetAddress.getLocalHost().getHostAddress()));
		try (ServerSocket socket = new ServerSocket(this.port, this.backlog, this.ip)) {
			serverSocket = socket;
			showEvent(String.format("Port: %s", serverSocket.getLocalPort()));
			showEvent(String.format("%s", Messages.LOG_CREATED.getMessageText(language.getState())));
			showEvent(Messages.START_SERVER.getMessageText(language.getState()));
			ClientConnection handler = null;
			String nickName = null;
			Message message;
			while (run) {
				try {
					handler = new ClientConnection();
					handler.setSocket(serverSocket.accept());
					handler.setServer(this);
					handler.openStreams();
					message = handler.receiveMessage();
					if (isRequest(message)) {
						nickName = message.getSender();
						showEvent(String.format("%s %s", nickName,
								Messages.IS_ONLINE.getMessageText(language.getState())));
						handler.setName(nickName);
						handler.sendMessage(new BuildMessage().from("server")
								.message(Messages.WELCOME.getMessageText(handler.getLanguage().getState()))
								.recipient(handler.getName()).time(TimeStamp.getCurrentTime())
								.users(list.getListOfUsers()).messageType(MESSAGE_TYPES.APPROVAL_FOR_INCLUSION)
								.build());
						list.add(handler);
						showEvent(String.format("%s %1s", nickName, "added to the list"));
						list.notifyAll(new BuildMessage().time(TimeStamp.getCurrentTime()).from("server")
								.users(list.getListOfUsers())
								.message(String.format("%s %s", nickName,
										Messages.IS_ONLINE.getMessageText(message.getLanguage().getState())))
								.recipient("all").messageType(MESSAGE_TYPES.STANDARD_MESSAGE).build());
						showEvent("Other users are notified for the event.");
						showStatistic();
						Thread client = new Thread(handler);
						client.start();
					} else {
						/**
						 * Send message to the client of his language.
						 */
						handler.sendMessage(new BuildMessage().from("server").time(TimeStamp.getCurrentTime())
								.message(Messages.NICK_NEW.getMessageText(handler.getLanguage().getState()))
								.users(list.getListOfUsers()).messageType(MESSAGE_TYPES.FAILURE_TO_CONNECT).build());
					}
				} catch (Exception e) {
					run = false;
					break;
				}
			}
		} catch (IOException e1) {
			showEvent("port taken....");
		}
	}

	/**
	 * Checks requests.
	 * 
	 * @param message
	 * @return
	 */
	private boolean isRequest(Message message) {
		String nickname = message.getSender();
		if (message.getType().equals(MESSAGE_TYPES.CONNECTION_REQUEST) && validator.validateNickName(nickname)
				&& !list.containsNickname(nickname)) {
			return true;
		}
		return false;
	}

	/**
	 * Shows statistic for online users.
	 */
	public void showStatistic() {
		statisticArea.setText(list.getStatistic());
	}

	/**
	 * Notifies users from the list, if any.
	 * 
	 * @param message
	 *            message forwarding.
	 */
	public void notifyOtherClients(Message message) {
		Message temp = message;

		if (list.getOnlineUsers() > 0) {
			list.notifyAll(new BuildMessage().from(String.format("%s", temp.getSender()))
					.messageType(MESSAGE_TYPES.STANDARD_MESSAGE).message(temp.getMessage()).recipient("all")
					.time(TimeStamp.getCurrentTime()).users(list.getListOfUsers()).language(language).build());
			showEvent(String.format("%s:%s", temp.getSender(), temp.getMessage()));
		}
		dbReadWriter.saveMessageIntoTable(createRecordInDb(message));
	}

	/**
	 * Extract necessary values fields from Message object.
	 * 
	 * @param message
	 * @return
	 */
	private Record createRecordInDb(Message message) {
		Record record = new Record();
		record.setTime(message.getTime());
		record.setSender(message.getSender());
		record.setType(message.getType());
		record.setMessage(message.getMessage());
		return record;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void run() {
		try {
			runServer();
		} catch (UnknownHostException e) {
			LOGGER.error("Unknown host.", e);
		}
	}

	/**
	 * Stops the server.
	 */
	public void stop() {
		if (serverSocket != null) {
			try {
				showEvent(Messages.STOP_SERVER.getMessageText(language.getState()));
				list.notifyAll(new BuildMessage().from("server").messageType(MESSAGE_TYPES.SERVER_SHUTDOWN)
						.recipient("all").time(TimeStamp.getCurrentTime()).users("").language(language).build());
				serverSocket.close();
				list.clearListOfOnlineClients();
				showStatistic();
				run = false;
			} catch (IOException e) {
				showEvent("Couldn't close the server socket.");
			}
		}
	}

	/**
	 * 
	 * @param client
	 */
	public void dissconnectClient(ClientConnection client) {
		if (list.remove(client)) {
			showEvent(String.format("%s %s", client.getName(), "is Offline."));
			list.notifyAll(new BuildMessage().time(TimeStamp.getCurrentTime()).users(list.getListOfUsers())
					.from("server").recipient("all").messageType(MESSAGE_TYPES.STANDARD_MESSAGE)
					.message(String.format("%s %s", client.getName(), "is offline.")).build());
		}
	}

	/**
	 * Visualizing system messages on the screen.
	 * 
	 * @param event
	 *            Message to set.
	 */
	public boolean showEvent(String event) {
		if (event != null && (screen != null) && run) {
			StringBuilder builder = new StringBuilder();
			builder.append(String.format("%s%s%s", TimeStamp.getCurrentTime(), event, NL));
			screen.appendText(builder.toString());
			Log.logEvent(builder.toString());
			return true;
		}
		return false;
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
	 * Getter method for ip.
	 *
	 * @return the ip
	 */
	public InetAddress getIp() {
		return ip;
	}

	/**
	 * Setter method for ip.
	 *
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(InetAddress ip) {
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
	 * Setter method for screen.
	 * 
	 * @param screen
	 *            {@link TextArea}
	 */
	public void setScreen(TextArea screen) {
		this.screen = screen;
	}

	/**
	 * Getter method for screen.
	 * 
	 * @return {@link TextArea}
	 */
	public TextArea getScreen() {
		return this.screen;
	}

	/**
	 * Getter method for run.
	 * 
	 * @return run
	 */
	public boolean isRun() {
		return run;
	}

	/**
	 * Setter method for run.
	 * 
	 * @param run
	 */
	public void setRun(boolean run) {
		this.run = run;
	}

	/**
	 * Getter method for backlog.
	 *
	 * @return the backlog
	 */
	public int getBacklog() {
		return backlog;
	}

	/**
	 * Setter method for backlog.
	 *
	 * @param backalog
	 *            the backlog to set
	 */
	public void setBacklog(int backalog) {
		this.backlog = backalog;
	}

	public ListOfClients getHandleToTheList() {
		return this.list;
	}

	/**
	 * Getter method for dbArea.
	 *
	 * @return the dbArea
	 */
	public TextArea getDbArea() {
		return dbArea;
	}

	/**
	 * Setter method for dbArea.
	 *
	 * @param dbArea
	 *            the dbArea to set
	 */
	public void setDbArea(TextArea dbArea) {
		this.dbArea = dbArea;
	}

	public void showDdRecords() {
		ArrayList<Record> listOfMessages = dbReadWriter.getListWithRecords();
		dbArea.clear();
		if (listOfMessages.size() > 0) {
			listOfMessages.forEach(e -> {
				String record = String.format("%s - %s - %s - %s.%s", e.getTime(), e.getSender(), e.getType(),
						e.getMessage(), System.lineSeparator());
				dbArea.appendText(record);
			});
		}
	}
}
