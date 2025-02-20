package com.ctfs.api.step.ts2;

import com.ctfs.api.pojos.request.ts2.RetrieveCustomerAddressesRequestPojo;
import com.ctfs.api.pojos.response.GetCustomerPOJO;
import com.ctfs.api.pojos.response.ts2response.CustomerAddressErrorPojo;
import com.ctfs.api.pojos.response.ts2response.RetrieveCustomerAddressesResponsePojo;
import com.ctfs.api.service.ts2.RetrieveCustomerAddressesService;
import com.ctfs.api.step.AbstractStep;
import com.ctfs.common.service.StepDefinitionDataManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class RetrieveCustomerAddressesStep extends AbstractStep {
    @Autowired
    private RetrieveCustomerAddressesRequestPojo retrieveCustomerAddressesRequestPojo;
    static GetCustomerPOJO res_obj = null;
    @Autowired
    private RetrieveCustomerAddressesService retrieveCustomerAddressesService;
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;

    @Given("The user makes a post call to retrieveCustomerAddresses API by passing {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void postCallToRetrieveCustomerAddressesAPI(String cardNum, String accountId, String customerId, String statusCode, String operatorId, String addrType, String restartToken) {
        try {
            retrieveCustomerAddressesRequestPojo.setOperatorId(operatorId.isEmpty() ? null : operatorId.equals("null") ? "" : operatorId);
            retrieveCustomerAddressesRequestPojo.setCardNbr(cardNum.isEmpty() ? null : cardNum);
            retrieveCustomerAddressesRequestPojo.setAccountId(accountId.isEmpty() ? null : accountId);
            retrieveCustomerAddressesRequestPojo.setCustId(customerId.isEmpty() ? null : customerId);
            retrieveCustomerAddressesRequestPojo.setStatusCode(statusCode.isEmpty() ? null : statusCode);
            retrieveCustomerAddressesRequestPojo.setAddrType(addrType.isEmpty() ? null : addrType);
            retrieveCustomerAddressesRequestPojo.setRestartToken(restartToken.isEmpty() ? null : restartToken);
            retrieveCustomerAddressesService.postCall(retrieveCustomerAddressesRequestPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("The user validates {string}, {string} and {string} from the success response message of retrieveCustomerAddresses API")
    public void validateSuccessResponseForRetrieveCustomerAddressesAPI(String statusCode, String statusMsg, String status) {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveCustomerAddresses");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
        if (response.getStatusCode() == Integer.parseInt(statusCode)) {
            RetrieveCustomerAddressesResponsePojo retrieveCustomerAddressesResponsePojo = response.getBody().as(RetrieveCustomerAddressesResponsePojo.class);
            Assert.assertEquals(retrieveCustomerAddressesResponsePojo.getStatus(), status);
            Assert.assertEquals(retrieveCustomerAddressesResponsePojo.getStatusMsg(), statusMsg);
        }
    }

    @Then("The user validates {string}, {string} and {string} from the error response message of retrieveCustomerAddresses API")
    public void validateErrorResponseForRetrieveCustomerAddressesAPI(String statusCode, String statusMsg, String status) {
        Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("retrieveCustomerAddresses");
        if (!statusCode.isEmpty()) {
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
            CustomerAddressErrorPojo customerAddressErrorPojo = response.getBody().as(CustomerAddressErrorPojo.class);
            Assert.assertEquals(customerAddressErrorPojo.getStatus(), status);
            Assert.assertEquals(customerAddressErrorPojo.getStatusMsg(), statusMsg);

        } else {
            Assert.assertEquals(response.getStatusCode(), 200);
            CustomerAddressErrorPojo customerAddressErrorPojo = response.getBody().as(CustomerAddressErrorPojo.class);
            Assert.assertEquals(customerAddressErrorPojo.getStatus(), status);
            Assert.assertEquals(customerAddressErrorPojo.getStatusMsg(), statusMsg);

        }
    }

    @Given("The user makes a post call to retrieveCustomerAddresses API without any payload")
    public void postCallWithNoPayload() {
        try {
            retrieveCustomerAddressesService.postCall("{}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}