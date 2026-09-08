package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.id("a");
    private final By lastName = By.id("b");
    private final By email = By.id("e");
    private final By password = By.id("p");
    private final By confirmPassword = By.id("q");
    private final By termsCheckbox = By.id("z");

    private final By submitButton = By.cssSelector("form#r button.btn.p");
    private final By message = By.id("m");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public RegistrationPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/register");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName));

        return this;
    }

    private void replaceText(By locator, String value) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(value);
    }

    public void setFirstName(String value) {
        replaceText(firstName, value);
    }

    public void setLastName(String value) {
        replaceText(lastName, value);
    }

    public void setEmail(String value) {
        replaceText(email, value);
    }

    public void setPassword(String value) {
        replaceText(password, value);
    }

    public void setConfirmPassword(String value) {
        replaceText(confirmPassword, value);
    }

    public void acceptTerms() {
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(termsCheckbox));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void submit() {
        wait.until(
                ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void register(
            String first,
            String last,
            String emailValue,
            String passwordValue,
            String confirmValue,
            boolean acceptTerms) {
        setFirstName(first);
        setLastName(last);
        setEmail(emailValue);
        setPassword(passwordValue);
        setConfirmPassword(confirmValue);

        if (acceptTerms) {
            acceptTerms();
        }

        submit();
    }

    public String getMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public String getFirstNameValue() {
        return driver.findElement(firstName).getAttribute("value");
    }

    public String getLastNameValue() {
        return driver.findElement(lastName).getAttribute("value");
    }

    public String getPasswordValue() {
        return driver.findElement(password).getAttribute("value");
    }

    public boolean isAccountPageDisplayed() {
        try {
            wait.until(
                    ExpectedConditions.urlContains("/account"));

            return driver.getCurrentUrl().endsWith("/account");

        } catch (Exception exception) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}