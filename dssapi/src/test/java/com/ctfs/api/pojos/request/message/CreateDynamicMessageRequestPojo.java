package com.ctfs.api.pojos.request.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateDynamicMessageRequestPojo {
    private String targetIdentifier;
    private String priority;
    private String state;
    private String messageText_en;
    private String messageText_fr;
    private String expiryDate;

    @Override
    public String toString() {
        try {
            // Convert the object to JSON using Jackson's ObjectMapper
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            // Return a fallback if JSON serialization fails
            return "Error converting to JSON: " + e.getMessage();
        }
    }
}
