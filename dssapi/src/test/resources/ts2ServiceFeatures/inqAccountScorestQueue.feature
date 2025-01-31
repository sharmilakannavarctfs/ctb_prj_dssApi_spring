#Author: shkan
#Keywords This feature is to test inqAccountForCustomCode api of TS2 service
#This api fetches the Customer info "/v1/inqAccountScores"
#Feature: List of scenarios.
@ts2
Feature: This feature is to test inqAccountForCustomCode api of TS2 service
   this api fetches the Customer Income info "/v1/inqAccountScores"

  @tag1
  Scenario Outline: Testing the /v1/inqAccountScores api with valid account details
    Given post operation to request inqAccountScores api to get the custom code with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" ids "<ids>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and respective account score for the given ids "<scores>" is fetched with status "<status>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | ids                     | scores         | statusCode | status | desc |
      #| 5259951135597608 | 00000871595 | 000232912 | SERVIC     | T0093,T0083,T0001,T0080 | 980,15,763,733 |        200 |    000 |      |
      | 5259951135597608 | 00000871595 | 000232912 | SERVIC     | T0093                   |            980 |        200 |    000 |      |
      | 5259951135597608 | 00000871595 | 000232912 | SERVIC     | T0083                   |             15 |        200 |    000 |      |
      | 5259951135597608 | 00000871595 | 000232912 | SERVIC     | T0001                   |            763 |        200 |    000 |      |
      | 5259951135597608 | 00000871595 | 000232912 | SERVIC     | T0083,T0080             |         15,733 |        200 |    000 |      |

  @tag2
  Scenario Outline: Testing the /v1/inqAccountScores api with invalid account details
    Given post operation to request inqAccountScores api to get the custom code with cardNbr "<cardNbr>" accountId "<accountId>" custId "<custId>" ids "<ids>" and operatorId "<operatorId>"
    Then validate the status code as <statusCode> and respective account score for the given ids "<scores>" is fetched with status "<status>" and desc "<desc>"

    Examples: 
      | cardNbr          | accountId   | custId    | operatorId | ids     | scores | statusCode | status | desc |
      | 5259951135597123 | 00000871595 | 000232912 | SERVIC     | T0083   |     15 |        200 |    000 |      |
      | 5259951135597608 |             |           | SERVIC     | T0083   |     15 |        200 |    000 |      |
      | 5259951135597608 |             |           |            | T0083   |     15 |        200 |    000 |      |
      | 5259951135597608 |             |           |            | INVALID |     15 |        200 |    000 |      |
      | 5259951135597608 |             |           |            |         |        |        200 |    000 |      |
      | 5259951135597123 |             |           | INVALID    | T0083   |     15 |        200 |    000 |      |
