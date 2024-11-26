package com.ctfs.framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateFormatter {
	private SimpleDateFormat formatter;
	public DateFormatter(String format) {
		formatter = new SimpleDateFormat(format);
	}
	public DateFormatter(String format, Locale locale) {
		formatter = new SimpleDateFormat(format, locale);
	}
	
	public Date parse(String dateString) {
		if (dateString == null) {
			dateString = "";
		}
		dateString = dateString.trim();
		if (dateString.equals("--") ||
			dateString.equals("00/00/00") ||
			dateString.equals("N/A") ||
			dateString.equals("yyyy-mm-dd") ||
			dateString.equals("aaaa-mm-jj")){
			dateString = "";
		}
		Date d = null;
		if (dateString != null) {
			dateString = dateString.trim();
		}
		try {
			if (dateString != null && dateString.length() > 0) {
				d = formatter.parse(dateString);
			}
		} catch (ParseException e) {
			throw new RuntimeException("Unable to parse date==" + dateString + "==");
		}
		
		return d;
	}
	
	private Map<String,String> monthOverrides;
	public void addMonthOverride(String origMonth, String overrideMonth) {
		if (monthOverrides == null) {
			monthOverrides = new HashMap<String,String>();
		}
		monthOverrides.put(origMonth, overrideMonth);
	}
	
	public String format(Date date) {
		if (date == null) {
			return "";
		}
		String rv = formatter.format(date);
		if (monthOverrides != null) {
			for (String origMonth : monthOverrides.keySet()) {
				System.out.println("origMonth=" + origMonth + " rv=" + rv);
				if (rv.contains(origMonth)) {
					rv = rv.replace(origMonth, monthOverrides.get(origMonth));
					break;
				}
			}
		}
		return rv;
	}
}
