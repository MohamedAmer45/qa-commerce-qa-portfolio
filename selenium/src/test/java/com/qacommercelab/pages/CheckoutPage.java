package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By heading = By.xpath("//h1[normalize-space()='Shipping & payment']");

    private final By firstName = By.id("a");
    private final By lastName = By.id("b");
    private final By email = By.id("e");
    private final By address = By.id("ad");
    private final By city = By.id("ci");
    private final By postal = By.id("po");
    private final By cardholder = By.id("na");

    private final By cardNumber = By.cssSelector("[data-testid='card-number']");

    private final By expiry = By.id("ex");
    private final By cvv = By.id("cv");

    private final By placeOrderButton = By.cssSelector("[data-testid='place-order']");

    private final By message = By.id("m");

    private final By orderSuccess = By.cssSelector("[data-testid='order-success']");

    private final By cartCount = By.cssSelector("[data-testid='cart-count']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public CheckoutPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/checkout");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(heading));

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

    public void setAddress(String value) {
        replaceText(address, value);
    }

    public void setCity(String value) {
        replaceText(city, value);
    }

    public void setPostal(String value) {
        replaceText(postal, value);
    }

    public void setCardholder(String value) {
        replaceText(cardholder, value);
    }

    public void setCardNumber(String value) {
        replaceText(cardNumber, value);
    }

    public void setExpiry(String value) {
        replaceText(expiry, value);
    }

    public void setCvv(String value) {
        replaceText(cvv, value);
    }

    public void fillValidShipping() {
        setAddress("123 QA Street");
        setCity("Cairo");
        setPostal("12345");
        setCardholder("QA Tester");
    }

    public void fillPayment(
            String card,
            String expiryValue,
            String cvvValue) {
        setCardNumber(card);
        setExpiry(expiryValue);
        setCvv(cvvValue);
    }

    public void fillValidPayment() {
        fillPayment(
                "4242424242424242",
                "12/30",
                "123");
    }

    public void submitOrder() {
        wait.until(
                ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    public String getMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public String getFirstName() {
        return driver.findElement(firstName)
                .getAttribute("value");
    }

    public String getLastName() {
        return driver.findElement(lastName)
                .getAttribute("value");
    }

    public String getEmail() {
        return driver.findElement(email)
                .getAttribute("value");
    }

    public boolean isPlaceOrderDisabled() {
        return !driver.findElement(placeOrderButton).isEnabled();
    }

    public String getPlaceOrderButtonText() {
        return driver.findElement(placeOrderButton).getText();
    }

    public boolean waitForOrderSuccess() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            orderSuccess))
                    .isDisplayed();

        } catch (Exception exception) {
            return false;
        }
    }

    public String getOrderSuccessText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        orderSuccess))
                .getText();
    }

    public int getCartCount() {
        return Integer.parseInt(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cartCount))
                        .getText());
    }

    public boolean isOrderSuccessVisibleWithin(int seconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(seconds));

            return shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            orderSuccess))
                    .isDisplayed();

        } catch (Exception exception) {
            return false;
        }
    }
}
