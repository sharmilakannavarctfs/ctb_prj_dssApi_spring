package com.ctfs.api.pojos.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailsRequestPojo {
	private String productId;
	private PaginationParameters paginationParameters;
	
	public static ProductDetailsRequestPojo setProductDetailsRequestPojo(Map<String, String> entry) {
		ProductDetailsRequestPojo productDetails= new ProductDetailsRequestPojo();
		if(entry.get("productId")!=null) {
			productDetails.setProductId(entry.get("productId"));
		}
		
		if(entry.containsKey("page_size")||entry.containsKey("page_start")||entry.containsKey("page_token")) {
			productDetails.setPaginationParameters(PaginationParameters.setPaginationParametersPojo(entry));
		}
		return productDetails;
	}
}
