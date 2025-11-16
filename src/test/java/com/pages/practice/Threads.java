package com.pages;

import org.openqa.selenium.*;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

 class SmartElementFinder {

    private WebDriver driver;
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public SmartElementFinder(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By... locators) {
        List<Callable<WebElement>> tasks = new ArrayList<>();

        for (By locator : locators) {
            tasks.add(() -> {
                try {
                    WebElement element = driver.findElement(locator);
                    return element; // If found, return
                } catch (NoSuchElementException e) {
                    return null;
                }
            });
        }

        try {
            // invokeAny returns as soon as one task returns non-null
            WebElement element = executor.invokeAny(tasks);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}

public class Threads{

}