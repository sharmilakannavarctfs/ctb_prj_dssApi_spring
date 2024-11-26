package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class TermPeriodicLineFeatureInterestRate {
	
	private String creditInterestRate;
	private String creditInterestTierAmount;
	private String creditInterestTierPercentage;
	private String debitInterestRate;
	private String debitInterestTierAmount;
	private String debitInterestTierPercentage;
}
