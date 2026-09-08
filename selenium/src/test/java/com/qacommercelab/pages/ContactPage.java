package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.id("na");
    private final By emailInput = By.id("em");
    private final By subjectSelect = By.id("su");
    private final By messageInput = By.id("me");
    private final By fileInput = By.id("fi");

    private final By submitButton =
            By.cssSelector("form#f button.btn.p");

    private final By resultMessage = By.id("m");

    public ContactPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")
                )
        );
    }

    public ContactPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/contact");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameInput)
        );

        return this;
    }

    private void replaceText(By locator, String value) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.clear();
        element.sendKeys(value);
    }

    public void setName(String value) {
        replaceText(nameInput, value);
    }

    public void setEmail(String value) {
        replaceText(emailInput, value);
    }

    public void setMessage(String value) {
        replaceText(messageInput, value);
    }

    public void selectSubject(String subject) {
        Select select = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                subjectSelect
                        )
                )
        );

        select.selectByVisibleText(subject);
    }

    public void uploadFile(String absolutePath) {
        wait.until(
                ExpectedConditions.presenceOfElementLocated(fileInput)
        ).sendKeys(absolutePath);
    }

    public void submit() {
        wait.until(
                ExpectedConditions.elementToBeClickable(submitButton)
        ).click();
    }

    public void fillValidForm() {
        setName("QA Tester");
        setEmail("qa@example.com");
        selectSubject("Technical problem");
        setMessage(
                "This is a valid support request created by Selenium."
        );
    }

    public String getResultMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        resultMessage
                )
        ).getText();
    }

    public String getMessageValue() {
        return driver.findElement(messageInput)
                .getAttribute("value");
    }

    public String getSelectedSubject() {
        return new Select(
                driver.findElement(subjectSelect)
        ).getFirstSelectedOption().getText();
    }
}