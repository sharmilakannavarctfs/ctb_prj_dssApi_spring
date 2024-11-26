package com.ctfs.api.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class OptOuts {

	@JsonProperty("categoryCode")
	private String categoryCode;
	
	@JsonProperty("categoryDesc")
	private String categoryDesc;
	
	@JsonProperty("category")
	private String category;

	@JsonProperty("flag")
	private String flag;

	//RetrieveAccountCustomer
	@JsonProperty("actionCode")
	private String actionCode;
	
	@JsonProperty("response")
	private Boolean response;


}
