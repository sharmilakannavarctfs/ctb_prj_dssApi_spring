package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkedAccountsRequestPojo {
	private String customerId;
	private String beneficiaryId;
	private String cardId;
	private String ownAccount;
	
	private PaginationParameters paginationParameters;
	public static LinkedAccountsRequestPojo setLinkedAccountsRequestPojo(Map<String, String> entry) {
		LinkedAccountsRequestPojo linkedAccounts = new LinkedAccountsRequestPojo();
		
		if(entry.get("customerId")!=null) {
			linkedAccounts.setCustomerId(entry.get("customerId"));
		}
		if(entry.get("beneficiaryId")!=null) {
			linkedAccounts.setBeneficiaryId(entry.get("beneficiaryId"));
		}
		if(entry.get("cardId")!=null) {
			linkedAccounts.setCardId(entry.get("cardId"));
		}
		if(entry.get("ownAccount")!=null) {
			linkedAccounts.setOwnAccount(entry.get("ownAccount"));
		}
		
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			linkedAccounts.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return linkedAccounts;
	}

}
