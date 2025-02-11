package com.ctfs.api.pojos.response.message;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class RetrieveMessageResponsePojo {
    @JsonProperty("messageCount")
    private String messageCount;
    @JsonProperty("messages")
    private ArrayList<MessagesPojo> messages;
}
