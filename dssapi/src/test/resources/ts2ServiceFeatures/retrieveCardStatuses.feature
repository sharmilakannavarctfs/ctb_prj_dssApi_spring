#Author: Veer
#
@tag
Feature: this feature is to test TS2 retrievecardstatuses api from TS2 Service

  @tag1
  Scenario Outline: Testing the api with valid account id ,card number and cust id
    Given post operation to retrievecardstatuses request "<operatorId>" "<accountId>" "<cardNbr>" "<custId>"
    Then validate the retrievecardstatuses api status code as <statusCode> for the cardnumbers "<custId>" and statusMsg as "<statusMsg>" "<status>"

    Examples: 
      | operatorId | accountId   | cardNbr          | custId    | statusCode | statusMsg | status |
      | SERVIC     | 00000710634 | 5446121000070107 | 000034735 |        200 | passed    |    000 |

  @tag2
  Scenario Outline: Testing the api with invalid cardnbr and custId
    Given post operation to retrievecardstatuses request "<operatorId>" "<accountId>" "<cardNbr>" "<custId>"
    Then validate the retrievecardstatuses api status code as <statusCode> for the cardnumbers "<custId>" and statusMsg as "<statusMsg>" "<status>"

    Examples: 
      | operatorId | accountId   | cardNbr          | custId    | statusCode | statusMsg | status |
      | SERVIC     | 00000710634 |  525995040906026 | 000034735 |        200 | failed    |    999 |
      | SERVIC     | 00000710634 | 5446121000070107 |       000 |        200 | passed    |    000 |
