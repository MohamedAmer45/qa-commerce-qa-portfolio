package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import com.qacommercelab.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class AccountSteps {

    private final Pages pages;

    public AccountSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("a dynamic user {string} {string} with email {string} and password {string} is signed in")
    public void aDynamicUserIsSignedIn(
            String first,
            String last,
            String email,
            String password) {

        pages.driver().get(ConfigReader.get("baseUrl"));

        // Seeds the same localStorage structure the application uses for a registered user.
        String script = """
                const user = {
                    id: 987654321,
                    first: arguments[0],
                    last: arguments[1],
                    email: arguments[2],
                    pass: arguments[3]
                };
                localStorage.setItem('users', JSON.stringify([user]));
                localStorage.setItem('user', JSON.stringify(user));
                """;

        ((JavascriptExecutor) pages.driver())
                .executeScript(script, first, last, email, password);
    }

    @When("I open the account page")
    public void iOpenTheAccountPage() {
        pages.account().open();
    }

    @When("I log out")
    public void iLogOut() {
        pages.account().logout();
    }

    @When("I request account deletion with confirmation {string}")
    public void iRequestAccountDeletionWithConfirmation(String confirmation) {
        pages.account().deleteAccount(confirmation);
    }

    @Then("the account page should show the name {string} and email {string}")
    public void theAccountPageShouldShowTheNameAndEmail(String name, String email) {
        Assert.assertEquals(
                pages.account().getFullName(),
                name);

        Assert.assertEquals(
                pages.account().getEmail(),
                email);
    }

    @Then("I should be returned to the home page")
    public void iShouldBeReturnedToTheHomePage() {
        Assert.assertTrue(
                pages.account().getCurrentUrl().endsWith("/"),
                "User was not returned to the home page.");
    }

    @Then("I should still be on the account page")
    public void iShouldStillBeOnTheAccountPage() {
        Assert.assertTrue(
                pages.account().getCurrentUrl().endsWith("/account"),
                "User should have stayed on the account page.");
    }

    @Then("the signed-out message should be displayed")
    public void theSignedOutMessageShouldBeDisplayed() {
        Assert.assertTrue(
                pages.account().isSignedOutDisplayed(),
                "Direct account access should show the signed-out state.");
    }

    @Then("the account message should be {string}")
    public void theAccountMessageShouldBe(String expected) {
        Assert.assertEquals(
                pages.account().getMessage(),
                expected);
    }
}
