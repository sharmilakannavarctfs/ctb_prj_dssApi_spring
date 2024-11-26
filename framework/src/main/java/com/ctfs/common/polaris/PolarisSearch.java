package com.ctfs.common.polaris;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.common.externalutils.ExternalDriverHelper;
import com.ctfs.common.externalutils.ExternalDriverManager;

public class PolarisSearch  {
	
	@Autowired
	private ExternalDriverManager driverManager;
	
	@Autowired
	private ExternalDriverHelper driverHelper;
	
	
	
	private String cardNumber;
	protected Logger log = LoggerFactory.getLogger(PolarisSearch.class);
	
	public PolarisSearch() {
//		this.driver = driver;
	}
	
	public void searchByCardNumber(String cn) throws InterruptedException {
		this.cardNumber = cn.trim();
		log.info("Entering cardNumber=" + cardNumber);
		WebElement element = null;
		
		// The following loop is a major hack
		// The account number field is seriously flakey.
		// Sometimes it will always lose the first digit
		// Othertimes it won't
		// So loop a few times and add an additional digit to the beginning in the hope that the correct number
		// will eventually be entered.
		for (int i=0; i<10; i++) {
			element = new WebDriverWait(driverManager.getExternalWebDriver(), 15).until(
					ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLoyaltyCardNum']")));
//			element = driver.findElement(By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLoyaltyCardNum']"));
			element.clear();
			element.click();
			for (int j=0; j<16; j++) {
				element.sendKeys(Keys.ARROW_LEFT);
				Thread.sleep(100);
			}
			element.sendKeys(cardNumber);
			Thread.sleep(500);
			if (driverHelper.stripNonNumeric(element.getAttribute("value")).equals(cardNumber)) {
				log.info("Entering loyalty number, breaking");
				break;
			}
			log.info("Loyalty number not entered correctly");
			Thread.sleep(500);
		}
		
		//element.sendKeys(cardNumber.substring(6));
		//SeleniumWrapper wrapper = new SeleniumWrapper(driver);
		//wrapper.enterInputValueOneCharAtTime(By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLoyaltyCardNum']"), cardNumber);
		//wrapper.enterInputValue(By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLoyaltyCardNum']"), cardNumber);
		//element.clear();
		//element.sendKeys(cardNumber);
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@type='submit'][@id='ctl00_c2_profilesearch_frmViewProfileSearch_btnSearch']"));
		log.info("About to click search");
		System.out.println("About to click search");
		element.click();
		log.info("Exiting");
	}
	
	public void searchNoCardFound(String cn) throws InterruptedException {
		this.cardNumber = cn.trim();
		log.info("Entering cardNumber=" + cardNumber);
		WebElement element = null;
		for (int i=0; i<10; i++) {
			element = new WebDriverWait(driverManager.getExternalWebDriver(), 15).until(
					ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLoyaltyCardNum']")));
			element.clear();
			element.click();
			for (int j=0; j<16; j++) {
				element.sendKeys(Keys.ARROW_LEFT);
				Thread.sleep(100);
			}
			element.sendKeys(cardNumber);
			Thread.sleep(500);
			if (driverHelper.stripNonNumeric(element.getAttribute("value")).equals(cardNumber)) {
				log.info("Entering loyalty number, breaking");
				break;
			}
			log.info("Loyalty number not entered correctly");
			Thread.sleep(500);
		}
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@type='submit'][@id='ctl00_c2_profilesearch_frmViewProfileSearch_btnSearch']"));
		log.info("About to click search");
		System.out.println("About to click search");
		element.click();
		try {
			element = driverManager.getExternalWebDriver().findElement(By.xpath("//span[@id='ctl00_c2_profilesearch_rgridSearchResults_ctl00_ctl04_NoRecordsLabel']"));
			Assert.assertTrue(element.isDisplayed());
			System.out.println(" No card found");
		}catch(Exception e) {
			System.out.println("Card Match Found \n");
			throw e;
		}
	}
	
	public void searchByCardName(String firstName, String lastName) {
		log.info("Entering firstName=" + firstName + " lastName=" + lastName);
		
		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLoyaltyCardNum']"));
		element.click();
		element.sendKeys("                ");
		
		driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtLastName']")).sendKeys(lastName);
		driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_profilesearch_frmViewProfileSearch_txtFirstName']")).sendKeys(firstName);
			
		log.info("About to click search");
		driverManager.getExternalWebDriver().findElement(By.xpath("//input[@type='submit'][@id='ctl00_c2_profilesearch_frmViewProfileSearch_btnSearch']")).click();
		
		log.info("Exiting");
	}

}
