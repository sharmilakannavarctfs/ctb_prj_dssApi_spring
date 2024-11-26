package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TermPeriodicLineFeatureRequestPojo {
	
	private String productId;
	private String currency;
	private PaginationParameters paginationParameters;
	public static TermPeriodicLineFeatureRequestPojo setTermPeriodicLineFeatureRequestPojo(Map<String, String> entry) {
		TermPeriodicLineFeatureRequestPojo termPerLiFe = new TermPeriodicLineFeatureRequestPojo();
		if(entry.get("productId")!=null) {
			termPerLiFe.setProductId(entry.get("productId"));
		}
		if(entry.get("currency")!=null) {
			termPerLiFe.setCurrency(entry.get("currency"));
		}
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			termPerLiFe.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return termPerLiFe;
	}
}
