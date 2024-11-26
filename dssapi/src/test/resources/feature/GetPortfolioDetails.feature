@DSS-17334
Feature: getPortfolioDetails DSS api
 @run5
 Scenario: getPortfolioDetails api with valid payload portfolioId
   Given Perform post operation to hit getPortfolioDetails DSS api using request payload
      | portfolioId      |
      | 1001004-1 |
   Then Validate getPortfolioDetails DSS api response status code as "200"
   And Validate getPortfolioDetails DSS api response should match with schema
   And Validate getPortfolioDetails DSS api response
   
 Scenario: getPortfolioDetails api with valid payload portfolioId
   Given Perform post operation to hit getPortfolioDetails DSS api using request payload
      | portfolioId      |page_size |
      | 1001004-1        |        1 | 
   Then Validate getPortfolioDetails DSS api response status code as "200"
   And Validate getPortfolioDetails DSS api response should match with schema
   And Validate getPortfolioDetails DSS api response
   
 Scenario: getPortfolioDetails api with valid payload portfolioId
   Given Perform post operation to hit getPortfolioDetails DSS api using request payload
      | portfolioId      |page_start |
      | 1001004-1        |        1 | 
   Then Validate getPortfolioDetails DSS api response status code as "200"
   And Validate getPortfolioDetails DSS api response should match with schema
   And Validate getPortfolioDetails DSS api response
 
  Scenario: getPortfolioDetails api with valid payload portfolioId
   Given Perform post operation to hit getPortfolioDetails DSS api using request payload
      | portfolioId      |page_size | page_start |
      | 1001004-1        |        1 |        1   |
   Then Validate getPortfolioDetails DSS api response status code as "200"
   And Validate getPortfolioDetails DSS api response should match with schema
   And Validate getPortfolioDetails DSS api response
 
  Scenario: getPortfolioDetails api with valid payload portfolioId and wrong page_start
   Given Perform post operation to hit getPortfolioDetails DSS api using request payload
      | portfolioId      |page_size | page_start |
      | 1001004-1        |        1 |        5   |  
    Then Validate getPortfolioDetails DSS api response status code as "404"
    And Validate getPortfolioDetails DSS api error response should match with schema
    And Validate "description" value in getPortfolioDetails DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getPortfolioDetails DSS api response should be "40401"
      
Scenario: getPortfolioDetails api without payload
	  Given Perform post operation to hit getPortfolioDetails DSS api without payload
    Then Validate getPortfolioDetails DSS api response status code as "400"
    And Validate getPortfolioDetails DSS api error response should match with schema
    And Validate "description" value in getPortfolioDetails DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getPortfolioDetails DSS api response should be "40001"
	
Scenario: getPortfolioDetails api with invalid attribute name
   Given Perform post operation to hit getPortfolioDetails DSS api using "1001004-1" as "portfolioTTD"
    Then Validate getPortfolioDetails DSS api response status code as "400"
    And Validate getPortfolioDetails DSS api error response should match with schema
    And Validate "description" value in getPortfolioDetails DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getPortfolioDetails DSS api response should be "40001"
    
 Scenario: getPortfolioDetails DSS api without portfolioId value
    Given Perform post operation to hit getPortfolioDetails DSS api using "" as "portfolioId"
    Then Validate getPortfolioDetails DSS api response status code as "400"
    And Validate getPortfolioDetails DSS api error response should match with schema
    And Validate "description" value in getPortfolioDetails DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getPortfolioDetails DSS api response should be "40001"
    
  Scenario Outline: getPortfolioDetails DSS api with invalid portfolioId
    Given Perform post operation to hit getPortfolioDetails DSS api using "<values>" as "portfolioId"
    Then Validate getPortfolioDetails DSS api response status code as "404"
    And Validate getPortfolioDetails DSS api error response should match with schema
    And Validate "description" value in getPortfolioDetails DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getPortfolioDetails DSS api response should be "40401"

    Examples: 
      | values     |
      | abcABC     |
      | sdfAB12%^& |
      | !@%^&*     |
	@run16
   Scenario Outline: getPortfolioDetails api with invalid portfilioId four times
    Given Perform post operation to hit getPortfolioDetails DSS api using "<values>" as "portfolioId"
    Then Validate getPortfolioDetails DSS api response status code as "<statusCode>"
    And Validate getPortfolioDetails DSS api error response should match with schema
    And Validate "description" value in getPortfolioDetails DSS api response should be "<description>"
    And Validate "statusCode" value in getPortfolioDetails DSS api response should be "<responseStatusCode>"
    Examples: 
      | values | statusCode | description                                           |responseStatusCode|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        500 | Runtime error                                             |50001|
 
  #Token has been not implemented by dev Team
  #Scenario: getPortfolioDetails DSS api with expired Token
    #Given Perform post operation to hit getPortfolioDetails DSS api using request payload with expired Token
      #| portfolioId |
      #|    1001004-1 |
    #Then Validate getPortfolioDetails DSS api response status code as "401"
    #And Validate getPortfolioDetails DSS api error response should match with schema
    #And Validate "description" value in getPortfolioDetails DSS api response should be "The token has expired"
    #And Validate "statusCode" value in getPortfolioDetails DSS api response should be "40001"
#
  #Scenario: getPortfolioDetails DSS api with token does not match with Customer Number
    #Given Perform post operation to hit getPortfolioDetails DSS api using request payload with token does not match with Customer Number
      #| portfolioId |
      #|    1001004-1 |
    #Then Validate getPortfolioDetails DSS api response status code as "403"
    #And Validate getPortfolioDetails DSS api error response should match with schema
    #And Validate "description" value in getPortfolioDetails DSS api response should be "The token does not match the customer number and access is denied"
    #And Validate "statusCode" value in getPortfolioDetails DSS api response should be "40001"
    #
    #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  #Scenario: getPortfolioDetails DSS api with disablePagination header setted to be true
    #Given Perform post operation to hit getPortfolioDetails DSS api using request payload and disablePaginationheader as "true"
      #| portfolioId |
      #|    1001004-1 |
    #Then Validate getPortfolioDetails DSS api response status code as "200"
	
	
	
	