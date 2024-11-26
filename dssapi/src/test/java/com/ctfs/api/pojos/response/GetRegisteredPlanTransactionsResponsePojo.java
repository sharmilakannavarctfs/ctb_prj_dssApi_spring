package com.ctfs.api.pojos.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
public class GetRegisteredPlanTransactionsResponsePojo {

	List<RegisteredPlanTransactions> registeredPlanTransactions;
	
}
