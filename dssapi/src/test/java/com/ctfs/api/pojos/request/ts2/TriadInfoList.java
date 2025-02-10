package com.ctfs.api.pojos.request.ts2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class TriadInfoList {
	

	@JsonProperty("decisionArea")
	private String decisionArea;
	@JsonProperty("scenarioID")
	private String scenarioID;
	@JsonProperty("strategyID")
	private String strategyID;
	@JsonProperty("testDigitInd")
	private String testDigitInd;
	@JsonProperty("x100")
	private String x100;
	@JsonProperty("action")
	private action action;
	
	public class action {
		
		@JsonProperty("date")
		private String date;
		@JsonProperty("time")
		private String time;
		@JsonProperty("x100")
		private String x100;
		
	}
}


