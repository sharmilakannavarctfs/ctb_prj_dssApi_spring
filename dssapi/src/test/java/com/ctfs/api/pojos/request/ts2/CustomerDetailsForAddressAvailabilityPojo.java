package com.ctfs.api.pojos.request.ts2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDetailsForAddressAvailabilityPojo {
    private String cardNbr;
    private String accountId;
    private String custId;
    private String operatorId;
    private String restartToken;
    private String statusCode;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            return "Error converting to JSON: " + e.getMessage();
        }
    }
}
