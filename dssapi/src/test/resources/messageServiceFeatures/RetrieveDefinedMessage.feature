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
@RetrieveDefinedMessage
Feature: Delete message functionality
  To check whether the Retrieve Defined Message functionality is working as expected or not

  @Positive
  Scenario Outline: Perform a successful Retrieve Defined Message operation
    Given The user makes post call to retrieveDefinedMessage API by passing "<targetIdentifier>" and "<messageId>"
    Then The user validates the message response from retrieveDefinedMessage API by comparing it with <status code>
    Examples:
      | targetIdentifier                     | status code | messageId                            |
      | 7c1736ec-665b-4f9f-aac6-99302c3d7ed4 | 200         | df0586b3-cc37-43f7-a411-7e343f9ad20f |
      | 7c1736ec-665b-4f9f-aac6-99302c3d7ed4 | 501         | df0586b3-cc37-43f7-a411-7e343        |
      |                                      | 501         | df0586b3-cc37-43f7-a411-7e343f9ad20f |
      | 7c1736ec-665b-4f9f-aac6-99302c3d7ed4 | 404         |                                      |

  @Negative
  Scenario Outline: Passing no payload and validating retrieveDefinedMessage negative scenario
    Given The user tries to make a post call to the retrieveDefinedMessage API without passing any payload
    And The user verifies the error response by validating <status_code> and "<description>" for retrieveDefinedMessage API
    Examples:
      | status_code | description          |
      | 404         | MessageId not found. |

