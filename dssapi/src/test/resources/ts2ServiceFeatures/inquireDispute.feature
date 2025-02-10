#Author: shkan
#Keywords This feature is to test ​/v1​/inquireDispute api of TS2 service
#This api fetches the Customer info "​/v1​/inquireDispute"
#Feature: List of scenarios.
@ts2
Feature: This feature is to test inquireDispute api of TS2 service
   this api fetches the Customer Income info "​/v1​/inquireDispute"

  @tag1
  Scenario Outline: Testing the ​inquireDispute api with valid account details
    Given post operation to request inquireDispute api to get the inquireDisputeOutput with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" decisionArea "<decisionArea>" onlyCurr "<onlyCurr>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and inquireDisputeOutput is fetched with status "<status>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | statusCode | status | desc |
      | 5446122320099081 | 00000879635 | 000241529 | SERVIC     |        200 |    000 |      |
      | 5259950288351508 | 00000821175 | 000205005 | SERVIC     |        200 |    000 |      |
      | 5259950288351508 | 00000821175 | 000205005 | SERVIC     |        200 |    000 |      |

  #@tag2
  #Scenario Outline: Testing the /v1/inqTriad8 api with invalid account details
    #Given post operation to request inqTriad8 api to get the triadInfoList with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" decisionArea "<decisionArea>" onlyCurr "<onlyCurr>" and operatorId "<operatorId>"
    #Then validate the status code as <statusCode> and triadInfoList is fetched with status "<status>" and desc "<desc>"
#
    #Examples: 
      #| cardNbr          | accountId   | custId    | operatorId | decisionArea    | onlyCurr | statusCode | status | desc                        |
      #| 5259951947596079 |             |           | SERVIC     | Authorizations2 | true     |        200 |    000 | No data matches for request |
      #| 5446122320099081 | 00000879635 | 000241529 | SERVIC     | Authorizations2 | true     |        200 |    000 | No data matches for request |
      #| 5446122320099123 | 00000879635 | 000241529 | SERVIC     | Authorizations2 | true     |        200 |    999 | Account Identifier Error    |
      #|                  | 00000879635 | 000241529 | SERVIC     | Authorizations2 | true     |        200 |    999 | Account Identifier Error    |
