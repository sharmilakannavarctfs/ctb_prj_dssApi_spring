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
@RetrieveCustomerAddressAvailability
Feature: Delete message functionality
  To check whether the retrieveCustomerAddressAvailability functionality is working as expected or not

  @Positive
  Scenario Outline: Validate successful retrieveCustomerAddressAvailability functionality
    Given The user makes a post call to retrieveCustomerAddressAvailability API by passing "<cardNbr>", "<accountId>", "<custId>", "<statusCode>", "<operationId>" and "<restartToken>"
    Then The user validates "<statusCode>", "<statusMsg>" and "<status>" from the success response message of retrieveCustomerAddressAvailability API
    Examples:
      | cardNbr                           | accountId                | custId              | status | statusMsg | statusCode | operationId | restartToken |
      | 5259950409060269,5446122599872697 | 00000839126, 00000839126 | 000203361,000218131 | 000    | passed    | 200        | 8980        | 7070         |

  @Negative
  Scenario Outline: Validate error response for retrieveCustomerAddressAvailability API
    Given The user makes a post call to retrieveCustomerAddressAvailability API by passing "<cardNbr>", "<accountId>", "<custId>", "<statusCode>", "<operationId>" and "<restartToken>"
    Then The user validates "<statusCode>", "<statusMsg>" and "<status>" from the error response message of retrieveCustomerAddressAvailability API
    Examples:
      | cardNbr          | accountId   | custId    | status | statusMsg           | statusCode | operationId | restartToken |
      | 1365747375757095 | 00000839126 | 000209233 | 999    | failed              | 200        | 8980        | 7070         |
      |                  | 00000839126 | 000209233 | 999    | failed              | 200        | 8980        | 7070         |
      |                  |             |           | 000    | passed              |            |             |              |
      #| 5446122020477629 | 00000839126 | 000209233 | 50301  | TS2 Service Failure |            | 8980        | 7070         |