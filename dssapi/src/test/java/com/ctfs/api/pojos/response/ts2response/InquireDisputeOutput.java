package com.ctfs.api.pojos.response.ts2response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InquireDisputeOutput {
	
	@JsonProperty("acctFunction")
	private String acctFunction;
	@JsonProperty("acctNbr")
	private String acctNbr;
	@JsonProperty("actionCode")
	private String actionCode;
	@JsonProperty("amt")
	private String amt;
	@JsonProperty("amtTran")
	private String amtTran;
	@JsonProperty("caseTrackingNbr")
	private String caseTrackingNbr;
	@JsonProperty("catCode")
	private String catCode;
	@JsonProperty("dateLastWork")
	private String dateLastWork;
	@JsonProperty("dateMailStamp")
	private String dateMailStamp;
	@JsonProperty("dateNextWork")
	private String dateNextWork;
	@JsonProperty("datePost")
	private String datePost;
	@JsonProperty("dateSettled")
	private String dateSettled;
	@JsonProperty("dateStmtBegin")
	private String dateStmtBegin;
	@JsonProperty("dateTimePost")
	private String dateTimePost;
	@JsonProperty("dateTran")
	private String dateTran;
	@JsonProperty("dbaCity")
	private String dbaCity;
	@JsonProperty("dbaCountry")
	private String dbaCountry;
	@JsonProperty("dbaName")
	private String dbaName;
	@JsonProperty("dbaStateprovince")
	private String dbaStateprovince;	
	@JsonProperty("disputeDate")
	private String disputeDate;
	@JsonProperty("operId")
	private String operId;
	@JsonProperty("pendingReason")
	private String pendingReason;
	@JsonProperty("refNbr")
	private String refNbr;
	@JsonProperty("resolveAction")
	private String resolveAction;
	@JsonProperty("resolveStatus")
	private String resolveStatus;
	@JsonProperty("resultCode")
	private String resultCode;
	@JsonProperty("tcat")
	private String tcat;
	@JsonProperty("text")
	private String text;
	@JsonProperty("timeNextWork")
	private String timeNextWork;
	@JsonProperty("timePost")
	private String timePost;
	@JsonProperty("tranCode")
	private String tranCode;
	@JsonProperty("type")
	private String type;
	
		

}
