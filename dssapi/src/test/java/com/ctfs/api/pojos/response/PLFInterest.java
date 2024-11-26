package com.ctfs.api.pojos.response;

import lombok.Data;

@Data
public class PLFInterest {
    private String dayBasis;
    private String description;
    private String floatingIndex;
    private String interestId;
    private String interestType;
    private String marginOperand;
    private int marginRate;
    private String marginType;
    private String rateTierType;

}
