package com.mc.innuce.global.util.hreftonewsdto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author JIN
 */
public class WebConverter {
	
	public int getIntPressKey(String href) {
		int idx = href.indexOf("le");
		return Integer.parseInt(href.substring(idx + 3, idx + 6));
	}
	
	public String getStringPressKey(String href) {
		return String.format("%03d", getIntPressKey(href));
	}
	
	public int getIntNewsKey(String href) {
		int idx = href.indexOf("le");
		return Integer.parseInt(href.substring(idx + 7, idx + 17));
	}
	
	public String getStringNewsKey(String href) {
		return String.format("%010d", getIntNewsKey(href));
	}

	public Timestamp getStringToTimestamp(String datestring) {
		// 2024-01-11 14:47:41
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try {
			Date stringToDate = sdf.parse(datestring);
			Timestamp ts = new Timestamp(stringToDate.getTime());
			return ts;
		} catch (ParseException e) {
			System.out.println("WebConverter.getTimestamp ERROR");
			e.printStackTrace();
		}
		return null;
	}

	public long getLongNewsKey(String href) {
		return Long.parseLong(getStringPressKey(href)+getStringNewsKey(href));
	}
}
