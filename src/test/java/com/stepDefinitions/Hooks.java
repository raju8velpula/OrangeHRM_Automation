package com.stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utils.RepotsUtility;
import com.winUtils.GenericUtility;
import com.winUtils.WebUtility;
import com.winUtils.WindowUtility;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    static String filePath;
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
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String day = df.format(date);
        File reportsDir = new File("./Reports");
        File[] listOfFiles = reportsDir.listFiles();
        assert listOfFiles != null;
        for (File oneFile : listOfFiles) {
            if (oneFile.isFile()) {
                String folderName = "";
                folderName = oneFile.getName().split("_")[1].split(" ")[0];
                new File("./Reports/" + folderName).mkdir();
                Path source = Paths.get(oneFile.getPath());
                Path target = Paths.get("./Reports/" + folderName + "/" + oneFile.getName());
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        String currentTime = String.valueOf(new Timestamp(new Date().getTime()));
        currentTime = currentTime.split("[.]")[0].replace(":", "");
         filePath=System.getProperty("user.dir") + "/Reports/ExecutionReport_" + currentTime + ".html";
        extentSparkReporter = new ExtentSparkReporter(filePath);
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
    public void afterStep(Scenario scenario) throws IOException {
        WindowUtility.logger = extentTest;
        WebUtility.logger = origextentTest;
        if (scenario.isFailed()) {
            // Capture screenshot
            File sourcePath = ((TakesScreenshot) WebUtility.driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            extentTest.log(Status.FAIL, "").addScreenCaptureFromPath(sourcePath.getPath());
            // Attach screenshot to report
            scenario.attach(fileContent, "image/png", "image");
        }
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
        new RepotsUtility().reportProperties(filePath);
    }
}
