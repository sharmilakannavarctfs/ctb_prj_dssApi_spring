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
//import com.ctfs.api.pojos.request.DemandPeriodicLineFeaturesRequestPojo;
//import com.ctfs.api.pojos.request.ProductTransactionsRequestPojo;
//import com.ctfs.api.pojos.response.DemandPeriodicLineFeaturesResponsePojo;
//import com.ctfs.api.pojos.response.ProductTransactionsResponsePojo;
//import com.ctfs.api.service.GetCustomerProductsService;
//import com.ctfs.api.service.GetDemandPeriodicLineFeaturesService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24ProductConditionsDTO;
//import com.ctfs.temenos.dss.datobjects.T24ProductTransactionsDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.*;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetDemandPeriodicLineFeaturesSteps extends AbstractStep{
//	
//	private final Logger log = LoggerFactory.getLogger(GetDemandPeriodicLineFeaturesSteps.class);
//	@Autowired
//	private GetDemandPeriodicLineFeaturesService getDemandPeriodicLineFeaturesService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public DemandPeriodicLineFeaturesRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return DemandPeriodicLineFeaturesRequestPojo.setDemandPeriodicLineFeaturesRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload")
//	public void perform_post_operation_to_hit_get_demand_periodic_line_features_dss_api_using_request_payload(List<DemandPeriodicLineFeaturesRequestPojo> demandPeriodicLineFeaturesRequestPojos) {
//	    try {
//			for (DemandPeriodicLineFeaturesRequestPojo demandPeriodicLineFeaturesRequestPojo : demandPeriodicLineFeaturesRequestPojos) {
//				getDemandPeriodicLineFeaturesService.getDemandPeriodicLineFeatures(demandPeriodicLineFeaturesRequestPojo);
//			}
//			sessionContext.setDemandPeriodicLineFeaturesRequests(demandPeriodicLineFeaturesRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandPeriodicLineFeatures API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandPeriodicLineFeatures DSS api response status code as {string}")
//	public void validate_get_demand_periodic_line_features_dss_api_response_status_code_as(String statusCode) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandPeriodicLineFeaturesService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getDemandPeriodicLineFeatures API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandPeriodicLineFeatures DSS api response should match with schema")
//	public void validate_get_demand_periodic_line_features_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandPeriodicLineFeaturesService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getDemandPeriodicLineFeaturesSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getDemandPeriodicLineFeatures API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandPeriodicLineFeatures DSS api response")
//	public void validate_get_demand_periodic_line_features_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (DemandPeriodicLineFeaturesRequestPojo demandPeriodicLineFeaturesRequest : sessionContext.getDemandPeriodicLineFeaturesRequests()) {
//				T24ProductConditionsDTO t24ProductConditionsDTO=transactUtil.retrieveProductConditions(requestParams);
//				DemandPeriodicLineFeaturesResponsePojo demandPeriodicLineFeaturesResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandPeriodicLineFeaturesService"), DemandPeriodicLineFeaturesResponsePojo.class);
//				comparePDemandPeriodicLineFeatures(demandPeriodicLineFeaturesResponsePojo,t24ProductConditionsDTO);
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing the response of temenos and DSS");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate {string} value in getDemandPeriodicLineFeatures DSS api response should be {string}")
//	public void validate_value_in_get_demand_periodic_line_features_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandPeriodicLineFeaturesService");
//			JsonPath jsonPath=new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field),value,"description value inside response is different");
//		} catch (Exception e) {
//			log.info("Error while asserting the descriptions and DssStatusCode");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandPeriodicLineFeatures DSS api using {string} as {string} and {string} as {string}")
//	public void perform_post_operation_to_hit_get_demand_periodic_line_features_dss_api_using_as_and_as(String productIdValue, String productId, String currencyValue, String currency) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();
//			if(productIdValue!=null) {
//				requestPayload.put(productId, productIdValue);
//			}
//			if(currencyValue!=null) {
//				requestPayload.put(currency,currencyValue);
//			}
//			
//			getDemandPeriodicLineFeaturesService.getDemandPeriodicLineFeatures(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandPeriodicLineFeatures API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getDemandPeriodicLineFeatures DSS api error response should match with schema")
//	public void validate_get_demand_periodic_line_features_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("DemandPeriodicLineFeaturesService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getProductTransactions API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandPeriodicLineFeatures DSS api without payload")
//	public void perform_post_operation_to_hit_get_demand_periodic_line_features_dss_api_without_payload() {
//		try {  
//			getDemandPeriodicLineFeaturesService.getDemandPeriodicLineFeatures("{}");;
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandPeriodicLineFeatures API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_demand_periodic_line_features_dss_api_using_request_payload_with_expired_token(List<DemandPeriodicLineFeaturesRequestPojo> demandPeriodicLineFeaturesPojos) {
//		try {
//			for(DemandPeriodicLineFeaturesRequestPojo demandPeriodicLineFeaturesPojo: demandPeriodicLineFeaturesPojos) {
//				getDemandPeriodicLineFeaturesService.getDemandPeriodicLineFeaturesWithToken(demandPeriodicLineFeaturesPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandPeriodicLineFeatures API using request Pojo with Expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_demand_periodic_line_features_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<DemandPeriodicLineFeaturesRequestPojo> demandPeriodicLineFeaturesPojos) {
//		try {
//			for(DemandPeriodicLineFeaturesRequestPojo demandPeriodicLineFeaturesPojo: demandPeriodicLineFeaturesPojos) {
//				getDemandPeriodicLineFeaturesService.getDemandPeriodicLineFeaturesWithToken(demandPeriodicLineFeaturesPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandPeriodicLineFeatures API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_demand_periodic_line_features_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<DemandPeriodicLineFeaturesRequestPojo> demandPeriodicLineFeaturesPojos) {
//		try {
//			for(DemandPeriodicLineFeaturesRequestPojo demandPeriodicLineFeaturesPojo: demandPeriodicLineFeaturesPojos) {
//				getDemandPeriodicLineFeaturesService.getDemandPeriodicLineFeaturesWithDisablePagination(pagination, demandPeriodicLineFeaturesPojo);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandPeriodicLineFeatures API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	public void comparePDemandPeriodicLineFeatures(DemandPeriodicLineFeaturesResponsePojo demandPeriodicLineFeaturesResponsePojo,T24ProductConditionsDTO t24ProductConditionsDTO) {
//		for(int i=0;i<t24ProductConditionsDTO.getBody().size();i++) {
//			if(t24ProductConditionsDTO.getBody().get(i).getAvailableFromDate()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getAvailableFromDate());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getAvailableFromDate(),t24ProductConditionsDTO.getBody().get(i).getAvailableFromDate(),"Failed to validate AvailableFromDate");
//			}
//			//loop for ccySpecific Start
//			for(int j=0;j<t24ProductConditionsDTO.getBody().get(i).getCcySpecific().size();j++) {
//				if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getCurrency()==null) {
//					Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getCurrency());
//				}
//				else {
//					Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getCurrency(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getCurrency(),"Failed to validate getCurrency");
//				}
//				//loop starting for interests
//				for(int k=0;k<t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().size();k++) {
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getDayBasis()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getDayBasis());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getDayBasis(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getDayBasis(),"Failed to validate getDayBasis");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getDescription()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getDescription());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getDescription(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getDescription(),"Failed to validate getDescription");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getFloatingIndex()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getFloatingIndex());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getFloatingIndex(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getFloatingIndex(),"Failed to validate getFloatingIndex");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestId()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestId());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestId(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestId(),"Failed to validate getInterestId");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestType()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestType());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestType(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getInterestType(),"Failed to validate getInterestType");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginOperand()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginOperand());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginOperand(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginOperand(),"Failed to validate getMarginOperand");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginRate()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginRate());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginRate(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginRate(),"Failed to validate getMarginRate");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginType()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginType());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginType(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getMarginType(),"Failed to validate getMarginType");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getRateTierType()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getRateTierType());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getInterests().get(k).getRateTierType(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getInterests().get(k).getRateTierType(),"Failed to validate getRateTierType");
//					}
//				}//loop end of interests
//				//loop start for paymentSchedule
//				for(int k=0;k<t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().size();k++) {
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentFrequency()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentFrequency());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentFrequency(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentFrequency(),"Failed to validate getPaymentFrequency");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentMethod()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentMethod());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentMethod(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentMethod(),"Failed to validate getPaymentMethod");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentTypes()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentTypes());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentTypes(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getPaymentTypes(),"Failed to validate getPaymentTypes");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getProperty()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getProperty());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getProperty(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getProperty(),"Failed to validate getProperty");
//					}
//					if(t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getScheduleId()==null) {
//						Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getScheduleId());
//					}
//					else {
//						Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getScheduleId(),t24ProductConditionsDTO.getBody().get(i).getCcySpecific().get(j).getPaymentSchedule().get(k).getScheduleId(),"Failed to validate getScheduleId");
//					}
//				}//loop end for paymentSchedule
//			}//loop end for ccySpecific
//			if(t24ProductConditionsDTO.getBody().get(i).getProductCoCode()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductCoCode());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductCoCode(),t24ProductConditionsDTO.getBody().get(i).getProductCoCode(),"Failed to validate getProductCoCode");
//			}
//			if(t24ProductConditionsDTO.getBody().get(i).getProductDescription()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductDescription());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductDescription(),t24ProductConditionsDTO.getBody().get(i).getProductDescription(),"Failed to validate getProductDescription");
//			}
//			if(t24ProductConditionsDTO.getBody().get(i).getProductGroupDisplayName()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductGroupDisplayName());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductGroupDisplayName(),t24ProductConditionsDTO.getBody().get(i).getProductGroupDisplayName(),"Failed to validate getProductGroupDisplayName");
//			}
//			if(t24ProductConditionsDTO.getBody().get(i).getProductGroupId()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductGroupId());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductGroupId(),t24ProductConditionsDTO.getBody().get(i).getProductGroupId(),"Failed to validate getProductGroupId");
//			}
//			if(t24ProductConditionsDTO.getBody().get(i).getProductId()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductId());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductId(),t24ProductConditionsDTO.getBody().get(i).getProductId(),"Failed to validate getProductId");
//			}
//			if(t24ProductConditionsDTO.getBody().get(i).getProductLine()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductLine());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductLine(),t24ProductConditionsDTO.getBody().get(i).getProductLine(),"Failed to validate getProductLine");
//			}
//			if(t24ProductConditionsDTO.getBody().get(i).getProductLineDisplayName()==null) {
//				Assert.assertNull(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductLineDisplayName());
//			}
//			else {
//				Assert.assertEquals(demandPeriodicLineFeaturesResponsePojo.getPeriodicLineFeatures().get(i).getProductLineDisplayName(),t24ProductConditionsDTO.getBody().get(i).getProductLineDisplayName(),"Failed to validate getProductLineDisplayName");
//			}
//		}
//	}
//
//}
