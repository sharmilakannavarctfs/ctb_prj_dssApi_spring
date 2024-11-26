package com.ctfs.common.polaris;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctfs.common.externalutils.ExternalDriverManager;


public class PolarisMemberMain {
	
	protected Logger log = LoggerFactory.getLogger(PolarisMemberMain.class);
	
	@Autowired
	private ExternalDriverManager driverManager;
	
	public PolarisMemberMain() {
	}
	
	
	public MemberMainDTO scrape() {
		MemberMainDTO mmdo = new MemberMainDTO();
		log.info("Entering scrape");
		
		scrapeBanner(mmdo);
		
		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//span[@id='ctl00_c2_accountInfo_frmViewAccountInformation_lblAccountType']"));
		mmdo.setMainAccountType(element.getText());
		
		element = driverManager.getExternalWebDriver().findElement(By.id("memberCardNumber"));
		mmdo.setLoyaltyNumber(element.getText());
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_accountInfo_frmViewAccountInformation_cmbAccountStatus_Input']"));
		mmdo.setMainAccountStatus(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_txtAddressLine1']"));
		mmdo.setMainAddressLine1(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_txtAddressLine2']"));
		mmdo.setMainAddressLine2(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_txtAddressLine1']"));
		mmdo.setMainAddressLine1(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_txtCity']"));
		mmdo.setMainCity(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_cmbStates_Input']"));
		mmdo.setMainProv(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_txtZip']"));
		mmdo.setMainPostalCode(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_cmbCountry_Input']"));
		mmdo.setMainCntry(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressInfo_formViewAddressInformation_chkInvalidAddress']"));
		mmdo.setMainReturnedMail(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_cmbPrefix_Input']"));
		mmdo.setMainPrefixName(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_txtFirstName']"));
		mmdo.setMainFirstName(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_txtMidlleInit']"));
		mmdo.setMainMiddleInitial(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_txtLastName']"));
		mmdo.setMainLastName(element.getAttribute("value"));
		
		String male = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_rbtnMale']")).getAttribute("checked");
		String female = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_rbtnFemale']")).getAttribute("checked");
		if (male != null && male.equals("true")) {
			mmdo.setMainGender("MALE");
		} else if (female != null && female.equals("true")) {
			mmdo.setMainGender("FEMALE");
		} else {
			mmdo.setMainGender("");
		}
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_cmbDOBMonth_Input']"));
		mmdo.setMainDOBMonth(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_cmbDOBDate_Input']"));
		mmdo.setMainDOBDay(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_nameInfo_formViewNameInformation_cmbDOBYear_Input']"));
		mmdo.setMainDOBYear(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//span[@id='ctl00_c2_cardInfo_frmViewCardInformation_lblLastMaintDate']"));
		mmdo.setMainMaintDate(element.getText());
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_cardInfo_frmViewCardInformation_cmbRedeemer_Input']"));
		mmdo.setMainRedeemer(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.id("ctl00_c1_customerBanner_bannerFormView_lblStartday"));
		mmdo.setHdrActivateDate(element.getText());
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_phoneInfo_frmViewPhoneInfo_txtPrimaryPhone']"));
		mmdo.setMainPrimaryPhone(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_phoneInfo_frmViewPhoneInfo_cmbPrimaryPhoneType_Input']"));
		mmdo.setMainPrimaryPhoneType(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_phoneInfo_frmViewPhoneInfo_txtSecondaryPhone']"));
		mmdo.setMainAltPhone(element.getAttribute("value"));
		
		if (mmdo.getMainAltPhone().length() > 0) {
			element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_phoneInfo_frmViewPhoneInfo_cmbSecondaryPhoneType_Input']"));
			mmdo.setMainAltPhoneType(element.getAttribute("value"));
		} else {
			mmdo.setMainAltPhoneType("");
		}
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_emailInfo_frmViewEmailInfo_txtEmail']"));
		mmdo.setMainEmail(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_emailInfo_frmViewEmailInfo_chkInvalidEmail']"));
		String badEmail = element.getAttribute("checked");
		if (badEmail != null && badEmail.equals("true")) {
			mmdo.setMainBadEmail("TRUE");
		} else {
			mmdo.setMainBadEmail("FALSE");
		}
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_subscriptionInfo_subscriptionsGrid_ctl00_ctl04_RadOptStatus_Input']"));
		mmdo.setMainCommPrefEmail(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_subscriptionInfo_subscriptionsGrid_ctl00_ctl06_RadOptStatus_Input']"));
		mmdo.setMainCommPrefDirectMail(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_subscriptionInfo_subscriptionsGrid_ctl00_ctl08_RadOptStatus_Input']"));
		mmdo.setMainCommPrefPhone(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_subscriptionInfo_subscriptionsGrid_ctl00_ctl10_RadOptStatus_Input']"));
		mmdo.setMainCommPrefGI(element.getAttribute("value"));
		
		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_subscriptionInfo_cmbLanguage_Input']"));
		mmdo.setMainCommPrefLanguage(element.getAttribute("value"));
		
		// Scrape the Organization Info if present
		if (mmdo.getMainAccountType().equals("Community Member") || mmdo.getMainAccountType().equals("Charity Member")) {
			element = driverManager.getExternalWebDriver().findElement(By.xpath("//span[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_lblAccountType']"));
			mmdo.setOrgInfoAccountType(element.getText());
			
			element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtOrgName']"));
			mmdo.setOrgInfoOrgName(element.getAttribute("value"));
			
			element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtBusinessNumber']"));
			mmdo.setOrgInfoBusinessNumber(element.getAttribute("value"));
			
			element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_cmbOrgType_Input']"));
			mmdo.setOrgInfoTypeOfOrganization(element.getAttribute("value"));
			
			element = driverManager.getExternalWebDriver().findElement(By.xpath("//textarea[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtOrgPurpose']"));
			mmdo.setOrgInfoPurposeOfOrganization(element.getAttribute("value"));
		}
		
		log.info("Exiting " + mmdo);
		
		return mmdo;
	}
	
	public MemberMainDTO scrapeBanner(MemberMainDTO mmdo) {
		if (mmdo == null) {
			mmdo = new MemberMainDTO();
		}
		WebElement base = new WebDriverWait(driverManager.getExternalWebDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ctl00_c1_customerBanner_bannerFormView']"))) ;
		if (base == null) {
			log.info("Unable to find MemberMain element, checking for No Loyalty Card Number found");
			driverManager.getExternalWebDriver().findElement(By.xpath("//span[text()='No Loyalty Card Number Found']"));
			// If not found will throw an exception
			// Else, must be on No Loyalty Card Number Found screen
			return null;
		}
		WebElement accountStatus = base.findElement(By.xpath(".//td[contains(text(), 'Account Status:')]"));
		accountStatus = accountStatus.findElement(By.xpath(".//following-sibling::td"));
		mmdo.setHdrAccountStatus(accountStatus.getText());
		
		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//span[@id='ctl00_c1_customerBanner_bannerFormView_lblCurrentDollarBalance']"));
		mmdo.setHdrCurrentValue(element.getText().replaceAll(",", ""));
		
//		element = driverManager.getExternalWebDriver().findElement(By.xpath("//span[@id='ctl00_c1_customerBanner_bannerFormView_lbLtotal']"));
//		mmdo.setHdrTotalLifeValue(element.getText().replaceAll(",", ""));
//		
//		element = base.findElement(By.xpath(".//td[contains(text(), 'Relationship Status:')]"));
//		element = element.findElement(By.xpath(".//following-sibling::td"));
//		mmdo.setHdrRelationshipStatus(element.getText());
//		
		WebElement HdrLinkedCreditCard = base.findElement(By.xpath(".//td[contains(text(), 'CTFS Linked Credit Card:')]"));
		HdrLinkedCreditCard = HdrLinkedCreditCard.findElement(By.xpath(".//following-sibling::td"));
		mmdo.setHdrLinkedCreditCard(HdrLinkedCreditCard.getText());
//		
//		element = base.findElement(By.xpath(".//td[contains(text(), 'Redeemer:')]"));
//		element = element.findElement(By.xpath(".//following-sibling::td"));
//		mmdo.setHdrRedeemer(element.getText());
		
//		element = driverManager.getExternalWebDriver().findElement(By.id("ctl00_c1_customerBanner_frmBanner"));
//		System.out.println("BANNER text=" + element.getText());
//		System.out.println("BANNER html=" + element.getAttribute("innerHTML"));
//		
//		String [] lines = element.getText().split("\\r?\\n");
//		System.out.println("lines.length=" + lines.length);
//		for (String line : lines) {
//			System.out.println("line=" + line);
//		}
		
		return mmdo;
	}

	
	public boolean selectSubMenu(String subMenu) throws Exception
	{
		boolean bStatus=false;
		ArrayList<Boolean> bList=new ArrayList<>();

		try
		{

			Actions actions = new Actions(driverManager.getExternalWebDriver());

			String subMenuPath_xpath="//span[contains(text(),'"+subMenu+"')]";
			log.info("subMenuPath_xpath---->"+subMenuPath_xpath);

			WebElement polarisSubMenuTab=driverManager.getExternalWebDriver().findElement(By.xpath(subMenuPath_xpath));
			actions.moveToElement(polarisSubMenuTab).click().perform();
			Thread.sleep(2000);
			String selectedSubMenu=driverManager.getExternalWebDriver().findElement(By.xpath("//div[@id='pageTitle']")).getText();
			log.info("selectedSubMenu-->"+selectedSubMenu);
			log.info("SubMenu-->"+subMenu);
			if(selectedSubMenu.contains(subMenu)) {
				bStatus=true;
				log.info("if cond check contains--->"+bStatus);
			}
			else {
				bStatus=false;
				log.info("if cond check contains--->"+bStatus);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
		return bStatus;
	}

		


}
