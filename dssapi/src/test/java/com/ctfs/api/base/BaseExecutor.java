package com.ctfs.api.base;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctfs.common.service.RestService;
import com.ctfs.common.utils.ApplicationProperties;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Component
public class BaseExecutor {

	private final Logger log = LoggerFactory.getLogger(BaseExecutor.class);
	@Autowired
	protected RestService restService;

	private static final ThreadLocal<Response> jwtToken = new ThreadLocal<>();
	private static final ThreadLocal<Response> sessionID = new ThreadLocal<>();
	protected static final ThreadLocal<RequestSpecification> requestSpecificationObject = new ThreadLocal<>();

	protected BaseExecutor(RestService restService, ApplicationProperties applicationProperties) {
		log.info("Inside Base Executor");
		RequestSpecification rSpecification = restService.getRequestSpecification();
		requestSpecificationObject.set(rSpecification);
		getRequestSpecification().baseUri(applicationProperties.getDssApiUrl());
		log.info("Base URL" + getRequestSpecification().baseUri(applicationProperties.getDssApiUrl()));

	}

	public RequestSpecification getRequestSpecification() {
		return requestSpecificationObject.get();
	}

	public void setBody(Object obj) {
		getRequestSpecification().body(obj);
	}

	@SuppressWarnings("unchecked")
	public void setParams(Object obj) {
		getRequestSpecification().params((Map<String, ?>) obj);
	}

	public void setQueryParams(Object obj) {
		getRequestSpecification().queryParams((Map<String, ?>) obj);
	}

	@SuppressWarnings("unchecked")
	public void setHeader(Object obj) {
		getRequestSpecification().headers((Map<String, ?>) obj);
	}
	// addParams
	// addHeaders
	// addqueryParams
	// addBody

}
