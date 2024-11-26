package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
public class LinkedAccount {
    private Object cardId;
    private String customerId;
    private String accountTitle1;
    private String accountTitle2;
    private String accountName;
    private Object ownAccount;
    private String companyId;
    private String beneficiaryId;
    private String nickName;
    private Object categoryId;
    private String beneficiaryAccountId;
    private String beneficiaryCustomerId;
    private String transactionType;
    private String bankSortCode;
    @JsonProperty("BIC") 
    private Object bIC;
    @JsonProperty("IBAN") 
    private Object iBAN;
    private Object linkBeneficiaryId;
    private Object customerReference;
    private Object accountWithBank;
    private Object paymentCurrencyId;
    private Object preferredPaymentAmount;
    private Object ourCharges;
    private Object paymentCountry;
    private String transactionName;
    private Object beneficiaryNickName;
    private Object preferredPaymentProduct;
    private Object defaultNarrative;
    private String dateTime;
    private String shortName;
    private String bankName;
    private Object beneficiaryAddress;
    private Object beneficiaryAddressCity;
    private Object postCode;
    private Object beneficiaryCountryName;
    private String versionNumber;
    private Object departmentId;
    private Object subDepartmentId;
    private Object buildingNumber;
    private Object buildingName;
    private Object floorNumber;
    private Object postBoxNumber;
    private Object apartmentNumber;
    private Object town;
    private Object districtName;
    private Object state;

}
