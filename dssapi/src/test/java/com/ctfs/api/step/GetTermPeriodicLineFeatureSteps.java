package com.ctfs.api.step;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.DemandPeriodicLineFeaturesRequestPojo;
import com.ctfs.api.pojos.request.GetPortfoliosRequestPojo;
import com.ctfs.api.pojos.request.TermPeriodicLineFeatureRequestPojo;
import com.ctfs.api.pojos.response.GetPortfoliosResponsePojo;
import com.ctfs.api.pojos.response.TermPeriodicLineFeatureResponsePojo;
import com.ctfs.api.service.telus.GetTermPeriodicLineFeatureService;
import com.ctfs.api.session.SessionContext;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.temenos.api.util.TransactUtil;
import com.ctfs.temenos.dss.datobjects.T24PortfoliosDTO;
import com.ctfs.temenos.dss.datobjects.T24TermPeriodicLineFeatureDTO;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTermPeriodicLineFeatureSteps extends AbstractStep{
	
	private final Logger log = LoggerFactory.getLogger(GetTermPeriodicLineFeatureSteps.class);
	@Autowired
	private GetTermPeriodicLineFeatureService getTermPeriodicLineFeatureService;
	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	@Autowired
	SessionContext sessionContext;
	String token;
	Map<String, String> requestParams= new HashMap<>();
	
	@DataTableType
	public TermPeriodicLineFeatureRequestPojo getData(Map<String, String> entry) {
		requestParams.putAll(entry);
		return TermPeriodicLineFeatureRequestPojo.setTermPeriodicLineFeatureRequestPojo(entry);
	}
	
	@Given("Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload")
	public void perform_post_operation_to_hit_get_term_periodic_line_feature_dss_api_using_request_payload(List<TermPeriodicLineFeatureRequestPojo> termPeriodicLineFeatureRequestPojos) {
		 try {
				for (TermPeriodicLineFeatureRequestPojo termPeriodicLineFeatureRequestPojo : termPeriodicLineFeatureRequestPojos) {
					getTermPeriodicLineFeatureService.getTermPeriodicLineFeature(termPeriodicLineFeatureRequestPojo);
				}
			} catch (Exception e) {
				log.info("Error while hitting getTermPeriodicLineFeature API using request Pojo");
				Assert.fail(e.getMessage());
			}
	}
	
	@Given("Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload with expired Token")
	public void perform_post_operation_to_hit_get_term_periodic_line_feature_dss_api_using_request_payload_with_expired_token(List<TermPeriodicLineFeatureRequestPojo> termPeriodicLineFeatureRequestPojos) {
		try {
			for(TermPeriodicLineFeatureRequestPojo termPeriodicLineFeatureRequestPojo: termPeriodicLineFeatureRequestPojos) {
				getTermPeriodicLineFeatureService.getTermPeriodicLineFeatureWithToken(termPeriodicLineFeatureRequestPojo, token );
			}
		} catch (Exception e) {
			log.info("Error while hitting getTermPeriodicLineFeature API using request Pojo with Expired Token");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload with token does not match with Customer Number")
	public void perform_post_operation_to_hit_get_term_periodic_line_feature_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<TermPeriodicLineFeatureRequestPojo> termPeriodicLineFeatureRequestPojos) {
		try {
			for(TermPeriodicLineFeatureRequestPojo termPeriodicLineFeatureRequestPojo: termPeriodicLineFeatureRequestPojos) {
				getTermPeriodicLineFeatureService.getTermPeriodicLineFeatureWithToken(termPeriodicLineFeatureRequestPojo, token );
			}
		} catch (Exception e) {
			log.info("Error while hitting getTermPeriodicLineFeature API using request Pojo with token does not match with Customer Number");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload and disablePaginationheader as {string}")
	public void perform_post_operation_to_hit_get_term_periodic_line_feature_dss_api_using_request_payload_and_disable_paginationheader_as(List<TermPeriodicLineFeatureRequestPojo> termPeriodicLineFeatureRequestPojos,String pagination) {
		try {
			for(TermPeriodicLineFeatureRequestPojo termPeriodicLineFeatureRequestPojo: termPeriodicLineFeatureRequestPojos) {
				getTermPeriodicLineFeatureService.getTermPeriodicLineFeatureWithDisablePagination(termPeriodicLineFeatureRequestPojo, pagination );
			}
		} catch (Exception e) {
			log.info("Error while hitting getTermPeriodicLineFeature API using request Pojo with disablePaginationheader");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit getTermPeriodicLineFeature DSS api using {string} as {string} and {string} as {string}")
	public void perform_post_operation_to_hit_get_term_periodic_line_feature_dss_api_using_as_and_as(String value1, String field1, String value2, String field2) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put(field1, value1);  
			requestPayload.put(field2, value2);  
			getTermPeriodicLineFeatureService.getTermPeriodicLineFeature(requestPayload);
		} catch (Exception e) {
			log.info("Error while hitting getTermPeriodicLineFeature API using request Payload");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit getTermPeriodicLineFeature DSS api without payload")
	public void perform_post_operation_to_hit_get_term_periodic_line_feature_dss_api_without_payload() {
		try {
			Map<String, Object> requestPayload = new HashMap<>();                  
			getTermPeriodicLineFeatureService.getTermPeriodicLineFeature(requestPayload);
		} catch (Exception e) {
			log.info("Error while hitting getTermPeriodicLineFeature API without Payload");
			Assert.fail(e.getMessage());
		}
	}

	@Then("Validate getTermPeriodicLineFeature DSS api response status code as {string}")
	public void validate_get_term_periodic_line_feature_dss_api_response_status_code_as(String statusCode) {
		try {
			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermPeriodicLineFeatureService");
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode), "FAILED!! Status code is not matching");
		} catch (Exception e) {
			log.info("Error while comparing getTermPeriodicLineFeature API's status code");
			Assert.fail(e.getMessage());
		}
	}

	@Then("Validate getTermPeriodicLineFeature DSS api response should match with schema")
	public void validate_get_term_periodic_line_feature_dss_api_response_should_match_with_schema() {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermPeriodicLineFeatureService");
			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\getTermPeriodicLineFeatureSchema.json"));
		} catch (Exception e) {
			log.info("Error while comparing getTermPeriodicLineFeature API's response Schema");
			Assert.fail(e.getMessage());
		}
	}
	
	@Then("Validate getTermPeriodicLineFeature DSS api error response should match with schema")
	public void validate_get_term_periodic_line_feature_dss_api_error_response_should_match_with_schema() {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermPeriodicLineFeatureService");
			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
		} catch (Exception e) {
			log.info("Error while comparing getTermPeriodicLineFeature API's Error response Schema");
			Assert.fail(e.getMessage());
		}
	}

	@Then("Validate getTermPeriodicLineFeature DSS api response")
	public void validate_get_term_periodic_line_feature_dss_api_response() {
		try {
			TransactUtil transactUtil = new TransactUtil();
			T24TermPeriodicLineFeatureDTO t24termPeriodicLineFeatureDTO=transactUtil.retrieveTermPeriodicLineFeature(requestParams);
			TermPeriodicLineFeatureResponsePojo getTermPeriodicLineFeaturePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("TermPeriodicLineFeatureService"), TermPeriodicLineFeatureResponsePojo.class);
			compareTermPeriodicLineFeatureResponse(getTermPeriodicLineFeaturePojo,t24termPeriodicLineFeatureDTO);
		} catch (Exception e) {
			log.info("Error while comparing getTermPeriodicLineFeature API's response with temenos API response");
			Assert.fail(e.getMessage());
		}	
	}
	
	@Then("Validate {string} value in getTermPeriodicLineFeature DSS api response should be {string}")
	public void validate_value_in_get_term_periodic_line_feature_dss_api_response_should_be(String field, String value) {
		try {
			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("TermPeriodicLineFeatureService");
			JsonPath jsonPath= new JsonPath(response.asString());
			Assert.assertEquals(jsonPath.getString(field), value,field +" value mismatch Found");
		} catch (Exception e) {
			log.info("Error while comparing getTermPeriodicLineFeature API's Error response filed values");
			Assert.fail(e.getMessage());
		}	
	}

	private void compareTermPeriodicLineFeatureResponse(TermPeriodicLineFeatureResponsePojo dss,T24TermPeriodicLineFeatureDTO t24) {
		for(int i = 0; i < t24.getBody().size(); i++) {
			//loop for ccySpecific Start
			for(int j=0;j<t24.getBody().get(i).getCcySpecific().size();j++) {
				//loop for fees
				for(int k=0;k<t24.getBody().get(i).getCcySpecific().get(j).getFees().size();k++) {
					if(t24.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFee()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getFees().get(k).getFee());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getFees().get(k).getFee(),t24.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFee(),"Failed to validate Fee");
					}
					if(t24.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount(),t24.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount(),"Failed to validate Fee Amount");
					}
					if(t24.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency(),t24.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency(),"Failed to validate Fee Frequency");
					}
				}
				//Loop for Interest Rates
				if(t24.getBody().get(i).getCcySpecific().get(j).getInterestRates()!=null) {
				for(int l=0;l<t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().size();l++) {
					if(t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierAmount()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierAmount());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierAmount(),t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierAmount(),"Failed to validate Credit Interest TierAmount");
					}
					if(t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierPercentage()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierPercentage());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierPercentage(),t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestTierPercentage(),"Failed to validate Credit Interest TierPercentage");
					}
					if(t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestRate()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestRate());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestRate(),t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getCreditInterestRate(),"Failed to validate Credit Interest Rate");
					}
					if(t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierAmount()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierAmount());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierAmount(),t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierAmount(),"Failed to validate Debit Interest TierAmount");
					}
					if(t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierPercentage()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierPercentage());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierPercentage(),t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestTierPercentage(),"Failed to validate Debit Interest TierPercentage");
					}
					if(t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestRate()==null) {
						Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestRate());
					}
					else {
						Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestRate(),t24.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(l).getDebitInterestRate(),"Failed to validate Debit Interest Rate");
					}
				}
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getCurrency()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCurrency());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCurrency(),t24.getBody().get(i).getCcySpecific().get(j).getCurrency(),"Failed to validate Currency");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getCreditInterest()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterest());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterest(),t24.getBody().get(i).getCcySpecific().get(j).getCreditInterest(),"Failed to validate Credit Interest");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestType()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestType());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestType(),t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestType(),"Failed to validate Credit Interest Type");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance(),t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance(),"Failed to validate Credit Interest MinimumBalance");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance(),t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance(),"Failed to validate Credit Interest MaximumBalance");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestMarginRate()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestMarginRate());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getCreditInterestMarginRate(),t24.getBody().get(i).getCcySpecific().get(j).getCreditInterestMarginRate(),"Failed to validate Credit Interest MarginRate");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getDebitInterest()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterest());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterest(),t24.getBody().get(i).getCcySpecific().get(j).getDebitInterest(),"Failed to validate Debit Interest");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestType()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestType());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestType(),t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestType(),"Failed to validate Debit InterestType");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance(),t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance(),"Failed to validate Debit Interest MinimumBalance");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestMaximumBalance()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestMaximumBalance());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestMaximumBalance(),t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestMaximumBalance(),"Failed to validate Debit Interest MaximumBalance");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestMarginRate()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestMarginRate());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getDebitInterestMarginRate(),t24.getBody().get(i).getCcySpecific().get(j).getDebitInterestMarginRate(),"Failed to validate Debit Interest MarginRate");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getOverdraftAmount()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getOverdraftAmount());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getOverdraftAmount(),t24.getBody().get(i).getCcySpecific().get(j).getOverdraftAmount(),"Failed to validate Overdraft Amount");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getMinimumAmount()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMinimumAmount());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMinimumAmount(),t24.getBody().get(i).getCcySpecific().get(j).getMinimumAmount(),"Failed to validate Minimum Amount");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getMaximumAmount()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMaximumAmount());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMaximumAmount(),t24.getBody().get(i).getCcySpecific().get(j).getMaximumAmount(),"Failed to validate Maximum Amount");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getAmount()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getAmount());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getAmount(),t24.getBody().get(i).getCcySpecific().get(j).getAmount(),"Failed to validate Amount");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getNoticePeriod()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getNoticePeriod());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getNoticePeriod(),t24.getBody().get(i).getCcySpecific().get(j).getNoticePeriod(),"Failed to validate Notice Period");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getTerm()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getTerm());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getTerm(),t24.getBody().get(i).getCcySpecific().get(j).getTerm(),"Failed to validate Term");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getMinimumTerm()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMinimumTerm());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMinimumTerm(),t24.getBody().get(i).getCcySpecific().get(j).getMinimumTerm(),"Failed to validate Minimum Term");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getMaximumTerm()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMaximumTerm());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getMaximumTerm(),t24.getBody().get(i).getCcySpecific().get(j).getMaximumTerm(),"Failed to validate Maximum Term");
				}
				if(t24.getBody().get(i).getCcySpecific().get(j).getPeriodicIndex()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getPeriodicIndex());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getCcySpecific().get(j).getPeriodicIndex(),t24.getBody().get(i).getCcySpecific().get(j).getPeriodicIndex(),"Failed to validate Periodic Index");
				}
			}
			//Loop for facilities
			if(t24.getBody().get(i).getFacilities()!=null) {
			for(int m=0;m<t24.getBody().get(i).getFacilities().size();m++) {
				if(t24.getBody().get(i).getFacilities().get(m).getService()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getService());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getService(),t24.getBody().get(i).getFacilities().get(m).getService(),"Failed to validate Service");
				}
				if(t24.getBody().get(i).getFacilities().get(m).getServiceAvailability()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getServiceAvailability());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getServiceAvailability(),t24.getBody().get(i).getFacilities().get(m).getServiceAvailability(),"Failed to validate Service Availability");
				}
				if(t24.getBody().get(i).getFacilities().get(m).getDefaultOption()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getDefaultOption());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getDefaultOption(),t24.getBody().get(i).getFacilities().get(m).getDefaultOption(),"Failed to validate Default Option");
				}
				if(t24.getBody().get(i).getFacilities().get(m).getCustomerOptions()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getCustomerOptions());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getCustomerOptions(),t24.getBody().get(i).getFacilities().get(m).getCustomerOptions(),"Failed to validate Customer Options");
				}
				if(t24.getBody().get(i).getFacilities().get(m).getServiceAvailabiltyOptions()==null) {
					Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getServiceAvailabiltyOptions());
				}
				else {
					Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getFacilities().get(m).getServiceAvailabiltyOptions(),t24.getBody().get(i).getFacilities().get(m).getServiceAvailabiltyOptions(),"Failed to validate Service Availabilty Options");
				}
			}
			}
			if(t24.getBody().get(i).getProductId()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getProductId());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getProductId(),t24.getBody().get(i).getProductId(),"Failed to validate ProductId");
			}
			if(t24.getBody().get(i).getProductGroupId()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getProductGroupId());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getProductGroupId(),t24.getBody().get(i).getProductGroupId(),"Failed to validate Product GroupId");
			}
			if(t24.getBody().get(i).getProductLine()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getProductLine());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getProductLine(),t24.getBody().get(i).getProductLine(),"Failed to validate ProductLine");
			}
			if(t24.getBody().get(i).getProductDescription()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getProductDescription());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getProductDescription(),t24.getBody().get(i).getProductDescription(),"Failed to validate Product Description");
			}
			if(t24.getBody().get(i).getProductLineDisplayName()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getProductLineDisplayName());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getProductLineDisplayName(),t24.getBody().get(i).getProductLineDisplayName(),"Failed to validate Product Line DisplayName");
			}
			if(t24.getBody().get(i).getPrintingAttributePosition()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getPrintingAttributePosition());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getPrintingAttributePosition(),t24.getBody().get(i).getPrintingAttributePosition(),"Failed to validate Printing Attribute Position");
			}
			if(t24.getBody().get(i).getProductCoCode()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getProductCoCode());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getProductCoCode(),t24.getBody().get(i).getProductCoCode(),"Failed to validate Product CoCode");
			}
			if(t24.getBody().get(i).getAvailableFromDate()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getAvailableFromDate());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getAvailableFromDate(),t24.getBody().get(i).getAvailableFromDate(),"Failed to validate Available FromDate");
			}
			if(t24.getBody().get(i).getAvailableToDate()==null) {
				Assert.assertNull(dss.getGetTermPeriodicLineFeature().get(i).getAvailableToDate());
			}
			else {
				Assert.assertEquals(dss.getGetTermPeriodicLineFeature().get(i).getAvailableToDate(),t24.getBody().get(i).getAvailableToDate(),"Failed to validate Available ToDate");
			}
		}
		
	}
	
}
