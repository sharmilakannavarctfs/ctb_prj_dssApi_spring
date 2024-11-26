package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class ProductInformation {

	private String productLineId;
    private String productLineName;
    private String productGroupId;
    private String productGroupName;
    private String productId;
    private String productName;
    private String availableFromDate;
    private String currencyId;
    private String currencyName;
    private String minimumTerm;
    private String maximumTerm;

}
