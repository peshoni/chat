package com.sirma.itt.javacourse.chatapplication.utility;

import java.io.Serializable;

/**
 * Class creates serializible object and has the necessary methods for this, namely to set the sender, recipient list of
 * users request for inclusion time stamp and the text of message.
 * 
 * @author Petar Ivanov
 */
public class Message implements Serializable {

	/**
	 * Comment for serialVersionUID.
	 */
	private static final long serialVersionUID = -7248865415001890060L;
	private MESSAGE_TYPES type;
	private Language language;
	private String sender;
	private String recipient;
	private String time;
	private String message;
	private String users;

	/**
	 * Constructor.
	 */
	public Message() {
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
		if (sender == null) {
			this.sender = "";
		} else {
			this.sender = String.format("%s%s", sender.toUpperCase().charAt(0), sender.substring(1));
		}
	}

	/**
	 * Getter method for recipient.
	 *
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Setter method for recipient.
	 *
	 * @param recepient
	 *            the recipient to set
	 */
	public void setRecipient(String recepient) {
		this.recipient = recepient;
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
		this.message = String.format("%s%s", message.toUpperCase().charAt(0), message.substring(1));
	}

	/**
	 * Getter method for users.
	 *
	 * @return the users
	 */
	public String getUsers() {
		return users;
	}

	/**
	 * Setter method for users.
	 *
	 * @param users
	 *            the users to set
	 */
	public void setUsers(String users) {
		this.users = users;
	}

	/**
	 * Getter method for language.
	 *
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Setter method for language.
	 *
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

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
}
