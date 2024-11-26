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
@Builder
public class MarketingOption {
	@JsonProperty("eMarketingOptInState")
	private String eMarketingOptInState;
	@JsonProperty("eOfferOptInState")
	private String eOfferOptInState;
	@JsonProperty("eStatementEnrollmentState")
	private String eStatementEnrollmentState;
}
