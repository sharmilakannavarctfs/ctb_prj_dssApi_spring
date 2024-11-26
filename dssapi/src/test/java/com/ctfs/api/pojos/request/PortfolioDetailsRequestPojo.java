package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PortfolioDetailsRequestPojo {

	private String portfolioId;
	private PaginationParameters paginationParameters;
	public static PortfolioDetailsRequestPojo setPortfolioDetailsRequestPojo(Map<String, String> entry) {
		PortfolioDetailsRequestPojo portfolioDetails = new PortfolioDetailsRequestPojo();
		if(entry.get("portfolioId")!=null) {
			portfolioDetails.setPortfolioId(entry.get("portfolioId"));
		}
		
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			portfolioDetails.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return portfolioDetails;
	}

}
