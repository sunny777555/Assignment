package com.automation;

import com.automation.testcases.FunctionalTesting;
import com.automation.testcases.UITesting;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
    public static void main(String[] args) {

        // Setup WebDriverManager to automatically manage ChromeDriver binary
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.safaridriver().setup();
        UITesting testCaseOne = new UITesting();
//        testCaseOne.initiate();
        FunctionalTesting functionalTesting = new FunctionalTesting();
        functionalTesting.initiate();
    }


}