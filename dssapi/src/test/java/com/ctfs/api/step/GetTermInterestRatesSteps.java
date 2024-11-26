//package com.ctfs.api.step;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.testng.Assert;
//
//import com.ctfs.api.pojos.request.GetTermInterestRatesRequestPojo;
//import com.ctfs.api.pojos.response.GetTermInterestRatesResponsePojo;
//import com.ctfs.api.service.GetTermInterestRatesService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24TermInterestRatesDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetTermInterestRatesSteps extends AbstractStep{
//	
//	private final Logger log = LoggerFactory.getLogger(GetTermInterestRatesSteps.class);
//	@Autowired
//	private GetTermInterestRatesService getTermInterestRatesService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public GetTermInterestRatesRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return GetTermInterestRatesRequestPojo.setTermInterestRatesRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getTermInterestRates DSS api using request payload")
//	public void perform_post_operation_to_hit_get_term_interest_rates_dss_api_using_request_payload(List<GetTermInterestRatesRequestPojo> interestRatesRequestPojos) {
//		try {
//			for(GetTermInterestRatesRequestPojo interestRatesRequestPojo: interestRatesRequestPojos) {
//				getTermInterestRatesService.getTermInterestRates(interestRatesRequestPojo);			
//			}
//			sessionContext.setTermInterestRatesRequests(interestRatesRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getTermInterestRates API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getTermInterestRates DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_term_interest_rates_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);
//			getTermInterestRatesService.getTermInterestRates(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getTermInterestRates API using request payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getTermInterestRates DSS api without payload")
//	public void perform_post_operation_to_hit_get_term_interest_rates_dss_api_without_payload() {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			getTermInterestRatesService.getTermInterestRates(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getTermInterestRates API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getTermInterestRates DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_term_interest_rates_dss_api_using_request_payload_with_expired_token(List<GetTermInterestRatesRequestPojo> interestRatesRequestPojos) {
//		try {
//			for(GetTermInterestRatesRequestPojo interestRatesRequestPojo: interestRatesRequestPojos) {
//				getTermInterestRatesService.getTermInterestRatesWithToken(interestRatesRequestPojo,token);			
//			}
//			sessionContext.setTermInterestRatesRequests(interestRatesRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getTermInterestRates API using request Pojo with expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getTermInterestRates DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_term_interest_rates_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<GetTermInterestRatesRequestPojo> interestRatesRequestPojos) {
//		try {
//			for(GetTermInterestRatesRequestPojo interestRatesRequestPojo: interestRatesRequestPojos) {
//				getTermInterestRatesService.getTermInterestRatesWithToken(interestRatesRequestPojo,token);			
//			}
//			sessionContext.setTermInterestRatesRequests(interestRatesRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getTermInterestRates API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getTermInterestRates DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_term_interest_rates_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<GetTermInterestRatesRequestPojo> interestRatesRequestPojos) {
//		try {
//			for(GetTermInterestRatesRequestPojo interestRatesRequestPojo: interestRatesRequestPojos) {
//				getTermInterestRatesService.getTermInterestRatesWithDisablePagination(interestRatesRequestPojo, pagination);		
//			}
//			sessionContext.setTermInterestRatesRequests(interestRatesRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getTermInterestRates API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getTermInterestRates DSS api response status code as {string}")
//	public void validate_get_term_interest_rates_dss_api_response_status_code_as(String statusCode) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermInterestRatesService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getTermInterestRates API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getTermInterestRates DSS api response should match with schema")
//	public void validate_get_term_interest_rates_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermInterestRatesService");
//			response.then().log().all().assertThat().body(matchesJsonSchemaInClasspath("Schema\\getTermInterestRatesSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getTermInterestRates API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getTermInterestRates DSS api error response should match with schema")
//	public void validate_get_term_interest_rates_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("TermInterestRatesService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getTermInterestRates API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getTermInterestRates DSS api response")
//	public void validate_get_term_interest_rates_dss_api_response() throws URISyntaxException {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (GetTermInterestRatesRequestPojo ProductInterestRatesRequest : sessionContext.getTermInterestRatesRequests()) {
//				T24TermInterestRatesDTO t24ProductInterestRatesDTO=transactUtil.retrieveTermInterestRates(requestParams);
//				GetTermInterestRatesResponsePojo dssResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("TermInterestRatesService"), GetTermInterestRatesResponsePojo.class);
//				compareTermInterestRate(dssResponsePojo,t24ProductInterestRatesDTO);
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing getTermInterestRates API's response with temenos API response");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate {string} value in getTermInterestRates DSS api response should be {string}")
//	public void validate_value_in_get_term_interest_rates_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("TermInterestRatesService");
//			JsonPath jsonPath= new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field), value,field +" value mismatch Found");
//		} catch (Exception e) {
//			log.info("Error while comparing getTermInterestRates API's Error response filed values");
//			Assert.fail(e.getMessage());
//		}	
//	}
//	
//	public void compareTermInterestRate(GetTermInterestRatesResponsePojo dssResponsePojo,T24TermInterestRatesDTO t24ProductInterestRatesDTO) {
//		try {
//			for(int i=0;i<t24ProductInterestRatesDTO.getBody().size();i++) {
//				if(t24ProductInterestRatesDTO.getBody().get(i).getAmount()==null) {
//					Assert.assertNull(dssResponsePojo.getGetInterestRates().get(i).getAmount());
//				}else {
//					Assert.assertEquals(dssResponsePojo.getGetInterestRates().get(i).getAmount(), t24ProductInterestRatesDTO.getBody().get(i).getAmount(),"Failed to validate Amount");
//				}
//				if(t24ProductInterestRatesDTO.getBody().get(i).getInterestRate()==null) {
//					Assert.assertNull(dssResponsePojo.getGetInterestRates().get(i).getInterestRate());
//				}else {
//					Assert.assertEquals(dssResponsePojo.getGetInterestRates().get(i).getInterestRate(), t24ProductInterestRatesDTO.getBody().get(i).getInterestRate(),"Failed to validate Interest Rate");
//				}
//				if(t24ProductInterestRatesDTO.getBody().get(i).getTerm()==null) {
//					Assert.assertNull(dssResponsePojo.getGetInterestRates().get(i).getTerm());
//				}else {
//					Assert.assertEquals(dssResponsePojo.getGetInterestRates().get(i).getTerm(), t24ProductInterestRatesDTO.getBody().get(i).getTerm(),"Failed to validate Term");
//				}
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing response of DSS and Temenos");
//			Assert.fail(e.getMessage());
//		}
//	}
//}
//
