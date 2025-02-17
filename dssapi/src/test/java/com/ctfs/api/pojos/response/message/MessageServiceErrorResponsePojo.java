package com.ctfs.api.pojos.response.message;

import lombok.Data;

@Data
public class MessageServiceErrorResponsePojo {
    private String statusCode;
    private String description;
}
