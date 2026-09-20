package com.qacommercelab.cucumber.hooks;

import com.qacommercelab.base.DriverFactory;
import com.qacommercelab.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Hooks {

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    @BeforeAll
    public static void writeAllureEnvironment() {

        Path resultsDirectory = Path.of(
                System.getProperty("user.dir"),
                "target",
                "allure-results");

        try {
            Files.createDirectories(resultsDirectory);

            Properties properties = new Properties();

            properties.setProperty("Application", "QA Commerce Lab");
            properties.setProperty("Base URL", ConfigReader.get("baseUrl"));
            properties.setProperty("Browser", ConfigReader.get("browser"));
            properties.setProperty("Headless", String.valueOf(ConfigReader.getBoolean("headless")));
            properties.setProperty("Java", System.getProperty("java.version"));
            properties.setProperty("Operating System", System.getProperty("os.name"));
            properties.setProperty("Automation Framework", "Cucumber JVM 7.34.8 + Selenium 4.48.0");
            properties.setProperty("Test Framework", "TestNG 7.12.0");

            try (OutputStream output = Files.newOutputStream(
                    resultsDirectory.resolve("environment.properties"))) {
                properties.store(output, "QA Commerce Lab Cucumber Environment");
            }

        } catch (IOException exception) {
            System.err.println(
                    "Unable to write Allure environment: "
                            + exception.getMessage());
        }
    }

    /**
     * Only scenarios tagged @ui start a browser, so REST API scenarios run
     * without launching Chrome.
     */
    @Before("@ui")
    public void startBrowser() {

        DriverFactory.createDriver();

        WebDriver driver = DriverFactory.getDriver();

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

        ((JavascriptExecutor) driver)
                .executeScript(
                        "window.localStorage.clear();" +
                                "window.sessionStorage.clear();");

        driver.navigate().refresh();
    }

    @After("@ui")
    public void stopBrowser(Scenario scenario) {

        try {
            if (scenario.isFailed()) {
                captureFailureScreenshot(scenario);
            }
        } finally {
            DriverFactory.quitDriver();
        }
    }

    private void captureFailureScreenshot(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriverIfPresent();

        if (driver == null) {
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            // Attached to the Cucumber HTML report and, through the Allure plugin, to Allure.
            scenario.attach(screenshot, "image/png", "Failure screenshot");

            saveScreenshotToRepository(scenario, screenshot);

        } catch (Exception exception) {
            System.err.println(
                    "Failed to capture screenshot for '"
                            + scenario.getName()
                            + "': "
                            + exception.getMessage());
        }
    }

    private void saveScreenshotToRepository(
            Scenario scenario,
            byte[] screenshot) throws IOException {

        Path directory = resolveRepositoryRoot()
                .resolve("reports")
                .resolve("cucumber")
                .resolve("screenshots");

        Files.createDirectories(directory);

        String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9._-]", "_")
                + "-"
                + LocalDateTime.now().format(TIMESTAMP_FORMAT)
                + ".png";

        Path path = directory.resolve(fileName);

        Files.write(path, screenshot);

        System.out.println("Failure screenshot saved: " + path.toAbsolutePath());
    }

    private Path resolveRepositoryRoot() {

        Path currentDirectory = Path.of(
                System.getProperty("user.dir")).toAbsolutePath().normalize();

        if (currentDirectory.getFileName().toString().equalsIgnoreCase("cucumber")) {
            return currentDirectory.getParent();
        }

        return currentDirectory;
    }
}
