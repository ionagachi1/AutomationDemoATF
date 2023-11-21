package testRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs", "utilities"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/reports/TestReport.html"
        },
        tags = "@run"
)
public class TestRunner {
}
