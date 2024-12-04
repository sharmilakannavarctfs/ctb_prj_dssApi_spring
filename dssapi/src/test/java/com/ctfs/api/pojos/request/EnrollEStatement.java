package com.ctfs.api.pojos.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollEStatement {
	private String accountId;
	private String cardNbr;
	private String custId;
	private String electronicVendorOptionId;
	private String emailAddr;
	private String restartToken;
	private String statusCode;
	private String operatorId;
}
