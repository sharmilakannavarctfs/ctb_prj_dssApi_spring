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
Feature: GetAccountTest
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Invalid Card Number Test
    Given Perform post operation to hit getAccount from TS2-service using invalid "<cardNbr>" and "<opId_servic>"

    Then Validate getAccount DSS api response status code as "200"
    
    Examples: 
      | cardNbr          | opId_servic |
      | 5446147925503534 | SERVIC      |

      
  @tag2
  Scenario Outline: Valid Card Number Test
    Given Perform post operation to hit getAccount from TS2-service using invalid "<cardNbr>" and "<opId_servic>"

    Then Validate getAccount DSS api response status code as "200"
    
    Examples: 
      | cardNbr          | opId_servic |
      | 5446147292141611 | SERVIC 		 |
      
  
  @regression
  Scenario Outline: testing flow
    Given testing flow
    Then validate "200"
    
    Examples: 
      | cardNbr          | opId_servic |
      | 5446147292141611 | SERVIC 		 |