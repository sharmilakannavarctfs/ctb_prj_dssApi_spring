package com.ctfs.api.pojos.request.offerServices;

import org.springframework.stereotype.Component;

import com.ctfs.api.pojos.request.AddOrUpdateNboRepricingMatrixRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Component
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddOrUpdateDisclosureGroupRequestPojo {
	private String disclosureGroupCode;
	private String disclosureGroup;
	private String disclosureGroupDesc;
	private String welcomeKitFlag;
}
