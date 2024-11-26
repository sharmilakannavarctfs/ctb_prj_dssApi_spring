package com.ctfs.common.email;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctfs.framework.TestLanguage;
import com.ctfs.framework.ValidationException;
import com.ctfs.ui.utils.DriverHelper;
import com.ctfs.ui.utils.DriverManager;

public abstract class ValidateGmailFormat {
	
	
//	protected static Logger log = Logger.getLogger(ValidateGmailFormat.class);
	private final Logger log = LoggerFactory.getLogger(DriverHelper.class);
	protected GmailMessageBean bean = null;
	public String language = "";
	protected String lastFour;
	protected String email = "";
	protected String [] subjects = null;
	String pan = null;
		
	public String getLastFour() {
		return lastFour;
	}

	private void setLastFour(String lastFour) {
		if (lastFour.length() > 4) {
			lastFour = lastFour.substring(lastFour.length()-4);
		}
		this.lastFour = lastFour;
	}
	
	private int emailTimeoutMinutes = 15;
	public void setEmailTimeoutMinutes(int minutes) {
		emailTimeoutMinutes = minutes;
	}
	
	public ValidateGmailFormat(String email, String language, String pan) {
		this(email, language, pan, null);
	}
	
	public ValidateGmailFormat(String email, String language, String pan, String [] subjects) {
		System.out.println("ValidateGmailFormat language=" + language);
		TestLanguage.setLanguage(language);
		this.language = language.toUpperCase();
		this.email = email.toUpperCase();
		this.subjects = subjects;
		this.pan = pan;
//		this.driverManager = new DriverManager();
	}
	
	public void validate() throws GmailValidationException, InterruptedException {
		WebDriver driver = null;
		log.info("Entering ValidateGmailFormat::validate email=" + email + " language=" + language + " pan=" + pan);
		if (subjects != null) {
			log.info("subjects.length=" + subjects.length);
			for (int i=0; i<subjects.length; i++) {
				log.info("subjects[" + i + "]=" + subjects[i]);
			}
		}
		setLastFour(pan);
		
		//get last email
		_GmailAccess gmail = new _GmailAccess();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1 * Math.abs(emailTimeoutMinutes));
		gmail.setCufoffTimes(cal.getTime(), null);
		Thread.sleep(5000); // Give system a bit of time to send email
		int retries = 5;
		for (int i=0; i<retries; i++) {
			bean = gmail.getLastMessage(email, subjects);
			if (bean != null) {
				log.info("Found matching email, breaking");
				break;
			}
			if (i<(retries - 1)) {
				log.info("No message yet, will sleep for 15 seconds and retry");
				Thread.sleep(15000);
			}
		}
		
		ChromeOptions options = new ChromeOptions();
		
		String [] arguments = new String [] {
				"disable-popup-blocking",
				"start-maximized"
			};
		options.addArguments(arguments);
		System.out.println("DEBUG 3");
		Map<String, Object> prefs = new HashMap<String, Object>(); 
		prefs.put("safebrowsing.enabled", "true");
		options.setExperimentalOption("prefs", prefs);
				
		BufferedWriter bw = null;
				
		File temp = null;
		try {
			temp = File.createTempFile("tempFile", ".html"); 
			String absolutePath = temp.getAbsolutePath();
			System.out.println("Path=" + absolutePath);
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.UTF_8));
			bw.write(bean.getContent());
			bw.close();
			
			driver = new ChromeDriver(options);
			driver.get(absolutePath);
			
			//validate
			validateGmail();					
		} catch (Exception e) {
			log.error("Caught exception:", e);
			throw new RuntimeException(e);
		} finally {
			if (driver != null) {
				driver.quit();
			}
//			if (temp != null) {
//				temp.delete();
//			}
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
		
	protected abstract void validateGmail() throws GmailValidationException, ValidationException;
	
	static {
		if (System.getProperty("webdriver.chrome.driver") == null) {
			ManageDriverExecutable.setChromeDriverExecutableFromLocalDirectoryMostRecent(new File("C:/MyDocuments/browserDriver/"));
		}
	}


}
