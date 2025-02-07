package com.ctfs.api.service;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctfs.api.annotations.RetailBankService;
import com.ctfs.api.base.BaseExecutor;
import com.ctfs.api.endpoints.Endpoints;
import com.ctfs.api.servicehelper.APIService;
import com.ctfs.common.service.RestService;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.common.utils.ApplicationProperties;

import io.restassured.response.Response;

@RetailBankService
public class GetDemandInterestRateService extends BaseExecutor{
	protected GetDemandInterestRateService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties,"TELUSWBSERVICE_V1");
		// TODO Auto-generated constructor stub
	}
	
	private final Logger log = LoggerFactory.getLogger(GetDemandInterestRateService.class);
    
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;
    
	public void getDemandInterestRates(Object requestbody) throws URISyntaxException { 
		try {
			setBody(requestbody);
	        Response response = post(Endpoints.getDemandInterestRate); 
	        stepDefinitionDataManager.addToStoredObjectMap("DemandInterestRateService", response);
	        log.info("response getStatusLine: "+response.getStatusLine());
	        log.info("response getBody: "+response.getBody().asPrettyString());
	        
		} catch (Exception e) {
			e.getStackTrace();
		}
    }
	
	public void getDemandInterestRatesWithToken(Object requestbody, Object token) {
		try {
			setBody(requestbody);
			setHeader(token);
			Response response = post(Endpoints.getDemandInterestRate);
			stepDefinitionDataManager.addToStoredObjectMap("DemandInterestRateService", response);
			log.info("request body: " + requestbody);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());
		}catch(Exception e) {
			log.info(e.getMessage(),"Error while hitting API with Token");
		}
	}
	
	public void getDemandInterestRatesWithDisablePagination(Object requestbody, Object pagination) {
		try {
			setBody(requestbody);
			setHeader(pagination);
			Response response = post(Endpoints.getDemandInterestRate);
			stepDefinitionDataManager.addToStoredObjectMap("DemandInterestRateService", response);
			log.info("request body: " + requestbody);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());
		}catch(Exception e) {
			log.info(e.getMessage(),"Error while hitting API with pagination");
		}
	}

}
