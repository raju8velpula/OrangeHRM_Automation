package com.stepDefinitions;

import com.winUtils.WindowUtility;
import io.cucumber.java.en.Given;

import java.io.IOException;
import java.net.URISyntaxException;

public class Blender extends  WindowUtility{
    //
    @Given("Launch blender app")
    public void  launchBlenderApp() throws IOException, URISyntaxException {
        launchWinApplication("calculator");
    }
}
