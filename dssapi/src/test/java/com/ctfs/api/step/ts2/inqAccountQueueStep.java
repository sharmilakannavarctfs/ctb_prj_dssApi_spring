package com.ctfs.api.step.ts2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.model.response.Ts2ResponsePojo;
import com.ctfs.api.pojos.request.EnrollEStatement;
import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.response.GetAccount;
import com.ctfs.api.pojos.response.GetAccountInfo_res_pojo;
import com.ctfs.api.pojos.response.GetCustomerPOJO;
import com.ctfs.api.pojos.response.ts2response.InqAccountforCustomCodeResponse;
import com.ctfs.api.service.EStatementDeenrollmentService;
import com.ctfs.api.service.EvaluateCreditLimitService;
import com.ctfs.api.service.GetAccountInfoService;
import com.ctfs.api.service.GetCustomerIncomeInfoService;
import com.ctfs.api.service.GetCustomerService;
import com.ctfs.api.service.ts2.GetCustomerInfoService;
import com.ctfs.api.service.ts2.InqAccountQueueService;
import com.ctfs.api.service.ts2.InqAccountforCustomCodeService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class inqAccountQueueStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(inqAccountQueueStep.class);

	@Autowired
	private TS2RequestPojo requestObj;

	static GetCustomerPOJO res_obj = null;

	@Autowired
	private InqAccountQueueService service;

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	@Given("post operation to request inqAccountQueue api to get the custom code with cardNbr {string} "
			+ "accountId {string} custId {string} workflowid {string} and operatorId {string}")
	public void post_operation(String cardNbr, 
			String accountId,
			String custId,
			String workflowId,
			String op_id) throws Throwable {
		try {
			if(!cardNbr.equals("")) requestObj.setCardNbr(cardNbr);
			if(!op_id.equals("")) requestObj.setOperatorId(op_id);
			if(!accountId.equals("")) requestObj.setAccountId(accountId);
			if(!custId.equals("")) requestObj.setCustId(custId);
			if(!workflowId.equals("")) requestObj.setWorkflowId(workflowId);
			//			dpm.initializeTestProfile("group=ApiGeneric");
			service.post(requestObj);

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}


	@Then("validate the status code as {int} and values along with queue id is fetched with status {string} and desc {string}")
	public void validate_getCustomer(int statusCode,String status, String desc) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("inqAccountQueueResponse");
			Assert.assertEquals(response.getStatusCode(),statusCode);
			//			int code=response.getStatusCode();
			if(response.getStatusCode()==200){
				res_obj = response.getBody().as(GetCustomerPOJO.class);
				//				Assert.assertEquals(response.getStatus(), status);

				if (res_obj.getStatus().equals("000")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "passed");
					if(res_obj.getFaults()!=null)Assert.assertTrue(res_obj.getFaults().length==0);
//					Assert.assertNotNull(res_obj.getQueueId());
				}
				if (res_obj.getStatus().equals("999")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "failed");
					Assert.assertTrue(res_obj.getFaults().length!=0);
					Assert.assertEquals(res_obj.getFaults()[0].getDesc(), desc);
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

