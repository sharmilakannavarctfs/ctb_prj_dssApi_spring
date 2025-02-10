package com.ctfs.api.step;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import com.ctfs.api.pojos.request.GetTermRequestPojo;
import com.ctfs.api.pojos.response.GetTermResponsePojo;
import com.ctfs.api.service.telus.GetTermService;
import com.ctfs.api.session.SessionContext;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.temenos.api.util.TransactUtil;
import com.ctfs.temenos.dss.datobjects.T24TermDTO;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTermSteps extends AbstractStep{

	private final Logger log = LoggerFactory.getLogger(GetTermSteps.class);
	@Autowired
	private GetTermService getTermService;
	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	@Autowired 
	SessionContext sessionContext;
	String token;
	Map<String, String> requestParams= new HashMap<>();
	
	@DataTableType
	public GetTermRequestPojo getData(Map<String, String> entry) {
		requestParams.putAll(entry);
		return GetTermRequestPojo.setTermRequestPojo(entry);
	}
	
	@Given("Perform post operation to hit getTerm DSS api using request payload")
	public void perform_post_operation_to_hit_get_term_dss_api_using_request_payload(List<GetTermRequestPojo> termRequestPojos) {
		try {
			for(GetTermRequestPojo termRequestPojo: termRequestPojos) {
				getTermService.getTerm(termRequestPojo);
			}
			sessionContext.setTermRequests(termRequestPojos);
		} catch (Exception e) {
			log.info("Error while hitting getTerm API with request pojo");
			Assert.fail(e.getMessage());
		}    
	}
	
	
	@Given("Perform post operation to hit getTerm DSS api using {string} as {string} and {string} as {string} and {string} as {string}")
	public void perform_post_operation_to_hit_get_term_dss_api_using_as_and_as_and_as(String customerNumberValue, String customerNumber, String idValue, String id, String typeValue, String type) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put(customerNumber, customerNumberValue);        
			requestPayload.put(id, idValue);       
			requestPayload.put(type, typeValue);   
			getTermService.getTerm(requestPayload);
		} catch (Exception e) {
			log.info("Error while hitting getTerm API with request payload");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit getTerm DSS api using {string} as {string} and {string} as {string}")
	public void perform_post_operation_to_hit_get_term_dss_api_using_as_and_as(String value1, String field1, String value2, String field2) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put(field1, value1);        
			requestPayload.put(field2, value2);         
			getTermService.getTerm(requestPayload);
		} catch (Exception e) {
			log.info("Error while hitting getTerm API with request payload");
			Assert.fail(e.getMessage());
		}
	}

	@Given("Perform post operation to hit getTerm DSS api using {string} as {string}")
	public void perform_post_operation_to_hit_get_term_dss_api_using_as(String value, String field) {
		try {
			Map<String, Object> requestPayload = new HashMap<>();        
			requestPayload.put(field, value);                
			getTermService.getTerm(requestPayload);
		} catch (Exception e) {
			log.info("Error while hitting getTerm API with request payload");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit getTerm DSS api without payload")
	public void perform_post_operation_to_hit_get_term_dss_api_without_payload() {
		try {
			Map<String, Object> requestPayload = new HashMap<>();                     
			getTermService.getTerm(requestPayload);
		} catch (Exception e) {
			log.info("Error while hitting getTerm API without payload");
			Assert.fail(e.getMessage());
		}
	}
	
	@Given("Perform post operation to hit getTerm DSS api using request payload with expired Token")
	public void perform_post_operation_to_hit_get_term_dss_api_using_request_payload_with_expired_token(List<GetTermRequestPojo> termRequestPojos) {
		try {
			for(GetTermRequestPojo termRequestPojo: termRequestPojos) {
				getTermService.getTermWithToken(termRequestPojo,token);
			}
		} catch (Exception e) {
			log.info("Error while hitting getTerm API using request pojo with expired Token");
			Assert.fail(e.getMessage());
		}    
	}
	
	@Given("Perform post operation to hit getTerm DSS api using request payload with token does not match with Customer Number")
	public void perform_post_operation_to_hit_get_term_dss_api_using_request_payload_with_token_does_not_match_with_customer_number(List<GetTermRequestPojo> termRequestPojos) {
		try {
			for(GetTermRequestPojo termRequestPojo: termRequestPojos) {
				getTermService.getTermWithToken(termRequestPojo,token);
			}
		} catch (Exception e) {
			log.info("Error while hitting getTerm API using request pojo with token does not match with Customer Number");
			Assert.fail(e.getMessage());
		}  
	}
	
	@Given("Perform post operation to hit getTerm DSS api using request payload and disablePaginationheader as {string}")
	public void perform_post_operation_to_hit_get_term_dss_api_using_request_payload_and_disable_paginationheader_as(String pagination, List<GetTermRequestPojo> termRequestPojos) {
		try {
			for(GetTermRequestPojo termRequestPojo: termRequestPojos) {
				getTermService.getTermWithDisablePagination(termRequestPojo,pagination);
			}
		} catch (Exception e) {
			log.info("Error while hitting getTerm API using request pojo with Disable Pagination");
			Assert.fail(e.getMessage());
		}  
	}

	@Then("Validate getTerm DSS api response status code as {string}")
	public void validate_get_term_dss_api_response_status_code_as(String statusCode) {
		try {
			 //ProductTransactionsResponsePojo getProductTransactionsRoot = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("ProductTransactionService"), ProductTransactionsResponsePojo.class);
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermService");
			Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statusCode),  "FAILED!! Status code is not matching");
		} catch (Exception e) {
			log.info("Error while comparing getTerm API's status code");
			Assert.fail(e.getMessage());
		}
	}
	
	@Then("Validate getTerm DSS api response should match with schema")
	public void validate_get_term_dss_api_response_should_match_with_schema() {
		try {
			Response response = (Response)stepDefinitionDataManager.getStoredObjectMap().get("TermService");
			response.then().log().all().assertThat().body(matchesJsonSchemaInClasspath("Schema\\GetTermSchema.json"));
		} catch (Exception e) {
			log.info("Error while comparing getTerm API's response Schema");
			Assert.fail(e.getMessage());
		}
	}
	
	@Then("Validate getTerm DSS api error response should match with schema")
	public void validate_get_term_dss_api_error_response_should_match_with_schema() {
		try {
			Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("TermService");
			response.then().assertThat().body(matchesJsonSchemaInClasspath("schema\\ErrorSchema.json"));
		} catch (Exception e) {
			log.info("Error while comparing getTerm API's Error response Schema");
			Assert.fail(e.getMessage());
		}
	}

	@Then("Validate getTerm DSS api response")
	public void validate_get_term_dss_api_response() {
		try {
			TransactUtil transactUtil = new TransactUtil();
			for (GetTermRequestPojo TermRequest : sessionContext.getTermRequests()) {
				T24TermDTO t24TermDto=transactUtil.retrieveTerm(requestParams);
				GetTermResponsePojo dssResponsePojo = deserializeResponse((Response)stepDefinitionDataManager.getStoredObjectMap().get("TermService"), GetTermResponsePojo.class);
				compareResponse(dssResponsePojo,t24TermDto);
			}
		} catch (Exception e) {
			log.info("Error while comparing getTerm API's response with temenos API response");
			Assert.fail(e.getMessage());
		}
	}
	
	@Then("Validate {string} value in getTerm DSS api response should be {string}")
	public void validate_value_in_get_term_dss_api_response_should_be(String field, String value) {
		try {
		Response response=(Response)stepDefinitionDataManager.getStoredObjectMap().get("TermService");
		JsonPath jsonPath= new JsonPath(response.asString());
		Assert.assertEquals(jsonPath.getString(field), value,field +" value mismatch Found");
		} catch (Exception e) {
			log.info("Error while comparing getTerm API's Error response filed values");
			Assert.fail(e.getMessage());
		}
	}
	
	public void compareResponse(GetTermResponsePojo dss, T24TermDTO t24) {
		try {
		for(int i=0;i<t24.getBody().size();i++) {
			for(int j=0;j<t24.getBody().get(i).getCustomers().size();j++) {
				if(t24.getBody().get(i).getCustomers().get(j).getCustomerId()==null) {
					Assert.assertNull(dss.getTermProductOutput().get(i).getCustomers().get(j).getCustomerId());
				}
				else {
					Assert.assertEquals(dss.getTermProductOutput().get(i).getCustomers().get(j).getCustomerId(), t24.getBody().get(i).getCustomers().get(j).getCustomerId(),"Failed to validate CustomerId");
				}
				if(t24.getBody().get(i).getCustomers().get(j).getCustomerName()==null) {
					Assert.assertNull(dss.getTermProductOutput().get(i).getCustomers().get(j).getCustomerName());
				}
				else {
					Assert.assertEquals(dss.getTermProductOutput().get(i).getCustomers().get(j).getCustomerName(), t24.getBody().get(i).getCustomers().get(j).getCustomerName(),"Failed to validate CustomerName");
				}
				if(t24.getBody().get(i).getCustomers().get(j).getRoleDisplayName()==null) {
					Assert.assertNull(dss.getTermProductOutput().get(i).getCustomers().get(j).getRoleDisplayName());
				}
				else {
					Assert.assertEquals(dss.getTermProductOutput().get(i).getCustomers().get(j).getRoleDisplayName(), t24.getBody().get(i).getCustomers().get(j).getRoleDisplayName(),"Failed to validate RoleDisplayName");
				}	
			}
			for(int k=0;k<t24.getBody().get(i).getInterests().size();k++) {
				if(t24.getBody().get(i).getInterests().get(k).getInterestAmount()==null) {
					Assert.assertNull(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestAmount());
				}
				else {
					Assert.assertEquals(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestAmount(), t24.getBody().get(i).getInterests().get(k).getInterestAmount(),"Failed to validate InterestAmount");
				}
				if(t24.getBody().get(i).getInterests().get(k).getInterestProperty()==null) {
					Assert.assertNull(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestProperty());
				}
				else {
					Assert.assertEquals(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestProperty(), t24.getBody().get(i).getInterests().get(k).getInterestProperty(),"Failed to validate InterestProperty");
				}
				for(int l=0;l<t24.getBody().get(i).getInterests().get(k).getInterestRates().size();l++) {
					if(t24.getBody().get(i).getInterests().get(k).getInterestRates().get(l).getInterestRate()==null) {
						Assert.assertNull(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestRates().get(l).getInterestRate());
					}
					else {
						Assert.assertEquals(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestRates().get(l).getInterestRate(), t24.getBody().get(i).getInterests().get(k).getInterestRates().get(l).getInterestRate(),"Failed to validate InterestRate");
					}
					if(t24.getBody().get(i).getInterests().get(k).getInterestRates().get(l).getPeriod()==null) {
						Assert.assertNull(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestRates().get(l).getPeriod());
					}
					else {
						Assert.assertEquals(dss.getTermProductOutput().get(i).getInterests().get(k).getInterestRates().get(l).getPeriod(), t24.getBody().get(i).getInterests().get(k).getInterestRates().get(l).getPeriod(),"Failed to validate Period");
					}
				}	
			}
			if(t24.getBody().get(0).getPostingRestrictDisplayNames()!=null) {
				for(int m=0;m<t24.getBody().get(i).getPostingRestrictDisplayNames().size();m++) {
					if(t24.getBody().get(i).getPostingRestrictDisplayNames().get(m).getPostingRestrictDisplayName()==null) {
					Assert.assertNull(dss.getTermProductOutput().get(i).getPostingRestrictDisplayNames().get(m).getPostingRestrictDisplayName());
					}
					else {
					Assert.assertEquals(dss.getTermProductOutput().get(i).getPostingRestrictDisplayNames().get(m).getPostingRestrictDisplayName(), t24.getBody().get(i).getPostingRestrictDisplayNames().get(m).getPostingRestrictDisplayName(),"Failed to validate Posting Restrict DisplayName");
					}	
			}
			}
			if(t24.getBody().get(i).getArrangementId()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getArrangementId());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getArrangementId(), t24.getBody().get(i).getArrangementId(),"Failed to validate ArrangementId");
			}
			if(t24.getBody().get(i).getNumericCurrencyCode()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getNumericCurrencyCode());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getNumericCurrencyCode(), t24.getBody().get(i).getNumericCurrencyCode(),"Failed to validate Numeric CurrencyCode");
			}
			if(t24.getBody().get(i).getSettlementCurrency()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getSettlementCurrency());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getSettlementCurrency(), t24.getBody().get(i).getSettlementCurrency(),"Failed to validate Settlement Currency");
			}
			if(t24.getBody().get(i).getOriginalContractDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getOriginalContractDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getOriginalContractDate(), t24.getBody().get(i).getOriginalContractDate(),"Failed to validate Original ContractDate");
			}
			if(t24.getBody().get(i).getRenewalDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getRenewalDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getRenewalDate(), t24.getBody().get(i).getRenewalDate(),"Failed to validate getRenewalDate");
			}
			if(t24.getBody().get(i).getFirstInterestDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getFirstInterestDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getFirstInterestDate(), t24.getBody().get(i).getFirstInterestDate(),"Failed to validate FirstInterestDate");
			}
			if(t24.getBody().get(i).getNextInterestDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getNextInterestDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getNextInterestDate(), t24.getBody().get(i).getNextInterestDate(),"Failed to validate NextInterestDate");
			}
			if(t24.getBody().get(i).getMaturityIndicator()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getMaturityIndicator());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getMaturityIndicator(), t24.getBody().get(i).getMaturityIndicator(),"Failed to validate MaturityIndicator");
			}
			if(t24.getBody().get(i).getPortfolio()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getPortfolio());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getPortfolio(), t24.getBody().get(i).getPortfolio(),"Failed to validate Portfolio");
			}
			if(t24.getBody().get(i).getPlanType()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getPlanType());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getPlanType(), t24.getBody().get(i).getPlanType(),"Failed to validate PlanType");
			}
			if(t24.getBody().get(i).getRenewalRate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getRenewalRate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getRenewalRate(), t24.getBody().get(i).getRenewalRate(),"Failed to validate RenewalRate");
			}
			if(t24.getBody().get(i).getRolloverType()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getRolloverType());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getRolloverType(), t24.getBody().get(i).getRolloverType(),"Failed to validate RolloverType");
			}
			if(t24.getBody().get(i).getTerm()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getTerm());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getTerm(), t24.getBody().get(i).getTerm(),"Failed to validate Term");
			}
			if(t24.getBody().get(i).getPayoutAccountId()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getPayoutAccountId());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getPayoutAccountId(), t24.getBody().get(i).getPayoutAccountId(),"Failed to validate Payout AccountId");
			}
			if(t24.getBody().get(i).getTransferIndicator()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getTransferIndicator());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getTransferIndicator(), t24.getBody().get(i).getTransferIndicator(),"Failed to validate Transfer Indicator");
			}
			if(t24.getBody().get(i).getLockedFlag()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getLockedFlag());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getLockedFlag(), t24.getBody().get(i).getLockedFlag(),"Failed to validate LockedFlag");
			}
			if(t24.getBody().get(i).getSweepExempt()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getSweepExempt());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getSweepExempt(), t24.getBody().get(i).getSweepExempt(),"Failed to validate SweepExempt");
			}
			if(t24.getBody().get(i).getInterestPayoutIndicator()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getInterestPayoutIndicator());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getInterestPayoutIndicator(), t24.getBody().get(i).getInterestPayoutIndicator(),"Failed to validate Interest PayoutIndicator");
			}
			if(t24.getBody().get(i).getPrincipal()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getPrincipal());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getPrincipal(), t24.getBody().get(i).getPrincipal(),"Failed to validate Principal");
			}
			if(t24.getBody().get(i).getProductGroup()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getProductGroup());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getProductGroup(), t24.getBody().get(i).getProductGroup(),"Failed to validate ProductGroup");
			}
			if(t24.getBody().get(i).getNickName()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getNickName());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getNickName(), t24.getBody().get(i).getNickName(),"Failed to validate NickName");
			}
			if(t24.getBody().get(i).getAccountTitle1()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getAccountTitle1());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getAccountTitle1(), t24.getBody().get(i).getAccountTitle1(),"Failed to validate AccountTitle1");
			}
			if(t24.getBody().get(i).getAccountTitle2()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getAccountTitle2());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getAccountTitle2(), t24.getBody().get(i).getAccountTitle2(),"Failed to validate AccountTitle2");
			}
			if(t24.getBody().get(i).getCompanyCode()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getCompanyCode());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getCompanyCode(), t24.getBody().get(i).getCompanyCode(),"Failed to validate CompanyCode");
			}
			if(t24.getBody().get(i).getLastRenewalDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getLastRenewalDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getLastRenewalDate(), t24.getBody().get(i).getLastRenewalDate(),"Failed to validate LastRenewalDate");
			}
			if(t24.getBody().get(i).getDepAccountId()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepAccountId());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepAccountId(), t24.getBody().get(i).getDepAccountId(),"Failed to validate DepAccountId");
			}
			if(t24.getBody().get(i).getDepProduct()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepProduct());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepProduct(), t24.getBody().get(i).getDepProduct(),"Failed to validate DepProduct");
			}
			if(t24.getBody().get(i).getDepCurrency()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepCurrency());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepCurrency(), t24.getBody().get(i).getDepCurrency(),"Failed to validate DepCurrency");
			}
			if(t24.getBody().get(i).getDepAmount()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepAmount());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepAmount(), t24.getBody().get(i).getDepAmount(),"Failed to validate DepAmount");
			}
			if(t24.getBody().get(i).getDepInterestType()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepInterestType());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepInterestType(), t24.getBody().get(i).getDepInterestType(),"Failed to validate DepInterestType");
			}
			if(t24.getBody().get(i).getDepInterestRate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepInterestRate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepInterestRate(), t24.getBody().get(i).getDepInterestRate(),"Failed to validate DepInterestRate");
			}
			if(t24.getBody().get(i).getDepStartDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepStartDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepStartDate(), t24.getBody().get(i).getDepStartDate(),"Failed to validate DepStartDate");
			}
			if(t24.getBody().get(i).getDepEndDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepEndDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepEndDate(), t24.getBody().get(i).getDepEndDate(),"Failed to validate DepEndDate");
			}
			if(t24.getBody().get(i).getDepBalance()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepBalance());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepBalance(), t24.getBody().get(i).getDepBalance(),"Failed to validate DepBalance");
			}
			if(t24.getBody().get(i).getDepNextPayDate()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepNextPayDate());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepNextPayDate(), t24.getBody().get(i).getDepNextPayDate(),"Failed to validate DepNextPayDate");
			}
			if(t24.getBody().get(i).getDepProductDescription()==null) {
				Assert.assertNull(dss.getTermProductOutput().get(i).getDepProductDescription());
			}
			else {
				Assert.assertEquals(dss.getTermProductOutput().get(i).getDepProductDescription(), t24.getBody().get(i).getDepProductDescription(),"Failed to validate DepProductDescription");
			}
		}
		} catch (Exception e) {
			log.info("Error while comparing getTerm API's Response with Temenos response");
			Assert.fail(e.getMessage());
		}
		
		
	}
}
