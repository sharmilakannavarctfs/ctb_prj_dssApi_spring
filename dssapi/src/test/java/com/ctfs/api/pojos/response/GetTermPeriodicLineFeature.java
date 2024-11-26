package com.ctfs.api.pojos.response;

import java.util.List;

import lombok.Data;
@Data
public class GetTermPeriodicLineFeature {
	
	private List<TermPeriodicLineFeatureCcySpecific> ccySpecific;
    private List<TermPeriodicLineFeatureFacility> facilities;
    private String productId;
    private String productGroupId;
    private String productLine;
    private String productDescription;
    private String productLineDisplayName;
    private String productGroupDisplayName;
    private String printingAttributePosition;
    private String productCoCode;
    private String availableFromDate;
    private String availableToDate;
}
