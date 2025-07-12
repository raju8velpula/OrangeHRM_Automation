package com.stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.winUtils.GenericUtility;
import com.winUtils.WebUtility;
import com.winUtils.WindowUtility;
import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.core.backend.StepDefinition;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.*;

public class Hooks extends GenericUtility {

    private static LinkedHashMap<Integer, String> scenarios;
    private static HashMap<Integer, String> steps;
    public static WebDriver driver;
    WebUtility webUtil;
    static ExtentSparkReporter extentSparkReporter;
    static ExtentReports extentReports;
    public static ExtentTest extentTest;
    int threadID = 0;
    WebUtility webUtility;
    static ExtentTest origextentTest;

    public Hooks() {
        if (scenarios == null) scenarios = new LinkedHashMap<Integer, String>();
        if (steps == null) steps = new HashMap<Integer, String>();
    }

    private void addScenario(String scenario) {
        Thread currentThread = Thread.currentThread();
        Thread.activeCount();
        threadID = currentThread.hashCode();
        scenarios.put(threadID, scenario);
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
        extentSparkReporter.config().setReportName("Test Report");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        if (!readProperty("runEachTestInNewBrowser").equalsIgnoreCase("true")) {
            WebUtility webUtility = new WebUtility();
            webUtility.luanchBrowser(extentTest);
        }
    }

    @Before
    public void testSetUp(Scenario scenario) throws Exception {
        addScenario(scenario.getName());
        Thread.sleep(5000);
        extentTest = extentReports.createTest(scenarios.get(threadID));
        origextentTest = extentTest;

        if (readProperty("app").equalsIgnoreCase("web")) {
            webUtility = new WebUtility();
            if (readProperty("runEachTestInNewBrowser").equalsIgnoreCase("true")) {
                webUtility.luanchBrowser(extentTest);
            }
        }
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        WindowUtility.logger = extentTest;
        WebUtility.logger = origextentTest;
    }

    @After
    public void after() throws IOException {
        webUtil = new WebUtility();
        if (readProperty("runEachTestInNewBrowser").equalsIgnoreCase("true")) {
            webUtil.closeBrowser();
        }
    }

    @AfterAll
    public static void afterAll() throws IOException {
        extentReports.flush();
        if (!readProperty("runEachTestInNewBrowser").equalsIgnoreCase("true")) {
            WebUtility webUtil = new WebUtility();
            webUtil.closeBrowser();
        }
    }
}
