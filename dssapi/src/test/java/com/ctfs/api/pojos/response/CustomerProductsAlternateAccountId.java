package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class CustomerProductsAlternateAccountId {
	private ArrayList<String> alternateAccountId;
	private ArrayList<String> alternateAccountType;

}
