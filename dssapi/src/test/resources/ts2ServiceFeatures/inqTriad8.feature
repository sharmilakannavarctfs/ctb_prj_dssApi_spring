#Author: shkan
#Keywords This feature is to test ​/v1​/inqTriad8 api of TS2 service
#This api fetches the Customer info "​/v1​/inqTriad8"
#Feature: List of scenarios.
@ts2
Feature: This feature is to test inqAccountForCustomCode api of TS2 service
   this api fetches the Customer Income info "​/v1​/inqTriad8"

  @tag1
  Scenario Outline: Testing the ​inqTriad8 api with valid account details
    Given post operation to request inqTriad8 api to get the triadInfoList with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" decisionArea "<decisionArea>" onlyCurr "<onlyCurr>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and triadInfoList is fetched with status "<status>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | decisionArea    | onlyCurr | statusCode | status | desc                        |
      | 5259951947596079 |             |           | SERVIC     | Authorizations2 | true     |        200 |    000 | No data matches for request |
      | 5446122320099081 | 00000879635 | 000241529 | SERVIC     | Authorizations2 | true     |        200 |    000 | No data matches for request |
      | 5446122320099081 | 00000879635 | 000241529 | SERVIC     |                 | true     |        200 |    000 |                             |
      | 5259950288351508 | 00000821175 | 000205005 | SERVIC     |                 | true     |        200 |    000 |                             |
  #@tag2
  #Scenario Outline: Testing the /v1/inqAccountQueue api with valid account details
    #Given post operation to request inqAccountQueue api to get the custom code with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" workflowid "<workflowId>" and operatorId "<operatorId>"
    #Then validate the status code as <statusCode> and values along with queue id is fetched with status "<status>" and desc "<desc>"
#
    #Examples: 
      #| cardNbr          | accountId   | custId    | operatorId | workflowId  | statusCode | status | desc                                                             |
      #| 5446122020477629 | 00000839126 | 000209233 | SERVIC     |             |        200 |    000 | Value of true is invalid for attribute nil on element workflowID |
      #| 5446122020477629 | 00000839126 | 000209233 | SERVIC     |           1 |        200 |    000 | Value is not a valid choice                                      |
      #| 5446122020477629 | 00000839126 | 000209233 | SERVIC     | randomValue |        200 |    000 | Value is not a valid choice                                      |
      #|                  |             |           | SERVIC     | CFR         |        200 |    000 | Account Identifier Error                                         |
