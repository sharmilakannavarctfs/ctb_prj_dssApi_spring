package com.ctfs.common.polaris;


import java.util.Date;

import com.ctfs.framework.Address;
import com.ctfs.framework.DateFormatter;

public class MemberMainDTO {
	private String loyaltyNumber;
	private String hdrCurrentValue;	
	private String hdrTotalLifeValue;
	private String hdrRelationshipStatus;
	private String hdrLinkedCreditCard;
	private String hdrRedeemer;
	private Date hdrActivateDate;
	private String hdrAccountStatus;
	private String mainOneCardFlag;
	private String mainAccountType;
	private String mainAccountStatus;
	private String mainAddressLine1;
	private String mainAddressLine2;
	private String mainCity;
	private String mainProv;
	private String mainPostalCode;
	private String mainCntry;
	private String mainReturnedMail;
	private String mainPrefixName;
	private String mainFirstName;
	private String mainMiddleInitial;
	private String mainLastName;
	private String mainGender;
	private String mainDOBMonth;
	private String mainDOBDay;
	private String mainDOBYear;
	private String mainMaintDate;
	private String mainRedeemer;
	private String mainPrimaryPhone;
	private String mainPrimaryPhoneType;
	private String mainAltPhone;
	private String mainAltPhoneType;
	private String mainEmail;
	private String mainBadEmail;
	private String mainCommPrefEmail;
	private String mainCommPrefDirectMail;
	private String mainCommPrefPhone;
	private String mainCommPrefGI;
	private String mainCommPrefLanguage;
	private String orgInfoAccountType;
	private String orgInfoOrgName;
	private String orgInfoBusinessNumber;
	private String orgInfoTypeOfOrganization;
	private String orgInfoPurposeOfOrganization;
	
/*	public void compare(EnrolDO edo) {
		CheckPoint.checkPointEquals(mainAccountType, edo.getAccountType().toString(), "mainAccountType", null);
		CheckPoint.checkPointEquals(mainPrefixName, edo.getPrefix(), "mainPrefixName", null);
		CheckPoint.checkPointEquals(mainFirstName, edo.getFirstName(), "mainFirstName", null);
		CheckPoint.checkPointEquals(mainMiddleInitial, edo.getMiddleInitial(), "mainMiddleInitial", null);
		CheckPoint.checkPointEquals(mainLastName, edo.getLastName(), "mainLastName", null);
		CheckPoint.checkPointEquals(mainAddressLine1, edo.getAddress1(), "mainAddressLine1", null);
		CheckPoint.checkPointEquals(mainAddressLine2, edo.getAddress2(), "mainAddressLine2", null);
		CheckPoint.checkPointEquals(mainCity, edo.getCity(), "mainCity", null);
		CheckPoint.checkPointEquals(mainProv, edo.getProvince(), "mainProv", null);
		CheckPoint.checkPointEquals(mainPostalCode, edo.getPostalCode(), "mainPostalCode", null);
		CheckPoint.checkPointEquals(mainCntry, edo.getCountry(), "mainCntry", null);
		CheckPoint.checkPointEquals(mainPrimaryPhone, edo.getPrimaryPhone(), "mainPrimaryPhone", null);
		CheckPoint.checkPointEquals(mainPrimaryPhoneType, edo.getPrimaryPhoneType(), "mainPrimaryPhoneType", null);
		CheckPoint.checkPointEquals(mainAltPhone, edo.getAltPhone(), "mainAltPhone", null);
		CheckPoint.checkPointEquals(mainAltPhoneType, edo.getAltPhoneType(), "mainAltPhoneType", null);
		CheckPoint.checkPointEquals(mainEmail, edo.getEmail(), "mainEmail", null);
		//CheckPoint.checkPointEquals(mainCommPrefEmail, edo.getAltPhone(), "mainCommPrefEmail", null);
		CheckPoint.checkPointEquals(mainDOBMonth, edo.getDobMonth(), "mainDOBMonth", null);
		CheckPoint.checkPointEquals(mainDOBDay, edo.getDobDay(), "mainDOBDay", null);
		CheckPoint.checkPointEquals(mainDOBYear, edo.getDobYear(), "mainDOBYear", null);
		CheckPoint.checkPointEquals(mainCommPrefLanguage, edo.getLanguage(), "mainCommPrefLanguage", null);
		CheckPoint.checkPointEquals(mainGender, edo.getGender(), "mainGender", null);
	}
	
	public void equalExceptForLoyaltyNumber(MemberMainDTO bean) {
		CheckPoint.checkPointEquals(hdrCurrentValue, bean.hdrCurrentValue, "hdrCurrentValue", null);
		CheckPoint.checkPointEquals(hdrTotalLifeValue, bean.hdrTotalLifeValue, "hdrTotalLifeValue", null);
		CheckPoint.checkPointEquals(hdrRelationshipStatus, bean.hdrRelationshipStatus, "hdrRelationshipStatus", null);
		CheckPoint.checkPointEquals(hdrLinkedCreditCard, bean.hdrLinkedCreditCard, "hdrLinkedCreditCard", null);
		CheckPoint.checkPointEquals(hdrRedeemer, bean.hdrRedeemer, "hdrRedeemer", null);
		CheckPoint.checkPointEquals(hdrAccountStatus, bean.hdrAccountStatus, "hdrAccountStatus", null);
		CheckPoint.checkPointEquals(mainOneCardFlag, bean.mainOneCardFlag, "mainOneCardFlag", null);
		CheckPoint.checkPointEquals(mainAccountType, bean.mainAccountType, "mainAccountType", null);
		CheckPoint.checkPointEquals(mainAccountStatus, bean.mainAccountStatus, "mainAccountStatus", null);
		CheckPoint.checkPointEquals(mainAddressLine1, bean.mainAddressLine1, "mainAddressLine1", null);
		CheckPoint.checkPointEquals(mainAddressLine2, bean.mainAddressLine2, "mainAddressLine2", null);
		CheckPoint.checkPointEquals(mainCity, bean.mainCity, "mainCity", null);
		CheckPoint.checkPointEquals(mainProv, bean.mainProv, "mainProv", null);
		CheckPoint.checkPointEquals(mainPostalCode, bean.mainPostalCode, "mainPostalCode", null);
		CheckPoint.checkPointEquals(mainCntry, bean.mainCntry, "mainCntry", null);
		CheckPoint.checkPointEquals(mainReturnedMail, bean.mainReturnedMail, "mainReturnedMail", null);
		CheckPoint.checkPointEquals(mainPrefixName, bean.mainPrefixName, "mainPrefixName", null);
		CheckPoint.checkPointEquals(mainFirstName, bean.mainFirstName, "mainFirstName", null);
		CheckPoint.checkPointEquals(mainMiddleInitial, bean.mainMiddleInitial, "mainMiddleInitial", null);
		CheckPoint.checkPointEquals(mainLastName, bean.mainLastName, "mainLastName", null);
		CheckPoint.checkPointEquals(mainGender, bean.mainGender, "mainGender", null);
		CheckPoint.checkPointEquals(mainDOBMonth, bean.mainDOBMonth, "mainDOBMonth", null);
		CheckPoint.checkPointEquals(mainDOBDay, bean.mainDOBDay, "mainDOBDay", null);
		CheckPoint.checkPointEquals(mainDOBYear, bean.mainDOBYear, "mainDOBYear", null);
		//CheckPoint.checkPointEquals(mainMaintDate, bean.mainMaintDate, "mainMaintDate", null);
		CheckPoint.checkPointEquals(mainRedeemer, bean.mainRedeemer, "mainRedeemer", null);
		CheckPoint.checkPointEquals(mainPrimaryPhone, bean.mainPrimaryPhone, "mainPrimaryPhone", null);
		CheckPoint.checkPointEquals(mainPrimaryPhoneType, bean.mainPrimaryPhoneType, "mainPrimaryPhoneType", null);
		CheckPoint.checkPointEquals(mainAltPhone, bean.mainAltPhone, "mainAltPhone", null);
		CheckPoint.checkPointEquals(mainAltPhoneType, bean.mainAltPhoneType, "mainAltPhoneType", null);
		CheckPoint.checkPointEquals(mainEmail, bean.mainEmail, "mainEmail", null);
		CheckPoint.checkPointEquals(mainBadEmail, bean.mainBadEmail, "mainBadEmail", null);
		CheckPoint.checkPointEquals(mainCommPrefEmail, bean.mainCommPrefEmail, "mainCommPrefEmail", null);
		CheckPoint.checkPointEquals(mainCommPrefDirectMail, bean.mainCommPrefDirectMail, "mainCommPrefDirectMail", null);
		CheckPoint.checkPointEquals(mainCommPrefPhone, bean.mainCommPrefPhone, "mainCommPrefPhone", null);
		CheckPoint.checkPointEquals(mainCommPrefGI, bean.mainCommPrefGI, "mainCommPrefGI", null);
		CheckPoint.checkPointEquals(mainCommPrefLanguage, bean.mainCommPrefLanguage, "mainCommPrefLanguage", null);
		CheckPoint.checkPointEquals(orgInfoAccountType, bean.orgInfoAccountType, "orgInfoAccountType", null);
		CheckPoint.checkPointEquals(orgInfoOrgName, bean.orgInfoOrgName, "orgInfoOrgName", null);
		CheckPoint.checkPointEquals(orgInfoBusinessNumber, bean.orgInfoBusinessNumber, "orgInfoBusinessNumber", null);
		CheckPoint.checkPointEquals(orgInfoTypeOfOrganization, bean.orgInfoTypeOfOrganization, "orgInfoTypeOfOrganization", null);
		CheckPoint.checkPointEquals(orgInfoPurposeOfOrganization, bean.orgInfoPurposeOfOrganization, "orgInfoPurposeOfOrganization", null);
	}
	
	public void equalAfterMerge(MemberMainDTO bean) {
		CheckPoint.checkPointEquals(hdrCurrentValue, bean.hdrCurrentValue, "hdrCurrentValue", null);
		CheckPoint.checkPointEquals(hdrTotalLifeValue, bean.hdrTotalLifeValue, "hdrTotalLifeValue", null);
		CheckPoint.checkPointEquals(hdrRelationshipStatus, bean.hdrRelationshipStatus, "hdrRelationshipStatus", null);
		CheckPoint.checkPointEquals(hdrLinkedCreditCard, bean.hdrLinkedCreditCard, "hdrLinkedCreditCard", null);
		CheckPoint.checkPointEquals(hdrRedeemer, bean.hdrRedeemer, "hdrRedeemer", null);
		CheckPoint.checkPointEquals(hdrAccountStatus, bean.hdrAccountStatus, "hdrAccountStatus", null);
		CheckPoint.checkPointEquals(mainOneCardFlag, bean.mainOneCardFlag, "mainOneCardFlag", null);
		CheckPoint.checkPointEquals(mainAccountType, bean.mainAccountType, "mainAccountType", null);
		CheckPoint.checkPointEquals(mainAccountStatus, bean.mainAccountStatus, "mainAccountStatus", null);
		CheckPoint.checkPointEquals(mainAddressLine1, bean.mainAddressLine1, "mainAddressLine1", null);
		CheckPoint.checkPointEquals(mainAddressLine2, bean.mainAddressLine2, "mainAddressLine2", null);
		CheckPoint.checkPointEquals(mainCity, bean.mainCity, "mainCity", null);
		CheckPoint.checkPointEquals(mainProv, bean.mainProv, "mainProv", null);
		CheckPoint.checkPointEquals(mainPostalCode, bean.mainPostalCode, "mainPostalCode", null);
		CheckPoint.checkPointEquals(mainCntry, bean.mainCntry, "mainCntry", null);
		CheckPoint.checkPointEquals(mainReturnedMail, bean.mainReturnedMail, "mainReturnedMail", null);
		//CheckPoint.checkPointEquals(mainPrefixName, bean.mainPrefixName, "mainPrefixName", null);
		CheckPoint.checkPointEquals(mainFirstName, bean.mainFirstName, "mainFirstName", null);
		CheckPoint.checkPointEquals(mainMiddleInitial, bean.mainMiddleInitial, "mainMiddleInitial", null);
		CheckPoint.checkPointEquals(mainLastName, bean.mainLastName, "mainLastName", null);
		CheckPoint.checkPointEquals(mainGender, bean.mainGender, "mainGender", null);
		CheckPoint.checkPointEquals(mainDOBMonth, bean.mainDOBMonth, "mainDOBMonth", null);
		CheckPoint.checkPointEquals(mainDOBDay, bean.mainDOBDay, "mainDOBDay", null);
		CheckPoint.checkPointEquals(mainDOBYear, bean.mainDOBYear, "mainDOBYear", null);
		//CheckPoint.checkPointEquals(mainMaintDate, bean.mainMaintDate, "mainMaintDate", null);
		CheckPoint.checkPointEquals(mainRedeemer, bean.mainRedeemer, "mainRedeemer", null);
		CheckPoint.checkPointEquals(mainPrimaryPhone, bean.mainPrimaryPhone, "mainPrimaryPhone", null);
		CheckPoint.checkPointEquals(mainPrimaryPhoneType, bean.mainPrimaryPhoneType, "mainPrimaryPhoneType", null);
		CheckPoint.checkPointEquals(mainAltPhone, bean.mainAltPhone, "mainAltPhone", null);
		CheckPoint.checkPointEquals(mainAltPhoneType, bean.mainAltPhoneType, "mainAltPhoneType", null);
		CheckPoint.checkPointEquals(mainEmail, bean.mainEmail, "mainEmail", null);
		CheckPoint.checkPointEquals(mainBadEmail, bean.mainBadEmail, "mainBadEmail", null);
		//CheckPoint.checkPointEquals(mainCommPrefEmail, bean.mainCommPrefEmail, "mainCommPrefEmail", null);
		//CheckPoint.checkPointEquals(mainCommPrefDirectMail, bean.mainCommPrefDirectMail, "mainCommPrefDirectMail", null);
		//CheckPoint.checkPointEquals(mainCommPrefPhone, bean.mainCommPrefPhone, "mainCommPrefPhone", null);
		//CheckPoint.checkPointEquals(mainCommPrefGI, bean.mainCommPrefGI, "mainCommPrefGI", null);
		CheckPoint.checkPointEquals(mainCommPrefLanguage, bean.mainCommPrefLanguage, "mainCommPrefLanguage", null);
		CheckPoint.checkPointEquals(orgInfoAccountType, bean.orgInfoAccountType, "orgInfoAccountType", null);
		CheckPoint.checkPointEquals(orgInfoOrgName, bean.orgInfoOrgName, "orgInfoOrgName", null);
		CheckPoint.checkPointEquals(orgInfoBusinessNumber, bean.orgInfoBusinessNumber, "orgInfoBusinessNumber", null);
		CheckPoint.checkPointEquals(orgInfoTypeOfOrganization, bean.orgInfoTypeOfOrganization, "orgInfoTypeOfOrganization", null);
		CheckPoint.checkPointEquals(orgInfoPurposeOfOrganization, bean.orgInfoPurposeOfOrganization, "orgInfoPurposeOfOrganization", null);
	} */
	
	public String getLoyaltyNumber() {
		return loyaltyNumber;
	}
	public void setLoyaltyNumber(String loyaltyNumber) {
		this.loyaltyNumber = loyaltyNumber;
	}
	public String getHdrRelationshipStatus() {
		return hdrRelationshipStatus;
	}
	public void setHdrRelationshipStatus(String hdrRelationshipStatus) {
		this.hdrRelationshipStatus = hdrRelationshipStatus;
	}

	public String getHdrCurrentValue() {
		return hdrCurrentValue;
	}

	public void setHdrCurrentValue(String hdrCurrentValue) {
		this.hdrCurrentValue = hdrCurrentValue;
	}

	public String getHdrTotalLifeValue() {
		return hdrTotalLifeValue;
	}

	public void setHdrTotalLifeValue(String hdrTotalLifeValue) {
		this.hdrTotalLifeValue = hdrTotalLifeValue;
	}
	
	public String getHdrAccountStatus() {
		return hdrAccountStatus;
	}
	public void setHdrAccountStatus(String hdrAccountStatus) {
		this.hdrAccountStatus = hdrAccountStatus;
	}
	public String getMainOneCardFlag() {
		return mainOneCardFlag;
	}
	public void setMainOneCardFlag(String mainOneCardFlag) {
		this.mainOneCardFlag = mainOneCardFlag;
	}
	public String getMainAccountType() {
		return mainAccountType;
	}
	public void setMainAccountType(String mainAccountType) {
		this.mainAccountType = mainAccountType;
	}
	public String getMainAddressLine1() {
		return mainAddressLine1;
	}
	public void setMainAddressLine1(String mainAddressLine1) {
		this.mainAddressLine1 = mainAddressLine1;
	}
	public String getMainAddressLine2() {
		return mainAddressLine2;
	}
	public void setMainAddressLine2(String mainAddressLine2) {
		this.mainAddressLine2 = mainAddressLine2;
	}
	public String getMainCity() {
		return mainCity;
	}
	public void setMainCity(String mainCity) {
		this.mainCity = mainCity;
	}
	public String getMainProv() {
		return mainProv;
	}
	public void setMainProv(String mainProv) {
		this.mainProv = mainProv;
	}
	public String getMainPostalCode() {
		return mainPostalCode;
	}
	public void setMainPostalCode(String mainPostalCode) {
		this.mainPostalCode = mainPostalCode;
	}
	public String getMainCntry() {
		return mainCntry;
	}
	public void setMainCntry(String mainCntry) {
		this.mainCntry = mainCntry;
	}
	public String getMainReturnedMail() {
		return mainReturnedMail;
	}
	public void setMainReturnedMail(String mainReturnedMail) {
		this.mainReturnedMail = mainReturnedMail;
	}
	public String getMainPrefixName() {
		return mainPrefixName;
	}
	public void setMainPrefixName(String mainPrefixName) {
		this.mainPrefixName = mainPrefixName;
	}
	public String getMainFirstName() {
		return mainFirstName;
	}
	public void setMainFirstName(String mainFirstName) {
		this.mainFirstName = mainFirstName;
	}
	public String getMainMiddleInitial() {
		return mainMiddleInitial;
	}
	public void setMainMiddleInitial(String mainMiddleInitial) {
		this.mainMiddleInitial = mainMiddleInitial;
	}
	public String getMainLastName() {
		return mainLastName;
	}
	public void setMainLastName(String mainLastName) {
		this.mainLastName = mainLastName;
	}
	public String getMainGender() {
		return mainGender;
	}
	public void setMainGender(String mainGender) {
		this.mainGender = mainGender;
	}
	public String getMainDOBMonth() {
		return mainDOBMonth;
	}
	public void setMainDOBMonth(String mainDOBMonth) {
		this.mainDOBMonth = mainDOBMonth;
	}
	public String getMainDOBDay() {
		return mainDOBDay;
	}
	public void setMainDOBDay(String mainDOBDay) {
		this.mainDOBDay = mainDOBDay;
	}
	public String getMainDOBYear() {
		return mainDOBYear;
	}
	public void setMainDOBYear(String mainDOBYear) {
		this.mainDOBYear = mainDOBYear;
	}
	public String getMainMaintDate() {
		return mainMaintDate;
	}
	public void setMainMaintDate(String mainMaintDate) {
		this.mainMaintDate = mainMaintDate;
	}
	public String getMainRedeemer() {
		return mainRedeemer;
	}
	public void setMainRedeemer(String mainRedeemer) {
		this.mainRedeemer = mainRedeemer;
	}
	public String getMainPrimaryPhone() {
		return mainPrimaryPhone;
	}
	public void setMainPrimaryPhone(String mainPrimaryPhone) {
		this.mainPrimaryPhone = mainPrimaryPhone;
	}
	public String getMainPrimaryPhoneType() {
		return mainPrimaryPhoneType;
	}
	public void setMainPrimaryPhoneType(String mainPrimaryPhoneType) {
		this.mainPrimaryPhoneType = mainPrimaryPhoneType;
	}
	public String getMainAltPhone() {
		return mainAltPhone;
	}
	public void setMainAltPhone(String mainAltPhone) {
		this.mainAltPhone = mainAltPhone;
	}
	public String getMainAltPhoneType() {
		return mainAltPhoneType;
	}
	public void setMainAltPhoneType(String mainAltPhoneType) {
		this.mainAltPhoneType = mainAltPhoneType;
	}
	public String getMainEmail() {
		return mainEmail;
	}
	public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}
	public String getMainBadEmail() {
		return mainBadEmail;
	}
	public void setMainBadEmail(String mainBadEmail) {
		this.mainBadEmail = mainBadEmail;
	}
	public String getMainCommPrefEmail() {
		return mainCommPrefEmail;
	}
	public void setMainCommPrefEmail(String mainCommPrefEmail) {
		this.mainCommPrefEmail = mainCommPrefEmail;
	}
	public String getMainCommPrefDirectMail() {
		return mainCommPrefDirectMail;
	}
	public void setMainCommPrefDirectMail(String mainCommPrefDirectMail) {
		this.mainCommPrefDirectMail = mainCommPrefDirectMail;
	}
	public String getMainCommPrefPhone() {
		return mainCommPrefPhone;
	}
	public void setMainCommPrefPhone(String mainCommPrefPhone) {
		this.mainCommPrefPhone = mainCommPrefPhone;
	}
	public String getMainCommPrefGI() {
		return mainCommPrefGI;
	}
	public void setMainCommPrefGI(String mainCommPrefGI) {
		this.mainCommPrefGI = mainCommPrefGI;
	}
	public String getMainCommPrefLanguage() {
		return mainCommPrefLanguage;
	}
	public void setMainCommPrefLanguage(String mainCommPrefLanguage) {
		this.mainCommPrefLanguage = mainCommPrefLanguage;
	}
	public String getMainAccountStatus() {
		return mainAccountStatus;
	}
	public void setMainAccountStatus(String mainAccountStatus) {
		this.mainAccountStatus = mainAccountStatus;
	}
	public String getHdrLinkedCreditCard() {
		return hdrLinkedCreditCard;
	}
	public void setHdrLinkedCreditCard(String hdrLinkedCreditCard) {
		this.hdrLinkedCreditCard = hdrLinkedCreditCard;
	}
	public String getHdrRedeemer() {
		return hdrRedeemer;
	}
	public void setHdrRedeemer(String hdrRedeemer) {
		this.hdrRedeemer = hdrRedeemer;
	}
	public String getOrgInfoAccountType() {
		return orgInfoAccountType;
	}
	public void setOrgInfoAccountType(String orgInfoAccountType) {
		this.orgInfoAccountType = orgInfoAccountType;
	}
	public String getOrgInfoOrgName() {
		return orgInfoOrgName;
	}
	public void setOrgInfoOrgName(String orgInfoOrgName) {
		this.orgInfoOrgName = orgInfoOrgName;
	}
	public String getOrgInfoBusinessNumber() {
		return orgInfoBusinessNumber;
	}
	public void setOrgInfoBusinessNumber(String orgInfoBusinessNumber) {
		this.orgInfoBusinessNumber = orgInfoBusinessNumber;
	}
	public String getOrgInfoTypeOfOrganization() {
		return orgInfoTypeOfOrganization;
	}
	public void setOrgInfoTypeOfOrganization(String orgInfoTypeOfOrganization) {
		this.orgInfoTypeOfOrganization = orgInfoTypeOfOrganization;
	}
	public String getOrgInfoPurposeOfOrganization() {
		return orgInfoPurposeOfOrganization;
	}
	public void setOrgInfoPurposeOfOrganization(String orgInfoPurposeOfOrganization) {
		this.orgInfoPurposeOfOrganization = orgInfoPurposeOfOrganization;
	}
	public Date getHdrActivateDate() {
		return hdrActivateDate;
	}
	public void setHdrActivateDate(Date hdrActivateDate) {
		this.hdrActivateDate = hdrActivateDate;
	}
	private static DateFormatter format = new DateFormatter("MM/dd/yyyy");
	public void setHdrActivateDate(String hdrActivateDateS) {
		this.hdrActivateDate = format.parse(hdrActivateDateS);
	}

	public Address getAddress() {
		Address addr = new Address();
		addr.setAddrLine1(mainAddressLine1);
		addr.setAddrLine2(mainAddressLine2);
		addr.setCity(mainCity);
		addr.setProvince(mainProv);
		addr.setPostalCode(mainPostalCode);
		//addr.setCountry(mainc);
		return addr;
	}
	
	/*public String toString() {
		return BeanFunctions.serializeBeanForDisplay(this);
	}*/
	
}
