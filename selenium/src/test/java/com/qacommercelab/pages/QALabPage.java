package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QALabPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By heading = By.xpath("//h1[normalize-space()='Deterministic failure modes']");

    private final By output = By.id("out");

    private final By delayedDomButton = By.id("dy");

    private final By dynamicResult = By.cssSelector("[data-testid='dynamic-result']");

    private final By modalButton = By.id("mo");

    private final By dialog = By.id("dlg");

    private final By cancelButton = By.id("cl");

    private final By confirmButton = By.id("cf");

    public QALabPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public QALabPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/qa-lab");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(heading));

        return this;
    }

    private By trigger(String testId) {
        return By.cssSelector(
                "[data-testid='" + testId + "']");
    }

    public void triggerScenario(String testId) {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        trigger(testId)))
                .click();

        wait.until(driver -> {
            String text = driver.findElement(output).getText();

            return !text.isBlank()
                    && !text.equals("Loading…")
                    && !text.equals("Choose a scenario.");
        });
    }

    public String getOutput() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(output)).getText();
    }

    public int getStatusFromOutput() {

        Pattern pattern = Pattern.compile("\"status\"\\s*:\\s*(\\d+)");

        Matcher matcher = pattern.matcher(getOutput());

        if (!matcher.find()) {
            throw new IllegalStateException(
                    "Status was not found in QA Lab output.");
        }

        return Integer.parseInt(matcher.group(1));
    }

    public int getElapsedMilliseconds() {

        Pattern pattern = Pattern.compile("\"elapsedMs\"\\s*:\\s*(\\d+)");

        Matcher matcher = pattern.matcher(getOutput());

        if (!matcher.find()) {
            throw new IllegalStateException(
                    "elapsedMs was not found in QA Lab output.");
        }

        return Integer.parseInt(matcher.group(1));
    }

    public String getRetryAfter() {

        Pattern pattern = Pattern.compile(
                "\"retryAfter\"\\s*:\\s*\"([^\"]+)\"");

        Matcher matcher = pattern.matcher(getOutput());

        if (!matcher.find()) {
            return null;
        }

        return matcher.group(1);
    }

    public void triggerDelayedDom() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        delayedDomButton))
                .click();
    }

    public boolean waitForDynamicElement() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            dynamicResult))
                    .isDisplayed();

        } catch (Exception exception) {
            return false;
        }
    }

    public String getDynamicElementText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        dynamicResult))
                .getText();
    }

    public void openModal() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        modalButton))
                .click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(dialog));
    }

    public boolean isModalDisplayed() {
        return !driver.findElements(dialog).isEmpty()
                && driver.findElement(dialog).isDisplayed();
    }

    public void cancelModal() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        cancelButton))
                .click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(dialog));
    }

    public void confirmModal() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmButton))
                .click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(dialog));
    }

}
