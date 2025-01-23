#Author: SHKAN
## (Comments)
#this api retrieves the records form CTFSDISCLOSUREGROUPDESC from pduser db. 

@tag
Feature: this api retrieves the record on CTFSDISCLOSUREGROUPDESC table on pduser
retrievdisclosure
  @tag2
  Scenario Outline: Adding the updating the records in CTFSDISCLOSUREGROUPDESC table of pduser then deleting
    Given post operation to retrieveDisclosureGroup request
    Then validate the retrieveDisclosureGroup api status code as "200"

