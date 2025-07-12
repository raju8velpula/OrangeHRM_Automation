package com.testNgPackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {

        System.out.println("onstart");
    }


    @Override
    public void onTestStart(ITestResult result) {

        System.out.println(result.getName());
    }
}
