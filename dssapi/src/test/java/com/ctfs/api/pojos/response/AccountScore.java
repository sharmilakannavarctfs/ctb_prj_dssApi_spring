package com.ctfs.api.pojos.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class AccountScore {
	
	private String bureauScore;
	private String attritionScore;
	private Date alignedScoreDate;
	private String ctrHighValueScore;
	private String amlScore;
	private String summitScore;
	private String fusionScore;
	private BigDecimal customerScore;
	private BigDecimal customerServiceScore; 
	private BigDecimal scoreNet;
	private String everestScore;

}
