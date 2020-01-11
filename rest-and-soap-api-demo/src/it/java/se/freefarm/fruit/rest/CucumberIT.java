package se.freefarm.fruit.rest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/it/resources/features",
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report/html",
                "junit:target/surefire-reports/cucumber.xml"
        },
        glue = "se.freefarm.fruit.rest"
)
public class CucumberIT {
    public CucumberIT() {}
}