package com.ctfs.common.tsys;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ctfs.tsys.model.IAED_DTO;
import com.ctfs.tsys.model.IAHR_DTO;
import com.ctfs.tsys.model.IAPG_DTO;
import com.ctfs.tsys.model.IAPH_DTO;
import com.ctfs.tsys.model.IAPS_DTO;
import com.ctfs.tsys.model.IASC_DTO;
import com.ctfs.tsys.model.ICAU_DTO;
import com.ctfs.tsys.model.IECS_DTO;
import com.ctfs.tsys.model.IFRDTransactionItem_DTO;
import com.ctfs.tsys.model.IFRD_DTO;
import com.ctfs.tsys.model.ITAR_DTO;
import com.ctfs.tsys.model.MAAP_DTO;
import com.ctfs.tsys.model.MACD_DTO;
import com.ctfs.tsys.model.MACL_DTO;
import com.ctfs.tsys.model.MACR_DTO;
import com.ctfs.tsys.model.MACV_DTO;
import com.ctfs.tsys.model.MAEB_DTO;
import com.ctfs.tsys.model.MAMM_DTO;
import com.ctfs.tsys.model.MAMO_DTO;
import com.ctfs.tsys.model.MAOO_DTO;
import com.ctfs.tsys.model.MASC_DTO;
import com.ctfs.tsys.model.MCCD_DTO;
import com.ctfs.tsys.model.MCEA_DTO;
import com.ctfs.tsys.model.MCEI_DTO;
import com.ctfs.tsys.model.MCFI_DTO;
import com.ctfs.tsys.model.MCGI_DTO;
import com.ctfs.tsys.model.MCNA_DTO;
import com.ctfs.tsys.model.MCOS_DTO;
import com.ctfs.tsys.model.MPTI_DTO;
import com.ctfs.tsys.model.RCRD_DTO;
import com.ctfs.tsys.model.WAGN_DTO;
import com.ctfs.tsys.model.WCAD_DTO;
import com.ctfs.uat.automation.tsys_interface2.wrapper.TSYSClient;
import com.ctfs.uat.automation.tsys_interface2.wrapper.TSYSResponse;
import com.ctfs.ui.utils.DriverManager;

@Component
public class TsysScreensScapper {

	public static TSYSClient tsys;

//	private final Logger log = LoggerFactory.getLogger(TsysScreensScapper.class);
	
	public TsysScreensScapper(DriverManager driverManager) {
		tsys = new TSYSClient();
	}
	
	public WCAD_DTO updateWCADDetls(String accountNumber) throws Exception
	{
		WCAD_DTO wcad=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// WCAD Screen Details
			tsys.addUpdateWcadRequest(accountNumber, "", "ct", "pp");
			tsys.addWcadRequest(accountNumber);
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			wcad = resp.getWcadDTO();
			System.out.println("wcad=" + wcad);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
		return wcad;
	}


	public MAAP_DTO getMAAPDetls(String accountNumber) throws Exception
	{
		MAAP_DTO maap=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MAAP Screen Details
			tsys.addMaapRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			maap = resp.getMaapDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return maap;
	}

	public void updateEmailIdInTsys(String accountNumber,String email) throws Exception
	{
		try		
		{
			tsys.startTransaction();
			tsys.addUpdateMceaRequest(accountNumber, email);
			TSYSResponse resp = tsys.makeRequest();
//			log.info("Email Id Updated Successfully");
			System.out.println("Email Id Updated Successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public MAMO_DTO getMAMODetls(String accountNumber) throws Exception
	{
		MAMO_DTO mamo=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MAMO Screen Details
			tsys.addMamoRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			mamo = resp.getMamoDTO();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
		return mamo;
	}


	public WAGN_DTO getWAGNDetls(String accountNumber) throws Exception
	{
		WAGN_DTO wagn=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// WAGN Screen Details
			tsys.addWagnRequest(accountNumber);
//			TSYSResponse resp = tsys.makeRequest();
			
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			wagn = resp.getWagnDTO();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
		return wagn;
	}

	public IAED_DTO getIAEDDetls(String accountNumber) throws Exception
	{
		IAED_DTO iaed=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// IAED Screen Details
			tsys.addIaedRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			iaed = resp.getIaedDTO();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
		return iaed;
	}


	public RCRD_DTO getRCRDDetls(String accountNumber) throws Exception
	{
		RCRD_DTO rcrd=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// RCRD Screen Details
			tsys.addRcrdRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			rcrd = resp.getRcrdDTO();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
		return rcrd;
	}


	public MCCD_DTO getMCCDDetls(String accountNumber) throws Exception
	{
		MCCD_DTO mccd=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCCD Screen Details
			tsys.addMccdRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			mccd = resp.getMccdDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mccd;
	}

	public IASC_DTO getIASCDetls(String accountNumber) throws Exception
	{
		IASC_DTO iasc=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// IACS Screen Details
			tsys.addIascRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			iasc = resp.getIascDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return iasc;
	}

	public IAHR_DTO getIAHRDetls(String accountNumber) throws Exception
	{
		IAHR_DTO iahr=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// IAHR Screen Details
			tsys.addIahrRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			iahr = resp.getIahrDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return iahr;
	}	

	public MCNA_DTO getMCNADetls(String accountNumber) throws Exception
	{
		MCNA_DTO mcna=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCNA Screen
			tsys.addMcnaRequest(accountNumber);
			
//			TSYSResponse resp = tsys.makeRequest();
			
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			mcna = resp.getMcnaDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mcna;
	}

	public MCEA_DTO getMCEADetls(String accountNumber) throws Exception
	{
		MCEA_DTO mcea=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCEA Screen Details
			tsys.addMceaRequest(accountNumber);
//			TSYSResponse resp1 = tsys.makeRequest();
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			mcea = resp.getMceaDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mcea;
	}

	public MCFI_DTO getMCFIDetls(String accountNumber) throws Exception
	{
		MCFI_DTO mcfi=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCFI Screen Details
			tsys.addMcfiRequest(accountNumber);
//			TSYSResponse respMCFI = tsys.makeRequest();
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			mcfi = resp.getMcfiDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mcfi;
	}

	public MCGI_DTO getMCGIDetls(String accountNumber) throws Exception
	{
		MCGI_DTO mcgi=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCGI Screen Details
			tsys.addMcgiRequest(accountNumber);
//			TSYSResponse resp2 = tsys.makeRequest();
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp2 = tsys.getAsynchResponses(id);
			mcgi = resp2.getMcgiDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mcgi;
	}

	public MCEI_DTO getMCEIDetls(String accountNumber) throws Exception
	{
		MCEI_DTO mcei=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCEI Screen Details
			tsys.addMceiRequest(accountNumber);
//			TSYSResponse resp2 = tsys.makeRequest();
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp2 = tsys.getAsynchResponses(id);
			mcei = resp2.getMceiDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mcei;
	}

	public MACR_DTO getMACRDetls(String accountNumber) throws Exception
	{
		MACR_DTO macr=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCEI Screen Details
			tsys.addMacrRequest(accountNumber);
			TSYSResponse resp2 = tsys.makeRequest();
			macr = resp2.getMacrDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return macr;
	}

	public ITAR_DTO getITARDetls(String accountNumber) throws Exception
	{
		ITAR_DTO itar=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// ITAR Screen Details
			tsys.addItarRequest(accountNumber);
			TSYSResponse resp2 = tsys.makeRequest();
			itar = resp2.getItarDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return itar;
	}

	public MASC_DTO getMASCDetls(String accountNumber) throws Exception
	{
		MASC_DTO masc=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MASC Screen
			tsys.addMascRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			masc = resp.getMascDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return masc;
	}

	public ICAU_DTO getICAUDetls(String accountNumber) throws Exception
	{
		ICAU_DTO icau=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// ICAU Screen
			tsys.addIcauRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			icau = resp.getIcauDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return icau;
	}

	public IECS_DTO getIECSDetls(String accountNumber) throws Exception
	{
		IECS_DTO iecs=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// IECS Screen
			tsys.addIecsRequest(accountNumber);
//			TSYSResponse resp = tsys.makeRequest();
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			iecs = resp.getIecsDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return iecs;
	}

	public MPTI_DTO getMPTIDetls(String accountNumber) throws Exception
	{
		MPTI_DTO mpti=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MPTI Screen Details
			tsys.addMptiRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			mpti = resp.getMptiDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mpti;
	}

	public IAPH_DTO getIAPHDetls(String accountNumber) throws Exception
	{
		IAPH_DTO iaph=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// IAPH Screen Details
			tsys.addIaphRequest(accountNumber);
//			TSYSResponse resp = tsys.makeRequest();
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			
			iaph = resp.getIaphDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return iaph;
	}

	public IAPS_DTO getIAPSDetls(String accountNumber) throws Exception
	{
		IAPS_DTO iaps=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// IAPH Screen Details
			tsys.addIapsRequest(accountNumber);
//			TSYSResponse resp = tsys.makeRequest();
			
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			iaps = resp.getIapsDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return iaps;
	}

	public MAMM_DTO getMAMMDetls(String accountNumber) throws Exception
	{
		MAMM_DTO mamm=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MAMM Screen Details
			tsys.addMammRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			mamm = resp.getMammDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mamm;
	}

	public MCOS_DTO getMCOSDetls(String accountNumber) throws Exception
	{
		MCOS_DTO mcos=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCOS Screen Details
			tsys.addMcosRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			mcos = resp.getMcosDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return mcos;
	}

	public MAEB_DTO getMAEBDetls(String accountNumber) throws Exception
	{
		MAEB_DTO maeb=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MAEB Screen Details
			tsys.addMaebRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			maeb = resp.getMaebDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return maeb;
	}

	public MAOO_DTO getMAOODetls(String accountNumber) throws Exception
	{
		MAOO_DTO maoo=null;
		try
		{	
			tsys.startTransaction();
			tsys.addMaooRequest(accountNumber);
//			TSYSResponse resp = tsys.makeRequest();
			
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			maoo = resp.getMaooDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return maoo;
	}

	public MCFI_DTO getMCFIDetails(String accountNumber)
	{
		MCFI_DTO mcfi=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCFI Screen Details
			tsys.addMcfiRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			mcfi = resp.getMcfiDTO();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mcfi;
	}

	public MACL_DTO getMACLDetls(String accountNumber) throws Exception
	{
		MACL_DTO macl=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MASC Screen
			tsys.addMaclRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			macl = resp.getMaclDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return macl;
	}

	public MACD_DTO getMACDDetls(String accountNumber) throws Exception
	{
		MACD_DTO macd=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MACD Screen Details
			tsys.addMacdRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			macd = resp.getMacdDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return macd;
	}

	public MAOO_DTO getDMDetls(String accountNumber) throws Exception
	{
		MAOO_DTO maoo=null;
		try
		{	
			tsys.startTransaction();
			tsys.addMaooRequest(accountNumber);
//			TSYSResponse resp = tsys.makeRequest();
			
			long id = tsys.makeAsynchRequest();
			System.out.println("id=" + id);
			TSYSResponse resp = tsys.getAsynchResponses(id);
			maoo = resp.getMaooDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return maoo;
	}

	public void updateEmailMCEADetls(String accountNumber, String email) throws Exception
	{
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCEA Screen Details
			tsys.addUpdateMceaRequest(accountNumber, email);
			TSYSResponse resp1 = tsys.makeRequest();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void removeDOB(String accountNumber) throws Exception
	{
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCGI Screen Details
			tsys.deleteDOBMcgi(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void removeHomePhone(String accountNumber) throws Exception
	{
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCNA Screen Details
			tsys.deleteMcnaHomePhone(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void removeAddress(String accountNumber) throws Exception
	{
		try
		{
			/// TSYS Response
			tsys.startTransaction();

			/// MCNA Screen Details
			tsys.deletePrimaryAddress(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public List<IFRDTransactionItem_DTO> ifrdTransactions(String accountNumber,String fDate, String eDate) throws Exception
	{
		IFRD_DTO ifrdScreen=null;
		List<IFRDTransactionItem_DTO> ifrdTransactionItem_DTOs=null;
		try
		{
			System.out.println("Account Number "+accountNumber);
			tsys.startTransaction();
			System.out.println("Start Date is "+fDate);
			System.out.println("End Date is "+eDate);
			tsys.addIfrdRequestWithDateRange(accountNumber, new SimpleDateFormat("MM/dd/yyyy").parse(fDate), 
					new SimpleDateFormat("MM/dd/yyyy").parse(eDate));
			long idifrd = tsys.makeAsynchRequest();
			System.out.println("Id is "+idifrd);
			TSYSResponse respIfrd = tsys.getAsynchResponses(idifrd);
			ifrdScreen = respIfrd.getIfrdDTO();
			ifrdTransactionItem_DTOs = ifrdScreen.getTransItems();
			System.out.println((ifrdTransactionItem_DTOs.size() + "  List of Transactions"));
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return ifrdTransactionItem_DTOs;
	}

	public MACV_DTO getMACVDetls(String accountNumber) throws Exception
	{
		MACV_DTO macv=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();
			/// MACV Screen Details
			tsys.addMacvRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			macv = resp.getMacvDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return macv;
	}
	
	public IAPG_DTO getIapgDtl(String accountNumber) throws Exception
	{
		IAPG_DTO iapg=null;
		try
		{
			/// TSYS Response
			tsys.startTransaction();
			/// IAPG Screen Details
			tsys.addIapgRequest(accountNumber);
			TSYSResponse resp = tsys.makeRequest();
			iapg = resp.getIapgDTO();
		}
		catch(Exception e)
		{
//			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e);
		}
		return iapg;
	}
}
