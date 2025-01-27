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
public class GetCustomerPOJO {
	
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusMsg")
	 private String statusMsg;
	@JsonProperty("faults")
	 private Fault[] faults;
	@JsonProperty("customer")
	 private Customer customer;	
	@JsonProperty("custID")
	private String custID;
	@JsonProperty("jobTitle")
	private String jobTitle;
	@JsonProperty("employerName")
	private String employerName;
	@JsonProperty("dateEmploymentMnt")
	private String  dateEmploymentMnt;
	
	@JsonProperty("businessPhone")
	private String businessPhone;
	@JsonProperty("employerAddr")
	private Address employerAddr;
	@JsonProperty("restartToken")
	private String restartToken;
	@JsonProperty("dateLastMonthlyIncome")
	private String dateLastMonthlyIncome;
	@JsonProperty("customData")
	List<CustomData> customData;
	@JsonProperty("custCardInfo")
	List<CardReceipt> cardReceipts;	
	@JsonProperty("lastReqReason")
	private String lastReqReason;	
	@JsonProperty("cardNbr")
	private String cardNbr;
	@JsonProperty("lastReqOper")
	private String lastReqOper;	
	
	@JsonProperty("inquireDisputeOutput")
	private InquireDisputeOutput[] inquireDisputeOutput;
	
	@JsonProperty("nextWorkDate")
	private String nextWorkDate;	
	
	@JsonProperty("queueId")
	private String queueId;
	@JsonProperty("customerdetails")
	List<CustomerDetails> customerdetails;
	@JsonProperty("newLimit")
	private String newLimit;
	
}
