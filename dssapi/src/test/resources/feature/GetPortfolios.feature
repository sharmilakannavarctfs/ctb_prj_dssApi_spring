@DSS-17333
Feature: getPortfolios DSS api
	
	@TestRun
  Scenario: getPortfolios DSS api with valid customerId which contains single portfolio
    Given Perform post operation to hit getPortfolios DSS api using request payload
      | customerId |
      |    1001004 |
      |1001008|
    Then Validate getPortfolios DSS api response status code as "200"
    And Validate getPortfolios DSS api response should match with schema
    And Validate getPortfolios DSS api response

  Scenario: getPortfolios DSS api with valid customerId and page_start
    Given Perform post operation to hit getPortfolios DSS api using request payload
      | customerId | page_start |
      |    1001008 |          1 |
    Then Validate getPortfolios DSS api response status code as "200"
    And Validate getPortfolios DSS api response should match with schema
    And Validate getPortfolios DSS api response

  Scenario: getPortfolios DSS api with valid customerId and page_size
    Given Perform post operation to hit getPortfolios DSS api using request payload
      | customerId | page_size |
      |    1001044 |         1 |
    Then Validate getPortfolios DSS api response status code as "200"
    And Validate getPortfolios DSS api response should match with schema
    And Validate getPortfolios DSS api response

  Scenario: getPortfolios DSS api with valid customerId and valid pagination
    Given Perform post operation to hit getPortfolios DSS api using request payload
      | customerId | page_start | page_size |page_token |
      |    1001044 |          1 |         1 ||
    Then Validate getPortfolios DSS api response status code as "200"
    And Validate getPortfolios DSS api response should match with schema
    And Validate getPortfolios DSS api response

  #negative scenarios
  Scenario: getPortfolios DSS api with invalid field Name as customer instead of customerId
    Given Perform post operation to hit getPortfolios DSS api using "100218" as "customer"
    Then Validate getPortfolios DSS api response status code as "400"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getPortfolios DSS api response should be "40001"

  Scenario: getPortfolios DSS api without customerId value
    Given Perform post operation to hit getPortfolios DSS api using request payload
      | customerId |
      |            |
    Then Validate getPortfolios DSS api response status code as "400"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getPortfolios DSS api response should be "40001"

  Scenario: getPortfolios DSS api without payload
    Given Perform post operation to hit getPortfolios DSS api without payload
    Then Validate getPortfolios DSS api response status code as "400"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getPortfolios DSS api response should be "40001"

  Scenario Outline: Validate the response of getPortfolios DSS api with invalid customerId
    Given Perform post operation to hit getPortfolios DSS api using "<values>" as "customerId"
    Then Validate getPortfolios DSS api response status code as "400"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "ERROR IN EXEXCUTION OF SELECT COMMAND"
    And Validate "statusCode" value in getPortfolios DSS api response should be "40001"

    Examples: 
      | values     |
      | abcABC     |
      | sdfAB12%^& |
      | !@%^&*     |

 
  Scenario: getPortfolios DSS api with valid customerId and invalid PaginationParameters
    Given Perform post operation to hit getPortfolios DSS api using request payload
      | customerId | page_size | page_start | page_token |
      |    1001044 |         1 |          4 |            |
    Then Validate getPortfolios DSS api response status code as "404"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getPortfolios DSS api response should be "40401"

  Scenario Outline: getPortfolios DSS api with invalid customerId four times
    Given Perform post operation to hit getPortfolios DSS api using "<values>" as "customerId"
    Then Validate getPortfolios DSS api response status code as "<statusCode>"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "<description>"
    And Validate "statusCode" value in getPortfolios DSS api response should be "<responseStatusCode>"

    Examples: 
      | values | statusCode | description                                               | responseStatusCode |
      |    123 |        404 | No records were found that matched the selection criteria |              40401 |
      |    123 |        404 | No records were found that matched the selection criteria |              40401 |
      |    456 |        404 | No records were found that matched the selection criteria |              40401 |
      |    789 |        500 | Runtime error                                             |              50001 |

  #Token has been not implemented by dev Team
  Scenario: Validate the response of getPortfolios DSS api with expired Token
    Given Perform post operation to hit getPortfolios DSS api using request payload with expired Token
      | customerId |
      |    1001004 |
    Then Validate getPortfolios DSS api response status code as "401"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "The token has expired"
    And Validate "statusCode" value in getPortfolios DSS api response should be "40001"

  Scenario: Validate the response of getPortfolios DSS api with token does not match with Customer Number
    Given Perform post operation to hit getPortfolios DSS api using request payload with token does not match with Customer Number
      | customerId |
      |    1001004 |
    Then Validate getPortfolios DSS api response status code as "403"
    And Validate getPortfolios DSS api error response should match with schema
    And Validate "description" value in getPortfolios DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getPortfolios DSS api response should be "40001"

  #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getPortfolios DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getPortfolios DSS api using request payload and disablePaginationheader as "true"
      | customerId |
      |    1001004 |
    Then Validate getPortfolios DSS api response status code as "200"
