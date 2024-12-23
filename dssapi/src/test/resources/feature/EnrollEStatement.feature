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
Feature: EnrollEStatement
  Here i will be giving the details on the api

	@tag2
  Scenario Outline: testing with valid card Number which has closed status
   Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
   Then Validate enrollEstatement DSS api response status code as "200" and fault description as "Determinator Declined Status ENROLLMENT REQUEST DECLINED DUE TO ACCOUNT STATUS"
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122335397983 | 000255706| TP04 													| SERVIC 			|TEST.AUTOMATION@CTFS.COM |
	
	@tag2
  Scenario Outline: testing with valid cardNUmber and invalid electronicVendorOptionId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "Field length is less than minimum"
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122335397983 | 000255706|  		    123											| SERVIC 			|TEST.AUTOMATION@CTFS.COM |
	@tag2
  Scenario Outline: testing with valid account and longer electronicVendorOptionId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "Field length exceeds maximum"
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122335397983 | 000255706|  	electronicVendorOptionId		| SERVIC 			|TEST.AUTOMATION@CTFS.COM |
	@tag2
  Scenario Outline: testing with testing with valid cardNUmber and null electronicVendorOptionId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "Required Attribute optID is missing."
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122335397983 | 000255706|  												| SERVIC 			|TEST.AUTOMATION@CTFS.COM |
    
	@tag2
  Scenario Outline: testing with testing with valid cardNUmber and invalid custId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "Required Attribute optID is missing."
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122335397983 | 000255123|  			TP04										| SERVIC 			|TEST.AUTOMATION@CTFS.COM |
    
	@tag2
  Scenario Outline: testing with testing with valid cardNUmber and null custId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "Required Attribute optID is missing."
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122335397983 | |  			TP04										| SERVIC 			|TEST.AUTOMATION@CTFS.COM |
    
	@tag2
  Scenario Outline: testing with testing with valid cardNUmber and null custId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "Required Attribute optID is missing."
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446147292141611 | |  			TP04										| SERVIC 			|TEST.AUTOMATION@CTFS.COM |
    
	@tag2
  Scenario Outline: testing with testing with valid cardNUmber and null operatorId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "400" and fault description as "No_error_msg"
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122013638658 | 000218140|  			TP04										|  			|TEST.AUTOMATION@CTFS.COM |
    
	@tag2
  Scenario Outline: testing with testing with valid cardNUmber and other values
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "No_error_msg"
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122591479038 | |  			TP04										|  	SERVIC		|TEST.AUTOMATION@CTFS.COM |
	@tag2
  Scenario Outline: testing with testing with valid cardNUmber and no emailId
    Given Post operation to hit enrollEStatement from TS2-service using valid "<cardNbr>" "<custId>" "<electronicVendorOptionId>" "<operatorId>" and "<emailId>"
   
    Then Validate enrollEstatement DSS api response status code as "200" and fault description as "No_error_msg"
    
    Examples: 
    | cardNbr          | custId	  | electronicVendorOptionId      | operatorId | emailId |
    | 5446122591479038 | |  			TP04										|  	SERVIC		| |
  

