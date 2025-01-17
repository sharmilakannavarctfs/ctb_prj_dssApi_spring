package com.ctfs.api.step.offerServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.offerServices.DeleteDisclosureGroupRequest;
import com.ctfs.api.service.offerservices.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.service.offerservices.DeleteDisclosreGroupService;
import com.ctfs.api.service.offerservices.DeleteNBORepricingMatrixService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class DeleteDisclosureGroupStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(DeleteDisclosureGroupStep.class);
	
	@Autowired
	private DeleteDisclosureGroupRequest requestObj;
	

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
	
    @Autowired
    private DeleteDisclosreGroupService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
	
	@Then("delete the disclosureGrp record created with {string} and get the status as {string}")
	public void validate_disclosureGrprecord(String disclosureGroupCode, String statusCode) throws Throwable {
		requestObj.setDisclosureGroupCode(disclosureGroupCode);
    	if(!disclosureGroupCode.equals("") && !disclosureGroupCode.equals("null"))service.deleteDisclGrp(requestObj);
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("deletedisclosureGroup");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
//			System.out.println(AtomicServicesDBUtils.getRecord(Database.db_pdowner, "CTFSNBOREPRICINGMATRIX", "id", id, "id"));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
