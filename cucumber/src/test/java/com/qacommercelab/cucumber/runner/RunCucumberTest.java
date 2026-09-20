package com.qacommercelab.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Entry point for the Cucumber BDD suite.
 *
 * Filter scenarios at runtime with a tag expression, for example:
 * mvn test -Dcucumber.filter.tags="@smoke and not @known-defect"
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.qacommercelab.cucumber",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
