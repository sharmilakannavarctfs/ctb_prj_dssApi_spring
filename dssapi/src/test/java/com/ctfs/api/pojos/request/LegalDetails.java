package com.ctfs.api.pojos.request;

import lombok.Data;

@Data
public class LegalDetails {
	private String legalId;
	private String legalDocumentName;
	private String legalHolderName;
	private String legalIssueAuthorisedDate;
	private String legalIssueCountry;
	private String legalIssueDate;
	private String legalExpiredDate;
}
