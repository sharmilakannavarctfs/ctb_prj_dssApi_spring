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
@ts2
Feature: Evaluate Credit Limit Test
  This api evaluates the cre

	@tag2
  Scenario Outline: testing with valid Account excluded from TRIAD credit line decisioning.
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as "Account excluded from TRIAD credit line decisioning."
    
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122498962581 | 1500     				| SERVIC 			|
    
	@tag2
  Scenario Outline: testing with valid Account excluded from TRIAD credit line decisioning.
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as "Account excluded from TRIAD credit line decisioning."
    
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122498962581 | 1500     				| SERVIC 			|
    
	@tag2
  Scenario Outline: testing with valid Account and 00 monthlyIncome.
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as "Account excluded from TRIAD credit line decisioning."
    
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122498962581 | 000     				| SERVIC 			|
	
    
	@tag2
  Scenario Outline: testing with valid Account and no value for monthlyIncome.
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as "Account excluded from TRIAD credit line decisioning."
    
    Examples:
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122498962581 |      				| SERVIC 			|
    
	@tag2
  Scenario Outline: testing with valid Account and invalid operatorId
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as "Account excluded from TRIAD credit line decisioning."
    
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122498962581 |      3000				| 123 			|
    
	@tag2
  Scenario Outline: testing with valid Account and no operatorId
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "400" and fault description as "no_error"
    
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122498962581 |      3000				|  			|
    
	@tag2
  Scenario Outline: testing with valid Account with CL008 decline status
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as ""
   And Decline code and description as "CL008" and "Scores do not meet criteria"
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122174020365 |      3000				|  	SERVIC		|
    
	@tag2
  Scenario Outline: testing with valid Account with CL013 decline status
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as ""
   And Decline code and description as "CL013" and "Credit File Freeze"
    
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446122540545954 |      3000				|  	SERVIC		|
    
	@tag2
  Scenario Outline: testing with valid Account with CL014 decline status
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as ""
   And Decline code and description as "CL014" and "C86 Card Review" 
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446147989948708 |      3000				|  	SERVIC		|
    
	@tag2
  Scenario Outline: testing with valid Account with CL014 decline status
   Given Post operation to hit evaluateCreditLimit from TS2-service using valid "<cardNbr>" "<monthlyIncome>" and "<operatorId>"
   
   Then Validate evaluateCreditLimit DSS api response status code as "200" and fault description as ""
   And Decline code and description as "CL015" and "C86 Card Review" 
    Examples: 
    | cardNbr          | monthlyIncome	  |  operatorId | 
    | 5446147989948708 |      3000				|  	SERVIC		|
	
	
  

