package com.ctfs.api.utils;

public interface Database {
	String db_app_app_tctoutlet = "jdbc:oracle:thin:@d9ctbora03:4190:mi01ppdb,app_tctoutlet,mi01ddb_p1rL0yl1t_0817-"
			+ "jdbc:oracle:thin:@s9cb00misdb01:4190:mi01ppdb,app_tctoutlet,MI01PPDB_ad82jf2!9k(_1018";
	String db_app_webic_app = "jdbc:oracle:thin:@d9ctbora01:4150:olt1ddb,WEBIC_APP,olt_webica66_0713-"
			+ "jdbc:oracle:thin:@s9cb00oltdb01:4150:olt1ppdb,WEBIC_APP,olt_webicpp1_0913";
	String db_app_instcr = "jdbc:oracle:thin:@d9cbajdevdb11.idm.internal.ctfst.com:4150:olt1ddb,app_instcr,olt1dappinstcr-"
			+ "jdbc:oracle:thin:@s9cb00oltdb01:4150:olt1ppdb,app_instcr,ppapp_admin";
	
	String db_app_instcr23 = "jdbc:oracle:thin:@d9ctbora01:4150:olt1ddb,app_instcr,olt1dappinstcr-"
			+ "jdbc:oracle:thin:@s9cb00oltdb01:4150:olt1ppdb,app_instcr_23,0LtIPPdb_YiPk98*vB!_0423";
	
	String db_pduser = "jdbc:oracle:thin:@D9CB00EXCDB01:4260:ccsdjx,pduser,mappings-"
			+ "jdbc:oracle:thin:@s9cb00excdb01:4260:ccsppjx,pduser,mappings";
	String db_pdowner = "jdbc:oracle:thin:@D9CB00ORADB06:4330:ccs01d,pduser,ccs01d_pduser_1012-"
			+ "jdbc:oracle:thin:@D9CB00ORADB06:4330:ccs01d,pduser,ccs01d_pduser_1012";
	
	String db_ssService = "jdbc:oracle:thin:@d9ctbora04:4200:css1tdb,sdd,sdd0123-"
			+ "jdbc:oracle:thin:@d9ctbora04:4200:css1tdb,sdd,sdd0123";
//	String db_consent_store = "jdbc:oracle:thin:@d9ctbora01:4140:WCC1DDB,CONSENT_STORE,WCC1DDB_C0nS3nT_sT0R3_0722-"
//			+ "jdbc:oracle:thin:@d9ctbora01:4140:WCC1DDB,CONSENT_STORE,WCC1DDB_C0nS3nT_sT0R3_0722";
	String db_consent_store = "jdbc:oracle:thin:@D9CB00EXCDB01:4260:CCSDJX,CONSENT_STORE_APP,CCSDJX_C0nS3nT_sT0R3_0123-"
			+ "jdbc:oracle:thin:@S9CB00EXCDB01.ctal.ctc:4260:CCSPPJX,CONSENT_STORE_APP,CCSPPJX_A33Utye24!_0123";
	String db_RecognitionMgr_app = "jdbc:oracle:thin:@S9CB00WCCDB01:4140:WCC1PPDB,RECOGNITION_MGR_APP,wcc1ppdb_Ap1p_0615-"
			+ "jdbc:oracle:thin:@S9CB00WCCDB01:4140:WCC1PPDB,RECOGNITION_MGR_APP,wcc1ppdb_Ap1p_0615";

//	String db_userProfile_service = "jdbc:mysql://t9dsdb02.ctal.ctc:3306/dss?verifyServerCertificate=false&useSSL=false&requireSSL=false&autoReconnect=true&serverTimezone=EST5EDT,dss_user,hXFImWAFCOlIUikS-"
//			+ "jdbc:mysql://Q9DSDB-VIP.ctal.ctc:3306/dss?verifyServerCertificate=false&useSSL=false&requireSSL=false&autoReconnect=true&serverTimezone=EST5EDT,dss_user,R$sx$kp[";
	String db_userProfile_service = "jdbc:mysql://t9dsdb02.ctal.ctc:3306/dss?verifyServerCertificate=false&useSSL=false&requireSSL=false&autoReconnect=true&serverTimezone=EST5EDT,dss_user,hXFImWAFCOlIUikS-"
			+ "jdbc:mysql://Q9DSDB-VIP.ctal.ctc:3306/dss,dss_user,R$sx$kp[";
	String db_dss_profile = "jdbc:mysql://t9dsdb02.ctal.ctc:3306/dss,dss_user,hXFImWAFCOlIUikS-"
			+ "jdbc:mysql://Q9DSDB-VIP.ctal.ctc:3306/dss,dss_user,R$sx$kp[";
	
	String db_mob_con = "jdbc:mysql://t9dsdb02.ctal.ctc:3306/dss,dss_user,hXFImWAFCOlIUikS%" 
			+ "jdbc:mysql://Q9DSDB-VIP.ctal.ctc:3306/dss,dss_user,R$sx$kp[";

	String db_app_retension = "jdbc:oracle:thin:@d9ctbora01:4150:olt1ddb,app_retention,olt1ddbappretention-"
			+ "jdbc:oracle:thin:@d9ctbora01.ctal.ctc:4150:olt1ddb,app_retention,olt1ddbappretention";
	
	String db_loyalty_audit = "jdbc:oracle:thin:@d9ctbora01:4150:olt1ddb,app_loyalty,olt1dapploy-"
			+"jdbc:oracle:thin:@s9cb00oltdb01:4150:olt1ppdb,app_loyalty,olt1dapploy";

	String db_T9sdb02 = "3306/dss?verifyServerCertificate=false&useSSL=false&requireSSL=false&autoReconnect=true&serverTimezone=EST5EDT";
}
