package com.sirma.itt.javacourse.chatapplication.utility;

/**
 * Build message object using Fluent interface.
 * 
 * @author Petar Ivanov
 */
public class BuildMessage {
	private Message message;

	/**
	 * Constructs message object.
	 */
	public BuildMessage() {
		message = new Message();
	}

	/**
	 * Setter method for type of message.
	 * 
	 * @param type
	 * @return {@link Message} with a type of message.
	 */
	public BuildMessage messageType(MESSAGE_TYPES type) {
		message.setType(type);
		return this;
	}

	/**
	 * Setter method for sender name.
	 * 
	 * @param sender
	 *            To set.
	 * @return{@link Message} with a set sender value.
	 */
	public BuildMessage from(String sender) {
		message.setSender(sender);
		return this;
	}

	/**
	 * Setter method for time stamp.
	 * 
	 * @param time
	 *            To set.
	 * @return{@link Message} with a set time value.
	 */
	public BuildMessage time(String time) {
		message.setTime(time);
		return this;
	}

	/**
	 * Setter method for message.
	 * 
	 * @param mesage
	 *            To set.
	 * @return{@link Message} with a set message value.
	 */
	public BuildMessage message(String messageToSet) {
		message.setMessage(messageToSet);
		return this;
	}

	/**
	 * Setter method for array with users names.
	 * 
	 * @param users
	 *            To set.
	 * @return{@link Message} with a set users array.
	 */
	public BuildMessage users(String users) {
		message.setUsers(users);
		return this;
	}

	/**
	 * Setter method for recipient of the message.
	 * 
	 * @param recepient
	 *            To set.
	 * @return{@link Message} with a set recipient value.
	 */
	public BuildMessage recipient(String recipient) {
		message.setRecipient(recipient);
		return this;
	}

	/**
	 * Setter method for language of the message.
	 * 
	 * @param language
	 * @return {@link Message} with a set {@link Language}.
	 */
	public BuildMessage language(Language language) {
		message.setLanguage(language);
		return this;
	}

	/**
	 * Message builder.
	 * 
	 * @return Created message object.
	 */
	public Message build() {
		return message;
	}
}
