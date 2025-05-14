package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
//        tags = "@test_search",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests{




}
