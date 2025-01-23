#Author: SHKAN
## (Comments)
#this api retrieves the records form CTFSDISCLOSUREGROUPOFFERS from pduser db. 

@tag
Feature: this api retrieves the record on CTFSDISCLOSUREGROUPOFFERS table on pduser
retrievdisclosure
  @tag2
  Scenario Outline: Adding the updating the records in CTFSDISCLOSUREGROUPOFFERS table of pduser then deleting
    Given post operation to retrieveCTFSDisclosureGroupOffers request
    Then validate the retrieveCTFSDisclosureGroupOffers api status code as "200"

