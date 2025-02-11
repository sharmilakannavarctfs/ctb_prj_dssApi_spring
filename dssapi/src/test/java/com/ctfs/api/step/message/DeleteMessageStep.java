package com.ctfs.api.step.message;

import com.ctfs.api.pojos.request.message.DeleteMessageRequestPojo;
import com.ctfs.api.pojos.request.message.RetrieveMessageRequestPojo;
import com.ctfs.api.pojos.response.message.MessagesPojo;
import com.ctfs.api.pojos.response.message.RetrieveMessageResponsePojo;
import com.ctfs.api.service.message.DeleteMessageService;
import com.ctfs.api.service.message.RetrieveMessageService;
import com.ctfs.common.service.StepDefinitionDataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.ArrayList;

public class DeleteMessageStep {
    private final Logger log = LoggerFactory.getLogger(DeleteMessageStep.class);

    @Autowired
    private DeleteMessageRequestPojo deleteMessageRequestPojo;

    @Autowired
    private RetrieveMessageRequestPojo retrieveMessageRequestPojo;

    @Autowired
    private DeleteMessageService deleteMessageService;

    @Autowired
    private RetrieveMessageService retrieveMessageService;

    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;

    public String msgId = null;
    public int msgCnt = 0;

    @When("The user makes post call to deleteMessage API and gets the response by passing messageId and {string}")
    public void postCallToDeleteMessageAPI(String targetIdentifier) {
        try {
            if (!msgId.isEmpty()) deleteMessageRequestPojo.setMessageId(msgId);
            if (!targetIdentifier.isEmpty()) deleteMessageRequestPojo.setTargetIdentifier(targetIdentifier);
            deleteMessageService.post(deleteMessageRequestPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("The user validates whether the created record with {string} is being deleted successfully by verifying the {int} and the count of the number of messages")
    public void validateSuccessfulDeleteMessageScenario(String targetIdentifier, int statusCode) {
        int initialCount = msgCnt;
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("deleteMessage");
        Assert.assertEquals(response.getStatusCode(), statusCode);
        if (response.getStatusCode() == 200) {
            postCallToRetrieveMessageAPI(targetIdentifier);
            storeMessageCountBeforeCreatingMessage();
            int finalCount = msgCnt;
            System.out.println(initialCount + "    " + finalCount);
            Assert.assertNotEquals(initialCount, finalCount);
        }
    }

    @Given("The user makes a post call to retrieveMessage API by passing {string}")
    public void postCallToRetrieveMessageAPI(String targetIdentifier) {
        try {
            if (!targetIdentifier.isEmpty()) retrieveMessageRequestPojo.setTargetIdentifier(targetIdentifier);
            retrieveMessageService.post(retrieveMessageRequestPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("The user stores the retrieveMessage count before Creating dynamic message")
    public void storeMessageCountBeforeCreatingMessage() {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveMessages");
        if (response.getStatusCode() == 200) {
            RetrieveMessageResponsePojo retrieveMessageResponsePojo = response.getBody().as(RetrieveMessageResponsePojo.class);
            ArrayList<MessagesPojo> messages = retrieveMessageResponsePojo.getMessages();
            for (MessagesPojo message : messages) {
                System.out.println("ID : " + message.getId());
            }
            System.out.println(messages.size());
            msgCnt = messages.size();
            msgId = messages.get(msgCnt - 1).getId();
            System.out.println(msgId);
        }
    }
}