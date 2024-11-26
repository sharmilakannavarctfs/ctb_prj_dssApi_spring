package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class CustomerProductsInterestRate {
    private ArrayList<String> interestProperty;
//    public ArrayList<int> interestRate;
    private ArrayList<Integer> interestRate;
    private ArrayList<String> interestType;

}
