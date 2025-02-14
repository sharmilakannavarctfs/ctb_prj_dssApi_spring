#Author: veesu
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser
    Given post operation to retriveMessages request with targetidentifier as "<targetIdentifier>"
    Then validate the status code of the post operations as <statusCode>

    Examples: 
      | targetIdentifier                     | statusCode |
      | 7c1736ec-665b-4f9f-aac6-99302c3d7ed4 |        200 |

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser
    Given post operation to retriveMessagesCnt request with Invalid targetidentifier as "<targetIdentifier>"
    Then validate the status code and messages of the post operation as <statusCode>

    Examples: 
      | targetIdentifier                     | statusCode |
      | 25b0af31-4c36-4aaf-a229-b2f7f2ab2000 |        200 |
      |                                      |        200 |

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser
    Given post operation to retriveMessagesCnt request with Invalid payload
    Then validate the status code for invalid payload message value of the post operation as <statusCode>

    Examples: 
      | statusCode |
      |        501 |
