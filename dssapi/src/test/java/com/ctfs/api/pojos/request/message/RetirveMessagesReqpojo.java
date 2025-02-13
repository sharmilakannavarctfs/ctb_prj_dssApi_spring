package com.ctfs.api.pojos.request.message;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetirveMessagesReqpojo {

	private String targetIdentifier;
	private String state;

}
