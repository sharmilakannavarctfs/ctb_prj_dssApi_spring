package com.ctfs.api.pojos.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties

public class EmployeeInfo {
	
	 @JsonProperty("jobTitle")
	 private String jobTitle;
	@JsonProperty("name")
	 private String name;
	@JsonProperty("employerCity")
	 private String employerCity;
	@JsonProperty("employeeProvince")
	 private String employeeProvince;
	@JsonProperty("employeeCountry")
	 private String employeeCountry;
	@JsonProperty("businessPhone")
	 private String businessPhone;
	@JsonProperty("employerLastMaintained")
	 private Date employerLastMaintained;
	@JsonProperty("employmentStatus")
	 private String employmentStatus;
	@JsonProperty("jobDescription")
	 private String jobDescription;

}
