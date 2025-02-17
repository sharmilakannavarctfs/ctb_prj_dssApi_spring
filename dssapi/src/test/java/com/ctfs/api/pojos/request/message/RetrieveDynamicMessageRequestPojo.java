package com.ctfs.api.pojos.request.message;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveDynamicMessageRequestPojo {
    private String targetIdentifier;
    private String messageId;
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            return "Error converting to JSON: " + e.getMessage();
        }
    }
}
