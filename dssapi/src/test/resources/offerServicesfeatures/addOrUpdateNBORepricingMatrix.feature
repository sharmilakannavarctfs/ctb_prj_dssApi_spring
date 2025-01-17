#Author: SHKAN
## (Comments)
#this api adds if not present or updates the record on CTFSNBOREPRICINGMATRIX table on pduser
@tag
Feature: this api adds if not present or updates the record on CTFSNBOREPRICINGMATRIX table on pduser

  @tag2
  Scenario Outline: Adding the updating the records in CTFSNBOREPRICINGMATRIX table of pduser
    Given post operation to addorUpdateANBORepricingMatrix request "<alternateDesc>" "<clientProdCode>" "<currentPrimaryDisclosure>" "<cycle>" "<id>" "<newAltDisclosure>" "<newPrimaryDisclosure>" "<offerCode>" "<offerStatus>" "<primaryDescr>" "<productChangeOption>" "<promoId>" "<retentionId>" "<tempDuration>" "<treatmentCode>" "<treatmentDescription>" "<tsysLetterId>"
    Then validate the status code as "200"

    Examples: 
      | alternateDesc | clientProdCode | currentPrimaryDisclosure | cycle | id                                   | newAltDisclosure | newPrimaryDisclosure | offerCode | offerStatus | primaryDescr | productChangeOption | promoId | retentionId | tempDuration | treatmentCode | treatmentDescription | tsysLetterId |
      | alternateDesc |         001234 | M2599151                 | cycle | 1a1b14e2-2790-4000-8f6b-d0c401de5d41 | newAltD          | M2599151             |       916 | P           | primaryDescr | prCO                | promoId | PM          |           10 | OMJ_ROC_T     | treatmentDescription | OMJCON       |
      | alternateDesc |         001234 | M2599151                 | cycle |                                 1234 | newAltD          | M2599151             |       916 | P           | primaryDescr | prCO                | promoId | PM          |           10 | OMJ_ROC_T     | treatmentDescription | OMJCON       |
      |               |         001434 | M2599154                 | cycle | 1a1b14e2-2790-4000-8f6b-d0c401de5d41 | new              | M2599141             |       926 | D           | primaryDcr   | pCO                 | promo   | AM          |           11 | OMJ_RDC_T     | TD                   | OMJCAN       |
      |               |                |                          | cycle | 1a1b14e2-2790-4000-8f6b-d0c401de5d41 | new              | M2599141             |       926 | D           | primaryDcr   | pCO                 | promo   | AM          |           11 | OMJ_RDC_T     | TD                   | OMJCAN       |
