@DSS-17xxx
Feature: getDemandInterestRate DSS api
 @test11
  Scenario: getDemandInterestRate api with valid payload rateId
    Given Perform post operation to hit getDemandInterestRate DSS api using request payload
      | rateId      |
      | 20CAD20240111 |
    Then Validate getDemandInterestRate DSS api response status code as "200"
    And Validate getDemandInterestRate DSS api response should match with schema
    And Validate getDemandInterestRate DSS api response

 
 Scenario: getDemandInterestRate api without payload
	  Given Perform post operation to hit getDemandInterestRate DSS api without payload
    Then Validate getDemandInterestRate DSS api response status code as "400"
    And Validate getDemandInterestRate DSS api error response should match with schema
    And Validate "description" value in getDemandInterestRate DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getDemandInterestRate DSS api response should be "40001"
	
Scenario: getDemandInterestRate api with invalid attribute name
    Given Perform post operation to hit getDemandInterestRate DSS api using "20CAD20240111" as "rateTTD"
    Then Validate getDemandInterestRate DSS api response status code as "400"
    And Validate getDemandInterestRate DSS api error response should match with schema
    And Validate "description" value in getDemandInterestRate DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getDemandInterestRate DSS api response should be "40001"
    
 Scenario: getDemandInterestRate DSS api without rateId value
    Given Perform post operation to hit getDemandInterestRate DSS api using "" as "rateId"
    Then Validate getDemandInterestRate DSS api response status code as "400"
    And Validate getDemandInterestRate DSS api error response should match with schema
    And Validate "description" value in getDemandInterestRate DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getDemandInterestRate DSS api response should be "40001"
    
 Scenario Outline: getDemandInterestRate DSS api with invalid rateId value
    Given Perform post operation to hit getDemandInterestRate DSS api using "<values>" as "rateId"
    Then Validate getDemandInterestRate DSS api response status code as "400"
    And Validate getDemandInterestRate DSS api error response should match with schema
    And Validate "description" value in getDemandInterestRate DSS api response should be "NO.: INVALID NUMERIC CHAR."
    And Validate "statusCode" value in getDemandInterestRate DSS api response should be "40001"

    Examples: 
      | values     |
      | abcABC     |
      | sdfAB12%^& |
      | ^&*%^&     |
	
	 Scenario Outline: getDemandInterestRate api with invalid rateId four times-Hystrix
    Given Perform post operation to hit getDemandInterestRate DSS api using "<values>" as "rateId"
    Then Validate getDemandInterestRate DSS api response status code as "<statusCode>"
    And Validate getDemandInterestRate DSS api error response should match with schema
    And Validate "description" value in getDemandInterestRate DSS api response should be "<description>"
    And Validate "statusCode" value in getDemandInterestRate DSS api response should be "<responseStatusCode>"
    Examples: 
      | values | statusCode | description     |responseStatusCode|
      | 123 |        404 | CCY. MISSING       |40401|
      | 123 |        404 | CCY. MISSING       |40401|
      | 123 |        404 | CCY. MISSING       |40401|
      | 123 |        500 | Runtime error      |50001|
	
  #Token has been not implemented by dev Team
  Scenario: getDemandInterestRate DSS api with expired Token
    Given Perform post operation to hit getDemandInterestRate DSS api using request payload with expired Token
      | rateId |
      | 20CAD20240111 |
    Then Validate getDemandInterestRate DSS api response status code as "401"
    And Validate getDemandInterestRate DSS api error response should match with schema
    And Validate "description" value in getDemandInterestRate DSS api response should be "The token has expired"
    And Validate "statusCode" value in getDemandInterestRate DSS api response should be "40001"

  Scenario: getDemandInterestRate DSS api with token does not match with Customer Number
    Given Perform post operation to hit getDemandInterestRate DSS api using request payload with token does not match with Customer Number
      | rateId |
      | 20CAD20240111 |
    Then Validate getDemandInterestRate DSS api response status code as "403"
    And Validate getDemandInterestRate DSS api error response should match with schema
    And Validate "description" value in getDemandInterestRate DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getDemandInterestRate DSS api response should be "40001"
    
    #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getDemandInterestRate DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getDemandInterestRate DSS api using request payload and disablePaginationheader as "true"
      | rateId |
      | 20CAD20240111 |
    Then Validate getDemandInterestRate DSS api response status code as "200"