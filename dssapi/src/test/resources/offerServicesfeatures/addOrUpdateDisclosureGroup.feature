#Author: SHKAN
## (Comments)
#this api adds if not present or updates the record on CTFSDISCLOSUREGROUPDESC table on pduser
@tag
Feature: this api adds if not present or updates the record on CTFSDISCLOSUREGROUPDESC table on pduser

  @tag2
  Scenario Outline: Adding the updating the records in CTFSDISCLOSUREGROUPDESC  table of pduser
    Given post operation to addorUpdateDisclosureGroup request "<disclosureGroupCode>" "<disclosureGroupDesc>" "<welcomeKitFlag>"
    Then validate the addorUpdateDisclosureGroup api status code as "<statusCode>"

    Examples: 
      | disclosureGroupCode | disclosureGroupDesc     | welcomeKitFlag | statusCode |
      | MCBASE10            | P-APR 25.99/C-APR 25.99 | Y              |        200 |
      | MCBASE10            | P-APR 25.99/C-APR 25.99 | Y              |        200 |
      | MCBASE10            | P-APR 25.99/C-APR 100   | N              |        200 |
      |                     | P-APR 25.99/C-APR 100   | N              |        200 |
      | MCBASE10            |                         | N              |        200 |
      | MCBASE10            | P-APR 25.99/C-APR 100   |                |        200 |
      |                     |                         |                |        200 |
      | MCBASE10            | P-APR 25.99/C-APR 25.99 | Y              |        200 |
