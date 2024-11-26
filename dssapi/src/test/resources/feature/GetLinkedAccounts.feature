@DSS-173xx
Feature: getLinkedAccounts DSS api

 @run1
  Scenario: getLinkedAccounts api with valid payload productId
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId      |
      | 1001018 |
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
 
   Scenario: getLinkedAccounts api with valid payload productId and beneficiaryId
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId  |beneficiaryId|
      | 100215      | BEN2400800001 | 
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
    
   Scenario: getLinkedAccounts api with valid payload productId and cardId
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId  |cardId    |
      | 100215      |  | 
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
    
    Scenario: getLinkedAccounts api with valid payload productId and ownAccount
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId  |ownAccount    |
      | 100215      |  | 
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
    
   Scenario: getLinkedAccounts api with valid payload productId and beneficiaryId
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId  |beneficiaryId  |cardId    |
      | 100215      | BEN2400800001 |  |
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
    
  Scenario: getLinkedAccounts api with valid payload productId and beneficiaryId
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId  |beneficiaryId  |ownAccount    |
      | 100215      | BEN2400800001 |  |
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
    
  Scenario: getLinkedAccounts api with valid payload productId and beneficiaryId
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId  |cardId  |ownAccount    |
      | 100215      |       |         |
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
    
  Scenario: getLinkedAccounts api with valid payload productId and beneficiaryId
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload
      | customerId  | beneficiaryId |cardId  |ownAccount    |
      | 100215      | BEN2400800001 |      |         |
    Then Validate getLinkedAccounts DSS api response status code as "200"
    And Validate getLinkedAccounts DSS api response should match with schema
    And Validate getLinkedAccounts DSS api response 
 
   Scenario Outline: getLinkedAccounts api with invalid customerId four times
    Given Perform post operation to hit getLinkedAccounts DSS api using "<values>" as "customerId"
    Then Validate getLinkedAccounts DSS api response status code as "<statusCode>"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "<description>"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "<responseStatusCode>"
    Examples: 
      | values | statusCode | description                                           |responseStatusCode|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        404 | No records were found that matched the selection criteria |40401|
      | 123 |        500 | Runtime error                                             |50001|
 
 
 Scenario: getLinkedAccounts api without payload
	  Given Perform post operation to hit getLinkedAccounts DSS api without payload
    Then Validate getLinkedAccounts DSS api response status code as "400"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "40001"
 
  Scenario Outline: getLinkedAccounts DSS api with invalid field names
    Given Perform post operation to hit getLinkedAccounts DSS api using "1001018" as "<customerIdField>" and "BEN2400800001" as "<beneficiaryIdField>" and "cardId" as "<cardIdField>" and "ownAccount" as "<ownAccountField>" 
    Then Validate getLinkedAccounts DSS api response status code as "400"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "40001"

    Examples: 
      | customerIdField | beneficiaryIdField | cardIdField |ownAccountField|
      | customerName    | beneficiaryId      | cardId     |  ownAc      |
      | customerName    | beneficiaryId      | card       |  ownAccount |
      | customerName    | beneficiar         | cardId     |  ownAccount |
      | customerName    | beneficiar         | card       |  ownAccount |
      | customerName    | beneficiar         | cardId     |  ownAc      |
      | customerName    | beneficiaryId      | card       |  ownAc      |
      | customerName    | beneficiary        | card       |  ownAc      |
 @run20
  Scenario Outline: getLinkedAccounts DSS api with invalid value combinations
    Given Perform post operation to hit getLinkedAccounts DSS api using "<customerIdValue>" as "customerId" and "<beneficiaryIdValue>" as "beneficiaryId" and "<cardIdValue>" as "cardId" and "<ownAccountValue>" as "ownAccount" 
    Then Validate getLinkedAccounts DSS api response status code as "404"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "40401"

    Examples: 
      | customerIdValue | beneficiaryIdValue | cardIdValue |ownAccountValue|
      | 1001018      | abc                  |            |             |
      | 1001018      | BEN2400800001        | abc        |             |
      | 1001018      | BEN2400800001        |            |  abc        |
      | 1001018      | abc                  | abc        |             |
      | 1001018      | abc                  |            |  abc        |
      | 1001018      | BEN2400800001        | abc        |  abc        |
    
 Scenario: getLinkedAccounts DSS api without customerId value
    Given Perform post operation to hit getLinkedAccounts DSS api using "" as "customerId"
    Then Validate getLinkedAccounts DSS api response status code as "400"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "40001"
    
 Scenario Outline: getLinkedAccounts DSS api with invalid customerId
    Given Perform post operation to hit getLinkedAccounts DSS api using "<values>" as "customerId"
    Then Validate getLinkedAccounts DSS api response status code as "400"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "ERROR IN EXEXCUTION OF SELECT COMMAND"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "40001"

    Examples: 
      | values     |
      | abcABC     |
      | sdfAB12%^& |
      | !@%^&*     |
    
  #Token has been not implemented by dev Team
  Scenario: getLinkedAccounts DSS api with expired Token
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload with expired Token
      | customerId |
      | 1001018    |
    Then Validate getLinkedAccounts DSS api response status code as "401"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "The token has expired"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "40001"

  Scenario: getLinkedAccounts DSS api with token does not match with Customer Number
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload with token does not match with Customer Number
      | customerId |
      |  1001018 |
    Then Validate getLinkedAccounts DSS api response status code as "403"
    And Validate getLinkedAccounts DSS api error response should match with schema
    And Validate "description" value in getLinkedAccounts DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getLinkedAccounts DSS api response should be "40001"
    
    #Add scenario for The disablePagination header can be set to true on request to Temenos to disable response paging
  Scenario: getLinkedAccounts DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getLinkedAccounts DSS api using request payload and disablePaginationheader as "true"
      | customerId |
      | 1001004    |
    Then Validate getLinkedAccounts DSS api response status code as "200"