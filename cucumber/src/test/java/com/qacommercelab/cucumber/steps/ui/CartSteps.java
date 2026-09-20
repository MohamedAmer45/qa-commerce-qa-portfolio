package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {

    private final Pages pages;

    public CartSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("I open the cart page")
    public void iOpenTheCartPage() {
        pages.cart().open();
    }

    @When("I change the quantity of product {int} to {int}")
    public void iChangeTheQuantityOfProductTo(int productId, int quantity) {
        pages.cart().setValidQuantity(productId, quantity);
    }

    @When("I try to change the quantity of product {int} to {int}")
    public void iTryToChangeTheQuantityOfProductTo(int productId, int quantity) {
        pages.cart().attemptQuantityChange(productId, quantity);
    }

    @When("I remove product {int} from the cart")
    public void iRemoveProductFromTheCart(int productId) {
        pages.cart().removeProduct(productId);
    }

    @When("I refresh the cart page")
    public void iRefreshTheCartPage() {
        pages.cart().refresh();
    }

    @When("I proceed to checkout from the cart")
    public void iProceedToCheckoutFromTheCart() {
        pages.cart().clickCheckout();
    }

    @Then("the cart should show the empty state")
    public void theCartShouldShowTheEmptyState() {
        Assert.assertTrue(
                pages.cart().isEmptyCartDisplayed(),
                "Empty cart state was not displayed.");
    }

    @Then("the cart should contain {int} product line(s)")
    public void theCartShouldContainProductLines(int expected) {
        Assert.assertEquals(
                pages.cart().getCartRowCount(),
                expected);
    }

    @Then("the cart counter should show {int}")
    public void theCartCounterShouldShow(int expected) {
        Assert.assertEquals(
                pages.cart().getCartCount(),
                expected,
                "Unexpected cart counter value.");
    }

    @Then("the quantity of product {int} in the cart should be {int}")
    public void theQuantityOfProductInTheCartShouldBe(int productId, int expected) {
        Assert.assertEquals(
                pages.cart().getQuantity(productId),
                expected);
    }

    @Then("the cart subtotal should be {double}")
    public void theCartSubtotalShouldBe(double expected) {
        Assert.assertEquals(
                pages.cart().getSubtotal(),
                expected,
                0.01);
    }

    @Then("the cart subtotal text should be {string}")
    public void theCartSubtotalTextShouldBe(String expected) {
        Assert.assertEquals(
                pages.cart().getSubtotalText(),
                expected);
    }

    @Then("the cart total should be {double}")
    public void theCartTotalShouldBe(double expected) {
        Assert.assertEquals(
                pages.cart().getTotal(),
                expected,
                0.01);
    }

    @Then("a cart notification should say {string}")
    public void aCartNotificationShouldSay(String expected) {
        Assert.assertEquals(
                pages.cart().getToastMessage(),
                expected);
    }

    @Then("I should be on the checkout page")
    public void iShouldBeOnTheCheckoutPage() {
        Assert.assertTrue(
                pages.cart().getCurrentUrl().endsWith("/checkout"),
                "User was not taken to /checkout.");
    }

    @Then("I should be asked to sign in")
    public void iShouldBeAskedToSignIn() {
        Assert.assertTrue(
                pages.cart().isSignInRequiredDisplayed(),
                "Guest checkout should require authentication.");
    }
}
