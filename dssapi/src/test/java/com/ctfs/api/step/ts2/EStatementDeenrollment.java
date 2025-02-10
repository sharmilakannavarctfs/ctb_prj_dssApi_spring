package com.ctfs.api.step.ts2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.service.ts2.EStatementDeenrollmentService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class EStatementDeenrollment extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(EStatementDeenrollment.class);
	
	@Autowired
	private TS2RequestPojo tS2RequestPojo;
	

//    @Autowired
//    private DashProfileManagerUtils dpm ; 
    
    @Autowired
    private EStatementDeenrollmentService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("Post operation to hit EStatementDeenrollment from TS2-service using valid {string} {string}{string}and {string}")
	public void perform_post_operation_to_hit_EStatementDeEnrollment_Test(String cardNbr, 
			String accountId, 
			String custId,
			String operID) throws Throwable {
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
//			service.getAccount(getPayload(cardNo,dpm.getAccountID(),dpm.getCustomerId(),operID));
			service.estatementDeenrollment(getPayload(cardNbr,accountId,custId,operID));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    

	
	public TS2RequestPojo getPayload(String cardNbr, String accountId, String custId,String operator_id){
		System.out.println("cardNbr"+cardNbr+" accountId"+accountId+custId);
		tS2RequestPojo.setCardNbr(cardNbr);
		tS2RequestPojo.setAccountId(accountId);
		tS2RequestPojo.setCustId(custId);
		tS2RequestPojo.setOperatorId(operator_id);
		return tS2RequestPojo; 
	}
	
	@Then("Validate estatementDeenrollment DSS api response status code as {string}")
	public void validate_get_Account_TestResponse(String statusCode) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("getAccountResponse");
			GetAccount res_obj = null;
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
			int code=response.getStatusCode();
			if(response.getStatusCode()==900){
				res_obj = response.getBody().as(GetAccount.class);
//				Assert.assertEquals(response.getStatus(), status);

				if (res_obj.getStatus().equals("000")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "passed");
					
//					if(getTsysValidationFlag()) {
////						TSYS Validation
//					}
				}
				String desc = null;
				if (res_obj.getStatus().equals("999")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "failed");
					if(res_obj.getFaults().size()!=0)
						Assert.assertEquals(res_obj.getFaults().get(0).getDesc(), desc);
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
}
