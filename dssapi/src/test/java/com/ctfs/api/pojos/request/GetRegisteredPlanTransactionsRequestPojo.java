package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetRegisteredPlanTransactionsRequestPojo {

	private String portfolioId;
	private String taxYear;
	private String debitCreditIndicator;
	private PaginationParameters paginationParameters;
	public static GetRegisteredPlanTransactionsRequestPojo setRegisteredPlanTransactionsRequestPojo(Map<String, String> entry) {
		GetRegisteredPlanTransactionsRequestPojo regPlanTrans = new GetRegisteredPlanTransactionsRequestPojo();
		if(entry.get("portfolioId")!=null) {
			regPlanTrans.setPortfolioId(entry.get("portfolioId"));
		}
		if(entry.get("taxYear")!=null) {
			regPlanTrans.setTaxYear(entry.get("taxYear"));
		}
		if(entry.get("debitCreditIndicator")!=null) {
			regPlanTrans.setDebitCreditIndicator(entry.get("debitCreditIndicator"));
		}
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			regPlanTrans.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return regPlanTrans;
	}
}
