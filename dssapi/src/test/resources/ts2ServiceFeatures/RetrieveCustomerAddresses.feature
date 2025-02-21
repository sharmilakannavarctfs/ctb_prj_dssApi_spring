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
@RetrieveCustomerAddresses
Feature: Delete message functionality
  To check whether the retrieveCustomerAddresses functionality is working as expected or not

  @Positive
  Scenario Outline: Validate successful retrieveCustomerAddresses functionality
    Given The user makes a post call to retrieveCustomerAddresses API by passing "<cardNbr>", "<accountId>", "<custId>", "<statusCode>", "<operatorId>", "<addrType>" and "<restartToken>"
    Then The user validates "<statusCode>", "<statusMsg>" and "<status>" from the success response message of retrieveCustomerAddresses API
    Examples:
      | cardNbr          | accountId   | custId    | operatorId | addrType | restartToken | status | statusMsg | statusCode |
      | 5259950409060269 | 00000839126 | 000203361 | SERVIC     | ACTIVE   | 7070         | 000    | passed    | 200        |

  @Negative
  Scenario Outline: Validate error response for retrieveCustomerAddresses API
    Given The user makes a post call to retrieveCustomerAddresses API by passing "<cardNbr>", "<accountId>", "<custId>", "<statusCode>", "<operatorId>", "<addrType>" and "<restartToken>"
    Then The user validates "<statusCode>", "<statusMsg>" and "<status>" from the error response message of retrieveCustomerAddresses API
    Examples:
      | cardNbr          | accountId   | custId    | operatorId | addrType | restartToken | status | statusMsg                | statusCode |
      | 1365747375757095 | 00000839126 | 000209233 | SERVIC     | ACTIVE   | 7070         | 999    | failed                   | 200        |
      |                  | 00000839126 | 000209233 | SERVIC     | ACTIVE   | 7070         | 999    | failed                   | 200        |
      | 5446122020477629 | 00000839126 | 000209233 |            | ACTIVE   | 7070         | 40001  | Invalid Input Parameters | 400        |
      | 5446122020477629 | 00000839126 | 000209233 | null       | ACTIVE   | 7070         | 50301  | TS2 Service Failure      | 503        |

  @Negative
  Scenario Outline: Validate No payload JSON Scenario
    Given The user makes a post call to retrieveCustomerAddresses API without any payload
    Then The user validates "<statusCode>", "<statusMsg>" and "<status>" from the error response message of retrieveCustomerAddresses API
    Examples:
      | status | statusMsg                | statusCode |
      | 40001  | Invalid Input Parameters | 400        |