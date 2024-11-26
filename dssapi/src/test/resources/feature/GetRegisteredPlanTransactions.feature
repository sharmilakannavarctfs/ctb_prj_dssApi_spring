@DSS-17335
Feature: getRegisteredPlanTransactions DSS api

  Scenario: getRegisteredPlanTransactions DSS api with valid portfolioId and taxyear
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear |
      | 1001042-1   |    2024 |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "200"
    And Validate getRegisteredPlanTransactions DSS api response should match with schema
    And Validate getRegisteredPlanTransactions DSS api response

  Scenario: getRegisteredPlanTransactions DSS api with valid portfolioId, taxyear and debitCreditIndicator
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear | debitCreditIndicator |
      | 1001042-1   |    2024 | CR                   |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "200"
    And Validate getRegisteredPlanTransactions DSS api response should match with schema
    And Validate getRegisteredPlanTransactions DSS api response

  Scenario: getRegisteredPlanTransactions DSS api with valid portfolioId, taxyear and pagination
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear | page_size | page_start | page_token |
      | 1001042-1   |    2024 |         1 |          1 |            |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "200"
    And Validate getRegisteredPlanTransactions DSS api response should match with schema
    And Validate getRegisteredPlanTransactions DSS api response

  Scenario: getRegisteredPlanTransactions DSS api with valid portfolioId, taxyear, debitCreditIndicator and pagination
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear | debitCreditIndicator | page_size | page_start | page_token |
      | 1001042-1   |    2024 | CR                   |         1 |          1 |            |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "200"
    And Validate getRegisteredPlanTransactions DSS api response should match with schema
    And Validate getRegisteredPlanTransactions DSS api response

  #need to check data with debitcreditindicator as DR and create separate schema
  Scenario: getRegisteredPlanTransactions DSS api with valid portfolioId, taxyear and debitCreditIndicator
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear | debitCreditIndicator |
      | 1001042-1   |    2024 | DR                   |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "200"
    And Validate getRegisteredPlanTransactions DSS api response should match with schema
    And Validate getRegisteredPlanTransactions DSS api response

  #negative scenarios
  #Check with dev team about future date and check separate scenario for invalid taxyear(Future year validation covered by UI layer)
  #check with dev team about date before created portfolio
  Scenario Outline: getRegisteredPlanTransactions DSS api with invalid portfolioId and taxYear
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using "<portfolioIdValue>" as "portfolioId" and "<taxYearValue>" as "taxYear"
    Then Validate getRegisteredPlanTransactions DSS api response status code as "<statuscode>"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "<description>"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "<resStatusCode>"

    Examples: 
      | portfolioIdValue | taxYearValue | statuscode | description                                               | resStatusCode |
      | 1001008-2        |         2024 |        404 | No records were found that matched the selection criteria |         40401 |
      | 1001008-1        |         2023 |        404 | No records were found that matched the selection criteria |         40401 |
      | 1001008-2        |         2023 |        404 | No records were found that matched the selection criteria |         40401 |
      | 1001008-2        |         2023 |        500 | Runtime error                                             |         50001 |

  Scenario Outline: getRegisteredPlanTransactions DSS api with invalid values
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using "<portfolioIdValue>" as "portfolioId" and "<taxYearValue>" as "taxYear" and "<debitCreditIndicatorValue>" as "debitCreditIndicator"
    Then Validate getRegisteredPlanTransactions DSS api response status code as "<statuscode>"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "<description>"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "<resStatusCode>"

    Examples: 
      | portfolioIdValue | taxYearValue | debitCreditIndicatorValue | statuscode | description                                               | resStatusCode |
      | 1001008-2        |         2024 | CR                        |        404 | No records were found that matched the selection criteria |         40401 |
      | 1001008-1        |         2023 | CR                        |        404 | No records were found that matched the selection criteria |         40401 |
      | 1001008-2        |         2023 | cr                        |        404 | No records were found that matched the selection criteria |         40401 |
      | 1001008-2        |         2023 | dr                        |        500 | Runtime error                                             |         50001 |

  Scenario Outline: getRegisteredPlanTransactions DSS api with invalid portfolioId and taxYear
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using "<portfolioIdValue>" as "portfolioId" and "<taxYearValue>" as "taxYear"
    Then Validate getRegisteredPlanTransactions DSS api response status code as "<statuscode>"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "<description>"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "<resStatusCode>"

    Examples: 
      | portfolioIdValue | taxYearValue | statuscode | description                                               | resStatusCode |
      | abc              | abc          |        404 | No records were found that matched the selection criteria |         40401 |
      | abc123           | abc          |        404 | No records were found that matched the selection criteria |         40401 |
      | Abd!@$%          | !@)(&&       |        404 | No records were found that matched the selection criteria |         40401 |

  Scenario Outline: getRegisteredPlanTransactions DSS api with invalid field Names
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using "1001008-1" as "<portfoliofield>" and "2024" as "<taxYearField>" and "CR" as "<debitCreditIndicatorField>"
    Then Validate getRegisteredPlanTransactions DSS api response status code as "400"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40001"

    Examples: 
      | portfoliofield | taxYearField | debitCreditIndicatorField |
      | portfolio      | taxYear      | debitCreditIndicator      |
      | portfolioId    | year         | debitCreditIndicator      |
      | portfolio      | Year         | debitCreditIndicator      |
      | portfolio      | taxYear      | debitCreditIndicator      |
      | portfolioId    | Year         | debitCreditIndicators     |
      | portfolio      | Year         | debitCreditIndicators     |

  #create above three scenarios using examples table
  #include test for passing only taxyear or portfolioId
  Scenario Outline: getRegisteredPlanTransactions DSS api with only one field and value
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using "<value>" as "<field>"
    Then Validate getRegisteredPlanTransactions DSS api response status code as "400"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40001"

    Examples: 
      | value     | field                |
      | 1001008-1 | portfolioId          |
      |      2024 | taxYear              |
      | CR        | debitCreditIndicator |

  Scenario: getRegisteredPlanTransactions DSS api with only mandatory field Names
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear |
      |             |         |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "400"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40001"

  Scenario: getRegisteredPlanTransactions DSS api with only all field Names
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear | debitCreditIndicator |
      |             |         |                      |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "400"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40001"

  Scenario Outline: getRegisteredPlanTransactions DSS api with only one field name
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using "<value>" as "<field>"
    Then Validate getRegisteredPlanTransactions DSS api response status code as "400"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40001"

    Examples: 
      | value | field                |
      |       | portfolioId          |
      |       | taxYear              |
      |       | debitCreditIndicator |

  Scenario: getRegisteredPlanTransactions DSS api without any payload
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api without any payload
    Then Validate getRegisteredPlanTransactions DSS api response status code as "400"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40001"

  @TestRun
  Scenario: getRegisteredPlanTransactions DSS api with valid portfolioId, taxyear, debitCreditIndicator and Invalid pagination
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload
      | portfolioId | taxYear | debitCreditIndicator | page_size | page_start | page_token |
      | 1001042-1   |    2024 | CR                   |         1 |         14 |            |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "404"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40401"

  #Token has been not implemented by dev Team
  Scenario: getRegisteredPlanTransactions DSS api with expired Token
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload with expired Token
      | portfolioId | taxYear |
      | 1001008-1   |    2024 |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "401"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "The token has expired"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40101"

  Scenario: getRegisteredPlanTransactions DSS api with token does not match with Customer Number
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload with token does not match with Customer Number
      | portfolioId | taxYear |
      | 1001008-1   |    2024 |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "403"
    And Validate getRegisteredPlanTransactions DSS api error response should match with schema
    And Validate "description" value in getRegisteredPlanTransactions DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getRegisteredPlanTransactions DSS api response should be "40301"

  #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getPortfolios DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getRegisteredPlanTransactions DSS api using request payload and disablePaginationheader as "true"
      | portfolioId | taxYear |
      | 1001008-1   |    2024 |
    Then Validate getRegisteredPlanTransactions DSS api response status code as "200"
