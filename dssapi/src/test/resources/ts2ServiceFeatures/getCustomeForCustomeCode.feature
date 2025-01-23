#Author: Shkan
#
@tag
Feature: this feature is to test TS2 getCustomeerForCustomeCode api from TS2 Service

  @tag1
  Scenario Outline: Testing the api with valid card numbers and cust id
    Given post operation to getCustomerforCustomCode request "<operatorId>" "<cardNbr>" "<custId>"
    Then validate the getCustomerForCustomCode api status code as <statusCode> for the cardnumbers "<custId>" and statusMsg as "<statusMsg>"

    Examples: 
      | operatorId | cardNbr                           | custId              | statusCode | statusMsg |
      | SERVIC     | 5259950409060269,5259950409060269 | 000203361,000203361 |        200 | passed    |
      | SERVIC     | 5259950409060269,5446122599872697 | 000203361,000218131 |        200 | passed    |

  @tag1
  Scenario Outline: Testing the api with invalid cardnbr and custId
    Given post operation to getCustomerforCustomCode request "<operatorId>" "<cardNbr>" "<custId>"
    Then validate the getCustomerForCustomCode api status code as <statusCode> for the cardnumbers "<custId>" and statusMsg as "<statusMsg>"

    Examples: 
      | operatorId | cardNbr                           | custId              | statusCode | statusMsg |
      | SERVIC     | 5259950409060269,5259950409060264 | 000203361,000203364 |        200 | failed    |
      | SERVIC     |                  5259950409060212 |           000203361 |        200 |           |
      | SERVIC     |                  5259950409060269 |           000203333 |        200 |           |
      | SERVIC     |                                   |           000203333 |        200 |           |
      | SERVIC     |                  5259950409060269 |                     |        200 |           |
      | SERVIC     |                                   |                     |        200 |           |
