package com.ctfs.api.pojos.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCustomerRequestPojo {
	private String CustomerId;
	private List<Communication> communication;
	private List<GarnishmentReferences> garnishmentReferences;
	private List<DisplayNames> displayNames;
	private List<CustomerNames> customerNames;
	private List<CommunicationDevices> communicationDevices;
	private List<FaxIds> faxIds;
	private List<OfficePhoneNumbers> officePhoneNumbers;
	private List<Streets> streets;
	private List<Addresses> addresses;
	private List<AddressCities> addressCities;
	private List<Countries> countries;
	private List<LegalDetails> legalDetails;
}
