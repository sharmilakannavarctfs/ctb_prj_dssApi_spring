package com.ctfs.api.pojos.response.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateDefinedMessageResponsePojo {
    private String id;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            return "Error converting to JSON: " + e.getMessage();
        }
    }
}
