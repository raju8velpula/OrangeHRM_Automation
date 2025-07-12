package com.stepDefinitions;

import com.aventstack.extentreports.Status;
//import com.winUtils.BaseUtility;
import com.winUtils.WindowUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class TrainingMode extends WindowUtility {
//    public static ExtentTest  logger=Hooks.extentTest;

    WindowUtility winUtil=new WindowUtility();
    @When("Start the transaction")
    public void ftrrt(){

//        System.out.println(ThreadLocalPickleStep.get().getStepText());
        logger=logger.createNode("Start the transaction");
        logger.log(Status.INFO, "Clicked on Start Button");

    }

    @And("Skip the Donation")
    public void skipTheDonation() {
        logger=logger.createNode("Skip the Donation");

        logger.log(Status.INFO, "Clicked on Skip Button");

    }

    @Then("Verify")
    public void startTheReport() {
        logger=logger.createNode("g7gc7gfa7gdagd7a");
        logger.log(Status.PASS, "Verify Welcome on attract screen");

    }

    @And("Click on Launch button")
    public void clickOnLaunchButton() {
        logger=logger.createNode("Click on Launch button");
        try {
            winUtil.click(By.name("Launch"));
            logger.log(Status.PASS, "click on launch button successful");
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Status.FAIL, e.getMessage());
        }

    }

    @Then("Create Maven project")
    public void createMavenProject() {

        logger=logger.createNode("Create Maven project");
        try {
            Thread.sleep(20000);

            winUtil.click(By.name("File"));
            winUtil.click(By.name("New\tAlt+Shift+N"));
            winUtil.click(By.name("Maven Project"));
            winUtil.click(By.name("&Next"));
            winUtil.enterText(By.name("Filter"),"org.apache.maven.archetype");

            logger.log(Status.PASS, "click on launch button successful");
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Status.FAIL, e.getMessage());
        }
    }


}
