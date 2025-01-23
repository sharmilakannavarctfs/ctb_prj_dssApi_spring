package com.ctfs.api.step.offerServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.request.offerServices.AddOrUpdateDisclosureGroupRequestPojo;
import com.ctfs.api.pojos.request.offerServices.RetrieveDisclosureGroupResponsePojo;
import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.offer.AddOrUpdateDisclosureGroupService;
import com.ctfs.api.service.offer.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.service.offer.RetrieveDisclosureGroupService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.api.utils.Database;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.dss.utils.AtomicServicesDBUtils;
import com.ctfs.profile.DataBase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class RetrieveeDisclosureGroupStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(RetrieveeDisclosureGroupStep.class);
	
	@Autowired
	private AddOrUpdateDisclosureGroupRequestPojo requestObj;
	
	static RetrieveDisclosureGroupResponsePojo res_obj = null;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    
	
    @Autowired
    private RetrieveDisclosureGroupService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to retrieveDisclosureGroup request")
	public void post_operation() throws Throwable {
    	
			service.retrieveDisclosureGroup(requestObj);
	
	}
    
    

	
	
	@Then("validate the retrieveDisclosureGroup api status code as {string}")
	public void validate_addUpdateNbo(String statusCode) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("retrievdisclosure");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
			if(response.getStatusCode() ==200) {
				res_obj = response.getBody().as(RetrieveDisclosureGroupResponsePojo.class);
				Assert.assertTrue(res_obj.getRetrieveDisclosureGroupsOutputList()!=null);
				String count = AtomicServicesDBUtils.runCustomQuery(Database.db_pduser, 
						"select count(*) as count from CTFSDISCLOSUREGROUPDESC", "count");
				Assert.assertEquals(Integer.parseInt(count), res_obj.getRetrieveDisclosureGroupsOutputList().size());
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
