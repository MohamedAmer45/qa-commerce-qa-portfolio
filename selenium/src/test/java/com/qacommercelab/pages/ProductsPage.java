package com.qacommercelab.pages;

import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchInput = By.cssSelector("[data-testid='product-search']");

    private final By categorySelect = By.id("c");

    private final By sortSelect = By.id("o");

    private final By resultCount = By.id("cnt");

    private final By emptyProducts = By.cssSelector("[data-testid='empty-products']");

    private final By cartCount = By.cssSelector("[data-testid='cart-count']");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));
    }

    public ProductsPage open() {
        driver.get(ConfigReader.get("baseUrl") + "/products");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput));

        return this;
    }

    public void search(String text) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput));

        input.clear();
        input.sendKeys(text);
    }

    public void selectCategory(String category) {
        Select select = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                categorySelect)));

        select.selectByVisibleText(category);
    }

    public void selectSort(String sortOption) {
        Select select = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                sortSelect)));

        select.selectByVisibleText(sortOption);
    }

    public String getResultCountText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(resultCount)).getText();
    }

    public int getVisibleProductCount() {
        return driver.findElements(
                By.cssSelector("[data-testid^='product-card-']")).size();
    }

    public boolean isEmptyStateDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(emptyProducts)).isDisplayed();
    }

    public boolean isProductDisplayed(int productId) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(
                                "[data-testid='product-card-" +
                                        productId +
                                        "']")))
                .isDisplayed();
    }

    public String getProductCardText(int productId) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(
                                "[data-testid='product-card-" +
                                        productId +
                                        "']")))
                .getText();
    }

    public boolean isAddToCartEnabled(int productId) {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(
                                "[data-testid='add-cart-" +
                                        productId +
                                        "']")))
                .isEnabled();
    }

    public void addProductToCart(int productId) {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(
                                "[data-testid='add-cart-" +
                                        productId +
                                        "']")))
                .click();
    }

    public int getCartCount() {
        String text = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartCount)).getText();

        return Integer.parseInt(text);
    }

    public List<Double> getVisiblePrices() {
        return driver.findElements(
                By.cssSelector(
                        "[data-testid^='product-card-'] .price"))
                .stream()
                .map(WebElement::getText)
                .map(this::parsePrice)
                .toList();
    }

    private double parsePrice(String text) {
        return Double.parseDouble(
                text
                        .replace("$", "")
                        .replace(",", "")
                        .trim());
    }
}