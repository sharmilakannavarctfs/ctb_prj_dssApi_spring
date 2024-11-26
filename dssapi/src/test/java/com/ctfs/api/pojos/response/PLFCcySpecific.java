package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PLFCcySpecific {
    private String currency;
    private ArrayList<PLFInterest> interests;
    private ArrayList<PLFPaymentSchedule> paymentSchedule;

}
