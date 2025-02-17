package com.ctfs.api.service.message;

import com.ctfs.api.annotations.RetailBankService;
import com.ctfs.api.base.BaseExecutor;
import com.ctfs.api.endpoints.Endpoints;
import com.ctfs.common.service.RestService;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.common.utils.ApplicationProperties;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;

@RetailBankService
public class CreateDefinedMessageService extends BaseExecutor {
    protected CreateDefinedMessageService(RestService restService, ApplicationProperties applicationProperties) {
        super(restService, applicationProperties, "MESSAGESERVICE_V1");
    }

    private final Logger log = LoggerFactory.getLogger(CreateDefinedMessageService.class);

    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;

    public void post(Object requestbody) throws URISyntaxException {
        try {
            log.info("request body: {}", requestbody, true);
            setBody(requestbody);
            Response response = post(Endpoints.msg_createDefinedMessage);
            stepDefinitionDataManager.addToStoredObjectMap("createDefinedMessage", response);
            log.info("response getStatusLine: {}", response.getStatusLine());
            log.info("response getBody: {}", response.getBody().asPrettyString());

        } catch (Exception e) {
            log.info(e.getMessage(), "Error while hitting API with body alone");
        }
    }
}
