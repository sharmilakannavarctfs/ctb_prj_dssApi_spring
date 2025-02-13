package com.ctfs.api.pojos.response.message;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Message {
	
		private String id;
		private String messageText_en;
		private String messageText_fr;
		private String createDateTime ;
		private String expiryDate ;
		private String state;
		private String priority;
		private String messageDefinitionId;

}



