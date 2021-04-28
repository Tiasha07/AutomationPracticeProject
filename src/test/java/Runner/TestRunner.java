package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features = {"src/test/resources/features/CustomerScenario.feature"},
		glue= {"com.example.StepDefinitions"}
		)

public class TestRunner extends AbstractTestNGCucumberTests {
	
}
