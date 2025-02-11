package com.ctfs.api.step.message;

import com.ctfs.api.pojos.request.message.DeleteMessageRequestPojo;
import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.service.message.DeleteMessageService;
import com.ctfs.common.service.StepDefinitionDataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteMessageStep {
    private final Logger log = LoggerFactory.getLogger(DeleteMessageStep.class);

    @Autowired
    private DeleteMessageRequestPojo requestObj;

    @Autowired
    private DeleteMessageService service;

    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;

    public String msgId="";

    @And("The user validates whether the created record is being deleted successfully by providing the messageId and targetIdentifier")
    public void validateSuccessfulDeleteMessageScenario() {

    }

    @When("The user makes post call to deleteMessage API and gets the response by passing messageId and {string}")
    public void theUserMakesPostCallToDeleteMessageAPIToGetTheResponse(String targetIdentifier) {
        try {
            if(!msgId.isEmpty()) requestObj.setMessageId(msgId);
            if(!targetIdentifier.isEmpty()) requestObj.setTargetIdentifier(targetIdentifier);
            service.post(requestObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}