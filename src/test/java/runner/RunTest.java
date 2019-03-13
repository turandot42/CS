package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"},
        features = "src/test/resources/features",
        glue = {"step_definitions"},
        tags = "@Test",
        dryRun = false
)
public class RunTest extends AbstractTestNGCucumberTests {
}
