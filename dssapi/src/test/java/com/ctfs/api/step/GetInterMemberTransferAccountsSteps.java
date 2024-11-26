package com.ctfs.api.step;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.service.GetInterMemberTransferAccountsService;
import com.ctfs.api.service.GetLinkedAccountsService;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetInterMemberTransferAccountsSteps extends AbstractStep {
	
	private final Logger log = LoggerFactory.getLogger(GetInterMemberTransferAccountsSteps.class);
	@Autowired
	private GetInterMemberTransferAccountsService getInterMemberTransferAccountsService;
	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	
	
	@Given("Perform post operation to hit getInterMemberTransferAccounts DSS api using {string} as customerNumber")
	public void perform_post_operation_to_hit_get_inter_member_transfer_accounts_dss_api_using_as_customer_number(String customerNumber) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put("customerNumber", customerNumber);   
			getInterMemberTransferAccountsService.getInterMemberTransferAccounts(requestPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Then("Validate getInterMemberTransferAccounts DSS api response status code as {int}")
	public void validate_get_inter_member_transfer_accounts_dss_api_response_status_code_as(Integer statusCode) {
		try {
			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("InterMemberTransferAccountsService");
			Assert.assertEquals(statusCode,  response.getStatusCode(),"FAILED!! Status code is not matching");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("Validate getInterMemberTransferAccounts DSS api response should contain {string}")
	public void validate_get_inter_member_transfer_accounts_dss_api_response_should_contain(String fields) {
		try {
			String[] fieldValues= fields.split(",");
		    for (String field : fieldValues) {
				System.out.println(field);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("Validate getInterMemberTransferAccounts DSS api response")
	public void validate_get_inter_member_transfer_accounts_dss_api_response() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("Validate {string} value in getInterMemberTransferAccounts DSS api response should be {string}")
	public void validate_value_in_get_inter_member_transfer_accounts_dss_api_response_should_be(String description, String value) {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("InterMemberTransferAccountsService");
			JsonPath jsonPath=new JsonPath(response.asString());
			Assert.assertEquals(jsonPath.getString("description"),value,"description value inside response is different");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Given("Perform post operation to hit getInterMemberTransferAccounts DSS api using {string} as customerABC")
	public void perform_post_operation_to_hit_get_inter_member_transfer_accounts_dss_api_using_as_customer_abc(String customerNumber) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put("customerABC", customerNumber);   
			getInterMemberTransferAccountsService.getInterMemberTransferAccounts(requestPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Given("Perform post operation to hit getInterMemberTransferAccounts DSS api without payload")
	public void perform_post_operation_to_hit_get_inter_member_transfer_accounts_dss_api_without_payload() {
		try {  
			getInterMemberTransferAccountsService.getInterMemberTransferAccounts("{}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
