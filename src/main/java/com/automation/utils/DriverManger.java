package com.automation.utils;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.config.DriverManagerType.*;


public class DriverManger {

    public static List<DriverManagerType> BROWSERS = Arrays.asList(CHROME, FIREFOX); // Safari can be added in mac os

    public static ChromeOptions getChromeOptionsForMobileDevice(int width, int height) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", width); // Custom width
        deviceMetrics.put("height", height); // Custom height
        deviceMetrics.put("deviceScaleFactor", 3.0);
        deviceMetrics.put("mobile", true);
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        return options;
    }

    public static FirefoxOptions getFireFoxOptionsForMobileDevice(int width, int height) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=" + width); // Custom width
        options.addArguments("--height=" + height); // Custom height
        return options;
    }
    public static WebDriver getDriver(DriverManagerType type) {
        WebDriver driver = null;
        switch (type) {
            case CHROME:
                driver = new ChromeDriver();
            break;
            case FIREFOX:
                driver = new FirefoxDriver();
            break;
            case SAFARI:
                driver = new SafariDriver();
            break;
            default:
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static WebDriver getDriver(DriverManagerType type, String device, int width, int height) {
        WebDriver driver = null;
        switch (type) {
            case CHROME:
                if (device.equals("Mobile")) driver = new ChromeDriver(getChromeOptionsForMobileDevice(width, height));
                else driver = new ChromeDriver();
                break;
            case FIREFOX:
                if (device.equals("Mobile"))
                    driver = new FirefoxDriver(getFireFoxOptionsForMobileDevice(width, height));
                else driver = new FirefoxDriver();
                break;
            case SAFARI:
                if (device.equals("Mobile")) {
                    driver = new SafariDriver();
                    driver.manage().window().setSize(new Dimension(width, height));
                } else driver = new SafariDriver();
                break;
            default:
        }
        if (device.equals("Desktop")) {
            driver.manage().window().setSize(new Dimension(width, height));
        }
        return driver;
    }
}
