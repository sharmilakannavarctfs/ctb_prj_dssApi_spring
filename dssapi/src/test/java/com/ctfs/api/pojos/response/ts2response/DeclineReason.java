package com.ctfs.api.pojos.response.ts2response;


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


