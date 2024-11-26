package com.ctfs.api.pojos.response;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@Data
public class ProductDetailsResponsePojo {
    private ArrayList<ProductDetail> productDetails;
}
