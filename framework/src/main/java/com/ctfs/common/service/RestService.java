package com.ctfs.common.service;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import tech.grasshopper.filter.ExtentRestAssuredFilter;

import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    public RequestSpecification getRequestSpecification() {
        return given().header("Content-Type", "application/json").filter( new ExtentRestAssuredFilter());
    }
    public RequestSpecification getRequestSpecification_controller(Map<String, String> addtionalHeaders) {
    	return given().header("Content-Type","application/json")
    			.header("Authorization","Basic QTQxbk1uMnNnKnk5IUcw")
    			.filter( new ExtentRestAssuredFilter());
    }
}
