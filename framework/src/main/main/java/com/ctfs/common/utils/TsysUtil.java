package com.ctfs.common.utils;

import org.springframework.stereotype.Component;

import com.ctfs.tsys.Tsys;
import com.ctfs.tsys.model.ADM_DTO;
import com.ctfs.uat.automation.tsys_interface2.wrapper.dataobjects.ReqType;
import com.ctfs.uat.automation.tsys_interface2.wrapper.dataobjects.ScrapeADM;
import com.ctfs.uat.automation.tsys_interface2.wrapper.dataobjects.TSYSReqRespDO;

@Component
public class TsysUtil {
	
	public static Tsys isys;
	public ScrapeADM scrapeADM = new ScrapeADM();
	
	public static void launchTsys() {
		System.setProperty("driverPath","C:\\MyDocuments\\Drivers\\IEDriverServer.exe");
		isys = new Tsys(ReqType.ISYS);
		isys.logon("parka2", "Autumn10");
	}

	public static ADM_DTO scrapeADMData(String firstName, String lastName, String testToPerform) throws Exception {
		ADM_DTO dto = null;
		ScrapeADM scrapeADM = new ScrapeADM();
		if(firstName.length()>16) {
			String fname = firstName.substring(0, 1);
			scrapeADM.setFirstName(fname);
			scrapeADM.setLastName(lastName);
		}
		else {
			scrapeADM.setFirstName(firstName);
			scrapeADM.setLastName(lastName);
		}
		
		switch(testToPerform) {
		case "":
			System.out.println("Normal validation");
			break;
		case "CreditBureauCheck":
			scrapeADM.setCreditBureau(true);
			break;
		case "Approve":
			scrapeADM.setSelectStatus(true);
//			scrape();
			break;
		case "CreditBureauCheckAndApprove":
			scrapeADM.setCreditBureau(true);
			scrapeADM.setSelectStatus(true);
			break;
		}
		
		TSYSReqRespDO tdo = new TSYSReqRespDO(ReqType.ISYS, "ADM", "", scrapeADM);
				dto = (ADM_DTO) isys.handleRequest(tdo);
		return dto;
	}
	
}
