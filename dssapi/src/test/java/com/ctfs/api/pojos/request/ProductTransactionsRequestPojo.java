package com.ctfs.api.pojos.request;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductTransactionsRequestPojo {
private String id;
private String startDate;
private PaginationParameters paginationParameters;
private String endDate;	

public static ProductTransactionsRequestPojo setProductTransactionsRequestPojo(Map<String, String> entry) {
	ProductTransactionsRequestPojo productTransactions = new ProductTransactionsRequestPojo();
		if(entry.get("id")!=null) {
			productTransactions.setId(entry.get("id"));
		}
		if(entry.get("startDate")!=null) {
			productTransactions.setStartDate(entry.get("startDate").replace("/", ""));
		}
		if(entry.get("endDate")!=null) {
			productTransactions.setEndDate(entry.get("endDate").replace("/", ""));
		}
		
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			productTransactions.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return productTransactions;
	}

}
