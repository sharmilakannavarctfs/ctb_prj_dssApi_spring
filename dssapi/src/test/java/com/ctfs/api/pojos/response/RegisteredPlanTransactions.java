package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class RegisteredPlanTransactions {

	private String accountId;
	private String balanceAmount;
	private String beneficiaryId;
	private String bookingDate;
	private String currency;
	private String customerId;
	private String customerName;
	private String debitCreditIndicator;
	private String excessAmount;
	private String federalTax;
	private String federalTaxAfterPeriod;
	private String federalTaxWithinPeriod;
	private String minimumAmount;
	private String nonResidenceTax;
	private String nonResidenceTaxAfterPeriod;
	private String nonResidenceTaxWithinPeriod;
	private String ourCharges;
	private String planType;
	private String portfolioId;
	private String province;
	private String provinceTax;
	private String provinceTaxAfterPeriod;
	private String provinceTaxWithinPeriod;
	private String residence;
	private String signatureRequired;
	private String spouse;
	private String transactionAmount;
	private String transactionCode;
	private String transactionDescription;
	private String transactionPurpose;
	private String transactionReference;
	private String transactionType;
	private String valueDate;
	
}
