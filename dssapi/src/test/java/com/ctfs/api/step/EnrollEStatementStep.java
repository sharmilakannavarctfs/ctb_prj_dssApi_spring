package com.ctfs.api.step;


import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.EnrollEStatementResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EnrollEStatementService;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class EnrollEStatementStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(EnrollEStatementStep.class);
	
	@Autowired
	private EnrollEStatement tS2RequestPojo;
	

    @Autowired
    private DashProfileManagerUtils dpm ; 
    
    @Autowired
    private EnrollEStatementService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("Post operation to hit enrollEStatement from TS2-service using valid {string} {string} {string} {string} and {string}")
	public void perform_post_enrollment(String cardNbr, 
			String custId,
			String electronicVendorOptionId,
			String op_id,
			String emailId) throws Throwable {
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
//			service.getAccount(getPayload(cardNo,dpm.getAccountID(),dpm.getCustomerId(),operID));
			service.enrollEStatement(getPayload(cardNbr,
					custId,
					electronicVendorOptionId,
					emailId,
					op_id));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    

	
	public EnrollEStatement getPayload(String cardNbr, 
			String custId,
			String electronicVendorOptionId, 
			String emailId, 
			String operator_id){
		if(cardNbr.equals("")) tS2RequestPojo.setCardNbr(null);
		else tS2RequestPojo.setCardNbr(cardNbr);
		if(emailId.equals("")) tS2RequestPojo.setEmailAddr(null);
		else tS2RequestPojo.setEmailAddr(emailId);
		if(custId.equals("")) tS2RequestPojo.setCustId(null);
		else tS2RequestPojo.setCustId(custId);
		if(operator_id.equals("")) tS2RequestPojo.setOperatorId(null);
		else tS2RequestPojo.setOperatorId(operator_id);
		if(electronicVendorOptionId.equals("")) tS2RequestPojo.setElectronicVendorOptionId(null);
		else tS2RequestPojo.setElectronicVendorOptionId(electronicVendorOptionId);
		return tS2RequestPojo; 
	}
	
	@Then("Validate enrollEstatement DSS api response status code as {string} and fault description as {string}")
	public void validate_get_Account_TestResponse(String statusCode,String desc) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("enrollEstatementresponse");
			EnrollEStatementResponsePojo res_obj = null;
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
			int code=response.getStatusCode();
			if(response.getStatusCode()==200){
				res_obj = response.getBody().as(EnrollEStatementResponsePojo.class);
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
}
