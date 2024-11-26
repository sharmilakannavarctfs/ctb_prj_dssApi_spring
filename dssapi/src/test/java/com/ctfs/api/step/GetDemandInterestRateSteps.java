//package com.ctfs.api.step;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.testng.Assert;
//
//import com.ctfs.api.pojos.request.CustomerProductsRequestPojo;
//import com.ctfs.api.pojos.request.DemandInterestRateRequestPojo;
//import com.ctfs.api.pojos.request.GetPortfoliosRequestPojo;
//import com.ctfs.api.pojos.request.LinkedAccountsRequestPojo;
//import com.ctfs.api.pojos.response.DemandInterestRateResponsePojo;
//import com.ctfs.api.pojos.response.GetPortfoliosResponsePojo;
//import com.ctfs.api.pojos.response.LinkedAccountsResponsePojo;
//import com.ctfs.api.service.GetCustomerProductsService;
//import com.ctfs.api.service.GetDemandInterestRateService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24DemandInterestRateDTO;
//import com.ctfs.temenos.dss.datobjects.T24PortfoliosDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.*;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetDemandInterestRateSteps extends AbstractStep {
//	
//	private final Logger log = LoggerFactory.getLogger(GetDemandInterestRateSteps.class);
//	@Autowired
//	private GetDemandInterestRateService getDemandInterestRateService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public DemandInterestRateRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return DemandInterestRateRequestPojo.setDemandInterestRatesRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getDemandInterestRate DSS api using request payload")
//	public void perform_post_operation_to_hit_get_demand_interest_rate_dss_api_using_request_payload(List<DemandInterestRateRequestPojo> demandInterestRatesRequestPojos) {
//		try {
//			for(DemandInterestRateRequestPojo demandInterestRatesRequestPojo: demandInterestRatesRequestPojos) {
//				getDemandInterestRateService.getDemandInterestRates(demandInterestRatesRequestPojo);
//			}
//			sessionContext.setDemandInterestRatesRequests(demandInterestRatesRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandInterestRate API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandInterestRate DSS api response status code as {string}")
//	public void validate_get_demand_interest_rate_dss_api_response_status_code_as(String statusCode) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandInterestRateService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getDemandInterestRate API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandInterestRate DSS api response should match with schema")
//	public void validate_get_demand_interest_rate_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandInterestRateService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getDemandInterestRateSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getDemandInterestRate API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandInterestRate DSS api response")
//	public void validate_get_demand_interest_rate_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (DemandInterestRateRequestPojo demandInterestRateRequest : sessionContext.getDemandInterestRatesRequests()) {
//				T24DemandInterestRateDTO t24DemandInterestRateDTO=transactUtil.retrieveDemandInterestRate(requestParams);
//				DemandInterestRateResponsePojo demandInterestRateResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandInterestRateService"), DemandInterestRateResponsePojo.class);
//				
//				compareDemandInterestRate(demandInterestRateResponsePojo,t24DemandInterestRateDTO);
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing");
//			Assert.fail(e.getMessage());
//		}
//		
//	}
//
//	@Given("Perform post operation to hit getDemandInterestRate DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_demand_interest_rate_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);          
//			getDemandInterestRateService.getDemandInterestRates(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandInterestRate API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandInterestRate DSS api error response should match with schema")
//	public void validate_get_demand_interest_rate_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandInterestRateService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getDemandInterestRate API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate {string} value in getDemandInterestRate DSS api response should be {string}")
//	public void validate_value_in_get_demand_interest_rate_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandInterestRateService");
//			JsonPath jsonPath=new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field),value, field+" value inside response is different");
//		} catch (Exception e) {
//			log.info("Error while matching getDemandInterestRate API's error descriptions and status");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandInterestRate DSS api without payload")
//	public void perform_post_operation_to_hit_get_demand_interest_rate_dss_api_without_payload() {
//		try {  
//			getDemandInterestRateService.getDemandInterestRates("{}");
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandInterestRate API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandInterestRate DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_demand_interest_rate_dss_api_using_request_payload_with_expired_token(List<DemandInterestRateRequestPojo> demandInterestRatePojos) {
//		try {
//			for(DemandInterestRateRequestPojo demandInterestRatePojo: demandInterestRatePojos) {
//				getDemandInterestRateService.getDemandInterestRatesWithToken(demandInterestRatePojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandInterestRate API using request Pojo with Expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandInterestRate DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_demand_interest_rate_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<DemandInterestRateRequestPojo> demandInterestRatePojos) {
//		try {
//			for(DemandInterestRateRequestPojo demandInterestRatePojo: demandInterestRatePojos) {
//				getDemandInterestRateService.getDemandInterestRatesWithToken(demandInterestRatePojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandInterestRate API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandInterestRate DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_demand_interest_rate_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination,List<DemandInterestRateRequestPojo> demandInterestRatePojos) {
//		try {
//			for(DemandInterestRateRequestPojo demandInterestRatePojo: demandInterestRatePojos) {
//				getDemandInterestRateService.getDemandInterestRatesWithDisablePagination(demandInterestRatePojo, pagination );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandInterestRate API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	public void compareDemandInterestRate(DemandInterestRateResponsePojo demandInterestRateResponsePojo,T24DemandInterestRateDTO t24DemandInterestRateDTO) {
//		if(t24DemandInterestRateDTO.getBody().getInterestRate()==null) {
//			Assert.assertNull(demandInterestRateResponsePojo.getPeriodicInterestList().getInterestRate());
//		}
//		else {
//			Assert.assertEquals(demandInterestRateResponsePojo.getPeriodicInterestList().getInterestRate(),t24DemandInterestRateDTO.getBody().getInterestRate(),"Failed to validate InterestRate");
//		}
//		if(t24DemandInterestRateDTO.getBody().getNegativeInterestRate()==null) {
//			Assert.assertNull(demandInterestRateResponsePojo.getPeriodicInterestList().getNegativeInterestRate());
//		}
//		else {
//			Assert.assertEquals(demandInterestRateResponsePojo.getPeriodicInterestList().getNegativeInterestRate(),t24DemandInterestRateDTO.getBody().getNegativeInterestRate(),"Failed to validate NegativeInterestRate");
//		}
//	}
//
//}
