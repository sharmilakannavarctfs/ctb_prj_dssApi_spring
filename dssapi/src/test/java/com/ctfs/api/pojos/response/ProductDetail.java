package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class ProductDetail {
    public ArrayList<ProductDetailsCcySpecific> ccySpecific;
    public ArrayList<ProductDetailsFacility> facilities;
    public String productId;
    public String productGroupId;
    public String productLine;
    public String productDescription;
    public String productLineDisplayName;
    public String productGroupDisplayName;
    public String printingAttributePosition;
    public String productCoCode;
    public String availableFromDate;
    public String availableToDate;

}
