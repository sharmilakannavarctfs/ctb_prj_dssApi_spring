package com.ctfs.api.pojos.response;


import java.util.List;


import lombok.Data;

@Data
public class Account {
	
	private String addressAddedDate;
	private String availableCash;
	private String availableCashPad;
	private String billedLTD;
	private String cardReceiptVerification;
	private List<Cardholder> cardholder;
	private String cashApr;
	private String dateEmailAddrAdded;
	private String dateLastCardReq;
	private String enhancementFlag;
	private String homePhoneMaintainedDate;
	private String lastPaymentAmt;
	private String lastPaymentDate;
	private String minPaymentDueAmt;
	private String origStateProvince;
	private String outstandingCardRequest;
	private String outstandingMinPmtDue;
	private String paymentDueDate;
	private String purchaseApr;
	private String tokenCounter;
	
}
