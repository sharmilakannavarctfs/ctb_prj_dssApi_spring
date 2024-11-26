package com.ctfs.api.pojos.response;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Address {
	
	@JsonProperty("line1")
	private String line1;
	@JsonProperty("line2")
	private String line2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("prov")
	private String prov;
	@JsonProperty("street")
	private String street;
	@JsonProperty("postal")
	private String postal;
	@JsonProperty("postalZip")
	private String postalZip;
	@JsonProperty("country")
	private String country;
	@JsonProperty("indicator")
	private String indicator;
	@JsonProperty("description")
	private String description; 
	@JsonProperty("dateLastMaintained")
	private Date  dateLastMaintained; 
	@JsonProperty("startDate")
	private Date  startDate; 
	@JsonProperty("stopDate")
	private Date  stopDate; 
	@JsonProperty("type")
	private String type;
	@JsonProperty("addressOverrideFlag")
	private String addressOverrideFlag;
	
	@JsonProperty("addressDesc")
	private String addressDesc;
	@JsonProperty("addressLine1")
	private String addressLine1 ;
	@JsonProperty("addressLine2")
	private String addressLine2 ;
	@JsonProperty("addressLine3")
	private String addressLine3 ;
	@JsonProperty("province")
	private String province ;
	@JsonProperty("postalCode")
	private String postalCode; 
	@JsonProperty("postalCd")
	private String postalCd; 
	@JsonProperty("countryCode")	 
	private String countryCode; 
	@JsonProperty("aptNumber")	 
	private String aptNumber; 
	@JsonProperty("streetName")	 
	private String streetName; 
	@JsonProperty("streetNumber")	 
	private String streetNumber; 
	@JsonProperty("stateProv")	 
	private String stateProv; 
	@JsonProperty("countyName")	 
	private String countyName; 
	@JsonProperty("addrCode")	 
	private String addrCode; 
	@JsonProperty("addressTitle")	 
	private String addressTitle; 
	@JsonProperty("dateLastMnt")	 
	private String dateLastMnt; 
	@JsonProperty("dateAddrAdded")	 
	private String dateAddrAdded; 
	@JsonProperty("altAddr")	 
	private String altAddr; 
	@JsonProperty("code1Override")	 
	private String code1Override; 
	@JsonProperty("addrDefault")	 
	private String addrDefault; 
	@JsonProperty("cbrAddr")	 
	private String cbrAddr; 
	
}



