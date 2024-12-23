package com.ctfs.api.model.response;


import lombok.Data;

@Data
public class DeclineReason {
	
	private String code;
	private String description;
	private String id;
	private String reason;
	
	public static DeclineReason getFault(String code, String desc) {
		DeclineReason fault = new DeclineReason();
		fault.setCode(code);
		return fault;
	}

}


