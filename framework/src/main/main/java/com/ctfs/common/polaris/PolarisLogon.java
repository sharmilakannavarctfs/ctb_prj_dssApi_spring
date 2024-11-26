package com.ctfs.common.polaris;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctfs.common.externalutils.ExternalDriverManager;

public class PolarisLogon  {
	
	@Autowired
	private ExternalDriverManager driverManager;
	
	private String uid;
	private String pwd;
	
	private final Logger log = LoggerFactory.getLogger(PolarisLogon.class);
	private String url = "https://polaris-uat2.ctc.epsilon.com/Security/Login.aspx";
	
	public PolarisLogon() {}
	
	public PolarisLogon(String uid, String pwd) {
//		this.driver = driver;
		this.uid = uid;
		this.pwd = pwd;
	}
	
	public void load() {
		driverManager.getExternalWebDriver().get(url);
	}
	
	public void logon() {
		log.info("Entering uid=" + uid + " pwd=" + pwd);
		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_txtUserID']"));
		element.sendKeys(uid);
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_txtPassword']"));
		element.sendKeys(pwd);
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@type='submit'][@id='ctl00_c2_btn_Login']"));
		element.click();
		log.info("logon");
		
//		element = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit'][@id='continueButton']"))) ;
//		if (element == null) {
//			log.info("Unable to find continue button, will proceed anyway.");
//		} else {
//			element.click();
//		} 
		
		log.info("Exiting");
		//element = driver.findElement(By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLoyaltyCardNum_text']"));
		
	}

	
}
