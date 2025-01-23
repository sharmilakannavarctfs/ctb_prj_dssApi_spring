#Author: SHKAN
## (Comments)
#this api adds if not present or updates the record on CTFSDISCLOSUREGROUPOFFERS table on pduser
@tag
Feature: this api adds if not present or updates the record on CTFSDISCLOSUREGROUPOFFERS table on pduser

  @tag2
  Scenario Outline: Adding the updating the records in CTFSDISCLOSUREGROUPDESC  table of pduser
    Given post operation to addorUpdatectfsDisclosureGroupOffers request "<id>" "<offercode>"
    Then validate the addorUpdatectfsDisclosureGroupOffers api status code as "<statusCode>"

    Examples: 
      | id                                   | offercode | statusCode |
      | 2b2c8902-c92d-4a03-ad75-879e035fc380 | OMZ_PQ3   |        200 |
      |                               000345 | OMZ_PQ3   |        200 |
      |                               000345 |       123 |        200 |
      |                                      | OMZ_PQ3   |        200 |
      |                               000345 |           |        200 |
      |                                      |           |        200 |
      | 2b2c8902-c92d-4a03-ad75-879e035fc380 | OMZ_ROC   |        200 |
