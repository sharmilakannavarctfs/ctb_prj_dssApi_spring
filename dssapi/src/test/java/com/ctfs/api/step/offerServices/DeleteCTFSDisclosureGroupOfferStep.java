package com.ctfs.api.step.offerServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.offerServices.DeleteDisclosureGroupRequest;
import com.ctfs.api.service.offer.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.service.offer.DeleteCTFSDisclosreGroupOfferService;
import com.ctfs.api.service.offer.DeleteDisclosreGroupService;
import com.ctfs.api.service.offer.DeleteNBORepricingMatrixService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class DeleteCTFSDisclosureGroupOfferStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(DeleteCTFSDisclosureGroupOfferStep.class);
	
	@Autowired
	private DeleteDisclosureGroupRequest requestObj;
	

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
	
    @Autowired
    private DeleteCTFSDisclosreGroupOfferService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
	
	@Then("delete the ctfsDisclosureGroupOffers record created with {string} and get the status as {string}")
	public void validate_disclosureGrprecord(String id, String statusCode) throws Throwable {
		requestObj.setId(id);
    	if(!id.equals("") && !id.equals("null"))
    		service.deletectfsDisclGrpoffer(statusCode);
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("deletedctfsdisclosureGroupOffer");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
//			System.out.println(AtomicServicesDBUtils.getRecord(Database.db_pdowner, "CTFSNBOREPRICINGMATRIX", "id", id, "id"));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
