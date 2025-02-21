package com.ctfs.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {
//                "src\\test\\resources\\feature\\GetAccountTest.feature",
//                "src\\test\\resources\\feature\\EStatementDeenrollmentTest.feature",
//                "src\\test\\resources\\feature\\EvaluateCreditLimit.feature",
                "src\\test\\resources\\feature\\EnrollEStatement.feature"
                
		}, dryRun = false/* ,tags = "tag2" */,
        		plugin = { "summary", "json:target/cucumber-json.json",
        "tech.grasshopper.AllureCucumberMappingPlugin:target/cucumber-allure.json" })
public class RunnerTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

