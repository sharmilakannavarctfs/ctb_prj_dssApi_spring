package com.ctfs.api.step.offerServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.offerServices.AddOrUpdateDisclosureGroupRequestPojo;
import com.ctfs.api.pojos.request.offerServices.RetrieveDisclosureGroupResponsePojo;
import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.offer.AddOrUpdateDisclosureGroupService;
import com.ctfs.api.service.offer.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.service.offer.RetrieveCTFSDisclosureGroupOffersService;
import com.ctfs.api.service.offer.RetrieveDisclosureGroupService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.api.utils.Database;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.common.utils.ApplicationProperties;
import com.ctfs.dss.utils.AtomicServicesDBUtils;
import com.ctfs.profile.DataBase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class retrieveCTFSDisclosureGroupOffersStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(retrieveCTFSDisclosureGroupOffersStep.class);
	
	@Autowired
	private AddOrUpdateDisclosureGroupRequestPojo requestObj;
	
	static RetrieveDisclosureGroupResponsePojo res_obj = null;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    
    @Autowired
    private RetrieveCTFSDisclosureGroupOffersService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to retrieveCTFSDisclosureGroupOffers request")
	public void post_operation() throws Throwable {
    	
			service.retrieveCTFSDisclosureGroup(requestObj);
	
	}
    
	
	@Then("validate the retrieveCTFSDisclosureGroupOffers api status code as {string}")
	public void validate_addUpdateNbo(String statusCode) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("retrievCTFSdisclosureOff");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
			if(response.getStatusCode() ==200) {
				res_obj = response.getBody().as(RetrieveDisclosureGroupResponsePojo.class);
				Assert.assertTrue(res_obj.getCftsDisclosureGroupOffersData()!=null 
						&& res_obj.getCftsDisclosureGroupOffersData().size()!=0);
				String count = AtomicServicesDBUtils.runCustomQuery(Database.db_pduser, 
						"select count(*) as count from CTFSDISCLOSUREGROUPOFFERS", "count");
				Assert.assertEquals(Integer.parseInt(count), res_obj.getCftsDisclosureGroupOffersData().size());
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
