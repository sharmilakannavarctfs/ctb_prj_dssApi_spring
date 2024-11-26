package com.ctfs.framework;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctfs.tsys.utility.Province;
import com.ctfs.tsys.utility.ProvinceFunctions;

public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -565193271703113432L;

	private static Logger log = LoggerFactory.getLogger(Address.class);
	 
	private String unitNumber;
	private String streetNumber;
	private String streetName;
	private String addrLine2 = "";
	private String city;
	private Province province;
	private String postalCode;
	private String country;
	//private HashMap<String,String[]> provinceMappings;
	private ProvinceFunctions provinceFunctions = new ProvinceFunctions();
	
	/*public String toString() {
		return BeanFunctions.serializeBeanForDisplay(this);
	}*/
	
	public String formatForCSGHistory() {
		return getAddressLine1() +", " + getCity() + ", " + getProvince().toString() + ", " + getCountry() + ", " + getPostalCode();
	}
	
	public Address() {
		unitNumber = "";
		streetNumber = "";
		streetName = "";
		addrLine2 = "";
		city = "";
		province = Province.BLANK;
		postalCode = "";
		country = "";
	}
	
	public Address(Address a) {
		this.unitNumber = a.unitNumber;
		this.streetNumber = a.streetNumber;
		this.streetName = a.streetName;
		this.addrLine2 = a.addrLine2;
		this.city = a.city;
		this.province = a.province;
		this.postalCode = a.postalCode;
		this.country = a.country;
	}
	
	public Address(String addr, String format) {
		if (format.equals("addrLine1,city province postalCode country")) {
			Pattern pattern = Pattern.compile("(.*),(.*) {1,10}([A-Z]{2}) {1,10}([A-Z]{1}[0-9]{1}[A-Z]{1} {0,1}[0-9]{1}[A-Z]{1}[0-9]{1}) {1,10}([A-Z]{2})");
			Matcher matcher = pattern.matcher(addr);
			matcher.find();
			// assume addr1 is number space street
			String addrLine1 = matcher.group(1);
			int index = addrLine1.indexOf(" ");
			this.streetNumber = addrLine1.substring(0, index).trim();
			this.streetName = addrLine1.substring(index).trim();
			this.city = matcher.group(2).trim();
			this.province =  provinceFunctions.parse(matcher.group(3));
			this.postalCode = matcher.group(4).trim();
			this.country = matcher.group(5).trim();
			
			matcher.group(1);
		} else {
			throw new RuntimeException("Unsupported format=" + format);
		}
	}
	
	public Address(String unitNumber, String streetNumber, String streetName, String city, Province province, String postalCode, String country) {
		//loadProvinces();
		this.unitNumber = unitNumber;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	/*public Address(AddressMultiDayDTO bean) {
		this.unitNumber = bean.getUnitNumber();
		this.streetNumber = bean.getStreetNumber();
		this.streetName = bean.getStreetName();
		this.city = bean.getCity();
		this.province = bean.getProvince();
		this.postalCode = bean.getPostalCode();
		this.country = bean.getCountry();
	}*/
	
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber.trim();
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setAddrLine1(String addrLine1) {
		System.out.println("addrLine1=" + addrLine1);
		if (addrLine1.trim().length() > 0) {
			int index = addrLine1.indexOf(" ");
			if (index >=0) {
				setStreetNumber(addrLine1.substring(0, index).trim());
				streetName = addrLine1.substring(index + 1).trim();
			} else {
				streetNumber="";
				streetName = addrLine1;
			}
		} else {
			streetNumber = "";
			streetName = "";
		}
	}
	private static Pattern cityProvPostalPattern = Pattern.compile("(.*) {1,20}([A-Z]{2}) {1,20}([A-Za-z]{1}[0-9]{1}[A-Za-z]{1} {0,1}[0-9]{1}[A-Za-z]{1}{0,1}[0-9]{1})");
	public void setCityProvPostal(String s) {
		Matcher match = cityProvPostalPattern.matcher(s);
		if (match.find() == true) {
			setCity(match.group(1));
			setProvince(match.group(2));
			setPostalCode(match.group(3));
		} else {
			System.out.println("NO MATCH");
		}
	}
	public void setStreetNumber(String streetNumber) {
		streetNumber = streetNumber.trim();
		int index = streetNumber.indexOf("-");
		if (index > 0) {
			this.unitNumber = streetNumber.substring(0,index);
			this.streetNumber = streetNumber.substring(index + 1);
		} else {
			this.streetNumber = streetNumber.trim();
		}
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName.trim();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.trim();
	}
	public Province getProvince() {
		return province;
	}
//	public Province getProvince_French() {
//		return provinceMappings.get(province)[2];
//	}
//	public Province getProvince_English() {
//		return provinceMappings.get(province)[1];
//	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public void setProvince(String prov) {
		this.province = provinceFunctions.parse(prov.trim());
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode.replaceAll(" ", "");
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country.trim();
	}
	
	public String getAddressLine1() {
		StringBuffer sb = new StringBuffer();
		
		if (unitNumber != null && unitNumber.length() > 0) {
			sb.append(unitNumber + "-");
		}
		
		if (streetNumber.length() > 0) {
			sb.append(streetNumber + " " + streetName);
		} else {
			sb.append(streetName);
		}
		
		return sb.toString();
	}
	
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}
	
	public String getAddrLine2() {
		return addrLine2;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Address)) {
			throw new RuntimeException("Not a Address o=" + o);
		}
		Address addr = (Address)o;
		boolean rv = true;
		
		if (fixValue(unitNumber).equals(fixValue(addr.unitNumber)) == false) {
			log.info("unitNumber not equal un1=" + unitNumber + " un2=" + addr.unitNumber);
			rv = false;
		}
		if (fixValue(streetNumber).equals(fixValue(addr.streetNumber)) == false) {
			log.info("streetNumber not equal sn1=" + streetNumber + " streetNumber" + addr.streetName);
			rv = false;
		}
		if (fixValue(streetName).equals(fixValue(addr.streetName)) == false) {
			log.info("streetName not equal");
			rv = false;
		}
		if (fixValue(addrLine2).equals(fixValue(addr.addrLine2)) == false) {
			log.info("addrLine2 not equal");
			rv = false;
		}
		if (fixValue(city).equals(fixValue(addr.city)) == false) {
			log.info("city not equal");
			rv = false;
		}
		if (fixValue(province).equals(fixValue(addr.province)) == false) {
			log.info("province not equal");
			rv = false;
		}
		if (fixValue(postalCode).equals(fixValue(addr.postalCode)) == false) {
			log.info("postalCode not equal");
			rv = false;
		}
//		if ( !(fixValue(addr.country).equals("CA") && fixValue(country).equals("") ||
//			   fixValue(addr.country).equals("") && fixValue(country).equals("CA") ||
//			   fixValue(addr.country).equals(fixValue(country)))) {
//			rv = false;
//		}
		
		String country1 = fixValue(addr.country);
		String country2 = fixValue(country);
		if (country1.equals("") || country1.equals("CANADA")) {
			log.info("Updating country1 from " + country1 + " to CA");
			country1 = "CA";
		}
		if (country2.equals("") || country2.equals("CANADA")) {
			log.info("Updating country2 from " + country2 + " to CA");
			country2 = "CA";
		}
		if (country1.equals(country2) == false) {
			log.info("country not equal");
			rv = false;
		}
		
		return rv;
	}
	
	// Just used for equal method
	private String fixValue(String val) {
		if (val == null || val.trim().length() <= 0) {
			return "";
		}
		return val.trim().replace("  ", " ").toUpperCase();
	}
	private String fixValue(Province val) {
		if (val == null) {
			return "";
		}
		return val.toString();
	}
	
	public static void main(String [] args) {
		Address a1 = new Address();
		Address a2 = new Address();
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "CA";
		a2.country = "";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "CA";
		a2.country = "CA";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "";
		a2.country = "CA";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "US";
		a2.country = "US";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "US";
		a2.country = "CA";
		System.out.println("Expect false="+ a1.equals(a2));
		
		a1.country = "CA";
		a2.country = "US";
		System.out.println("Expect false="+ a1.equals(a2));
		
		a1.country = "";
		a2.country = "US";
		System.out.println("Expect false="+ a1.equals(a2));
		
		a1.country = "US";
		a2.country = "";
		System.out.println("Expect false="+ a1.equals(a2));
		
		a1.country = "US";
		a2.country = "CANADA";
		System.out.println("Expect false="+ a1.equals(a2));
		
		a1.country = "";
		a2.country = "CANADA";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "";
		a2.country = "CA";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "CANADA";
		a2.country = "";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "CA";
		a2.country = "";
		System.out.println("Expect true="+ a1.equals(a2));
		
		a1.country = "CANADA";
		a2.country = "CANADA";
		System.out.println("Expect true="+ a1.equals(a2));
		
		String addr = "309 BAY ST,TORONTO      ON      M5H4G4     CANADA";
		a1 = new Address(addr, "addrLine1,city province postalCode country");
		System.out.println("addr=" + a1);
	}
		
}

