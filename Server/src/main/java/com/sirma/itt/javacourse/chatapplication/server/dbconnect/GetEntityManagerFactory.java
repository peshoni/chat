package com.sirma.itt.javacourse.chatapplication.server.dbconnect;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * GetEntityManagerFactory за цялото приложение. Класа е Singleton
 */
class GetEntityManagerFactory {
	private static EntityManagerFactory emFactory;

	private GetEntityManagerFactory() {
	}

	/**
	 * Връща EntityManagerFactory Singleton, който може да се ползва за получаване на EntityManager, който да бъде
	 * използван за изпълнение на заявки към базата данни
	 * 
	 * @return EntityManagerFactory Singleton.
	 */
	public static EntityManagerFactory getInstance() {
		if (emFactory == null) {
			emFactory = Persistence.createEntityManagerFactory("persistenceUnit");
		}
		return emFactory;
	}

	/**
	 * Извиква се преди затварянето на приложението
	 * 
	 * @return void
	 */
	public static void closeEntityManagerFactory() {
		if (emFactory == null)
			return;
		emFactory.close();
	}
}
