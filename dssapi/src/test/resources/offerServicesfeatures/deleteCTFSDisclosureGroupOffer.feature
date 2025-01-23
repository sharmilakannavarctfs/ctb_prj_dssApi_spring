#Author: SHKAN
## (Comments)
#this api deleted the record frpm pduser. Before that we run addOrupdateNBORepricingMatrix adds if not present or updates the record on CTFSNBOREPRICINGMATRIX table on pduser
@tag
Feature: this api adds if not present or updates the record on CTFSDISCLOSUREGROUPOFFERS table on pduser

  @tag2
  Scenario Outline: Adding the updating the records in CTFSDISCLOSUREGROUPOFFERS table of pduser then deleting
    Given post operation to addorUpdatectfsDisclosureGroupOffers request "<id>" "<offercode>"
    Then validate the addorUpdatectfsDisclosureGroupOffers api status code as "<statusCode>"
    Then delete the disclosureGrp record created with "<id>" and get the status as "200"

    Examples: 
      | id                                   | offercode | statusCode |
      | 2b2c8902-c92d-4a03-ad75-879e035fc380 | OMZ_PQ3   |        200 |

  #@tag2
  #Scenario Outline: Deleting the id that is not there in the table
    #Then delete the ctfsDisclosureGroupOffers record created with "<id>" and get the status as "<statusCode>"
#
    #Examples: 
      #| id                                   | statusCode |
      #| 2b2c8902-c92d-4a03-ad75-879e035fc380 |        200 |
      #|                                      |        200 |
