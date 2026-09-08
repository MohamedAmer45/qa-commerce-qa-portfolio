package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.CartPage;
import com.qacommercelab.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("QA Commerce Lab")
@Feature("Coupons")
public class CouponTests extends BaseTest {

    private CartPage addProductAndOpenCart(int productId) {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();
        productsPage.addProductToCart(productId);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        return cartPage;
    }

    @Test(groups = { "smoke", "coupon" }, description = "SAVE10 applies a 10 percent discount")
    public void save10AppliesTenPercentDiscount() {

        // Product 2 = $129.99
        CartPage cartPage = addProductAndOpenCart(2);

        Assert.assertEquals(
                cartPage.getSubtotal(),
                129.99,
                0.01);

        cartPage.applyCoupon("SAVE10");

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Coupon applied.");

        // 129.99 - 10% + 9.99 shipping
        // = 126.981 -> displayed as $126.98
        cartPage.waitForTotal(126.98);

        Assert.assertEquals(
                cartPage.getTotal(),
                126.98,
                0.01);
    }

    @Test(groups = { "regression", "coupon" }, description = "FREESHIP removes shipping charge")
    public void freeShipRemovesShippingCost() {

        // Product 10 = $39.90
        CartPage cartPage = addProductAndOpenCart(10);

        Assert.assertEquals(
                cartPage.getSubtotal(),
                39.90,
                0.01);

        // Without coupon:
        // 39.90 + 9.99 = 49.89
        Assert.assertEquals(
                cartPage.getTotal(),
                49.89,
                0.01);

        cartPage.applyCoupon("FREESHIP");

        cartPage.waitForTotal(39.90);

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Coupon applied.");

        Assert.assertEquals(
                cartPage.getTotal(),
                39.90,
                0.01);
    }

    @Test(groups = { "regression", "coupon" }, description = "MIN100 applies at exactly 100 or above")
    public void min100AppliesWhenSubtotalExceedsThreshold() {

        // Product 2 = 129.99
        CartPage cartPage = addProductAndOpenCart(2);

        cartPage.applyCoupon("MIN100");

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Coupon applied.");

        // 129.99 - 15 + 9.99 = 124.98
        cartPage.waitForTotal(124.98);

        Assert.assertEquals(
                cartPage.getTotal(),
                124.98,
                0.01);
    }

    @Test(groups = { "regression", "coupon" }, description = "MIN100 is rejected below minimum subtotal")
    public void min100IsRejectedBelowThreshold() {

        CartPage cartPage = addProductAndOpenCart(10);

        Assert.assertEquals(
                cartPage.getSubtotal(),
                39.90,
                0.01);

        cartPage.applyCoupon("MIN100");

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Minimum $100 required.");

        // Total remains unchanged.
        Assert.assertEquals(
                cartPage.getTotal(),
                49.89,
                0.01);
    }

    @Test(groups = { "regression", "coupon" }, description = "Expired coupon is rejected")
    public void expiredCouponIsRejected() {

        CartPage cartPage = addProductAndOpenCart(10);

        cartPage.applyCoupon("EXPIRED");

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Expired coupon.");

        Assert.assertEquals(
                cartPage.getTotal(),
                49.89,
                0.01);
    }

    @Test(groups = { "regression", "coupon" }, description = "Unknown coupon is rejected")
    public void unknownCouponIsRejected() {

        CartPage cartPage = addProductAndOpenCart(10);

        cartPage.applyCoupon("NOTREAL");

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Coupon not found.");

        Assert.assertEquals(
                cartPage.getTotal(),
                49.89,
                0.01);
    }

    @Test(groups = { "regression", "coupon" }, description = "Coupon codes are case insensitive")
    public void couponIsCaseInsensitive() {

        CartPage cartPage = addProductAndOpenCart(10);

        cartPage.applyCoupon("freeship");

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Coupon applied.");

        cartPage.waitForTotal(39.90);

        Assert.assertEquals(
                cartPage.getTotal(),
                39.90,
                0.01);
    }

    @Test(groups = { "regression", "coupon" }, description = "Coupon whitespace is trimmed")
    public void couponWhitespaceIsTrimmed() {

        CartPage cartPage = addProductAndOpenCart(10);

        cartPage.applyCoupon("   FREESHIP   ");

        Assert.assertEquals(
                cartPage.getCouponMessage(),
                "Coupon applied.");

        cartPage.waitForTotal(39.90);

        Assert.assertEquals(
                cartPage.getTotal(),
                39.90,
                0.01);
    }

    @Test(groups = { "regression", "coupon",
            "cart" }, description = "Orders of 150 or more automatically receive free shipping")
    public void subtotalAbove150GetsFreeShipping() {

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.open();

        // 129.99 + 39.90 = 169.89
        productsPage.addProductToCart(2);
        productsPage.addProductToCart(10);

        CartPage cartPage = new CartPage(driver);
        cartPage.open();

        Assert.assertEquals(
                cartPage.getSubtotal(),
                169.89,
                0.01);

        Assert.assertEquals(
                cartPage.getTotal(),
                169.89,
                0.01,
                "Shipping should be free for subtotal >= 150.");
    }
}