package com.ctfs.api.step;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

@Component
public abstract class AbstractStep {
	
	public static <T> T deserializeResponse(Response response, Class<T> responseClass) {
		String jsonResponse = response.getBody().asString();
		try {
			ObjectMapper objectMapper= new ObjectMapper();
			return objectMapper.readValue(jsonResponse, responseClass);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
