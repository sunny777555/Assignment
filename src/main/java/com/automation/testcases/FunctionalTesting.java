package com.automation.testcases;

import com.automation.utils.DriverManger;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.logging.Logger;

import static com.automation.utils.Constants.DEAL_URL;
import static com.automation.utils.Constants.MILLIS;


public class FunctionalTesting {

    Logger log = Logger.getLogger(FunctionalTesting.class.getName());

    public void initiate() {
        WebDriver driver = DriverManger.getDriver(DriverManagerType.CHROME);
        try {
            log.info("Running Test Case 2 - Functional Testing ");
            driver.get(DEAL_URL);
            // enter username
            WebElement userName = driver.findElement(By.name("username"));
            userName.sendKeys("prexo.mis@dealsdray.com");
            // enter password
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("prexo.mis@dealsdray.com");

            Thread.sleep(MILLIS);
            // hit submit
            driver.findElement(By.xpath("//button[@type='submit']")).submit();

            Thread.sleep(MILLIS);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement mainButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButtonBase-root.has-submenu")));
            mainButton.click();

            // Wait for the submenu to be visible
            WebElement submenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".expansion-panel.submenu")));


            WebElement ordersButton = submenu.findElement(By.xpath("//button[contains(@class, 'css-1hytwp4') and contains(., 'Orders')]"));

            ordersButton.click();

            Thread.sleep(MILLIS);

            driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();

            Thread.sleep(MILLIS);

            driver.findElement(By.xpath("//input[@id='mui-7']")).sendKeys(System.getProperty("user.dir") + "/src/main/resources/demo-data.xlsx");

            driver.findElement(By.xpath("//button[text()='Import']")).click();

            Thread.sleep(MILLIS);

            driver.findElement(By.xpath("//button[text()='Validate Data']")).click();

            Thread.sleep(MILLIS);

            Alert alert = driver.switchTo().alert();

            alert.accept();

            Thread.sleep(MILLIS);

            File folder = new File("FunctionalTesting");
            if (!folder.exists()) {
                folder.mkdirs(); // Create directories if needed
            }
            // Capture screenshot
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = folder.getPath() + "/screenshot_"+ timestamp + ".png";

            // Save screenshot to a file
            File destination = new File(fileName);
            Files.copy(screenshot.toPath(), destination.toPath());
            Thread.sleep(MILLIS);

            // Wait until the element with the text "Hi PREXO-MIS-ADMIN" is clickable
            WebElement userGreeting = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".MuiBox-root.css-70qvj9 .MuiBox-root.css-1jfpitw span")));

            // Click on the element
            userGreeting.click();
            Thread.sleep(MILLIS);
            WebElement logoutMenuItem = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class, 'MuiMenuItem-root') and .//span[text()=' Logout ']]")));
            logoutMenuItem.click();

            Thread.sleep(MILLIS);
            driver.quit();

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
