package com.qacommercelab.base;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void createDriver() {

        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getBoolean("headless");

        WebDriver webDriver;

        switch (browser) {

            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();

                if (headless) {
                    options.addArguments(
                            "--headless=new",
                            "--window-size=1920,1080");
                } else {
                    options.addArguments("--start-maximized");
                }

                webDriver = new ChromeDriver(options);
            }

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();

                if (headless) {
                    options.addArguments(
                            "-headless",
                            "--width=1920",
                            "--height=1080");
                }

                webDriver = new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();

                if (headless) {
                    options.addArguments(
                            "--headless=new",
                            "--window-size=1920,1080");
                } else {
                    options.addArguments("--start-maximized");
                }

                webDriver = new EdgeDriver(options);
            }

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        DRIVER.set(webDriver);
    }

    public static WebDriver getDriver() {

        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized.");
        }

        return driver;
    }

    public static WebDriver getDriverIfPresent() {
        return DRIVER.get();
    }

    public static void quitDriver() {

        WebDriver driver = DRIVER.get();

        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

}