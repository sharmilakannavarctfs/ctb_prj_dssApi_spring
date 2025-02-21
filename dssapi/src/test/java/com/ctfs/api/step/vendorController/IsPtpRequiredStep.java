package com.ctfs.api.step.vendorController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.request.vendorController.IsPtpRequiredReqpojo;
import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.ctfs.api.pojos.request.DeleteNboRepricingMatrixRequest;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.pojos.response.vendorController.IsPtpRequiredResPojo;
import com.ctfs.api.service.offer.AddOrUpdateNBORepricingMatrixService;
import com.ctfs.api.service.offer.DeleteNBORepricingMatrixService;
import com.ctfs.api.service.ts2.EStatementDeenrollmentService;
import com.ctfs.api.service.ts2.EvaluateCreditLimitService;
import com.ctfs.api.service.ts2.GetAccountInfoService;
import com.ctfs.api.service.vendorController.IsPtpRequiredService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.api.utils.Database;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.dss.utils.AtomicServicesDBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class IsPtpRequiredStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(IsPtpRequiredStep.class);
	
	@Autowired
	private IsPtpRequiredReqpojo requestObj;
	
	static IsPtpRequiredResPojo res_obj = null;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    private String id_created = null;
	
    @Autowired
    private IsPtpRequiredService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post the isiPtpRequired api on vendorController with accountId {string} and ficoCaseId {string}")
	public void post_operation(String accountId, 
			String ficoCaseId) throws Throwable {
//    	requestObj.setAlternateDesc(alternateDesc);
    	
		try {
			if(accountId.equals("null")) requestObj.setAccountId(null);
			else if (accountId.isEmpty()) requestObj.setAccountId("");
			else if (!accountId.isEmpty()) requestObj.setAccountId(accountId);
			
			
			if(ficoCaseId.equals("null")) requestObj.setFicoCaseId(null);
			else if (ficoCaseId.isEmpty()) requestObj.setFicoCaseId("");
			else if (!ficoCaseId.isEmpty()) requestObj.setFicoCaseId(ficoCaseId);
			
			service.post(requestObj);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
	@Then("verify the statusCode as {int} and result as {string}")
	public void then_method(int statusCode, String result) throws Throwable {
		Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("isPtpRequired");
		Assert.assertEquals(response.getStatusCode(),statusCode);
		if(statusCode == 200) {
			res_obj = response.getBody().as(IsPtpRequiredResPojo.class);
			Assert.assertEquals(res_obj.getResult(), result);

		}
	}
}
