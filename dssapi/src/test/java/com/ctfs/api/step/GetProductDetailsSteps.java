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
//import com.ctfs.api.pojos.request.PortfolioDetailsRequestPojo;
//import com.ctfs.api.pojos.request.ProductDetailsRequestPojo;
//import com.ctfs.api.pojos.request.ProductTransactionsRequestPojo;
//import com.ctfs.api.pojos.response.PortfolioDetailsResponsePojo;
//import com.ctfs.api.pojos.response.ProductDetailsResponsePojo;
//import com.ctfs.api.pojos.response.ProductTransactionsResponsePojo;
//import com.ctfs.api.service.GetProductDetailsService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.pojos.T24ProductDetailsPojo;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24PortfolioDetailsDTO;
//import com.ctfs.temenos.dss.datobjects.T24ProductDetailsDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.*;
//import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetProductDetailsSteps extends AbstractStep {
//	
//	
//	private final Logger log = LoggerFactory.getLogger(GetProductTransactionsSteps.class);
//	@Autowired
//	private GetProductDetailsService getProductDetailsService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
////	T24ProductDetailsDTO t24ProductDetailsDTO;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public ProductDetailsRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return ProductDetailsRequestPojo.setProductDetailsRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getProductDetails DSS api using request payload")
//	public void perform_post_operation_to_hit_get_product_details_dss_api_using_request_payload(List<ProductDetailsRequestPojo> productDetailsRequestPojos) throws URISyntaxException{
//		try {
//			for(ProductDetailsRequestPojo productDetailsRequestPojo: productDetailsRequestPojos) {
//				getProductDetailsService.getProductDetails(productDetailsRequestPojo);
//			}
//			sessionContext.setProductDetailsRequests(productDetailsRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getProductDetails API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate {string} value in getProductDetails DSS api response should be {string}")
//	public void validate_value_in_get_product_details_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductDetailsService");
//			JsonPath jsonPath=new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field),value,"description value inside response is different");
//		} catch (Exception e) {
//			log.info("Error while matching descriptions and status values in getProductDetails API");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getProductDetails DSS api response status code as {string}")
//	public void validate_get_product_details_dss_api_response_status_code_as(String statusCode) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductDetailsService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getProductDetails API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getProductDetails DSS api response should match with schema")
//	public void validate_get_product_details_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductDetailsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getProductDetailsSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getProductDetails API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	@Then("Validate getProductDetails DSS api response")
//	public void validate_get_product_details_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (ProductDetailsRequestPojo productDetailsRequest : sessionContext.getProductDetailsRequests()) {
//				T24ProductDetailsDTO t24ProductDetailsDTO=transactUtil.retrieveProductDetails(requestParams);
//				ProductDetailsResponsePojo productDetailsResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductDetailsService"), ProductDetailsResponsePojo.class);
//				compareProductDetails(productDetailsResponsePojo,t24ProductDetailsDTO);
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing getProductDetails API's response with temenos API response");
//			Assert.fail(e.getMessage());
//		}	
//	}
//	
//	@Given("Perform post operation to hit getProductDetails DSS api without payload")
//	public void perform_post_operation_to_hit_get_producr_details_dss_api_without_payload() {
//		try {  
//			getProductDetailsService.getProductDetails("{}");
//		} catch (Exception e) {
//			log.info("Error while hitting getProductDetails API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getProductDetails DSS api error response should match with schema")
//	public void validate_get_product_details_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductDetailsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductDetails DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_product_details_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);          
//			getProductDetailsService.getProductDetails(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductDetails DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_product_details_dss_api_using_request_payload_with_expired_token(List<ProductDetailsRequestPojo> productDetailsPojos) {
//		try {
//			for(ProductDetailsRequestPojo productDetailsPojo: productDetailsPojos) {
//				getProductDetailsService.getProductDetailsWithToken(productDetailsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductDetails API using request Pojo with Expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductDetails DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_product_details_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<ProductDetailsRequestPojo> productDetailsPojos) {
//		try {
//			for(ProductDetailsRequestPojo productDetailsPojo: productDetailsPojos) {
//				getProductDetailsService.getProductDetailsWithToken(productDetailsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductDetails API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductDetails DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_product_details_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<ProductDetailsRequestPojo> productDetailsPojos) {
//		try {
//			for(ProductDetailsRequestPojo productDetailsPojo: productDetailsPojos) {
//				getProductDetailsService.getProductDetailsWithDisablePagination(productDetailsPojo, pagination );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductDetails API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	public void compareProductDetails(ProductDetailsResponsePojo productDetailsResponsePojo,T24ProductDetailsDTO t24ProductDetailsDTO){
//		for(int i=0;i<t24ProductDetailsDTO.getBody().size();i++){
//			
//			for(int j=0;j<t24ProductDetailsDTO.getBody().get(i).getCcySpecific().size();j++) { // loop start for ccySpecific
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCurrency()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCurrency());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCurrency(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCurrency(),"Failed to validate Currency");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterest()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterest());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterest(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterest(),"Failed to validate getCreditInterest");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestType()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestType());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestType(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestType(),"Failed to validate getCreditInterestType");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestMinimumBalance(),"Failed to validate getCreditInterestMinimumBalance");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestMaximumBalance(),"Failed to validate getCreditInterestMaximumBalance");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestMarginRate()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestMarginRate());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getCreditInterestMarginRate(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getCreditInterestMarginRate(),"Failed to validate getCreditInterestMarginRate");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterest()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterest());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterest(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterest(),"Failed to validate getDebitInterest");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterestType()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterestType());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterestType(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterestType(),"Failed to validate getDebitInterestType");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterestMinimumBalance(),"Failed to validate getDebitInterestMinimumBalance");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterestMarginRate()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterestMarginRate());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getDebitInterestMarginRate(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getDebitInterestMarginRate(),"Failed to validate getDebitInterestMarginRate");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getOverdraftAmount()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getOverdraftAmount());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getOverdraftAmount(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getOverdraftAmount(),"Failed to validate getOverdraftAmount");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMinimumAmount()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMinimumAmount());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMinimumAmount(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMinimumAmount(),"Failed to validate getMinimumAmount");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMaximumAmount()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMaximumAmount());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMaximumAmount(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMaximumAmount(),"Failed to validate getMaximumAmount");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getAmount()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getAmount());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getAmount(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getAmount(),"Failed to validate getAmount");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getNoticePeriod()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getNoticePeriod());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getNoticePeriod(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getNoticePeriod(),"Failed to validate getNoticePeriod");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getTerm()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getTerm());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getTerm(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getTerm(),"Failed to validate getTerm");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMinimumTerm()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMinimumTerm());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMinimumTerm(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMinimumTerm(),"Failed to validate getMinimumTerm");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMaximumTerm()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMaximumTerm());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getMaximumTerm(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getMaximumTerm(),"Failed to validate getMaximumTerm");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getPeriodicIndex()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getPeriodicIndex());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getPeriodicIndex(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getPeriodicIndex(),"Failed to validate getPeriodicIndex");
//				}
//				// loop start for InterestRates
//				for(int k=0;i<t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().size();k++) {
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierAmount()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierAmount());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierAmount(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierAmount(),"Failed to validate getCreditInterestTierAmount");
//					}
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierPercentage()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierPercentage());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierPercentage(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestTierPercentage(),"Failed to validate getCreditInterestTierPercentage");
//					}
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestRate()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestRate());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestRate(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getCreditInterestRate(),"Failed to validate getCreditInterestRate");
//					}
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierAmount()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierAmount());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierAmount(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierAmount(),"Failed to validate getDebitInterestTierAmount");
//					}
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierPercentage()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierPercentage());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierPercentage(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestTierPercentage(),"Failed to validate getDebitInterestTierPercentage");
//					}
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestRate()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestRate());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestRate(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getInterestRates().get(k).getDebitInterestRate(),"Failed to validate getDebitInterestRate");
//					}
//				} //loop end for interestRates
//				// loop started for fees
//				for(int k=0;i<t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getFees().size();k++) {
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFee()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getFees().get(k).getFee());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getFees().get(k).getFee(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFee(),"Failed to validate getFee");
//					}
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeAmount(),"Failed to validate getFeeAmount");
//					}
//					if(t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency()==null) {
//						Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency());
//					}
//					else {
//						Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency(), t24ProductDetailsDTO.getBody().get(i).getCcySpecific().get(j).getFees().get(k).getFeeFrequency(),"Failed to validate getFeeFrequency");
//					}
//				}// end of fees loop
//			}//loop end for ccySpecific
//			//loop start for facilities
//			for(int j=0;j<t24ProductDetailsDTO.getBody().get(i).getFacilities().size();j++) {
//				if(t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getService()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getService());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getService(), t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getService(),"Failed to validate getService");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getServiceAvailability()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getServiceAvailability());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getServiceAvailability(), t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getServiceAvailability(),"Failed to validate getServiceAvailability");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getDefaultOption()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getDefaultOption());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getDefaultOption(), t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getDefaultOption(),"Failed to validate getDefaultOption");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getCustomerOptions()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getCustomerOptions());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getCustomerOptions(), t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getCustomerOptions(),"Failed to validate getCustomerOptions");
//				}
//				if(t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getServiceAvailabiltyOptions()==null) {
//					Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getServiceAvailabiltyOptions());
//				}
//				else {
//					Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getFacilities().get(j).getServiceAvailabiltyOptions(), t24ProductDetailsDTO.getBody().get(i).getFacilities().get(j).getServiceAvailabiltyOptions(),"Failed to validate getServiceAvailabiltyOptions");
//				}
//			}// loop end for facilities
//			
//			if(t24ProductDetailsDTO.getBody().get(i).getProductId()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getProductId());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getProductId(), t24ProductDetailsDTO.getBody().get(i).getProductId(),"Failed to validate getProductId");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getProductGroupId()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getProductGroupId());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getProductGroupId(), t24ProductDetailsDTO.getBody().get(i).getProductGroupId(),"Failed to validate getProductGroupId");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getProductLine()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getProductLine());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getProductLine(), t24ProductDetailsDTO.getBody().get(i).getProductLine(),"Failed to validate getProductLine");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getProductDescription()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getProductDescription());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getProductDescription(), t24ProductDetailsDTO.getBody().get(i).getProductDescription(),"Failed to validate getProductDescription");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getProductLineDisplayName()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getProductLineDisplayName());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getProductLineDisplayName(), t24ProductDetailsDTO.getBody().get(i).getProductLineDisplayName(),"Failed to validate getProductLineDisplayName");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getProductGroupDisplayName()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getProductGroupDisplayName());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getProductGroupDisplayName(), t24ProductDetailsDTO.getBody().get(i).getProductGroupDisplayName(),"Failed to validate getProductGroupDisplayName");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getPrintingAttributePosition()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getPrintingAttributePosition());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getPrintingAttributePosition(), t24ProductDetailsDTO.getBody().get(i).getPrintingAttributePosition(),"Failed to validate getPrintingAttributePosition");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getProductCoCode()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getProductCoCode());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getProductCoCode(), t24ProductDetailsDTO.getBody().get(i).getProductCoCode(),"Failed to validate getProductCoCode");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getAvailableFromDate()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getAvailableFromDate());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getAvailableFromDate(), t24ProductDetailsDTO.getBody().get(i).getAvailableFromDate(),"Failed to validate getAvailableFromDate");
//			}
//			if(t24ProductDetailsDTO.getBody().get(i).getAvailableToDate()==null) {
//				Assert.assertNull(productDetailsResponsePojo.getProductDetails().get(i).getAvailableToDate());
//			}
//			else {
//				Assert.assertEquals(productDetailsResponsePojo.getProductDetails().get(i).getAvailableToDate(), t24ProductDetailsDTO.getBody().get(i).getAvailableToDate(),"Failed to validate getAvailableToDate");
//			}
//			
//		}// loop end for main for loop
//		
//	}//method brace
//	
//
//
//}
