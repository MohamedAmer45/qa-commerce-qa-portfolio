package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {

    private final Pages pages;

    public CheckoutSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("I am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        pages.checkout().open();
    }

    @When("I fill in valid shipping details")
    public void iFillInValidShippingDetails() {
        pages.checkout().fillValidShipping();
    }

    @When("I fill in valid payment details")
    public void iFillInValidPaymentDetails() {
        pages.checkout().fillValidPayment();
    }

    @When("I clear the shipping city")
    public void iClearTheShippingCity() {
        pages.checkout().setCity("");
    }

    @When("I set the shipping address to {string}")
    public void iSetTheShippingAddressTo(String address) {
        pages.checkout().setAddress(address);
    }

    @When("I pay with card {string}, expiry {string} and CVV {string}")
    public void iPayWithCard(String card, String expiry, String cvv) {
        pages.checkout().fillPayment(card, expiry, cvv);
    }

    @When("I place the order")
    public void iPlaceTheOrder() {
        pages.checkout().submitOrder();
    }

    @Then("the checkout form should be pre-filled with first name {string}, last name {string} and email {string}")
    public void theCheckoutFormShouldBePrefilled(String first, String last, String email) {
        Assert.assertEquals(pages.checkout().getFirstName(), first);
        Assert.assertEquals(pages.checkout().getLastName(), last);
        Assert.assertEquals(pages.checkout().getEmail(), email);
    }

    @Then("the checkout message should be {string}")
    public void theCheckoutMessageShouldBe(String expected) {
        Assert.assertEquals(
                pages.checkout().getMessage(),
                expected);
    }

    @Then("the order should be confirmed")
    public void theOrderShouldBeConfirmed() {
        Assert.assertTrue(
                pages.checkout().waitForOrderSuccess(),
                "Order success confirmation was not displayed.");
    }

    @Then("the order should not be confirmed")
    public void theOrderShouldNotBeConfirmed() {
        Assert.assertFalse(
                pages.checkout().isOrderSuccessVisibleWithin(2),
                "Checkout confirmed an order that should have been rejected.");
    }

    @Then("the order confirmation should contain {string}")
    public void theOrderConfirmationShouldContain(String text) {
        Assert.assertTrue(
                pages.checkout().getOrderSuccessText().contains(text),
                "Order confirmation does not contain '" + text + "'.");
    }

    @Then("the place order button should be disabled and read {string}")
    public void thePlaceOrderButtonShouldBeDisabled(String label) {
        Assert.assertTrue(
                pages.checkout().isPlaceOrderDisabled(),
                "Place order button should be disabled while processing.");

        Assert.assertEquals(
                pages.checkout().getPlaceOrderButtonText(),
                label);
    }
}
