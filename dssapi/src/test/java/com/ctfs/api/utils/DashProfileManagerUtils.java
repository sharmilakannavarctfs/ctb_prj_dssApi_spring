package com.ctfs.api.utils;



import java.sql.SQLException;

//Singleton design pattern

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbootjdbc.com.spring.jdbc.creditProfile.ProfileManager;
import com.springbootjdbc.com.spring.jdbc.dto.DashProfileDTO;

@Component
public class DashProfileManagerUtils{
	 @Autowired
	    private ProfileManager pm ; 

	private static DashProfileDTO dashProfileDTO ;
	
	public void initializeTestProfile(String groupName) {
		
		try 
		{ 
			dashProfileDTO = pm.getProfile(groupName);
			System.out.println(dashProfileDTO.getDashUid());
		} 
		catch (Throwable e) 
		{ 
			e.printStackTrace(); 
		} 
		
	}
	
	
	
//	public void initializeProfileByPan(String pan) {
//		
//		try 
//		{ 
//			dashProfileDTO = pm .getProfileByPan(pan);
//			System.out.println(dashProfileDTO.getDashUid());
//		} 
//		catch (Throwable e) 
//		{ 
//			e.printStackTrace(); 
//		} 
//		
//	}
	
//	public void initializeProfileByDashUid(String dash_uid) {
//
//		try 
//		{ 
//			dashProfileDTO = pm.getProfileByDashUid(dash_uid);
//			System.out.println(dashProfileDTO.getDashUid());
//		} 
//		catch (Throwable e) 
//		{ 
//			e.printStackTrace(); 
//		} 
//
//	}
	
	
	public String getCardNbr() {
		
		return dashProfileDTO.getDefaultAccountInfoBean().getPan();
		
	}
	
	
	public String getCvc() {

		return dashProfileDTO.getDefaultAccountInfoBean().getCvc();

	}

	public String getUsername() 
	{ 
		dashProfileDTO.getAccountInfos();
		return dashProfileDTO.getDashUid(); 
		
	}
	public String getPassword() 
	{ 
		dashProfileDTO.getAccountInfos();
		return dashProfileDTO.getDashPwd(); 
		
	}
	
	
	public String getAccountID() throws SQLException 
	{ 
		return dashProfileDTO.getDefaultAccountInfoBean().getTSYSAccountInfo().getAccountID();
		
	}
	
	public String getCustomerId() throws Throwable {
		
		return dashProfileDTO.getDefaultAccountInfoBean().getTSYSAccountInfo().getCustomerID();
		
	}
	
	

//	public void lockCleanUp() { 
//
//		ProfileManager pm = null; 
//
//		if (dashProfileDTO  != null) 
//		{ 
//			if (pm == null) 
//			{ 
//				try { pm = new
//						ProfileManager(); 
//				} 
//				catch (Throwable e) 
//				{ 
//					e.printStackTrace(); 
//				}
//			}
//			pm.removeLock(dashProfileDTO.getDashUid());
//			System.out.println("Getting Credit User Id after Clean Up "+dashProfileDTO.getDashUid());
//			//dashProfileDTO = null;
//		}
//
//	}



}
