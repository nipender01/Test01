package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
				 plugin={"json:target/jsonReports/cucumber-report.json",
						 "html:target/jsonReports/cucumber-report.html"},
				 glue={"stepDefinitions"})           //use tags="@AddPlace or @DeletePlace" for multiple tags

public class TestRunner {

}
