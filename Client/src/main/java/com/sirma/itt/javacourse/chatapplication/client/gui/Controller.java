package com.sirma.itt.javacourse.chatapplication.client.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.itt.javacourse.chatapplication.client.client.Client;
import com.sirma.itt.javacourse.chatapplication.client.statelanguage.Captions;
import com.sirma.itt.javacourse.chatapplication.client.statelanguage.Messages;
import com.sirma.itt.javacourse.chatapplication.guiUtility.FXElements;
import com.sirma.itt.javacourse.chatapplication.utility.Language;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageEnglish;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageState;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Client controller.
 * 
 * @author Petar Ivanov
 */
public class Controller implements Initializable, EventHandler<ActionEvent> {
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	private Client client;
	private Thread clientThread;
	private Language language;
	private LanguageState englishLanguage;
	private LanguageState bulgarianLanguage;
	private String ip;
	private int port;
	private ArrayList<TextField> list;
	private Hashtable<Object, String> properties;
	// private Field[] array;

	@FXML
	private StackPane stackPane;
	@FXML
	private GridPane chatPane;
	@FXML
	private GridPane ipGrid;
	@FXML
	private BorderPane firstPane;
	@FXML
	private TextField writeArea;
	@FXML
	private TextField nick;
	@FXML
	private FlowPane flowPane;
	@FXML
	private BorderPane connectPane;
	@FXML
	private Button sendButton;
	@FXML
	private Button connect;
	@FXML
	private Button disconnect;
	@FXML
	private Button saveServerAddres;
	@FXML
	private Button clearServerSettings;
	@FXML
	private TextArea readArea;
	@FXML
	private MenuBar menuBar;
	@FXML
	private TextField nickName;
	@FXML
	private Menu settings;
	@FXML
	private MenuItem server;
	@FXML
	private MenuItem chat;
	@FXML
	private Menu changeLanguage;
	@FXML
	private RadioMenuItem english;
	@FXML
	private RadioMenuItem bulgarian;
	@FXML
	private Menu menuInfo;
	@FXML
	private MenuItem about;
	@FXML
	private MenuItem help;
	@FXML
	private MenuItem home;
	@FXML
	private BorderPane connectPane1;
	@FXML
	private GridPane loginGrid;
	@FXML
	private TextArea clientsList;
	@FXML
	private Label labelOnlineUsers;
	@FXML
	private Label labelLoggedAs;
	@FXML
	private GridPane sendGridPane;
	@FXML
	private TextField ipOne;
	@FXML
	private TextField ipTwo;
	@FXML
	private TextField ipThree;
	@FXML
	private TextField ipFour;
	@FXML
	private TextField portField;
	@FXML
	private ToggleGroup languageRadioGroup;

	protected void setClient(Client client) {
		this.client = client;
	}

	/**
	 * 
	 */
	@FXML
	public void doSomething() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stopClient();
				openModalWindow(new Text("Insert new nickname..."), "Invalid nickname", 300, 200);
				FXElements.switchPane(null, null, chatPane, ipGrid, firstPane);
				nickName.requestFocus();
			}
		});
	}

	public void showNoConnectionWindow() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				openModalWindow(
						new Text(
								"No connection with server. \n1.Address is invalid.\n2.Connection timed out.\n Check ip settings."),
						"No connection with server.", 300, 200);
			}

		});

	}

	@FXML
	public void handleEnterPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			sendMessage();
		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'Client.fxml'.";
		assert chatPane != null : "fx:id=\"chatPane\" was not injected: check your FXML file 'Client.fxml'.";
		assert ipGrid != null : "fx:id=\"ipGrid\" was not injected: check your FXML file 'Client.fxml'.";
		assert firstPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Client.fxml'.";
		assert writeArea != null : "fx:id=\"writeArea\" was not injected: check your FXML file 'Client.fxml'.";
		assert nick != null : "fx:id=\"nick\" was not injected: check your FXML file 'Client.fxml'.";
		assert flowPane != null : "fx:id=\"flowPane\" was not injected: check your FXML file 'Client.fxml'.";
		assert connectPane != null : "fx:id=\"connectPane\" was not injected: check your FXML file 'Client.fxml'.";
		assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'Client.fxml'.";
		assert connect != null : "fx:id=\"connect\" was not injected: check your FXML file 'Client.fxml'.";
		assert disconnect != null : "fx:id=\"disconnect\" was not injected: check your FXML file 'Client.fxml'.";
		assert readArea != null : "fx:id=\"readArea\" was not injected: check your FXML file 'Client.fxml'.";
		assert server != null : "fx:id=\"server\" was not injected: check your FXML file 'Client.fxml'.";
		assert chat != null : "fx:id=\"chat\" was not injected: check your FXML file 'Client.fxml'.";
		assert help != null : "fx:id=\"help\" was not injected: check your FXML file 'Client.fxml'.";
		assert about != null : "fx:id=\"about\" was not injected: check your FXML file 'Client.fxml'.";
		assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'Client.fxml'.";
		assert nickName != null : "fx:id=\"nickName\" was not injected: check your FXML file 'Client.fxml'.";
		assert settings != null : "fx:id=\"settings\" was not injected: check your FXML file 'Client.fxml'.";
		assert loginGrid != null : "fx:id=\"loginGrid\" was not injected: check your FXML file 'Client.fxml'.";
		assert connectPane1 != null : "fx:id=\"connectPane1\" was not injected: check your FXML file 'Client.fxml'.";
		assert clientsList != null : "fx:id=\"clientsList\" was not injected: check your FXML file 'Client.fxml'.";
		assert labelOnlineUsers != null : "fx:id=\"labelOnlineUsers\" was not injected: check your FXML file 'Client.fxml'.";
		assert labelLoggedAs != null : "fx:id=\"labelInsertNickName\" was not injected: check your FXML file 'Client.fxml'.";
		assert changeLanguage != null : "fx:id=\"changeLanguage\" was not injected: check your FXML file 'Client.fxml'.";
		assert saveServerAddres != null : "fx:id=\"saveServerAddres\" was not injected: check your FXML file 'Client.fxml'.";
		assert clearServerSettings != null : "fx:id=\"clearServerSettings\" was not injected: check your FXML file 'Client.fxml'.";

		properties = new Hashtable<Object, String>();
		clearServerSettings.setDisable(true);
		connect.setDisable(true);
		nick.setEditable(false);
		loadLanguage();
		FXElements.changeStateOfNode(disconnect, sendButton, writeArea);
		FXElements.switchPane(null, null, chatPane, ipGrid, firstPane);
		list = FXElements.createIpObservableList(ipOne, ipTwo, ipThree, ipFour);
		FXElements.setTextLimit(portField, 5, 65535, "0-65535", false, false, true);
		FXElements.setTextLimit(writeArea, 200, 0, Messages.WRITE.getMessageText(language.getState()), true, false,
				false);
		/**
		 * Connect event.
		 */
		//// ******************************************************************
		saveServerAddres.setOnAction(e -> {
			StringBuilder builder = new StringBuilder();
			list.forEach(ipCell -> {
				builder.append(String.format("%s\u002E", ipCell.getText()));
			});

			if (builder.length() > 4 && portField.getText().length() > 0) {
				ip = builder.toString().substring(0, builder.length() - 1);
				port = Integer.parseInt(portField.getText());
				FXElements.changeStateOfNode(saveServerAddres, clearServerSettings, connect);
			} else {
				openModalWindow(new Text("Empty fields.."), "Incorrect data", 200, 100);
			}

		});
		clearServerSettings.setOnAction(e -> {
			clearAddressSettings();
		});

		connect.setOnAction(e -> {
			FXElements.changeStateOfNode(disconnect, sendButton, writeArea);
			loadStartPage();
			startClient();
		});
		/**
		 * Creates runnable client.
		 */

		disconnect.setOnAction(e -> {
			FXElements.changeStateOfNode(disconnect, sendButton, writeArea);
			stopClient();
		});

		server.setOnAction(e -> {
			FXElements.switchPane(null, null, chatPane, ipGrid, firstPane);
		});

		chat.setOnAction(e -> {
			FXElements.switchPane(null, null, ipGrid, chatPane, firstPane);
		});

		english.setOnAction(event -> {
			englishLanguage.getState(language);
			setCaptions();
		});
		bulgarian.setOnAction(event -> {
			bulgarianLanguage.getState(language);
			setCaptions();
		});

		english.setOnAction(this);
		properties.put(english, english.getId());
		sendButton.setOnAction(this);
		properties.put(sendButton, sendButton.getId());
		sendButton.setOnAction(this);
		properties.put(sendButton, sendButton.getId());
		about.setOnAction(this);
		properties.put(about, about.getId());
		help.setOnAction(this);
		properties.put(help, help.getId());
	}

	/**
	 * Clears ip and port fields and set enable state of connect button.
	 */
	private void clearAddressSettings() {
		ip = "";
		port = 0;
		list.forEach(element -> {
			element.clear();
			element.setPromptText("255");
		});
		portField.clear();
		portField.setPromptText("65535");
		FXElements.changeStateOfNode(clearServerSettings, saveServerAddres);
		connect.setDisable(true);
	}

	/**
	 * 
	 */
	public void loadStartPage() {
		FXElements.changeStateOfNode(connect, disconnect);
		FXElements.switchPane(null, null, ipGrid, chatPane, firstPane);
	}

	private void startClient() {
		client = new Client();
		client.setController(this);
		client.setIp(ip);
		client.setPort(port);
		client.setScreen(readArea);
		client.setLanguage(language);
		client.setAreaWithOnlineUsers(clientsList);
		disconnect.setDisable(false);
		client.setNickName(nickName.getText());
		clientThread = new Thread(client);
		clientThread.setDaemon(true);
		clientThread.start();
		nickName.setEditable(false);
		nick.setText(nickName.getText());
		writeArea.requestFocus();
	}

	private void openModalWindow(Text text, String title, int width, int height) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(null);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(text);
		dialogVbox.setAlignment(Pos.CENTER);
		Scene dialogScene = new Scene(dialogVbox, width, height);
		dialog.setScene(dialogScene);
		dialog.getIcons().add(new Image("clientLogo.jpg"));
		dialog.setTitle(title);
		dialog.show();

		dialog.setOnCloseRequest(e -> {
			e.consume();
			dialog.close();
		});
	}

	/**
	 * Stops client.
	 */
	public void stopClient() {
		client.stopClient();
		nickName.clear();
		nick.clear();
		nickName.setEditable(true);
		FXElements.changeStateOfNode(connect, disconnect);
		FXElements.switchPane(null, null, chatPane, ipGrid, firstPane);
	}

	/**
	 * Initial basic language Bulgarian.
	 */
	private void loadLanguage() {
		language = new Language();
		englishLanguage = new LanguageEnglish();
		bulgarianLanguage = new LanguageBulgarian();
		bulgarianLanguage.getState(language);
		setCaptions();
	}

	/**
	 * Set captions of the user interface.
	 */
	private void setCaptions() {
		settings.setText(Captions.SETTINGS.getCaption(language.getState()));
		english.setText(Captions.ENGLISH.getCaption(language.getState()));
		bulgarian.setText(Captions.BULGARIAN.getCaption(language.getState()));
		server.setText(Captions.SERVER.getCaption(language.getState()));
		changeLanguage.setText(Captions.CHANGE_LANGUAGE.getCaption(language.getState()));
		sendButton.setText(Captions.SEND.getCaption(language.getState()));
		writeArea.setPromptText(Captions.WRITE_MESSAGE.getCaption(language.getState()));
		labelOnlineUsers.setText(Captions.ONLINE_USERS.getCaption(language.getState()));
		labelLoggedAs.setText(Captions.LOGGED_AS.getCaption(language.getState()));
		saveServerAddres.setText(Captions.SAVE_IP_SETTINGS.getCaption(language.getState()));
		chat.setText(Captions.CHAT.getCaption(language.getState()));
		help.setText(Captions.HELP.getCaption(language.getState()));
		about.setText(Captions.ABOUT.getCaption(language.getState()));
		connect.setText(Captions.CONNECT.getCaption(language.getState()));
		disconnect.setText(Captions.DISCONNECT.getCaption(language.getState()));
		nickName.setPromptText(Captions.NICKNAME.getCaption(language.getState()));
		nick.setPromptText(Captions.NOT_LOGGED.getCaption(language.getState()));
		clearServerSettings.setText(Captions.CLEAR_IP_SETTINGS.getCaption(language.getState()));
		menuInfo.setText(Captions.INFO.getCaption(language.getState()));
	}

	@Override
	public void handle(ActionEvent event) {
		switch (properties.get(event.getSource())) {
		case "about":
			openModalWindow(new Text("This is about text"), Captions.ABOUT.getCaption(language.getState()), 400, 300);
			break;
		case "help":
			openModalWindow(new Text("Write message + ENTER -> send message."),
					Captions.HELP.getCaption(language.getState()), 400, 300);
			break;
		case "english":
			englishLanguage.getState(language);
			setCaptions();
			break;
		case "sendButton":
			sendMessage();
			break;
		default:
			break;
		}
	}

	/**
	 * Send text message to the server.
	 * 
	 * @return True if message is not empty and send successfully.
	 */
	private boolean sendMessage() {
		String message = writeArea.getText();
		if (!message.isEmpty()) {
			client.sendMessage(message);
			writeArea.clear();
			writeArea.focusTraversableProperty();
			writeArea.requestFocus();
			return true;
		} else {
			return false;
		}
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
}
