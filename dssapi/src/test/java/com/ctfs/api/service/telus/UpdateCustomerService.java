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
public class UpdateCustomerService extends BaseExecutor{

	protected UpdateCustomerService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties,"TELUSWBSERVICE_V1");
	}
	
	private final Logger log = LoggerFactory.getLogger(UpdateCustomerService.class);

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	
	public void updateCustomer(Object requestbody) throws URISyntaxException {
		try {
			log.info("request body: "+ requestbody);
			setBody(requestbody);
			Response response = post(Endpoints.updateCustomer);
			stepDefinitionDataManager.addToStoredObjectMap("UpdateCustomerService", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting UpdateCustomer API with body");
		}
	}

}
