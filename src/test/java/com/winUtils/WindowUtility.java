package com.winUtils;

import com.aventstack.extentreports.ExtentTest;
import com.stepDefinitions.Hooks;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WindowUtility extends GenericUtility{

    /* Window Application */
    public  static ExtentTest logger= Hooks.extentTest;
    public static WindowsDriver windriver;
    public static WebDriver webDriver;

    public  void start()
    {
        try
        {
            Desktop desktop = Desktop.getDesktop();

            File file = new File("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe");

            /* Check if there is support for Desktop or not */
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }

            if (file.exists())
            {
                System.out.println("Open WinAppDriver.exe");
                desktop.open(file);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Encountered Exception\n");
            throw new RuntimeException(e);
        }
    }

    public  void stop()
    {
        try
        {
            ProcessBuilder processBuilder =new ProcessBuilder("taskkill ","/f","/IM","WinAppDriver.exe");
            processBuilder.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public void launchWinApplication(String appName) throws IOException, URISyntaxException {

        DesiredCapabilities capability = new DesiredCapabilities();
        closeWinApplication();
//        start();
        capability.setCapability("app",readProperty(appName));
        windriver= new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
//        windriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void closeWinApplication(){

        try
        {
            ProcessBuilder processBuilder =new ProcessBuilder("taskkill ","/f","/IM","eclipse.exe");
            processBuilder.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void click( By by) throws InterruptedException {
        Thread.sleep(10000);
        Actions action = new Actions(windriver);
//        WebElement menuItem = windriver.findElement(by);
//        action.click(menuItem).perform();
    }
    public void enterText( By by, String text) throws InterruptedException {
        Thread.sleep(10000);
        Actions action = new Actions(windriver);
//        WebElement menuItem = windriver.findElement(by);
//        action.sendKeys(menuItem, text).perform();
    }




    }
