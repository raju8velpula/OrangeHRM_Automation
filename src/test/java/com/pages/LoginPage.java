package com.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.stepDefinitions.Hooks;
import com.winUtils.GlobalVariables;
import com.winUtils.WebUtility;
import io.appium.java_client.windows.WindowsDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static com.winUtils.GenericUtility.readProperty;

public class LoginPage extends  WebUtility{
 static WebDriver driver= WebUtility.driver;
 String userNameTxtByName="name==us`rname";
 String passwordTxtByCSS="css==[name='password']";
 String signinBtnByXpath="xpath==//button[@type='submit']";
    public void loginToApplication(String userName, String password) throws IOException {
        boolean launchAppFlag=launchApplication();
        asertData(launchAppFlag, "Launch application");
        boolean flag= enterTextIntoTextbox(userNameTxtByName,userName);
        asertData(flag, "entering text into username test box");
        enterTextIntoTextbox(passwordTxtByCSS, password);
        clickOElement(signinBtnByXpath);
    }

}
