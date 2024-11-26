package com.ctfs.common.email;

import java.util.Date;

public class GmailMessageBean  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	private String from;
	private String to;
	private Date date;
	private int size;
	private String content;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/*public String toString() {
		return BeanFunctions.serializeBeanForDisplay(this);
	} */
}

