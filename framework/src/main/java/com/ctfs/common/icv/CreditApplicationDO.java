package com.ctfs.common.icv;

import java.io.Serializable;

//@Component
public class CreditApplicationDO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String processed;
	private String adjudication;
	
//	AQUISITION INFORMATION
	private String ctfsAppID;
	private String acqStart;
	private String refID;
	private String aceAppID;
	private String hostessCompany;
	private String acqStrat;
	private String reqCardType;
	private String campaign;
	private String affliationCode;
	private String businessStoreNo;
	private String acqCmplt;
	private String storeNumber;
	private String deviceNumber;
	private String authUserID;
	private String promoCode;
	private String approvedCardType;
	private String guiVer;
	private String acquirerCode;
	private String estatmentConsent;
	private String elapsedTime;
	private String channel;
	private String hostess;
	private String tabSerialID;
	
	/******Identification Information Table*****/
	private String typeOfIdentification;
	private String IDNumber;
	private String DLSwiped;
	private String loyaltyCardNum;
	private String placeOfIssue;
	private String expiryDate;
	
	/****Customer Information Table*****/
	private String custName;
	private String emailAddr;
	private String emailMktgConsentString;
	private String mothersMaidenName;
	private String applicantGender;
	private String MACAddr;
	private String correspLang;
	private String tcpIPAddr;
	private String sin;
	private String dob;
	
	
	/*****Current Address as Entered****/
	private String residentType;
	private String street;
	private String city;
	private String Prov;
	private String phone;
	private String mthlyRent;
	private String country;
	private String cell;
	private String appt;
	private String postalCode;
	private String ncodeCode;
	private String duration;
	private String fpsTreatmentScore;
	private String appState;
	
	/*****Employment****/
	private String occupation;
	private String income;
	private String householdIncome;
	private String type;
	private String employer;
	private String empStreet;
	private String empCity;
	private String empCountry;
	private String occupationDesc;
	private String incomeFreq;
	private String empPhone;
	private String empDuration;
	private String empAppt;
	private String empProv;
	private String empPostalCode;
	private String bankLoan;
	private String chqAcct;
	private String storeCard;
	private String bankName;
	private String SavingsAcct;
	private String VisaMCAmex;
	private String oilGasCard;
	private String otherCard;
	
	
	/*****Supplementary Cards*****/
	private String suppCard1Name;
	private String Relationship;
	private String suppDOB;
	private String suppOccupation;
	private String addSameAsPrimary;
	private String suppStreet;
	private String suppAppt;
	private String suppCity;
	private String suppProvince;
	private String suppCountry;
	private String suppPostalCode;
	private String suppPhone;
	
	
	/******Electronic ID Verification******/
	private String transactionID;
	private String issueDt;
	private String issuingCountry;
	private String appStatusAtScan;
	private String firstNamefromDVS;
	private String lastNamefromDVS;
	private String middleNamefromDVS;
	private String streetAddfromDVS;
	private String cityfromDVS;
	private String provincefromDVS;
	private String dobfromDVS;
	private String postCodefromDVS;
	private String scanResult;
	private String driverLicPhotoFlag;
	private String suspectedFlag;
	private String rejectedFlag;
	private String documentType;
	private String documentNum;
	private String issuingAuthority;
	private String expiryDateID;
	private String source;
	private String SKConsent;
	private String SKConsentDate;
	private String eKYCDecisionResult;
	private String fnameMatchScore;
	private String lnameMatchScore;
	private String dobMatchScore;
	private String postcodeMatchScore;
	
	//ID Verification
	
		public String getTransactionID() {
			return transactionID;
		}
		public void setTransactionID(String transactionID) {
			this.transactionID = transactionID;
		}
		public String getIssueDt() {
			return issueDt;
		}
		public void setIssueDt(String issueDt) {
			this.issueDt = issueDt;
		}
		public String getIssuingCountry() {
			return issuingCountry;
		}
		public void setIssuingCountry(String issuingCountry) {
			this.issuingCountry = issuingCountry;
		}
		public String getAppStatusAtScan() {
			return appStatusAtScan;
		}
		public void setAppStatusAtScan(String appStatusAtScan) {
			this.appStatusAtScan = appStatusAtScan;
		}
		public String getFirstNamefromDVS() {
			return firstNamefromDVS;
		}
		public void setFirstNamefromDVS(String firstNamefromDVS) {
			this.firstNamefromDVS = firstNamefromDVS;
		}
		public String getLastNamefromDVS() {
			return lastNamefromDVS;
		}
		public void setLastNamefromDVS(String lastNamefromDVS) {
			this.lastNamefromDVS = lastNamefromDVS;
		}
		public String getMiddleNamefromDVS() {
			return middleNamefromDVS;
		}
		public void setMiddleNamefromDVS(String middleNamefromDVS) {
			this.middleNamefromDVS = middleNamefromDVS;
		}
		public String getStreetAddfromDVS() {
			return streetAddfromDVS;
		}
		public void setStreetAddfromDVS(String streetAddfromDVS) {
			this.streetAddfromDVS = streetAddfromDVS;
		}
		public String getCityfromDVS() {
			return cityfromDVS;
		}
		public void setCityfromDVS(String cityfromDVS) {
			this.cityfromDVS = cityfromDVS;
		}
		public String getProvincefromDVS() {
			return provincefromDVS;
		}
		public void setProvincefromDVS(String provincefromDVS) {
			this.provincefromDVS = provincefromDVS;
		}
		public String getDobfromDVS() {
			return dobfromDVS;
		}
		public void setDobfromDVS(String dobfromDVS) {
			this.dobfromDVS = dobfromDVS;
		}
		public String getpostCodefromDVS() {
			return postCodefromDVS;
		}
		public void setpostCodefromDVS(String postCodefromDVS) {
			this.postCodefromDVS = postCodefromDVS;
		}
		public String getscanResult() {
			return scanResult;
		}
		public void setscanResult(String scanResult) {
			this.scanResult = scanResult;
		}
		public String getDriverLicPhotoFlag() {
			return driverLicPhotoFlag;
		}
		public void setDriverLicPhotoFlag(String driverLicPhotoFlag) {
			this.driverLicPhotoFlag = driverLicPhotoFlag;
		}
		public String getSuspectedFlag() {
			return suspectedFlag;
		}
		public void setSuspectedFlag(String suspectedFlag) {
			this.suspectedFlag = suspectedFlag;
		}
		public String getdocumentType() {
			return documentType;
		}
		public void setDocumentType(String documentType) {
			this.documentType = documentType;
		}
		public String getDocumentNum() {
			return documentNum;
		}
		public void setDocumentNum(String documentNum) {
			this.documentNum = documentNum;
		}
		public String getRejectedFlag() {
			return rejectedFlag;
		}
		public void setrejectedFlag(String rejectedFlag) {
			this.rejectedFlag = rejectedFlag;
		}
		public String getIssuingAuthority() {
			return issuingAuthority;
		}
		public void setIssuingAuthority(String issuingAuthority) {
			this.issuingAuthority = issuingAuthority;
		}
		public String getExpiryDateID() {
			return expiryDateID;
		}
		public void setexpiryDateID(String expiryDateID) {
			this.expiryDateID = expiryDateID;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getSKConsent() {
			return SKConsent;
		}
		public void setSKConsent(String SKConsent) {
			this.SKConsent = SKConsent;
		}
		public String getSKConsentDate() {
			return SKConsentDate;
		}
		public void setSKConsentDate(String SKConsentDate) {
			this.SKConsentDate = SKConsentDate;
		}
		public String geteKYCDecisionResult() {
			return eKYCDecisionResult;
		}
		public void seteKYCDecisionResult(String eKYCDecisionResult) {
			this.eKYCDecisionResult = eKYCDecisionResult;
		}
		public String getFnameMatchScore() {
			return fnameMatchScore;
		}
		public void setFnameMatchScore(String fnameMatchScore) {
			this.fnameMatchScore = fnameMatchScore;
		}
		public String getLnameMatchScore() {
			return lnameMatchScore;
		}
		public void setLnameMatchScore(String lnameMatchScore) {
			this.lnameMatchScore = lnameMatchScore;
		}
		public String getDobMatchScore() {
			return dobMatchScore;
		}
		public void setDobMatchScore(String dobMatchScore) {
			this.dobMatchScore = dobMatchScore;
		}
		public String getPostcodeMatchScore() {
			return postcodeMatchScore;
		}
		public void setPostcodeMatchScore(String postcodeMatchScore) {
			this.postcodeMatchScore = postcodeMatchScore;
		}
		
	/**PROCESSING DETAILS**/
	private String ProcessingStatus;
public String getProcessingStatus() {
		return ProcessingStatus;
	}
	public void setProcessingStatus(String ProcessingStatus) {
		this.ProcessingStatus = ProcessingStatus;
	}
	
	private String ProcessingStatus2;
	public String getProcessingStatus2() {
		return ProcessingStatus2;
	}
	public void setProcessingStatus2(String ProcessingStatus2) {
		this.ProcessingStatus2 = ProcessingStatus2;
	}
	
	private String ProcessingStatus3;
	public String getProcessingStatus3() {
		return ProcessingStatus3;
	}
	public void setProcessingStatus3(String ProcessingStatus3) {
		this.ProcessingStatus3 = ProcessingStatus3;
	}

	public String getSuppCard1Name() {
		return suppCard1Name;
	}
	public void setSuppCard1Name(String suppCard1Name) {
		this.suppCard1Name = suppCard1Name;
	}
	public String getRelationship() {
		return Relationship;
	}
	public void setRelationship(String relationship) {
		Relationship = relationship;
	}
	public String getSuppDOB() {
		return suppDOB;
	}
	public void setSuppDOB(String suppDOB) {
		this.suppDOB = suppDOB;
	}
	public String getSuppOccupation() {
		return suppOccupation;
	}
	public void setSuppOccupation(String suppOccupation) {
		this.suppOccupation = suppOccupation;
	}
	public String getAddSameAsPrimary() {
		return addSameAsPrimary;
	}
	public void setAddSameAsPrimary(String addSameAsPrimary) {
		this.addSameAsPrimary = addSameAsPrimary;
	}
	public String getSuppStreet() {
		return suppStreet;
	}
	public void setSuppStreet(String suppStreet) {
		this.suppStreet = suppStreet;
	}
	public String getSuppAppt() {
		return suppAppt;
	}
	public void setSuppAppt(String suppAppt) {
		this.suppAppt = suppAppt;
	}
	public String getSuppCity() {
		return suppCity;
	}
	public void setSuppCity(String suppCity) {
		this.suppCity = suppCity;
	}
	public String getSuppProvince() {
		return suppProvince;
	}
	public void setSuppProvince(String suppProvince) {
		this.suppProvince = suppProvince;
	}
	public String getSuppCountry() {
		return suppCountry;
	}
	public void setSuppCountry(String suppCountry) {
		this.suppCountry = suppCountry;
	}
	public String getSuppPostalCode() {
		return suppPostalCode;
	}
	public void setSuppPostalCode(String suppPostalCode) {
		this.suppPostalCode = suppPostalCode;
	}
	public String getSuppPhone() {
		return suppPhone;
	}
	public void setSuppPhone(String suppPhone) {
		this.suppPhone = suppPhone;
	}


	
	private String appStatus;
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getProcessed() {
		return processed;
	}
	public void setProcessed(String processed) {
		this.processed = processed;
	}
	public String getAdjudication() {
		return adjudication;
	}
	public void setAdjudication(String adjudication) {
		this.adjudication = adjudication;
	}
	public String getCtfsAppID() {
		return ctfsAppID;
	}
	public void setCtfsAppID(String ctfsAppID) {
		this.ctfsAppID = ctfsAppID;
	}
	public String getAcqStart() {
		return acqStart;
	}
	public void setAcqStart(String acqStart) {
		this.acqStart = acqStart;
	}
	public String getRefID() {
		return refID;
	}
	public void setRefID(String refID) {
		this.refID = refID;
	}
	public String getAceAppID() {
		return aceAppID;
	}
	public void setAceAppID(String aceAppID) {
		this.aceAppID = aceAppID;
	}
	public String getHostessCompany() {
		return hostessCompany;
	}
	public void setHostessCompany(String hostessCompany) {
		this.hostessCompany = hostessCompany;
	}
	public String getAcqStrat() {
		return acqStrat;
	}
	public void setAcqStrat(String acqStrat) {
		this.acqStrat = acqStrat;
	}
	public String getReqCardType() {
		return reqCardType;
	}
	public void setReqCardType(String reqCardType) {
		this.reqCardType = reqCardType;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getAffliationCode() {
		return affliationCode;
	}
	public void setAffliationCode(String affliationCode) {
		this.affliationCode = affliationCode;
	}
	public String getBusinessStoreNo() {
		return businessStoreNo;
	}
	public void setBusinessStoreNo(String businessStoreNo) {
		this.businessStoreNo = businessStoreNo;
	}
	public String getAcqCmplt() {
		return acqCmplt;
	}
	public void setAcqCmplt(String acqCmplt) {
		this.acqCmplt = acqCmplt;
	}
	public String getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getAuthUserID() {
		return authUserID;
	}
	public void setAuthUserID(String authUserID) {
		this.authUserID = authUserID;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getApprovedCardType() {
		return approvedCardType;
	}
	public void setApprovedCardType(String approvedCardType) {
		this.approvedCardType = approvedCardType;
	}
	public String getGuiVer() {
		return guiVer;
	}
	public void setGuiVer(String guiVer) {
		this.guiVer = guiVer;
	}
	public String getAcquirerCode() {
		return acquirerCode;
	}
	public void setAcquirerCode(String acquirerCode) {
		this.acquirerCode = acquirerCode;
	}
	public String getEstatmentConsent() {
		return estatmentConsent;
	}
	public void setEstatmentConsent(String estatmentConsent) {
		this.estatmentConsent = estatmentConsent;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getHostess() {
		return hostess;
	}
	public void setHostess(String hostess) {
		this.hostess = hostess;
	}
	public String getTabSerialID() {
		return tabSerialID;
	}
	public void setTabSerialID(String tabSerialID) {
		this.tabSerialID = tabSerialID;
	}
	public String getTypeOfIdentification() {
		return typeOfIdentification;
	}
	public void setTypeOfIdentification(String typeOfIdentification) {
		this.typeOfIdentification = typeOfIdentification;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public String getDLSwiped() {
		return DLSwiped;
	}
	public void setDLSwiped(String dLSwiped) {
		DLSwiped = dLSwiped;
	}
	public String getLoyaltyCardNum() {
		return loyaltyCardNum;
	}
	public void setLoyaltyCardNum(String loyaltyCardNum) {
		this.loyaltyCardNum = loyaltyCardNum;
	}
	public String getPlaceOfIssue() {
		return placeOfIssue;
	}
	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getEmailMktgConsentString() {
		return emailMktgConsentString;
	}
	public void setEmailMktgConsentString(String emailMktgConsentString) {
		this.emailMktgConsentString = emailMktgConsentString;
	}
	public String getMothersMaidenName() {
		return mothersMaidenName;
	}
	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}
	public String getApplicantGender() {
		return applicantGender;
	}
	public void setApplicantGender(String applicantGender) {
		this.applicantGender = applicantGender;
	}
	public String getMACAddr() {
		return MACAddr;
	}
	public void setMACAddr(String mACAddr) {
		MACAddr = mACAddr;
	}
	public String getCorrespLang() {
		return correspLang;
	}
	public void setCorrespLang(String correspLang) {
		this.correspLang = correspLang;
	}
	public String getTcpIPAddr() {
		return tcpIPAddr;
	}
	public void setTcpIPAddr(String tcpIPAddr) {
		this.tcpIPAddr = tcpIPAddr;
	}
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getResidentType() {
		return residentType;
	}
	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProv() {
		return Prov;
	}
	public void setProv(String prov) {
		Prov = prov;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMthlyRent() {
		return mthlyRent;
	}
	public void setMthlyRent(String mthlyRent) {
		this.mthlyRent = mthlyRent;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getAppt() {
		return appt;
	}
	public void setAppt(String appt) {
		this.appt = appt;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getNcodeCode() {
		return ncodeCode;
	}
	public void setNcodeCode(String ncodeCode) {
		this.ncodeCode = ncodeCode;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getFpsTreatmentScore() {
		return fpsTreatmentScore;
	}
	public void setFpsTreatmentScore(String fpsTreatmentScore) {
		this.fpsTreatmentScore =fpsTreatmentScore;
	}
	public String getAppState() {
		return appState;
	}
	public void setAppState(String appState) {
		this.appState =appState;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getHouseholdIncome() {
		return householdIncome;
	}
	public void setHouseholdIncome(String householdIncome) {
		this.householdIncome = householdIncome;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getEmpStreet() {
		return empStreet;
	}
	public void setEmpStreet(String empStreet) {
		this.empStreet = empStreet;
	}
	public String getEmpCity() {
		return empCity;
	}
	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}
	public String getEmpCountry() {
		return empCountry;
	}
	public void setEmpCountry(String empCountry) {
		this.empCountry = empCountry;
	}
	public String getOccupationDesc() {
		return occupationDesc;
	}
	public void setOccupationDesc(String occupationDesc) {
		this.occupationDesc = occupationDesc;
	}
	public String getIncomeFreq() {
		return incomeFreq;
	}
	public void setIncomeFreq(String incomeFreq) {
		this.incomeFreq = incomeFreq;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getEmpDuration() {
		return empDuration;
	}
	public void setEmpDuration(String empDuration) {
		this.empDuration = empDuration;
	}
	public String getEmpAppt() {
		return empAppt;
	}
	public void setEmpAppt(String empAppt) {
		this.empAppt = empAppt;
	}
	public String getEmpProv() {
		return empProv;
	}
	public void setEmpProv(String empProv) {
		this.empProv = empProv;
	}
	public String getEmpPostalCode() {
		return empPostalCode;
	}
	public void setEmpPostalCode(String empPostalCode) {
		this.empPostalCode = empPostalCode;
	}
	public String getBankLoan() {
		return bankLoan;
	}
	public void setBankLoan(String bankLoan) {
		this.bankLoan = bankLoan;
	}
	public String getChqAcct() {
		return chqAcct;
	}
	public void setChqAcct(String chqAcct) {
		this.chqAcct = chqAcct;
	}
	public String getStoreCard() {
		return storeCard;
	}
	public void setStoreCard(String storeCard) {
		this.storeCard = storeCard;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSavingsAcct() {
		return SavingsAcct;
	}
	public void setSavingsAcct(String savingsAcct) {
		SavingsAcct = savingsAcct;
	}
	public String getVisaMCAmex() {
		return VisaMCAmex;
	}
	public void setVisaMCAmex(String visaMCAmex) {
		VisaMCAmex = visaMCAmex;
	}
	public String getOilGasCard() {
		return oilGasCard;
	}
	public void setOilGasCard(String oilGasCard) {
		this.oilGasCard = oilGasCard;
	}
	public String getOtherCard() {
		return otherCard;
	}
	public void setOtherCard(String otherCard) {
		this.otherCard = otherCard;
	}
	
//	@Override
//	public String toString() {
//		return BeanFunctions.serializeBeanForDisplay(this);
//	}
	

}
