Feature: Verify post operations to get Inter Member Transfer Accounts

 Scenario Outline: Validate the response of  getInterMemberTransferAccounts api with valid payload customerNumber as 100215
   Given Perform post operation to hit getInterMemberTransferAccounts DSS api using "100215" as customerNumber
   Then Validate getInterMemberTransferAccounts DSS api response status code as 200
   And Validate getInterMemberTransferAccounts DSS api response should contain "<fields>"
   And Validate getInterMemberTransferAccounts DSS api response
    
    Examples:
	|fields|
	|customerId,dateTime,accountName,nickName,beneficiaryAccountId,bankName,versionNumber,companyId,beneficiaryCustomerId,ownAccount,bankSortCode,accountTitle2,accountTitle1,customerId,shortName,beneficiaryId|
   
   
   Scenario: Validate the response of  getInterMemberTransferAccounts api with invalid payload customerNumber as 123
    Given Perform post operation to hit getInterMemberTransferAccounts DSS api using "123" as customerNumber
    Then Validate getInterMemberTransferAccounts DSS api response status code as 501
    And Validate "description" value in getInterMemberTransferAccounts DSS api response should be "Service Unavilable"
    
    
 Scenario: Validate the response of  getInterMemberTransferAccounts api without payload
	Given Perform post operation to hit getInterMemberTransferAccounts DSS api without payload
	Then Validate getInterMemberTransferAccounts DSS api response status code as 200
	#And Validate values in getInterMemberTransferAccounts DSS api response should be null
	
  Scenario: Validate the response of  getInterMemberTransferAccounts api with invalid payload name customerABC
    Given Perform post operation to hit getInterMemberTransferAccounts DSS api using "100215" as customerABC
    Then Validate getInterMemberTransferAccounts DSS api response status code as 200
   # And Validate "description" value in getInterMemberTransferAccounts DSS api response should be "Service Unavilable" 
 
  Scenario: Validate the response of getInterMemberTransferAccounts api with expired token
	 Given Perform post operation to hit getInterMemberTransferAccounts DSS api using "100215" as customerNumber
	 Then Validate getInterMemberTransferAccounts DSS api response status code as 401
	 #And Validate "description" value in getInterMemberTransferAccounts DSS api response should be "token expired"

Scenario: Validate the response of getInterMemberTransferAccounts api with incorrect token with correct user
	 Given Perform post operation to hit getInterMemberTransferAccounts DSS api using "100215" as customerNumber
		Then Validate getInterMemberTransferAccounts DSS api response status code as 403
	 #And Validate "description" value in getInterMemberTransferAccounts DSS api response should be "token expired"  
   
   
   
   