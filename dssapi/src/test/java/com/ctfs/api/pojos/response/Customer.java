package com.ctfs.api.pojos.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Customer {
	
	 @JsonProperty("custType")
	 private String custType;
	 @JsonProperty("acctId")
	 private String acctId;
	 @JsonProperty("custId")
	 private String custId;
	 @JsonProperty("prefix")
	 private String prefix;
	 @JsonProperty("suffix")
	 private String suffix;
	 @JsonProperty("firstName")
	 private String firstName;
	 @JsonProperty("lastName")
	 private String lastName;
	 @JsonProperty("middleName")
	 private String middleName;
	 @JsonProperty("nameType")	 
	 private String nameType;
	 @JsonProperty("embossingName")
	 private String embossingName;
	 @JsonProperty("wholeName")
	 private String wholeName;
	 @JsonProperty("lastMaintained")
	 private Date lastMaintained;
	 @JsonProperty("languageCode")
	 private String languageCode;
	 @JsonProperty("dateOfBirth")
	 private Date dateOfBirth;
	 @JsonProperty("phoneNumbers")
	 private List < PhoneNumbers > phoneNumbers ;
	 @JsonProperty("phones")
	 private List <Phone> phones ;
	 @JsonProperty("emailAddress")
	 private String emailAddress;
	 @JsonProperty("emailLastMaintained")
	 private Date emailLastMaintained;
	 @JsonProperty("dateCustOpen")
	 private String dateCustOpen;
	 @JsonProperty("monthlyIncome")
	 private BigDecimal  monthlyIncome;
	 @JsonProperty("incomeLastMaintained")
	 private Date incomeLastMaintained;
	 @JsonProperty("employeeInfo")
	 private EmployeeInfo employeeInfo;
	 @JsonProperty("address")
	 private Address address;
	 @JsonProperty("lastAddressMaintained")
	 private Date lastAddressMaintained;
	 @JsonProperty("dateAddressAdded")
	 private Date dateAddressAdded;
	 @JsonProperty("cardNbr")
	 private String cardNbr;
	 @JsonProperty("homeType")
	 private String homeType;
	 @JsonProperty("correspondenceViaEmail")
	 private String correspondenceViaEmail;
	 @JsonProperty("acctRelStatus")
	 private String acctRelStatus ;
	 @JsonProperty("transferFromAcctNum")
	 private String transferFromAcctNum ;
	 @JsonProperty("transferToAcctNum")
	 private String transferToAcctNum ;
	 @JsonProperty("dateTransfer")
	 private String dateTransfer;
	 @JsonProperty("addressAvailable")
	 private boolean addressAvailable;
	 @JsonProperty("appSuffix")
	 private String appSuffix ;
	 @JsonProperty("monthlyRentMortgage")
	 private BigDecimal monthlyRentMortgage;
	 
	 
}
