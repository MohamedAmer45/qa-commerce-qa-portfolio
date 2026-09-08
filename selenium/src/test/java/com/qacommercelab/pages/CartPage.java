package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By couponInput = By.id("cp");

    private final By applyCouponButton = By.id("ap");

    private final By couponMessage = By.id("cm");

    private final By pageHeading = By.xpath("//h1[normalize-space()='Your basket']");

    private final By emptyCart = By.cssSelector("[data-testid='empty-cart']");

    private final By cartRows = By.cssSelector(".cartrow");

    private final By subtotal = By.id("sub");

    private final By total = By.id("tot");

    private final By cartCount = By.cssSelector("[data-testid='cart-count']");

    private final By checkoutLink = By.cssSelector("a[href='/checkout']");

    private final By toast = By.cssSelector(".toast");

    public CartPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public CartPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/cart");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageHeading));

        return this;
    }

    public boolean isEmptyCartDisplayed() {
        return !driver.findElements(emptyCart).isEmpty()
                && driver.findElement(emptyCart).isDisplayed();
    }

    public int getCartRowCount() {
        return driver.findElements(cartRows).size();
    }

    public int getCartCount() {
        return Integer.parseInt(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(cartCount)).getText());
    }

    private By quantityInput(int productId) {
        return By.cssSelector(
                "input.cq[data-id='" + productId + "']");
    }

    private By removeButton(int productId) {
        return By.cssSelector(
                "button.rm[data-id='" + productId + "']");
    }

    public int getQuantity(int productId) {

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        quantityInput(productId)));

        String value = input.getAttribute("value");

        return Integer.parseInt(value);
    }

    public void setValidQuantity(int productId, int quantity) {

        By locator = quantityInput(productId);

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        // Select the existing value instead of calling clear(),
        // because clear() causes this application to re-render the cart.
        input.sendKeys(
                Keys.chord(Keys.CONTROL, "a"),
                String.valueOf(quantity));

        // Click outside the field to trigger onchange.
        driver.findElement(pageHeading).click();

        // The application replaces the original input after onchange.
        wait.until(
                ExpectedConditions.stalenessOf(input));

        // Locate the newly rendered input and verify the saved value.
        wait.until(
                ExpectedConditions.attributeToBe(
                        locator,
                        "value",
                        String.valueOf(quantity)));
    }

    public void attemptQuantityChange(int productId, int quantity) {

        By locator = quantityInput(productId);

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        // Do not use clear() here.
        input.sendKeys(
                Keys.chord(Keys.CONTROL, "a"),
                String.valueOf(quantity));

        // Trigger onchange.
        driver.findElement(pageHeading).click();

        // Both valid and invalid quantity changes cause render().
        wait.until(
                ExpectedConditions.stalenessOf(input));

        // Wait for the replacement input.
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getToastMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(toast)).getText();
    }

    public void removeProduct(int productId) {
        int originalRows = getCartRowCount();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeButton(productId)))
                .click();

        wait.until(driver -> driver.findElements(cartRows).size() < originalRows);
    }

    public String getSubtotalText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(subtotal)).getText();
    }

    public double getSubtotal() {
        return parseMoney(getSubtotalText());
    }

    public double getTotal() {
        String value = wait.until(
                ExpectedConditions.visibilityOfElementLocated(total)).getText();

        return parseMoney(value);
    }

    public void refresh() {
        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageHeading));
    }

    public void clickCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutLink)).click();

        wait.until(
                ExpectedConditions.urlContains("/checkout"));
    }

    public boolean isSignInRequiredDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//*[contains(normalize-space(), " +
                                        "'Sign in required.')]")))
                .isDisplayed();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    private double parseMoney(String text) {
        return Double.parseDouble(
                text
                        .replace("$", "")
                        .replace(",", "")
                        .trim());
    }

    public void enterCoupon(String coupon) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(couponInput));

        input.clear();
        input.sendKeys(coupon);
    }

    public void applyCoupon(String coupon) {
        enterCoupon(coupon);

        wait.until(
                ExpectedConditions.elementToBeClickable(applyCouponButton)).click();
    }

    public String getCouponMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(couponMessage)).getText();
    }

    public void waitForTotal(double expectedTotal) {
        wait.until(driver -> {
            try {
                return Math.abs(getTotal() - expectedTotal) < 0.01;
            } catch (Exception exception) {
                return false;
            }
        });
    }
}