package com.ctfs.api.pojos.response.ts2response;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class RetrieveCardStatus {
	
	private String Status;
	private String statusMsg;
	private List<Fault> faults;
	private List<CardStatus> cardsStatus;

}

