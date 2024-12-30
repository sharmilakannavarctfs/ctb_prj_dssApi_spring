package com.ctfs.api.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AddressTitle {
	
	@JsonProperty("closingSalutationDesc")
	private String closingSalutationDesc; 
	@JsonProperty("openingSalutationDesc")
	private String openingSalutationDesc; 
	@JsonProperty("titleCode")
	private String titleCode; 
	@JsonProperty("titleDesc")
	private String titleDesc; 
	

}
