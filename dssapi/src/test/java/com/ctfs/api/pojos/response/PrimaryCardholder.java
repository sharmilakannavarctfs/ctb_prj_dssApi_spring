package com.ctfs.api.pojos.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class PrimaryCardholder {
	
	
	private Long id;
	private String name;
	private String cardNbr;
	private String accountRelationship;
	private String dateOfBirth; 
	private String sin; 
	private String verificationId; 
	private String cardActivated; 
	private String transferToCardNbr;
	private String transferFromCardNbr;
	private String emailAddress; 
	private String cardStatus;
	private String embossedName;
	private String preferredLanguage; 
	private String homePhone; 
	private String workPhone; 
	private String altPhone1; 
	private String altPhone1ConsentInd;
	private String altPhone2ConsentInd;
	private String homePhoneConsentInd;
	private String workPhoneConsentInd;
	private Long altPhone2;
	private String occupation; 
	private Address address;
	private String homePhoneInd;
	private String workPhoneInd;
	private String altPhone1Ind;
	private String altPhone2Ind;
	private String prefix;
	private String suffix;
	private MarketingOption customerMarketingOption;
	private String autoDialFlag;
	private Integer tokenCounter;
	private String fisrtName;
	private String lastName;	
	private String middleName;
	private String regionCode;
	private Date dateEmailaddMaint;
	private Date dateEmailAdded;
	private Date dateLastRequest;
	private String lastRequestReason;
	private Date homePhoneDateMaintained;
	private Date workPhoneDateMaintained;
	private Date altPhone1DateMaintained;
	private Date altPhone2DateMaintained;
	
	private String personalHomeType;
	private String homeTypeCode;
	private BigDecimal mortgageAmount;
	private String mandatoryInfoMissing;

}
