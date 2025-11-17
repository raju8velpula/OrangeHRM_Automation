package com.stepDefinitions;

import com.pages.LoginPage;
import com.winUtils.GlobalVariables;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class OrangeHrm {
//  logger= Hooks.extentTest;
    LoginPage loginPage=new LoginPage();
    @Given("Launch orange hrm application")
    public void launchOrangeHrmApplication() throws IOException, InterruptedException {
        GlobalVariables.logger=Hooks.extentTest.createNode("Launch orange hrm application");
        loginPage.loginToApplication("admin", "admin123");

    }

    @When("Enter User Name")
    public void enterUserName() {
        GlobalVariables.logger=Hooks.extentTest.createNode("Enter User Name");

    }

    @And("Enter Password")
    public void enterPassword() {
        GlobalVariables.logger=Hooks.extentTest.createNode("Enter Password");

    }

    @And("Click signin button")
    public void clickSigninButton() {
        GlobalVariables.logger=Hooks.extentTest.createNode("Click signin button");

    }

    @Then("Verify homepage")
    public void verifyHomepage() {
        GlobalVariables.logger=Hooks.extentTest.createNode("Verify homepage");
        Assert.assertEquals("raju", "Raju");
    }
}
