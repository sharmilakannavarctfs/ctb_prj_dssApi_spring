package com.ctfs.common.polaris;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctfs.common.externalutils.ExternalDriverManager;


public class PolarisMemberMain {
//	private WebDriver driver;
	protected Logger log = LoggerFactory.getLogger(PolarisMemberMain.class);
	
	@Autowired
	private ExternalDriverManager driverManager;
	
	public PolarisMemberMain() {
//		this.driver = driver;
	}
	
//	@SuppressWarnings("unused")
//	public void gotoPage(ValidPages page) {
//		logger.writeLognote("Entering page=" + page, driver);
//		if (page == ValidPages.CASES) {
//			WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//a[contains(@href, 'Profile.aspx')]"));
//			System.out.println("Hovering over " + element.getAttribute("href"));
//			if (true) throw new RuntimeException("Removed with new selenium version");
//	//		WebDriverBackedSelenium selenium = new WebDriverBackedSelenium(driver, driverManager.getExternalWebDriver().getCurrentUrl());
//	//		selenium.fireEvent("//a[contains(@href, 'Profile.aspx')]", "mouseover");
////			
////			WebElement e2 = driverManager.getExternalWebDriver().findElement(By.xpath("//a[contains(@href, 'CaseManagementList.aspx')]"));
////			MiscFunctions.sleep(2000);
////			e2.click();
//		} else {
//			throw new RuntimeException("PolarisMemberMain::gotoPage unsupported page=" + page);
//		}
//		logger.writeLognote("Exiting", driver);
//	}
	
//	public void validateOrganizationInfo() {
//		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//div[@id='ctl00_c2_dvOrgInfo']/div"));
//		CheckPoint2.checkPointEquals(element.getText(), "Organization Information", "SubHeader", driver);
//		
//		OrganizationTypes [] orgTypes = OrganizationTypes.values();
//		WebElement arrow = driverManager.getExternalWebDriver().findElement(By.xpath("//a[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_cmbOrgType_Arrow']"));
//		arrow.click();
//		MiscFunctions.sleep(250);
//		
//		List<WebElement> elements = driverManager.getExternalWebDriver().findElements(By.xpath("//div[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_cmbOrgType_DropDown']/div/ul/li"));
//		CheckPoint2.checkPointEquals(elements.size(), orgTypes.length + 1, "Org Info - Type of Org Count", null);
//		if (elements.size() == orgTypes.length + 1) {
//			for (int i=0; i<orgTypes.length; i++) {
//				CheckPoint2.checkPointEquals(orgTypes[i].toString(), elements.get(i + 1).getText(), "Org Info - Type of Org", null);
//			}
//		}
//		
//	}
//	
//	public String scrapeAccountStatus() {
//		WebElement base = driverManager.getExternalWebDriver().findElement(By.xpath("//table[@id='ctl00_c1_customerBanner_bannerFormView']"));
//		WebElement element = base.findElement(By.xpath(".//td[contains(text(), 'Account Status:')]"));
//		element = element.findElement(By.xpath(".//following-sibling::td"));
//		return element.getText();
//	}
	
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

	
	
//	public void updateBusinessNumber(String bn) {
//		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtBusinessNumber_text']"));
//		element.clear();
//		element.sendKeys(bn);
//		
//		// shift focus to another edit to reset error astrick
//		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtOrgName_text']"));
//		element.click();
//	}
//	
//	public void updateEmail(String email) {
//		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_emailInfo_frmViewEmailInfo_txtEmail']"));
//		element.clear();
//		element.sendKeys(email);
//		
//		// Click on the email disclosure
//		acceptEmailDialog();
//		
//		// shift focus to another edit to reset error astrick
//		//element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_addressValidationWindow_C_txtOrigZip']"));
//		//element.click();
//	}
//	
//	public void acceptEmailDialog() {
//		MiscFunctions.sleep(500);
//		List<WebElement> elements = driverManager.getExternalWebDriver().findElements(By.xpath("//div[@id='dialog']"));
//		
//		logger.writeLognote("Entering", driver);
//		System.out.println("count=" + elements.size());
//		MiscFunctions.sleep(2000);
//		for (WebElement element : elements) {
//			System.out.println("element=" + element.getText());
//			System.out.println("style=" + element.getAttribute("style"));
//		}
//		MiscFunctions.sleep(2000);
//		//elements = driverManager.getExternalWebDriver().findElements(By.xpath(".//*"));
//		elements = driverManager.getExternalWebDriver().findElements(By.xpath(".//*[contains(@class, 'ui-button')][./text()='Yes']"));
//
//		System.out.println("count *=" + elements.size());
//		MiscFunctions.sleep(2000);
//		
//		for (WebElement element : elements) {
//			if (element.getText().equals("Yes") == true) {
//				System.out.println("||" + element.getText() + "||");
//				System.out.println(element.getAttribute("id"));
//				System.out.println(element.getAttribute("class"));
//				System.out.println(element.getAttribute("tag"));
//				element.click();
//				break;
//			}
//		}
//		
//		for (int i=0; i<1; i++) {
//			CheckPoint2.checkPointEquals( elements.get(i).getText(), "Account is not redeemable", "Error message", null);
//			WebElement element = elements.get(i).findElement(By.xpath(".//following-sibling::div/a"));
//			element.click();
//		}
//		if (elements.size() == 0) {
//			Errors.getErrorsObject().addError("Did not find acceptAccountNotRedeemableError message");
//		}
//	}
//	
//	public void updateTypeOfOrganization(OrganizationTypes type) {
//		WebElement arrow = driverManager.getExternalWebDriver().findElement(By.xpath("//a[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_cmbOrgType_Arrow']"));
//		arrow.click();
//		MiscFunctions.sleep(250);
//		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_cmbOrgType_Input']"));
//		
//		// Get current index
//		String currentType = element.getAttribute("value");
//		currentType = currentType.replace("&amp;", "&");
//		int currentIndex = MiscFunctions.getIndexInArray(OrganizationTypes.values(), currentType);
//		logger.writeLognote("currentIndex=" + currentIndex);
//		
//		// Get new index
//		int newIndex = -1;	// represents Select One
//		if (type != null) {
//			newIndex = MiscFunctions.getIndexInArray(OrganizationTypes.values(), type.toString());
//		}
//		logger.writeLognote("newIndex=" + newIndex);
//		
//		// Calc Difference and go up of down
//		int diff = newIndex - currentIndex;
//		if (diff < 0) {
//			for (int i=diff; i<0; i++) {
//				element.sendKeys(Keys.ARROW_UP);
//			}
//		} else {
//			for (int i=0; i<diff; i++) {
//				element.sendKeys(Keys.ARROW_DOWN);
//			}
//		}
//		arrow.click();
//		MiscFunctions.sleep(1000);
//		
//		// Click in business number to change focus
//		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtBusinessNumber_text']"));
//		element.click();
//		
//	}
//	
//	public void updatePurposeOfOrganization(String purpose) {
//		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//textarea[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtOrgPurpose_text']"));
//		element.clear();
//		element.sendKeys(purpose);
//		
//		// Click in business number to change focus
//		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtBusinessNumber_text']"));
//		element.click();
//	}
//	
//	public void updateOrganizationName(String name) {
//		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtOrgName_text']"));
//		element.clear();
//		element.sendKeys(name);
//		
//		// Click in business number to change focus
//		element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_txtBusinessNumber_text']"));
//		element.click();
//	}
//	
//	public void save() {
//		WebElement element = driverManager.getExternalWebDriver().findElement(By.xpath("//input[@type='submit'][@id='ctl00_c2_btnSave']"));
//		element.click();
//	}
//	
//	public void dismissSaveConfirmation(boolean confirm) {
//		Alert alert = driverManager.getExternalWebDriver().switchTo().alert();
//		CheckPoint2.checkPointEquals(alert.getText(), "You are about to Save changes to the Member profile.\n\nDo you wish to continue?\n", "dismissSaveConfirmation", null);
//		if (confirm == true) {
//			alert.accept();
//		} else {
//			alert.dismiss();
//		}
//	}
//	
//	public boolean isAccountTypeEditable() {
//		boolean rc = true;
//		
//		// if we find this element, it is not editable because there is no editable elements
//		driverManager.getExternalWebDriver().findElement(By.xpath("//span[@id='ctl00_c2_accountInfo_frmViewAccountInformation_lblAccountType']/.."));
//		
//		return rc;
//	}
//	
//	public void invalidBusinessNumberAstrik(boolean present) {
//		inlineErrorPresent(present, "//span[@id='ctl00_c2_organizationInfo_frmViewOrganizationInfo_revBusinessNumber']", "Invalid Business Number - should be 9 digits long and numeric", "invalidBusinessNumberError present=" + present);
//	}
//	
//	public void invalidOrgNameWarning(boolean present) {
//		MiscFunctions.sleep(500);
//		List<WebElement> elements = driverManager.getExternalWebDriver().findElements(By.xpath("//div[@class='rwDialogText']"));
//		logger.writeLognote("Entering", driver);
//		for (int i=0; i<1; i++) {
//			CheckPoint2.checkPointEquals( elements.get(i).getText(), "Invalid Organization Name", "Error message", null);
//			WebElement element = elements.get(i).findElement(By.xpath(".//following-sibling::div/a"));
//			element.click();
//		}
//		if (elements.size() == 0) {
//			Errors.getErrorsObject().addError("Did not find invalidOrgNameWarning message");
//		}
//	}
//	
//	public void dimissInvalidBusinessNumberAlert() {
//		Alert alert = driverManager.getExternalWebDriver().switchTo().alert();
//		CheckPoint2.checkPointEquals(alert.getText(), "Invalid Business Number - should be 9 digits long and numeric", "dimissInvalidBusinessNumberAlert", null);
//		alert.dismiss();
//	}
//	
//	public void inlineErrorPresent(boolean present, String xpath, String errorMessage, String location) {
//		logger.writeLognote("Entering location=" + location, driver);
//		String style = "color: red; display: inline;";
//		if (present == false) {
//			style = "color: red; display: none;";
//		}
//		List<WebElement> elements = driverManager.getExternalWebDriver().findElements(By.xpath(xpath));
//		CheckPoint2.checkPointEquals(elements.size(), 1, "error count location=" + location, null);
//		if (elements.size() == 1) {
//			CheckPoint.checkPointContainsText(elements.get(0).getAttribute("errormessage"), errorMessage, location + " errormessage attribute text", null);
//			CheckPoint.checkPointContainsText(elements.get(0).getAttribute("style"), style, location + " errormessage style", null);
//		}
//	}
//	
//	public void organizationPurposeErrorAstrick(boolean present) {
//		errorAstrick(present, new String [] {"ctl00_c2_organizationInfo_frmViewOrganizationInfo_rgOrganizationPurpose"}, "organizationPurposeErrorAstrick");
//	}
//	
//	public void organizationTypeErrorAstrick(boolean present) {
//		errorAstrick(present, new String [] {"ctl00_c2_organizationInfo_frmViewOrganizationInfo_rvOrgType"}, "organizationTypeErrorAstrick");
//	}
//	
//	public void organizationNameAstrick(boolean present) {
//		errorAstrick(present, new String [] {"ctl00_c2_organizationInfo_frmViewOrganizationInfo_rvOrgName", "ctl00_c2_organizationInfo_frmViewOrganizationInfo_revOrgName", "ctl00_c2_organizationInfo_frmViewOrganizationInfo_revOrgName1"},
//				"organizationNameAstrick");
//		
//	}
//	
//	public void errorAstrick(boolean present, String [] ids, String location) {
//		boolean visible = false;
//		// Check if any are visible. If visible, confirm * as text
//		for (int i=0; i<ids.length; i++) {
//			List<WebElement> elements = driverManager.getExternalWebDriver().findElements(By.xpath("//span[@id='" + ids[i] + "']"));
//			logger.writeLognote("Looking for " + ids[i]);
//			CheckPoint2.checkPointEquals(elements.size(), 1, "asterick error count", null);
//			if (elements.size() == 1) {
//				String style = elements.get(0).getAttribute("style");
//				logger.writeLognote("style=" + style);
//				if (style.equals("color: red; display: inline;")) {
//					visible = true;
//					CheckPoint.checkPointContainsText(elements.get(0).getText(), "*", "Asterick text", null);
//				} else if (style.equals("color: red; display: none;")) {
//					// don't do anything
//				} else {
//					throw new RuntimeException("Unexpected style=" + style);
//				}
//			}
//		}
//		// if present, confirm at least 1 * is visible
//		CheckPoint2.checkPointEquals(visible, present, "errorAstrick Asterick visible location=" + location, null);
//	}
//	
//	public void dismissPurpose3Chars() {
//		Alert alert = driverManager.getExternalWebDriver().switchTo().alert();
//		CheckPoint2.checkPointEquals(alert.getText(), 
//				"Purpose of Organization must be at least 3 characters.", 
//				"dismissPurpose3Chars", null);
//		alert.dismiss();
//	}
//	
//	public void dismissOrgNameAlert() {
//		Alert alert = driverManager.getExternalWebDriver().switchTo().alert();
//		CheckPoint2.checkPointEquals(alert.getText(), 
//				"Please enter the organization name.", 
//				"dismissOrgNameAlert", null);
//		alert.dismiss();
//	}
//	
//	public void dismissInvalidOrgNameAlert() {
//		Alert alert = driverManager.getExternalWebDriver().switchTo().alert();
//		CheckPoint2.checkPointEquals(alert.getText(), 
//				"Invalid Organization name", 
//				"dismissInvalidOrgNameAlert", null);
//		alert.dismiss();
//	}

}
