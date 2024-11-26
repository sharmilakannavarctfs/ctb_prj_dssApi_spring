//package com.ctfs.api.step;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//import java.io.InputStream;
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
//import com.ctfs.api.pojos.request.ProductDetailsRequestPojo;
//import com.ctfs.api.pojos.request.ProductTransactionsRequestPojo;
//import com.ctfs.api.pojos.response.ProductDetailsResponsePojo;
//import com.ctfs.api.pojos.response.ProductTransactionsResponsePojo;
//import com.ctfs.api.service.GetProductTransactionService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24ProductDetailsDTO;
//import com.ctfs.temenos.dss.datobjects.T24ProductTransactionsDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.*;
//import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetProductTransactionsSteps extends AbstractStep{
//	
//	private final Logger log = LoggerFactory.getLogger(GetProductTransactionsSteps.class);
//	@Autowired
//	private GetProductTransactionService getProductTransactionService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
////	T24ProductTransactionsDTO t24ProductTransactionsDTO;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public ProductTransactionsRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return ProductTransactionsRequestPojo.setProductTransactionsRequestPojo(entry);
//	}
//
//	@Given("Perform post operation to hit getProductTransactions DSS api using request payload")
//	public void postOperationForDssApi(List<ProductTransactionsRequestPojo> productTransactionsRequestPojos) throws URISyntaxException {
//		try {
//			for(ProductTransactionsRequestPojo productTransactionsRequestPojo: productTransactionsRequestPojos) {
//				getProductTransactionService.getProductTransaction(productTransactionsRequestPojo);
//			}
//			sessionContext.setProductTransactionsRequests(productTransactionsRequestPojos);
//			
//		} catch (Exception e) {
//			log.info("Error while hitting getProductTransactions API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//		
//	}
//	
//	@Given("Validate getProductTransactions DSS api response status code as {string}")
//	public void validate_getProduct_transactions_Dss_Api(String statusCode) throws URISyntaxException {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getProductTransactions API's status code");
//			Assert.fail(e.getMessage());
//		}
//		
//	}
//	
//	@Then("Validate {string} value in getProductTransactions DSS api response should be {string}")
//	public void validate_value_in_get_product_transactions_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService");
//			JsonPath jsonPath=new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field),value,"description value inside response is different");
//		} catch (Exception e) {
//			log.info("Error while asserting the descriptions and DssStatusCode");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getProductTransactions DSS api response should match with schema")
//	public void validate_get_product_transactions_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getProductTransactionsSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getProductTransactions API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	@Then("Validate getProductTransactions DSS api response")
//	public void validate_get_product_transactions_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (ProductTransactionsRequestPojo productTransactionsRequest : sessionContext.getProductTransactionsRequests()) {
//				T24ProductTransactionsDTO t24ProductTransactionsDTO=transactUtil.retrieveProductTransactions(requestParams);
//				ProductTransactionsResponsePojo productTransactionsResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
//				compareProductTransactions(productTransactionsResponsePojo,t24ProductTransactionsDTO);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.info("Error while comparing the response of temenos and DSS");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getProductTransactions DSS api error response should match with schema")
//	public void validate_get_product_transactions_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getProductTransactions API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductTransactions DSS api using {string} as {string} and {string} as {string} and {string} as {string}")
//	public void perform_post_operation_to_hit_get_product_transactions_dss_api_using_as(String idValue, String id,String startDateValue, String startDate, String endDateValue, String endDate) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();
//			if(idValue!=null) {
//				requestPayload.put(id, idValue);
//			}
//			if(startDateValue!=null) {
//				requestPayload.put(startDate,startDateValue.replace("/", ""));
//			}
//			if(endDateValue!=null) {
//				requestPayload.put(endDate, endDateValue.replace("/", ""));
//			}
//			getProductTransactionService.getProductTransaction(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getProductTransactions API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductTransactions DSS api without payload")
//	public void perform_post_operation_to_hit_get_product_transactions_dss_api_without_payload() {
//		try {  
//			getProductTransactionService.getProductTransaction("{}");;
//		} catch (Exception e) {
//			log.info("Error while hitting getProductTransactions API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductTransactions DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_product_transactions_dss_api_using_request_payload_with_expired_token(List<ProductTransactionsRequestPojo> productTransactionsPojos) {
//		try {
//			for(ProductTransactionsRequestPojo productTransactionsPojo: productTransactionsPojos) {
//				getProductTransactionService.getProductTransactionWithToken(productTransactionsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductTransactions API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductTransactions DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_product_transactions_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<ProductTransactionsRequestPojo> productTransactionsPojos) {
//		try {
//			for(ProductTransactionsRequestPojo productTransactionsPojo: productTransactionsPojos) {
//				getProductTransactionService.getProductTransactionWithToken(productTransactionsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductTransactions API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getProductTransactions DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_product_transactions_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<ProductTransactionsRequestPojo> productTransactionsPojos) {
//		try {
//			for(ProductTransactionsRequestPojo productTransactionsPojo: productTransactionsPojos) {
//				getProductTransactionService.getProductTransactionWithDisablePagination(productTransactionsPojo, pagination );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getProductTransactions API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	public void compareProductTransactions(ProductTransactionsResponsePojo productTransactionsResponsePojo, T24ProductTransactionsDTO t24ProductTransactionsDTO) {
//		for(int i=0;i<t24ProductTransactionsDTO.getBody().size();i++) {
//			
//			for(int j=0;j<t24ProductTransactionsDTO.getBody().get(i).getNarratives().size();j++) {
//				if(t24ProductTransactionsDTO.getBody().get(i).getNarratives().get(j).getNarrative()==null) {
//					Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getNarratives().get(j).getNarrative());
//				}
//				else {
//					Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getNarratives().get(j).getNarrative(), t24ProductTransactionsDTO.getBody().get(i).getNarratives().get(j).getNarrative(),"Failed to validate Narrative");
//				}
//			}
//			
//			if(t24ProductTransactionsDTO.getBody().get(i).getAccountId()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getAccountId());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getAccountId(),t24ProductTransactionsDTO.getBody().get(i).getAccountId(),"Failed to validate AccountId");
//			}
//			if(t24ProductTransactionsDTO.getBody().get(i).getBookingDate()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getBookingDate());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getBookingDate(),t24ProductTransactionsDTO.getBody().get(i).getBookingDate(),"Failed to validate getBookingDate");
//			}
//			if(t24ProductTransactionsDTO.getBody().get(i).getTransactionReference()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getTransactionReference());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getTransactionReference(),t24ProductTransactionsDTO.getBody().get(i).getTransactionReference(),"Failed to validate getTransactionReference");
//			}
//			if(t24ProductTransactionsDTO.getBody().get(i).getTransactionCode()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getTransactionCode());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getTransactionCode(),t24ProductTransactionsDTO.getBody().get(i).getTransactionCode(),"Failed to validate getTransactionCode");
//			}
//			if(t24ProductTransactionsDTO.getBody().get(i).getValueDate()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getValueDate());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getValueDate(),t24ProductTransactionsDTO.getBody().get(i).getValueDate(),"Failed to validate getValueDate");
//			}
//			if(t24ProductTransactionsDTO.getBody().get(i).getDebitAmount()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getDebitAmount());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getDebitAmount(),t24ProductTransactionsDTO.getBody().get(i).getDebitAmount(),"Failed to validate getDebitAmount");
//			}
//			if(t24ProductTransactionsDTO.getBody().get(i).getCreditAmount()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getCreditAmount());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getCreditAmount(),t24ProductTransactionsDTO.getBody().get(i).getCreditAmount(),"Failed to validate getCreditAmount");
//			}
//			if(t24ProductTransactionsDTO.getBody().get(i).getClosingBalance()==null) {
//				Assert.assertNull(productTransactionsResponsePojo.getProductTransactions().get(i).getClosingBalance());
//			}
//			else {
//				Assert.assertEquals(productTransactionsResponsePojo.getProductTransactions().get(i).getClosingBalance(),t24ProductTransactionsDTO.getBody().get(i).getClosingBalance(),"Failed to validate getClosingBalance");
//			}
//		}
//		
//	}
//	
//}
