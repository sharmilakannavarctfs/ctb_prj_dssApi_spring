package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class ProductDetailsInterestRate {
    private String creditInterestTierAmount;
    private String creditInterestTierPercentage;
    private String creditInterestRate;
    private String debitInterestTierAmount;
    private String debitInterestTierPercentage;
    private String debitInterestRate;

}
