package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By heading = By.cssSelector("main.wrap h1");

    private final By email = By.cssSelector("main.wrap h1 + p");

    private final By logoutButton = By.id("lo");

    private final By deleteConfirmation = By.id("dc");

    private final By deleteButton = By.id("de");

    private final By message = By.id("m");

    private final By navAccount = By.cssSelector("[data-testid='nav-account']");

    private final By signedOutMessage = By.xpath("//*[contains(normalize-space(), 'Signed out.')]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public AccountPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/account");
        return this;
    }

    public String getFullName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(heading)).getText();
    }

    public String getEmail() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(email)).getText();
    }

    public void logout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(logoutButton)).click();

        wait.until(
                ExpectedConditions.urlToBe(
                        ConfigReader.get("baseUrl") + "/"));
    }

    public void enterDeleteConfirmation(String value) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        deleteConfirmation));

        input.clear();
        input.sendKeys(value);
    }

    public void clickDeleteAccount() {
        wait.until(
                ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public String getMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public void deleteAccount(String confirmation) {
        enterDeleteConfirmation(confirmation);
        clickDeleteAccount();
    }

    public String getNavAccountText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(navAccount)).getText();
    }

    public boolean isSignedOutDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        signedOutMessage))
                .isDisplayed();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}