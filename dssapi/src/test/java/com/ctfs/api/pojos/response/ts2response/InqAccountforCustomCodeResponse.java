package com.ctfs.api.pojos.response.ts2response;

import java.util.List;

import com.ctfs.api.pojos.response.Customer;

import lombok.Data;

@Data
public class InqAccountforCustomCodeResponse {
	private String amountRecomended;
	private String cardNumber;
	private String restartToken;
	private List<DeclineReason> declineReasons ;
	private List<Fault> faults ;
	private Customer requestType;
	private String status;
	private String statusMsg;
	private List<Skuitems> skuitems;
	private String cdf10Flag;
	private String cdf10Value;
	private AccountAlignedScores[] accountAlignedScores;
	
}
