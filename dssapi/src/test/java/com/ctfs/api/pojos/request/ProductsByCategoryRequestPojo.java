package com.ctfs.api.pojos.request;

import java.util.Map;

import lombok.Data;

@Data
public class ProductsByCategoryRequestPojo {

	private String productLineId;
	public static ProductsByCategoryRequestPojo setProductsByCategoryRequestPojo(Map<String, String> entry) {
		ProductsByCategoryRequestPojo productsByCategory = new ProductsByCategoryRequestPojo();
		if(entry.get("productLineId")!=null) {
			productsByCategory.setProductLineId(entry.get("productLineId"));
		}
		return productsByCategory;
	}
}
