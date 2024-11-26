package com.ctfs.api.step;

import com.ctfs.api.config.AbstractTestDefinition;
import com.ctfs.api.model.response.LocationWeatherRootResponse;
import com.ctfs.api.service.WeatherService;
import com.ctfs.api.utils.DashProfileManagerUtils;
import com.ctfs.common.service.StepDefinitionDataManager;
import com.google.gson.Gson;
import com.springbootjdbc.com.spring.jdbc.creditProfile.ProfileManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Step extends AbstractTestDefinition {

    private final Logger log = LoggerFactory.getLogger( Step.class);
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;

    @Autowired
    private DashProfileManagerUtils dpm ; 
    @Then("^The weather for (.*) should be returned$")
    public void theWeatherForDublinShouldBeReturned(String location) {
        final SoftAssertions softAssertions = new SoftAssertions();
        final LocationWeatherRootResponse locationWeatherRootResponse = new Gson().fromJson(((Response) stepDefinitionDataManager.getStoredObjectMap().get("class")).getBody().asString(), LocationWeatherRootResponse.class);
        log.info("Verifying the Response location : " + locationWeatherRootResponse.getName() + ", is equal to the expected location : " + location);
        softAssertions.assertThat(locationWeatherRootResponse.getName()).as("Expected the weather forecast to be for : " + location).withFailMessage("But it was for : " + locationWeatherRootResponse.getName()).isEqualToIgnoringCase(location);
        softAssertions.assertAll();
    }

    @Given("^The user has requested the weather for (.*)$")
    public void theUserHasRequestedTheWeatherForDublin(String location) throws Throwable {
        log.info("The user makes an request for the weather in : " + location);
        dpm.initializeTestProfile("group=ApiGeneric");
        System.out.println(dpm.getAccountID());
        weatherService.getWeatherForLocation(location);
    }
}