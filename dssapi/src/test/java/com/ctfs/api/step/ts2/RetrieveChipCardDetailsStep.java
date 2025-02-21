package com.ctfs.api.step.ts2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.ts2.RetrieveChipCardDetailsReqPojo;
import com.ctfs.api.pojos.response.ts2response.RetrieveChipCardDetailsResPojo;
import com.ctfs.api.service.ts2.RetreiveChipCardDetailsService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class RetrieveChipCardDetailsStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(RetrieveChipCardDetailsStep.class);

	@Autowired
	private RetrieveChipCardDetailsReqPojo requestObj;

	static RetrieveChipCardDetailsResPojo res_obj = null;

	@Autowired
	private RetreiveChipCardDetailsService service;

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	@Given("post operation to retrievechipcarddetails request {string} {string} {string} {string}")
	public void post_operation(String operatorId, String accountId, String cardNbr, String custId) throws Throwable {

		requestObj.setAccountId(accountId);
		requestObj.setCardNbr(cardNbr);
		requestObj.setCustId(custId);
		requestObj.setOperatorId(operatorId);
		requestObj.setRestartToken("7070");
		requestObj.setStatusCode("200");
		requestObj.setMostCurr(true);
		service.retrieveChipCardDetails(requestObj);
	}

	@Then("validate the retrievechipcarddetails api status code as {int} for the cardnumbers {string} and statusMsg as {string} {string}")
	public void validate(int statusCode, String custIds, String statusMsg, String status) throws Throwable {
		try {
			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap()
					.get("retrievechipdcarddetailsres");
			Assert.assertEquals(statusCode, response.statusCode());
			if (response.getStatusCode() == statusCode) {
				res_obj = response.getBody().as(RetrieveChipCardDetailsResPojo.class);
				if (res_obj != null && !statusMsg.equals("")) {
					Assert.assertEquals(res_obj.getStatusMsg(), statusMsg);
					Assert.assertEquals(res_obj.getStatus(), status);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
