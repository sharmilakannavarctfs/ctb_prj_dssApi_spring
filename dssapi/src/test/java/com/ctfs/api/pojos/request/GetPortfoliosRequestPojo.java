package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPortfoliosRequestPojo {

	private String customerId;
	private PaginationParameters paginationParameters;
	public static GetPortfoliosRequestPojo setPortfoliosRequestPojo(Map<String, String> entry) {
		GetPortfoliosRequestPojo portfolios = new GetPortfoliosRequestPojo();
		if(entry.get("customerId")!=null) {
			portfolios.setCustomerId(entry.get("customerId"));
		}
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			portfolios.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return portfolios;
	}
}
