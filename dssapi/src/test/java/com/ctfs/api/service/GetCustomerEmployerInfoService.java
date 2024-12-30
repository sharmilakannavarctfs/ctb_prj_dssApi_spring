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
public class GetCustomerEmployerInfoService extends BaseExecutor{

	protected GetCustomerEmployerInfoService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties);
		// TODO Auto-generated constructor stub
	}

	private final Logger log = LoggerFactory.getLogger(GetCustomerEmployerInfoService.class);

	@Autowired
	private StepDefinitionDataManager stepDefinitionDataManager;
	
	public void getCustomerEmpInfo(Object requestbody) throws URISyntaxException {
		try {
			log.info("request body: "+ requestbody,true);
			setBody(requestbody);
			Response response = APIService.post(Endpoints.ts2_getCustomerEmployerInfo);
			stepDefinitionDataManager.addToStoredObjectMap("getCustomerEmployerInfoRes", response);
			log.info("response getStatusLine: " + response.getStatusLine());
			log.info("response getBody: " + response.getBody().asPrettyString());

		} catch (Exception e) {
			log.info(e.getMessage(),"Error while hitting API with body alone");
		}
	}
	
	
}
