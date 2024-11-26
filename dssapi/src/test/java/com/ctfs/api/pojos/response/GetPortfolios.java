package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class GetPortfolios {

	private String jointCustomerName;
	private String referenceCurrency;
	private String valuationCurrency;
	private String accountName;
	private String unrealisedProfitLossPercentage;
	private String jointCustomer;
	private double valuationAmt;
	private String customerName;
	private int unrealProfitLoss;
	private String managedAccount;
	private String portfolioId;
	private String investmentProgram;
	private String customerId;
	private String depositoryId;
	private String subAccount;
	private String subAcctExtId;
	
}
