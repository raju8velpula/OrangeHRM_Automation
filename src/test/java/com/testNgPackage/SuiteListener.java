package com.testNgPackage;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("SuiteListener onFinish ");
    }

    @Override
    public void onStart(ISuite suite) {
        System.out.println("SuiteListener onStart");
    }
}
