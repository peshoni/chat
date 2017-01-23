package com.sirma.itt.javacourse.chatapplication.server.gui;

import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.itt.javacourse.chatapplication.guiUtility.FXElements;
import com.sirma.itt.javacourse.chatapplication.server.enums.Captions;
import com.sirma.itt.javacourse.chatapplication.server.server.Server;
import com.sirma.itt.javacourse.chatapplication.utility.Language;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageBulgarian;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageEnglish;
import com.sirma.itt.javacourse.chatapplication.utility.LanguageState;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Server Controller class.
 * 
 * @author Petar Ivanov
 */
public class Controler implements Initializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Controler.class);
	private Language language;
	private LanguageState englishLanguage;
	private LanguageState bulgarianLanguage;
	private Thread serverThread;
	private Server server;
	private ArrayList<TextField> list;
	private String ip;
	private int port;
	private int backLog;

	@FXML
	private StackPane mainPane;
	@FXML
	private BorderPane borderPane;
	@FXML
	private SplitPane logPane;
	@FXML
	private AnchorPane leftSplitPane;
	@FXML
	private AnchorPane rightSplitPane;
	@FXML
	private Button start;
	@FXML
	private Button stop;
	@FXML
	private TextArea screen;
	@FXML
	private TextArea statisticArea;
	@FXML
	private Menu settings;
	@FXML
	private Menu languageMenu;
	@FXML
	private Menu users;
	@FXML
	private MenuItem online;
	@FXML
	private MenuItem eventArea;
	@FXML
	private ToggleGroup languageRadioGroup;
	@FXML
	private RadioMenuItem english;
	@FXML
	private RadioMenuItem bulgarian;
	@FXML
	private GridPane usersPane;
	@FXML
	private MenuItem address;
	@FXML
	private GridPane ipGrid;
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
	private TextField backlog;

	@FXML
	private Button saveServerAddres;
	@FXML
	private Button clearServerSettings;
	@FXML
	private GridPane dbPane;
	@FXML
	private TextArea dbArea;
	@FXML
	private MenuItem dbMenu;

	/**
	 * This method is called by the FXMLLoader when initialization is complete. {@inheritDoc}
	 */
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert logPane != null : "fx:id=\"logPane\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert usersPane != null : "fx:id=\"usersPane\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert leftSplitPane != null : "fx:id=\"leftSplitPane\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert rightSplitPane != null : "fx:id=\"rightSplitPane\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert start != null : "fx:id=\"firstButton\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert stop != null : "fx:id=\"secondButton\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert screen != null : "fx:id=\"screen\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert users != null : "fx:id=\"users\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert online != null : "fx:id=\"online\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert eventArea != null : "fx:id=\"eventArea\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert statisticArea != null : "fx:id=\"statisticArea\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert ipGrid != null : "fx:id=\"ipGrid\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert saveServerAddres != null : "fx:id=\"saveServerAddres\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert clearServerSettings != null : "fx:id=\"clearServerSettings\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert backlog != null : "fx:id=\"backlog\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert dbPane != null : "fx:id=\"dbPane\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert dbArea != null : "fx:id=\"eventArea\" was not injected: check your FXML file 'ServerGUI.fxml'.";
		assert dbMenu != null : "fx:id=\"eventArea\" was not injected: check your FXML file 'ServerGUI.fxml'.";

		server = new Server();
		server.setDbArea(dbArea);
		loadLanguage();
		FXElements.switchPane(usersPane, logPane, dbPane, ipGrid, borderPane);
		clearServerSettings.setDisable(true);
		stop.setDisable(true);
		start.setDisable(true);
		list = FXElements.createIpObservableList(ipOne, ipTwo, ipThree, ipFour);

		/**
		 * Validate port field
		 */
		FXElements.setTextLimit(portField, 5, 65535, "0-65535", false, false, true);
		FXElements.setTextLimit(backlog, 3, 100, "0-100", false, false, true);
		/**
		 * Starts server.
		 */
		start.setOnAction(e -> {
			turnOn();
		});
		/**
		 * Stops server.
		 */
		stop.setOnAction(e -> {
			turnOff();
		});
		english.setOnAction(event -> {
			englishLanguage.getState(language);
			setCaptions();
		});
		bulgarian.setOnAction(event -> {
			bulgarianLanguage.getState(language);
			setCaptions();
		});
		/**
		 * 
		 */
		online.setOnAction(e -> {
			FXElements.switchPane(null, null, null, usersPane, borderPane);
			// FXElements.changeMenuItemState(online);
			online.setDisable(true);
			// FXElements.changeMenuItemState(eventArea);
			eventArea.setDisable(false);
			eventArea.setVisible(true);
			dbMenu.setDisable(false);// dbArea.setDisable(false);
			// dbArea.setVisible(false);
		});
		/**
		 * 
		 */
		dbMenu.setOnAction(e -> {
			FXElements.switchPane(null, null, null, dbArea, borderPane);
			dbMenu.setDisable(true);
			server.showDdRecords();
		});
		/**
		 * 
		 */
		eventArea.setOnAction(e -> {
			eventArea.setDisable(true);
			online.setDisable(false);
			if (!server.isRun()) {
				stop.setDisable(true);
			}
			FXElements.switchPane(usersPane, ipGrid, dbPane, logPane, borderPane);
			dbMenu.setDisable(false);
		});

		address.setOnAction(e -> {
			FXElements.switchPane(null, null, null, ipGrid, borderPane);
		});

		saveServerAddres.setOnAction(e -> {
			StringBuilder builder = new StringBuilder();
			list.forEach(ipCell -> {
				builder.append(String.format("%s\u002E", ipCell.getText()));
			});
			if (builder.length() > 4 && portField.getText().length() > 0 && backlog.getText().length() > 0) {
				ip = builder.toString().substring(0, builder.length() - 1);
				port = Integer.parseInt(portField.getText());
				backLog = Integer.parseInt(backlog.getText());
				InetAddress address = null;
				try {
					address = InetAddress.getByName(ip);
					server.setIp(address);
					server.setPort(port);
					server.setBacklog(backLog);
					FXElements.changeStateOfNode(saveServerAddres, clearServerSettings);
					FXElements.switchPane(null, null, ipGrid, logPane, borderPane);
					FXElements.changeStateOfNode(start);
				} catch (Exception e1) {
					showAlert(new Alert(AlertType.INFORMATION), "Host", "Unknown host!");
					LOGGER.error("UnknownHost", e1);
				}
			} else {
				showAlert(new Alert(AlertType.INFORMATION), "Empty fields.", "Please complete all fields.");
			}
		});

		clearServerSettings.setOnAction(e -> {
			ip = "";
			port = 0;
			backLog = 0;
			list.forEach(ee -> {
				ee.clear();
				ee.setPromptText("255");
			});
			portField.clear();
			portField.setPromptText("0-65535");
		});
	}

	private void showAlert(Alert alert, String title, String contextText) {
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contextText);
		alert.showAndWait();
	}

	/**
	 * 
	 */
	private void turnOn() {
		server.setScreen(screen);
		server.setStatisticArea(statisticArea);
		server.setRun(true);
		// stop.setDisable(true);
		serverThread = new Thread(server);
		serverThread.setDaemon(true);
		serverThread.start();
		FXElements.changeStateOfNode(start, stop);
	}

	/**
	 * 
	 */
	private void turnOff() {
		server.stop();
		serverThread.interrupt();
		FXElements.changeStateOfNode(stop, start);
	}

	/**
	 * Creates language instances and initial basic language Bulgarian.
	 */
	private void loadLanguage() {
		language = new Language();
		server.setLanguage(language);
		englishLanguage = new LanguageEnglish();
		bulgarianLanguage = new LanguageBulgarian();
		bulgarianLanguage.getState(language);
		setCaptions();
	}

	/**
	 * Change captions by language state.
	 */
	private void setCaptions() {
		start.setText(Captions.START.getCaption(language.getState()));
		stop.setText(Captions.STOP.getCaption(language.getState()));
		settings.setText(Captions.SETTINGS.getCaption(language.getState()));
		languageMenu.setText(Captions.LANGUAGE.getCaption(language.getState()));
		english.setText(Captions.ENGLISH.getCaption(language.getState()));
		bulgarian.setText(Captions.BULGARIAN.getCaption(language.getState()));
		users.setText(Captions.USERS.getCaption(language.getState()));
		online.setText(Captions.ONLINE_USERS.getCaption(language.getState()));
		eventArea.setText(Captions.EVENTS.getCaption(language.getState()));
		saveServerAddres.setText(Captions.SAVE_IP_SETTINGS.getCaption(language.getState()));
		clearServerSettings.setText(Captions.CLEAR_IP_SETTINGS.getCaption(language.getState()));
		address.setText(Captions.SERVER.getCaption(language.getState()));
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
	 * Getter method for backlog.
	 *
	 * @return the backlog
	 */
	public int getBacklog() {
		return backLog;
	}

	/**
	 * Setter method for backlog.
	 *
	 * @param backlog
	 *            the backlog to set
	 */
	public void setBacklog(int backlog) {
		this.backLog = backlog;
	}
}
