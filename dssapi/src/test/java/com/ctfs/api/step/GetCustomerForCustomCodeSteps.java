package com.ctfs.api.step;


import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.GetCustomerforCustomCodereqPojo;
import com.ctfs.api.pojos.request.TS2RequestPojo;
import com.ctfs.api.pojos.request.Ts2custDetailInput;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.pojos.response.GetCustomerPOJO;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.GetCustomerForCustomCodeService;
import com.ctfs.api.service.GetCustomerIncomeInfoService;
import com.ctfs.api.service.GetCustomerService;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class GetCustomerForCustomCodeSteps extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(GetCustomerForCustomCodeSteps.class);
	
	@Autowired
	private GetCustomerforCustomCodereqPojo requestObj;
	
	static GetCustomerPOJO res_obj = null;
    
    @Autowired
    private GetCustomerForCustomCodeService service;
    
    @Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
    
    @Given("post operation to request getCustomerForCustomCode api to get the customercode information on {string} {string} {string} and {string}")
	public void post_operation(String cardNbr, 
			String accountId,
			String custId,
			String op_id
			) throws Throwable {
		try {
//			dpm.initializeTestProfile("group=ApiGeneric");
			if(op_id.equals("")) requestObj.setOperatorId(op_id);
			else requestObj.setOperatorId(op_id);
			Ts2custDetailInput custDetails = getTs2CustDetailInput(cardNbr,
				 	custId);
			ArrayList<Ts2custDetailInput> list = new ArrayList<Ts2custDetailInput>();
			list.add(custDetails);
			requestObj.setTs2custDetailInputs(list);
			System.out.println("Check");
			service.getCustomCode(requestObj);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
    

	
	public Ts2custDetailInput getTs2CustDetailInput(String cardNbr, 
			String custId){
		
				/*{
		  "operatorId": "string",
		  "ts2custDetailInputs": [
		    {
		      "cardNbr": "string",
		      "custId": "string"
		    }
		  ]
		}*/
		Ts2custDetailInput custDetail = new Ts2custDetailInput() ;
		
		if(cardNbr.equals(""))  custDetail.setCardNbr(null);
		else {
			custDetail.setCardNbr(cardNbr);
		}
		
		
		
		if(custId.equals("")) custDetail.setCustId(null);
		else {
			custDetail.setCustId(custId);
		}
		
		return custDetail; 
	}
	
	@Then("validate the status code as {string} and customer Custom Codes info is fetched with status {string} and desc {string}")
	public void validate_getCustomer(String statusCode,String status, String desc) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("getCustomerForCustomCode");
			System.out.println(response.statusCode());
			Assert.assertTrue(statusCode.equals(String.valueOf(response.getStatusCode())));
//			int code=response.getStatusCode();
			if(response.getStatusCode()==200){
				res_obj = response.getBody().as(GetCustomerPOJO.class);
//				Assert.assertEquals(response.getStatus(), status);

				if (res_obj.getStatus().equals("000")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "passed");
					Assert.assertTrue(res_obj.getFaults().length==0);
					Assert.assertTrue(res_obj.getCustomData().get(0).getCodes().size()!=0);
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
