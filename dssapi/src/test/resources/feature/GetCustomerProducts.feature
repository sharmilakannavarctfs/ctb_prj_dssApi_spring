@DSS-17xxx
Feature: getCustomerProducts DSS api

  Scenario: getCustomerProducts DSS api with valid customerId
    Given Perform post operation to hit getCustomerProducts DSS api using request payload
      | customerId |
      |    1001004 |
    Then Validate getCustomerProducts DSS api response status code as "200"
    And Validate getCustomerProducts DSS api response should match with schema
    And Validate getCustomerProducts DSS api response
    
    #negative scenarios
 
  Scenario Outline: getCustomerProducts DSS api with invalid customerId four times-Hystrix
    Given Perform post operation to hit getCustomerProducts DSS api using "<values>" as "customerId"
    Then Validate getCustomerProducts DSS api response status code as "<statusCode>"
    And Validate getCustomerProducts DSS api error response should match with schema
    And Validate "description" value in getCustomerProducts DSS api response should be "<description>"
    And Validate "statusCode" value in getCustomerProducts DSS api response should be "<responseStatusCode>"

    Examples: 
      | values | statusCode | description                                               | responseStatusCode |
      | 123    |        404 | No records were found that matched the selection criteria |              40401 |
      | abc    |        404 | No records were found that matched the selection criteria |              40401 |
      | abc123 |        404 | No records were found that matched the selection criteria |              40401 |
      | a@!5   |        500 | Runtime error                                             |              50001 |
      
  Scenario: getCustomerProducts DSS api with invalid field Name
    Given Perform post operation to hit getCustomerProducts DSS api using "100218" as "customerTTD"
    Then Validate getCustomerProducts DSS api response status code as "400"
    And Validate getCustomerProducts DSS api error response should match with schema
    And Validate "description" value in getCustomerProducts DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getCustomerProducts DSS api response should be "40001"
    
  Scenario: getCustomerProducts DSS api without customerId value
    Given Perform post operation to hit getCustomerProducts DSS api using request payload
      | customerId |
      |            |
    Then Validate getCustomerProducts DSS api response status code as "400"
    And Validate getCustomerProducts DSS api error response should match with schema
    And Validate "description" value in getCustomerProducts DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getCustomerProducts DSS api response should be "40001"
    
  Scenario: getCustomerProducts DSS api without payload
    Given Perform post operation to hit getCustomerProducts DSS api without payload
    Then Validate getCustomerProducts DSS api response status code as "400"
    And Validate getCustomerProducts DSS api error response should match with schema
    And Validate "description" value in getCustomerProducts DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getCustomerProducts DSS api response should be "40001"
    
  Scenario Outline: Validate the response of getCustomerProducts DSS api with invalid customerId
    Given Perform post operation to hit getCustomerProducts DSS api using "<values>" as "customerId"
    Then Validate getCustomerProducts DSS api response status code as "400"
    And Validate getCustomerProducts DSS api error response should match with schema
    And Validate "description" value in getCustomerProducts DSS api response should be "ERROR IN EXEXCUTION OF SELECT COMMAND"
    And Validate "statusCode" value in getCustomerProducts DSS api response should be "40001"

    Examples: 
      | values     |
      | abcABC     |
      | sdfAB12%^& |
      | !@%^&*     |

  #Token has been not implemented by dev Team
  Scenario: Validate the response of getCustomerProducts DSS api with expired Token
    Given Perform post operation to hit getCustomerProducts DSS api using request payload with expired Token
      | customerId |
      |    1001004 |
    Then Validate getCustomerProducts DSS api response status code as "401"
    And Validate getCustomerProducts DSS api error response should match with schema
    And Validate "description" value in getCustomerProducts DSS api response should be "The token has expired"
    And Validate "statusCode" value in getCustomerProducts DSS api response should be "40001"

  Scenario: Validate the response of getCustomerProducts DSS api with token does not match with Customer Number
    Given Perform post operation to hit getCustomerProducts DSS api using request payload with token does not match with Customer Number
      | customerId |
      |    1001004 |
    Then Validate getCustomerProducts DSS api response status code as "403"
    And Validate getCustomerProducts DSS api error response should match with schema
    And Validate "description" value in getCustomerProducts DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getCustomerProducts DSS api response should be "40001"

  #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getCustomerProducts DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getCustomerProducts DSS api using request payload and disablePaginationheader as "true"
      | customerId |
      |    1001004 |
    Then Validate getCustomerProducts DSS api response status code as "200"