package com.ctfs.api.pojos.request.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteMessageRequestPojo {
    private String targetIdentifier;
    private String messageId;
}
