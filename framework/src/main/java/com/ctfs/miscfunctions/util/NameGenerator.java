package com.ctfs.miscfunctions.util;

import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class NameGenerator {
	
	public Random rand = new Random();
	public char [] alphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public String generateRandomFirstName() {
		return "NCAT" + generateRandomString(5);
	}
	
	public String generateRandomLastName() {
		return "AUTON" + generateRandomString(5);
	}
	
	public String generateLongFirstName() {
		return "NCAT" + generateRandomString(14);
	}
	
	public String generateLongLastName() {
		return "AUTON" + generateRandomString(16);
	}
	public String generateRandomString(int len) {
		return generateRandomString(alphas, len);
	}
	
	public String generateRandomString(char [] possibleVals, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<len; i++) {
			sb.append(possibleVals[rand.nextInt(possibleVals.length)]);
		}
		return sb.toString();
	}
}
