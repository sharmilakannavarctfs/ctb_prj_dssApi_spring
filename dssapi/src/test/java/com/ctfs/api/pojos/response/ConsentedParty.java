package com.ctfs.api.pojos.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class ConsentedParty {
	
	@JsonProperty("occupation")
	private String occupation;
	
	@JsonProperty("dateOfBirth")
	private Date dateOfBirth;
	
	@JsonProperty("addressLine1")
	private String addressLine1;
	
	@JsonProperty("addressLine2")
	private String addressLine2;
	
	@JsonProperty("stateProv")
	private String stateProv;
	
	@JsonProperty("countryCode")
	private String countryCode;
	
	@JsonProperty("postalCode")
	private String postalCode;
	
	@JsonProperty("city")
	private String city;

}
