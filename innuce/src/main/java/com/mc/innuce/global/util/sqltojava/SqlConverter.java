package com.mc.innuce.global.util.sqltojava;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author JIN
 */
public class SqlConverter {
	
	public Calendar timestampToCalendar(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal; 
	}
	
}
