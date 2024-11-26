Feature: Get Demand Periodic Line Features
  @run11
   Scenario: getDemandPeriodicLineFeatures api with valid payload productId and currency
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
      | productId     | currency |
      | AR.TFSA.HISA |  CAD  |   
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "200"
    And Validate getDemandPeriodicLineFeatures DSS api response should match with schema
    And Validate getDemandPeriodicLineFeatures DSS api response
    
   Scenario: getDemandPeriodicLineFeatures api with valid payload as correct productId only
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
     | productId        |
     | AR.TFSA.HISA     |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "200"
    And Validate getDemandPeriodicLineFeatures DSS api response should match with schema
    And Validate getDemandPeriodicLineFeatures DSS api response
    
   
  Scenario: getDemandPeriodicLineFeatures api with valid payload as correct productId and page_size
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
     | productId        |page_size |
     | AR.TFSA.HISA     |  1    |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "200"
    And Validate getDemandPeriodicLineFeatures DSS api response should match with schema
    And Validate getDemandPeriodicLineFeatures DSS api response
    
  Scenario: getDemandPeriodicLineFeatures api with valid payload as correct productId and page_start
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
     | productId        |page_start |
     | AR.TFSA.HISA     |  1    |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "200"
    And Validate getDemandPeriodicLineFeatures DSS api response should match with schema
    And Validate getDemandPeriodicLineFeatures DSS api response
    
   Scenario: getDemandPeriodicLineFeatures api with valid payload as correct productId and page_start and page_size
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
     | productId        |page_size |page_start |
     | AR.TFSA.HISA     |  1    |    1  |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "200"
    And Validate getDemandPeriodicLineFeatures DSS api response should match with schema
    And Validate getDemandPeriodicLineFeatures DSS api response
 
   Scenario: getDemandPeriodicLineFeatures api with valid payload as correct productId and page_start
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
     | productId        |page_start |
     | AR.TFSA.HISA     |  11    |
        Then Validate getDemandPeriodicLineFeatures DSS api response status code as "404"
    And Validate getDemandPeriodicLineFeatures DSS api error response should match with schema
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getDemandPeriodicLineFeatures DSS api response should be "40401"  
    
   Scenario: getDemandPeriodicLineFeatures api with valid payload as correct currency only
     Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
     | currency        |
     | CAD     |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "400"
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "invalid input parameters"
      
   Scenario Outline: getDemandPeriodicLineFeatures DSS api with invalid productId and currency values-Hystrix also
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using "<productId>" as "productId" and "<currency>" as "currency"
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "<statuscode>"
    And Validate getDemandPeriodicLineFeatures DSS api error response should match with schema
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "<description>"
    And Validate "statusCode" value in getDemandPeriodicLineFeatures DSS api response should be "<resStatusCode>"
 
    Examples: 
      | productId    | currency  | statuscode |         description                                       | resStatusCode |
      | AR.TFSA.HISA | 123        |        404 | No records were found that matched the selection criteria |   40401 |
      | 123          | CAD        |        404 | No records were found that matched the selection criteria |   40401 |
      | ABC123       | USD        |        404 | No records were found that matched the selection criteria |   40401 |
      | 123          | 123        |        500 | Runtime error                                             |   50001 |
  
   Scenario Outline: getDemandPeriodicLineFeatures DSS api with invalid field names
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using "AR.TFSA.HISA" as "<productIdF>" and "CAD" as "<currencyF>"
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "<statuscode>"
    And Validate getDemandPeriodicLineFeatures DSS api error response should match with schema
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "<description>"
    And Validate "statusCode" value in getDemandPeriodicLineFeatures DSS api response should be "<resStatusCode>"
 
    Examples: 
      | productIdF    | currencyF  | statuscode |      description         | resStatusCode |
      | pidABC        | currency   |        400 | Missing input attributes |   40001 |
      | pidABC        | ccyABC     |        400 | Missing input attributes |   40001 |
      
   Scenario: getDemandPeriodicLineFeatures api without payload
	  Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api without payload
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "400"
    And Validate getDemandPeriodicLineFeatures DSS api error response should match with schema
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "Missing input Attribute"
    And Validate "statusCode" value in getDemandPeriodicLineFeatures DSS api response should be "40001"
    
   Scenario: getDemandPeriodicLineFeatures DSS api without productId value
     Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload
     | productId        |
     |      |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "400"
    And Validate getDemandPeriodicLineFeatures DSS api error response should match with schema
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "Missing input Attribute"
    And Validate "statusCode" value in getDemandPeriodicLineFeatures DSS api response should be "40001"
    
    Scenario: getDemandPeriodicLineFeatures DSS api with expired Token
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload with expired Token
      | productId  |
      | AR.TFSA.HISA |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "401"
     And Validate getDemandPeriodicLineFeatures DSS api error response should match with schema
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "The token has expired"
    And Validate "statusCode" value in getDemandPeriodicLineFeatures DSS api response should be "40101"

  Scenario: getDemandPeriodicLineFeatures DSS api with token does not match with Customer Number
     Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload with token does not match with Customer Number
      | productId  |
      | AR.TFSA.HISA |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "403"
    And Validate getDemandPeriodicLineFeatures DSS api error response should match with schema
    And Validate "description" value in getDemandPeriodicLineFeatures DSS api response should be "The token does not match the customer number and access is denied"
     And Validate "statusCode" value in getDemandPeriodicLineFeatures DSS api response should be "40101"
     
      #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getDemandPeriodicLineFeatures DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getDemandPeriodicLineFeatures DSS api using request payload and disablePaginationheader as "true"
      | productId |
      | AR.TFSA.HISA |
    Then Validate getDemandPeriodicLineFeatures DSS api response status code as "200"  
    