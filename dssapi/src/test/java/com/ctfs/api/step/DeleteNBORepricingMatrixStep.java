package com.ctfs.api.step;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.ctfs.api.pojos.request.DeleteNboRepricingMatrixRequest;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.offerservices.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.service.offerservices.DeleteNBORepricingMatrixService;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.api.utils.Database;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.dss.utils.AtomicServicesDBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class DeleteNBORepricingMatrixStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(DeleteNBORepricingMatrixStep.class);
	
	@Autowired
	private AddOrUpdateNboRepricingMatrixRequest addRequestObj;
	@Autowired
	private DeleteNboRepricingMatrixRequest requestObj;
	
	static AddOrUpdateNboRepricingMatrixRequest res_obj = null;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    private String id_created = null;
	
    @Autowired
    private DeleteNBORepricingMatrixService service;
    @Autowired
    private AddOrUpdateNBORepricingMatrixService addservice;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to addorUpdateANBORepricingMatrix request {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} before deleting")
	public void post_operation(String alternateDesc, 
			String clientProdCode, 
			String currentPrimaryDisclosure, 
			String cycle, 
			String id, 
			String newAltDisclosure, 
			String newPrimaryDisclosure, 
			String offerCode, 
			String offerStatus, 
			String primaryDescr, 
			String productChangeOption, 
			String promoId, 
			String retentionId, 
			String tempDuration, 
			String treatmentCode, 
			String treatmentDescription, 
			String tsysLetterId) throws Throwable {
//    	requestObj.setAlternateDesc(alternateDesc);
    	addRequestObj.setClientProdCode(clientProdCode);
    	addRequestObj.setCurrentPrimaryDisclosure(currentPrimaryDisclosure);
    	addRequestObj.setCycle(cycle);
//    	addRequestObj.setId(id);
    	addRequestObj.setNewAltDisclosure(newAltDisclosure);
    	addRequestObj.setNewPrimaryDisclosure(newPrimaryDisclosure);
    	addRequestObj.setOfferCode(offerCode);
    	addRequestObj.setOfferStatus(offerStatus);
    	addRequestObj.setPrimaryDescr(primaryDescr);
    	addRequestObj.setProductChangeOption(productChangeOption);
    	addRequestObj.setPromoId(promoId);
    	addRequestObj.setRetentionId(retentionId);
    	addRequestObj.setTempDuration(tempDuration);
    	addRequestObj.setTreatmentCode(treatmentCode);
    	addRequestObj.setTreatmentDescription(treatmentDescription);
    	addRequestObj.setTsysLetterId(tsysLetterId);
		try {
			addservice.addOrUpdateNboRM(addRequestObj);
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("addupdateResponse");
			this.id_created = response.getBody().as(AddOrUpdateNboRepricingMatrixRequest.class).getId();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
	@Then("delete the record created with {string} and get the status as {string}")
	public void validate_addUpdateNbo(String id, String statusCode) throws Throwable {
		System.out.println("id created "+this.id_created);
		System.out.println("id sent "+id);
		if(id.equals("na")) requestObj.setId(this.id_created);
		else if(id.equals("")) requestObj.setId(null);
		else requestObj.setId(id);
    	service.deleteDisclosureGrp(requestObj);
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("deleteNBOMatrix");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
//			System.out.println(AtomicServicesDBUtils.getRecord(Database.db_pdowner, "CTFSNBOREPRICINGMATRIX", "id", id, "id"));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
