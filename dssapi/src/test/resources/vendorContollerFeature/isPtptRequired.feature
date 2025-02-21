#Author: shkan
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag2
  Scenario Outline: This scenario verifies the isptpRequired api fron vendorContoller to check if there is Promise to Pay on a given valid accountId
    Given post the isiPtpRequired api on vendorController with accountId "<accountId>" and ficoCaseId "<ficoCaseId>"
    Then verify the statusCode as <statusCode> and result as "<result>"

    Examples: 
      | accountId   | ficoCaseId | statusCode | result | description         |
      | 00000852357 | ABC        |        200 | Yes    | account with PTP    |
      | 00000823546 | ABC        |        200 | No     | account without PTP |

  @tag2
  Scenario Outline: This scenario verifies the isptpRequired api fron vendorContoller to check if there is Promise to Pay on a given invalid accountId
    Given post the isiPtpRequired api on vendorController with accountId "<accountId>" and ficoCaseId "<ficoCaseId>"
    Then verify the statusCode as <statusCode> and result as "<result>"

    Examples: 
      | accountId   | ficoCaseId | statusCode | result | description       |
      | 00000823546 |            |        200 | No     | empty ficoCaseId  |
      | 00000823546 | null       |        422 | No     | null ficoCaseId   |
      |             | ABC        |        400 | No     | empty accountId   |
      | null        | ABC        |        422 | No     | null accountId    |
      | 00000823512 | ABC        |        501 | No     | invalid accountId |
