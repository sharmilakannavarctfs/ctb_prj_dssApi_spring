package com.ctfs.api.base;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctfs.api.utils.AtomicServicePortNbrUtil;
import com.ctfs.api.utils.AtomicServices;
import com.ctfs.common.service.RestService;
import com.ctfs.common.utils.ApplicationProperties;
import com.springbootjdbc.com.spring.jdbc.Application;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Component
public class BaseExecutor {

	public BaseExecutor() {
		super();
	}

	private final Logger log = LoggerFactory.getLogger(BaseExecutor.class);
	@Autowired
	protected RestService restService;
	
	AtomicServicePortNbrUtil portUtility = new AtomicServicePortNbrUtil();

	private static final ThreadLocal<Response> jwtToken = new ThreadLocal<>();
	private static final ThreadLocal<Response> sessionID = new ThreadLocal<>();
	protected static final ThreadLocal<RequestSpecification> requestSpecificationObject = new ThreadLocal<>();

	protected BaseExecutor(RestService restService, ApplicationProperties applicationProperties, String service) {
		log.info("Inside Base Executor");
		RequestSpecification rSpecification = restService.getRequestSpecification();
		requestSpecificationObject.set(rSpecification);
		
//		String baseUri = applicationProperties.getDssApiUrl();
		String baseUri = applicationProperties.getDssApiUrl()+portUtility.getPort(service);
		System.out.println("baseuri + endpoint : "+baseUri);
		getRequestSpecification().baseUri(baseUri);
		log.info("Base URL" + getRequestSpecification().baseUri(baseUri));

	}
	
	public Response post(String url) throws URISyntaxException {	
		System.out.println("Endpoint-"+url);
		Response response = requestSpecificationObject.get().post(new URI(url));
		return response;
    }
	
	public Response get(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().get(new URI(url));
    }
	
	public Response put(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().put(new URI(url));
    }
	
	public Response delete(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().delete(new URI(url));
    }
	
	public Response patch(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().patch(new URI(url));
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
