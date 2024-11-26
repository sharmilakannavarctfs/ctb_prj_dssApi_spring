package com.ctfs.api.pojos.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class GetTermRates {

	private String productId;
	private String productName;
	private String interestMethod;
	private LevelRate levelRate;
	private String maxBalance;
	private String minBalance;
	private String productGroupID;
	private String productLineId;

}
