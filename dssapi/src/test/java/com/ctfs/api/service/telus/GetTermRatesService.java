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
public class GetTermRatesService extends BaseExecutor{

	protected GetTermRatesService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties,"TELUSWBSERVICE_V1");
		// TODO Auto-generated constructor stub
	}
	
	private final Logger log = LoggerFactory.getLogger(GetTermRatesService.class);

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;

	public void getTermRates(Object requestbody) throws URISyntaxException {
		try {
			setBody(requestbody);
			Response response = post(Endpoints.getTermRates);
			stepDefinitionDataManager.addToStoredObjectMap("TermRatesService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
