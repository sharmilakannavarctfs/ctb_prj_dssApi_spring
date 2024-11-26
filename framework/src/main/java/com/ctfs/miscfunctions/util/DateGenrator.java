package com.ctfs.miscfunctions.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class DateGenrator {

	public static Random rand = new Random();
	public static String getRandomDOB(String format) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, randomNumber(1950, 1990));
		cal.set(Calendar.DAY_OF_YEAR, randomNumber(1, cal.getActualMaximum(Calendar.DAY_OF_YEAR)));
		SimpleDateFormat f = new SimpleDateFormat(format);
		String dob = f.format(cal.getTime());
		return dob;
	}
	
	public static String getMajorityAgeRandomDOB(String format) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, randomNumber(2005, 2018));
		cal.set(Calendar.DAY_OF_YEAR, randomNumber(1, cal.getActualMaximum(Calendar.DAY_OF_YEAR)));
		SimpleDateFormat f = new SimpleDateFormat(format);
		String dob = f.format(cal.getTime());
		return dob;
	}
	
	public static Date getRandomDOB() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1*randomNumber(20, 80));
		cal.add(Calendar.DATE, -1*randomNumber(1,365));
		return cal.getTime();
	}
	public static int randomNumber(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
	
}
