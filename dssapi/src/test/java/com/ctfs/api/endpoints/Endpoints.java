package com.ctfs.api.endpoints;

public interface Endpoints {
 static String  BaseURL ="http://wbapi-qateluslidc.internal.ctfs.com";//
 static String  BaseURLDSA ="http://d9cbwpdssa01:8765";
 static String  baseUrl_dss_dev ="http://d9cbwpdssa01:";
 static String  baseUrl_dss_qa03 ="http://q9dssa03:";
 static String  baseUrl_dss_qa04 ="http://q9dssa04:";
 
 static String initializeAPI= "todos/1";
 static String validateTMXProfileAPI ="/brb/v1/validateTMXProfile";
 static String submitApplicationAPI ="/brb/v2/submitApp";
 static String pollAppStatusAPI="/brb/v1/pollAppStatus";
 static String postEcho="getProductTransactions";
 static String getTermInterestRates="getTermInterestRate";
 static String getPortfolios="getPortfolios";
 static String getRegisteredPlanTransactions="getRegisteredPlanTransactions";
 static String getTerm="getTerm";
 static String getProductsByCategory="getProducts";
 static String getTermRates="getTermRates";
 static String getPortfolioDetails="getPortfolioDetails";
 static String getLinkedAccounts="getLinkedAccounts";
 static String getInterMemberTransferAccounts="getInterMemberTransferAccounts";
 static String getDemandRates="getDemandRates";
 static String getProductDetails="getProductDetails";
 static String getCustomerProducts="getCustomerProducts";
 static String getDemandInterestRate="getDemandInterestRate";
 static String updateCustomer="updateCustomer";
 static String getCustomer ="getCustomer";
 static String getDemandPeriodicLineFeatures="getDemandPeriodicLineFeatures";
 static String getTermPeriodicLineFeature="getTermPeriodicLineFeature";
 static String getAccount_ts2="/v2/getAccount";
 static String ts2_estatementdeenrollment="/v2/eStatementDeenrollment";
 static String ts2_enrollestatement="/v1/enrollEstatement";
 static String ts2_evaluateCreditLimit="/v1/evaluateCreditLimit";
 static String ts2_getAccountInfo="/v1/getAccountInfo";
 static String ts2_getCustomer = "/v1/getCustomer"; 
 static String ts2_getCustomerIncomeInfo = "/v1/getCustomerIncomeInfo"; 
 static String ts2_getCustomerForCustomCode= "/v1/getCustomerforCustomCode"; 
 static String ts2_getCustomerEmployerInfo = "/v1/getCustomerEmployerInfo"; 
 static String off_addorupdtnboMtx = "/v1/addOrUpdateNBORepricingMatrix"; 
 static String off_addorupdtdisclGrp = "/v1/addOrUpdateDisclosureGroup"; 
 static String off_deletenboMtx = "/v1/deleteNBORepricingMatrix"; 
 static String off_deletedisclGrp = "/v1/deleteDisclosureGroup"; 
}
