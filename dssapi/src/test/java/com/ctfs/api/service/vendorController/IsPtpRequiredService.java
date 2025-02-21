package com.ctfs.api.service.vendorController;

import java.net.URISyntaxException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctfs.api.annotations.RetailBankService;
import com.ctfs.api.base.BaseExecutor;
import com.ctfs.api.endpoints.Endpoints_controllers;
import com.ctfs.common.service.RestService;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.ctfs.common.utils.ApplicationProperties;

import io.restassured.response.Response;

@RetailBankService
public class IsPtpRequiredService extends BaseExecutor{
	static Map<String, String> headers ;
	protected IsPtpRequiredService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties,headers);

		// TODO Auto-generated constructor stub
	}

	private final Logger log = LoggerFactory.getLogger(IsPtpRequiredService.class);

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	
	public void post(Object requestbody) throws URISyntaxException {
		try {
			log.info("request body: "+ requestbody,true);
			setBody(requestbody);
			Response response = post(Endpoints_controllers.vendor_isPtpRequired);
			stepDefinitionDataManager.addToStoredObjectMap("isPtpRequired", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with body alone");
		}
	}
	
	
}
