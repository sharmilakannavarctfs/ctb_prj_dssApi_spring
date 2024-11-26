package com.ctfs.api.step;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.AddressCities;
import com.ctfs.api.pojos.request.Addresses;
import com.ctfs.api.pojos.request.Communication;
import com.ctfs.api.pojos.request.CommunicationDevices;
import com.ctfs.api.pojos.request.Countries;
import com.ctfs.api.pojos.request.CustomerNames;
import com.ctfs.api.pojos.request.DisplayNames;
import com.ctfs.api.pojos.request.FaxIds;
import com.ctfs.api.pojos.request.GarnishmentReferences;
import com.ctfs.api.pojos.request.LegalDetails;
import com.ctfs.api.pojos.request.OfficePhoneNumbers;
import com.ctfs.api.pojos.request.Streets;
import com.ctfs.api.pojos.request.UpdateCustomerRequestPojo;
import com.ctfs.api.service.UpdateCustomerService;
import com.ctfs.api.session.SessionContext;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.profile.MiscFunctions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class UpdateCustomerSteps extends AbstractStep{
	
	private final Logger log = LoggerFactory.getLogger(UpdateCustomerSteps.class);
	@Autowired
	private UpdateCustomerService updateCustomerService;
	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	@Autowired 
	SessionContext sessionContext;
	
	@Given("Perform post operation to hit updateCustomer DSS api using {string} as customerId and update for {string}")
	public void perform_post_operation_to_hit_update_customer_dss_api_using_as_customer_id_and_update_for(String customerId, String field) {
		try {
			String ranNumber=MiscFunctions.getRandomPhoneNumber("xxxxxxxxxx", "1");
			UpdateCustomerRequestPojo payload= new UpdateCustomerRequestPojo();
			payload.setCustomerId(customerId);
			Communication com= new Communication();
			com.setCommunicationDevice(field);
			com.setContactDetail(ranNumber);
			List<Communication> c = new ArrayList<>();
			c.add(com);
			payload.setCommunication(c);
			updateCustomerService.updateCustomer(payload);
		} catch (Exception e) {
			log.info("Error while hitting updateCustomer API with request payload");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit updateCustomer DSS api using {string} as customerId and update values of {string}")
	public void perform_post_operation_to_hit_update_customer_dss_api_using_as_customer_id_and_update_values_of(String customerId, String field) {
		try {
			UpdateCustomerRequestPojo payload= new UpdateCustomerRequestPojo();
			payload.setCustomerId(customerId);
			switch (field) {
			case "garnishmentReferences":{
				String ranNumber=MiscFunctions.getRandomPhoneNumber("xxxxxxxxxx", "1");
				GarnishmentReferences garRef = new GarnishmentReferences();
				garRef.setGarnishmentReference(ranNumber);
				List<GarnishmentReferences> garRefList = new ArrayList<>();
				garRefList.add(garRef);
				payload.setGarnishmentReferences(garRefList);
				break;
			}
			case "displayNames": {
				String ranName=MiscFunctions.generateRandomString(10);
				DisplayNames dName= new DisplayNames();
				dName.setDisplayName(ranName);
				List<DisplayNames> dNames= new ArrayList<>();
				dNames.add(dName);
				payload.setDisplayNames(dNames);
				break;
			}
			case "customerNames": {
				CustomerNames cusNames= new CustomerNames();
				cusNames.setCustomerName(MiscFunctions.generateRandomString(15));
				cusNames.setCustomerNameAdditional(MiscFunctions.generateRandomString(10));
				List<CustomerNames> cusNamesList = new ArrayList<>();
				cusNamesList.add(cusNames);
				payload.setCustomerNames(cusNamesList);
				break;
			}
			case "communicationDevices":{
				CommunicationDevices comDev = new CommunicationDevices();
				comDev.setPhoneNumber(MiscFunctions.getRandomPhoneNumber("xxxxxxxxxx", "1"));
				comDev.setSmsNumber(MiscFunctions.getRandomPhoneNumber("xxxxxxxxxx", "1"));
				comDev.setEmail("asdfgh@gmail.com");  //check to generate random mail
				comDev.setCommunicationType("CAMPAIGN");
				comDev.setPreferredChannel("CALLCENTRE");
				List<CommunicationDevices> comDevList= new ArrayList<>();
				comDevList.add(comDev);
				payload.setCommunicationDevices(comDevList);
				break;
			}
			case "faxIds":{
				FaxIds fIds= new FaxIds();
				fIds.setFaxId(MiscFunctions.getRandomPhoneNumber("xxxxxxxxxx", "1"));
				List<FaxIds> fIdList = new ArrayList<>();
				fIdList.add(fIds);
				payload.setFaxIds(fIdList);
				break;
			}
			case "officePhoneNumbers":{
				OfficePhoneNumbers opn= new OfficePhoneNumbers();
				opn.setOfficePhoneNumber(MiscFunctions.getRandomPhoneNumber("xxx-xxx-xxxx", "1"));
				List<OfficePhoneNumbers> opnList = new ArrayList<>();
				opnList.add(opn);
				payload.setOfficePhoneNumbers(opnList);
				break;
			}
			case "streets": {
				Streets s = new Streets();
				s.setStreet(MiscFunctions.generateRandomString(5)+" Street");
				List<Streets> sList = new ArrayList<>();
				sList.add(s);
				payload.setStreets(sList);
				break;
			}
			case "addresses":{
				Addresses a = new Addresses();
				a.setAddress(MiscFunctions.generateRandomString(8)+" address");
				List<Addresses> adList = new ArrayList<>();
				adList.add(a);
				payload.setAddresses(adList);
				break;
			}
			case "addressCities":{
				AddressCities ac= new AddressCities();
				ac.setAddressCity(MiscFunctions.getRandomCity("Na"));
				List<AddressCities> acList = new ArrayList<>();
				acList.add(ac);
				payload.setAddressCities(acList);
				break;
			}
			case "countries":{
				Countries co = new Countries();
				co.setCountry(MiscFunctions.getRandomCountry("CA"));
				List<Countries> coList= new ArrayList<>();
				coList.add(co);
				payload.setCountries(coList);
				break;
			}
			case "legalDetails":{
				Random r = new Random();
				LegalDetails l = new LegalDetails();
				l.setLegalId("1");
				l.setLegalDocumentName("BIRTH.CERTIFICATE");
				l.setLegalHolderName("Test"+Integer.toString(r.nextInt(100)));
				String todaysDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
				l.setLegalIssueAuthorisedDate(todaysDate);
				l.setLegalIssueCountry("CA");
				l.setLegalIssueDate(todaysDate);
				l.setLegalExpiredDate("20250129");
				List<LegalDetails> lList = new ArrayList<>();
				lList.add(l);
				payload.setLegalDetails(lList);
				break;
			}
				
			default:
				log.info("No Valid field passed");
				Assert.fail();
				break;
			}
			
			updateCustomerService.updateCustomer(payload);
		} catch (Exception e) {
			log.info("Error while hitting updateCustomer API with request payload");
			Assert.fail(e.getMessage());
		}
	}

	@Then("Validate updateCustomer DSS api response status code as {string}")
	public void validate_update_customer_dss_api_response_status_code_as(String statusCode) {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("UpdateCustomerService");
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),  "FAILED!! Status code is not matching");
		} catch (Exception e) {
			log.info("Error while comparing updateCustomer API's status code");
			Assert.fail(e.getMessage());
		}
	}
}
