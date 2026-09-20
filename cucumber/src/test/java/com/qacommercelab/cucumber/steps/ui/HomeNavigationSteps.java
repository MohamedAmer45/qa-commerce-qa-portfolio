package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import com.qacommercelab.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomeNavigationSteps {

    private final Pages pages;

    public HomeNavigationSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        pages.driver().get(ConfigReader.get("baseUrl"));
    }

    @Then("the home page should be displayed")
    public void theHomePageShouldBeDisplayed() {
        Assert.assertTrue(
                pages.home().isLoaded(),
                "Home page did not load correctly.");

        Assert.assertEquals(
                pages.home().getCurrentUrl(),
                ConfigReader.get("baseUrl") + "/",
                "Unexpected home page URL.");
    }

    @When("I open the products page from the home page")
    public void iOpenTheProductsPageFromTheHomePage() {
        pages.home().openProducts();
    }

    @Then("I should be on the products page")
    public void iShouldBeOnTheProductsPage() {
        new WebDriverWait(
                pages.driver(),
                Duration.ofSeconds(ConfigReader.getInt("explicitWait")))
                .until(ExpectedConditions.urlContains("/products"));

        Assert.assertTrue(
                pages.driver().getCurrentUrl().contains("/products"),
                "Products page was not opened.");
    }

    @When("I refresh the current page")
    public void iRefreshTheCurrentPage() {
        pages.driver().navigate().refresh();
    }
}
