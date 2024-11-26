package com.ctfs.api.pojos.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class PaymentHistory {
	
	private Integer pastDue30Nbr;
	private Integer pastDue60Nbr;
	private Integer pastDue90Nbr;
	private Integer pastDue120Nbr;
	private Integer pastDue150Nbr;
	private Integer pastDue180Nbr;
	private Integer pastDue210Nbr;
	private Integer pastDue240Nbr;
	private Integer pastDue270Nbr;
	private Integer pastDue300Nbr;
	private Integer pastDue330Nbr;
	private Integer pastDue330PlusNbr;
	private String  currentCyclePaymentStatus;
	private String  currentCyclePaymentTiming;
	private Integer paymentsCTDNbr;
	private BigDecimal paymentsCTDAmt;
	private Integer paymentsYTDNbr;
	private BigDecimal paymentsYTDAmt;

}
