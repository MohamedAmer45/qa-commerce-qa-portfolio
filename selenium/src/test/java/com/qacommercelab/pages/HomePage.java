package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productsLink = By.cssSelector("a[href='/products']");

    public HomePage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public boolean isLoaded() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsLink)).isDisplayed();
    }

    public void openProducts() {
        wait.until(
                ExpectedConditions.elementToBeClickable(productsLink)).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}