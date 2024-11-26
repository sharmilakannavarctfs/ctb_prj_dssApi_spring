package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProductsRequestPojo {
	private String customerId;
	private String accountId;
	private String cardId;
	private String channelId;
	private String currencyId;
	private String productId;
	private PaginationParameters paginationParameters;
	public static CustomerProductsRequestPojo setCustomerProductsRequestPojo(Map<String, String> entry) {
		CustomerProductsRequestPojo customerProducts = new CustomerProductsRequestPojo();
		if(entry.get("customerId")!=null) {
			customerProducts.setCustomerId(entry.get("customerId"));
		}
		if(entry.get("accountId")!=null) {
			customerProducts.setAccountId(entry.get("accountId"));
		}
		if(entry.get("cardId")!=null) {
			customerProducts.setCardId(entry.get("cardId"));
		}
		if(entry.get("channelId")!=null) {
			customerProducts.setChannelId(entry.get("channelId"));
		}
		if(entry.get("currencyId")!=null) {
			customerProducts.setCurrencyId(entry.get("currencyId"));
		}
		if(entry.get("productId")!=null) {
			customerProducts.setProductId(entry.get("productId"));
		}
		 
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			customerProducts.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return customerProducts;
	}
}
