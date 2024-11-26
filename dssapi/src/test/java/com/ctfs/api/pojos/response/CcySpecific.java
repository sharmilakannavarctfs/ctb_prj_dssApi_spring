package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import lombok.Data;
@Data
public class CcySpecific {
    private String creditInterestType;
    private ArrayList<Fee> fees;
    private String creditInterest;
    private String minimumAmount;
    private String currency;
    private String creditInterestMarginRate;
    private String periodicIndex;

}
