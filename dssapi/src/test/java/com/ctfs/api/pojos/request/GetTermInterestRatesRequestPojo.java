package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetTermInterestRatesRequestPojo {

	private String periodicInterestId;
	private PaginationParameters paginationParameters;
	public static GetTermInterestRatesRequestPojo setTermInterestRatesRequestPojo(Map<String, String> entry) {
		GetTermInterestRatesRequestPojo portfolios = new GetTermInterestRatesRequestPojo();
		if(entry.get("periodicInterestId")!=null) {
			portfolios.setPeriodicInterestId(entry.get("periodicInterestId"));
		}
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			portfolios.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return portfolios;
		
	}
}
