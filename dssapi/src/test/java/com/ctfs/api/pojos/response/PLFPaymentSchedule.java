package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class PLFPaymentSchedule {
    private String paymentFrequency;
    private String paymentMethod;
    private String paymentTypes;
    private String property;
    private String scheduleId;

}
