package com.ctfs.api.pojos.response.ts2response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class CardStatus {
	
	private String cardNbr;
	private String custId;
	private List<Fault> status;

}
