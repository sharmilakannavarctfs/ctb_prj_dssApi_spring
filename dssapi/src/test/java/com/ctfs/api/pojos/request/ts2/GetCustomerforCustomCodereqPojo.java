package com.ctfs.api.pojos.request.ts2;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCustomerforCustomCodereqPojo {
	private String operatorId;
	private List<Ts2custDetailInput> ts2custDetailInputs;
}

