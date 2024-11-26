package com.ctfs.api.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Fault {
	
	@JsonProperty("code")
	private String code;
	@JsonProperty("desc")
	private String desc;
	@JsonProperty("id")
	private String id;
	@JsonProperty("reason")
	private String reason;
	
	public static Fault getFault(String code, String desc) {
		Fault fault = new Fault();
		fault.setCode(code);
		fault.setDesc(desc);
		return fault;
	}


}
