package com.sirma.itt.javacourse.chatapplication.server.dbconnect;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.sirma.itt.javacourse.chatapplication.utility.Message;

/**
 * 
 * @author Petar Ivanov
 */
public class DBReadWriter {
	private EntityManagerFactory entityFactory;
	private final String query = "DELETE FROM Record";

	/**
	 * Constructor.
	 */
	public DBReadWriter() {
		entityFactory = GetEntityManagerFactory.getInstance();
	}

	/**
	 * Adds massage object into table.
	 * 
	 * @param messageToBeSaved
	 *            {@link Message}
	 */
	public void saveMessageIntoTable(Record record) {
		save(record);
	}

	/**
	 * Adds massage object into table.
	 * 
	 * @param message
	 *            {@link Message}
	 */
	private void save(Record record) {
		EntityManager em = entityFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(record);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Record> getListWithRecords() {
		EntityManager em = entityFactory.createEntityManager();
		em.getTransaction().begin();
		ArrayList<Record> listOfMessages = (ArrayList<Record>) (em.createQuery("SELECT r FROM Record r")
				.getResultList());
		em.getTransaction().commit();
		em.close();
		for (Record messageElement : listOfMessages) {
			System.out.println(messageElement.getTime() + " " + messageElement.getSender() + " "
					+ messageElement.getType() + " " + messageElement.getMessage());
		}
		return listOfMessages;
	}

	/**
	 * Clears all records.
	 */
	public int clearAllEntrysFromTable() {
		int numberOfEntryes = 0;
		EntityManager em = entityFactory.createEntityManager();
		em.getTransaction().begin();
		numberOfEntryes = em.createQuery(query).executeUpdate();
		// System.out.println("Deleted - " + + " accounts");
		em.getTransaction().commit();
		em.close();
		return numberOfEntryes;
	}

}
