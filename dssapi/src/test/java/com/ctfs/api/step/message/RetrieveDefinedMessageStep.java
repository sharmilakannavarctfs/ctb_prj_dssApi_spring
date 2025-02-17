package com.ctfs.api.step.message;

import com.ctfs.api.pojos.request.message.CreateDynamicMessageRequestPojo;
import com.ctfs.api.pojos.request.message.RetrieveDynamicMessageRequestPojo;
import com.ctfs.api.pojos.request.message.RetrieveMessageRequestPojo;
import com.ctfs.api.pojos.response.message.DeleteMessageErrorResponsePojo;
import com.ctfs.api.service.message.CreateDynamicMessageService;
import com.ctfs.common.service.StepDefinitionDataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class RetrieveDefinedMessageStep {
    private final Logger log = LoggerFactory.getLogger(RetrieveDefinedMessageStep.class);
    @Autowired
    private RetrieveDynamicMessageRequestPojo retrieveDynamicMessageRequestPojo;
    @Autowired
    private RetrieveMessageRequestPojo retrieveMessageRequestPojo;
    @Autowired
    private CreateDynamicMessageRequestPojo createDynamicMessageRequestPojo;
    @Autowired
    private CreateDynamicMessageService createDynamicMessageService;
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;
    @Given("The user makes post call to retrieveDefinedMessage API by passing {string} and {string}")
    public void postCallToRetrieveDefinedMessageAPI(String targetIdentifier, String msgId){
        try {
            if (!msgId.isEmpty()) retrieveDynamicMessageRequestPojo.setMessageId(msgId);
            if (!targetIdentifier.isEmpty()) retrieveDynamicMessageRequestPojo.setTargetIdentifier(targetIdentifier);
            createDynamicMessageService.post(retrieveDynamicMessageRequestPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("The user validates the message response from retrieveDefinedMessage API by comparing it with {int}")
    public void validateAPIResponseStatusCode(int statusCode) {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveDefinedMessage");
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Given("The user tries to make a post call to the retrieveDefinedMessage API without passing any payload")
    public void postCallToRetrieveDefinedMessageWithNoPayload() {
        try {
            createDynamicMessageService.post(retrieveDynamicMessageRequestPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("The user verifies the error response by validating {int} and {string} for retrieveDefinedMessage API")
    public void verifyStatusCodeAndDesc(int statusCode, String desc) {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveDefinedMessage");
        Assert.assertEquals(response.getStatusCode(), statusCode);
        if (response.getStatusCode() == statusCode) {
            DeleteMessageErrorResponsePojo deleteMessageErrorResponsePojo = response.getBody().as(DeleteMessageErrorResponsePojo.class);
            Assert.assertEquals(deleteMessageErrorResponsePojo.getDescription(), desc);
        }
    }
}
