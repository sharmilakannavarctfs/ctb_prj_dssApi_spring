package com.ctfs.api.step;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.service.telus.GetDemandRatesService;
import com.ctfs.common.service.StepDefinitionDataManager;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class GetDemandRatesSteps extends AbstractStep {
	
	
	private final Logger log = LoggerFactory.getLogger(GetDemandRatesSteps.class);
	@Autowired
	private GetDemandRatesService getDemandRatesService;
	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	
	
	@Given("Perform post operation to hit getDemandRates DSS api using {string} as category")
	public void perform_post_operation_to_hit_get_demand_rates_dss_api_using_as_category(String category) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put("category", category);   
			getDemandRatesService.getDemandRates(requestPayload);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Then("Validate getDemandRates DSS api response status code as {int}")
	public void validate_get_demand_rates_dss_api_response_status_code_as(Integer statusCode) {
		try {
			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandRatesService");
			Assert.assertEquals(statusCode,  response.getStatusCode(),"FAILED!! Status code is not matching");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
