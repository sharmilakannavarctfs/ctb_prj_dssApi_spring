package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class PaginationParameters {
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int page_size;
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int page_start;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String page_token;
    public static PaginationParameters setPaginationParametersPojo(Map<String, String> entry) {
    	PaginationParameters pagParam = new PaginationParameters();
		if(entry.get("page_size")!=null) {
			pagParam.setPage_size(Integer.parseInt(entry.get("page_size")));
		}
		if(entry.get("page_start")!=null) {
			pagParam.setPage_start(Integer.parseInt(entry.get("page_start")));
		}
		if(entry.get("page_token")!=null) {
			pagParam.setPage_token(entry.get("page_token"));
		}
		return pagParam;
	}

}
