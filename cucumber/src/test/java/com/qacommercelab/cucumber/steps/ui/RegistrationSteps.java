package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class RegistrationSteps {

    private final Pages pages;

    public RegistrationSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("I open the registration page")
    public void iOpenTheRegistrationPage() {
        pages.registration().open();
    }

    @When("I enter registration details {string}, {string}, {string}, {string} and {string}")
    public void iEnterRegistrationDetails(
            String first,
            String last,
            String email,
            String password,
            String confirmation) {

        pages.registration().setFirstName(first);
        pages.registration().setLastName(last);
        pages.registration().setEmail(email);
        pages.registration().setPassword(password);
        pages.registration().setConfirmPassword(confirmation);
    }

    @When("the terms and conditions checkbox is {string}")
    public void theTermsCheckboxIs(String state) {
        if (state.equalsIgnoreCase("checked")) {
            pages.registration().acceptTerms();
        }
    }

    @When("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        pages.registration().submit();
    }

    @When("I register {string} {string} with email {string} and password {string}")
    public void iRegisterWithEmailAndPassword(
            String first,
            String last,
            String email,
            String password) {

        pages.registration().register(
                first,
                last,
                email,
                password,
                password,
                true);
    }

    @Then("the registration message should be {string}")
    public void theRegistrationMessageShouldBe(String expected) {
        Assert.assertEquals(
                pages.registration().getMessage(),
                expected);
    }

    @When("I type {int} characters into the {string} registration field")
    public void iTypeCharactersIntoTheRegistrationField(int length, String field) {
        String value = "A".repeat(length);

        switch (field) {
            case "first name" -> pages.registration().setFirstName(value);
            case "last name" -> pages.registration().setLastName(value);
            case "password" -> pages.registration().setPassword(value);
            default -> throw new IllegalArgumentException("Unknown registration field: " + field);
        }
    }

    @Then("the {string} registration field should contain {int} characters")
    public void theRegistrationFieldShouldContainCharacters(String field, int expected) {
        String value = switch (field) {
            case "first name" -> pages.registration().getFirstNameValue();
            case "last name" -> pages.registration().getLastNameValue();
            case "password" -> pages.registration().getPasswordValue();
            default -> throw new IllegalArgumentException("Unknown registration field: " + field);
        };

        Assert.assertEquals(
                value.length(),
                expected,
                "The " + field + " input did not enforce its maximum length.");
    }

    @When("I end the session but keep the registered account")
    public void iEndTheSessionButKeepTheRegisteredAccount() {
        // Registration stores the account in localStorage; only the active session is removed.
        pages.driver().manage().deleteAllCookies();

        ((JavascriptExecutor) pages.driver())
                .executeScript("localStorage.removeItem('user');");
    }
}
