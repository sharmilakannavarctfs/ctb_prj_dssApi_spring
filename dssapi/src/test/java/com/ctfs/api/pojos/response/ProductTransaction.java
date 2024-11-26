package com.ctfs.api.pojos.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@Data
public class ProductTransaction {
	
	private String accountId;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private List<Narrative> narratives;
	private String transactionReference;
	private String closingBalance;
	
	private String bookingDate;
	private String transactionCode;
	private String valueDate;
	private String creditAmount;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String debitAmount;
		

}
