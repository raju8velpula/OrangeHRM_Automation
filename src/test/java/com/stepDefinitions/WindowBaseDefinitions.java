package com.stepDefinitions;

import com.aventstack.extentreports.Status;
import com.winUtils.WindowUtility;
import io.cucumber.java.en.When;

import java.io.IOException;

public class WindowBaseDefinitions extends  WindowUtility{

    WindowUtility baseUtil =new WindowUtility();
    @When("Launch Calculator Application")
    public void launchCalculatorApp() throws IOException {
        logger.createNode("Launch Calculator Application");
        try {
            baseUtil.launchWinApplication("calculator");
            logger.log(Status.PASS, "Launched Calculator Successfully");
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Status.FAIL, e.getMessage());
        }

    }

    @When("Launch Eclipse Application")
    public void launchEclipseApp() throws IOException {
        logger=logger.createNode("Launch Calculator Application");
        try {
            baseUtil.launchWinApplication("eclipse");
            logger.log(Status.PASS, "Launched Eclipse Successfully");
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Status.FAIL, e.getMessage());
        }

    }



}
