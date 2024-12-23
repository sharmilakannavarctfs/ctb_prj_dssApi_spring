package com.ctfs.api.pojos.request;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TS2RequestPojo {
	
	private String accountId;
	private String cardNbr;
	private String custId;
	private String electronicVendorOptionId;
	private int monthlyIncome;
	private String emailAddr;
	private String restartToken;
	private String statusCode;
	private String operatorId;

}