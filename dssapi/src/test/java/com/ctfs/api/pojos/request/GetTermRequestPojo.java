package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetTermRequestPojo {

	private String customerId;
	private String Id;
	private String arrangementId;
	private PaginationParameters paginationParameters;
	public static GetTermRequestPojo setTermRequestPojo(Map<String, String> entry) {
		GetTermRequestPojo getTerm = new GetTermRequestPojo();
		if(entry.get("customerId")!=null) {
			getTerm.setCustomerId(entry.get("customerId"));
		}
		if(entry.get("Id")!=null) {
			getTerm.setId(entry.get("Id"));
		}
		if(entry.get("arrangementId")!=null) {
			getTerm.setArrangementId(entry.get("arrangementId"));
		}
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			getTerm.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return getTerm;
	}
}
