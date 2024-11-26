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
//import com.ctfs.api.pojos.request.LinkedAccountsRequestPojo;
//import com.ctfs.api.pojos.response.CustomerProductsResponsePojo;
//import com.ctfs.api.pojos.response.LinkedAccountsResponsePojo;
//import com.ctfs.api.service.GetCustomerProductsService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24CustomerProductsDTO;
//import com.ctfs.temenos.dss.datobjects.T24LinkedAccountsDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.*;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetCustomerProductsSteps extends AbstractStep {
//	
//	private final Logger log = LoggerFactory.getLogger(GetCustomerProductsSteps.class);
//	@Autowired
//	private GetCustomerProductsService getCustomerProductsService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public CustomerProductsRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return CustomerProductsRequestPojo.setCustomerProductsRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getCustomerProducts DSS api using request payload")
//	public void perform_post_operation_to_hit_get_customer_products_dss_api_using_request_payload(List<CustomerProductsRequestPojo> customerProductsRequestPojos) {
//		try {
//			for(CustomerProductsRequestPojo customerProductsRequestPojo: customerProductsRequestPojos) {
//				getCustomerProductsService.getCustomerProducts(customerProductsRequestPojo);
//			}
//			sessionContext.setCustomerProductsRequests(customerProductsRequestPojos);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Then("Validate getCustomerProducts DSS api response status code as {string}")
//	public void validate_get_customer_products_dss_api_response_status_code_as(String statusCode) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("CustomerProductsService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Then("Validate getCustomerProducts DSS api response should match with schema")
//	public void validate_get_customer_products_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("CustomerProductsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getCustomerProductsSchema.json"));
//		} catch (Exception e) {
//			log.info(e.getMessage(),"Error while comparing getCustomerProducts API's response Schema");
//		}
//	}
//
//	@Then("Validate getCustomerProducts DSS api response")
//	public void validate_get_customer_products_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (CustomerProductsRequestPojo customerProductsRequest : sessionContext.getCustomerProductsRequests()) {
//				T24CustomerProductsDTO t24CustomerProductsDTO=transactUtil.retrieveCustomerProducts(requestParams);
//				CustomerProductsResponsePojo customerProductsResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("CustomerProductsService"), CustomerProductsResponsePojo.class);
//				compareCustomerProducts(customerProductsResponsePojo,t24CustomerProductsDTO);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.info(e.getMessage(),"coming inside catch of response compare");
//		}
//	}
//
//	@Given("Perform post operation to hit getCustomerProducts DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_customer_products_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);          
//			getCustomerProductsService.getCustomerProducts(requestPayload);
//		} catch (Exception e) {
//			log.info(e.getMessage(),"Error while hitting getCustomerProducts API using request Payload");
//		}
//	}
//
//	@Then("Validate getCustomerProducts DSS api error response should match with schema")
//	public void validate_get_customer_products_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("CustomerProductsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info(e.getMessage(),"Error while comparing getCustomerProducts API's Error response Schema");
//		}
//	}
//
//	@Then("Validate {string} value in getCustomerProducts DSS api response should be {string}")
//	public void validate_value_in_get_customer_products_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("CustomerProductsService");
//			JsonPath jsonPath=new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field),value, field+" value inside response is different");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Given("Perform post operation to hit getCustomerProducts DSS api without payload")
//	public void perform_post_operation_to_hit_get_customer_products_dss_api_without_payload() {
//		try {  
//			getCustomerProductsService.getCustomerProducts("{}");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Given("Perform post operation to hit getCustomerProducts DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_customer_products_dss_api_using_request_payload_with_expired_token(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("Perform post operation to hit getCustomerProducts DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_customer_products_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("Perform post operation to hit getCustomerProducts DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_customer_products_dss_api_using_request_payload_and_disable_paginationheader_as(String string, io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    throw new io.cucumber.java.PendingException();
//	}
//	
//	public void compareCustomerProducts(CustomerProductsResponsePojo customerProductsResponsePojo,T24CustomerProductsDTO t24CustomerProductsDTO) {
//		for(int i=0;i<t24CustomerProductsDTO.getBody().size();i++) {
//			for(int j=0;j<t24CustomerProductsDTO.getBody().get(i).getAlternateAccountIds().size();j++) {
//				for(int k=0;k<t24CustomerProductsDTO.getBody().get(i).getAlternateAccountIds().get(j).getAlternateAccountType().size();k++) {
//					
//				}
//				for(int k=0;k<t24CustomerProductsDTO.getBody().get(i).getAlternateAccountIds().get(j).getAlternateAccountId().size();k++) {
//					
//				}
//				
//			}
//			
//			for(int j=0;j<t24CustomerProductsDTO.getBody().get(i).getInterestRates().size();j++) {
//				for(int k=0;k<t24CustomerProductsDTO.getBody().get(i).getInterestRates().get(j).getInterestProperty().size();k++) {
//					
//				}
//				for(int k=0;k<t24CustomerProductsDTO.getBody().get(i).getInterestRates().get(j).getInterestRate().size();k++) {
//					
//				}
//				for(int k=0;k<t24CustomerProductsDTO.getBody().get(i).getInterestRates().get(j).getInterestType().size();k++) {
//					
//				}
//			}
//			
//			if(t24CustomerProductsDTO.getBody().get(i).getRoutingNumber()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getRoutingNumber());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getRoutingNumber(), t24CustomerProductsDTO.getBody().get(i).getRoutingNumber(),"Failed to validate getRoutingNumber");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAccountWithBankSortCode()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAccountWithBankSortCode());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAccountWithBankSortCode(), t24CustomerProductsDTO.getBody().get(i).getAccountWithBankSortCode(),"Failed to validate getAccountWithBankSortCode");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAccountOpenOtherReason()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAccountOpenOtherReason());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAccountOpenOtherReason(), t24CustomerProductsDTO.getBody().get(i).getAccountOpenOtherReason(),"Failed to validate getAccountOpenOtherReason");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAccountOpenReasonCode()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAccountOpenReasonCode());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAccountOpenReasonCode(), t24CustomerProductsDTO.getBody().get(i).getAccountOpenReasonCode(),"Failed to validate getAccountOpenReasonCode");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getNumericCurrencyCode()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getNumericCurrencyCode());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getNumericCurrencyCode(), t24CustomerProductsDTO.getBody().get(i).getNumericCurrencyCode(),"Failed to validate getNumericCurrencyCode");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getPlanType()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getPlanType());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getPlanType(), t24CustomerProductsDTO.getBody().get(i).getPlanType(),"Failed to validate getPlanType");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAccountTitle1()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAccountTitle1());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAccountTitle1(), t24CustomerProductsDTO.getBody().get(i).getAccountTitle1(),"Failed to validate getAccountTitle1");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAccountTitle2()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAccountTitle2());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAccountTitle2(), t24CustomerProductsDTO.getBody().get(i).getAccountTitle2(),"Failed to validate getAccountTitle2");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getProductLine()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getProductLine());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getProductLine(), t24CustomerProductsDTO.getBody().get(i).getProductLine(),"Failed to validate getProductLine");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getThirdPartyUse()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getThirdPartyUse());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getThirdPartyUse(), t24CustomerProductsDTO.getBody().get(i).getThirdPartyUse(),"Failed to validate getThirdPartyUse");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getPostingRestriction()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getPostingRestriction());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getPostingRestriction(), t24CustomerProductsDTO.getBody().get(i).getPostingRestriction(),"Failed to validate getPostingRestriction");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getLastCreditDate()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getLastCreditDate());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getLastCreditDate(), t24CustomerProductsDTO.getBody().get(i).getLastCreditDate(),"Failed to validate getLastCreditDate");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getLastDebitDate()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getLastDebitDate());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getLastDebitDate(), t24CustomerProductsDTO.getBody().get(i).getLastDebitDate(),"Failed to validate getLastDebitDate");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAllowBillPayment()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAllowBillPayment());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAllowBillPayment(), t24CustomerProductsDTO.getBody().get(i).getAllowBillPayment(),"Failed to validate getAllowBillPayment");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAllowDeposit()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAllowDeposit());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAllowDeposit(), t24CustomerProductsDTO.getBody().get(i).getAllowDeposit(),"Failed to validate getAllowDeposit");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAllowWithdrawal()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAllowWithdrawal());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAllowWithdrawal(), t24CustomerProductsDTO.getBody().get(i).getAllowWithdrawal(),"Failed to validate getAllowWithdrawal");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getIsInternetBankingService()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getIsInternetBankingService());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getIsInternetBankingService(), t24CustomerProductsDTO.getBody().get(i).getIsInternetBankingService(),"Failed to validate getIsInternetBankingService");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAllowInterMemberTransfer()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAllowInterMemberTransfer());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAllowInterMemberTransfer(), t24CustomerProductsDTO.getBody().get(i).getAllowInterMemberTransfer(),"Failed to validate getAllowInterMemberTransfer");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAllowMeToMeTransfer()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAllowMeToMeTransfer());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAllowMeToMeTransfer(), t24CustomerProductsDTO.getBody().get(i).getAllowMeToMeTransfer(),"Failed to validate getAllowMeToMeTransfer");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getOtherParty()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getOtherParty());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getOtherParty(), t24CustomerProductsDTO.getBody().get(i).getOtherParty(),"Failed to validate getOtherParty");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getProductCategoryId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getProductCategoryId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getProductCategoryId(), t24CustomerProductsDTO.getBody().get(i).getProductCategoryId(),"Failed to validate getProductCategoryId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getNumberOfSignatory()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getNumberOfSignatory());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getNumberOfSignatory(), t24CustomerProductsDTO.getBody().get(i).getNumberOfSignatory(),"Failed to validate getNumberOfSignatory");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getSignatory()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getSignatory());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getSignatory(), t24CustomerProductsDTO.getBody().get(i).getSignatory(),"Failed to validate getSignatory");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCardNumber()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCardNumber());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCardNumber(), t24CustomerProductsDTO.getBody().get(i).getCardNumber(),"Failed to validate getCardNumber");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCompanyCode()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCompanyCode());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCompanyCode(), t24CustomerProductsDTO.getBody().get(i).getCompanyCode(),"Failed to validate getCompanyCode");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getProductName()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getProductName());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getProductName(), t24CustomerProductsDTO.getBody().get(i).getProductName(),"Failed to validate getProductName");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAccountId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAccountId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAccountId(), t24CustomerProductsDTO.getBody().get(i).getAccountId(),"Failed to validate getAccountId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getDisplayName()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getDisplayName());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getDisplayName(), t24CustomerProductsDTO.getBody().get(i).getDisplayName(),"Failed to validate getDisplayName");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCurrencyId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCurrencyId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCurrencyId(), t24CustomerProductsDTO.getBody().get(i).getCurrencyId(),"Failed to validate getCurrencyId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getLockedAmount()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getLockedAmount());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getLockedAmount(), t24CustomerProductsDTO.getBody().get(i).getLockedAmount(),"Failed to validate getLockedAmount");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getWorkingBalance()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getWorkingBalance());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getWorkingBalance(), t24CustomerProductsDTO.getBody().get(i).getWorkingBalance(),"Failed to validate getWorkingBalance");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getOnlineActualBalance()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getOnlineActualBalance());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getOnlineActualBalance(), t24CustomerProductsDTO.getBody().get(i).getOnlineActualBalance(),"Failed to validate getOnlineActualBalance");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAvailableBalance()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAvailableBalance());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAvailableBalance(), t24CustomerProductsDTO.getBody().get(i).getAvailableBalance(),"Failed to validate getAvailableBalance");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getSortCode()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getSortCode());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getSortCode(), t24CustomerProductsDTO.getBody().get(i).getSortCode(),"Failed to validate getSortCode");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCustomerId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCustomerId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCustomerId(), t24CustomerProductsDTO.getBody().get(i).getCustomerId(),"Failed to validate getCustomerId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCustomerName()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCustomerName());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCustomerName(), t24CustomerProductsDTO.getBody().get(i).getCustomerName(),"Failed to validate getCustomerName");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAvailableLimit()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAvailableLimit());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAvailableLimit(), t24CustomerProductsDTO.getBody().get(i).getAvailableLimit(),"Failed to validate getAvailableLimit");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getIBAN()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getIBAN());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getIBAN(), t24CustomerProductsDTO.getBody().get(i).getIBAN(),"Failed to validate getIBAN");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getPortfolioId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getPortfolioId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getPortfolioId(), t24CustomerProductsDTO.getBody().get(i).getPortfolioId(),"Failed to validate getPortfolioId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getOpeningDate()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getOpeningDate());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getOpeningDate(), t24CustomerProductsDTO.getBody().get(i).getOpeningDate(),"Failed to validate getOpeningDate");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getLimitReference()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getLimitReference());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getLimitReference(), t24CustomerProductsDTO.getBody().get(i).getLimitReference(),"Failed to validate getLimitReference");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getClearedBalance()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getClearedBalance());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getClearedBalance(), t24CustomerProductsDTO.getBody().get(i).getClearedBalance(),"Failed to validate getClearedBalance");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAvailableFunds()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAvailableFunds());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAvailableFunds(), t24CustomerProductsDTO.getBody().get(i).getAvailableFunds(),"Failed to validate getAvailableFunds");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getArrangementId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getArrangementId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getArrangementId(), t24CustomerProductsDTO.getBody().get(i).getArrangementId(),"Failed to validate getArrangementId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getProductId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getProductId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getProductId(), t24CustomerProductsDTO.getBody().get(i).getProductId(),"Failed to validate getProductId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getProductGroupId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getProductGroupId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getProductGroupId(), t24CustomerProductsDTO.getBody().get(i).getProductGroupId(),"Failed to validate getProductGroupId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCustomer()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCustomer());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCustomer(), t24CustomerProductsDTO.getBody().get(i).getCustomer(),"Failed to validate getCustomer");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getRelationCode()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getRelationCode());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getRelationCode(), t24CustomerProductsDTO.getBody().get(i).getRelationCode(),"Failed to validate getRelationCode");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCustomerRole()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCustomerRole());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCustomerRole(), t24CustomerProductsDTO.getBody().get(i).getCustomerRole(),"Failed to validate getCustomerRole");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCustomerType()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCustomerType());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCustomerType(), t24CustomerProductsDTO.getBody().get(i).getCustomerType(),"Failed to validate getCustomerType");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getRoleDisplayName()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getRoleDisplayName());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getRoleDisplayName(), t24CustomerProductsDTO.getBody().get(i).getRoleDisplayName(),"Failed to validate getRoleDisplayName");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getTaxId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getTaxId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getTaxId(), t24CustomerProductsDTO.getBody().get(i).getTaxId(),"Failed to validate getTaxId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getFirstName()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getFirstName());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getFirstName(), t24CustomerProductsDTO.getBody().get(i).getFirstName(),"Failed to validate getFirstName");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getLastName()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getLastName());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getLastName(), t24CustomerProductsDTO.getBody().get(i).getLastName(),"Failed to validate getLastName");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getDateOfBirth()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getDateOfBirth());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getDateOfBirth(), t24CustomerProductsDTO.getBody().get(i).getDateOfBirth(),"Failed to validate getDateOfBirth");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getPhoneNumber()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getPhoneNumber());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getPhoneNumber(), t24CustomerProductsDTO.getBody().get(i).getPhoneNumber(),"Failed to validate getPhoneNumber");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getEmail()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getEmail());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getEmail(), t24CustomerProductsDTO.getBody().get(i).getEmail(),"Failed to validate getEmail");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getPostCode()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getPostCode());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getPostCode(), t24CustomerProductsDTO.getBody().get(i).getPostCode(),"Failed to validate getPostCode");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getStreet()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getStreet());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getStreet(), t24CustomerProductsDTO.getBody().get(i).getStreet(),"Failed to validate getStreet");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getTownCountry()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getTownCountry());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getTownCountry(), t24CustomerProductsDTO.getBody().get(i).getTownCountry(),"Failed to validate getTownCountry");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getSalutation()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getSalutation());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getSalutation(), t24CustomerProductsDTO.getBody().get(i).getSalutation(),"Failed to validate getSalutation");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getMaritalStatus()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getMaritalStatus());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getMaritalStatus(), t24CustomerProductsDTO.getBody().get(i).getMaritalStatus(),"Failed to validate getMaritalStatus");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getEmploymentStatus()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getEmploymentStatus());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getEmploymentStatus(), t24CustomerProductsDTO.getBody().get(i).getEmploymentStatus(),"Failed to validate getEmploymentStatus");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCategoryName()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCategoryName());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCategoryName(), t24CustomerProductsDTO.getBody().get(i).getCategoryName(),"Failed to validate getCategoryName");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getCategoryId()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getCategoryId());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getCategoryId(), t24CustomerProductsDTO.getBody().get(i).getCategoryId(),"Failed to validate getCategoryId");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getContactType()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getContactType());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getContactType(), t24CustomerProductsDTO.getBody().get(i).getContactType(),"Failed to validate getContactType");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getIddPrefixPhone()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getIddPrefixPhone());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getIddPrefixPhone(), t24CustomerProductsDTO.getBody().get(i).getIddPrefixPhone(),"Failed to validate getIddPrefixPhone");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getContactData()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getContactData());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getContactData(), t24CustomerProductsDTO.getBody().get(i).getContactData(),"Failed to validate getContactData");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getAccountStatus()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getAccountStatus());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getAccountStatus(), t24CustomerProductsDTO.getBody().get(i).getAccountStatus(),"Failed to validate getAccountStatus");
//			}
//			if(t24CustomerProductsDTO.getBody().get(i).getBeneficialOwner()==null) {
//				Assert.assertNull(customerProductsResponsePojo.getCustomerList().get(i).getBeneficialOwner());
//			}
//			else {
//				Assert.assertEquals(customerProductsResponsePojo.getCustomerList().get(i).getBeneficialOwner(), t24CustomerProductsDTO.getBody().get(i).getBeneficialOwner(),"Failed to validate getBeneficialOwner");
//			}
//		}
//	}
//
//}
