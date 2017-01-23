package com.sirma.itt.javacourse.chatapplication.guiUtility;

import java.util.ArrayList;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Petar Ivanov
 */
public class FXElements {
	/**
	 * Private constructor.
	 */
	private FXElements() {
	}

	/**
	 * 
	 * @param firstToDisable
	 *            Pane that will be disabled.
	 * @param secondToDisable
	 *            Pane that will be disabled.
	 * @param toTurnOn
	 *            Pane that will be enabled.
	 * @param borderPane
	 *            To set in center enabled node.
	 */
	public static void switchPane(Node firstToDisable, Node secondToDisable, Node thirdTodisable, Node toTurnOn,
			BorderPane borderPane) {
		if (firstToDisable != null) {
			firstToDisable.setDisable(true);
			firstToDisable.setVisible(false);
		}
		if (secondToDisable != null) {
			secondToDisable.setDisable(true);
			secondToDisable.setVisible(false);
		}
		if (thirdTodisable != null) {
			thirdTodisable.setDisable(true);
			thirdTodisable.setVisible(false);
		}
		if (toTurnOn != null) {
			toTurnOn.setDisable(false);
			toTurnOn.setVisible(true);
			borderPane.setCenter(toTurnOn);
		}
	}

	/**
	 * Set a limit on text fields.
	 * 
	 * @param textField
	 *            Text field to limit.
	 * @param length
	 *            Max length of the text in text field.
	 * @param maxValue
	 *            Maximum value, depending on whether it is port or ip.
	 * @param promptText
	 *            Prompt text.
	 * @param message
	 *            true in the case of field communication and false if not.
	 * @param ip
	 *            true in the case of field "ip" and false if not.
	 * @param port
	 *            true in the case of field "port" and false if not.
	 */
	public static void setTextLimit(TextField textField, int length, int maxValue, String promptText, boolean message,
			boolean ip, boolean port) {
		textField.setPromptText(promptText);
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (port || ip) {
					textField.setAlignment(Pos.BASELINE_RIGHT);
					if (newValue.length() > length || !newValue.matches("[0-9]*")
							|| (Integer.parseInt(newValue) > maxValue)) {
						((StringProperty) observable).setValue(oldValue);
					}
				}
				if (message) {
					if (newValue.length() > length) {
						((StringProperty) observable).setValue(oldValue);
					}
				}
			} catch (Exception e1) {
			}
		});
	}

	/**
	 * 
	 * @param fields
	 * @return List with ip fields.
	 */
	public static ArrayList<TextField> createIpObservableList(TextField... fields) {
		ArrayList<TextField> list = new ArrayList<TextField>();
		list.addAll(FXCollections.observableArrayList(fields));
		/**
		 * Validate input strings in ip textfields.
		 */
		list.forEach(e -> {
			FXElements.setTextLimit(e, 3, 255, "255", false, true, false);
		});
		return list;
	}

	/**
	 * Changes in the opposite state.
	 * 
	 * @param node
	 *            Node to process.
	 */
	public static void changeStateOfNode(Node... node) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.addAll(FXCollections.observableArrayList(node));
		nodes.forEach(e -> {
			e.setDisable(!e.isDisable());
		});
	}

	/**
	 * Change menu item state and visibility.
	 * 
	 * @param menuItem
	 *            MenuItem to process.
	 */
	public static void changeMenuItemState(MenuItem menuItem) {
		menuItem.setDisable(!menuItem.isDisable());
		menuItem.setVisible(!menuItem.isVisible());
	}
}
