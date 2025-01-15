package com.ctfs.api.pojos.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NonNull;

@Data
@Component
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddOrUpdateNboRepricingMatrixRequest {
	private String alternateDesc;
	private String clientProdCode;
	private String currentPrimaryDisclosure;
	private String cycle;
	private String id;
	private String newAltDisclosure;
	private String newPrimaryDisclosure;
	private String offerCode;
	private String offerStatus;
	private String primaryDescr;
	private String productChangeOption;
	private String promoId;
	private String retentionId;
	private String tempDuration;
	private String treatmentCode;
	private String treatmentDescription;
	private String tsysLetterId;
}
