package com.qacommercelab.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class AllureEnvironment {

    private AllureEnvironment() {
    }

    public static void write() {

        Path resultsDirectory = Path.of(
                System.getProperty("user.dir"),
                "target",
                "allure-results");

        try {
            Files.createDirectories(resultsDirectory);

            Properties properties = new Properties();

            properties.setProperty(
                    "Application",
                    "QA Commerce Lab");

            properties.setProperty(
                    "Base URL",
                    ConfigReader.get("baseUrl"));

            properties.setProperty(
                    "Browser",
                    ConfigReader.get("browser"));

            properties.setProperty(
                    "Headless",
                    String.valueOf(
                            ConfigReader.getBoolean("headless")));

            properties.setProperty(
                    "Java",
                    System.getProperty("java.version"));

            properties.setProperty(
                    "Operating System",
                    System.getProperty("os.name"));

            properties.setProperty(
                    "Automation Framework",
                    "Selenium 4.48.0");

            properties.setProperty(
                    "Test Framework",
                    "TestNG 7.12.0");

            Path environmentFile = resultsDirectory.resolve(
                    "environment.properties");

            try (
                    OutputStream output = Files.newOutputStream(environmentFile)) {
                properties.store(
                        output,
                        "QA Commerce Lab Selenium Environment");
            }

            System.out.println(
                    "Allure environment written: "
                            + environmentFile.toAbsolutePath());

        } catch (IOException exception) {
            System.err.println(
                    "Unable to write Allure environment: "
                            + exception.getMessage());
        }
    }
}