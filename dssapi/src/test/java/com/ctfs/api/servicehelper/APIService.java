package com.ctfs.api.servicehelper;

import java.net.URI;
import java.net.URISyntaxException;

import com.ctfs.common.service.RestService;
import com.ctfs.common.utils.ApplicationProperties;

import io.restassured.response.Response;

public final class APIService extends com.ctfs.api.base.BaseExecutor{

   
    protected APIService(RestService restService, ApplicationProperties applicationProperties) {
		super(restService, applicationProperties);
	}

	public static Response post(String url) throws URISyntaxException {	
		return requestSpecificationObject.get().post(new URI(url));
    }
	
	public static Response get(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().get(new URI(url));
    }
	
	public static Response put(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().put(new URI(url));
    }
	
	public static Response delete(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().delete(new URI(url));
    }
	
	public static Response patch(String url) throws URISyntaxException {		
		return requestSpecificationObject.get().patch(new URI(url));
    }
}
