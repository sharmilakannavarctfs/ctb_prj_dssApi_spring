package com.ctfs.api.pojos.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class DemandInterestRateResponsePojo {
	private DemandInterestRatePeriodicInterestList periodicInterestList;

}
