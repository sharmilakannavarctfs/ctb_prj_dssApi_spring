#Author: YugantaKishore.das@cfts.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Delete message functionality
  To check whether the Delete message functionality is working as expected or not

  @tag1
  Scenario Outline: Perform a successful Delete message operation
    Given The user makes a post call to retrieveMessage API by passing "<targetIdentifier>"
    Then The user stores the retrieveMessage count before Creating dynamic message
    Given The user creates a Dynamic Message by passing "<targetIdentifier>", "<msgTextEng>", "<msgTextFch>" and msgExpiryDate and makes a post call to createDynamicMessage API
    Then The user verifies whether the dynamic message for "<targetIdentifier>" is created or not by comparing the count
    When The user makes post call to deleteMessage API and gets the response by passing messageId and "<targetIdentifier>"
    And The user validates whether the created record with "<targetIdentifier>" is being deleted successfully by verifying the <status code> and the count of the number of messages
    Examples:
      | targetIdentifier                     | status code | msgTextEng      | msgTextFch     |
      | 7c1736ec-665b-4f9f-aac6-99302c3d7ed4 | 200         | Text in English | Text in French |

  @tag2
  Scenario: Passing no payload and validating deleteMessage negative scenario
    Given The user tries to make a post call to the deleteMessage API without passing any payload

#  @tag2
#  Scenario Outline: Validating the negative test cases with missing data
#    Given I want to write a step with <name>
#    When I check for the <value> in step
#    Then I verify the <status> in step
#
#    Examples:
#      | name  | value | status  |
#      | name1 | 5     | success |
#      | name2 | 7     | Fail    |
