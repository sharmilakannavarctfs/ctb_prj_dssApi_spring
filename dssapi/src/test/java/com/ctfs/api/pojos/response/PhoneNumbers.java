package com.ctfs.api.pojos.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties

public class PhoneNumbers {
	
	@JsonProperty("type")
	private String type;
	@JsonProperty("number")
    private String number;
	@JsonProperty("consent")
    private String consent;
	@JsonProperty("lastMaintained")
    private Date lastMaintained;
	@JsonProperty("dateMaint")
	private String dateMaint;
	@JsonProperty("nbr")
	private String nbr;

}
