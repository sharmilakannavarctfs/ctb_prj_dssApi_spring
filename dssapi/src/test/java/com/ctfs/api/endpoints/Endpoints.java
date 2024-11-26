package com.ctfs.api.endpoints;

public interface Endpoints {
 static String  BaseURL ="http://wbapi-qateluslidc.internal.ctfs.com";//
 static String  BaseURLDSA ="http://d9cbwpdssa01:8765";
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
 
}
