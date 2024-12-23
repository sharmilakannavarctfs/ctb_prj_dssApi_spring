package com.ctfs.api.model.response;

import org.springframework.stereotype.Component;

import com.ctfs.api.pojos.response.Account;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class Ts2ResponsePojo {
	private Fault[] faults;
	private String status;
	private String statusMsg;
	private String operatorId;
	private DeclineReason[] declineReasons;
	private Account account;
}
