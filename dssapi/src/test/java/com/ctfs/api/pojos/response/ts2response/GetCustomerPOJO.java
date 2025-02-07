package com.ctfs.api.pojos.response.ts2response;

import java.util.List;
import com.ctfs.api.pojos.request.ts2.TriadInfoList;
import lombok.Data;

@Data
public class GetCustomerPOJO {

	private String status;
	private String statusMsg;
	private List<Fault> faults;
	private String restartToken;
	private List<TriadInfoList> triadInfoList;
	private List<InquireDisputeOutput> inquireDisputeOutput;

}
