package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class DemandPeriodicLineFeaturesResponsePojo {
    @JsonProperty("PeriodicLineFeatures") 
    private ArrayList<PeriodicLineFeature> periodicLineFeatures;

}
