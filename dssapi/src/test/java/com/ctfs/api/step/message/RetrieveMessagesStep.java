package com.ctfs.api.step.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.message.RetirveMessagesReqpojo;
import com.ctfs.api.pojos.response.message.RetrieveMessagesResPojo;
import com.ctfs.api.service.message.RetrieveMessagesService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class RetrieveMessagesStep extends AbstractStep {
	private final Logger log = LoggerFactory.getLogger(RetrieveMessagesStep.class);

	@Autowired
	private RetirveMessagesReqpojo requestObj;

	static RetrieveMessagesResPojo response_obj;

	@Autowired
	private RetrieveMessagesService service;

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	@Given("post operation to retriveMessages request with targetidentifier as {string}")
	public void post_operation_to_retriveMessages_request_with_targetidentifier_as(String targetIdentifier)
			throws Throwable {

		requestObj.setTargetIdentifier(targetIdentifier);
		service.post(requestObj);
		log.info("request body: " + requestObj, true);
		setBody(requestObj);
	}

	private void setBody(RetirveMessagesReqpojo requestObj2) {
		// TODO Auto-generated method stub

	}

	@Then("validate the status code of the post operations as {int}")
	public void validate_addUpdateNbo(int statusCode) throws Throwable {
		try {

			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap()
					.get("retrieveMessagesresponse");
			Assert.assertEquals(response.statusCode(), statusCode);
			response_obj = response.getBody().as(RetrieveMessagesResPojo.class);
			Assert.assertTrue(response_obj.getMessages().size() > 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("post operation to retriveMessagesCnt request with Invalid targetidentifier as {string}")
	public void post_operation_to_retriveMessages_request_with_Invalid_targetidentifier_as(String targetIdentifier)
			throws Throwable {

		requestObj.setTargetIdentifier(targetIdentifier);
		service.post(requestObj);
		log.info("request body: " + requestObj, true);
		setBody(requestObj);
	}

	@Then("validate the status code and messages of the post operation as {int}")
	public void validate_messages_list(int statusCode) throws Throwable {
		try {

			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap()
					.get("retrieveMessagesresponse");
			Assert.assertEquals(response.statusCode(), statusCode);
			response_obj = response.getBody().as(RetrieveMessagesResPojo.class);
			Assert.assertEquals(response_obj.getMessages(), null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("post operation to retriveMessagesCnt request with Invalid payload")
	public void post_operation_to_retriveMessages_request_with_invalid_payload() throws Throwable {
		requestObj.setState("RED");
		service.post(requestObj);
		log.info("request body: " + requestObj, true);
		setBody(requestObj);
	}

	@Then("validate the status code for invalid payload message value of the post operation as {int}")
	public void validate_messages_for_invalidpayload(int statusCode) throws Throwable {
		try {

			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap()
					.get("retrieveMessagesresponse");
			Assert.assertEquals(response.statusCode(), statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
