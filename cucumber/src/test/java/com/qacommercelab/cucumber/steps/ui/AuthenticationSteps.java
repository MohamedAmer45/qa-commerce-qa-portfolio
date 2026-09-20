package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import com.qacommercelab.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AuthenticationSteps {

    private final Pages pages;

    public AuthenticationSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        pages.login().open();
    }

    @Given("I am logged in as the seed user")
    public void iAmLoggedInAsTheSeedUser() {
        pages.login().open();

        pages.login().login(
                ConfigReader.get("seedEmail"),
                ConfigReader.get("seedPassword"));

        Assert.assertTrue(
                pages.login().isAccountPageDisplayed(),
                "Seed user login failed during scenario setup.");
    }

    @When("I log in as the seed user")
    public void iLogInAsTheSeedUser() {
        pages.login().login(
                ConfigReader.get("seedEmail"),
                ConfigReader.get("seedPassword"));
    }

    @When("I log in with email {string} and password {string}")
    public void iLogInWithEmailAndPassword(String email, String password) {
        pages.login().login(email, password);
    }

    @Then("I should be on my account page")
    public void iShouldBeOnMyAccountPage() {
        Assert.assertTrue(
                pages.login().isAccountPageDisplayed(),
                "Account page was not displayed.");

        Assert.assertTrue(
                pages.login().getCurrentUrl().endsWith("/account"),
                "User was not redirected to /account.");
    }

    @Then("the account navigation should show {string}")
    public void theAccountNavigationShouldShow(String expected) {
        Assert.assertEquals(
                pages.login().getAccountNavigationText(),
                expected,
                "Unexpected account navigation text.");
    }

    @Then("the login error should be {string}")
    public void theLoginErrorShouldBe(String expected) {
        Assert.assertEquals(
                pages.login().getMessage(),
                expected);
    }
}
