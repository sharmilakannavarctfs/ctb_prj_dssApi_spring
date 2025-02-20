package com.ctfs.api.pojos.request.ts2;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveChipCardDetailsReqPojo {
	private String operatorId;
	private String accountId;
	private String cardNbr;
	private String custId;
	private String restartToken;
	private String statusCode;
	private boolean mostCurr;

}

