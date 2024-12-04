#Author: your.email@your.domain.com
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
Feature: EStatementDeenrollment
  Here i will be giving the details on the api

	@tag2
  Scenario Outline: testing with valid account credentials
    Given Post operation to hit EStatementDeenrollment from TS2-service using valid "<cardNbr>" "<accountId>""<custId>"and "<opId_servic>"

    Then Validate estatementDeenrollment DSS api response status code as "200"
    
    Examples: 
     | cardNbr          | accountId | custId      | opId_servic |
      | 5446122335397983 | 00000894131| 000255706 | SERVIC |
  

