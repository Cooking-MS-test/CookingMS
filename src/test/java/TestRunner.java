import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".idea/Features",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE ,
        glue = "stepDefinitions"

)
public class TestRunner {
}
