#Author: shkan
#Keywords This feature is to test getCustomerIncomeInfo api of TS2 service
#This api fetches the Customer info "/v1/getCustomerIncomeInfo"
#Feature: List of scenarios.
@ts2
Feature: This feature is to test getCustomerIncomeInfo api of TS2 service
   this api fetches the Customer Income info "/v1/getCustomerIncomeInfo"

  @tag1
  Scenario Outline: Testing the /v1/getCustomerIncomeInfo api with valid account details
    Given post operation to request getCustomerIncome api to get the customerIncome information on "<cardNbr>" "<accountId>" "<custId>" and "<operatorId>"
    Then validate the status code as "<statusCode>" and customer income info is fetched with status "<statusCode>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | statusCode | status | desc |
      | 5446122020477629 | 00000839126 | 000209233 | SERVIC     |        200 |    000 |      |
      | 5446122020477629 |             | 000209233 | SERVIC     |        200 |    000 |      |
      | 5446122020477629 | 00000839126 |           | SERVIC     |        200 |    000 |      |
      | 5446122020477629 |             |           | SERVIC     |        200 |    000 |      |

  @tag2
  Scenario Outline: Testing getAccountInfo api with invalid carndNbr
    Given post operation to request getCustomerIncome api to get the customerIncome information on "<cardNbr>" "<accountId>" "<custId>" and "<operatorId>"
    Then validate the status code as "<statusCode>" and customer income info is fetched with status "<statusCode>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | statusCode | status | desc                     |
      | 5446122020477629 | 00000839126 | 000209233 |            |        400 |    000 |                          |
      |                  | 00000839126 | 000209233 | SERVIC     |        200 |    000 |                          |
      | 5446122020477779 | 00000839126 | 000209233 | SERVIC     |        200 |    500 | Account Identifier Error |
      |                  |             |           |            |        400 |        |                          |