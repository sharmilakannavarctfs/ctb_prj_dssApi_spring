package com.ctfs.common.email;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageDriverExecutable {
	public static void setChromeDriverExecutableFromLocalDirectoryMostRecent(File dir) {
		File [] files = dir.listFiles();
        Pattern pat = Pattern.compile("chromedriver_([0-9]{2,3}).exe");
        int maxVersion = -1;
        File maxDriver = null;
        for (File file : files) {
        	//System.out.println(file.getName());
        	Matcher match = pat.matcher(file.getName());
        	if (match.find()) {
        		int version = Integer.parseInt(match.group(1));
        		if (version > maxVersion) {
        			maxDriver = file;
        		}
        	}
        }
    	
    	System.setProperty("webdriver.chrome.driver", maxDriver.getAbsolutePath());
	}
	
	// Test comment
	public static void main(String [] args) {
		System.out.println("Hello world driver=" + System.getProperty("webdriver.chrome.driver"));
		setChromeDriverExecutableFromLocalDirectoryMostRecent(new File("C:/MyDocuments/browserDriver/"));
		System.out.println("Hello world driver=" + System.getProperty("webdriver.chrome.driver"));
	}
}
