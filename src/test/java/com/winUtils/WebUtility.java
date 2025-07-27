package com.winUtils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.stepDefinitions.Hooks;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WebUtility extends  GenericUtility{
    public  static ExtentTest logger= Hooks.extentTest;
    public static WebDriver driver =Hooks.driver;
    public void luanchBrowser(ExtentTest logger) throws IOException {
        boolean headless=Boolean.valueOf(readProperty("headless"));
        boolean insecureCerts=Boolean.valueOf(readProperty("acceptInsecureCerts"));
        String browser=readProperty("browser");
        switch (browser){
            case "chrome":


                ChromeOptions options=new ChromeOptions();
                if (headless) {
                    options.addArguments("headless");
                }
                options.setAcceptInsecureCerts(insecureCerts);
                Map<String, String> chromePrefs= new HashMap<>();
                chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"downloads");
                options.setExperimentalOption("prefs", chromePrefs);
               WebDriverManager.chromedriver().setup();
                               driver=new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();


        }
    }

    public void closeBrowser(){
        driver.close();
    }

    public boolean launchApplication() throws IOException {
        try {
            driver.get(readProperty("appUrl"));
            return true;
        } catch (Exception e) {
            addFailureToReport(e.getMessage());
            return false;

        }
        }
    public boolean enterTextIntoTextbox(String locator, String textToEnter) throws IOException {
       try {
           WebElement element = getElement(locator);
           element.sendKeys(textToEnter);
           return true;
       } catch (Exception e) {
           addFailureToReport(e.getMessage());
           return false;
       }
    }

    public void clickOElement(String locator){
        WebElement element=getElement(locator);
        element.click();

    }
    public WebElement getElement(String locatorData){
        String[] data=locatorData.split("==");
        String locator=data[1];
        String locatorType=data[0];
        switch (locatorType.toLowerCase()){
            case "xpath" :
                return driver.findElement(By.xpath(locator));
            case "css" :
                return driver.findElement(By.cssSelector(locator));
            case "name" :
                return driver.findElement(By.name(locator));
            default:
                return null;
        }
    }

    public void asertData(boolean flag, String message){

        if(flag){
            GlobalVariables.logger.log(Status.PASS, message+" is successful");
        }else{
            GlobalVariables.logger.log(Status.FAIL,message+" is failed" );
        }
        Assert.assertEquals(true, flag);
    }

    public void addFailureToReport(String message) throws IOException {
        File sourcePath = ((TakesScreenshot) WebUtility.driver).getScreenshotAs(OutputType.FILE);
        File destFile=new File("./Reports/Errors/sreenshot" + Math.random()+".png");
        FileUtils.copyFile(sourcePath,destFile );
        GlobalVariables.logger.log(Status.PASS, message).addScreenCaptureFromPath(destFile.getAbsolutePath());
    }

}
