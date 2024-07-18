package com.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = "com.steps",
    tags = "@searchTest",
    plugin = {"pretty","html:target/report-html","json:target/cucumber-reports/Cucumber.json"},
    monochrome = true
)
public class TestRunner {
}
