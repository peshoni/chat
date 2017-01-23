package com.sirma.itt.javacourse.chatapplication.server.gui;

import java.net.Inet4Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ServerFX extends Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerFX.class);

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(ServerFX.class, (java.lang.String[]) null);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane page = (StackPane) FXMLLoader.load(ServerFX.class.getResource("ServerGUI.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Server " + Inet4Address.getLocalHost().getHostAddress());
			primaryStage.getIcons().add(new Image("bubble.jpg"));
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