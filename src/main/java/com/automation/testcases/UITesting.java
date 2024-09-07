package com.automation.testcases;

import com.automation.utils.Constants;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.automation.utils.Constants.DEVICES_AND_RES_MAP;
import static com.automation.utils.DriverManger.BROWSERS;
import static com.automation.utils.DriverManger.getDriver;

public class UITesting {
    public void initiate() {
        WebDriver driver = null;
        try {
            for (DriverManagerType browserType : BROWSERS) {
                for (String device : DEVICES_AND_RES_MAP.keySet()) {
                    for (String resolution : DEVICES_AND_RES_MAP.get(device)) {
                        String[] sizes = resolution.split("x");
                        int width = Integer.parseInt(sizes[0]);
                        int height = Integer.parseInt(sizes[1]);
                        driver = getDriver(browserType, device, width, height);
                        for (String url : Constants.TEST_CASE_ONE_ULRS) {
                            driver.get(url);
                            Thread.sleep(3000);
                            File folder = new File(device+"/"+resolution);
                            if (!folder.exists()) {
                                folder.mkdirs(); // Create directories if needed
                            }
                            // Capture screenshot
                            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                            File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

                            // Generate filename with timestamp
                            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                            String fileName = folder.getPath() + "/screenshot_"+browserType.getBrowserName() +"_"+ timestamp + ".png";

                            // Save screenshot to a file
                            File destination = new File(fileName);
                            Files.copy(screenshot.toPath(), destination.toPath());

                            System.out.println("Screenshot saved as " + fileName);
                        }
                        driver.quit();
                        Thread.sleep(5000);
                    }
                }
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
