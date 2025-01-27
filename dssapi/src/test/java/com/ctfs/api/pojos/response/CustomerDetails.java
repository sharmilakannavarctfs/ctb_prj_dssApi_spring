package com.ctfs.api.pojos.response;

import java.util.Date;
import java.util.List;

import com.ctfs.api.pojos.response.ts2response.Fault;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class CustomerDetails {
	
	 @JsonProperty("accountId")
	 private String accountId;
	 @JsonProperty("custID")
	 private String custID;
	 @JsonProperty("nameThirdParty")
	 private String nameThirdParty;	 
	 @JsonProperty("dateThirdPartyAdded")
	 private Date dateThirdPartyAdded;
	 @JsonProperty("customData")
	 List<CustomData> customData;
	 @JsonProperty("consentedParty")
	 ConsentedParty consentedParty;
	 @JsonProperty("faults")
	 List<Fault> faults;	
	 @JsonProperty("attorney")
	 Attorney attorney;
	 @JsonProperty("attorney2")
	 Attorney attorney2;

}

@Data
class Attorney {
	
	 @JsonProperty("address")
	 private String address;
	 @JsonProperty("attorneyId")
	 private String attorneyId;
	 @JsonProperty("firm")
	 private String firm;
	 @JsonProperty("name")
	 private String name;
	 @JsonProperty("phoneNbr")
	 private String phoneNbr;
}
	
