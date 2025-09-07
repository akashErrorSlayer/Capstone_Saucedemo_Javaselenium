package com.example.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.example.stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"
        		},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)   // 👈 force sequential execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
