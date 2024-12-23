package com.ctfs.api.pojos.response;

import java.util.ArrayList;
import java.util.List;

import com.ctfs.miscfunctions.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Phone {
	
	
	@JsonProperty("type")
	private String type;
	@JsonProperty("value")
	private String value;
	@JsonProperty("phoneNbr")
	private String phoneNbr;
	@JsonProperty("phoneType")
	private String phoneType;
	@JsonProperty("consent")
	private String consent;
	@JsonProperty("deleted")
	private Boolean deleted;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	

}


