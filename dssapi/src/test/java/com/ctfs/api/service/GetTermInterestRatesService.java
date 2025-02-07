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
public class GetTermInterestRatesService extends BaseExecutor {

	protected GetTermInterestRatesService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties,"TELUSWBSERVICE_V1");
	}

	private final Logger log = LoggerFactory.getLogger(GetTermInterestRatesService.class);

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	public void getTermInterestRates(Object requestBody) throws URISyntaxException {
		try {
			log.info("request payload: " + requestBody);
			setBody(requestBody);
			Response response = post(Endpoints.getTermInterestRates);
			stepDefinitionDataManager.addToStoredObjectMap("TermInterestRatesService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with body alone");
		}
	}
	
	public void getTermInterestRatesWithToken(Object requestBody,Object token) throws URISyntaxException {
		try {
			log.info("request payload: " + requestBody);
			setHeader(token);
			setBody(requestBody);
			Response response = post(Endpoints.getTermInterestRates);
			stepDefinitionDataManager.addToStoredObjectMap("TermInterestRatesService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with token");
		}
	}
	
	public void getTermInterestRatesWithDisablePagination(Object requestBody,Object pagination) throws URISyntaxException {
		try {
			log.info("request payload: " + requestBody);
			setHeader(pagination);
			setBody(requestBody);
			Response response = post(Endpoints.getTermInterestRates);
			stepDefinitionDataManager.addToStoredObjectMap("TermInterestRatesService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with Disable Pagination");
		}
	}

}
