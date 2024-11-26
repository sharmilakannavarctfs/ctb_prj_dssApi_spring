package com.ctfs.common.polaris;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PolarisLoyatyTransaction {

	public Date transDate;
	public String transType;
	public String banner;
	public String store;
	public String city;
	public String amount;
	public String eCTM;
	public String loyaltyCard;
	public String transaction;
	public String operator;
	
	
}
