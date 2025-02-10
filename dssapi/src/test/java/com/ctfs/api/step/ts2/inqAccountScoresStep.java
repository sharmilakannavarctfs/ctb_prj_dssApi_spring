package com.ctfs.api.step.ts2;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.response.ts2response.AccountAlignedScores;
import com.ctfs.api.pojos.response.ts2response.InqAccountforCustomCodeResponse;
import com.ctfs.api.service.ts2.inqAccountScoresService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class inqAccountScoresStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(inqAccountScoresStep.class);

	@Autowired
	private TS2RequestPojo requestObj;

	static InqAccountforCustomCodeResponse res_obj = null;

	@Autowired
	private inqAccountScoresService service;

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	@Given("post operation to request inqAccountScores api to get the custom code with cardNbr {string} accountId {string} custId {string} ids {string} and operatorId {string}")
	public void post_operation(String cardNbr, 
			String accountId,
			String custId,
			String ids,
			String op_id) throws Throwable {
		try {
			if(!cardNbr.equals("")) requestObj.setCardNbr(cardNbr);
			if(!op_id.equals("")) requestObj.setOperatorId(op_id);
			if(!accountId.equals("")) requestObj.setAccountId(accountId);
			if(!custId.equals("")) requestObj.setCustId(custId);
			if(!ids.equals("")) {
				requestObj.setIds(ids.split(","));
			}
			//			dpm.initializeTestProfile("group=ApiGeneric");
			service.post(requestObj);

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}


	@Then("validate the status code as {int} and respective account score for the given ids {string} is fetched with status {string} and desc {string}")
	public void validate_getCustomer(int statusCode,String scores,String status, String desc) throws Throwable {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("inqAccountScoresResponse");
			Assert.assertEquals(response.getStatusCode(),statusCode);
			//			int code=response.getStatusCode();
			if(response.getStatusCode()==200){
				res_obj = response.getBody().as(InqAccountforCustomCodeResponse.class);
				//				Assert.assertEquals(response.getStatus(), status);

				if (res_obj.getStatus().equals("000")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "passed");
					if(res_obj.getFaults()!=null)Assert.assertTrue(res_obj.getFaults().size()==0);
					Assert.assertNotNull(res_obj.getAccountAlignedScores());
					String[] scores_arr = scores.split(",");
					for (int i=0 ; i<requestObj.getIds().length ; i++) {
						System.out.println(requestObj.getIds()[i]);
						
						 for (AccountAlignedScores aas : res_obj.getAccountAlignedScores())
						{
//							if (requestObj.getIds()[i].equals(res_obj.getAccountAlignedScores()[i].getId()))
								if (requestObj.getIds()[i].equals(aas.getId()))
								Assert.assertEquals(res_obj.getAccountAlignedScores()[i].getAlignedScore(),
										scores_arr[i]);
						}
					}
				}
				if (res_obj.getStatus().equals("999")) {
					Assert.assertEquals(res_obj.getStatusMsg(), "failed");
//					Assert.assertTrue(res_obj.getFaults().size()!=0);
//					Assert.assertEquals(res_obj.getFaults().get(0).getDesc(), desc);
					Assert.assertTrue(res_obj.getAccountAlignedScores().length==0);
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

