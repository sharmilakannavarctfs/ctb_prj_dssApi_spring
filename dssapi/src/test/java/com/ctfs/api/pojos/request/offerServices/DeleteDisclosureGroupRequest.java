package com.ctfs.api.pojos.request.offerServices;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NonNull;

@Data
@Component
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeleteDisclosureGroupRequest {
	private String disclosureGroupCode;
}
