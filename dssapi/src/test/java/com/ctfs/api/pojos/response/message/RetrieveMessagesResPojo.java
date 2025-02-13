package com.ctfs.api.pojos.response.message;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveMessagesResPojo {

	private String messageCount;
	private ArrayList<Message> messages;
}
