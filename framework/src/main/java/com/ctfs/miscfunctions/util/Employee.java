package com.ctfs.miscfunctions.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Employee {

	public static Random rand = new Random();
	
	public static String getRandomEmployer(String notEmp) {
		String rv = getRandomValue(employers, notEmp)  + " " + generateRandomString(5);
		return rv;
	}
	
	public static String getRandomValue(String [] values, String notVal) {
		int index = randomNumber(0, values.length - 1);
		String rv = values[index];
		if (rv.equalsIgnoreCase(notVal)) {
			if (index > 0) {
				rv = values[index - 1];
			} else {
				rv = values[randomNumber(1, values.length - 1)];
			}
		}
		return rv;
	}
	
	public static int randomNumber(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static char [] alphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static String generateRandomString(int len) {
		return generateRandomString(alphas, len);
	}
	
	public static char [] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ'-".toCharArray();
	public static String generateString(int len)
	{
		return generateRandomString(alpha, len);
	}
	
	public static String generateRandomString(char [] possibleVals, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<len; i++) {
			sb.append(possibleVals[rand.nextInt(possibleVals.length)]);
		}
		return sb.toString();
	}
	
	public static String [] employers = new String [] {
			"RIM",
			"NORTEL",
			"DND",
			"ARMY",
			"NAVY",
			"AIRFORCE",
			"WALMART",
			"TARGET",
			"THE BAY",
			"DOLLAR TREE",
			"CTFS",
			"CTC",
			"GM",
			"FORD",
			"DODGE",
			"TOYOTA",
			"SUNCORP",
			"TRW",
			"CITY OF WELLAND",
			"CRA",
			"WELLAND HOSPITAL",
			"STELCO",
			"OC TRANSPO",
			"TTC"
		};
	
	public static String getRandomOccupation(String notOcc) {
		String rv = getRandomValue(occupations, notOcc);
		return rv;
	}
	
	public static String [] occupations = new String [] {
			"MANAGER",
			"ENG",
			"MSEO",
			"CSEO",
			"STOKER",
			"BOWSEN",
			"UWWO",
			"AWWO",
			"ELECT",
			"TECHSUP",
			"CLERK",
			"LAWYER",
			"DOCTER",
			"CODER",
			"PLUMBER",
			"WELDER",
			"INFANTY",
			"GUNNER",
			"TANKER"
		};
	
	public static String getRandomCity(String notCity) {
		return getRandomValue(cities, notCity);
	}
	public static final String [] cities = new String [] {
			"WELLAND",
			"OTTAWA",
			"TORONTO",
			"HAMILTON",
			"CALGARY",
			"EDMONTON",
			"MOOSE JAW",
			"MONTREAL",
			"VANCOUVER",
			"REGINA"
		};
}
