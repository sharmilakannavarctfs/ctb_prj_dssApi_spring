package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PeriodicLineFeature {
    private String availableFromDate;
    private ArrayList<PLFCcySpecific> ccySpecific;
    private String productCoCode;
    private String productDescription;
    private String productGroupDisplayName;
    private String productGroupId;
    private String productId;
    private String productLine;
    private String productLineDisplayName;
	

}
