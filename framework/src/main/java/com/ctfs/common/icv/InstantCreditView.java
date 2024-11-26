package com.ctfs.common.icv;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctfs.common.externalutils.ExternalDriverHelper;
import com.ctfs.common.externalutils.ExternalDriverManager;
import com.ctfs.common.externalutils.ExternalDriverWait;
import com.ctfs.ui.utils.DriverHelper;
import com.ctfs.ui.utils.DriverWait;

@Component
@SuppressWarnings("unused")
public class InstantCreditView {
	
	@Autowired
    private ExternalDriverManager externalDriverManager;
	
	@Autowired
	protected ExternalDriverHelper externalDriverHelper;
	
	@Autowired
	private ExternalDriverWait externalDriverWait;
	
	protected Actions actions; 
	
	public final Logger logg = LoggerFactory.getLogger(DriverHelper.class);

	private String url = "http://craev:welcome1@preprod8.ctal.ctc/mktgcredview/StartAppServlet";
	
	public static void main(String[] args) throws Exception {
		
//		InstantCreditView creditView = new InstantCreditView(driverManager);
//		creditView.getInstantCreditView();
////		creditView.gotoCreditApp();
//		creditView.searchBy_AppID("000896358");
//		creditView.creditApp_Match();
//		creditView.verifyEmpDetails(dto, dsaData, null, null);
//		creditView.verifyAquisitionInfo(null, dsaData);
//		creditView.verifyPersonalInfo(null, dsaData);
	}
	
	public void getInstantCreditView() {
		
		externalDriverManager.getExternalWebDriver().get(url);
//		try {
//			Robot robot = new Robot();
//			Thread.sleep(3000);
//			String uid = "craev";
//			String pwd = "welcome1";
//			System.out.println("About to enter uid");
//			for(int i = 0; i < uid.length(); i++) {
//				final char user = uid.charAt(i);
//				robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(user));
//				
//			}
//			System.out.println("Entered uid");
//			robot.keyPress(KeyEvent.VK_TAB);
//			Thread.sleep(2000);
//			for(int j = 0; j < pwd.length(); j++) {
//				final char password = pwd.charAt(j);
//				robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(password));
//			}
//			robot.keyPress(KeyEvent.VK_TAB);
//			robot.keyPress(KeyEvent.VK_ENTER);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		
	}
	
	public void getCreditApp(String firstName, String lastName, String loyaltyNumber)throws Exception{
		getInstantCreditView();
		externalDriverManager.getExternalWebDriver().findElement(By.linkText("Credit Applications")).click();
		try {
			externalDriverManager.getExternalWebDriver().findElement(By.linkText("Credit Applications")).click();
		} catch (Exception e) {
			logg.info("Caught exception, will retry. msg=" + e.getMessage());
			Thread.sleep(3000);
			externalDriverManager.getExternalWebDriver().navigate().refresh();
			externalDriverManager.getExternalWebDriver().findElement(By.linkText("Credit Applications")).click();
		}
		
		try {
			WebElement baseElement = externalDriverManager.getExternalWebDriver().findElement(By.xpath("//form[@id='queryform']/div[@class='tab_over']//tr/td[@class='head']"));
			WebElement appAccID =baseElement.findElement(By.xpath("//input[@name='tsys_applid_txt']"));
			appAccID.sendKeys(loyaltyNumber);
			WebElement custFirstName = baseElement.findElement(By.xpath("//input[@name='cust_first_name']"));
			custFirstName.sendKeys(firstName);
			
			WebElement custLastName = baseElement.findElement(By.xpath("//input[@name='cust_last_name']"));
			custLastName.sendKeys(lastName);
			externalDriverManager.getExternalWebDriver().findElement(By.xpath("//input[@name='query_submit']")).click();
		} catch (Exception e) {
			logg.info("Caught exception, will retry. msg=" + e.getMessage());
			WebElement baseElement = externalDriverManager.getExternalWebDriver().findElement(By.xpath("//form[@id='queryform']/div[@class='tab_over']//tr/td[@class='head']"));
			externalDriverManager.getExternalWebDriver().navigate().refresh();
			WebElement appAccID =baseElement.findElement(By.xpath("//input[@name='tsys_applid_txt']"));
			appAccID.sendKeys(loyaltyNumber);
			WebElement custFirstName = baseElement.findElement(By.xpath("//input[@name='cust_first_name']"));
			custFirstName.sendKeys(firstName);
			
			WebElement custLastName = baseElement.findElement(By.xpath("//input[@name='cust_last_name']"));
			custLastName.sendKeys(lastName);
			externalDriverManager.getExternalWebDriver().findElement(By.xpath("//input[@name='query_submit']")).click();
		}
	}
	
	public void getCreditApp() throws Exception {
		externalDriverManager.getExternalWebDriver().findElement(By.linkText("Credit Applications")).click();
		WebElement baseElement = externalDriverManager.getExternalWebDriver().findElement(By.xpath("//form[@id='queryform']/div[@class='tab_over']//tr/td[@class='head']"));
		
		WebElement microRefNbr = baseElement.findElement(By.xpath("//input[@name='form_subm_id']"));
		microRefNbr.sendKeys("");
		WebElement custPhone = baseElement.findElement(By.xpath("//input[@name='cust_phone']"));
		custPhone.sendKeys("");
		WebElement appAccID =baseElement.findElement(By.xpath("//input[@name='tsys_applid_txt']"));
		appAccID.sendKeys("");
		
		WebElement processed_Any = baseElement.findElement(By.xpath("//td[@class='summary']/input[1]"));
		processed_Any.click();
		
		WebElement processed_Yes = baseElement.findElement(By.xpath("//td[@class='summary']/input[2]"));
		processed_Yes.click();
		
		WebElement processed_No = baseElement.findElement(By.xpath("//td[@class='summary']/input[3]"));
		processed_No.click();
		
		WebElement startYear = baseElement.findElement(By.xpath("//input[@name='begDate_y']"));
		startYear.clear();
		startYear.sendKeys("");
		
		WebElement startMonth = baseElement.findElement(By.xpath("//input[@name='begDate_m']"));
		startMonth.clear();
		startMonth.sendKeys("");
		
		WebElement starDay = baseElement.findElement(By.xpath("//input[@name='begDate_d']"));
		starDay.clear();
		starDay.sendKeys("");
		
		WebElement endYear = baseElement.findElement(By.xpath("//input[@name='endDate_y']"));
		endYear.sendKeys("");
		
		WebElement endMonth = baseElement.findElement(By.xpath("//input[@name='endDate_m']"));
		endMonth.sendKeys("");
		
		WebElement endDay = baseElement.findElement(By.xpath("//input[@name='endDate_d']"));
		endDay.sendKeys("");
		
		WebElement custFirstName = baseElement.findElement(By.xpath("//input[@name='cust_first_name']"));
		custFirstName.sendKeys("");
		
		WebElement custLastName = baseElement.findElement(By.xpath("//input[@name='cust_last_name']"));
		custLastName.sendKeys("");
		
		WebElement StoreNbr = baseElement.findElement(By.xpath("//input[@name='assc_stor_nbr']"));
		StoreNbr.sendKeys("");
		
		WebElement deviceAssetNbr = baseElement.findElement(By.xpath("//input[@name='devcAssetNbr']"));
		deviceAssetNbr.sendKeys("");
		
		externalDriverHelper.selectFromDropdownByText(By.xpath("//select[@name='channel']"), "ANY");
		externalDriverHelper.selectFromDropdownByText(By.xpath("//select[@name='adjudication']"), "ANY");
		
		WebElement search = baseElement.findElement(By.xpath("//input[@name='query_submit']"));
//		search.click();
	}
	
	public void searchBy_AppID(String appID) {
		externalDriverManager.getExternalWebDriver().findElement(By.linkText("Credit Applications")).click();
		try {
			WebElement baseElement = externalDriverManager.getExternalWebDriver().findElement(By.xpath("(//form[@id='queryform']/div[@class='tab_over']//tr/td[@class='head'])[1]"));
			if(baseElement.isDisplayed()) {
				WebElement appAccID =baseElement.findElement(By.xpath("//input[@name='tsys_applid_txt']"));
				appAccID.sendKeys(appID);
				
				WebElement search = baseElement.findElement(By.xpath("//input[@name='query_submit']"));
				search.click();
			}
		} catch (ElementNotInteractableException e) {
			logg.info("baseElement is not Visible");
		}
		
	}
	
	public void searchBy_MicroFilmID(String microfilm, String appID) {
		externalDriverManager.getExternalWebDriver().findElement(By.linkText("Credit Applications")).click();
		try {
			WebElement baseElement = externalDriverManager.getExternalWebDriver().findElement(By.xpath("//form[@id='queryform']/div[@class='tab_over']//tr/td[@class='head']"));
			if(baseElement.isDisplayed()) {
				WebElement microRefNbr = baseElement.findElement(By.xpath("//input[@name='form_subm_id']"));
				microRefNbr.sendKeys(microfilm);
				
				WebElement appAccID =baseElement.findElement(By.xpath("//input[@name='tsys_applid_txt']"));
				appAccID.sendKeys(appID);
				
				WebElement search = baseElement.findElement(By.xpath("//input[@name='query_submit']"));
				search.click();
			}
		} catch (ElementNotInteractableException e) {
			logg.info("baseElement is not Visible");
		}
		
	}
	
	public void searchBy_CustName(String fisrtName, String lastName) {
		externalDriverManager.getExternalWebDriver().findElement(By.linkText("Credit Applications")).click();
		try {
			WebElement baseElement = externalDriverManager.getExternalWebDriver().findElement(By.xpath("//form[@id='queryform']/div[@class='tab_over']//tr/td[@class='head']"));
			if(baseElement.isDisplayed()) {
				WebElement custFirstName = baseElement.findElement(By.xpath("//input[@name='cust_first_name']"));
				custFirstName.sendKeys(fisrtName);
				
				WebElement custLastName = baseElement.findElement(By.xpath("//input[@name='cust_last_name']"));
				custLastName.sendKeys(lastName);
				WebElement search = baseElement.findElement(By.xpath("//input[@name='query_submit']"));
				search.click();
			}
		} catch (ElementNotInteractableException e) {
			logg.info("baseElement is not Visible");
		}
	}
	
	
	public void creditApp_Match() {
		try {
			String title = "Credit Applications - Matches";
			WebElement tableHeader = externalDriverManager.getExternalWebDriver().findElement(By.xpath("//body/table/tbody/tr/td/h3"));
			
			
			System.out.println(tableHeader.getText());
			if(tableHeader.getText().matches(title)) {
				WebElement custName = externalDriverManager.getExternalWebDriver().findElement(By.xpath("(//*[@class='table_box_left2'][4])"));
				System.out.println("CUSTNAME: " + custName.getText());
				WebElement view = externalDriverManager.getExternalWebDriver().findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td[@class='table2']/a"));
				view.click();
			}				
		} catch (Exception e) {
			throw e;
		}	
	}

	public void textValidation(String actual, String expected) {
		String errorMsg = "MISMATCH: expected: " + expected +  " " + "but found" + " " + actual;
		if(actual != null) {
			try {
				System.out.println("\n" + expected + " : " + actual + "\n");
				assertTrue(actual.contains(expected));
			} catch (Exception e) {
				System.out.println(errorMsg);
				e.printStackTrace();			
			}
			System.out.println("DONE COMPARING");
		}else if(actual == null){
			logg.info("ACTUAL text is null");
		}
	}
	
	
public String dateConversion(String inputDate, String outputDate, String givenDate) throws ParseException
{
	
	SimpleDateFormat input = new SimpleDateFormat(inputDate);
	SimpleDateFormat output = new SimpleDateFormat(outputDate);
	
	Date date= null;
	String requiredDate= null;
	
	date= input.parse(givenDate);
	requiredDate= output.format(date);
	
	return requiredDate;
	
}

public int replaceLeadingZeros(String inputData)  
{
	String str= inputData;
	int number= Integer.parseInt(str);
	
	return number;
	
}
	
public CreditApplicationDO scrapeAcqInformation(boolean SuppCard) throws InterruptedException {
	
	CreditApplicationDO crdtAppDo = new CreditApplicationDO();
	actions = new Actions(externalDriverManager.getExternalWebDriver());
	
System.out.println("CreditApplicationDO");
System.out.println("Supp Account"+SuppCard);
	//List<WebElement> baseElementsList = externalDriverManager.getExternalWebDriver().findElements(By.xpath(".//*[@class='tab_over']/table"));
	
//WebElement baseElement= externalDriverManager.getExternalWebDriver().findElements(By.xpath(".//*[@class='tab_over']/table"));

WebElement baseElement=externalDriverWait.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='tab_over']/table)[2]")));
	
	try {
		//(.//*[@class='tab_over']/table)[1]/tbody/tr/td[4]
		WebElement processed = externalDriverManager.getExternalWebDriver().findElement(By.xpath("(//*[@class='tab_over']/table)[1]//tbody/tr/td[4]"));
		crdtAppDo.setProcessed(processed.getText());

		WebElement adjudication = externalDriverManager.getExternalWebDriver().findElement(By.xpath("(//*[@class='tab_over']/table)[1]//tbody/tr/td[6]"));
		crdtAppDo.setAdjudication(adjudication.getText());
		
		WebElement ctfsAppID = baseElement.findElement(By.xpath("(.//tbody/tr[2]//td[2])[1]"));
		crdtAppDo.setCtfsAppID(ctfsAppID.getText());
		
		WebElement acqStart = baseElement.findElement(By.xpath("//tbody/tr[3]//td[2]"));
		
		WebElement acqCmplt = baseElement.findElement(By.xpath("//tbody/tr[3]//td[4]"));
		crdtAppDo.setAcqCmplt(acqCmplt.getText());
	
		WebElement elapsedTime = baseElement.findElement(By.xpath("//tbody/tr[3]//td[6]"));
		crdtAppDo.setElapsedTime(elapsedTime.getText());
		
		WebElement refID = baseElement.findElement(By.xpath("//tbody/tr[4]//td[2]"));
		crdtAppDo.setRefID(refID.getText());
		
		WebElement storeNbr = baseElement.findElement(By.xpath("//tbody/tr[4]//td[4]"));
		crdtAppDo.setStoreNumber(storeNbr.getText());
		
		WebElement aceAppID = baseElement.findElement(By.xpath("//tbody/tr[5]//td[2]"));
		crdtAppDo.setAceAppID(aceAppID.getText());
		
		WebElement deviceNbr = baseElement.findElement(By.xpath("//tbody/tr[5]//td[4]"));
		crdtAppDo.setDeviceNumber(deviceNbr.getText());
		
		WebElement channel = baseElement.findElement(By.xpath("//tbody/tr[5]//td[6]"));
		crdtAppDo.setChannel(channel.getText());
		
		WebElement hostessComapny = baseElement.findElement(By.xpath("//tbody/tr[6]//td[2]"));
		crdtAppDo.setHostessCompany(hostessComapny.getText());
		
		WebElement authUserID = baseElement.findElement(By.xpath("//tbody/tr[6]//td[4]"));
		crdtAppDo.setAuthUserID(authUserID.getText());
		
		WebElement acqStrat = baseElement.findElement(By.xpath("//tbody/tr[7]/td[1][contains(text(),'Acq')]/following-sibling::td[1]"));
		crdtAppDo.setAcqStrat(acqStrat.getText());
		
		WebElement promoCode = baseElement.findElement(By.xpath("//tbody/tr[7]//td[4]"));
		crdtAppDo.setPromoCode(promoCode.getText());
		
		WebElement hostess = baseElement.findElement(By.xpath("//tbody/tr[7]//td[6]"));
		crdtAppDo.setHostess(hostess.getText());
		
		WebElement reqCardType = baseElement.findElement(By.xpath("//tbody/tr[8]//td[2]"));
		crdtAppDo.setReqCardType(reqCardType.getText());
		
		WebElement approvedCardType = baseElement.findElement(By.xpath("//tbody/tr[8]//td[4]"));
		crdtAppDo.setApprovedCardType(approvedCardType.getText());
		
		WebElement campaign = baseElement.findElement(By.xpath("//tbody/tr[9]//td[2]"));
		crdtAppDo.setCampaign(campaign.getText());
		
		
		WebElement guiVer = baseElement.findElement(By.xpath("//tbody/tr[9]//td[4]"));
		crdtAppDo.setGuiVer(guiVer.getText());
		
		WebElement affilaitionCode = baseElement.findElement(By.xpath("//tbody/tr[10]//td[2]"));
		crdtAppDo.setAffliationCode(affilaitionCode.getText());
		
		WebElement acquirerCode = baseElement.findElement(By.xpath("//tbody/tr[10]//td[4]"));
		crdtAppDo.setAcquirerCode(acquirerCode.getText());
		
		WebElement tabSerialID = baseElement.findElement(By.xpath("//tbody/tr[10]//td[6]"));
		crdtAppDo.setTabSerialID(tabSerialID.getText());
		
		WebElement businessStoreID = baseElement.findElement(By.xpath("//tbody/tr[11]//td[2]"));
		crdtAppDo.setBusinessStoreNo(businessStoreID.getText());
		
		WebElement eStatementConsent = baseElement.findElement(By.xpath("//tbody/tr[11]//td[4]"));
		crdtAppDo.setEstatmentConsent(eStatementConsent.getText());
		
		WebElement idType = baseElement.findElement(By.xpath("//tbody/tr[3]//tr[2]/td[2]"));
		crdtAppDo.setTypeOfIdentification(idType.getText());
		System.out.println("Pass 2: 139");
		
		WebElement idNumber = baseElement.findElement(By.xpath("//tbody/tr[3]//tr[3]/td[2]"));
		crdtAppDo.setIDNumber(idNumber.getText());
		System.out.println("Pass 3: 143");
		WebElement placeOfIssue = baseElement.findElement(By.xpath("//tbody/tr[3]//tr[3]/td[4]"));
		crdtAppDo.setPlaceOfIssue(placeOfIssue.getText());
		
		WebElement expiryDate = baseElement.findElement(By.xpath("//tbody/tr[3]//tr[3]/td[4]"));
		crdtAppDo.setExpiryDate(expiryDate.getText());
		
		WebElement dlSwiped = baseElement.findElement(By.xpath("//tbody/tr[3]//tr[4]/td[2]"));
		crdtAppDo.setDLSwiped(dlSwiped.getText());
		
		WebElement loyaltyCardNum = baseElement.findElement(By.xpath("//tbody/tr[3]//tr[5]/td[2]"));
		crdtAppDo.setLoyaltyCardNum(loyaltyCardNum.getText());
		
		WebElement customerName = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[2]/td[2]"));
		crdtAppDo.setCustName(customerName.getText());
		
		 WebElement email = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[3]/td[2]"));
		 crdtAppDo.setEmailAddr(email.getText());
		 
		 WebElement correspLang = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[3]/td[4]"));
		 crdtAppDo.setCorrespLang(correspLang.getText());
		 
		 WebElement emailMktgConsent = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[4]/td[2]"));
		 crdtAppDo.setEmailMktgConsentString(emailMktgConsent.getText());
		 
		 WebElement tcpIP = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[4]/td[4]"));
		 crdtAppDo.setTcpIPAddr(tcpIP.getText());
		 
		 WebElement dob = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[5]/td[4]"));
		 crdtAppDo.setDob(dob.getText());
		 
		 WebElement mothersMaidenName = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[5]/td[2]"));
		 crdtAppDo.setMothersMaidenName(mothersMaidenName.getText());
		 
		 System.out.println("FIx from here to end");
		 
		 WebElement sin = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[6]/td[4]"));
		 crdtAppDo.setSin(sin.getText());
		 
		 WebElement applicantGender = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[6]/td[2]"));
		 crdtAppDo.setApplicantGender(applicantGender.getText());
		 
		 WebElement macAddr = baseElement.findElement(By.xpath("//tbody/tr[4]//tr[7]/td[2]"));
		 crdtAppDo.setMACAddr(macAddr.getText());
		 
		 WebElement residenceType = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[2]/td[2]"));
		 crdtAppDo.setResidentType(residenceType.getText());
		 
		 WebElement phone = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[2]/td[4]"));
		 crdtAppDo.setPhone(phone.getText());
		 
		 WebElement cell = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[2]/td[6]"));
		 crdtAppDo.setCell(cell.getText());
		 
		 WebElement ncodeCode = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[2]/td[8]"));
		 crdtAppDo.setNcodeCode(ncodeCode.getText());
		 
		 WebElement street = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[3]/td[2]"));
		 crdtAppDo.setStreet(street.getText());
		 
		 WebElement city = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[4]/td[2]"));
		 crdtAppDo.setCity(city.getText());
		 
		 WebElement mthlyRent = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[4]/td[4]"));
		 crdtAppDo.setMthlyRent(mthlyRent.getText());
		 
		 WebElement appt = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[4]/td[6]"));
		 crdtAppDo.setAppt(appt.getText());
		 
		 WebElement province = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[5]/td[2]"));
		 crdtAppDo.setProv(province.getText());
		 
		 WebElement country = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[5]/td[4]"));
		 crdtAppDo.setCountry(country.getText());
		 
		 WebElement postalCode = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[5]/td[6]"));
		 crdtAppDo.setPostalCode(postalCode.getText());
		 
		 WebElement duration = baseElement.findElement(By.xpath("//tbody/tr[5]//tr[5]/td[8]"));
		 crdtAppDo.setDuration(duration.getText());
		 
		 
		 WebElement appState = baseElement.findElement(By.xpath("(//*[@class='tab_over']/table)[1]//tbody/tr/td[2]"));
		 crdtAppDo.setAppState(appState.getText());
		 
		 WebElement occupation = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[2]/td[2]"));
		 actions.moveToElement(occupation).build().perform();
		 crdtAppDo.setOccupation(occupation.getText());
		 
		 WebElement occupationDesc = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[2]/td[4]"));
		 actions.moveToElement(occupationDesc).build().perform();
		 crdtAppDo.setOccupationDesc(occupationDesc.getText());
		 
		 System.out.println("ICV JobCategory:"+crdtAppDo.getOccupation());
		 
		 if(crdtAppDo.getOccupation().trim().equalsIgnoreCase("retired") || crdtAppDo.getOccupation().trim().equalsIgnoreCase("UNEMPLOYED")||crdtAppDo.getOccupation().trim().equalsIgnoreCase("homemaker"))
		 {
		 
		 WebElement income = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[5]/td[4]"));  //tr[8]
		 actions.moveToElement(income).build().perform();
		 crdtAppDo.setIncome(income.getText());
		 
		 WebElement incomeFreq = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[6]/td[2]"));  //tr[9]
		 crdtAppDo.setIncomeFreq(incomeFreq.getText());
		 
		 WebElement householdIncome = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[5]/td[2]"));  //tr[8]
		 actions.moveToElement(householdIncome).build().perform();
		 crdtAppDo.setHouseholdIncome(householdIncome.getText());
		 
		 WebElement type = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[5]/td[2]"));
		 actions.moveToElement(type).build().perform();
		 crdtAppDo.setType(type.getText());
		 
		 WebElement empPhone =baseElement.findElement(By.xpath("//tbody/tr[7]//tr[3]/td[4]"));
		 actions.moveToElement(empPhone).build().perform();
		 crdtAppDo.setEmpPhone(empPhone.getText());
		 
		 WebElement employer = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[4]/td[2]"));
		 actions.moveToElement(employer).build().perform();
		 crdtAppDo.setEmployer(employer.getText());
		 
		 WebElement empDuration = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[4]/td[4]"));  //TR[6]
		 actions.moveToElement(empDuration).build().perform();
		 crdtAppDo.setEmpDuration(empDuration.getText());
		 
//		 WebElement empStreet = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[7]/td[2]"));
//		 actions.moveToElement(empStreet).build().perform();
//		 crdtAppDo.setEmpStreet(empStreet.getText());
//		 
//		 WebElement empAppt = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[7]/td[4]"));
//		 actions.moveToElement(empAppt).build().perform();
//		 crdtAppDo.setEmpAppt(empAppt.getText());
		 
//		 WebElement empCity = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[6]/td[2]"));
//		 actions.moveToElement(empCity).build().perform();
//		 crdtAppDo.setEmpCity(empCity.getText());
//		 
//		 WebElement empProvince = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[8]/td[4]"));
//		 actions.moveToElement(empProvince).build().perform();
//		 crdtAppDo.setEmpProv(empProvince.getText());
//		 
//		 WebElement empPostalCode = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[9]/td[2]"));
//		 actions.moveToElement(empPostalCode).build().perform();
//		 crdtAppDo.setEmpPostalCode(empPostalCode.getText());
		 
		 }
		 
		 else
		 {
			 WebElement income = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[8]/td[4]"));  //tr[8]
			 actions.moveToElement(income).build().perform();
			 crdtAppDo.setIncome(income.getText());
			 
			 WebElement incomeFreq = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[9]/td[2]"));  //tr[9]
			 crdtAppDo.setIncomeFreq(incomeFreq.getText());
			 
			 WebElement householdIncome = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[8]/td[2]"));  //tr[8]
			 actions.moveToElement(householdIncome).build().perform();
			 crdtAppDo.setHouseholdIncome(householdIncome.getText());
			 
			 WebElement type = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[5]/td[2]"));
			 actions.moveToElement(type).build().perform();
			 crdtAppDo.setType(type.getText());
			 
			 WebElement empPhone =baseElement.findElement(By.xpath("//tbody/tr[7]//tr[3]/td[4]"));
			 actions.moveToElement(empPhone).build().perform();
			 crdtAppDo.setEmpPhone(empPhone.getText());
			 
			 WebElement employer = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[4]/td[2]"));
			 actions.moveToElement(employer).build().perform();
			 crdtAppDo.setEmployer(employer.getText());
			 
			 WebElement empDuration = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[6]/td[4]"));  //TR[6]
			 actions.moveToElement(empDuration).build().perform();
			 crdtAppDo.setEmpDuration(empDuration.getText());
			 
//			 WebElement empStreet = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[7]/td[2]"));
//			 actions.moveToElement(empStreet).build().perform();
//			 crdtAppDo.setEmpStreet(empStreet.getText());
//			 
//			 WebElement empAppt = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[7]/td[4]"));
//			 actions.moveToElement(empAppt).build().perform();
//			 crdtAppDo.setEmpAppt(empAppt.getText());
			 
			 WebElement empCity = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[6]/td[2]"));
			 actions.moveToElement(empCity).build().perform();
			 crdtAppDo.setEmpCity(empCity.getText());
			 
			 WebElement empProvince = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[8]/td[4]"));
			 actions.moveToElement(empProvince).build().perform();
			 crdtAppDo.setEmpProv(empProvince.getText());
			 
			 WebElement empPostalCode = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[9]/td[2]"));
			 actions.moveToElement(empPostalCode).build().perform();
			 crdtAppDo.setEmpPostalCode(empPostalCode.getText());
		 }
		 
//		 WebElement empCountry = baseElement.findElement(By.xpath("//tbody/tr[7]//tr[7]/td[2]"));
//		 actions.moveToElement(empCountry).build().perform();
//		 crdtAppDo.setEmpCountry(empCountry.getText());

		 if(SuppCard == true) {
			 try {
				 logg.info("Attemptimg to scrape Supplementary Card Table");
				 WebElement suppCard1Name = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[2]/td[2]"));
				 actions.moveToElement(suppCard1Name).build().perform();
				 crdtAppDo.setSuppCard1Name(suppCard1Name.getText());
				 
				 WebElement relationship = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[2]/td[4]"));
				 actions.moveToElement(relationship).build().perform();
				 crdtAppDo.setRelationship(relationship.getText());
				 
				 WebElement suppDob = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[3]/td[2]"));
				 actions.moveToElement(suppDob).build().perform();
				 crdtAppDo.setSuppDOB(suppDob.getText());
				 
				 WebElement suppOccupation = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[4]/td[2]"));
				 actions.moveToElement(suppOccupation).build().perform();
				 crdtAppDo.setSuppOccupation(suppOccupation.getText());
				 
				 WebElement addSameAsPrimary = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[5]/td[2]"));
				 actions.moveToElement(addSameAsPrimary).build().perform();
				 crdtAppDo.setAddSameAsPrimary(addSameAsPrimary.getText());
				 
				 WebElement suppStreet = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[6]/td[2]"));
				 actions.moveToElement(suppStreet).build().perform();
				 crdtAppDo.setStreet(suppStreet.getText());
				 
				 WebElement suppAppt = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[6]/td[4]"));
				 actions.moveToElement(suppAppt).build().perform();
				 crdtAppDo.setSuppAppt(suppAppt.getText());
				 
				 WebElement suppCity = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[7]/td[4]")); 
				 actions.moveToElement(suppCity).build().perform();
				 crdtAppDo.setSuppCity(suppCity.getText());
				 
				 WebElement suppProvince = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[8]/td[2]"));
				 actions.moveToElement(suppProvince).build().perform();
				 crdtAppDo.setSuppProvince(suppProvince.getText());
				 
				 WebElement suppCountry = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[8]/td[4]")); 
				 actions.moveToElement(suppCountry).build().perform();
				 crdtAppDo.setSuppCountry(suppCountry.getText());
				 
				 WebElement suppPostalCode = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[9]/td[2]"));
				 actions.moveToElement(suppPostalCode).build().perform();
				 crdtAppDo.setSuppPostalCode(suppPostalCode.getText());
				 
				 WebElement suppPhone = baseElement.findElement(By.xpath("//tbody/tr[9]//tr[9]/td[4]"));
				 actions.moveToElement(suppPhone).build().perform();
				 crdtAppDo.setSuppPhone(suppPhone.getText());
				 
				 WebElement fpsTreatmentScore = baseElement.findElement(By.xpath("//tbody/tr[12]//tr[3]/td[4]"));
				 crdtAppDo.setFpsTreatmentScore(fpsTreatmentScore.getText());
				 
				 WebElement status = baseElement.findElement(By.xpath("//tbody/tr[15]//tr[5]/td[8]"));
				 actions.moveToElement(status).build().perform();
				 crdtAppDo.setProcessingStatus(status.getText().trim());
				 System.out.println("Status1 " + status.getText().trim());
				 
//				 WebElement status2 = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[7]/td[8]"));
//				 actions.moveToElement(status2).build().perform();
//				 crdtAppDo.setProcessingStatus2(status2.getText().trim());
//				 System.out.println("Status2 " + status2.getText().trim());
//				 
//				 WebElement status3 = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[9]/td[8]"));
//				 actions.moveToElement(status3).build().perform();
//				 crdtAppDo.setProcessingStatus3(status3.getText().trim());
//				 System.out.println("Status3 " + status3.getText().trim());
				 
				 WebElement transactionID = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[2]/td[2]"));
				 actions.moveToElement(transactionID).build().perform();
				 crdtAppDo.setTransactionID(transactionID.getText());
				 
				 WebElement issueDt = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[2]/td[4]"));
				 actions.moveToElement(issueDt).build().perform();
				 crdtAppDo.setIssueDt(issueDt.getText());
				 
				 WebElement issuingCountry = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[2]/td[6]"));
				 actions.moveToElement(issuingCountry).build().perform();
				 crdtAppDo.setIssuingCountry(issuingCountry.getText());
				 
				 WebElement appStatusAtScan = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[2]/td[8]"));
				 actions.moveToElement(appStatusAtScan).build().perform();
				 crdtAppDo.setAppStatusAtScan(appStatusAtScan.getText());
				 
				 WebElement firstNamefromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[3]/td[2]"));
				 actions.moveToElement(firstNamefromDVS).build().perform();
				 crdtAppDo.setFirstNamefromDVS(firstNamefromDVS.getText());
				 
				 WebElement lastNamefromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[3]/td[4]"));
				 actions.moveToElement(lastNamefromDVS).build().perform();
				 crdtAppDo.setLastNamefromDVS(lastNamefromDVS.getText());
				 
				 WebElement middleNamefromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[3]/td[6]"));
				 actions.moveToElement(middleNamefromDVS).build().perform();
				 crdtAppDo.setMiddleNamefromDVS(middleNamefromDVS.getText());
			
				 WebElement dobfromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[3]/td[8]"));
				 actions.moveToElement(dobfromDVS).build().perform();
				 crdtAppDo.setDobfromDVS(dobfromDVS.getText());
				 
				 WebElement streetAddfromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[4]/td[2]"));
				 actions.moveToElement(streetAddfromDVS).build().perform();
				 crdtAppDo.setStreetAddfromDVS(streetAddfromDVS.getText());
				 
				 WebElement cityfromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[4]/td[4]"));
				 actions.moveToElement(cityfromDVS).build().perform();
				 crdtAppDo.setCityfromDVS(cityfromDVS.getText());
				 
				 WebElement provincefromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[4]/td[6]"));
				 actions.moveToElement(provincefromDVS).build().perform();
				 crdtAppDo.setProvincefromDVS(provincefromDVS.getText());
				 
				 WebElement postCodefromDVS = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[4]/td[8]"));
				 actions.moveToElement(postCodefromDVS).build().perform();
				 crdtAppDo.setpostCodefromDVS(postCodefromDVS.getText());
				 
				 WebElement scanResult = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[6]/td[2]"));
				 actions.moveToElement(scanResult).build().perform();
				 crdtAppDo.setscanResult(scanResult.getText());
				 
				 WebElement driverLicPhotoFlag = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[7]/td[2]"));
				 actions.moveToElement(driverLicPhotoFlag).build().perform();
				 crdtAppDo.setDriverLicPhotoFlag(driverLicPhotoFlag.getText());
				 
				 WebElement suspectedFlag = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[6]/td[4]"));
				 actions.moveToElement(suspectedFlag).build().perform();
				 crdtAppDo.setSuspectedFlag(suspectedFlag.getText());
				 
				 WebElement rejectedFlag = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[6]/td[6]"));
				 actions.moveToElement(rejectedFlag).build().perform();
				 crdtAppDo.setrejectedFlag(rejectedFlag.getText());
				 
				 WebElement documentType = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[7]/td[4]"));
				 actions.moveToElement(documentType).build().perform();
				 crdtAppDo.setDocumentType(documentType.getText());
				 
				 WebElement documentNum = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[7]/td[6]"));
				 actions.moveToElement(documentNum).build().perform();
				 crdtAppDo.setDocumentNum(documentNum.getText());
				 
				 WebElement issuingAuthority = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[7]/td[8]"));
				 actions.moveToElement(issuingAuthority).build().perform();
				 crdtAppDo.setIssuingAuthority(issuingAuthority.getText());
				 
				 WebElement expiryDateID = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[8]/td[2]"));
				 actions.moveToElement(expiryDateID).build().perform();
				 crdtAppDo.setexpiryDateID(expiryDateID.getText());
				 
				 WebElement source = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[6]/td[8]"));
				 actions.moveToElement(source).build().perform();
				 crdtAppDo.setSource(source.getText());
				 
				 WebElement SKConsent = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[9]/td[2]"));
				 actions.moveToElement(SKConsent).build().perform();
				 crdtAppDo.setSKConsent(SKConsent.getText());
				 
				 WebElement SKConsentDate = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[9]/td[4]"));
				 actions.moveToElement(SKConsentDate).build().perform();
				 crdtAppDo.setSKConsentDate(SKConsentDate.getText());
				 
				 WebElement eKYCDecisionResult = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[9]/td[6]"));
				 actions.moveToElement(eKYCDecisionResult).build().perform();
				 crdtAppDo.seteKYCDecisionResult(eKYCDecisionResult.getText());
				 
				 WebElement fnameMatchScore = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[5]/td[2]"));
				 actions.moveToElement(fnameMatchScore).build().perform();
				 crdtAppDo.setFnameMatchScore(fnameMatchScore.getText());
				 
				 WebElement lnameMatchScore = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[5]/td[4]"));
				 actions.moveToElement(lnameMatchScore).build().perform();
				 crdtAppDo.setLnameMatchScore(lnameMatchScore.getText());
				 
				 WebElement dobMatchScore = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[5]/td[6]"));
				 actions.moveToElement(dobMatchScore).build().perform();
				 crdtAppDo.setDobMatchScore(dobMatchScore.getText());
				 
				 WebElement postcodeMatchScore = baseElement.findElement(By.xpath("//tbody/tr[17]//tr[5]/td[8]"));
				 actions.moveToElement(postcodeMatchScore).build().perform();
				 crdtAppDo.setPostcodeMatchScore(postcodeMatchScore.getText());

				 logg.info("Done scraping Supplementary Card Table");
			} catch (Exception e) {
				logg.info("Caught Exception trying to Scrape Supplementary Card Table", e);
				e.printStackTrace();
				throw new RuntimeException();
			}
		 }
		 
		 else
		 {
			 WebElement fpsTreatmentScore = baseElement.findElement(By.xpath("//tbody/tr[11]//tr[3]/td[4]"));
			 crdtAppDo.setFpsTreatmentScore(fpsTreatmentScore.getText());
		 
		 WebElement transactionID = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[2]/td[2]"));
		 actions.moveToElement(transactionID).build().perform();
		 crdtAppDo.setTransactionID(transactionID.getText());
		 
		 WebElement issueDt = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[2]/td[4]"));
		 actions.moveToElement(issueDt).build().perform();
		 crdtAppDo.setIssueDt(issueDt.getText());
		 
		 WebElement issuingCountry = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[2]/td[6]"));
		 actions.moveToElement(issuingCountry).build().perform();
		 crdtAppDo.setIssuingCountry(issuingCountry.getText());
		 
		 WebElement appStatusAtScan = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[2]/td[8]"));
		 actions.moveToElement(appStatusAtScan).build().perform();
		 crdtAppDo.setAppStatusAtScan(appStatusAtScan.getText());
		 
		 WebElement firstNamefromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[3]/td[2]"));
		 actions.moveToElement(firstNamefromDVS).build().perform();
		 crdtAppDo.setFirstNamefromDVS(firstNamefromDVS.getText());
		 
		 WebElement lastNamefromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[3]/td[4]"));
		 actions.moveToElement(lastNamefromDVS).build().perform();
		 crdtAppDo.setLastNamefromDVS(lastNamefromDVS.getText());
		 
		 WebElement middleNamefromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[3]/td[6]"));
		 actions.moveToElement(middleNamefromDVS).build().perform();
		 crdtAppDo.setMiddleNamefromDVS(middleNamefromDVS.getText());
	
		 WebElement dobfromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[3]/td[8]"));
		 actions.moveToElement(dobfromDVS).build().perform();
		 crdtAppDo.setDobfromDVS(dobfromDVS.getText());
		 
		 WebElement streetAddfromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[4]/td[2]"));
		 actions.moveToElement(streetAddfromDVS).build().perform();
		 crdtAppDo.setStreetAddfromDVS(streetAddfromDVS.getText());
		 
		 WebElement cityfromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[4]/td[4]"));
		 actions.moveToElement(cityfromDVS).build().perform();
		 crdtAppDo.setCityfromDVS(cityfromDVS.getText());
		 
		 WebElement provincefromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[4]/td[6]"));
		 actions.moveToElement(provincefromDVS).build().perform();
		 crdtAppDo.setProvincefromDVS(provincefromDVS.getText());
		 
		 WebElement postCodefromDVS = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[4]/td[8]"));
		 actions.moveToElement(postCodefromDVS).build().perform();
		 crdtAppDo.setpostCodefromDVS(postCodefromDVS.getText());
		 
		 WebElement scanResult = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[6]/td[2]"));
		 actions.moveToElement(scanResult).build().perform();
		 crdtAppDo.setscanResult(scanResult.getText());
		 
		 WebElement driverLicPhotoFlag = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[7]/td[2]"));
		 actions.moveToElement(driverLicPhotoFlag).build().perform();
		 crdtAppDo.setDriverLicPhotoFlag(driverLicPhotoFlag.getText());
		 
		 WebElement suspectedFlag = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[6]/td[4]"));
		 actions.moveToElement(suspectedFlag).build().perform();
		 crdtAppDo.setSuspectedFlag(suspectedFlag.getText());
		 
		 WebElement rejectedFlag = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[6]/td[6]"));
		 actions.moveToElement(rejectedFlag).build().perform();
		 crdtAppDo.setrejectedFlag(rejectedFlag.getText());
		 
		 WebElement documentType = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[7]/td[4]"));
		 actions.moveToElement(documentType).build().perform();
		 crdtAppDo.setDocumentType(documentType.getText());
		 
		 WebElement documentNum = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[7]/td[6]"));
		 actions.moveToElement(documentNum).build().perform();
		 crdtAppDo.setDocumentNum(documentNum.getText());
		 
		 WebElement issuingAuthority = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[7]/td[8]"));
		 actions.moveToElement(issuingAuthority).build().perform();
		 crdtAppDo.setIssuingAuthority(issuingAuthority.getText());
		 
		 WebElement expiryDateID = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[8]/td[2]"));
		 actions.moveToElement(expiryDateID).build().perform();
		 crdtAppDo.setexpiryDateID(expiryDateID.getText());
		 
		 WebElement source = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[6]/td[8]"));
		 actions.moveToElement(source).build().perform();
		 crdtAppDo.setSource(source.getText());
		 
		 WebElement SKConsent = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[9]/td[2]"));
		 actions.moveToElement(SKConsent).build().perform();
		 crdtAppDo.setSKConsent(SKConsent.getText());
		 
		 WebElement SKConsentDate = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[9]/td[4]"));
		 actions.moveToElement(SKConsentDate).build().perform();
		 crdtAppDo.setSKConsentDate(SKConsentDate.getText());
		 
		 WebElement eKYCDecisionResult = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[9]/td[6]"));
		 actions.moveToElement(eKYCDecisionResult).build().perform();
		 crdtAppDo.seteKYCDecisionResult(eKYCDecisionResult.getText());
		 
		 WebElement fnameMatchScore = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[5]/td[2]"));
		 actions.moveToElement(fnameMatchScore).build().perform();
		 crdtAppDo.setFnameMatchScore(fnameMatchScore.getText());
		 
		 WebElement lnameMatchScore = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[5]/td[4]"));
		 actions.moveToElement(lnameMatchScore).build().perform();
		 crdtAppDo.setLnameMatchScore(lnameMatchScore.getText());
		 
		 WebElement dobMatchScore = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[5]/td[6]"));
		 actions.moveToElement(dobMatchScore).build().perform();
		 crdtAppDo.setDobMatchScore(dobMatchScore.getText());
		 
		 WebElement postcodeMatchScore = baseElement.findElement(By.xpath("//tbody/tr[16]//tr[5]/td[8]"));
		 actions.moveToElement(postcodeMatchScore).build().perform();
		 crdtAppDo.setPostcodeMatchScore(postcodeMatchScore.getText());
		 
		 logg.info("Exiting Scrape");
		 System.out.println("Exiting Scrape");
	} 
	}
	catch (Exception e) {
		throw e;
	}
	System.out.println(crdtAppDo + "\n");
	return crdtAppDo;
	
}

	public void logout() {
		externalDriverManager.getExternalWebDriver().findElement(By.linkText("Logout")).click();
		externalDriverManager.getExternalWebDriver().manage().deleteAllCookies();
		externalDriverManager.getExternalWebDriver().navigate().refresh();
		externalDriverManager.getExternalWebDriver().quit();
	}

	
}
