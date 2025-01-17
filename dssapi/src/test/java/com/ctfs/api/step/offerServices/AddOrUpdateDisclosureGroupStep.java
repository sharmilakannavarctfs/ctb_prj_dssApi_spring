package com.ctfs.api.step.offerServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.request.offerServices.AddOrUpdateDisclosureGroupRequestPojo;
import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.offerservices.AddOrUpdateDisclosureGroupService;
import com.ctfs.api.service.offerservices.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.api.utils.Database;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.dss.utils.AtomicServicesDBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class AddOrUpdateDisclosureGroupStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(AddOrUpdateDisclosureGroupStep.class);
	
	@Autowired
	private AddOrUpdateDisclosureGroupRequestPojo requestObj;
	
	static AddOrUpdateDisclosureGroupRequestPojo res_obj = null;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    
	
    @Autowired
    private AddOrUpdateDisclosureGroupService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to addorUpdateDisclosureGroup request {string} {string} {string}")
	public void post_operation(String disclosureGroupCode,
			String disclosureGroupDesc,
			String welcomeKitFlag) throws Throwable {
    	if(!disclosureGroupCode.equals("")) requestObj.setDisclosureGroupCode(disclosureGroupCode);
    	if(!disclosureGroupDesc.equals("")) requestObj.setDisclosureGroupDesc(disclosureGroupCode);
    	if(!welcomeKitFlag.equals("")) requestObj.setWelcomeKitFlag(welcomeKitFlag);
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
			service.addOrUpdatedisclosureGp(requestObj);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    
    

	
	
	@Then("validate the addorUpdateDisclosureGroup api status code as {string}")
	public void validate_addUpdateNbo(String statusCode) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("addupdatedisclosure");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
			if(response.getStatusCode() ==200) {
				res_obj = response.getBody().as(AddOrUpdateDisclosureGroupRequestPojo.class);
				if(res_obj.getDisclosureGroupCode()!=null) {
						Assert.assertEquals(res_obj.getDisclosureGroupCode(),requestObj.getDisclosureGroupCode());	
						Assert.assertEquals(res_obj.getDisclosureGroupDesc(),requestObj.getDisclosureGroupDesc());	
						Assert.assertEquals(res_obj.getWelcomeKitFlag(),requestObj.getWelcomeKitFlag());	
					}
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
