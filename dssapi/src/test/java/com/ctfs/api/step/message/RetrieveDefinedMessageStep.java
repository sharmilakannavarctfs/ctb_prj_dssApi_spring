package com.ctfs.api.step.message;

import com.ctfs.api.pojos.request.message.CreateDefinedMessageRequestPojo;
import com.ctfs.api.pojos.request.message.RetrieveDefinedMessageRequestPojo;
import com.ctfs.api.pojos.request.message.RetrieveMessageRequestPojo;
import com.ctfs.api.pojos.response.message.CreateDefinedMessageResponsePojo;
import com.ctfs.api.pojos.response.message.MessageServiceErrorResponsePojo;
import com.ctfs.api.service.message.CreateDefinedMessageService;
import com.ctfs.api.service.message.RetrieveDefinedMessageService;
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
    private RetrieveDefinedMessageRequestPojo retrieveDefinedMessageRequestPojo;
    @Autowired
    private CreateDefinedMessageRequestPojo createDefinedMessageRequestPojo;
    @Autowired
    private RetrieveDefinedMessageService retrieveDefinedMessageService;
    @Autowired
    private CreateDefinedMessageService createDefinedMessageService;
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;
    private String msgId;

    @Given("The user makes post call to retrieveDefinedMessage API by passing messageId")
    public void postCallToRetrieveDefinedMessageAPI() {
        try {
            if (!msgId.isEmpty()) retrieveDefinedMessageRequestPojo.setMessageId(msgId);
            retrieveDefinedMessageService.post(retrieveDefinedMessageRequestPojo);
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
            retrieveDefinedMessageService.post(retrieveDefinedMessageRequestPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("The user verifies the error response by validating {int} and {string} for retrieveDefinedMessage API")
    public void verifyStatusCodeAndDesc(int statusCode, String desc) {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveDefinedMessage");
        Assert.assertEquals(response.getStatusCode(), statusCode);
        if (response.getStatusCode() == statusCode) {
            MessageServiceErrorResponsePojo messageServiceErrorResponsePojo = response.getBody().as(MessageServiceErrorResponsePojo.class);
            Assert.assertEquals(messageServiceErrorResponsePojo.getDescription(), desc);
        }
    }

    @Given("The user makes post call to createDefinedMessage API by passing {int}")
    public void postCallToCreateDefinedMessageAPI(int lifeSpan) {
        try {
            if (lifeSpan >= 0) createDefinedMessageRequestPojo.setLifeSpan(lifeSpan);
            System.out.println(createDefinedMessageRequestPojo);
            createDefinedMessageService.post(createDefinedMessageRequestPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("The user gets the msgId from the createDefinedMessage API response")
    public void storeMsgIdFromResponse() {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("createDefinedMessage");
        Assert.assertEquals(response.getStatusCode(), 200);
        if (response.getStatusCode() == 200) {
        System.out.println(response.getStatusCode());
            CreateDefinedMessageResponsePojo createDefinedMessageResponsePojo = response.getBody().as(CreateDefinedMessageResponsePojo.class);
            msgId = createDefinedMessageResponsePojo.getId();
            System.out.println("msgid >ks;dodL        "+msgId);
        }
    }
}
