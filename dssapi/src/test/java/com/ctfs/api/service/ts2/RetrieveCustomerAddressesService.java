package com.ctfs.api.service.ts2;

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
public class RetrieveCustomerAddressesService extends BaseExecutor {
    protected RetrieveCustomerAddressesService(RestService restService, ApplicationProperties applicationProperties) {
        super(restService, applicationProperties, "TS2SERVICE_V1");
    }

    private final Logger log = LoggerFactory.getLogger(RetrieveCustomerAddressesService.class);

    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;

    public void postCall(Object requestbody) throws URISyntaxException {
        try {
            log.info("request body: " + requestbody, true);
            setBody(requestbody);
            Response response = post(Endpoints.ts2_retrieveCustomerAddresses);
            stepDefinitionDataManager.addToStoredObjectMap("retrieveCustomerAddresses", response);
            log.info("response getStatusLine: " + response.getStatusLine());
            log.info("response getBody: " + response.getBody().asPrettyString());

        } catch (Exception e) {
            log.info(e.getMessage(), "Error while hitting API with body alone");
        }
    }
}
