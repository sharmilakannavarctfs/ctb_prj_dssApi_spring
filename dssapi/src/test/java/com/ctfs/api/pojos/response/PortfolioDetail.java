package com.ctfs.api.pojos.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PortfolioDetail {
	private String planType;
	
	private String referenceCurrency;
	private String valuationCurrency;
	private String accountName;
	private String contributionTillDate;
	private String unrealisedProfitLossPercentage;
	private String valuationAmt;
	private String paymentAmount;
	private String customerName;
	private String unrealProfitLoss;
	private String managedAccount;
	private String portfolioId;
	private String branchId;
	private String lockedInIndicator;
	
	private List<AnnuitantDetail> annuitantDetails;
	
	private List<AccountId> accountIds;
    public ArrayList<BeneficiaryDetail> beneficiaryDetails;
	
	private String defaultSettlementAccount;
	private String investmentProgram;
	private String customerId;
	private String contributionSincePlanDate;
	private String openDate;
	@JsonProperty("Title")
	private String title;
	private String planGroupId;
	private String holderName;
	private String beneficiaryDOB;
	private String beneficiaryDateOfDeath;
	private String dateOfBirth;
	private String dateOfDeath;
	private String additionalTitle;
	private String beneficiaryName;
	private String paymentDate;
	private String lastPaymentDate;
	private String paymentFrequency;
	private String paymentIndicator;
	private String holderReference;
	private String depositoryId;
	private String subAccount;
	private String subAcctExtId;
	private String jointCustomer;
	private String jointCustomerName;
	@JsonProperty("Balance")
	private String balance;

}
