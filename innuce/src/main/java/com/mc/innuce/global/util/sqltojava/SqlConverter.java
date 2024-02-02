package com.mc.innuce.global.util.sqltojava;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author JIN
 */
public class SqlConverter {

	public LocalDate timestampToLocalDate(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalDate();
	}

	public LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
		return timestamp.toLocalDateTime();
	}
	
	public Timestamp localDateToTimestamp(LocalDate localdate) {
		return Timestamp.valueOf(localdate.atStartOfDay());
	}
	
	public Timestamp localDateTimeToTimestamp(LocalDateTime localdatetime) {
		return Timestamp.valueOf(localdatetime);
	}
}
