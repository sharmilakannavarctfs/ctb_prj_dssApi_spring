#Author: shkan
#Keywords This feature is to test inqAccountForCustomCode api of TS2 service
#This api fetches the Customer info "/v1/inqAccountQueue"
#Feature: List of scenarios.
@ts2
Feature: This feature is to test inqAccountForCustomCode api of TS2 service
   this api fetches the Customer Income info "/v1/inqAccountQueue"

  @tag1
  Scenario Outline: Testing the /v1/inqAccountQueue api with valid account details
    Given post operation to request inqAccountQueue api to get the custom code with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" workflowid "<workflowId>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and values along with queue id is fetched with status "<status>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId | custId | operatorId | workflowId | statusCode | status | desc |
      | 5259951947596079 |           |        | SERVIC     | P/O        |        200 |    000 |      |
      | 5446122020477629 |           |        | SERVIC     | CFR        |        200 |    000 |      |

  @tag2
  Scenario Outline: Testing the /v1/inqAccountQueue api with valid account details
    Given post operation to request inqAccountQueue api to get the custom code with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" workflowid "<workflowId>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and values along with queue id is fetched with status "<status>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | workflowId  | statusCode | status | desc                                                             |
      | 5446122020477629 | 00000839126 | 000209233 | SERVIC     |             |        200 |    000 | Value of true is invalid for attribute nil on element workflowID |
      | 5446122020477629 | 00000839126 | 000209233 | SERVIC     |           1 |        200 |    000 | Value is not a valid choice                                      |
      | 5446122020477629 | 00000839126 | 000209233 | SERVIC     | randomValue |        200 |    000 | Value is not a valid choice                                      |
      |                  |             |           | SERVIC     | CFR         |        200 |    000 | Account Identifier Error                                         |
