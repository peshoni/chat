package com.sirma.itt.javacourse.chatapplication.server;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.sirma.itt.javacourse.chatapplication.server.server.ClientConnection;
import com.sirma.itt.javacourse.chatapplication.server.server.ListOfClients;

public class TestListOfClients {

	@Test
	public void testListOfClients() {
		ListOfClients list = new ListOfClients();
		int expected = 0;
		Assert.assertTrue(expected == list.getOnlineUsers());
	}

	@Test
	public void testAdd() throws IOException {
		ListOfClients list = new ListOfClients();
		ClientConnection client = new ClientConnection();
		Assert.assertTrue(list.add(client));
	}

	@Test
	public void testRemove() {
		ListOfClients list = new ListOfClients();
		ClientConnection client = new ClientConnection();
		list.add(client);
		list.remove(client);
		Assert.assertTrue(0 == list.getOnlineUsers());
	}

	@Test
	public void testGetListOfUsers() {
		ListOfClients list = new ListOfClients();
		ClientConnection client = new ClientConnection();
		client.setName("Petar");
		list.add(client);
		Assert.assertTrue(list.getListOfUsers().contains("Petar"));
	}

	@Test
	public void testGetStatistic() {
		ListOfClients list = new ListOfClients();
		ClientConnection client = new ClientConnection();
		client.setName("Petar");
		list.add(client);
		String result = list.getStatistic();
		Assert.assertTrue(result.contains("Petar"));
	}

	@Test
	public void testClearListOfOnlineClients() {
		ListOfClients list = new ListOfClients();
		ClientConnection client = new ClientConnection();
		list.add(client);
		list.clearListOfOnlineClients();
		Assert.assertTrue(0 == list.getOnlineUsers());
	}

	@Test
	public void testContainsNickname() {
		ListOfClients list = new ListOfClients();
		ClientConnection client = new ClientConnection();
		client.setName("Petar");
		list.add(client);
		Assert.assertTrue(list.containsNickname("Petar"));
	}
}
