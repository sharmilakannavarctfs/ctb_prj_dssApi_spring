package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandInterestRateRequestPojo {
	private String rateId;
	private PaginationParameters paginationParameters;
	public static DemandInterestRateRequestPojo setDemandInterestRatesRequestPojo(Map<String, String> entry) {
		DemandInterestRateRequestPojo demandInterestRates = new DemandInterestRateRequestPojo();
		if(entry.get("rateId")!=null) {
			demandInterestRates.setRateId(entry.get("rateId"));
		}
		
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			demandInterestRates.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return demandInterestRates;
	}

}
