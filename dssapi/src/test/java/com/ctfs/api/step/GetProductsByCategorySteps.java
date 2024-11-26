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
//import com.ctfs.api.pojos.request.ProductsByCategoryRequestPojo;
//import com.ctfs.api.pojos.response.GetProductsByCategoryResponsePojo;
//import com.ctfs.api.service.GetProductsByCategoryService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24ProductsByCategoryDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetProductsByCategorySteps extends AbstractStep{
//
//	private final Logger log = LoggerFactory.getLogger(GetProductsByCategorySteps.class);
//	@Autowired
//	private GetProductsByCategoryService getProductsByCategoryService; 
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public ProductsByCategoryRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return ProductsByCategoryRequestPojo.setProductsByCategoryRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getProductsByCategory DSS api using request payload")
//	public void perform_post_operation_to_hit_get_products_by_category_dss_api_using_request_payload(List<ProductsByCategoryRequestPojo> productsByCategoryPojos) {
//		try {
//			for(ProductsByCategoryRequestPojo productsByCategoryPojo: productsByCategoryPojos) {
//				getProductsByCategoryService.getProductsByCategory(productsByCategoryPojo);
//			}
//			sessionContext.setProductsByCategoryRequest(productsByCategoryPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getProductsByCategory API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductsByCategory DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_products_by_category_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);        
//			getProductsByCategoryService.getProductsByCategory(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getProductsByCategory API using request payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductsByCategory DSS api without payload")
//	public void perform_post_operation_to_hit_get_products_by_category_dss_api_without_payload() {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();              
//			getProductsByCategoryService.getProductsByCategory(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getProductsByCategory API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductsByCategory DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_products_by_category_dss_api_using_request_payload_with_expired_token(List<ProductsByCategoryRequestPojo> productsByCategoryPojos) {
//		try {
//			for(ProductsByCategoryRequestPojo productsByCategoryPojo: productsByCategoryPojos) {
//				getProductsByCategoryService.getProductsByCategoryWithToken(productsByCategoryPojo,token);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductsByCategory API using request Pojo with expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getProductsByCategory DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_products_by_category_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<ProductsByCategoryRequestPojo> productsByCategoryPojos) {
//		try {
//			for(ProductsByCategoryRequestPojo productsByCategoryPojo: productsByCategoryPojos) {
//				getProductsByCategoryService.getProductsByCategoryWithToken(productsByCategoryPojo,token);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductsByCategory API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductsByCategory DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_products_by_category_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<ProductsByCategoryRequestPojo> productsByCategoryPojos) {
//		try {
//			for(ProductsByCategoryRequestPojo productsByCategoryPojo: productsByCategoryPojos) {
//				getProductsByCategoryService.getProductsByCategoryWithDisablePagination(productsByCategoryPojo,pagination);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductsByCategory API using request Pojo with disable Pagination header");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getProductsByCategory DSS api response status code as {string}")
//	public void validate_get_products_by_category_dss_api_response_status_code_as(String statusCode) {
//	    try {
//			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductsByCategoryService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),  "FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getProductsByCategory API's Status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getProductsByCategory DSS api response should match with schema")
//	public void validate_get_products_by_category_dss_api_response_should_match_with_schema() {
//		try {
//			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductsByCategoryService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\GetProductsSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getProductsByCategory API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getProductsByCategory DSS api error response should match with schema")
//	public void validate_get_products_by_category_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductsByCategoryService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getProductsByCategory API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getProductsByCategory DSS api response")
//	public void validate_get_products_by_category_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (ProductsByCategoryRequestPojo ProductsByCategoryRequest : sessionContext.getProductsByCategoryRequest()) {
//				T24ProductsByCategoryDTO t24ProductsByCategoryDTO=transactUtil.retrieveProductsByCategory(requestParams);
//				GetProductsByCategoryResponsePojo dssResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductsByCategoryService"), GetProductsByCategoryResponsePojo.class);
//				compareResponse(dssResponsePojo,t24ProductsByCategoryDTO);
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing getProductsByCategory API's response with temenos API response");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate {string} value in getProductsByCategory DSS api response should be {string}")
//	public void validate_value_in_get_products_by_category_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductsByCategoryService");
//			JsonPath jsonPath= new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field), value,field +" value mismatch Found");
//		} catch (Exception e) {
//			log.info("Error while comparing ProductsByCategoryService API's Error response filed values");
//			Assert.fail(e.getMessage());
//		}
//	}
//    
//	public void compareResponse(GetProductsByCategoryResponsePojo dss,T24ProductsByCategoryDTO t24) {
//		try {
//		for(int i=0;i<t24.getBody().size();i++) {
//			if(t24.getBody().get(i).getProductLineId()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getProductLineId());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getProductLineId(),t24.getBody().get(i).getProductLineId(),"Failed to validate ProductLineId");
//			}
//			if(t24.getBody().get(i).getProductLineName()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getProductLineName());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getProductLineName(),t24.getBody().get(i).getProductLineName(),"Failed to validate ProductLineName");
//			}
//			if(t24.getBody().get(i).getProductGroupId()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getProductGroupId());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getProductGroupId(),t24.getBody().get(i).getProductGroupId(),"Failed to validate ProductGroupId");
//			}
//			if(t24.getBody().get(i).getProductGroupName()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getProductGroupName());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getProductGroupName(),t24.getBody().get(i).getProductGroupName(),"Failed to validate ProductGroupName");
//			}
//			if(t24.getBody().get(i).getProductId()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getProductId());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getProductId(),t24.getBody().get(i).getProductId(),"Failed to validate ProductId");
//			}
//			if(t24.getBody().get(i).getProductName()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getProductName());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getProductName(),t24.getBody().get(i).getProductName(),"Failed to validate ProductName");
//			}
//			if(t24.getBody().get(i).getAvailableFromDate()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getAvailableFromDate());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getAvailableFromDate(),t24.getBody().get(i).getAvailableFromDate(),"Failed to validate AvailableFromDate");
//			}
//			if(t24.getBody().get(i).getCurrencyId()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getCurrencyId());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getCurrencyId(),t24.getBody().get(i).getCurrencyId(),"Failed to validate CurrencyId");
//			}
//			if(t24.getBody().get(i).getCurrencyName()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getCurrencyName());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getCurrencyName(),t24.getBody().get(i).getCurrencyName(),"Failed to validate CurrencyName");
//			}
//			if(t24.getBody().get(i).getMinimumTerm()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getMinimumTerm());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getMinimumTerm(),t24.getBody().get(i).getMinimumTerm(),"Failed to validate MinimumTerm");
//			}
//			if(t24.getBody().get(i).getMaximumTerm()==null) {
//				Assert.assertNull(dss.getProductInformation().get(i).getMaximumTerm());
//			}
//			else {
//				Assert.assertEquals(dss.getProductInformation().get(i).getMaximumTerm(),t24.getBody().get(i).getMaximumTerm(),"Failed to validate MaximumTerm");
//			}
//		}
//		} catch (Exception e) {
//			log.info("Error while comparing ProductsByCategoryService API's response with Temenos API");
//			Assert.fail(e.getMessage());
//		}
//	}
//}
