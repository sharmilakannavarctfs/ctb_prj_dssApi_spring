package com.ctfs.api.pojos.response;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ctfs.api.pojos.request.ts2.TS2RequestPojo;
import com.ctfs.api.pojos.response.ts2response.Fault;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class GetAccount {
		private String actionLastCreditLimitReview;
		private Integer actualBillPastDue;
		private String agencyCode;
		private String agencyDesc;
		private String altDisclosureGroup;
		private Integer amtPaymentsCTD;
		
		private Integer apr;
		private Integer arqCounter;
		private String associationProductCode;
		private String associationRewardProgramCode;
		private String authorizationReason;
		private String authorizationStatus;
		private String autoDialFlag;
		private Integer availableCash;
		private Integer availableCashPad;
		private Integer availableCredit;
		private Integer availableCreditPad;
		private String availableCreditSign;
		private Integer billingCycle;
		private String cardEnvironment;
		private Integer cardExpiryDate;
		private Integer cashAdvanceCTDAmount;
		private Integer cashLimit;
		private String cdf;
		private String cdf10;
		private String chipCardIndicator;
		private String chipCardOptionSet;
		private String clientProductCode;
		private Integer consecutiveDaysPastDue;
		private Integer creditCTDAmount;
		private Integer creditLimit;
		private String creditLimitDate;
		private Integer creditLimitPrevious;
		private String creditLimitTempEndDate;
		private String creditLimitTempStartDate;
		private Integer currentBalance;
		private String currentBalanceSign;
		private String custSegId;
		@JsonProperty("custSegValue")
		private String custSegValue;
		@JsonProperty("dateLastCreditLimitReview")
		private String dateLastCreditLimitReview;
		@JsonProperty("dateLastRequest")
		private String dateLastRequest;
		@JsonProperty("dateNextWork")
		private String dateNextWork;
		@JsonProperty("dateThirdPartyNameAdded")
		private String dateThirdPartyNameAdded;
		@JsonProperty("disclosureFlag")
		private String disclosureFlag;
		@JsonProperty("disclosureGroup")
		private String disclosureGroup;
		@JsonProperty("disclosureGroupDate")
		private String disclosureGroupDate;
		@JsonProperty("disclosureGroupDesc")
		private String disclosureGroupDesc;
		@JsonProperty("electronicStatementFlag")
		private String electronicStatementFlag;
		@JsonProperty("electronicVendorId")
		private String electronicVendorId;
		@JsonProperty("enhancement")
		private String enhancement;
		@JsonProperty("faults")
		private List<Fault> faults;
		@JsonProperty("fixedPaymentAmount")
		private Integer fixedPaymentAmount;
		@JsonProperty("fixedPaymentStartDate")
		private String fixedPaymentStartDate;
		@JsonProperty("fixedPaymentStopDate")
		private String fixedPaymentStopDate;
		@JsonProperty("id")
		private Integer id;
		@JsonProperty("idCompliant2Flag")
		private String idCompliant2Flag;
		@JsonProperty("idCompliantFlag")
		private String idCompliantFlag;
		@JsonProperty("increaseFreezeFlag")
		private String increaseFreezeFlag;
		@JsonProperty("lastAccessDateTime")
		private String lastAccessDateTime;
		@JsonProperty("lastCardIssueDate")
		private String lastCardIssueDate;
		@JsonProperty("lastCashAdvanceAmount")
		private Integer lastCashAdvanceAmount;
		@JsonProperty("lastCashAdvanceDate")
		private String lastCashAdvanceDate;
		@JsonProperty("lastCreditAdjustmentDate")
		private String lastCreditAdjustmentDate;
		@JsonProperty("lastCreditTransactionAmount")
		private Integer lastCreditTransactionAmount;
		@JsonProperty("lastCreditTransactionDate")
		private String lastCreditTransactionDate;
		@JsonProperty("lastDisputeAmount")
		private Integer lastDisputeAmount;
		@JsonProperty("lastDisputeDate")
		private String lastDisputeDate;
		@JsonProperty("lastFinanceChargeAmount")
		private Integer lastFinanceChargeAmount;
		@JsonProperty("lastNSFAmount")
		private Integer lastNSFAmount;
		@JsonProperty("lastNSFDate")
		private String lastNSFDate;
		@JsonProperty("lastOverlimitDate")
		private String lastOverlimitDate;
		@JsonProperty("lastPayment")
		private Integer lastPayment;
		@JsonProperty("lastPaymentDate")
		private String lastPaymentDate;
		@JsonProperty("lastPurchase")
		private Integer lastPurchase;
		@JsonProperty("lastPurchaseDate")
		private String lastPurchaseDate;
		@JsonProperty("lastReqOperator")
		private String lastReqOperator;
		@JsonProperty("lastReqReason")
		private String lastReqReason;
		@JsonProperty("lastStatementBalDue")
		private Integer lastStatementBalDue;
		@JsonProperty("lastStatementBalance")
		private Integer lastStatementBalance;
		@JsonProperty("lastStatementBalanceSign")
		private String lastStatementBalanceSign;
		@JsonProperty("lastStatementDate")
		private String lastStatementDate;
		@JsonProperty("lifeToDateInterestPd")
		private Integer lifeToDateInterestPd;
		@JsonProperty("loyaltyNumber")
		private String loyaltyNumber;
		@JsonProperty("minPaymentDueSign")
		private String minPaymentDueSign;
		@JsonProperty("minimumPaymentDue")
		private Integer minimumPaymentDue;
		@JsonProperty("mostRecentPastDueDate")
		private String mostRecentPastDueDate;
		@JsonProperty("nbrStmtsLTD")
		private Integer nbrStmtsLTD;
		@JsonProperty("nextStatementDate")
		private String nextStatementDate;
		@JsonProperty("nsfCounter")
		private Integer nsfCounter;
		@JsonProperty("openDate")
		private String openDate;
		@JsonProperty("optOutOnlineOffer")
		private String optOutOnlineOffer;
		@JsonProperty("optOuts")
		private List<OptOuts> optOuts;
		@JsonProperty("otherAccountNumber")
		private String otherAccountNumber;
		@JsonProperty("otherCardholders")
		private List<Cardholder> otherCardholders;
		@JsonProperty("outstandingAuthorizations")
		private Integer outstandingAuthorizations;
		@JsonProperty("overlimit")
		private Integer overlimit;
		@JsonProperty("pastDue")
		private Integer pastDue;
		@JsonProperty("paymentDue")
		private Integer paymentDue;
		@JsonProperty("paymentDueDate")
		private String paymentDueDate;
		@JsonProperty("paymentDueSign")
		private String paymentDueSign;
		@JsonProperty("paymentHistory")
		private PaymentHistory paymentHistory;
		@JsonProperty("pinCustomizationResult")
		private String pinCustomizationResult;
		@JsonProperty("postedBalance")
		private Integer postedBalance;
		@JsonProperty("previousBalance")
		private Integer previousBalance;
		@JsonProperty("previousDisclosureGroup")
		private String previousDisclosureGroup;
		@JsonProperty("previousDisclosureGroupDesc")
		private String previousDisclosureGroupDesc;
		@JsonProperty("previousQueueDate")
		private String previousQueueDate;
		@JsonProperty("previousQueueId")
		private String previousQueueId;
		@JsonProperty("primaryCardholder")
		private PrimaryCardholder primaryCardholder;
		@JsonProperty("productUpgradeCode")
		private String productUpgradeCode;
		@JsonProperty("products")
		private List<TSYSProduct> products;
		@JsonProperty("purchaseCTDAmount")
		private Integer purchaseCTDAmount;
		@JsonProperty("queueDate")
		private String queueDate;
		@JsonProperty("queueId")
		private String queueId;
		@JsonProperty("queueKey")
		private String queueKey;
		@JsonProperty("repaymentMonths")
		private Integer repaymentMonths;
		@JsonProperty("restartToken")
		private String restartToken;
		@JsonProperty("scores")
		private AccountScore scores;
		@JsonProperty("solicitationFlag")
		private String solicitationFlag;
		@JsonProperty("startDate")
		private Date startDate;
		@JsonProperty("statementMinimumDue")
		private Integer statementMinimumDue;
		@JsonProperty("statementMinimumDueDate")
		private String statementMinimumDueDate;
		@JsonProperty("status")
		private String status;
		@JsonProperty("statusCodes")
		private List<String> statusCodes;
		@JsonProperty("statusMsg")
		private String statusMsg;
		@JsonProperty("stopDate")
		private String stopDate;
		@JsonProperty("suppressHardCopyFlag")
		private String suppressHardCopyFlag;
		@JsonProperty("thirdPartyName")
		private String thirdPartyName;
		@JsonProperty("totalDue")
		private Integer totalDue;
		@JsonProperty("totalMinimumDue")
		private Integer totalMinimumDue;
		@JsonProperty("tsysProductCode")
		private String tsysProductCode;
		@JsonProperty("tuAuthenticationRequired")
		private String tuAuthenticationRequired;
		@JsonProperty("type")
		private String type;
		@JsonProperty("underivedOutstandingMinPaymentDue")
		private Integer underivedOutstandingMinPaymentDue;
		@JsonProperty("vipAccountType")
		private String vipAccountType;
		@JsonProperty("yearToDateInterestChgd")
		private Integer yearToDateInterestChgd;
		@JsonProperty("yearToDateInterestPd")
		private Integer yearToDateInterestPd;
		@JsonProperty("amtStmtFullPmt")
		private Integer amtStmtFullPmt;

}
