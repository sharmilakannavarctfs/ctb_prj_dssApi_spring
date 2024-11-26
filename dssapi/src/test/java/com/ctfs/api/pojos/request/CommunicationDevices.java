package com.ctfs.api.pojos.request;

import lombok.Data;

@Data
public class CommunicationDevices {
	private String phoneNumber;
	private String smsNumber;
	private String email;
	private String communicationType;
	private String preferredChannel;
}
