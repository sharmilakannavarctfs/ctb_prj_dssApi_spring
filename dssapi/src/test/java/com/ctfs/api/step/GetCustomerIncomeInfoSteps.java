package com.ctfs.api.step;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.pojos.response.GetCustomerPOJO;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.GetCustomerIncomeInfoService;
import com.ctfs.api.service.GetCustomerService;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class GetCustomerIncomeInfoSteps extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(GetCustomerIncomeInfoSteps.class);
	
	@Autowired
	private TS2RequestPojo requestObj;
	
	static GetCustomerPOJO res_obj = null;
    
    @Autowired
    private GetCustomerIncomeInfoService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to request getCustomerIncome api to get the customerIncome information on {string} {string} {string} and {string}")
	public void post_operation(String cardNbr, 
			String accountId,
			String custId,
			String op_id
			) throws Throwable {
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
			service.getCustomerIncomeInfo(getPayload(cardNbr,
				 	accountId,
				 	custId,
					op_id));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    

	
	public TS2RequestPojo getPayload(String cardNbr, 
			String accountId, 
			String custId, 
			String operator_id){
		if(cardNbr.equals("")) requestObj.setCardNbr(null);
		else requestObj.setCardNbr(cardNbr);
		
		if(operator_id.equals("")) requestObj.setOperatorId(null);
		else requestObj.setOperatorId(operator_id);
		
		if(accountId.equals("")) requestObj.setAccountId(null);
		else requestObj.setAccountId(accountId);
		
		if(custId.equals("")) requestObj.setCustId(null);
		else requestObj.setCustId(custId);
		
		return requestObj; 
	}
	
	@Then("validate the status code as {string} and customer income info is fetched with status {string} and desc {string}")
	public void validate_getCustomer(String statusCode,String status, String desc) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("getCustomerIncomeInfoRes");
			
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
//			int code=response.getStatusCode();
			if(response.getStatusCode()==200){
				res_obj = response.getBody().as(GetCustomerPOJO.class);
//				Assert.assertEquals(response.getStatus(), status);

				if (res_obj.getStatus().equals("000")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "passed");
					Assert.assertTrue(res_obj.getFaults().length==0);
					Assert.assertTrue(res_obj.getDateLastMonthlyIncome()!=null);
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
