package com.ctfs.api.step;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class EvaluateCreditLimitStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(EvaluateCreditLimitStep.class);
	
	@Autowired
	private TS2RequestPojo tS2RequestPojo;
	
	static Ts2ResponsePojo res_obj ;

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    
    @Autowired
    private EvaluateCreditLimitService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("Post operation to hit evaluateCreditLimit from TS2-service using valid {string} {string} and {string}")
	public void post_operation(String cardNbr, 
			String monthlyIncome,
			String op_id
			) throws Throwable {
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
			service.evaluateCreditLimit(getPayload(cardNbr,
				 	monthlyIncome,
					op_id));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    

	
	public TS2RequestPojo getPayload(String cardNbr, 
			String monthlyIncome, 
			String operator_id){
		if(cardNbr.equals("")) tS2RequestPojo.setCardNbr(null);
		else tS2RequestPojo.setCardNbr(cardNbr);
		if(operator_id.equals("")) tS2RequestPojo.setOperatorId(null);
		else tS2RequestPojo.setOperatorId(operator_id);
		if(monthlyIncome.equals("")) tS2RequestPojo.setElectronicVendorOptionId(null);
		else tS2RequestPojo.setMonthlyIncome(Integer.parseInt(monthlyIncome));
		return tS2RequestPojo; 
	}
	
	@Then("Validate evaluateCreditLimit DSS api response status code as {string} and fault description as {string}")
	public void validate_evaluate_creditlimit_TestResponse(String statusCode,String desc) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("evaluateCreditLimitResponse");
			
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
//			int code=response.getStatusCode();
			if(response.getStatusCode()==200){
				res_obj = response.getBody().as(Ts2ResponsePojo.class);
//				Assert.assertEquals(response.getStatus(), status);

				if (res_obj.getStatus().equals("000")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "passed");
					Assert.assertTrue(res_obj.getFaults().length==0);
					
				}
				if (res_obj.getStatus().equals("999")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "failed");
					if(res_obj.getFaults().length!=0)
						Assert.assertEquals(res_obj.getFaults()[0].getDesc(), desc);
				}
				if (res_obj.getStatus().equals("500")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "warning");
//					Assert.assertEquals(res_obj.getFaults().get(0).getDesc(), desc);
				}
				
				
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	@Then("Decline code and description as {string} and {string}")
	public void validate_decline_reason(String code, String description) {
		if(res_obj.getDeclineReasons().length!=0) {
			Assert.assertEquals(res_obj.getDeclineReasons()[0].getCode(), code);
			Assert.assertEquals(res_obj.getDeclineReasons()[0].getDescription(), "Scores do not meet criteria");
		}
	}
}
