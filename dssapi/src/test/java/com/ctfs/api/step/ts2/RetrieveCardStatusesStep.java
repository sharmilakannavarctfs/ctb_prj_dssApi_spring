package com.ctfs.api.step.ts2;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.ts2.RetrieveCardStatusesInputPojo;
import com.ctfs.api.pojos.response.ts2response.RetrieveCardStatus;
import com.ctfs.api.service.ts2.RetreiveCardStatusesService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class RetrieveCardStatusesStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(RetrieveCardStatusesStep.class);

	static RetrieveCardStatus res_obj = null;

	@Autowired
	private RetreiveCardStatusesService service;

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	@Given("post operation to retrievecardstatuses request {string} {string} {string} {string}")
	public void post_operation(String operatorId, String accountId, String cardNbr, String custId) throws Throwable {

		List<RetrieveCardStatusesInputPojo> retrivecardlist = new ArrayList<RetrieveCardStatusesInputPojo>();

		RetrieveCardStatusesInputPojo cardinput = new RetrieveCardStatusesInputPojo();
		cardinput.setAccountId(accountId);
		cardinput.setCardNbr(cardNbr);
		cardinput.setCustId(custId);
		cardinput.setOperatorId(operatorId);
		cardinput.setRestartToken("7070");
		retrivecardlist.add(cardinput);

		service.retrieveCardStatuses(retrivecardlist);
	}

	@Then("validate the retrievecardstatuses api status code as {int} for the cardnumbers {string} and statusMsg as {string} {string}")
	public void validate(int statusCode, String custIds, String statusMsg, String status) throws Throwable {
		try {
			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap()
					.get("retrievecardstatusesres");
			log.info("response body: "+ response);

			Assert.assertEquals(statusCode, response.statusCode());
			if (response.getStatusCode() == statusCode) {
				res_obj = response.getBody().as(RetrieveCardStatus.class);
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
