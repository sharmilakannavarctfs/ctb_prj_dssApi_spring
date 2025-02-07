package com.ctfs.api.service.telus;

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
public class GetTermPeriodicLineFeatureService extends BaseExecutor{

	protected GetTermPeriodicLineFeatureService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties,"TELUSWBSERVICE_V1");
		// TODO Auto-generated constructor stub
	}
	
private final Logger log = LoggerFactory.getLogger(GetTermPeriodicLineFeatureService.class);
    
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;
    
	public void getTermPeriodicLineFeature(Object requestbody) throws URISyntaxException { 
		try {
			log.info("request body: "+ requestbody);
			setBody(requestbody);
	        Response response = post(Endpoints.getTermPeriodicLineFeature); 
	        stepDefinitionDataManager.addToStoredObjectMap("TermPeriodicLineFeatureService", response);
	        log.info("response getStatusLine: "+response.getStatusLine());
	        log.info("response getBody: "+response.getBody().asPrettyString());
	        
		} catch (Exception e) {
			log.info("Error while hitting API in Service class");
		}
    }
	
	public void getTermPeriodicLineFeatureWithToken(Object requestbody, Object token) {
		try {
			setBody(requestbody);
			setHeader(token);
			Response response = post(Endpoints.getTermPeriodicLineFeature);
			stepDefinitionDataManager.addToStoredObjectMap("TermPeriodicLineFeatureService", response);
			log.info("request body: " + requestbody);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());
		}catch(Exception e) {
			log.info("Error while hitting API with Token in Service class");
		}
	}
	
	public void getTermPeriodicLineFeatureWithDisablePagination(Object requestbody, Object pagination) {
		try {
			setBody(requestbody);
			setHeader(pagination);
			Response response = post(Endpoints.getTermPeriodicLineFeature);
			stepDefinitionDataManager.addToStoredObjectMap("TermPeriodicLineFeatureService", response);
			log.info("request body: " + requestbody);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());
		}catch(Exception e) {
			log.info("Error while hitting API with pagination in Service class");
		}
	}

}
