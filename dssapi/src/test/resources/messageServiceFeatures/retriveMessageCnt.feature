#Author: veesu
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser
    Given post operation to retriveMessagesCnt request with targetidentifier as "<targetIdentifier>"
    Then validate the status code and message count of the post operation as <statusCode>

    Examples: 
      | targetIdentifier                     | statusCode |
      | 7c1736ec-665b-4f9f-aac6-99302c3d7ed4 |        200 |

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser
    Given post operation to retriveMessagesCnt request with Invalid and NULL targetidentifier as "<targetIdentifier>"
    Then validate the status code and message count for Invalid and NULL of the post operation as <statusCode>

    Examples: 
      | targetIdentifier                    | statusCode |
      | 59faa0fa-c46d-4ac6-967d-dafeb569650 |        200 |
      |                                     |        200 |

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser
    Given post operation to retriveMessagesCnt request for Invalid payload
    Then validate the status code for invalid payload message value for the post operation as <statusCode>

    Examples: 
      | statusCode |
      |        501 |
