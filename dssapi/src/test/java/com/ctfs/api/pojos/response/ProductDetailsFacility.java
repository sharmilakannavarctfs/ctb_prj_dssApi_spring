package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class ProductDetailsFacility {
    private String service;
    private String serviceAvailability;
    private String defaultOption;
    private String customerOptions;
    private String serviceAvailabiltyOptions;

}
