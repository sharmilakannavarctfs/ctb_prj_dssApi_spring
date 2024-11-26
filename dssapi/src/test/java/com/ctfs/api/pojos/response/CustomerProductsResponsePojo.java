package com.ctfs.api.pojos.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Component
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerProductsResponsePojo {
	 private ArrayList<CustomerList> customerList;

}
