package com.ctfs.api.pojos.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NonNull;

@Data
@Component
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeleteNboRepricingMatrixRequest {
	private String id;
}
