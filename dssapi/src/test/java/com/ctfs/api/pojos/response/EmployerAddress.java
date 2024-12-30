package com.ctfs.api.pojos.response;

import com.ctfs.profile.Province;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class EmployerAddress {
	@JsonProperty("addressLine1")
	private String addressLine1;
	@JsonProperty("addressLine2")
	private String addressLine2;
	@JsonProperty("addressLine3")
	private String addressLine3;
	
	@JsonProperty("addLine2")
	private String addLine2;
	@JsonProperty("addLine3")
	private String addLine3;
	@JsonProperty("city")
	private String city;
	@JsonProperty("country")
	private String country;
	@JsonProperty("postalCode")
	private String postalCode;
	@JsonProperty("stateProv")
	private String stateProv;
	
	@JsonProperty("addrCode")
	private String addrCode;
	@JsonProperty("addrDefault")
	private String addrDefault;
	@JsonProperty("addressTitle")
	AddressTitle addressTitle;
	@JsonProperty("altAddr")
	AlternateAddress altAddr;
	
	@JsonProperty("cbrAddr")
	private String cbrAddr;

	@JsonProperty("code1Override")
	private String code1Override;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("countyName")
	private String countyName;
	@JsonProperty("dateAddrAdded")
	private String dateAddrAdded;
	@JsonProperty("dateLastMnt")
	private String dateLastMnt;
	@JsonProperty("addressDesc")
	private String addressDesc;
	
	

}
