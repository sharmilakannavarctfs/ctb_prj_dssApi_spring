package com.ctfs.api.model.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class EnrollEStatementResponsePojo {
	private Fault[] faults;
	private String status;
	private String statusMsg;
	private String operatorId;
}
