package com.ctfs.api.pojos.response.ts2response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveCustomerAddressAvailabilityResponsePojo {
    private String status;
    private String statusMsg;
    @JsonProperty("faults")
    private List<Faults> faultsList;
    @JsonProperty("addressAvailable")
    private List<AddressAvailability> addressAvailabilityList;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            return "Error converting to JSON: " + e.getMessage();
        }
    }
}

@Data
@Component
class Faults {
    private String code;
    private String desc;
}

@Data
@Component
class AddressAvailability {
    private String cardNbr;
    private String custId;
    private String addressAvailable;
}