package com.ctfs.api.pojos.response;


import java.util.List;

import com.ctfs.api.pojos.response.ts2response.Fault;

import lombok.Data;

@Data
public class GetAccountInfo_res_pojo {
	
	Account account;
	List<Fault> faults;	
	private String restartToken;
	private String status;
	private String statusMsg;	
	private String c86ConsentType;	
	
	 

}
