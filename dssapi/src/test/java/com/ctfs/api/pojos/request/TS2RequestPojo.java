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

public class TS2RequestPojo {
	
	private String operatorId;
	private String accountId;
	private String custId;
	private String cardNbr;

}