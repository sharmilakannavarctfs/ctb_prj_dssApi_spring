Feature: Get Product Transactions

   Scenario: getProductTransactions api with valid payload id
    Given Perform post operation to hit getProductTransactions DSS api using request payload
      | id         | startDate | endDate  |
      | 1000007443 |  2023/10/01 | 2023/11/16 |   
      # date format to be-YYYYMMDD
    Then Validate getProductTransactions DSS api response status code as "200"
    And Validate getProductTransactions DSS api response should match with schema
    And Validate getProductTransactions DSS api response
    
      @run5 
  Scenario: getProductTransactions api with valid payload as correct id only
    Given Perform post operation to hit getProductTransactions DSS api using request payload
     | id        |
     | 1000007443   |
    Then Validate getProductTransactions DSS api response status code as "200"
    And Validate getProductTransactions DSS api response should match with schema
    And Validate getProductTransactions DSS api response
    
   Scenario: getProductTransactions api with valid payload as correct id and startDate only
    Given Perform post operation to hit getProductTransactions DSS api using request payload
     |     id     | startDate        |
     |  1000007443   | 2023/10/01     |
     # date format to be-YYYYMMDD
    Then Validate getProductTransactions DSS api response status code as "200"
    And Validate getProductTransactions DSS api response should match with schema
    And Validate getProductTransactions DSS api response
    
  Scenario: Validate the response of  getProductTransactions api with valid payload as correct id and endDate only
    Given Perform post operation to hit getProductTransactions DSS api using request payload
     |     id     | endDate        |
     |  1000007443   | 2023/11/16     |
     # date format to be-YYYYMMDD
    Then Validate getProductTransactions DSS api response status code as "200"
    And Validate getProductTransactions DSS api response should match with schema
    And Validate getProductTransactions DSS api response
    
    
    
  Scenario Outline: getProductTransactions DSS api with invalid id and startDate and endDate as one wrong value
    Given Perform post operation to hit getProductTransactions DSS api using "<id>" as "id" and "<startDate>" as "startDate" and "<endDate>" as "endDate"
    Then Validate getProductTransactions DSS api response status code as "<statuscode>"
    And Validate getProductTransactions DSS api error response should match with schema
    And Validate "description" value in getProductTransactions DSS api response should be "<description>"
    And Validate "statusCode" value in getProductTransactions DSS api response should be "<resStatusCode>"
 
    Examples: 
      | id         | startDate  |endDate     | statuscode |         description                                       | resStatusCode |
      | 123        | 2023/10/01 | 2023/11/16 |        404 | ACCOUNT DOES NOT EXIST IN THIS COMPANY                    |   40401 |
      | 1000007443 | 123        | 2023/11/16 |        404 | No records were found that matched the selection criteria |   40401 |
      | 1000007443 | 2023/10/01 | 123        |        404 | No records were found that matched the selection criteria |   40401 |

  Scenario Outline: getProductTransactions DSS api with invalid id and startDate and endDate as two wrong values
    Given Perform post operation to hit getProductTransactions DSS api using "<id>" as "id" and "<startDate>" as "startDate" and "<endDate>" as "endDate"
    Then Validate getProductTransactions DSS api response status code as "<statuscode>"
    And Validate getProductTransactions DSS api error response should match with schema
    And Validate "description" value in getProductTransactions DSS api response should be "<description>"
    And Validate "statusCode" value in getProductTransactions DSS api response should be "<resStatusCode>"
 
    Examples: 
      | id         | startDate  |endDate     | statuscode |         description                                       | resStatusCode |
      | 1000007443 | 123        | 123        |        404 | No records were found that matched the selection criteria |   40401 |
      | 123        | 2023/10/01 | 123        |        404 | No records were found that matched the selection criteria |   40401 |
      | 123        | 123        | 2023/11/16 |        404 | No records were found that matched the selection criteria |   40401 |
      | 123        | 123        | 2023/11/16 |        500 | Runtime error                                             |   50001 |

  Scenario: getProductTransactions api with invalid payload as incorrect id and incorrect startDate and incorrect endDate
     Given Perform post operation to hit getProductTransactions DSS api using request payload
     | id        | startDate | endDate  |
     | 123     |   123    | 123 |   
      # date format to be-YYYYMMDD
    Then Validate getProductTransactions DSS api response status code as "404"
    And Validate "description" value in getProductTransactions DSS api response should be "No records were found that matched the selection criteria"

  Scenario: getProductTransactions api with valid payload as correct startDate only
     Given Perform post operation to hit getProductTransactions DSS api using request payload
     | startDate        |
     | 2023/10/01     |
     # date format to be-YYYYMMDD
    Then Validate getProductTransactions DSS api response status code as "400"
    And Validate "description" value in getProductTransactions DSS api response should be "invalid input parameters"
    
  Scenario: getProductTransactions api with valid payload as correct endDate only
    Given Perform post operation to hit getProductTransactions DSS api using request payload
     | endDate        |
     | 2023/11/16     |
     # date format to be-YYYYMMDD
    Then Validate getProductTransactions DSS api response status code as "400"
    And Validate "description" value in getProductTransactions DSS api response should be "invalid input parameters"

Scenario: getProductTransactions api without payload
	  Given Perform post operation to hit getProductTransactions DSS api without payload
    Then Validate getProductTransactions DSS api response status code as "400"
    And Validate getProductTransactions DSS api error response should match with schema
    And Validate "description" value in getProductTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductTransactions DSS api response should be "40001"
    
  Scenario: getProductTransactions DSS api without id value
     Given Perform post operation to hit getProductTransactions DSS api using request payload
     | id        |
     |      |
    Then Validate getProductTransactions DSS api response status code as "400"
    And Validate getProductTransactions DSS api error response should match with schema
    And Validate "description" value in getProductTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductTransactions DSS api response should be "40001"
    
 Scenario Outline: getProductTransactions DSS api with invalid field names
    Given Perform post operation to hit getProductTransactions DSS api using "1000007443" as "<idField>" and "2023/10/01" as "<startDateField>" and "2023/11/16" as "<endDateField>"
    Then Validate getProductTransactions DSS api response status code as "400"
    And Validate getProductTransactions DSS api error response should match with schema
    And Validate "description" value in getProductTransactions DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getProductTransactions DSS api response should be "40001"

    Examples: 
      | idField   | startDateField  | endDateField |
      | ids       | startDate       | endDate      |
      | ids       | start           | endDate      |
      | ids       | startDate       | end          |
      | ids       | start           | end          |

  Scenario: getProductTransactions DSS api with expired Token
    Given Perform post operation to hit getProductTransactions DSS api using request payload with expired Token
      | id  |
      | 1000007443 |
    Then Validate getProductTransactions DSS api response status code as "401"
     And Validate getProductTransactions DSS api error response should match with schema
    And Validate "description" value in getProductTransactions DSS api response should be "The token has expired"
    And Validate "statusCode" value in getProductTransactions DSS api response should be "40101"

  Scenario: getProductTransactions DSS api with token does not match with Customer Number
     Given Perform post operation to hit getProductTransactions DSS api using request payload with token does not match with Customer Number
      | id  |
      | 1000007443 |
    Then Validate getProductTransactions DSS api response status code as "403"
    And Validate getProductTransactions DSS api error response should match with schema
    And Validate "description" value in getProductTransactions DSS api response should be "The token does not match the customer number and access is denied"
     And Validate "statusCode" value in getProductTransactions DSS api response should be "40101"
     
      #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getProductTransactions DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getProductTransactions DSS api using request payload and disablePaginationheader as "true"
      | id |
      | 1000007443 |
    Then Validate getProductTransactions DSS api response status code as "200"
