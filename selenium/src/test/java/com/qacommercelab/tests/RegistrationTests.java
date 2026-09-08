package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.LoginPage;
import com.qacommercelab.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("QA Commerce Lab")
@Feature("Registration")
public class RegistrationTests extends BaseTest {

    private static final String VALID_PASSWORD = "Password123!";

    @Test(groups = { "regression", "registration" }, description = "Empty first and last names are rejected")
    public void namesAreRequired() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "",
                "",
                "new.user@example.com",
                VALID_PASSWORD,
                VALID_PASSWORD,
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Names required.");
    }

    @Test(groups = { "regression", "registration" }, description = "Invalid email format is rejected")
    public void invalidEmailIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "invalid-email",
                VALID_PASSWORD,
                VALID_PASSWORD,
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Valid email required.");
    }

    @Test(groups = { "regression", "registration" }, description = "Existing seed email cannot be registered again")
    public void duplicateSeedEmailIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "QA.USER@EXAMPLE.COM",
                VALID_PASSWORD,
                VALID_PASSWORD,
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Email already exists.");
    }

    @Test(groups = { "regression", "registration" }, description = "Password shorter than eight characters is rejected")
    public void sevenCharacterPasswordIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "short.password@example.com",
                "Aa1!abc",
                "Aa1!abc",
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Weak password.");
    }

    @Test(groups = { "regression", "registration" }, description = "Password without uppercase character is rejected")
    public void passwordWithoutUppercaseIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "upper.test@example.com",
                "password123!",
                "password123!",
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Weak password.");
    }

    @Test(groups = { "regression", "registration" }, description = "Password without lowercase character is rejected")
    public void passwordWithoutLowercaseIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "lower.test@example.com",
                "PASSWORD123!",
                "PASSWORD123!",
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Weak password.");
    }

    @Test(groups = { "regression", "registration" }, description = "Password without number is rejected")
    public void passwordWithoutDigitIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "digit.test@example.com",
                "Password!",
                "Password!",
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Weak password.");
    }

    @Test(groups = { "regression", "registration" }, description = "Password without special character is rejected")
    public void passwordWithoutSpecialCharacterIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "special.test@example.com",
                "Password123",
                "Password123",
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Weak password.");
    }

    @Test(groups = { "regression", "registration" }, description = "Password confirmation must match password")
    public void passwordConfirmationMismatchIsRejected() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "mismatch@example.com",
                VALID_PASSWORD,
                "Different123!",
                true);

        Assert.assertEquals(
                page.getMessage(),
                "Passwords do not match.");
    }

    @Test(groups = { "regression", "registration" }, description = "Terms must be accepted before registration")
    public void termsAreRequired() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "New",
                "User",
                "terms@example.com",
                VALID_PASSWORD,
                VALID_PASSWORD,
                false);

        Assert.assertEquals(
                page.getMessage(),
                "Accept terms.");
    }

    @Test(groups = { "regression", "registration" }, description = "First name input enforces forty-character maximum")
    public void firstNameMaximumLengthIsEnforced() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        String fortyOneCharacters = "A".repeat(41);

        page.setFirstName(fortyOneCharacters);

        Assert.assertEquals(
                page.getFirstNameValue().length(),
                40,
                "First name input should enforce maxlength=40.");
    }

    @Test(groups = { "regression", "registration" }, description = "Last name input enforces forty-character maximum")
    public void lastNameMaximumLengthIsEnforced() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.setLastName("B".repeat(41));

        Assert.assertEquals(
                page.getLastNameValue().length(),
                40,
                "Last name input should enforce maxlength=40.");
    }

    @Test(groups = { "regression",
            "registration" }, description = "Password input enforces sixty-four-character maximum")
    public void passwordMaximumLengthIsEnforced() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.setPassword("A".repeat(65));

        Assert.assertEquals(
                page.getPasswordValue().length(),
                64,
                "Password input should enforce maxlength=64.");
    }

    @Test(groups = { "smoke", "registration" }, description = "Valid registration creates and authenticates account")
    public void validRegistrationSucceeds() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(
                "Automation",
                "Tester",
                "selenium.user@example.com",
                VALID_PASSWORD,
                VALID_PASSWORD,
                true);

        Assert.assertTrue(
                page.isAccountPageDisplayed(),
                "Valid registration should redirect to /account.");
    }

    @Test(groups = { "regression", "registration",
            "authentication" }, description = "Newly registered user can log out and authenticate again")
    public void registeredUserCanLoginAgain() {

        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        String email = "selenium.login@example.com";

        page.register(
                "Selenium",
                "Tester",
                email,
                VALID_PASSWORD,
                VALID_PASSWORD,
                true);

        Assert.assertTrue(
                page.isAccountPageDisplayed());

        /*
         * Registration stores the new account in localStorage.
         * Remove only the active session so the account itself remains.
         */
        driver.manage().deleteAllCookies();

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "localStorage.removeItem('user');");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                email,
                VALID_PASSWORD);

        Assert.assertTrue(
                loginPage.isAccountPageDisplayed(),
                "Registered account should authenticate successfully.");
    }
}