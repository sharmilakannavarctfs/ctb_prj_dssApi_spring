package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Interest {
	
	private String interestProperty;
	private String interestAmount;
	private ArrayList<InterestRate> interestRates;
}
