@DSS-17xxx
Feature: getProductDetails DSS api
 @run1
  Scenario: getProductDetails api with valid payload productId
    Given Perform post operation to hit getProductDetails DSS api using request payload
      | productId      |
      | AD.TFSA.GIC.2Y |
    Then Validate getProductDetails DSS api response status code as "200"
    And Validate getProductDetails DSS api response should match with schema
    And Validate getProductDetails DSS api response


  Scenario Outline: getProductDetails api with invalid productId four times-Hystrix
    Given Perform post operation to hit getProductDetails DSS api using "<values>" as "productId"
    Then Validate getProductDetails DSS api response status code as "<statusCode>"
    And Validate getProductDetails DSS api error response should match with schema
    And Validate "description" value in getProductDetails DSS api response should be "<description>"
    And Validate "statusCode" value in getProductDetails DSS api response should be "<responseStatusCode>"
    Examples: 
      | values | statusCode | description                                           |responseStatusCode|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        500 | Runtime error                                             |50001|
 
 Scenario: getProductDetails api without payload
	  Given Perform post operation to hit getProductDetails DSS api without payload
    Then Validate getProductDetails DSS api response status code as "400"
    And Validate getProductDetails DSS api error response should match with schema
    And Validate "description" value in getProductDetails DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductDetails DSS api response should be "40001"
	
Scenario: getProductDetails api with invalid attribute name
    Given Perform post operation to hit getProductDetails DSS api using "AD.TFSA.GIC.2Y" as "prodTTD"
    Then Validate getProductDetails DSS api response status code as "400"
    And Validate getProductDetails DSS api error response should match with schema
    And Validate "description" value in getProductDetails DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductDetails DSS api response should be "40001"
    
 Scenario: getProductDetails DSS api without productId value
    Given Perform post operation to hit getProductDetails DSS api using "" as "productId"
    Then Validate getProductDetails DSS api response status code as "400"
    And Validate getProductDetails DSS api error response should match with schema
    And Validate "description" value in getProductDetails DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductDetails DSS api response should be "40001"
    
 Scenario Outline: getProductDetails DSS api with invalid productId
    Given Perform post operation to hit getProductDetails DSS api using "<values>" as "productId"
    Then Validate getProductDetails DSS api response status code as "400"
    And Validate getProductDetails DSS api error response should match with schema
    And Validate "description" value in getProductDetails DSS api response should be "ERROR IN EXEXCUTION OF SELECT COMMAND"
    And Validate "statusCode" value in getProductDetails DSS api response should be "40001"

    Examples: 
      | values     |
      | abcABC     |
      | sdfAB12%^& |
      | !@%^&*     |
	
  #Token has been not implemented by dev Team
  Scenario: getProductDetails DSS api with expired Token
    Given Perform post operation to hit getProductDetails DSS api using request payload with expired Token
      | productId |
      |   AD.TFSA.GIC.2Y |
    Then Validate getProductDetails DSS api response status code as "401"
    And Validate getProductDetails DSS api error response should match with schema
    And Validate "description" value in getProductDetails DSS api response should be "The token has expired"
    And Validate "statusCode" value in getProductDetails DSS api response should be "40001"

  Scenario: getProductDetails DSS api with token does not match with Customer Number
    Given Perform post operation to hit getProductDetails DSS api using request payload with token does not match with Customer Number
      | productId |
      |    AD.TFSA.GIC.2Y |
    Then Validate getProductDetails DSS api response status code as "403"
    And Validate getProductDetails DSS api error response should match with schema
    And Validate "description" value in getProductDetails DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getProductDetails DSS api response should be "40001"
    
    #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getProductDetails DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getProductDetails DSS api using request payload and disablePaginationheader as "true"
      | productId |
      |   AD.TFSA.GIC.2Y |
    Then Validate getPortfolioDetails DSS api response status code as "200"