Feature: GetTermPeriodicLineFeature

  Scenario: getTermPeriodicLineFeature api with valid payload productId
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      |
      | AD.TFSA.GIC.2Y |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  @TestRun
  Scenario: getTermPeriodicLineFeature api with valid payload productId and currency
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | currency |
      | AD.TFSA.GIC.2Y | CAD      |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  Scenario: getTermPeriodicLineFeature api with valid payload productId and page_start
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | page_start |
      | AD.TFSA.GIC.2Y |          1 |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  Scenario: getTermPeriodicLineFeature api with valid payload productId and page_size
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | page_size |
      | AD.TFSA.GIC.2Y |         1 |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  Scenario: getTermPeriodicLineFeature api with valid payload productId, page_start and page_size
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | page_start | page_size |
      | AD.TFSA.GIC.2Y |          1 |         1 |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  Scenario: getTermPeriodicLineFeature api with valid payload productId, currency and page_start
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | currency | page_start |
      | AD.TFSA.GIC.2Y | CAD      |          1 |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  Scenario: getTermPeriodicLineFeature api with valid payload productId, currency and page_size
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | currency | page_size |
      | AD.TFSA.GIC.2Y | CAD      |         1 |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  Scenario: getTermPeriodicLineFeature api with valid payload productId, currency, page_start and page_size
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | currency | page_start | page_size |
      | AD.TFSA.GIC.2Y | CAD      |          1 |         1 |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  Scenario: getTermPeriodicLineFeature api with valid payload productId, currency and pagination parameters
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | productId      | currency | page_start | page_size | page_token |
      | AD.TFSA.GIC.2Y | CAD      |          1 |         1 |            |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
    And Validate getTermPeriodicLineFeature DSS api response should match with schema
    And Validate getTermPeriodicLineFeature DSS api response

  #negative scenarios
  Scenario Outline: getTermPeriodicLineFeature api with invalid field names
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using "AD.TFSA.GIC.2Y" as "<productIdField>" and "CAD" as "<currencyField>"
    Then Validate getTermPeriodicLineFeature DSS api response status code as "<statuscode>"
    And Validate getTermPeriodicLineFeature DSS api error response should match with schema
    And Validate "description" value in getTermPeriodicLineFeature DSS api response should be "<description>"
    And Validate "statusCode" value in getTermPeriodicLineFeature DSS api response should be "<resStatusCode>"

    Examples: 
      | productIdField | currencyField | statuscode | description           | resStatusCode |
      | product        | currency      |        400 | Missing key Attribute |         40001 |
      | product        | curr          |        400 | Missing key Attribute |         40001 |

  Scenario: getTermPeriodicLineFeature DSS api without values
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using "" as "<productId>" and "" as "<currency>"
    Then Validate getTermPeriodicLineFeature DSS api response status code as "400"
    And Validate getTermPeriodicLineFeature DSS api error response should match with schema
    And Validate "description" value in getTermPeriodicLineFeature DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTermPeriodicLineFeature DSS api response should be "40001"

  @TestRun
  Scenario: getTermPeriodicLineFeature api with valid payload as correct currency only
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload
      | currency |
      | CAD      |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "400"
    And Validate getTermPeriodicLineFeature DSS api error response should match with schema
    And Validate "description" value in getTermPeriodicLineFeature DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTermPeriodicLineFeature DSS api response should be "40001"

  Scenario: getTermPeriodicLineFeature DSS api without payload
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api without payload
    Then Validate getTermPeriodicLineFeature DSS api response status code as "400"
    And Validate getTermPeriodicLineFeature DSS api error response should match with schema
    And Validate "description" value in getTermPeriodicLineFeature DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTermPeriodicLineFeature DSS api response should be "40001"

  Scenario Outline: getTermPeriodicLineFeature DSS api with invalid productId and currency values-Hystrix also
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using "<productIdValue>" as "productId" and "<currencyValue>" as "currency"
    Then Validate getTermPeriodicLineFeature DSS api response status code as "<statuscode>"
    And Validate getTermPeriodicLineFeature DSS api error response should match with schema
    And Validate "description" value in getTermPeriodicLineFeature DSS api response should be "<description>"
    And Validate "statusCode" value in getTermPeriodicLineFeature DSS api response should be "<resStatusCode>"

    Examples: 
      | productIdValue | currencyValue | statuscode | description                                               | resStatusCode |
      | AD.TFSA.GIC.8Y |           123 |        404 | No records were found that matched the selection criteria |         40401 |
      |            123 | CAD           |        404 | No records were found that matched the selection criteria |         40401 |
      | ABC123         | USD           |        404 | No records were found that matched the selection criteria |         40401 |
      |            123 |           123 |        500 | Runtime error                                             |         50001 |

  Scenario: getTermPeriodicLineFeature DSS api with expired Token
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload with expired Token
      | productId    |
      | AR.TFSA.HISA |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "401"
    And Validate getTermPeriodicLineFeature DSS api error response should match with schema
    And Validate "description" value in getTermPeriodicLineFeature DSS api response should be "The token has expired"
    And Validate "statusCode" value in getTermPeriodicLineFeature DSS api response should be "40101"

  Scenario: getTermPeriodicLineFeature DSS api with token does not match with Customer Number
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload with token does not match with Customer Number
      | productId    |
      | AR.TFSA.HISA |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "403"
    And Validate getTermPeriodicLineFeature DSS api error response should match with schema
    And Validate "description" value in getTermPeriodicLineFeature DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getTermPeriodicLineFeature DSS api response should be "40101"

  #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getTermPeriodicLineFeature DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getTermPeriodicLineFeature DSS api using request payload and disablePaginationheader as "true"
      | productId    |
      | AR.TFSA.HISA |
    Then Validate getTermPeriodicLineFeature DSS api response status code as "200"
