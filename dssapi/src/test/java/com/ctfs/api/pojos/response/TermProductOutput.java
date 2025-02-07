package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TermProductOutput {
	
	private ArrayList<Customer> customers;
    private ArrayList<Interest> interests;
    private ArrayList<PostingRestrictDisplayName> postingRestrictDisplayNames;
    private String arrangementId;
    private String numericCurrencyCode;
    private String settlementCurrency;
    private String originalContractDate;
    private String renewalDate;
    private String firstInterestDate;
    private String nextInterestDate;
    private String maturityIndicator;
    private String portfolio;
    private String planType;
    private String renewalRate;
    private String rolloverType;
    private String term;
    private String payoutAccountId;
    private String transferIndicator;
    private String lockedFlag;
    private String sweepExempt;
    private String interestPayoutIndicator;
    private String principal;
    private String productGroup;
    private String nickName;
    private String accountTitle1;
    private String accountTitle2;
    private String companyCode;
    private String lastRenewalDate;
    private String depAccountId;
    private String depProduct;
    private String depCurrency;
    private String depAmount;
    private String depInterestType;
    private String depInterestRate;
    private String depStartDate;
    private String depEndDate;
    private String depBalance;
    private String depNextPayDate;
    private String depProductDescription;
	
}
