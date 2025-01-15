package com.ctfs.api.pojos.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class CustomData {
	@JsonProperty("custId")
	private String custId;
	@JsonProperty("codes")
	private List<Codes >codes;
//	@JsonProperty("code")
//	private String code;
	
	@JsonProperty("value")
	private String value;

}

class Codes {
	
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("value")
	private String value;
	

}
