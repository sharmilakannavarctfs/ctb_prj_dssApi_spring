package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import lombok.Data;
@Data
public class ProductDetailsCcySpecific {
   
    private String currency;
    private String creditInterest;
    private String creditInterestType;
    private String creditInterestMinimumBalance;
    private String creditInterestMaximumBalance;
    private String creditInterestMarginRate;
    private String debitInterest;
    private String debitInterestType;
    private String debitInterestMinimumBalance;
    private String debitInterestMaximumBalance;
    private String debitInterestMarginRate;
    private String overdraftAmount;
    private String minimumAmount;
    private String maximumAmount;
    private String amount;
    private String noticePeriod;
    private String term;
    private String minimumTerm;
    private String maximumTerm;
    private String periodicIndex;
    private ArrayList<ProductDetailsInterestRate> interestRates;
    private ArrayList<Fee> fees;
}
