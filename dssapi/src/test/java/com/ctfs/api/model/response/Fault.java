package com.ctfs.api.model.response;


import lombok.Data;

@Data
public class Fault {
	
	private String code;
	private String desc;
	private String description;
	private String id;
	private String reason;
	
	public static Fault getFault(String code, String desc) {
		Fault fault = new Fault();
		fault.setCode(code);
		fault.setDesc(desc);
		return fault;
	}

}


