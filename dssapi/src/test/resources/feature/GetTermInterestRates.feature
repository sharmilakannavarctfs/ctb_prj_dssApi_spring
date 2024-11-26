@DSS-17337 @DSS-17547 @DSS-17548
Feature: getTermInterestRates DSS api

  @TestRun
  Scenario: getTermInterestRates DSS api with valid periodicInterestId
    Given Perform post operation to hit getTermInterestRates DSS api using request payload
      | periodicInterestId |
      |                 10 |
    Then Validate getTermInterestRates DSS api response status code as "200"
    And Validate getTermInterestRates DSS api response should match with schema
    Then Validate getTermInterestRates DSS api response
    
   Scenario: getTermInterestRates DSS api with valid periodicInterestId and page_start
    Given Perform post operation to hit getTermInterestRates DSS api using request payload
      | periodicInterestId |page_start|
      |                 10 |1|
    Then Validate getTermInterestRates DSS api response status code as "200"
    And Validate getTermInterestRates DSS api response should match with schema
    Then Validate getTermInterestRates DSS api response
    
    Scenario: getTermInterestRates DSS api with valid periodicInterestId and page_size
    Given Perform post operation to hit getTermInterestRates DSS api using request payload
      | periodicInterestId |page_size|
      |                 10 |1|
    Then Validate getTermInterestRates DSS api response status code as "200"
    And Validate getTermInterestRates DSS api response should match with schema
    Then Validate getTermInterestRates DSS api response
    
    Scenario: getTermInterestRates DSS api with valid periodicInterestId and pagination
    Given Perform post operation to hit getTermInterestRates DSS api using request payload
      | periodicInterestId |page_start|page_size|page_token|
      |                 10 |1|1| |
    Then Validate getTermInterestRates DSS api response status code as "200"
    And Validate getTermInterestRates DSS api response should match with schema
    Then Validate getTermInterestRates DSS api response

  #negative scenarios
  Scenario Outline: getTermInterestRates DSS api with invalid periodicInterestId four times
    Given Perform post operation to hit getTermInterestRates DSS api using "<periodicInterestIdValue>" as "periodicInterestId"
    Then Validate getTermInterestRates DSS api response status code as "<statusCode>"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "<description>"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "<responseStatusCode>"

    Examples: 
      | periodicInterestIdValue | statusCode | description                      | responseStatusCode |
      |                       1 |        400 | Incorrect Id format              |              40001 |
      |                      14 |        400 | NULL KEY SPECIFIED FOR SELECTION |              40001 |
      |                     140 |        400 | Incorrect Id format              |              40001 |
      |                    1002 |        500 | Runtime error                    |              50001 |

  Scenario: getTermInterestRates DSS api with invalid field name as periodicInterest instead of periodicInterest
    Given Perform post operation to hit getTermInterestRates DSS api using "10" as "periodicInterest"
    Then Validate getTermInterestRates DSS api response status code as "400"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "40001"

  Scenario Outline: getTermInterestRates DSS api with invalid periodicInterestId
    Given Perform post operation to hit getTermInterestRates DSS api using "<periodicInterestIdValue>" as "periodicInterestId"
    Then Validate getTermInterestRates DSS api response status code as "400"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "Incorrect Id format"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "40001"

    Examples: 
      | periodicInterestIdValue |
      | a                       |
      | ab1                     |
      | !@BC1                   |

  Scenario: getTermInterestRates DSS api without periodicInterestId value
    Given Perform post operation to hit getTermInterestRates DSS api using request payload
      | periodicInterestId |
      |                    |
    Then Validate getTermInterestRates DSS api response status code as "400"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "40001"

  Scenario: getTermInterestRates DSS api without payload
    Given Perform post operation to hit getTermInterestRates DSS api without payload
    Then Validate getTermInterestRates DSS api response status code as "400"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "40001"
    
    Scenario: getTermInterestRates DSS api with valid periodicInterestId and invalid pagination
    Given Perform post operation to hit getTermInterestRates DSS api using request payload
      | periodicInterestId |page_start|page_size|page_token|
      |                 10 |6|1| |
    Then Validate getTermInterestRates DSS api response status code as "404"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "40401"
	
	#Token need to be implemented
  Scenario: getTermInterestRates DSS api with expired Token
    Given Perform post operation to hit getTermInterestRates DSS api using request payload with expired Token
      | periodicInterestId |
      |                 10 |
    Then Validate getTermInterestRates DSS api response status code as "401"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "The token has expired"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "40101"

  Scenario: getTermInterestRates DSS api with token does not match with Customer Number
    Given Perform post operation to hit getTermInterestRates DSS api using request payload with token does not match with Customer Number
      | periodicInterestId |
      |                 10 |
    Then Validate getTermInterestRates DSS api response status code as "403"
    And Validate getTermInterestRates DSS api error response should match with schema
    And Validate "description" value in getTermInterestRates DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getTermInterestRates DSS api response should be "40301"

  Scenario: getTermInterestRates DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getTermInterestRates DSS api using request payload and disablePaginationheader as "true"
      | periodicInterestId |
      |                 10 |
    Then Validate getTermInterestRates DSS api response status code as "200"
