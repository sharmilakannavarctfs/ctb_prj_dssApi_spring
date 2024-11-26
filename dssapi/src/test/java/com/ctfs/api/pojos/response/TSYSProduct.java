package com.ctfs.api.pojos.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class TSYSProduct {
	
	private String rate;
	private String state;
	private String product;
	private String company;
	private String telephone;
	private Date enrollmentDate;
	private String enhancementId;
	private String planId;
	private String optionSetId;

}
