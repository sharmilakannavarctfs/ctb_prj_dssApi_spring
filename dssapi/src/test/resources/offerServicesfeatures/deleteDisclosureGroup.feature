#Author: SHKAN
## (Comments)
#this api deleted the record frpm pduser. Before that we run addOrupdateNBORepricingMatrix adds if not present or updates the record on CTFSNBOREPRICINGMATRIX table on pduser
@tag
Feature: this api adds if not present or updates the record on CTFSNBOREPRICINGMATRIX table on pduser

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser then deleting
    Given post operation to addorUpdateDisclosureGroup request "<disclosureGroupCode>" "<disclosureGroupDesc>" "<welcomeKitFlag>"
    Then validate the addorUpdateDisclosureGroup api status code as "<statusCode>"
    Then delete the disclosureGrp record created with "<disclosureGroupCode>" and get the status as "200"

    Examples: 
      | disclosureGroupCode | disclosureGroupDesc     | welcomeKitFlag | statusCode |
      | MCBASE15            | P-APR 25.99/C-APR 25.99 | Y              |        200 |

  @tag2
  Scenario Outline: Deleting the id that is not there in the table
    Then delete the disclosureGrp record created with "<disclosureGroupCode>" and get the status as "<statusCode>"

    Examples: 
      | disclosureGroupCode | statusCode |
      | MCBASE17            |        404 |
      |                     |        404 |
      | null                |        404 |
