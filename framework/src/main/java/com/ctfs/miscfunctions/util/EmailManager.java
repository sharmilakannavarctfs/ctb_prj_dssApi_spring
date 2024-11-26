package com.ctfs.miscfunctions.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class EmailManager {
	private Connection conn = null;
	
	public EmailManager(Connection conn) throws Throwable {
		this.conn = conn;
	}
		
	public String getEmailAccount() throws SQLException {
		String newEmail = "";
		String sql = "select * from gmailvirtualaccounts where virtual_name = ?";	
		
		PreparedStatement ps = conn.prepareStatement(sql);
		boolean done = false;
		do {
			newEmail = generateEmail();
			ResultSet rs = null;
			ps.setString(1, newEmail);
			rs = ps.executeQuery();
			
			done = !rs.next();
			rs.close();
			
		} while (!done);
		
		String sql2 = "insert into gmailvirtualaccounts values (?, sysdate)";
		PreparedStatement ps2 = conn.prepareStatement(sql2);	
		
		ps2.setString(1, newEmail);		
		ps2.executeUpdate();
		
		conn.commit();
		ps.close();		
		ps2.close();
		
		newEmail += "@gmail.com";
		return newEmail;
	}
	
	private String generateEmail() {
		String baseEmail = "ctfsautomationtester1234567890";
				
		char chars[] = baseEmail.toCharArray();
		
		String newEmail = "";
		for (int i = 0; i < chars.length; i++) {
			newEmail += chars[i];
			if (i != chars.length-1 && randomNumber(0,100) > 50) {
				newEmail += ".";
			}
		}
				
		return newEmail;
	}
	
	public static Random rand = new Random();
	public static int randomNumber(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

	 public static String dummyEmail()
	 {
		 String emailStr=Employee.generateRandomString(10);
	        //String[] arr={"@gmail.com", "@yahoo.com", "@yahoo.co.in", "@ngit.in", "@ctfs.com"};
		 	ArrayList<String> email=new ArrayList<String>();
		 	email.add("@gmail.com");
		 	email.add("@yahoo.com");
		 	email.add("@yahoo.co.in");
		 	email.add("@ngit.in");
		 	email.add("@ctfs.com");
	        int randomNumber=rand.nextInt(email.size());
	        System.out.println(emailStr+email.get(randomNumber));
	        return emailStr+email.get(randomNumber);
	 }
}
