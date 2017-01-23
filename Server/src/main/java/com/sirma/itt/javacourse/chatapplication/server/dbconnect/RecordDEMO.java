package com.sirma.itt.javacourse.chatapplication.server.dbconnect;

import com.sirma.itt.javacourse.chatapplication.utility.BuildMessage;
import com.sirma.itt.javacourse.chatapplication.utility.MESSAGE_TYPES;
import com.sirma.itt.javacourse.chatapplication.utility.Message;
import com.sirma.itt.javacourse.chatapplication.utility.TimeStamp;

/**
 * 
 * @author Petar Ivanov
 */
public class RecordDEMO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBReadWriter readWriter = new DBReadWriter();
		Message message = new BuildMessage().from("Petar").messageType(MESSAGE_TYPES.CONNECTION_REQUEST)
				.time(TimeStamp.getCurrentTime()).build();
		Record record = new Record();
		record.setSender(message.getSender());
		record.setTime(message.getTime());
		record.setType(message.getType());
		record.setMessage(message.getMessage());
		readWriter.saveMessageIntoTable(record);

		readWriter.getListWithRecords();
		readWriter.clearAllEntrysFromTable();
		readWriter.getListWithRecords();
		GetEntityManagerFactory.closeEntityManagerFactory();
	}
}
