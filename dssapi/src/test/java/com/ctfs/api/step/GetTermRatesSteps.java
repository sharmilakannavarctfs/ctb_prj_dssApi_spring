package com.ctfs.api.step;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.service.telus.GetRegisteredPlanTransactionsService;
import com.ctfs.api.service.telus.GetTermRatesService;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class GetTermRatesSteps extends AbstractStep{

	private final Logger log = LoggerFactory.getLogger(GetTermRatesSteps.class);
	@Autowired
	private GetTermRatesService getTermRatesService; 
	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	
	@Given("Perform post operation to hit getTermRates DSS api using {string} as {string}")
	public void perform_post_operation_to_hit_get_term_rates_dss_api_using_as(String field, String value) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put(field, value);        
			getTermRatesService.getTermRates(requestPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Given("Perform post operation to hit getTermRates DSS api without payload")
	public void perform_post_operation_to_hit_get_term_rates_dss_api_without_payload() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Perform post operation to hit getTermRates DSS api using {string} as {string} with token does not match with Customer Number")
	public void perform_post_operation_to_hit_get_term_rates_dss_api_using_as_with_token_does_not_match_with_customer_number(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("Perform post operation to hit getTermRates DSS api using {string} as {string} with expired Token")
	public void perform_post_operation_to_hit_get_term_rates_dss_api_using_as_with_expired_token(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Validate getTermRates DSS api response status code as {int}")
	public void validate_get_term_rates_dss_api_response_status_code_as(Integer statusCode) {
		try {
			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermRatesService");
			Assert.assertEquals(statusCode,  response.getStatusCode(),"FAILED!! Status code is not matching");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("Validate getTermRates DSS api response should contain {string}")
	public void validate_get_term_rates_dss_api_response_should_contain(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Validate getTermRates DSS api response")
	public void validate_get_term_rates_dss_api_response() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Validate {string} value in getTermRates DSS api response should be {string}")
	public void validate_value_in_get_term_rates_dss_api_response_should_be(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	

	
}
