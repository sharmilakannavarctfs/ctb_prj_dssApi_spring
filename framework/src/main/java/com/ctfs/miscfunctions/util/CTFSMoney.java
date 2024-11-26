package com.ctfs.miscfunctions.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

import org.springframework.stereotype.Component;
@Component
public class CTFSMoney implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8800123973953297144L;
	
	private long money;
	String format = "#.00";	// default 12345.67
			
	public CTFSMoney(String money) {
		this.money = parseCurrency(money);
	}
    
	public CTFSMoney(double money) {				
		this.money = Math.round(money*100);
	}
	
	public CTFSMoney(int money) {	
		this.money = Math.round(money*100);
	}
	
    public CTFSMoney(long money) {
    	this.money = money;
    }
    
    public CTFSMoney(long money, Boolean dollars) {    	
    	if (dollars) {
    		money = Math.round(money*100);
    	}
    	this.money = money;
    }
    
    public void setFormat(String format) {
    	this.format = format;
    }
    
    public long getMoneyLong() {
    	return money;
    }
    
    public String getFormattedMoney() {
    	return new DecimalFormat(format).format(money/100.0);
    }
    
    public String getFormattedMoney(String format) {
    	return new DecimalFormat(format).format(money/100.0);
    }
    
	public static long parseCurrency(String ss) {
		long l = 0;
		
		String s = "";
		for (int i=0; i<ss.length(); i++) {
			char c = ss.charAt(i);
			if (Character.isDigit(c) || c == '.' || c == ',' || c=='(' || c==')' || c=='-' || c=='+' || c=='�') {
				s += c;
			}
		}
		
		//if cents then 
		if (s.contains("�")) {
			s = s.replace("�", "");
			s = s.trim();
			return Long.parseLong(s);
		}
		s = s.trim();
		if (s.equals("--") || s.length() == 0) {
			return 0;
		}
		if (s.charAt(0) == '.') {
			s = "0" + s;
		}
		if (s.length() > 1 && s.substring(0, 2).equals("-.")) {
			s = s.replace("-.","-0.");
		}
		if (s.startsWith("+")) {
			s = s.substring(1, s.length());
		}
		s = s.replace("$", "");
		s = s.replace("%", "");
		s = s.replaceAll(" ", "");
//System.out.println("s1=" + s);
		int negative = 1;
		if (s.indexOf("(") > -1 && s.indexOf(")") > -1) {
			negative = -1;
			s = s.replace("(", "");
			s = s.replace(")", "");
		}
		if (s.startsWith("-")) {
			negative = -1;
//System.out.println("isNegative");
		}
		// check for French format and replace the comma with period
		int commaIndex = s.lastIndexOf(",");
//System.out.println("commanIndex=" + commaIndex + " s.length - 2=" + (s.length() - 3));
		if (commaIndex >= 0) {
			if (commaIndex == (s.length() - 3)) {
				s = s.replaceAll(",", ".");
			}
		}
//System.out.println("s2=" + s);
		s = s.replaceAll(",", "");
//System.out.println("s3=" + s);
		if (s.startsWith(".")) {
			s = "0" + s;
		}
		int decimalIndex = s.indexOf(".");
		if (decimalIndex >=0) {
			int decimalPlaces = s.length() - decimalIndex - 1;
			if (decimalPlaces == 4) {
				// continue if ends in 00, just drop the extra chars. Some TSYS fields are formatted this way.
				if (s.endsWith("00")) {
					s = s.substring(0, s.length() - 2);
					decimalPlaces = s.length() - decimalIndex - 1;
				}
			}
			if (decimalPlaces > 2) {
				throw new NumberFormatException("s=" + s + " can't be currency. decumalPlaces=" + decimalPlaces);
			}
			//System.out.println("parsing s==" + s + "==");
			long l1 = Math.abs(Long.parseLong(s.substring(0, decimalIndex)));
			long l2 = Long.parseLong(s.substring(decimalIndex + 1));
			l = l1 * 100;
			if (decimalPlaces == 1) {
				l += l2 * 10;
			} else {
				l += l2;
			}
		} else {
			l = Long.parseLong(s) * 100;
		}
		return Math.abs(l) * negative;
	}
        
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CTFSMoney)) {
			throw new RuntimeException("Not a CTFSMoney o=" + o);
		}		
		CTFSMoney bean = (CTFSMoney)o;
		boolean rv = true;
		
		//if (money.equals(bean.money)) {
		if (money != bean.money) {
			rv = false;
		}		
		
		return rv;
	}
	
	
	@Override
    public int hashCode() {
           return Objects.hashCode(money);
    }
	
	@Override
	public String toString() {
		return "<CTFSMoney><money>" + money + "</money></CTFSMoney>\n";
	}

    
}
