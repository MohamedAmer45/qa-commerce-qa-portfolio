package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.CartPage;
import com.qacommercelab.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("QA Commerce Lab")
@Feature("Shopping Cart")
public class CartTests extends BaseTest {

    private void addProduct(int productId) {
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.addProductToCart(productId);
    }

    @Test(groups = { "smoke", "cart" }, description = "Empty cart displays empty state")
    public void emptyCartDisplaysEmptyState() {

        CartPage cartPage = new CartPage(driver);

        cartPage.open();

        Assert.assertTrue(
                cartPage.isEmptyCartDisplayed(),
                "Empty cart state was not displayed.");

        Assert.assertEquals(
                cartPage.getCartCount(),
                0);
    }

    @Test(groups = { "smoke", "cart" }, description = "Added product appears in cart")
    public void addedProductAppearsInCart() {

        addProduct(10);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        Assert.assertEquals(
                cartPage.getCartRowCount(),
                1);

        Assert.assertEquals(
                cartPage.getQuantity(10),
                1);

        Assert.assertEquals(
                cartPage.getSubtotal(),
                39.90,
                0.01);
    }

    @Test(groups = { "regression", "cart" }, description = "Updating quantity recalculates subtotal")
    public void updatingQuantityRecalculatesSubtotal() {

        addProduct(10);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        cartPage.setValidQuantity(10, 2);

        Assert.assertEquals(
                cartPage.getQuantity(10),
                2);

        Assert.assertEquals(
                cartPage.getSubtotal(),
                79.80,
                0.01);

        Assert.assertEquals(
                cartPage.getCartCount(),
                2);
    }

    @Test(groups = { "regression", "cart" }, description = "Product can be removed from cart")
    public void productCanBeRemoved() {

        addProduct(10);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        cartPage.removeProduct(10);

        Assert.assertEquals(
                cartPage.getCartRowCount(),
                0);

        Assert.assertTrue(
                cartPage.isEmptyCartDisplayed());

        Assert.assertEquals(
                cartPage.getCartCount(),
                0);
    }

    @Test(groups = { "regression", "cart" }, description = "Cart persists after browser refresh")
    public void cartPersistsAfterRefresh() {

        addProduct(10);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        Assert.assertEquals(
                cartPage.getQuantity(10),
                1);

        cartPage.refresh();

        Assert.assertEquals(
                cartPage.getCartRowCount(),
                1);

        Assert.assertEquals(
                cartPage.getQuantity(10),
                1);

        Assert.assertEquals(
                cartPage.getSubtotal(),
                39.90,
                0.01);
    }

    @Test(groups = { "regression", "cart" }, description = "Quantity zero is rejected")
    public void quantityZeroIsRejected() {

        addProduct(10);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        cartPage.attemptQuantityChange(10, 0);

        Assert.assertEquals(
                cartPage.getToastMessage(),
                "Invalid quantity");

        Assert.assertEquals(
                cartPage.getQuantity(10),
                1,
                "Invalid quantity should not be saved.");
    }

    @Test(groups = { "regression", "cart" }, description = "Quantity above available stock is rejected")
    public void quantityAboveStockIsRejected() {

        // Product 2 currently has stock = 1.
        addProduct(2);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        cartPage.attemptQuantityChange(2, 2);

        Assert.assertEquals(
                cartPage.getToastMessage(),
                "Invalid quantity");

        Assert.assertEquals(
                cartPage.getQuantity(2),
                1);
    }

    @Test(groups = { "regression", "cart" }, description = "Multiple product quantities produce correct subtotal")
    public void multipleProductsCalculateCorrectSubtotal() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();

        productsPage.addProductToCart(10);
        productsPage.addProductToCart(10);
        productsPage.addProductToCart(11);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        Assert.assertEquals(
                cartPage.getCartRowCount(),
                2);

        Assert.assertEquals(
                cartPage.getCartCount(),
                3);

        // Echo Mini Speaker:
        // 39.90 × 2 = 79.80
        //
        // Nomad USB-C Hub:
        // 89.00 × 1 = 89.00
        //
        // Total subtotal = 168.80

        Assert.assertEquals(
                cartPage.getSubtotal(),
                168.80,
                0.01);
    }

    @Test(groups = { "regression", "cart" }, description = "Product raw price is rounded correctly in cart")
    public void decimalProductPriceIsRounded() {

        // Product 8 raw price = 19.999.
        addProduct(8);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        Assert.assertEquals(
                cartPage.getSubtotal(),
                20.00,
                0.01);

        Assert.assertEquals(
                cartPage.getSubtotalText(),
                "$20.00");
    }

    @Test(groups = { "smoke", "cart",
            "checkout" }, description = "Guest cart can navigate to checkout and is asked to sign in")
    public void guestCheckoutRequiresAuthentication() {

        addProduct(10);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        cartPage.clickCheckout();

        Assert.assertTrue(
                cartPage.getCurrentUrl().endsWith("/checkout"));

        Assert.assertTrue(
                cartPage.isSignInRequiredDisplayed(),
                "Guest checkout should require authentication.");
    }

    @Test(groups = {
            "regression",
            "cart",
            "defect-candidate"
    }, description = "QA Sticker Pack quantity cannot exceed special maximum of 25")
    @Issue("BUG-UI-CART-001")
    @Severity(SeverityLevel.CRITICAL)
    public void stickerPackQuantityCannotExceed25() {

        // Product 7:
        // QA Sticker Pack
        // Stock = 500
        // Special cart-add maximum = 25

        addProduct(7);

        CartPage cartPage = new CartPage(driver);

        cartPage.open();

        Assert.assertEquals(
                cartPage.getQuantity(7),
                1);

        cartPage.attemptQuantityChange(
                7,
                26);

        Assert.assertEquals(
                cartPage.getQuantity(7),
                1,
                "Cart accepted quantity 26 even though the product has a special maximum of 25.");
    }
}