package com.ctfs.api.pojos.response.message;

import lombok.Data;

@Data
public class MessagesPojo {
    private String id;
    private String messageText_en;
    private String messageText_fr;
    private String createDateTime;
    private String expiryDate;
    private String state;
    private String priority;
    private String messageDefinitionId;
}