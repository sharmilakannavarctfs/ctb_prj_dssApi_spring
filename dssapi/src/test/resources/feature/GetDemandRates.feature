Feature: Verify post operations to get demand rates for HISA

 Scenario: Validate the response of  getDemandRates api with valid payload category as DMDS
   Given Perform post operation to hit getDemandRates DSS api using "DMDS" as category
   Then Validate getDemandRates DSS api response status code as 200
   
   
      Scenario: Validate the response of  getDemandRates api with invalid payload category as 123
    Given Perform post operation to hit getDemandRates DSS api using "123" as category
    Then Validate getDemandRates DSS api response status code as 501
    And Validate "description" value in getDemandRates DSS api response should be "Service Unavilable" 
    
    Scenario: Validate the response of  getDemandRates api without payload
	Given Perform post operation to hit getDemandRates DSS api without payload
	Then Validate getDemandRates DSS api response status code as 200
	#And Validate values in getDemandRates DSS api response should be null
    
   Scenario: Validate the response of  getDemandRates api with invalid payload name categoryABC
    Given Perform post operation to hit getDemandRates DSS api using "DMDS" as categoryABC
    Then Validate getDemandRates DSS api response status code as 200
   # And Validate "description" value in getDemandRates DSS api response should be "Service Unavilable" 
    
 Scenario: Validate the response of getDemandRates api with expired token
	 Given Perform post operation to hit getDemandRates DSS api using "DMDS" as category
	 Then Validate getDemandRates DSS api response status code as 401
	 #And Validate "description" value in getDemandRates DSS api response should be "token expired"

Scenario: Validate the response of getLinkedAccounts api with incorrect token with correct user
	 Given Perform post operation to hit getLinkedAccounts DSS api using "DMDS" as category
		Then Validate getLinkedAccounts DSS api response status code as 403
	 #And Validate "description" value in getLinkedAccounts DSS api response should be "token expired"