package com.ctfs.api.step.ts2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.response.ts2response.GetCustomerPOJO;
import com.ctfs.api.service.ts2.InquireDisputeService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class InquireDisputeStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(InquireDisputeStep.class);

	@Autowired
	private TS2RequestPojo requestObj;

	static GetCustomerPOJO res_obj = null;

	@Autowired
	private InquireDisputeService service;

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	@Given("post operation to request inquireDispute api to get the inquireDisputeOutput with cardNbr {string} accountId {string} custId {string} decisionArea {string} onlyCurr {string} and operatorId {string}")
	public void post_operation(String cardNbr, 
			String accountId,
			String custId,
			String decisionArea,
			String onlyCurr,
			String op_id) throws Throwable {
		try {
			if(!cardNbr.equals("")) requestObj.setCardNbr(cardNbr);
			if(!op_id.equals("")) requestObj.setOperatorId(op_id);
			if(!accountId.equals("")) requestObj.setAccountId(accountId);
			if(!custId.equals("")) requestObj.setCustId(custId);
			if(!decisionArea.equals("")) requestObj.setDecisionArea(decisionArea);
			if(!onlyCurr.equals("")) requestObj.setOnlyCurr(Boolean.parseBoolean(onlyCurr));
			//			dpm.initializeTestProfile("group=ApiGeneric");
			service.post(requestObj);

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}


	@Then("validate the status code as {int} and inquireDisputeOutput is fetched with status {string} and desc {string}")
	public void validate_getCustomer(int statusCode,String status, String desc) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("inquireDisputeResponse");
			Assert.assertEquals(response.getStatusCode(),statusCode);
			//			int code=response.getStatusCode();
			if(response.getStatusCode()==200){
				res_obj = response.getBody().as(GetCustomerPOJO.class);
				//				Assert.assertEquals(response.getStatus(), status);

				if (res_obj.getStatus().equals("000")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "passed");
					if(res_obj.getFaults().size()!=0) {
						Assert.assertEquals(res_obj.getFaults().get(0).getDesc(), desc);
					}
					else Assert.assertNotNull(res_obj.getInquireDisputeOutput());
					
					
				}
				if (res_obj.getStatus().equals("999")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "failed");
//					Assert.assertTrue(res_obj.getFaults().size()!=0);
//					Assert.assertEquals(res_obj.getFaults().get(0).getDesc(), desc);
//					Assert.assertTrue(res_obj.getTriadInfoList().length==0);
				}
				if (res_obj.getStatus().equals("500")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "warning");
				}
			}

			} catch (Exception e) {			
				e.printStackTrace();
			}

		}
	}

