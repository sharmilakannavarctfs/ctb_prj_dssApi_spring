package com.ctfs.api.pojos.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class AnnuitantDetail {
    private String branchId;
    private String holderName;
    private String dateOfBirth;
    private String holderReference;
    @JsonProperty("Title") 
    private Object title;

}
