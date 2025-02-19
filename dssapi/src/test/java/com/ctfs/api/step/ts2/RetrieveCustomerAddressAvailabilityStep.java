package com.ctfs.api.step.ts2;

import com.ctfs.api.pojos.request.ts2.CustomerDetailsForAddressAvailabilityPojo;
import com.ctfs.api.pojos.request.ts2.RetrieveCustomerAddressAvailabilityRequestPojo;
import com.ctfs.api.pojos.response.GetCustomerPOJO;
import com.ctfs.api.pojos.response.ts2response.retrieveCustomerAddressAvailabilityResponse.RetrieveCustomerAddressAvailabilityErrorPojo;
import com.ctfs.api.pojos.response.ts2response.retrieveCustomerAddressAvailabilityResponse.RetrieveCustomerAddressAvailabilityResponsePojo;
import com.ctfs.api.service.ts2.RetrieveCustomerAddressAvailabilityService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RetrieveCustomerAddressAvailabilityStep extends AbstractStep {
    @Autowired
    private RetrieveCustomerAddressAvailabilityRequestPojo retrieveCustomerAddressAvailabilityRequestPojo;

    @Autowired
    private CustomerDetailsForAddressAvailabilityPojo customerDetailsForAddressAvailabilityPojo;
    static GetCustomerPOJO res_obj = null;
    @Autowired
    private RetrieveCustomerAddressAvailabilityService retrieveCustomerAddressAvailabilityService;
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;

    @Given("The user makes a post call to retrieveCustomerAddressAvailability API by passing {string}, {string}, {string}, {string}, {string} and {string}")
    public void postCallToRetrieveCustomerAddressAvailabilityAPI(String cardNum, String accountId, String customerId, String statusCode, String operationId, String restartToken) {
        try {
            List<String> cardNumList = Arrays.asList(cardNum.split(","));
            List<String> accountIdList = Arrays.asList(accountId.split(","));
            List<String> customerIdList = Arrays.asList(customerId.split(","));
            List<CustomerDetailsForAddressAvailabilityPojo> customerDetailsForAddressAvailabilityPojos = new ArrayList<>();
            for (int i = 0; i < cardNumList.size(); i++) {
                customerDetailsForAddressAvailabilityPojo.setAccountId(accountIdList.get(i).trim());
                customerDetailsForAddressAvailabilityPojo.setCardNbr(cardNumList.get(i).trim());
                customerDetailsForAddressAvailabilityPojo.setCustId(customerIdList.get(i).trim());
                customerDetailsForAddressAvailabilityPojo.setOperatorId(operationId);
                customerDetailsForAddressAvailabilityPojo.setRestartToken(restartToken);
                customerDetailsForAddressAvailabilityPojo.setStatusCode(statusCode);
                customerDetailsForAddressAvailabilityPojos.add(customerDetailsForAddressAvailabilityPojo);
            }
            retrieveCustomerAddressAvailabilityRequestPojo.setCustomerDetails(customerDetailsForAddressAvailabilityPojos);
            retrieveCustomerAddressAvailabilityService.postCall(retrieveCustomerAddressAvailabilityRequestPojo.getCustomerDetails());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("The user validates {string}, {string} and {string} from the success response message of retrieveCustomerAddressAvailability API")
    public void validateSuccessResponseForRetrieveCustomerAddressAvailabilityAPI(String statusCode, String statusMsg, String status) {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveCustomerAddressAvailability");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
        if (response.getStatusCode() == Integer.parseInt(statusCode)) {
            RetrieveCustomerAddressAvailabilityResponsePojo retrieveCustomerAddressAvailabilityResponsePojo = response.getBody().as(RetrieveCustomerAddressAvailabilityResponsePojo.class);
            Assert.assertEquals(retrieveCustomerAddressAvailabilityResponsePojo.getStatus(), status);
            Assert.assertEquals(retrieveCustomerAddressAvailabilityResponsePojo.getStatusMsg(), statusMsg);
        }
    }

    @Then("The user validates {string}, {string} and {string} from the error response message of retrieveCustomerAddressAvailability API")
    public void validateErrorResponseForRetrieveCustomerAddressAvailabilityAPI(String statusCode, String statusMsg, String status) {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveCustomerAddressAvailability");
        if (!statusCode.isEmpty())
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
        if (response.getStatusCode() == 200) {
            RetrieveCustomerAddressAvailabilityErrorPojo retrieveCustomerAddressAvailabilityErrorPojo = response.getBody().as(RetrieveCustomerAddressAvailabilityErrorPojo.class);
            Assert.assertEquals(retrieveCustomerAddressAvailabilityErrorPojo.getStatus(), status);
            Assert.assertEquals(retrieveCustomerAddressAvailabilityErrorPojo.getStatusMsg(), statusMsg);
        }
    }
}
