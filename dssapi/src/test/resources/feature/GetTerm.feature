Feature: getTerm DSS api

  
  Scenario: getTerm DSS api with valid deposit Id
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         |
      | 1000556331 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id and customerId
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         |
      |    1001042 | 1000556331 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id and arrangementId
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         | arrangementId |
      | 1000556331 | AA24008VY1SR  |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id,arrangementId and customerId
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001042 | 1000556331 | AA24008VY1SR  |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id and page_start
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         | page_start |
      | 1000556331 |          1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id, customerId and page_start
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | page_start |
      |    1001042 | 1000556331 |          1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id, arrangementId and page_start
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         | arrangementId | page_start |
      | 1000556331 | AA24008VY1SR  |          1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response
	
	@TestRun
  Scenario: getTerm DSS api with valid deposit Id,arrangementId, customerId and page_start
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId | page_start |
      |    1001042 | 1000556331 | AA24008VY1SR  |          1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         | page_size |
      | 1000556331 |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id, customerId and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | page_size |
      |    1001042 | 1000556331 |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id, arrangementId and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         | arrangementId | page_size |
      | 1000556331 | AA24008VY1SR  |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id,arrangementId, customerId and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId | page_size |
      |    1001042 | 1000556331 | AA24008VY1SR  |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id, page_start and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         | page_start | page_size |
      | 1000556331 |          1 |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id, customerId, page_start and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | page_start | page_size |
      |    1001042 | 1000556331 |          1 |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id, arrangementId, page_start and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | Id         | arrangementId | page_start | page_size |
      | 1000556331 | AA24008VY1SR  |          1 |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  Scenario: getTerm DSS api with valid deposit Id,arrangementId, customerId, page_start and page_size
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId | page_start | page_size |
      |    1001042 | 1000556331 | AA24008VY1SR  |          1 |         1 |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  #need to add different data
  Scenario: getTerm DSS api with Registered Term data
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId  |
      |     100218 | 1000006471 | AD.TFSA.GIC.1Y |
    Then Validate getTerm DSS api response status code as "200"
    And Validate getTerm DSS api response should match with schema
    And Validate getTerm DSS api response

  #negative scenarios
  Scenario: getTerm DSS api with invalid customerId
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001043 | 1000556331 | AA24008VY1SR  |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api with invalid Id
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001042 | 1000556332 | AA24008VY1SR  |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api with invalid arrangementId
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001042 | 1000556331 | AA24008VY1S   |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api with invalid customerId and invalid Id
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001041 | 1000556332 | AA24008VY1SR  |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api with invalid customerId and invalid arrangementId
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001041 | 1000556331 | AA24008VY1SS  |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api with invalid Id and invalid arrangementId
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001042 | 1000556332 | AA24008VY1SS  |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api with invalid customerId, Id and arrangementId
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId |
      |    1001041 | 1000556332 | AA24008VY1SS  |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api with valid deposit Id,arrangementId, customerId, page_size and invalid page_start
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id         | arrangementId | page_start | page_size |
      |    1001042 | 1000556331 | AA24008VY1SR  |          4 |         1 |
    Then Validate getTerm DSS api response status code as "404"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "No records were found that matched the selection criteria"
    And Validate "statusCode" value in getTerm DSS api response should be "40401"

  Scenario: getTerm DSS api without Id
    Given Perform post operation to hit getTerm DSS api using "1001042" as "customerId" and "AA24008VY1SR" as "arrangementId"
    Then Validate getTerm DSS api response status code as "400"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTerm DSS api response should be "40001"

  Scenario Outline: getTerm DSS api with only one parameter
    Given Perform post operation to hit getTerm DSS api using "<value>" as "<field>"
    Then Validate getTerm DSS api response status code as "400"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTerm DSS api response should be "40001"

    Examples: 
      | value        | field         |
      | AA24008VY1SR | arrangementId |
      |      1001042 | customerId    |

  Scenario Outline: getTerm DSS api with invalid field names
    Given Perform post operation to hit getTerm DSS api using "1001042" as "<customerIdField>" and "1000556331" as "<IdField>" and "AA24008VY1SR" as "<arrangementIdField>"
    Then Validate getTerm DSS api response status code as "400"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTerm DSS api response should be "40001"

    Examples: 
      | customerIdField | IdField | arrangementIdField |
      | customerId      | deposit | arrangementId      |
      | customer        | deposit | arrangementId      |
      | customerId      | deposit | arrangement        |
      | customer        | deposit | arrangement        |

  
  Scenario: getTerm DSS api without values
    Given Perform post operation to hit getTerm DSS api using request payload
      | customerId | Id | arrangementId |
      | ""         | "" | ""            |
    Then Validate getTerm DSS api response status code as "400"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTerm DSS api response should be "40001"

  Scenario: getTerm DSS api without payload
    Given Perform post operation to hit getTerm DSS api without payload
    Then Validate getTerm DSS api response status code as "400"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "Missing key Attribute"
    And Validate "statusCode" value in getTerm DSS api response should be "40001"

  Scenario Outline: getTerm DSS api with invalid data four times
    Given Perform post operation to hit getTerm DSS api using "<customerIdValue>" as "customerId" and "<IdValue>" as "id" and "<arrangementIdValue>" as "arrangementId"
    Then Validate getTerm DSS api response status code as "<statusCode>"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "<des>"
    And Validate "statusCode" value in getTerm DSS api response should be "<resStatusCode>"

    Examples: 
      | customerIdValue | IdValue    | arrangementIdValue | statusCode | des                                                       | resStatusCode |
      |         1001041 | 1000556331 | AA24008VY1SR       |        404 | No records were found that matched the selection criteria |         40401 |
      |         1001042 | 1000556330 | AA24008VY1SR       |        404 | No records were found that matched the selection criteria |         40401 |
      |         1001042 | 1000556331 | AA24008VY1SS       |        404 | No records were found that matched the selection criteria |         40401 |
      |         1001041 | 1000556331 | AA24008VY1SR       |        500 | Runtime error                                             |         50001 |

  #Token need to be implemented
  Scenario: getTerm DSS api with expired Token
    Given Perform post operation to hit getTerm DSS api using request payload with expired Token
      | Id         |
      | 1000003464 |
    Then Validate getTerm DSS api response status code as "401"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "The token has expired"
    And Validate "statusCode" value in getTerm DSS api response should be "40101"

  Scenario: getTerm DSS api with token does not match with Customer Number
    Given Perform post operation to hit getTerm DSS api using request payload with token does not match with Customer Number
      | Id         |
      | 1000003464 |
    Then Validate getTerm DSS api response status code as "403"
    And Validate getTerm DSS api error response should match with schema
    And Validate "description" value in getTerm DSS api response should be "The token does not match the customer number and access is denied"
    And Validate "statusCode" value in getTerm DSS api response should be "40101"

  #include 500 validation
  Scenario: getTerm DSS api with disablePagination header setted to be true
    Given Perform post operation to hit getTerm DSS api using request payload and disablePaginationheader as "true"
      | Id         |
      | 1000003464 |
    Then Validate getTerm DSS api response status code as "200"
