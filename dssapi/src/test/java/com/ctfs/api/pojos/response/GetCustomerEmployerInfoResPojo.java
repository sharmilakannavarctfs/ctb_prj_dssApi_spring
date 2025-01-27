package com.ctfs.api.pojos.response;

import java.util.List;

import com.ctfs.api.pojos.response.ts2response.Fault;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GetCustomerEmployerInfoResPojo {
	
	@JsonProperty("businessPhone")
	private String businessPhone;
	@JsonProperty("custID")
	private String custID;
	@JsonProperty("dateEmploymentMnt")
	private String dateEmploymentMnt;
	@JsonProperty("employerAddr")
	private EmployerAddress employerAddr;
	@JsonProperty("employerName")
	private String employerName;
	@JsonProperty("faults")
	private List<Fault> faults;
	@JsonProperty("jobTitle")
	private String jobTitle;
	@JsonProperty("restartToken")
	private String restartToken;
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusMsg")
	private String statusMsg;
	
}
