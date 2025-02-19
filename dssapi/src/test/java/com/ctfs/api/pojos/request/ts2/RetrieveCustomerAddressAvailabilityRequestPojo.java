package com.ctfs.api.pojos.request.ts2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class RetrieveCustomerAddressAvailabilityRequestPojo {
    private List<CustomerDetailsForAddressAvailabilityPojo> customerDetails;
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            return "Error converting to JSON: " + e.getMessage();
        }
    }
}
