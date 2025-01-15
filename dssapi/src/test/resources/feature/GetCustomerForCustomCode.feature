#Author: shkan
#Keywords This feature is to test getCustomerIncomeInfo api of TS2 service
#This api fetches the Customer info "/v1/getCustomerForCustomCode"
#Feature: List of scenarios.
@ts2
Feature: This feature is to test /v1/getCustomerForCustomCode api of TS2 service
   this api fetches the Customer Income info "/v1/getCustomerForCustomCode"

  #@tag1
  #Scenario Outline: Testing the /v1/getCustomerForCustomCode api with valid account details
    #Given post operation to request getCustomerForCustomCode api to get the customercode information on "<cardNbr>" "<accountId>" "<custId>" and "<operatorId>"
    #Then validate the status code as "<statusCode>" and customer Custom Codes info is fetched with status "<statusCode>" and desc "<desc>"
#
    #Examples: 
      #| cardNbr          | accountId   | custId    | operatorId | statusCode | status | desc |
      #| 5446122020477629 | 00000839126 | 000209233 | SERVIC     |        200 |    000 |      |
      #| 5446122020477629 | 00000839126 |           | SERVIC     |        200 |    000 |      |
      #| 5446122020477629 |             |           | SERVIC     |        200 |    000 |      |

  @tag2
  Scenario Outline: Testing getAccountInfo api with invalid carndNbr
    Given post operation to request getCustomerIncome api to get the customerIncome information on "<cardNbr>" "<accountId>" "<custId>" and "<operatorId>"
    Then validate the status code as "<statusCode>" and customer Custom Codes info is fetched with status "<statusCode>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | statusCode | status | desc                     |
      | 5446122020477629 | 00000839126 | 000209233 |            |        400 |    000 |                          |
      #|                  | 00000839126 | 000209233 | SERVIC     |        200 |    000 |                          |
      #| 5446122020477779 | 00000839126 | 000209233 | SERVIC     |        200 |    500 | Account Identifier Error |
      #|                  |             |           |            |        400 |        |                          |
