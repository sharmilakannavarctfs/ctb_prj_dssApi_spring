//package com.ctfs.api.step;
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
//import com.ctfs.api.pojos.response.GetPortfolios;
//import com.ctfs.api.pojos.response.GetPortfoliosResponsePojo;
//import com.ctfs.api.service.GetPortfoliosService;
//import com.ctfs.api.session.SessionContext;
//import com.ctfs.common.service.StepDefinitionDataManager;
//import com.ctfs.temenos.api.pojos.T24PortfoliosPojo;
//import com.ctfs.temenos.api.util.TransactUtil;
//import com.ctfs.temenos.dss.datobjects.T24PortfoliosDTO;
//
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//public class GetPortfoliosSteps extends AbstractStep{
//	
//	private final Logger log = LoggerFactory.getLogger(GetPortfoliosSteps.class);
//	@Autowired
//	private GetPortfoliosService getPortfoliosService;
//	@Autowired
//	private StepDefinitionDataManager stepDefinitionDataManager;
//	@Autowired
//	SessionContext sessionContext;
//	String token;
//	Map<String, String> requestParams= new HashMap<>();
//	
//	@DataTableType
//	public GetPortfoliosRequestPojo getData(Map<String, String> entry) {
//		requestParams.putAll(entry);
//		return GetPortfoliosRequestPojo.setPortfoliosRequestPojo(entry);
//	}
//	
//	@Given("Perform post operation to hit getPortfolios DSS api using request payload")
//	public void perform_post_operation_to_hit_get_portfolios_dss_api_using_request_payload(List<GetPortfoliosRequestPojo> portfoliosPojos) {
//		try {
//			for(GetPortfoliosRequestPojo portfoliosPojo: portfoliosPojos) {
//				getPortfoliosService.getPortfolios(portfoliosPojo);
//			}
//			sessionContext.setPortfoliosRequests(portfoliosPojos);
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Pojo");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getPortfolios DSS api using {string} as {string}")
//	public void perform_post_operation_to_hit_get_portfolios_dss_api_using_as(String value, String field) {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();        
//			requestPayload.put(field, value);          
//			getPortfoliosService.getPortfolios(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	
//	@Given("Perform post operation to hit getPortfolios DSS api without payload")
//	public void perform_post_operation_to_hit_get_portfolios_dss_api_without_payload() {
//		try {
//			Map<String, Object> requestPayload = new HashMap<>();                  
//			getPortfoliosService.getPortfolios(requestPayload);
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API without Payload");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getPortfolios DSS api using request payload with expired Token")
//	public void perform_post_operation_to_hit_get_portfolios_dss_api_using_request_payload_with_expired_token(List<GetPortfoliosRequestPojo> portfoliosPojos) {
//		try {
//			for(GetPortfoliosRequestPojo portfoliosPojo: portfoliosPojos) {
//				getPortfoliosService.getPortfoliosWithToken(portfoliosPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Pojo with Expired Token");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//
//	@Given("Perform post operation to hit getPortfolios DSS api using request payload with token does not match with Customer Number")
//	public void perform_post_operation_to_hit_get_portfolios_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<GetPortfoliosRequestPojo> portfoliosPojos) {
//		try {
//			for(GetPortfoliosRequestPojo portfoliosPojo: portfoliosPojos) {
//				getPortfoliosService.getPortfoliosWithToken(portfoliosPojo, token );
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Pojo with token does not match with Customer Number");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Given("Perform post operation to hit getPortfolios DSS api using request payload and disablePaginationheader as {string}")
//	public void perform_post_operation_to_hit_get_portfolios_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<GetPortfoliosRequestPojo> portfoliosPojos) {
//		try {
//			for(GetPortfoliosRequestPojo portfoliosPojo: portfoliosPojos) {
//				getPortfoliosService.getPortfoliosWithDisablePagination(portfoliosPojo, pagination);
//			}
//		} catch (Exception e) {
//			log.info("Error while hitting getPortfolios API using request Pojo with disablePaginationheader");
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	@Then("Validate getPortfolios DSS api response status code as {string}")
//	public void validate_get_portfolios_dss_api_response_status_code_as(String statusCode) {
//		try {
//			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfoliosService");
//			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode), "FAILED!! Status code is not matching");
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's status code");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getPortfolios DSS api response should match with schema")
//	public void validate_get_portfolios_dss_api_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfoliosService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\GetPortfoliosSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getPortfolios DSS api error response should match with schema")
//	public void validate_get_portfolios_dss_api_error_response_should_match_with_schema() {
//		try {
//			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfoliosService");
//			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's Error response Schema");
//			Assert.fail(e.getMessage());
//		}
//	}
//	
//	@Then("Validate getPortfolios DSS api response")
//	public void validate_get_portfolios_dss_api_response() throws URISyntaxException {
//		try {
//			TransactUtil transactUtil = new TransactUtil();
//			for (GetPortfoliosRequestPojo portfoliosRequest : sessionContext.getPortfoliosRequests()) {	
//				T24PortfoliosDTO t24PortfoliosDTO=transactUtil.retrievePortfolios(requestParams);
//				GetPortfoliosResponsePojo getPortfoliosPojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfoliosService"), GetPortfoliosResponsePojo.class);
//				comparePortfolios(getPortfoliosPojo,t24PortfoliosDTO);
//			}	
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's response with temenos API response");
//			Assert.fail(e.getMessage());
//		}	
//	}
//	
//	@Then("Validate {string} value in getPortfolios DSS api response should be {string}")
//	public void validate_value_in_get_portfolios_dss_api_response_should_be(String field, String value) {
//		try {
//			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("PortfoliosService");
//			JsonPath jsonPath= new JsonPath(response.asString());
//			Assert.assertEquals(jsonPath.getString(field), value,field +" value mismatch Found");
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's Error response filed values");
//			Assert.fail(e.getMessage());
//		}	
//	}
//
//	public void comparePortfolios(GetPortfoliosResponsePojo getPortfoliosPojo,T24PortfoliosDTO t24PortfoliosDTO) {
//		try {
//			for(int i=0;i<t24PortfoliosDTO.getGetPortfolios().size();i++) {
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getAccountName()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getAccountName());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getAccountName(),t24PortfoliosDTO.getGetPortfolios().get(i).getAccountName(),"Failed to validate Account Name");
//				}
//				Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getUnrealProfitLoss(),t24PortfoliosDTO.getGetPortfolios().get(i).getUnrealProfitLoss(),"Failed to validate Unreal Profit Loss");
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getJointCustomerName()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getJointCustomerName());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getJointCustomerName(),t24PortfoliosDTO.getGetPortfolios().get(i).getJointCustomerName(),"Failed to validate JointCustomerName");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getReferenceCurrency()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getReferenceCurrency());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getReferenceCurrency(),t24PortfoliosDTO.getGetPortfolios().get(i).getReferenceCurrency(),"Failed to validate ReferenceCurrency");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getValuationCurrency()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getValuationCurrency());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getValuationCurrency(),t24PortfoliosDTO.getGetPortfolios().get(i).getValuationCurrency(),"Failed to validate ValuationCurrency");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getUnrealisedProfitLossPercentage()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getUnrealisedProfitLossPercentage());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getUnrealisedProfitLossPercentage(),t24PortfoliosDTO.getGetPortfolios().get(i).getUnrealisedProfitLossPercentage(),"Failed to validate UnrealisedProfitLossPercentage");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getJointCustomer()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getJointCustomer());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getJointCustomer(),t24PortfoliosDTO.getGetPortfolios().get(i).getJointCustomer(),"Failed to validate JointCustomer");
//				}
//				Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getValuationAmt(),t24PortfoliosDTO.getGetPortfolios().get(i).getValuationAmt(),"Failed to validate ValuationAmt");
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getCustomerName()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getCustomerName());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getCustomerName(),t24PortfoliosDTO.getGetPortfolios().get(i).getCustomerName(),"Failed to validate CustomerName");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getManagedAccount()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getManagedAccount());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getManagedAccount(),t24PortfoliosDTO.getGetPortfolios().get(i).getManagedAccount(),"Failed to validate ManagedAccount");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getPortfolioId()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getPortfolioId());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getPortfolioId(),t24PortfoliosDTO.getGetPortfolios().get(i).getPortfolioId(),"Failed to validate PortfolioId");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getInvestmentProgram()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getInvestmentProgram());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getInvestmentProgram(),t24PortfoliosDTO.getGetPortfolios().get(i).getInvestmentProgram(),"Failed to validate InvestmentProgram");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getCustomerId()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getCustomerId());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getCustomerId(),t24PortfoliosDTO.getGetPortfolios().get(i).getCustomerId(),"Failed to validate CustomerId");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getDepositoryId()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getDepositoryId());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getDepositoryId(),t24PortfoliosDTO.getGetPortfolios().get(i).getDepositoryId(),"Failed to validate DepositoryId");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getSubAccount()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getSubAccount());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getSubAccount(),t24PortfoliosDTO.getGetPortfolios().get(i).getSubAccount(),"Failed to validate SubAccount");
//				}
//				if(t24PortfoliosDTO.getGetPortfolios().get(i).getSubAcctExtId()==null) {
//					Assert.assertNull(getPortfoliosPojo.getGetPortfolios().get(i).getSubAcctExtId());
//				}
//				else {
//					Assert.assertEquals(getPortfoliosPojo.getGetPortfolios().get(i).getSubAcctExtId(),t24PortfoliosDTO.getGetPortfolios().get(i).getSubAcctExtId(),"Failed to validate SubAcctExtId");
//				}
//			}
//		} catch (Exception e) {
//			log.info("Error while comparing getPortfolios API's response with temenos API");
//			Assert.fail(e.getMessage());
//		}
//	}
//		
//}
