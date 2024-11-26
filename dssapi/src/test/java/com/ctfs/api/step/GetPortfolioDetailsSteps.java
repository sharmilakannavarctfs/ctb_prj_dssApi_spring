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
//import com.ctfs.api.pojos.request.GetPortfoliosRequestPojo;
//import com.ctfs.api.pojos.request.PortfolioDetailsRequestPojo;
//import com.ctfs.api.pojos.request.ProductDetailsRequestPojo;
//import com.ctfs.api.pojos.response.GetPortfoliosResponsePojo;
//import com.ctfs.api.pojos.response.PortfolioDetailsResponsePojo;
//import com.ctfs.api.pojos.response.ProductDetailsResponsePojo;
//import com.ctfs.api.service.GetPortfolioDetailsService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.pojos.T24PortfolioDetailsPojo;
//import com.ctfs.temenos.api.pojos.T24ProductDetailsPojo;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24PortfolioDetailsDTO;
//import com.ctfs.temenos.dss.datobjects.T24PortfoliosDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.*;
//import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class GetPortfolioDetailsSteps extends AbstractStep{
//	
//	
//	private final Logger log = LoggerFactory.getLogger(GetProductTransactionsSteps.class);
//	@Autowired
//	private GetPortfolioDetailsService getPortfolioDetailsService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
////	T24PortfolioDetailsDTO t24PortfolioDetailsDTO;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public PortfolioDetailsRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return PortfolioDetailsRequestPojo.setPortfolioDetailsRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getPortfolioDetails DSS api using request payload")
//	public void perform_post_operation_to_hit_get_portfolio_details_dss_api_using_request_payload(List<PortfolioDetailsRequestPojo> portfolioDetailsRequestPojos) throws URISyntaxException{
//		try {
//			for(PortfolioDetailsRequestPojo portfolioDetailsRequestPojo: portfolioDetailsRequestPojos) {
//				getPortfolioDetailsService.getPortfolioDetails(portfolioDetailsRequestPojo);
//			}
//			sessionContext.setPortfolioDetailsRequests(portfolioDetailsRequestPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolioDetails API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//
//	@Then("Validate getPortfolioDetails DSS api response status code as {string}")
//	public void validate_get_portfolio_details_dss_api_response_status_code_as(String statusCode) {
//		try {
//			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfolioDetailsService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),"FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolioDetails API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	
//	@Then("Validate getPortfolioDetails DSS api response should match with schema")
//	public void validate_get_portfolio_details_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfolioDetailsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getPortfolioDetailsSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolioDetails API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getPortfolioDetails DSS api response")
//	public void validate_get_portfolio_details_dss_api_response() {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (PortfolioDetailsRequestPojo portfolioDetailsRequest : sessionContext.getPortfolioDetailsRequests()) {
//				T24PortfolioDetailsDTO t24PortfolioDetailsDTO=transactUtil.retrievePortfolioDetails(requestParams);
//				PortfolioDetailsResponsePojo portfolioDetailsResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfolioDetailsService"), PortfolioDetailsResponsePojo.class);
//				comparePortfolioDetails(portfolioDetailsResponsePojo,t24PortfolioDetailsDTO);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.info("Error while comparing getPortfolioDetails API's response with temenos response");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate {string} value in getPortfolioDetails DSS api response should be {string}")
//	public void validate_value_in_get_portfolio_details_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfolioDetailsService");
//			JsonPath jsonPath=new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field),value,"field value inside response is different");
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolioDetails API's field values with wrong input");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Given("Perform post operation to hit getPortfolioDetails DSS api without payload")
//	public void perform_post_operation_to_hit_get_portfolio_details_dss_api_without_payload() {
//		try {  
//			getPortfolioDetailsService.getPortfolioDetails("{}");;
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolioDetails API without payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getPortfolioDetails DSS api error response should match with schema")
//	public void validate_get_portfolio_details_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfolioDetailsService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getPortfolioDetails DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_portfolio_details_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);          
//			getPortfolioDetailsService.getPortfolioDetails(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getPortfolioDetails DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_portfolio_details_dss_api_using_request_payload_with_expired_token(List<PortfolioDetailsRequestPojo> portfolioDetailsPojos) {
//		try {
//			for(PortfolioDetailsRequestPojo portfolioDetailsPojo: portfolioDetailsPojos) {
//				getPortfolioDetailsService.getPortfolioDetailsWithToken(portfolioDetailsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Pojo with Expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getPortfolioDetails DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_portfolio_details_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<PortfolioDetailsRequestPojo> portfolioDetailsPojos) {
//		try {
//			for(PortfolioDetailsRequestPojo portfolioDetailsPojo: portfolioDetailsPojos) {
//				getPortfolioDetailsService.getPortfolioDetailsWithToken(portfolioDetailsPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getPortfolioDetails DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_portfolio_details_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<PortfolioDetailsRequestPojo> portfolioDetailsPojos) {
//		try {
//			for(PortfolioDetailsRequestPojo portfolioDetailsPojo: portfolioDetailsPojos) {
//				getPortfolioDetailsService.getPortfolioDetailsWithDisablePagination(portfolioDetailsPojo, pagination);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	public void comparePortfolioDetails(PortfolioDetailsResponsePojo portfolioDetailsResponsePojo,T24PortfolioDetailsDTO t24PortfolioDetailsDTO) {
//		for(int i=0;i<t24PortfolioDetailsDTO.getBody().size();i++) {
//			for(int j=0;j<t24PortfolioDetailsDTO.getBody().get(i).getAccountIds().size();j++) {
//				if(t24PortfolioDetailsDTO.getBody().get(i).getAccountIds().get(j).getAccountId()==null) {
//					Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getAccountIds().get(j).getAccountId());
//				}
//				else {
//					Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getAccountIds().get(j).getAccountId(), t24PortfolioDetailsDTO.getBody().get(i).getAccountIds().get(j).getAccountId(),"Failed to validate getAccountId");
//				}
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails()!=null) {
//			for(int j=0;j<t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails().size();j++) {
//				if(t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails().get(j).getBeneficiary()==null) {
//					Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDetails().get(j).getBeneficiary());
//				}
//				else {
//					Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDetails().get(j).getBeneficiary(), t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails().get(j).getBeneficiary(),"Failed to validate getBeneficiary");
//				}
//				if(t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails().get(j).getBeneficiaryRole()==null) {
//					Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDetails().get(j).getBeneficiaryRole());
//				}
//				else {
//					Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDetails().get(j).getBeneficiaryRole(), t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails().get(j).getBeneficiaryRole(),"Failed to validate getBeneficiaryRole");
//				}
//				if(t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails().get(j).getPercentage()==null) {
//					Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDetails().get(j).getPercentage());
//				}
//				else {
//					Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDetails().get(j).getPercentage(), t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDetails().get(j).getPercentage(),"Failed to validate getPercentage");
//				}
//			}
//			}
//			
//			if(t24PortfolioDetailsDTO.getBody().get(i).getPortfolioId()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPortfolioId());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPortfolioId(), t24PortfolioDetailsDTO.getBody().get(i).getPortfolioId(),"Failed to validate getPortfolioId");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getBranchId()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBranchId());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBranchId(), t24PortfolioDetailsDTO.getBody().get(i).getBranchId(),"Failed to validate getBranchId");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getContributionTillDate()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getContributionTillDate());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getContributionTillDate(), t24PortfolioDetailsDTO.getBody().get(i).getContributionTillDate(),"Failed to validate getContributionTillDate");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getContributionSincePlanDate()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getContributionSincePlanDate());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getContributionSincePlanDate(), t24PortfolioDetailsDTO.getBody().get(i).getContributionSincePlanDate(),"Failed to validate getContributionSincePlanDate");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getBalance()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBalance());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBalance(), t24PortfolioDetailsDTO.getBody().get(i).getBalance(),"Failed to validate getBalance");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getOpenDate()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getOpenDate());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getOpenDate(), t24PortfolioDetailsDTO.getBody().get(i).getOpenDate(),"Failed to validate getOpenDate");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getPlanType()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPlanType());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPlanType(), t24PortfolioDetailsDTO.getBody().get(i).getPlanType(),"Failed to validate getPlanType");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getTitle()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getTitle());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getTitle(), t24PortfolioDetailsDTO.getBody().get(i).getTitle(),"Failed to validate getTitle");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getHolderName()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getHolderName());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getHolderName(), t24PortfolioDetailsDTO.getBody().get(i).getHolderName(),"Failed to validate getHolderName");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getDateOfBirth()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDateOfBirth());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDateOfBirth(), t24PortfolioDetailsDTO.getBody().get(i).getDateOfBirth(),"Failed to validate getDateOfBirth");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getDateOfDeath()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDateOfDeath());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDateOfDeath(), t24PortfolioDetailsDTO.getBody().get(i).getDateOfDeath(),"Failed to validate getDateOfDeath");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getAdditionalTitle()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getAdditionalTitle());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getAdditionalTitle(), t24PortfolioDetailsDTO.getBody().get(i).getAdditionalTitle(),"Failed to validate getAdditionalTitle");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryName()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryName());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryName(), t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryName(),"Failed to validate getBeneficiaryName");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDOB()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDOB());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDOB(), t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDOB(),"Failed to validate getBeneficiaryDOB");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDateOfDeath()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDateOfDeath());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getBeneficiaryDateOfDeath(), t24PortfolioDetailsDTO.getBody().get(i).getBeneficiaryDateOfDeath(),"Failed to validate getBeneficiaryDateOfDeath");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getLockedInIndicator()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getLockedInIndicator());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getLockedInIndicator(), t24PortfolioDetailsDTO.getBody().get(i).getLockedInIndicator(),"Failed to validate getLockedInIndicator");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getPaymentAmount()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentAmount());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentAmount(), t24PortfolioDetailsDTO.getBody().get(i).getPaymentAmount(),"Failed to validate getPaymentAmount");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getPaymentDate()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentDate());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentDate(), t24PortfolioDetailsDTO.getBody().get(i).getPaymentDate(),"Failed to validate getPaymentDate");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getLastPaymentDate()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getLastPaymentDate());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getLastPaymentDate(), t24PortfolioDetailsDTO.getBody().get(i).getLastPaymentDate(),"Failed to validate getLastPaymentDate");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getPaymentFrequency()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentFrequency());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentFrequency(), t24PortfolioDetailsDTO.getBody().get(i).getPaymentFrequency(),"Failed to validate getPaymentFrequency");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getPaymentIndicator()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentIndicator());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPaymentIndicator(), t24PortfolioDetailsDTO.getBody().get(i).getPaymentIndicator(),"Failed to validate getPaymentIndicator");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getDefaultSettlementAccount()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDefaultSettlementAccount());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDefaultSettlementAccount(), t24PortfolioDetailsDTO.getBody().get(i).getDefaultSettlementAccount(),"Failed to validate getDefaultSettlementAccount");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getPlanGroupId()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPlanGroupId());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getPlanGroupId(), t24PortfolioDetailsDTO.getBody().get(i).getPlanGroupId(),"Failed to validate getPlanGroupId");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getHolderReference()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getHolderReference());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getHolderReference(), t24PortfolioDetailsDTO.getBody().get(i).getHolderReference(),"Failed to validate getHolderReference");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getDepositoryId()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDepositoryId());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getDepositoryId(), t24PortfolioDetailsDTO.getBody().get(i).getDepositoryId(),"Failed to validate getDepositoryId");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getSubAccount()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getSubAccount());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getSubAccount(), t24PortfolioDetailsDTO.getBody().get(i).getSubAccount(),"Failed to validate getSubAccount");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getSubAcctExtId()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getSubAcctExtId());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getSubAcctExtId(), t24PortfolioDetailsDTO.getBody().get(i).getSubAcctExtId(),"Failed to validate getSubAcctExtId");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getValuationCurrency()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getValuationCurrency());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getValuationCurrency(), t24PortfolioDetailsDTO.getBody().get(i).getValuationCurrency(),"Failed to validate getValuationCurrency");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getAccountName()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getAccountName());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getAccountName(), t24PortfolioDetailsDTO.getBody().get(i).getAccountName(),"Failed to validate getAccountName");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getInvestmentProgram()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getInvestmentProgram());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getInvestmentProgram(), t24PortfolioDetailsDTO.getBody().get(i).getInvestmentProgram(),"Failed to validate getInvestmentProgram");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getManagedAccount()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getManagedAccount());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getManagedAccount(), t24PortfolioDetailsDTO.getBody().get(i).getManagedAccount(),"Failed to validate getManagedAccount");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getReferenceCurrency()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getReferenceCurrency());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getReferenceCurrency(), t24PortfolioDetailsDTO.getBody().get(i).getReferenceCurrency(),"Failed to validate getReferenceCurrency");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getValuationAmt()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getValuationAmt());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getValuationAmt(), t24PortfolioDetailsDTO.getBody().get(i).getValuationAmt(),"Failed to validate getValuationAmt");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getCustomerId()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getCustomerId());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getCustomerId(), t24PortfolioDetailsDTO.getBody().get(i).getCustomerId(),"Failed to validate getCustomerId");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getCustomerName()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getCustomerName());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getCustomerName(), t24PortfolioDetailsDTO.getBody().get(i).getCustomerName(),"Failed to validate getCustomerName");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getJointCustomer()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getJointCustomer());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getJointCustomer(), t24PortfolioDetailsDTO.getBody().get(i).getJointCustomer(),"Failed to validate getJointCustomer");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getJointCustomerName()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getJointCustomerName());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getJointCustomerName(), t24PortfolioDetailsDTO.getBody().get(i).getJointCustomerName(),"Failed to validate getJointCustomerName");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getUnrealProfitLoss()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getUnrealProfitLoss());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getUnrealProfitLoss(), t24PortfolioDetailsDTO.getBody().get(i).getUnrealProfitLoss(),"Failed to validate getUnrealProfitLoss");
//			}
//			if(t24PortfolioDetailsDTO.getBody().get(i).getUnrealisedProfitLossPercentage()==null) {
//				Assert.assertNull(portfolioDetailsResponsePojo.getPortFolioList().get(i).getUnrealisedProfitLossPercentage());
//			}
//			else {
//				Assert.assertEquals(portfolioDetailsResponsePojo.getPortFolioList().get(i).getUnrealisedProfitLossPercentage(), t24PortfolioDetailsDTO.getBody().get(i).getUnrealisedProfitLossPercentage(),"Failed to validate getUnrealisedProfitLossPercentage");
//			}
//		}
//	}
//	
//
//}
