package com.ctfs.api.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class CardReceipt {
	
	 @JsonProperty("acctRelStatus")
	 private String acctRelStatus;
	 @JsonProperty("custId")
	 private String custId;
	 @JsonProperty("cardNbr")
	 private String cardNbr;
	 @JsonProperty("receiptVerified")
	 private String receiptVerified;
	 @JsonProperty("dateVerification")
	 private String dateVerification;
	 @JsonProperty("canBeActivated")
	 private String canBeActivated;
}
