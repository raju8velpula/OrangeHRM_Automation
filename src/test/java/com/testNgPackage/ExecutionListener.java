package com.testNgPackage;

import org.testng.IExecutionListener;

public class ExecutionListener implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        System.out.println("onExecutionStart");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("onExecutionFinish");
    }
}
