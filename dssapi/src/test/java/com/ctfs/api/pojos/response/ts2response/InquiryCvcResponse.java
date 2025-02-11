package com.ctfs.api.pojos.response.ts2response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InquiryCvcResponse {
	
	@JsonProperty("cvc")
	private String cvc;
	@JsonProperty("faults")
	private List<Fault> faults;
	@JsonProperty("restartToken")
	private String restartToken;
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusMsg")
	private String statusMsg;
		

}
