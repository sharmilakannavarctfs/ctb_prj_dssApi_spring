package com.ctfs.api.pojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetInterestRates {

	@JsonProperty("Interest Rate")
	private String interestRate;
	private String term;
	@JsonProperty("Amount")
	private String amount;
	
}
