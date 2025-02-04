package com.ctfs.api.pojos.response.ts2response;

import java.util.List;
import com.ctfs.api.pojos.request.ts2.TriadInfoList;
import lombok.Data;

@Data
public class InqTriad8_POJO {

	private String status;
	private String statusMsg;
	private List<Fault> faults;
	private String restartToken;
	private TriadInfoList[] triadInfoList;

}
