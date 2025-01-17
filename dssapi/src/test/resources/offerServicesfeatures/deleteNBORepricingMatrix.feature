#Author: SHKAN
## (Comments)
#this api deleted the record frpm pduser. Before that we run addOrupdateNBORepricingMatrix adds if not present or updates the record on CTFSNBOREPRICINGMATRIX table on pduser
@tag
Feature: this api adds if not present or updates the record on CTFSNBOREPRICINGMATRIX table on pduser

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser then deleting
    Given post operation to addorUpdateANBORepricingMatrix request "<alternateDesc>" "<clientProdCode>" "<currentPrimaryDisclosure>" "<cycle>" "<id>" "<newAltDisclosure>" "<newPrimaryDisclosure>" "<offerCode>" "<offerStatus>" "<primaryDescr>" "<productChangeOption>" "<promoId>" "<retentionId>" "<tempDuration>" "<treatmentCode>" "<treatmentDescription>" "<tsysLetterId>" before deleting
    Then validate the status code as "200"
    Then delete the record created with "<id>" and get the status as "200"

    Examples: 
      | alternateDesc | clientProdCode | currentPrimaryDisclosure | cycle | id | newAltDisclosure | newPrimaryDisclosure | offerCode | offerStatus | primaryDescr | productChangeOption | promoId | retentionId | tempDuration | treatmentCode | treatmentDescription | tsysLetterId |
      | alternateDesc |         001234 | M2599151                 | cycle | na | newAltD          | M2599151             |       916 | P           | primaryDescr | prCO                | promoId | PM          |           10 | OMJ_ROC_T     | treatmentDescription | OMJCON       |

  @tag2
  Scenario Outline: Deleting the id that is not there in the table
    #Given post operation to addorUpdateANBORepricingMatrix request "<alternateDesc>" "<clientProdCode>" "<currentPrimaryDisclosure>" "<cycle>" "<id>" "<newAltDisclosure>" "<newPrimaryDisclosure>" "<offerCode>" "<offerStatus>" "<primaryDescr>" "<productChangeOption>" "<promoId>" "<retentionId>" "<tempDuration>" "<treatmentCode>" "<treatmentDescription>" "<tsysLetterId>"
    Then delete the record created with "<id>" and get the status as "<statusCode>"

    Examples: 
      | id                                   | statusCode |
      | 1a1b14e2-2790-4000-8f6b-d0c401de5d41 |        404 |
      |                                      |        503 |
