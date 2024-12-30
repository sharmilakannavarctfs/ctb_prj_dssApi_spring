package com.ctfs.api.pojos.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AlternateAddress {

@JsonProperty("altAddrCode")
private String altAddrCode;
@JsonProperty("dateStart")
private String dateStart;
@JsonProperty("dateStop")
private String dateStop;
@JsonProperty("x100")
private String x100;
@JsonProperty("any")
private List<String> any;
}
