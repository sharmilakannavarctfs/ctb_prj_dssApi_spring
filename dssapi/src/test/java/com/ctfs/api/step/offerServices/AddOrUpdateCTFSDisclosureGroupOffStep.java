package com.ctfs.api.step.offerServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.offerServices.AddOrUpdateDisclosureGroupRequestPojo;
import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.service.offer.AddOrUpdateCTFSDisclosureGroupoffService;
import com.ctfs.api.service.offer.AddOrUpdateDisclosureGroupService;
import com.ctfs.api.service.offer.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.service.ts2.EStatementDeenrollmentService;
import com.ctfs.api.service.ts2.EvaluateCreditLimitService;
import com.ctfs.api.service.ts2.GetAccountInfoService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.api.utils.Database;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.dss.utils.AtomicServicesDBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class AddOrUpdateCTFSDisclosureGroupOffStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(AddOrUpdateCTFSDisclosureGroupOffStep.class);
	
	@Autowired
	private AddOrUpdateDisclosureGroupRequestPojo requestObj;
	
	static AddOrUpdateDisclosureGroupRequestPojo res_obj = null;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    
	
    @Autowired
    private AddOrUpdateCTFSDisclosureGroupoffService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to addorUpdatectfsDisclosureGroupOffers request {string} {string}")
	public void post_operation(String id,
			String offerCode) throws Throwable {
    	if(!id.equals("")) requestObj.setId(id);
    	if(!offerCode.equals("")) requestObj.setOfferCode(offerCode);
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
			service.addOrUpdatectfsdisclosureGp(requestObj);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    
    
    public String getRecord(String id, String column)
    {
    	return AtomicServicesDBUtils.getRecord(Database.db_pduser, "CTFSDISCLOSUREGROUPOFFERS", "id", id, column);
    }
	
	@Then("validate the addorUpdatectfsDisclosureGroupOffers api status code as {string}")
	public void validate_addUpdateNbo(String statusCode) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("addupdatectfsdisclosure");
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
			if(response.getStatusCode() ==200) {
				res_obj = response.getBody().as(AddOrUpdateDisclosureGroupRequestPojo.class);
				if(res_obj.getId()!=null) {

						Assert.assertEquals(res_obj.getId(),requestObj.getId());	
						Assert.assertEquals(res_obj.getOfferCode(),requestObj.getOfferCode());	
						if(res_obj.getId()!=null)
						{
							System.out.println(getRecord(res_obj.getId(), "id")+res_obj.getId());
							Assert.assertEquals(getRecord(res_obj.getId(), "id"), res_obj.getId());
							Assert.assertEquals(getRecord(res_obj.getId(), "OFFER_CODE"), res_obj.getOfferCode());
						}
					}
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
