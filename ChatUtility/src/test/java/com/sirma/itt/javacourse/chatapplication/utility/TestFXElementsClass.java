package com.sirma.itt.javacourse.chatapplication.utility;

import org.junit.Assert;
import org.junit.Test;

import com.sirma.itt.javacourse.chatapplication.guiUtility.FXElements;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Petar Ivanov
 */
public class TestFXElementsClass {

	/**
	 * Test method for
	 * {@link com.sirma.itt.javacourse.chatapplication.guiUtility.FXElements#switchPane(javafx.scene.Node, javafx.scene.Node, javafx.scene.Node, javafx.scene.layout.BorderPane)}
	 * .
	 */
	@Test
	public void testSwitchPane() {
		BorderPane first = new BorderPane();
		BorderPane second = new BorderPane();
		BorderPane third = new BorderPane();
		third.setDisable(true);
		BorderPane pane = new BorderPane();
		FXElements.switchPane(null, first, second, third, pane);
		Assert.assertFalse(third.isDisable());
	}

	/**
	 * Test method for
	 * {@link com.sirma.itt.javacourse.chatapplication.guiUtility.FXElements#changeMenuItemState(javafx.scene.control.MenuItem)}
	 * .
	 */
	@Test
	public void testChangeMenuItemState() {
		MenuItem itemOne = new MenuItem("first item");
		itemOne.setDisable(true);
		itemOne.setVisible(false);
		FXElements.changeMenuItemState(itemOne);
		Assert.assertFalse(itemOne.isDisable());
	}
}
