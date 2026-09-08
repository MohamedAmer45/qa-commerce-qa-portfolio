package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.cssSelector("[data-testid='login-email']");

    private final By passwordInput = By.cssSelector("[data-testid='login-password']");

    private final By loginButton = By.cssSelector("[data-testid='login-submit']");

    private final By message = By.id("m");

    private final By navAccount = By.cssSelector("[data-testid='nav-account']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public LoginPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/login");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput));

        return this;
    }

    public LoginPage enterEmail(String email) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput)).clear();

        driver.findElement(emailInput).sendKeys(email);

        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)).clear();

        driver.findElement(passwordInput).sendKeys(password);

        return this;
    }

    public void clickLogin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public String getMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public boolean isAccountPageDisplayed() {
        try {
            wait.until(
                    ExpectedConditions.urlContains("/account"));

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(navAccount)).isDisplayed();

        } catch (Exception exception) {
            return false;
        }
    }

    public String getAccountNavigationText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(navAccount)).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}