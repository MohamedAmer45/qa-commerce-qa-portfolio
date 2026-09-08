package com.qacommercelab.base;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.qacommercelab.utils.AllureEnvironment;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void prepareAllureEnvironment() {
        AllureEnvironment.write();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        DriverFactory.createDriver();

        driver = DriverFactory.getDriver();

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(
                                ConfigReader.getInt("pageLoadTimeout")));

        if (!ConfigReader.getBoolean("headless")) {
            driver.manage().window().maximize();
        }

        driver.get(ConfigReader.get("baseUrl"));
        driver.manage().deleteAllCookies();

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "window.localStorage.clear();" +
                                "window.sessionStorage.clear();");

        driver.navigate().refresh();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}