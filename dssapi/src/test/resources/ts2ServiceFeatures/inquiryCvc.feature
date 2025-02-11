#Author: shkan
#Keywords This feature is to test /v1/inquiryCvc api of TS2 service
#This api fetches the Customer info "​/v1/inquiryCvc"
#Feature: List of scenarios.
@ts2
Feature: This feature is to test inquiryCvc api of TS2 service
   this api fetches the Customer Income info "/v1/inquiryCvc"

  @tag1
  Scenario Outline: Testing the ​inquiryCvc api with valid account details
    Given post operation to request inquiryCvc api to get the cvc with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" dateExpiry "<dateExpiry>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and cvc is fetched with status "<status>" and desc "<desc>" and cvc as "<cvc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | dateExpiry | statusCode | status | desc                      |cvc|
      | 5446122335397983 | 00000894131 | 000255706 | ACTIV      | 22-11-2022 |        200 |    999 | Account status is closed. ||
      | 5446122320099081 | 00000879635 | 000241529 | ACTIV      | 22-11-2022 |        200 |    000 |                           |658|

  @tag1
  Scenario Outline: Testing the ​inquiryCvc api with valid account details
    Given post operation to request inquiryCvc api to get the cvc with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" dateExpiry "<dateExpiry>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and cvc is fetched with status "<status>" and desc "<desc>" and cvc as "658"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | dateExpiry | statusCode | status | desc                     |
      | 5446122335397883 | 00000894131 | 000255706 | ACTIV      | 22-11-2022 |        200 |    999 | Account Identifier Error |
      |                  | 00000894131 | 000255706 | ACTIV      | 22-11-2022 |        200 |    999 | Account Identifier Error |
      | 5446122320099081 | 00000894131 | 000255706 | ACTIV      | 22-11-2022 |        200 |    000 |                          |
      | 5446122320099081 |             |           | ACTIV      | 22-11-2022 |        200 |    000 |                          |
      | 5446122320099081 |             |           | SERVIC     | 22-11-2022 |        200 |    000 |                          |
      | 5446122320099081 |             |           | ACTIV      | ACTIV      |        200 |    999 | DB2 Error                |
      | 5446122320099081 |             |           | ACTIV      |            |        200 |    000 |                          |
