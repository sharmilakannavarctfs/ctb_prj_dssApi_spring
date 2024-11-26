Feature: Validate the response of getTermRates api for valid GIC inputs

Scenario Outline: Validate the response of  getTermRates DSS api with valid payload 
	Given Perform post operation to hit getTermRates DSS api using "DEPOSITS" as "productLineId"
	Then Validate getTermRates DSS api response status code as 200
	And Validate getTermRates DSS api response should contain "<fields>"
	Then Validate getTermRates DSS api response
	
	Examples:
	|fields|
	|productLine,productLineName,productGroupId,fund,productId|
	
#negative
	
Scenario: Validate the response of getTermRates DSS api with invalid payload productLineId as ABC
	Given Perform post operation to hit getTermRates DSS api using "ABC" as "productLineId"
	Then Validate getTermRates DSS api response status code as 200
	And Validate "description" value in getTermRates DSS api response should be "No records were found that matched the selection criteria"
	
Scenario: Validate the response of getTermRates DSS api invalid field Name as productLine instead of productLineId 
	Given Perform post operation to hit getTermRates DSS api using "DEPOSITS" as "productLine"
	Then Validate getTermRates DSS api response status code as 400
	And Validate "description" value in getTermRates DSS api response should be "Missing key Attribute"
	
Scenario: Validate the response of getTermRates DSS api without payload
	Given Perform post operation to hit getTermRates DSS api without payload
	Then Validate getTermRates DSS api response status code as 400
	And Validate "description" value in getTermRates DSS api response should be "Missing key Attribute"
	
Scenario: Validate the response of getTermRates DSS api with expired Token
	Given Perform post operation to hit getTermRates DSS api using "DEPOSITS" as "productLineId" with expired Token
	Then Validate getTermRates DSS api response status code as 401
	And Validate "description" value in getTermRates DSS api response should be "The token has expired"
	
Scenario: Validate the response of getTermRates DSS api with token does not match with Customer Number
	Given Perform post operation to hit getTermRates DSS api using "DEPOSITS" as "productLineId" with token does not match with Customer Number
	Then Validate getTermRates DSS api response status code as 403
	And Validate "description" value in getTermRates DSS api response should be "The token does not match the customer number and access is denied"
	
	