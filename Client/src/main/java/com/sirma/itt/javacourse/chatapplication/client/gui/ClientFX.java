package com.sirma.itt.javacourse.chatapplication.client.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClientFX extends Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientFX.class);

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(ClientFX.class, (java.lang.String[]) null);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane page = (StackPane) FXMLLoader.load(ClientFX.class.getResource("Client.fxml"));
			Scene scene = new Scene(page);
			// scene.getStylesheets().add(getClass().getResource("myCss.css").toExternalForm());
			primaryStage.setMinHeight(620);
			primaryStage.setMinWidth(820);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Client");
			primaryStage.getIcons().add(new Image("clientLogo.jpg"));
			primaryStage.show();
			closeWindow(primaryStage);
		} catch (Exception ex) {
			LOGGER.error("something was wrong", ex);
		}
	}

	/**
	 * Window closing adapter.
	 * 
	 * @param primaryStage
	 *            To be closed.
	 */
	private void closeWindow(Stage primaryStage) {
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			primaryStage.close();
		});
	}
}