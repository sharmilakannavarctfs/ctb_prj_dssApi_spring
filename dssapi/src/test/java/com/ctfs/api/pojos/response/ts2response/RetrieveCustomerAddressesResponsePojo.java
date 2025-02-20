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
public class RetrieveCustomerAddressesResponsePojo {
    private String status;
    private String statusMsg;
    private String restartToken;

    @JsonProperty("customerAddresses")
    private List<CustomerAddress> customerAddressesList;

    @JsonProperty("faults")
    private List<Faults> faultsList;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
class CustomerAddress {
    private String addrCode;
    private boolean addrDefault;
    private String addressDesc;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private AddressTitle addressTitle;
    private AltAddress altAddr;
    private String cbrAddr;
    private String city;
    private String code1Override;
    private String countryCode;
    private String countyName;
    private String dateAddrAdded;
    private String dateLastMnt;
    private String postalCode;
    private String stateProv;
}

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
class AddressTitle {
    private String closingSalutationDesc;
    private String openingSalutationDesc;
    private String titleCode;
    private String titleDesc;
}

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
class AltAddress {
    private String altAddrCode;
    private List<Object> any;
    private String dateStart;
    private String dateStop;
    private String x100;
}