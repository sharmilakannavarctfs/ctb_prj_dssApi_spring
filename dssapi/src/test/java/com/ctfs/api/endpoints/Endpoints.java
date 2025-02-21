package com.ctfs.api.endpoints;

public interface Endpoints {
    static String BaseURL = "http://wbapi-qateluslidc.internal.ctfs.com";//
    static String BaseURLDSA = "http://d9cbwpdssa01:8765";
    static String baseUrl_dss_dev = "http://d9cbwpdssa01:";
    static String baseUrl_dss_qa03 = "http://q9dssa03:";
    static String baseUrl_dss_qa04 = "http://q9dssa04:";

    static String initializeAPI = "todos/1";
    static String validateTMXProfileAPI = "/brb/v1/validateTMXProfile";
    static String submitApplicationAPI = "/brb/v2/submitApp";
    static String pollAppStatusAPI = "/brb/v1/pollAppStatus";
    static String postEcho = "getProductTransactions";
    static String getTermInterestRates = "getTermInterestRate";
    static String getPortfolios = "getPortfolios";
    static String getRegisteredPlanTransactions = "getRegisteredPlanTransactions";
    static String getTerm = "getTerm";
    static String getProductsByCategory = "getProducts";
    static String getTermRates = "getTermRates";
    static String getPortfolioDetails = "getPortfolioDetails";
    static String getLinkedAccounts = "getLinkedAccounts";
    static String getInterMemberTransferAccounts = "getInterMemberTransferAccounts";
    static String getDemandRates = "getDemandRates";
    static String getProductDetails = "getProductDetails";
    static String getCustomerProducts = "getCustomerProducts";
    static String getDemandInterestRate = "getDemandInterestRate";
    static String updateCustomer = "updateCustomer";
    static String getCustomer = "getCustomer";
    static String getDemandPeriodicLineFeatures = "getDemandPeriodicLineFeatures";
    static String getTermPeriodicLineFeature = "getTermPeriodicLineFeature";
    static String ts2_getAccount = "/v2/getAccount";
    static String ts2_estatementdeenrollment = "/v2/eStatementDeenrollment";
    static String ts2_enrollestatement = "/v1/enrollEstatement";
    static String ts2_evaluateCreditLimit = "/v1/evaluateCreditLimit";
    static String ts2_getAccountInfo = "/v1/getAccountInfo";
    static String ts2_getCustomer = "/v1/getCustomer";
    static String ts2_getCustomerIncomeInfo = "/v1/getCustomerIncomeInfo";
    static String ts2_getCustomerInfo = "/v1/post";
    static String ts2_retrieveCustomerAddressAvailability = "/v1/retrieveCustomerAddressAvailabity";
    static String ts2_retrieveCustomerAddresses = "/v1/retrieveCustomerAddresses";
    static String ts2_InqAccountforCustomCode = "/v1/inqAccountforCustomCode";
    static String ts2_inqAccountQueue = "/v1/inqAccountQueue";
    static String ts2_inqAccountScores = "/v1/inqAccountScores";
    static String ts2_inqTriad8 = "/v1/inqTriad8";
    static String ts2_inquiryCvc = "/v1/inquiryCvc";
    static String ts2_inquireDispute = "/v1/inquireDispute";
    static String ts2_getCustomerForCustomCode = "/v1/getCustomerforCustomCode";
    static String ts2_getCustomerEmployerInfo = "/v1/getCustomerEmployerInfo";
    static String off_addorupdtnboMtx = "/v1/addOrUpdateNBORepricingMatrix";
    static String off_addorupdtdisclGrp = "/v1/addOrUpdateDisclosureGroup";
    static String off_addorupdtctfsdisclGrp = "/v1/addOrUpdateCTFSDisclosureGroupOffer";
    static String off_retrdisclGrp = "/v1/retrieveDisclosureGroups";
    static String off_retrCTFSdisclGrpOffs = "/v1/retrieveCTFSDisclosureGroupOffers";
    static String off_deletenboMtx = "/v1/deleteNBORepricingMatrix";
    static String off_deletedisclGrp = "/v1/deleteDisclosureGroup";
    static String off_deletectfsdisclGrpoffer = "/v1/deleteCTFSDisclosureGroupOffer";
    static String msg_deleteMessage = "/v1/deleteMessage";
    static String msg_retrieveMessages = "/v1/retrieveMessages";
    static String msg_retrieveMessagesCnt="/v1/retrieveMessageCnt";
    static String msg_createDynamicMessage = "/v1/createDynamicMessage";
    static String msg_retrieveDefinedMessage = "/v1/retrieveDefinedMessage";
    static String msg_createDefinedMessage = "/v1/maint/createDefinedMessage";
    static String ts2_retrieveCardStatuses = "/v1/retrieveCardStatuses";
    static String ts2_retrieveChipCardDetails = "/v1/retrieveChipCardDetails";

}
