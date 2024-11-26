Feature: getProductsByCategory DSS api

  @TestRun
  Scenario: getProductsByCategory api with valid productLineId
    Given Perform post operation to hit getProductsByCategory DSS api using request payload
      | productLineId |
      | ACCOUNTS      |
    Then Validate getProductsByCategory DSS api response status code as "200"
    And Validate getProductsByCategory DSS api response should match with schema
    Then Validate getProductsByCategory DSS api response

  Scenario: getProductsByCategory api with valid productLineId
    Given Perform post operation to hit getProductsByCategory DSS api using request payload
      | productLineId |
      | DEPOSITS      |
    Then Validate getProductsByCategory DSS api response status code as "200"
    And Validate getProductsByCategory DSS api response should match with schema
    Then Validate getProductsByCategory DSS api response
    
   

  #negative scenarios
  #include scenario to test passing only field name without any value
  Scenario Outline: getProductsByCategory DSS api with invalid productLineId
    Given Perform post operation to hit getProductsByCategory DSS api using "<productLineIdValue>" as "productLineId"
    Then Validate getProductsByCategory DSS api response status code as "<statusCode>"
    And Validate getProductsByCategory DSS api error response should match with schema
    And Validate "description" value in getProductsByCategory DSS api response should be "<description>"
    And Validate "statusCode" value in getProductsByCategory DSS api response should be "<resStatusCode>"

    Examples: 
      | productLineIdValue | statusCode | description                                               | resStatusCode |
      | abc                |        404 | No records were found that matched the selection criteria |         40401 |
      | XYZ                |        404 | No records were found that matched the selection criteria |         40401 |
      |              12345 |        404 | No records were found that matched the selection criteria |         40401 |
      | CBJ!*^12           |        500 | Runtime error                                             |         50001 |

  Scenario: getProductsByCategory DSS api with invalid field Name
    Given Perform post operation to hit getProductsByCategory DSS api using "DEPOSITS" as "productLine"
    Then Validate getProductsByCategory DSS api response status code as "400"
    And Validate getProductsByCategory DSS api error response should match with schema
    And Validate "description" value in getProductsByCategory DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductsByCategory DSS api response should be "40001"

  Scenario: getProductsByCategory DSS api without field value
    Given Perform post operation to hit getProductsByCategory DSS api using request payload
      | productLineId |
      |               |
    Then Validate getProductsByCategory DSS api response status code as "404"
    And Validate getProductsByCategory DSS api error response should match with schema
    And Validate "description" value in getProductsByCategory DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductsByCategory DSS api response should be "40001"

  Scenario: Validate the response of getProductsByCategory DSS api without payload
    Given Perform post operation to hit getProductsByCategory DSS api without payload
    Then Validate getProductsByCategory DSS api response status code as "400"
    And Validate getProductsByCategory DSS api error response should match with schema
    And Validate "description" value in getProductsByCategory DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductsByCategory DSS api response should be "40001"

  #Token has been not implemented by dev Team
  Scenario: Validate the response of getProductsByCategory DSS api with expired Token
    Given Perform post operation to hit getProductsByCategory DSS api using request payload with expired Token
      | productLineId |
      | ACCOUNTS      |
    Then Validate getProductsByCategory DSS api response status code as "401"
    And Validate getProductsByCategory DSS api error response should match with schema
    And Validate "description" value in getProductsByCategory DSS api response should be "The token has expired"
    And Validate "statusCode" value in getProductsByCategory DSS api response should be "40101"

  Scenario: Validate the response of getProductsByCategory DSS api with token does not match with Customer Number
    Given Perform post operation to hit getProductsByCategory DSS api using request payload with token does not match with Customer Number
      | productLineId |
      | ACCOUNTS      |
    Then Validate getProductsByCategory DSS api response status code as "403"
    And Validate getProductsByCategory DSS api error response should match with schema
    And Validate "description" value in getProductsByCategory DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getProductsByCategory DSS api response should be "40301"
    
    Scenario: getProductsByCategory DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getProductsByCategory DSS api using request payload and disablePaginationheader as "true"
     | productLineId |
      | DEPOSITS      |
    Then Validate getProductsByCategory DSS api response status code as "200"
