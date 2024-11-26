package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandPeriodicLineFeaturesRequestPojo {
	private String productId;
	private String currency;
	private PaginationParameters paginationParameters;

	public static DemandPeriodicLineFeaturesRequestPojo setDemandPeriodicLineFeaturesRequestPojo(Map<String, String> entry) {
		DemandPeriodicLineFeaturesRequestPojo demandPeriodicLineFeatures = new DemandPeriodicLineFeaturesRequestPojo();
		if(entry.get("productId")!=null) {
			demandPeriodicLineFeatures.setProductId(entry.get("productId"));
		}
		if(entry.get("currency")!=null) {
			demandPeriodicLineFeatures.setCurrency(entry.get("currency"));
		}
		
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			demandPeriodicLineFeatures.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return demandPeriodicLineFeatures;
	}

}
