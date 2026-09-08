package com.qacommercelab.listeners;

import com.qacommercelab.base.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = DriverFactory.getDriverIfPresent();

        if (driver == null) {
            System.err.println(
                    "Screenshot skipped because WebDriver was not initialized.");

            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            saveScreenshotToRepository(
                    result,
                    screenshot);

            attachScreenshotToAllure(
                    result.getMethod().getMethodName(),
                    screenshot);

        } catch (Exception exception) {
            System.err.println(
                    "Failed to capture screenshot for "
                            + result.getMethod().getMethodName()
                            + ": "
                            + exception.getMessage());
        }
    }

    private void saveScreenshotToRepository(
            ITestResult result,
            byte[] screenshot) throws IOException {

        Path repositoryRoot = resolveRepositoryRoot();

        Path screenshotDirectory = repositoryRoot
                .resolve("reports")
                .resolve("selenium")
                .resolve("screenshots");

        Files.createDirectories(
                screenshotDirectory);

        String className = result
                .getTestClass()
                .getRealClass()
                .getSimpleName();

        String methodName = result
                .getMethod()
                .getMethodName();

        String timestamp = LocalDateTime
                .now()
                .format(TIMESTAMP_FORMAT);

        String fileName = sanitize(className)
                + "-"
                + sanitize(methodName)
                + "-"
                + timestamp
                + ".png";

        Path screenshotPath = screenshotDirectory.resolve(fileName);

        Path temporaryFile = Files.createTempFile(
                "selenium-screenshot-",
                ".png");

        try {
            Files.write(
                    temporaryFile,
                    screenshot);

            Files.move(
                    temporaryFile,
                    screenshotPath,
                    StandardCopyOption.REPLACE_EXISTING);

        } finally {
            Files.deleteIfExists(
                    temporaryFile);
        }

        System.out.println(
                "Failure screenshot saved: "
                        + screenshotPath.toAbsolutePath());
    }

    private Path resolveRepositoryRoot() {

        Path currentDirectory = Path.of(
                System.getProperty("user.dir")).toAbsolutePath().normalize();

        if (currentDirectory
                .getFileName()
                .toString()
                .equalsIgnoreCase("selenium")) {
            return currentDirectory.getParent();
        }

        if (Files.exists(
                currentDirectory
                        .resolve("selenium")
                        .resolve("pom.xml"))) {
            return currentDirectory;
        }

        return currentDirectory;
    }

    private String sanitize(String value) {
        return value.replaceAll(
                "[^a-zA-Z0-9._-]",
                "_");
    }

    @Attachment(value = "Failure Screenshot - {testName}", type = "image/png")
    public byte[] attachScreenshotToAllure(
            String testName,
            byte[] screenshot) {
        return screenshot;
    }
}