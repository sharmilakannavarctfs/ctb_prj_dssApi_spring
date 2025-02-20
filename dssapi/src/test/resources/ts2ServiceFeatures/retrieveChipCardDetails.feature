#Author: Veer
#
@tag
Feature: this feature is to test TS2 retrievechipcarddetails api from TS2 Service

  @tag1
  Scenario Outline: Testing the retrievechipcarddetails api with valid account id ,card number and cust id and empty account id
    Given post operation to retrievechipcarddetails request "<operatorId>" "<accountId>" "<cardNbr>" "<custId>"
    Then validate the retrievechipcarddetails api status code as <statusCode> for the cardnumbers "<custId>" and statusMsg as "<statusMsg>" "<status>"

    Examples: 
      | operatorId | accountId   | cardNbr          | custId    | statusCode | statusMsg | status |
      | SERVIC     | 00000710634 | 5446121000070107 | 000034735 |        200 | passed    |    000 |
      | SERVIC     |             | 5446121000070107 | 000034735 |        200 | passed    |    000 |

  @tag1
  Scenario Outline: Testing the retrievechipcarddetails api with invalid cardnbr,card missing and invalid custid
    Given post operation to retrievechipcarddetails request "<operatorId>" "<accountId>" "<cardNbr>" "<custId>"
    Then validate the retrievechipcarddetails api status code as <statusCode> for the cardnumbers "<custId>" and statusMsg as "<statusMsg>" "<status>"

    Examples: 
      | operatorId | accountId   | cardNbr          | custId    | statusCode | statusMsg | status |
      | SERVIC     | 00000710634 | 1365747375757095 | 000034735 |        200 | failed    |    999 |
      | SERVIC     | 00000710634 |                  | 000034735 |        200 | failed    |    999 |
      | SERVIC     | 00000710634 | 5446121000070107 | 100003473 |        200 | warning   |    500 |
