package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Component
@Data
public class CustomerList {
	    private ArrayList<CustomerProductsAlternateAccountId> alternateAccountIds;
	    private ArrayList<CustomerProductsInterestRate> interestRates;
	    private String routingNumber;
	    private String accountWithBankSortCode;
	    private String accountOpenOtherReason;
	    private String accountOpenReasonCode;
	    private String numericCurrencyCode;
	    private String planType;
	    private String accountTitle1;
	    private String accountTitle2;
	    private String productLine;
	    private String thirdPartyUse;
	    private String postingRestriction;
	    private String lastCreditDate;
	    private String lastDebitDate;
	    private String allowBillPayment;
	    private String allowDeposit;
	    private String allowWithdrawal;
	    private String isInternetBankingService;
	    private String allowInterMemberTransfer;
	    private String allowMeToMeTransfer;
	    private String otherParty;
	    private String productCategoryId;
	    private String numberOfSignatory;
	    private String signatory;
	    private String cardNumber;
	    private String companyCode;
	    private String productName;
	    private String accountId;
	    private String displayName;
	    private String currencyId;
	    private String lockedAmount;
	    private String workingBalance;
	    private String onlineActualBalance;
	    private String availableBalance;
	    private String sortCode;
	    private String customerId;
	    private String customerName;
	    private String availableLimit;
	    @JsonProperty("IBAN") 
	    private String iBAN;
	    private String portfolioId;
	    private String openingDate;
	    private String limitReference;
	    private String clearedBalance;
	    private String availableFunds;
	    private String arrangementId;
	    private String productId;
	    private String productGroupId;
	    private String customer;
	    private String relationCode;
	    private String customerRole;
	    private String customerType;
	    private String roleDisplayName;
	    private String taxId;
	    private String firstName;
	    private String lastName;
	    private String dateOfBirth;
	    private String phoneNumber;
	    private String email;
	    private String postCode;
	    private String street;
	    private String townCountry;
	    private String salutation;
	    private String maritalStatus;
	    private String employmentStatus;
	    private String categoryName;
	    private String categoryId;
	    private String contactType;
	    private String iddPrefixPhone;
	    private String contactData;
	    private String accountStatus;
	    private String beneficialOwner;

}
