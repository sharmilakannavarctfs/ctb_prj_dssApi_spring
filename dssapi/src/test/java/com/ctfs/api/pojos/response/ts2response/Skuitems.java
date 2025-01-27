package com.ctfs.api.pojos.response.ts2response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Skuitems {
	
	@JsonProperty("amtGST")
	private String amtGST;
	@JsonProperty("amtHST")
	private String amtHST;
	@JsonProperty("amtProvTax")
	private String amtProvTax;
	@JsonProperty("itemDesc")
	private String itemDesc;
	@JsonProperty("itemPrice")
	private String itemPrice;
	@JsonProperty("itemSKU")
	private String itemSKU;

}
