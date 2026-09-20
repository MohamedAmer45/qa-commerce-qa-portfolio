package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CouponSteps {

    private final Pages pages;

    public CouponSteps(Pages pages) {
        this.pages = pages;
    }

    @When("I apply the coupon {string}")
    public void iApplyTheCoupon(String coupon) {
        pages.cart().applyCoupon(coupon);
    }

    @Then("the coupon message should be {string}")
    public void theCouponMessageShouldBe(String expected) {
        Assert.assertEquals(
                pages.cart().getCouponMessage(),
                expected);
    }

    @Then("the cart total should become {double}")
    public void theCartTotalShouldBecome(double expected) {
        // The total is re-rendered asynchronously after the coupon request completes.
        pages.cart().waitForTotal(expected);

        Assert.assertEquals(
                pages.cart().getTotal(),
                expected,
                0.01);
    }
}
