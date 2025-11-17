import io.cucumber.testng.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

import java.util.List;


@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/main/java/com/features/",
        glue = "com.stepDefinitions"
)
public class TestRunner   extends AbstractTestNGCucumberTests {



}
