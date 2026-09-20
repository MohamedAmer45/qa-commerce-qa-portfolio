package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductsSteps {

    private final Pages pages;

    public ProductsSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("I open the products page")
    public void iOpenTheProductsPage() {
        pages.products().open();
    }

    @Given("I have added product {int} to the cart")
    public void iHaveAddedProductToTheCart(int productId) {
        pages.products().open();
        pages.products().addProductToCart(productId);
    }

    @When("I add product {int} to the cart from the products page")
    public void iAddProductToTheCartFromTheProductsPage(int productId) {
        pages.products().addProductToCart(productId);
    }

    @When("I search for {string}")
    public void iSearchFor(String text) {
        pages.products().search(text);
    }

    @When("I filter products by the {string} category")
    public void iFilterProductsByCategory(String category) {
        pages.products().selectCategory(category);
    }

    @When("I sort products by {string}")
    public void iSortProductsBy(String option) {
        pages.products().selectSort(option);
    }

    @Then("I should see {int} product(s)")
    public void iShouldSeeProducts(int expected) {
        Assert.assertEquals(
                pages.products().getVisibleProductCount(),
                expected);
    }

    @Then("the result count should read {string}")
    public void theResultCountShouldRead(String expected) {
        Assert.assertEquals(
                pages.products().getResultCountText(),
                expected);
    }

    @Then("product {int} should be displayed")
    public void productShouldBeDisplayed(int productId) {
        Assert.assertTrue(
                pages.products().isProductDisplayed(productId),
                "Product " + productId + " was not displayed.");
    }

    @Then("product {int} should show {string}")
    public void productShouldShow(int productId, String text) {
        Assert.assertTrue(
                pages.products().getProductCardText(productId).contains(text),
                "Product " + productId + " does not show '" + text + "'.");
    }

    @Then("the empty products state should be displayed")
    public void theEmptyProductsStateShouldBeDisplayed() {
        Assert.assertTrue(pages.products().isEmptyStateDisplayed());
    }

    @Then("the add to cart button for product {int} should be disabled")
    public void theAddToCartButtonShouldBeDisabled(int productId) {
        Assert.assertFalse(
                pages.products().isAddToCartEnabled(productId),
                "Add to cart should be disabled for product " + productId + ".");
    }

    @Then("the add to cart button for product {int} should be enabled")
    public void theAddToCartButtonShouldBeEnabled(int productId) {
        Assert.assertTrue(
                pages.products().isAddToCartEnabled(productId),
                "Add to cart should be enabled for product " + productId + ".");
    }

    @Then("the product prices should be sorted in ascending order")
    public void thePricesShouldBeSortedAscending() {
        List<Double> actual = pages.products().getVisiblePrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Comparator.naturalOrder());

        Assert.assertEquals(actual, expected, "Products are not sorted by ascending price.");
    }

    @Then("the product prices should be sorted in descending order")
    public void thePricesShouldBeSortedDescending() {
        List<Double> actual = pages.products().getVisiblePrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Comparator.reverseOrder());

        Assert.assertEquals(actual, expected, "Products are not sorted by descending price.");
    }

    @Then("the first product price should be {double}")
    public void theFirstProductPriceShouldBe(double expected) {
        Assert.assertEquals(
                pages.products().getVisiblePrices().getFirst(),
                expected,
                0.001);
    }
}
