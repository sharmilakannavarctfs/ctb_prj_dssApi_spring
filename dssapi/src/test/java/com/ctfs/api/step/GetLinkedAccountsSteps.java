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
//import com.ctfs.api.pojos.request.LinkedAccountsRequestPojo;
//import com.ctfs.api.pojos.request.PortfolioDetailsRequestPojo;
//import com.ctfs.api.pojos.request.ProductDetailsRequestPojo;
//import com.ctfs.api.pojos.response.LinkedAccountsResponsePojo;
//import com.ctfs.api.pojos.response.PortfolioDetailsResponsePojo;
//import com.ctfs.api.pojos.response.ProductDetailsResponsePojo;
//import com.ctfs.api.service.GetLinkedAccountsService;
//import com.ctfs.api.service.GetPortfolioDetailsService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.pojos.T24LinkedAccountsPojo;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24LinkedAccountsDTO;
//import com.ctfs.temenos.dss.datobjects.T24ProductDetailsDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.*;
//import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetLinkedAccountsSteps extends AbstractStep {
//	
//	
//	private final Logger log = LoggerFactory.getLogger(GetLinkedAccountsSteps.class);
//	@Autowired
//	private GetLinkedAccountsService getLinkedAccountsService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//
//	
//	@DataTableType
//	public LinkedAccountsRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return LinkedAccountsRequestPojo.setLinkedAccountsRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getLinkedAccounts DSS api using request payload")
//	public void perform_post_operation_to_hit_get_linked_accounts_dss_api_using_request_payload(List<LinkedAccountsRequestPojo> linkedAccountsRequestPojos) throws URISyntaxException{
//		try {
//			for(LinkedAccountsRequestPojo linkedAccountsRequestPojo: linkedAccountsRequestPojos) {
//				getLinkedAccountsService.getLinkedAccounts(linkedAccountsRequestPojo);
//			}
//			sessionContext.setLinkedAccountsRequests(linkedAccountsRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getLinkedAccounts API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getLinkedAccounts DSS api response status code as {string}")
//	public void validate_get_linked_accounts_dss_api_response_status_code_as(String statusCode) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("LinkedAccountsService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getLinkedAccounts API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	
//	@Then("Validate getLinkedAccounts DSS api response should match with schema")
//	public void validate_get_linked_accounts_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("LinkedAccountsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getLinkedAccountsSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getLinkedAccounts API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getLinkedAccounts DSS api response")
//	public void validate_get_linked_accounts_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (LinkedAccountsRequestPojo linkedAccountsRequest : sessionContext.getLinkedAccountsRequests()) {
//				T24LinkedAccountsDTO t24LinkedAccountsDTO=transactUtil.retrieveLinkedAccounts(requestParams);
//				LinkedAccountsResponsePojo linkedAccountsResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("LinkedAccountsService"), LinkedAccountsResponsePojo.class);
//				
//				compareLinkedAccounts(linkedAccountsResponsePojo,t24LinkedAccountsDTO);
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing getLinkedAccounts DSS and Temenso response");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate {string} value in getLinkedAccounts DSS api response should be {string}")
//	public void validate_value_in_get_linked_accounts_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("LinkedAccountsService");
//			JsonPath jsonPath=new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field),value,"description value inside response is different");
//		} catch (Exception e) {
//			log.info("Error while matching getLinkedAccounts API description and statusCode");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getLinkedAccounts DSS api using {string} as {string} and {string} as {string} and {string} as {string} and {string} as {string}")
//	public void perform_post_operation_to_hit_get_linked_accounts_dss_api_using_as_and_as_and_as_and_as(String customerIdValue, String customerIdField, String beneficiaryIdValue, String beneficiaryIdField, String cardIdValue, String cardIdField, String ownAccountValue, String ownAccountField) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();
//			if(customerIdValue!=null) {
//				requestPayload.put(customerIdField, customerIdValue);
//			}
//			if(beneficiaryIdValue!=null) {
//				requestPayload.put(beneficiaryIdField, beneficiaryIdValue);
//			}
//			if(cardIdValue!=null) {
//				requestPayload.put(cardIdField, cardIdValue);
//			}
//			if(cardIdValue!=null) {
//				requestPayload.put(ownAccountField, ownAccountValue);
//			}
//			getLinkedAccountsService.getLinkedAccounts(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getDemandInterestRate API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getLinkedAccounts DSS api without payload")
//	public void perform_post_operation_to_hit_get_linked_accounts_dss_api_without_payload() {
//		try {  
//			getLinkedAccountsService.getLinkedAccounts("{}");
//		} catch (Exception e) {
//			log.info("Error while hitting getLinkedAccounts API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getLinkedAccounts DSS api error response should match with schema")
//	public void validate_get_linked_accounts_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("LinkedAccountsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getLinkedAccounts API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getLinkedAccounts DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_get_linked_accounts_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);          
//			getLinkedAccountsService.getLinkedAccounts(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getLinkedAccounts API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getLinkedAccounts DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_linked_accounts_dss_api_using_request_payload_with_expired_token(List<LinkedAccountsRequestPojo> linkedAccountsPojos) {
//		try {
//			for(LinkedAccountsRequestPojo linkedAccountsPojo: linkedAccountsPojos) {
//				getLinkedAccountsService.getLinkedAccountsWithToken(linkedAccountsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getLinkedAccounts API using request Pojo with Expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getLinkedAccounts DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_linked_accounts_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<LinkedAccountsRequestPojo> linkedAccountsPojos) {
//		try {
//			for(LinkedAccountsRequestPojo linkedAccountsPojo: linkedAccountsPojos) {
//				getLinkedAccountsService.getLinkedAccountsWithToken(linkedAccountsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getLinkedAccounts API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getLinkedAccounts DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_linked_accounts_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<LinkedAccountsRequestPojo> linkedAccountsPojos) {
//		try {
//			for(LinkedAccountsRequestPojo linkedAccountsPojo: linkedAccountsPojos) {
//				getLinkedAccountsService.getLinkedAccountsWithDisablePagination(linkedAccountsPojo, pagination );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getLinkedAccounts API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	public void compareLinkedAccounts(LinkedAccountsResponsePojo linkedAccountsResponsePojo,T24LinkedAccountsDTO t24LinkedAccountsDTO) {
//		for(int i=0;i<t24LinkedAccountsDTO.getBody().size();i++) {
//			if(t24LinkedAccountsDTO.getBody().get(i).getCardId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCardId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCardId(),t24LinkedAccountsDTO.getBody().get(i).getCardId(),"Failed to validate CardId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getCustomerId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCustomerId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCustomerId(),t24LinkedAccountsDTO.getBody().get(i).getCustomerId(),"Failed to validate CustomerId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getAccountTitle1()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountTitle1());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountTitle1(),t24LinkedAccountsDTO.getBody().get(i).getAccountTitle1(),"Failed to validate AccountTitle1");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getAccountTitle2()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountTitle2());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountTitle2(),t24LinkedAccountsDTO.getBody().get(i).getAccountTitle2(),"Failed to validate AccountTitle2");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getAccountName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountName(),t24LinkedAccountsDTO.getBody().get(i).getAccountName(),"Failed to validate AccountName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getOwnAccount()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getOwnAccount());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getOwnAccount(),t24LinkedAccountsDTO.getBody().get(i).getOwnAccount(),"Failed to validate getOwnAccount");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getCompanyId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCompanyId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCompanyId(),t24LinkedAccountsDTO.getBody().get(i).getCompanyId(),"Failed to validate getCompanyId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryId(),t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryId(),"Failed to validate getBeneficiaryId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getNickName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getNickName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getNickName(),t24LinkedAccountsDTO.getBody().get(i).getNickName(),"Failed to validate getNickName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getCategoryId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCategoryId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCategoryId(),t24LinkedAccountsDTO.getBody().get(i).getCategoryId(),"Failed to validate getCategoryId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryAccountId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryAccountId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryAccountId(),t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryAccountId(),"Failed to validate getBeneficiaryAccountId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryCustomerId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryCustomerId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryCustomerId(),t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryCustomerId(),"Failed to validate getBeneficiaryCustomerId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getTransactionType()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getTransactionType());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getTransactionType(),t24LinkedAccountsDTO.getBody().get(i).getTransactionType(),"Failed to validate getTransactionType");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBankSortCode()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBankSortCode());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBankSortCode(),t24LinkedAccountsDTO.getBody().get(i).getBankSortCode(),"Failed to validate getBankSortCode");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBIC()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBIC());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBIC(),t24LinkedAccountsDTO.getBody().get(i).getBIC(),"Failed to validate getBIC");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getIBAN()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getIBAN());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getIBAN(),t24LinkedAccountsDTO.getBody().get(i).getIBAN(),"Failed to validate getIBAN");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getLinkBeneficiaryId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getLinkBeneficiaryId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getLinkBeneficiaryId(),t24LinkedAccountsDTO.getBody().get(i).getLinkBeneficiaryId(),"Failed to validate getLinkBeneficiaryId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getCustomerReference()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCustomerReference());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getCustomerReference(),t24LinkedAccountsDTO.getBody().get(i).getCustomerReference(),"Failed to validate getCustomerReference");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getAccountWithBank()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountWithBank());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getAccountWithBank(),t24LinkedAccountsDTO.getBody().get(i).getAccountWithBank(),"Failed to validate getAccountWithBank");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getPaymentCurrencyId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPaymentCurrencyId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPaymentCurrencyId(),t24LinkedAccountsDTO.getBody().get(i).getPaymentCurrencyId(),"Failed to validate getPaymentCurrencyId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getPreferredPaymentAmount()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPreferredPaymentAmount());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPreferredPaymentAmount(),t24LinkedAccountsDTO.getBody().get(i).getPreferredPaymentAmount(),"Failed to validate getPreferredPaymentAmount");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getOurCharges()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getOurCharges());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getOurCharges(),t24LinkedAccountsDTO.getBody().get(i).getOurCharges(),"Failed to validate getOurCharges");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getPaymentCountry()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPaymentCountry());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPaymentCountry(),t24LinkedAccountsDTO.getBody().get(i).getPaymentCountry(),"Failed to validate getPaymentCountry");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getTransactionName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getTransactionName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getTransactionName(),t24LinkedAccountsDTO.getBody().get(i).getTransactionName(),"Failed to validate getTransactionName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryNickName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryNickName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryNickName(),t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryNickName(),"Failed to validate getBeneficiaryNickName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getPreferredPaymentProduct()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPreferredPaymentProduct());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPreferredPaymentProduct(),t24LinkedAccountsDTO.getBody().get(i).getPreferredPaymentProduct(),"Failed to validate getPreferredPaymentProduct");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getDefaultNarrative()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDefaultNarrative());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDefaultNarrative(),t24LinkedAccountsDTO.getBody().get(i).getDefaultNarrative(),"Failed to validate getDefaultNarrative");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getDateTime()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDateTime());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDateTime(),t24LinkedAccountsDTO.getBody().get(i).getDateTime(),"Failed to validate getDateTime");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getShortName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getShortName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getShortName(),t24LinkedAccountsDTO.getBody().get(i).getShortName(),"Failed to validate getShortName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBankName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBankName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBankName(),t24LinkedAccountsDTO.getBody().get(i).getBankName(),"Failed to validate getBankName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryAddress()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryAddress());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryAddress(),t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryAddress(),"Failed to validate getBeneficiaryAddress");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryAddressCity()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryAddressCity());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryAddressCity(),t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryAddressCity(),"Failed to validate getBeneficiaryAddressCity");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getPostCode()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPostCode());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPostCode(),t24LinkedAccountsDTO.getBody().get(i).getPostCode(),"Failed to validate getPostCode");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryCountryName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryCountryName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBeneficiaryCountryName(),t24LinkedAccountsDTO.getBody().get(i).getBeneficiaryCountryName(),"Failed to validate getBeneficiaryCountryName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getVersionNumber()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getVersionNumber());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getVersionNumber(),t24LinkedAccountsDTO.getBody().get(i).getVersionNumber(),"Failed to validate getVersionNumber");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getDepartmentId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDepartmentId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDepartmentId(),t24LinkedAccountsDTO.getBody().get(i).getDepartmentId(),"Failed to validate getDepartmentId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getSubDepartmentId()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getSubDepartmentId());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getSubDepartmentId(),t24LinkedAccountsDTO.getBody().get(i).getSubDepartmentId(),"Failed to validate getSubDepartmentId");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBuildingNumber()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBuildingNumber());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBuildingNumber(),t24LinkedAccountsDTO.getBody().get(i).getBuildingNumber(),"Failed to validate getBuildingNumber");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getBuildingName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBuildingName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getBuildingName(),t24LinkedAccountsDTO.getBody().get(i).getBuildingName(),"Failed to validate getBuildingName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getFloorNumber()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getFloorNumber());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getFloorNumber(),t24LinkedAccountsDTO.getBody().get(i).getFloorNumber(),"Failed to validate getFloorNumber");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getPostBoxNumber()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPostBoxNumber());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getPostBoxNumber(),t24LinkedAccountsDTO.getBody().get(i).getPostBoxNumber(),"Failed to validate getPostBoxNumber");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getApartmentNumber()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getApartmentNumber());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getApartmentNumber(),t24LinkedAccountsDTO.getBody().get(i).getApartmentNumber(),"Failed to validate getApartmentNumber");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getTown()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getTown());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getTown(),t24LinkedAccountsDTO.getBody().get(i).getTown(),"Failed to validate getTown");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getDistrictName()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDistrictName());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getDistrictName(),t24LinkedAccountsDTO.getBody().get(i).getDistrictName(),"Failed to validate getDistrictName");
//			}
//			if(t24LinkedAccountsDTO.getBody().get(i).getState()==null) {
//				Assert.assertNull(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getState());
//			}
//			else {
//				Assert.assertEquals(linkedAccountsResponsePojo.getLinkedAccounts().get(i).getState(),t24LinkedAccountsDTO.getBody().get(i).getState(),"Failed to validate getState");
//			}
//			
//		}
//	}
//
//}
