package com.ctfs.api.pojos.request.vendorController;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Component
@Data
public class IsPtpRequiredReqpojo {
	

	private String accountId;
	private String ficoCaseId;
	
	@Override
	public String toString() {
	    try {
	        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
	    } catch (Exception e) {
	        return "Error converting to JSON: " + e.getMessage();
	    }
	}
}

