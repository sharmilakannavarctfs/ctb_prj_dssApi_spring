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
public class GetTermService extends BaseExecutor {

	protected GetTermService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties,"TELUSWBSERVICE_V1");
	}

	private final Logger log = LoggerFactory.getLogger(GetTermService.class);

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	public void getTerm(Object requestbody) throws URISyntaxException {
		try {
			log.info("request body: "+ requestbody);
			setBody(requestbody);
			Response response = post(Endpoints.getTerm);
			stepDefinitionDataManager.addToStoredObjectMap("TermService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with body alone");
		}
	}
	
	public void getTermWithToken(Object requestbody, Object token) throws URISyntaxException {
		try {
			log.info("request body: "+ requestbody);
			setHeader(token);
			setBody(requestbody);
			Response response = post(Endpoints.getTerm);
			stepDefinitionDataManager.addToStoredObjectMap("TermService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with Token");
		}
	}
	
	public void getTermWithDisablePagination(Object requestbody, Object pagination) throws URISyntaxException {
		try {
			log.info("request body: "+ requestbody);
			setHeader(pagination);
			setBody(requestbody);
			Response response = post(Endpoints.getTerm);
			stepDefinitionDataManager.addToStoredObjectMap("TermService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with Disable Pagination");
		}
	}
	
}
