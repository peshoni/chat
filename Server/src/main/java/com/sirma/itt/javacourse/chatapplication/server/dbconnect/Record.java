package com.sirma.itt.javacourse.chatapplication.server.dbconnect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sirma.itt.javacourse.chatapplication.utility.MESSAGE_TYPES;

/**
 * 
 * @author Petar Ivanov
 */
@Entity
public class Record {
	@Id
	@GeneratedValue
	private int id;
	private String time;
	private String sender;
	private MESSAGE_TYPES type;
	private String message;

	/**
	 * Getter method for type.
	 *
	 * @return the type
	 */
	public MESSAGE_TYPES getType() {
		return type;
	}

	/**
	 * Setter method for type.
	 *
	 * @param type
	 *            the type to set
	 */
	public void setType(MESSAGE_TYPES type) {
		this.type = type;
	}

	/**
	 * Getter method for sender.
	 *
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Setter method for sender.
	 *
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Getter method for time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Setter method for time.
	 *
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Getter method for message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter method for message.
	 *
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
