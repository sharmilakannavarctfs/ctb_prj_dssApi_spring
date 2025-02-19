package com.ctfs.api.pojos.response.ts2response.retrieveCustomerAddressAvailabilityResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressAvailability {
    private String cardNbr;
    private String custId;
    private String addressAvailable;
}
