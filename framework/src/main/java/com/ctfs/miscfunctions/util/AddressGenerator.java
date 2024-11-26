package com.ctfs.miscfunctions.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ctfs.framework.Address;
import com.springbootjdbc.com.spring.jdbc.enums.Province;
import com.springbootjdbc.com.spring.jdbc.profile.MiscFunctions;
@Component
public class AddressGenerator {
	
	private static Logger log = Logger.getLogger(AddressGenerator.class);
	
	public AddressGenerator() {
		loadAddresses();
	}
	
	public Address getAddressForProvinceNotPostalCode(Province prov, String notPostalCode) {
		log.info("Entering prov=" + prov + " notPostalCode=" + notPostalCode);
		ArrayList<Address> al = new ArrayList<Address>();
		
		for (int i=0; i<addresses.length; i++) {
			Address a = addresses[i];
			log.info("==" + a.getProvince() + "==" + prov + "==" + a.getProvince().toString().equals(prov));
			log.info("==" + a.getPostalCode() + "==" + notPostalCode + "==" + a.getPostalCode().equals(notPostalCode));
			if (a.getProvince() == prov && 
				a.getPostalCode().equals(notPostalCode) == false && 
				a.getCountry().toString().equals("CA")) {
				al.add(a);	
			}
		}
		if (al.size() == 0) {
			throw new RuntimeException("AddressGenerator::GetAddressForProvinceNotPostalCode unable to find match");
		}
		
		Address addr = al.get(MiscFunctions.randomNumber(0, al.size() - 1));
		log.info("Returning " + addr);
		return addr;
	}
	
	public Address getAddressFromPostalCodeCity(String PostalCode, String city) {
		//SeleniumLogger logger = SeleniumLogger.getInstance();
		//logger.writeLognote("PostalCode=" + PostalCode + " City=" + city);
		ArrayList<Address> al = new ArrayList<Address>();
		
		for (int i=0; i<addresses.length; i++) {
			Address a = addresses[i];
			if (a.getPostalCode().equals(PostalCode) && 
				a.getCity().toString().equals(city)) {
				al.add(a);	
			}
		}
		if (al.size() == 0) {
			throw new RuntimeException("AddressGenerator::getAddressFromPostalCodeCity unable to find match");
		}
		
		Address addr = al.get(MiscFunctions.randomNumber(0, al.size() - 1));
		//logger.writeLognote("Returning " + addr);
		return addr;
	}
	
	public Address getAddressForCountry(String country) {
		//SeleniumLogger logger = SeleniumLogger.getInstance();
		//logger.writeLognote("Entering country=" + country);
		ArrayList<Address> al = new ArrayList<Address>();
		
		for (int i=0; i<addresses.length; i++) {
			Address a = addresses[i];
			//System.out.println("==" + a.getProvince() + "==" + prov + "==" + a.getProvince().toString().equals(prov));
			//System.out.println("==" + a.getPostalCode() + "==" + notPostalCode + "==" + a.getPostalCode().equals(notPostalCode));
			if (a.getCountry().toString().equals(country)) {
				al.add(a);	
			}
		}
		if (al.size() == 0) {
			throw new RuntimeException("AddressGenerator::getAddressForCountry unable to find match");
		}
		
		Address addr = al.get(MiscFunctions.randomNumber(0, al.size() - 1));
		//logger.writeLognote("Returning " + addr);
		return addr;
	}
	
	public Address getAddressNotForProvince(String prov) {
		//SeleniumLogger logger = SeleniumLogger.getInstance();
		//logger.writeLognote("Entering prov=" + prov);
		ArrayList<Address> al = new ArrayList<Address>();
		
		for (int i=0; i<addresses.length; i++) {
			Address a = addresses[i];
			//System.out.println("==" + a.getProvince() + "==" + prov + "==" + a.getProvince().toString().equals(prov));
			//System.out.println("==" + a.getPostalCode() + "==" + notPostalCode + "==" + a.getPostalCode().equals(notPostalCode));
			if (a.getProvince().toString().equals(prov) == false && a.getCountry().toString().equals("CA")) {
				al.add(a);	
			}
		}
		if (al.size() == 0) {
			throw new RuntimeException("AddressGenerator::getAddressNotForProvince unable to find match");
		}
		
		Address addr = al.get(MiscFunctions.randomNumber(0, al.size() - 1));
		//logger.writeLognote("Returning " + addr);
		return addr;
	}
	
	public Address getAddressNotForProvinces(List<Province> provList) {
		//SeleniumLogger logger = SeleniumLogger.getInstance();
		//logger.writeLognote("Entering prov=" + prov);
		ArrayList<Address> al = new ArrayList<Address>();
		
		for (int i=0; i<addresses.length; i++) {
			Address a = addresses[i];
			
			if (provList.contains(a.getProvince()) == false && a.getCountry().toString().equals("CA")) {
				al.add(a);
			}
			
		}
		if (al.size() == 0) {
			throw new RuntimeException("AddressGenerator::getAddressNotForProvince unable to find match");
		}
		
		Address addr = al.get(MiscFunctions.randomNumber(0, al.size() - 1));
		//logger.writeLognote("Returning " + addr);
		return addr;
	}
	
	public Address getAddressForPartialPostalCode(String postalCode) {
		//SeleniumLogger logger = SeleniumLogger.getInstance();
		System.out.println("Entering postalCode=" + postalCode);
		log.info("Entering postalCode=" + postalCode);
		ArrayList<Address> al = new ArrayList<Address>();
		
		for (int i=0; i<addresses.length; i++) {
			Address a = addresses[i];
			//System.out.println("==" + a.getProvince() + "==" + prov + "==" + a.getProvince().toString().equals(prov));
			//System.out.println("==" + a.getPostalCode() + "==" + notPostalCode + "==" + a.getPostalCode().equals(notPostalCode));
			if (a.getPostalCode().toString().contains(postalCode) == true) {
				al.add(a);	
			}
		}
		if (al.size() == 0) {
			throw new RuntimeException("AddressGenerator::getAddressForPostalCode unable to find match");
		}
		
		Address addr = al.get(MiscFunctions.randomNumber(0, al.size() - 1));
		//logger.writeLognote("Returning " + addr);
		return addr;
	}
	
	// random Canadian address
	public Address getRandomAddress() {
		//SeleniumLogger logger = SeleniumLogger.getInstance();
		//logger.writeLognote("Entering");
		ArrayList<Address> al = new ArrayList<Address>();
		
		for (int i=0; i<addresses.length; i++) {
			Address a = addresses[i];
			if (a.getCountry().toString().equals("CA")) {
				al.add(a);	
			}
		}
		if (al.size() == 0) {
			throw new RuntimeException("Unable to find match");
		}
		
		Address addr = al.get(MiscFunctions.randomNumber(0, al.size() - 1));
		//logger.writeLognote("Returning " + addr);
		return addr;
	}
	
	public Address getRandomAdress(Address notAdd) {
		Address addr = null;
		
		for (int i=0; i<1000; i++) {
			Address a = getRandomAddress();
			if (a.getAddressLine1().equalsIgnoreCase(notAdd.getAddressLine1()) &&
				a.getPostalCode().equalsIgnoreCase(notAdd.getPostalCode())) {
				continue;
			}
			addr = a;
			break;
		}
		return addr;
		
	}
	
	public static void main(String [] args) {
		AddressGenerator ag = new AddressGenerator();
			
		Address address1 = ag.getRandomAddress();
		Address address2 = ag.getRandomAdress(address1);
		System.out.println("Add is" + address1);
		System.out.println("New Add is" + address2);
	
	
	}
	
	public Address [] addresses = null;
	private void loadAddresses() {
		addresses = new Address[] {
			new Address("","917","MASON ST","VICTORIA",Province.BC,"V8T1A1","CA"),
			new Address("","1234","ACTON ST","VICTORIA",Province.BC,"V8T1Y2","CA"),
			new Address("","3120",	"IVANHOE ST","VANCOUVER",Province.BC,	"V5R4W1",	"CA"),
			new Address("","5505",	"OBEN ST",	"VANCOUVER",Province.BC,	"V5R4P2",	"CA"),
			new Address("","250025","MUNRO RD",	"CALGARY",Province.AB,	"T3Z1N1",	"CA"),
			new Address("","3","SOUTH VALLEY BLVD",	"CALGARY",Province.AB,	"T3R1H8",	"CA"),
			new Address("","6","ALGONQUIN PL W",	"LETHBRIDGE",Province.AB,	"T1K5H2",	"CA"),
			new Address("","55","RIVERGREEN RD W",	"LETHBRIDGE",Province.AB,	"T1K7Y1",	"CA"),
			new Address("","123","DORE CRES",	"SASKATOON",Province.SK,	"S7K4X6",	"CA"),
			new Address("","103",	"MENDEL CRES",	"SASKATOON",Province.SK,	"S7J5J7",	"CA"),
			new Address("","11",	"CONLIN DR",	"SWIFT CURRENT",Province.SK,	"S9H3A7",	"CA"),
			new Address("","541",	"YATES ST",	"SWIFT CURRENT",Province.SK,	"S9H1W5","CA"),
			new Address("","209",	"DICKEY CRES",	"SASKATOON",Province.SK,	"S7L5N8","CA"),
			new Address("","245",	"DICKEY CRES",	"SASKATOON",Province.SK,	"S7L5N8","CA"),
			new Address("","1520",	"CHARLES ST",	"BRANDON",Province.MB,	"R7C1A4",	"CA"),
			new Address("","202",	"PACIFIC AVE",	"BRANDON",Province.MB,	"R7A0H4",	"CA"),
			new Address("","201",	"MANITOBA AVE",	"SELKIRK",Province.MB,	"R1A0Y4",	"CA"),
			new Address("","225",	"MANITOBA AVE",	"SELKIRK",Province.MB,	"R1A0Y4",	"CA"),
			new Address("","25",	"MAIN ST",	"TORONTO",Province.ON,	"M4E2V5",	"CA"),
			new Address("","309",	"BAY ST",	"TORONTO",Province.ON,	"M5H4G4",	"CA"),	
			new Address("","309",	"BAY ST",	"TORONTO",Province.ON,	"M5H4G4",	"CA"),
			new Address("","203",	"ALBERT AVE",	"NORTH BAY",Province.ON,	"P1B7J6",	"CA"),
			new Address("","821",	"BOURKE ST",	"NORTH BAY",Province.ON,	"P1B3K5",	"CA"),
			new Address("","438",	"AUTUMN CRES",	"WELLAND",Province.ON,	"L3C7J9",	"CA"),
			new Address("","85",	"ACADIA DR",	"WELLAND",Province.ON,	"L3C6T5",	"CA"),
			new Address("","1000",	"EAST MAIN ST",	"WELLAND",Province.ON,	"L3B3Z3",	"CA"),
			new Address("","990",	"EAST MAIN ST",	"WELLAND",Province.ON,	"L3B3Z3",	"CA"),
			new Address("2","6625",	"RUE RIVIERE",	"LAVAL",Province.QC,	"H7H1B6",	"CA"),
			new Address("","4355",	"AV ELIOT",	"LAVAL",Province.QC,	"H7W5L5",	"CA"),
			new Address("","11",	"RUE HENEKER",	"SHERBROOKE",Province.QC,	"J1J3G2",	"CA"),
			new Address("","105",	"RUE WYATT",	"SHERBROOKE",Province.QC,	"J1J2V7",	"CA"),
			new Address("","25",	"HASTINGS ST",	"MONCTON",Province.NB,	"E1C3Z9",	"CA"),
			new Address("","1",	"ACORN CRT",	"MONCTON",Province.NB,	"E1G4X9","CA"),
			new Address("","15",	"ALMA ST",	"YARMOUTH",Province.NS,	"B5A3E9",	"CA"),
			new Address("","4",	"PUBLIC LANE",	"YARMOUTH",Province.NS,	"B5A3A1",	"CA"),
			new Address("","9",	"BRUNSWICK ST",	"YARMOUTH",Province.NS,	"B5A2E2",	"CA"),
			new Address("","15",	"BRUNSWICK ST",	"YARMOUTH",Province.NS,	"B5A2E2",	"CA"),
			new Address("","3",	"HEATHER ST",	"HALIFAX",Province.NS,	"B3R1M3",	"CA"),
			new Address("","5834",	"MACLEOD DR",	"HALIFAX",Province.NS,	"B3H1C7",	"CA"),
			new Address("","4",	"ADMIRAL ST",	"CHARLOTTETOWN",Province.PE,	"C1A2C2",	"CA"),
			new Address("","110",	"RUFUS ST",	"SUMMERSIDE",Province.PE,	"C1N3X7",	"CA"),
			new Address("","125",	"CRAIG AVE",	"SUMMERSIDE",Province.PE,	"C1N4Y5",	"CA"),
			new Address("","4",	"HEMLOCK AVE",	"CORNER BROOK",Province.NL,	"A2H3C8",	"CA"),
			new Address("","1",	"WOODROW AVE",	"CORNER BROOK",Province.NL,	"A2H7V6",	"CA"),
			new Address("","2",	"BALBO ST",	"GANDER",Province.NL,	"A1V1K2",	"CA"),
			new Address("","27",	"MEDCALF ST",	"GANDER",Province.NL,	"A1V1R9",	"CA"),
			new Address("","100",	"BECK CRT",	"YELLOWKNIFE",Province.NT,	"X1A3Y5",	"CA"),
			new Address("","521",	"CATALINA DR",	"YELLOWKNIFE",Province.NT,	"X1A2X2",	"CA"),
			new Address("","1501",	"ALDER ST",	"WHITEHORSE",Province.YT,	"Y1A3W7",	"CA"),
			new Address("","55",	"WANN RD",	"WHITEHORSE",Province.YT,	"Y1A5X4","CA"),
			new Address("","",	"PO BOX 417",	"DOKKI - GIZA",Province.EG,	"EG",	"EG")
		};
	}

}
