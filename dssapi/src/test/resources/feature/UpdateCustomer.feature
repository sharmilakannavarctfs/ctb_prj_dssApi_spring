Feature: updateCustomer DSS api
	
  Scenario Outline: updateCustomer DSS api with valid communication details for Work Phone
    Given Perform post operation to hit updateCustomer DSS api using "1001006" as customerId and update for "<field>"
    Then Validate updateCustomer DSS api response status code as "200"

    Examples: 
      | field        |
      | HOME PHONE   |
      | WORK PHONE   |
      | MOBILE PHONE |
      | EMAIL        |
      | FAX          |

	@TestRun
  Scenario Outline: UpdateCustomer DSS api with valid values
    Given Perform post operation to hit updateCustomer DSS api using "1001006" as customerId and update values of "<field>"
    Then Validate updateCustomer DSS api response status code as "200"

    Examples: 
      | field                |
      #|idDocument	|                  
      #| garnishmentReferences |
      #| displayNames          | 
      #| customerNames         |
      #| communicationDevices  |  
      #| faxIds                |
      #| officePhoneNumbers    |
      #| streets               |
      #|addresses		|
      #|addressCities|
      #|countries|
      |legalDetails|
      
