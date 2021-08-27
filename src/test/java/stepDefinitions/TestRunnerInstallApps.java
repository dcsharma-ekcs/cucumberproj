package stepDefinitions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/StoreApps/installApp.feature"
        ,glue={"stepDefinitions"}
        ,monochrome = true
        ,plugin = {"pretty", "html:target/HtmlReports/htmlReports.html"
        ,"json:target/JsonReports/jsonReports.json"
        ,"junit:target/JunitReports/junitReports.xml"}
)

public class TestRunnerInstallApps {
}
