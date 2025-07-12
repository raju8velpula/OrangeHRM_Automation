import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/main/java/com/features/",
        glue = "com.stepDefinitions",
        tags = " @rajuv"
)
public class TestRunner {



}
