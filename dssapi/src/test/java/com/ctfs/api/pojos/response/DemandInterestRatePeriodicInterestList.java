package com.ctfs.api.pojos.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DemandInterestRatePeriodicInterestList {
	private DemandInterestRateExtensions extensions;
	private String interestRate;
	private String negativeInterestRate;

}
