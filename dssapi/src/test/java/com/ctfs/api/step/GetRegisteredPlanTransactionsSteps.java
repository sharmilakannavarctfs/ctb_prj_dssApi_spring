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
//import com.ctfs.api.pojos.request.GetRegisteredPlanTransactionsRequestPojo;
//import com.ctfs.api.pojos.response.GetRegisteredPlanTransactionsResponsePojo;
//import com.ctfs.api.service.GetRegisteredPlanTransactionsService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24RegisteredPlansTransactionsDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetRegisteredPlanTransactionsSteps extends AbstractStep {
//
//	private final Logger log = LoggerFactory.getLogger(GetRegisteredPlanTransactionsSteps.class);
//	@Autowired
//	private GetRegisteredPlanTransactionsService getRegisteredPlanTransactionsService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//
//	@DataTableType
//	public GetRegisteredPlanTransactionsRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return GetRegisteredPlanTransactionsRequestPojo.setRegisteredPlanTransactionsRequestPojo(entry);
//	}
//
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_using_request_payload(
//			List<GetRegisteredPlanTransactionsRequestPojo> RegisteredPlanTransactionsPojos) {
//		try {
//			for (GetRegisteredPlanTransactionsRequestPojo RegisteredPlanTransactionsPojo : RegisteredPlanTransactionsPojos) {
//				getRegisteredPlanTransactionsService.getRegisteredPlanTransactions(RegisteredPlanTransactionsPojo);
//			}
//			sessionContext.setRegisteredPlanTransactionsRequest(RegisteredPlanTransactionsPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api using {string} as {string} and {string} as {string} and {string} as {string}")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_using_as_and_as_and_as(String portfolioIdValue, String portfolioIdField, String taxYearValue, String taxYearField, String debitCreditIndicatorValue, String debitCreditIndicatorField) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();
//			requestPayload.put(portfolioIdField, portfolioIdValue);
//			requestPayload.put(taxYearField, taxYearValue);
//			requestPayload.put(debitCreditIndicatorField, debitCreditIndicatorValue);
//			getRegisteredPlanTransactionsService.getRegisteredPlanTransactions(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API using request payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api using {string} as {string} and {string} as {string}")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_using_as_and_as(
//			String portfolioIdValue, String portfolioIdField, String taxYearValue, String taxYearField) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();
//			requestPayload.put(portfolioIdField, portfolioIdValue);
//			requestPayload.put(taxYearField, taxYearValue);
//			getRegisteredPlanTransactionsService.getRegisteredPlanTransactions(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API using request payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_using_as(String value,
//			String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();
//			requestPayload.put(field, value);
//			getRegisteredPlanTransactionsService.getRegisteredPlanTransactions(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API using single field payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api without any payload")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_without_any_payload() {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();
//			getRegisteredPlanTransactionsService.getRegisteredPlanTransactions(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_using_request_payload_with_expired_token(List<GetRegisteredPlanTransactionsRequestPojo> RegisteredPlanTransactionsPojos) {
//		try {
//			for (GetRegisteredPlanTransactionsRequestPojo RegisteredPlanTransactionsPojo : RegisteredPlanTransactionsPojos) {
//				getRegisteredPlanTransactionsService.getRegisteredPlanTransactionsWithToken(RegisteredPlanTransactionsPojo,token);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<GetRegisteredPlanTransactionsRequestPojo> RegisteredPlanTransactionsPojos)  {
//		try {
//			for (GetRegisteredPlanTransactionsRequestPojo RegisteredPlanTransactionsPojo : RegisteredPlanTransactionsPojos) {
//				getRegisteredPlanTransactionsService.getRegisteredPlanTransactionsWithToken(RegisteredPlanTransactionsPojo,token);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_registered_plan_transactions_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<GetRegisteredPlanTransactionsRequestPojo> RegisteredPlanTransactionsPojos) {
//		try {
//			for (GetRegisteredPlanTransactionsRequestPojo RegisteredPlanTransactionsPojo : RegisteredPlanTransactionsPojos) {
//				getRegisteredPlanTransactionsService.getRegisteredPlanTransactionsWithDisablePagination(RegisteredPlanTransactionsPojo,pagination);
//			}
//			sessionContext.setRegisteredPlanTransactionsRequest(RegisteredPlanTransactionsPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getRegisteredPlanTransactions API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getRegisteredPlanTransactions DSS api response status code as {string}")
//	public void validate_get_registered_plan_transactions_dss_api_response_status_code_as(String statusCode) {
//		try {
//			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("RegisteredPlanTransactionsService");
//			Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode),
//					"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getRegisteredPlanTransactions API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getRegisteredPlanTransactions DSS api response should match with schema")
//	public void validate_get_registered_plan_transactions_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("RegisteredPlanTransactionsService");
//			response.then().log().all().assertThat()
//					.body(matchesJsonSchemaInClasspath("schema\\getRegisteredPlanTransactionsSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getRegisteredPlanTransactions API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getRegisteredPlanTransactions DSS api error response should match with schema")
//	public void validate_get_registered_plan_transactions_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response) stepDefinitionDataManager.getStoredObjectMap().get("RegisteredPlanTransactionsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getProductInterestRates API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getRegisteredPlanTransactions DSS api response")
//	public void validate_get_registered_plan_transactions_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			GetRegisteredPlanTransactionsResponsePojo dssResponsePojo = deserializeResponse((Response) stepDefinitionDataManager.getStoredObjectMap().get("RegisteredPlanTransactionsService"),GetRegisteredPlanTransactionsResponsePojo.class);
//			for (GetRegisteredPlanTransactionsRequestPojo request : sessionContext.getRegisteredPlanTransactionsRequest()) {
//				T24RegisteredPlansTransactionsDTO t24RegisteredPlansTransactionsDTO=transactUtil.retrieveRegisteredPlansTransactions(requestParams);
//				compareRegisteredPlanTransactions(dssResponsePojo,t24RegisteredPlansTransactionsDTO);
//			}
//
//		} catch (Exception e) {
//			log.info("Error while comparing getRegisteredPlanTransactions API's response with temenos API response");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate {string} value in getRegisteredPlanTransactions DSS api response should be {string}")
//	public void validate_value_in_get_registered_plan_transactions_dss_api_response_should_be(String field,
//			String value) {
//		try {
//			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("RegisteredPlanTransactionsService");
//			JsonPath jsonPath= new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field), value,field +" value mismatch Found");
//		} catch (Exception e) {
//			log.info("Error while comparing getRegisteredPlanTransactions API's Error response filed values");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	public void compareRegisteredPlanTransactions(GetRegisteredPlanTransactionsResponsePojo dss,T24RegisteredPlansTransactionsDTO t24) {
//		try {
//		for (int i = 0; i < t24.getBody().size(); i++) {
//			if(t24.getBody().get(i).getAccountId()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getAccountId());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getAccountId(), t24.getBody().get(i).getAccountId(),"Failed to validate AccountId");
//			}
//			if(t24.getBody().get(i).getBalanceAmount()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getBalanceAmount());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getBalanceAmount(), t24.getBody().get(i).getBalanceAmount(),"Failed to validate Balance Amount");
//			}
//			if(t24.getBody().get(i).getBeneficiaryId()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getBeneficiaryId());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getBeneficiaryId(), t24.getBody().get(i).getBeneficiaryId(),"Failed to validate BeneficiaryId");
//			}
//			if(t24.getBody().get(i).getBookingDate()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getBookingDate());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getBookingDate(), t24.getBody().get(i).getBookingDate(),"Failed to validate BookingDate");
//			}
//			if(t24.getBody().get(i).getCurrency()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getCurrency());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getCurrency(), t24.getBody().get(i).getCurrency(),"Failed to validate Currency");
//			}
//			if(t24.getBody().get(i).getCustomerId()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getCustomerId());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getCustomerId(), t24.getBody().get(i).getCustomerId(),"Failed to validate CustomerId");
//			}
//			if(t24.getBody().get(i).getCustomerName()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getCustomerName());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getCustomerName(), t24.getBody().get(i).getCustomerName(),"Failed to validate CustomerName");
//			}
//			if(t24.getBody().get(i).getDebitCreditIndicator()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getDebitCreditIndicator());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getDebitCreditIndicator(), t24.getBody().get(i).getDebitCreditIndicator(),"Failed to validate DebitCreditIndicator");
//			}
//			if(t24.getBody().get(i).getExcessAmount()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getExcessAmount());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getExcessAmount(), t24.getBody().get(i).getExcessAmount(),"Failed to validate ExcessAmount");
//			}
//			if(t24.getBody().get(i).getFederalTax()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getFederalTax());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getFederalTax(), t24.getBody().get(i).getFederalTax(),"Failed to validate FederalTax");
//			}
//			if(t24.getBody().get(i).getFederalTaxAfterPeriod()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getFederalTaxAfterPeriod());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getFederalTaxAfterPeriod(), t24.getBody().get(i).getFederalTaxAfterPeriod(),"Failed to validate FederalTaxAfterPeriod");
//			}
//			if(t24.getBody().get(i).getFederalTaxWithinPeriod()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getFederalTaxWithinPeriod());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getFederalTaxWithinPeriod(), t24.getBody().get(i).getFederalTaxWithinPeriod(),"Failed to validate FederalTaxWithinPeriod");
//			}
//			if(t24.getBody().get(i).getMinimumAmount()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getMinimumAmount());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getMinimumAmount(), t24.getBody().get(i).getMinimumAmount(),"Failed to validate MinimumAmount");
//			}
//			if(t24.getBody().get(i).getNonResidenceTax()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getNonResidenceTax());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getNonResidenceTax(), t24.getBody().get(i).getNonResidenceTax(),"Failed to validate NonResidenceTax");
//			}
//			if(t24.getBody().get(i).getNonResidenceTaxAfterPeriod()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getNonResidenceTaxAfterPeriod());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getNonResidenceTaxAfterPeriod(), t24.getBody().get(i).getNonResidenceTaxAfterPeriod(),"Failed to validate NonResidenceTaxAfterPeriod");
//			}
//			if(t24.getBody().get(i).getNonResidenceTaxWithinPeriod()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getNonResidenceTaxWithinPeriod());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getNonResidenceTaxWithinPeriod(), t24.getBody().get(i).getNonResidenceTaxWithinPeriod(),"Failed to validate NonResidenceTaxWithinPeriod");
//			}
//			if(t24.getBody().get(i).getOurCharges()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getOurCharges());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getOurCharges(), t24.getBody().get(i).getOurCharges(),"Failed to validate OurCharges");
//			}
//			if(t24.getBody().get(i).getPlanType()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getPlanType());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getPlanType(), t24.getBody().get(i).getPlanType(),"Failed to validate PlanType");
//			}
//			if(t24.getBody().get(i).getPortfolioId()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getPortfolioId());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getPortfolioId(), t24.getBody().get(i).getPortfolioId(),"Failed to validate PortfolioId");
//			}
//			if(t24.getBody().get(i).getProvince()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getProvince());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getProvince(), t24.getBody().get(i).getProvince(),"Failed to validate Province");
//			}
//			if(t24.getBody().get(i).getProvinceTax()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getProvinceTax());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getProvinceTax(), t24.getBody().get(i).getProvinceTax(),"Failed to validate ProvinceTax");
//			}
//			if(t24.getBody().get(i).getProvinceTaxAfterPeriod()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getProvinceTaxAfterPeriod());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getProvinceTaxAfterPeriod(), t24.getBody().get(i).getProvinceTaxAfterPeriod(),"Failed to validate ProvinceTaxAfterPeriod");
//			}
//			if(t24.getBody().get(i).getProvinceTaxWithinPeriod()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getProvinceTaxWithinPeriod());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getProvinceTaxWithinPeriod(), t24.getBody().get(i).getProvinceTaxWithinPeriod(),"Failed to validate ProvinceTaxWithinPeriod");
//			}
//			if(t24.getBody().get(i).getResidence()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getResidence());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getResidence(), t24.getBody().get(i).getResidence(),"Failed to validate Residence");
//			}
//			if(t24.getBody().get(i).getSignatureRequired()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getSignatureRequired());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getSignatureRequired(), t24.getBody().get(i).getSignatureRequired(),"Failed to validate SignatureRequired");
//			}
//			if(t24.getBody().get(i).getSpouse()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getSpouse());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getSpouse(), t24.getBody().get(i).getSpouse(),"Failed to validate Spouse");
//			}
//			if(t24.getBody().get(i).getTransactionAmount()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getTransactionAmount());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getTransactionAmount(), t24.getBody().get(i).getTransactionAmount(),"Failed to validate TransactionAmount");
//			}
//			if(t24.getBody().get(i).getTransactionCode()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getTransactionCode());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getTransactionCode(), t24.getBody().get(i).getTransactionCode(),"Failed to validate TransactionCode");
//			}
//			if(t24.getBody().get(i).getTransactionDescription()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getTransactionDescription());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getTransactionDescription(), t24.getBody().get(i).getTransactionDescription(),"Failed to validate TransactionDescription");
//			}
//			if(t24.getBody().get(i).getTransactionPurpose()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getTransactionPurpose());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getTransactionPurpose(), t24.getBody().get(i).getTransactionPurpose(),"Failed to validate TransactionPurpose");
//			}
//			if(t24.getBody().get(i).getTransactionReference()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getTransactionReference());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getTransactionReference(), t24.getBody().get(i).getTransactionReference(),"Failed to validate TransactionReference");
//			}
//			if(t24.getBody().get(i).getTransactionType()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getTransactionType());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getTransactionType(), t24.getBody().get(i).getTransactionType(),"Failed to validate TransactionType");
//			}
//			if(t24.getBody().get(i).getValueDate()==null) {
//				Assert.assertNull(dss.getRegisteredPlanTransactions().get(i).getValueDate());
//			}else {
//				Assert.assertEquals(dss.getRegisteredPlanTransactions().get(i).getValueDate(), t24.getBody().get(i).getValueDate(),"Failed to validate ValueDate");
//			}
//		}
//		} catch (Exception e) {
//			log.info("Error while comparing response DSS and Temenos");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	
//}
