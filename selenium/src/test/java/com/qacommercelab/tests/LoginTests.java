package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.LoginPage;
import com.qacommercelab.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("QA Commerce Lab")
@Feature("Authentication")
public class LoginTests extends BaseTest {

        @Test(groups = { "smoke",
                        "authentication" }, description = "Valid seeded credentials authenticate successfully")
        public void validSeedLogin() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login(
                                ConfigReader.get("seedEmail"),
                                ConfigReader.get("seedPassword"));

                Assert.assertTrue(
                                loginPage.isAccountPageDisplayed(),
                                "Account page was not displayed after valid login.");

                Assert.assertTrue(
                                loginPage.getCurrentUrl().endsWith("/account"),
                                "User was not redirected to /account.");

                Assert.assertEquals(
                                loginPage.getAccountNavigationText(),
                                "QA",
                                "Unexpected account navigation text.");
        }

        @Test(groups = { "regression",
                        "authentication" }, description = "Incorrect password displays invalid credentials")
        public void invalidPasswordShowsError() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login(
                                ConfigReader.get("seedEmail"),
                                "WrongPassword123!");

                Assert.assertEquals(
                                loginPage.getMessage(),
                                "Invalid credentials.");
        }

        @Test(groups = { "regression",
                        "authentication" }, description = "Malformed email displays email validation message")
        public void invalidEmailFormatShowsError() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login(
                                "invalid-email",
                                ConfigReader.get("seedPassword"));

                Assert.assertEquals(
                                loginPage.getMessage(),
                                "Invalid email format.");
        }

        @Test(groups = { "regression", "authentication" }, description = "Empty credentials are rejected")
        public void emptyCredentialsShowRequiredError() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login("", "");

                Assert.assertEquals(
                                loginPage.getMessage(),
                                "Email and password required.");
        }

        @Test(groups = { "regression", "authentication" }, description = "Email comparison is case insensitive")
        public void uppercaseEmailCanLogin() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login(
                                "QA.USER@EXAMPLE.COM",
                                ConfigReader.get("seedPassword"));

                Assert.assertTrue(
                                loginPage.isAccountPageDisplayed(),
                                "Uppercase email should authenticate successfully.");
        }

        @Test(groups = { "regression",
                        "authentication" }, description = "Leading and trailing email whitespace is trimmed")
        public void emailWhitespaceIsTrimmed() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login(
                                "   qa.user@example.com   ",
                                ConfigReader.get("seedPassword"));

                Assert.assertTrue(
                                loginPage.isAccountPageDisplayed(),
                                "Email whitespace should be trimmed before authentication.");
        }

        @Test(groups = { "regression", "authentication" }, description = "Password comparison is case sensitive")
        public void passwordIsCaseSensitive() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login(
                                ConfigReader.get("seedEmail"),
                                "password123!");

                Assert.assertEquals(
                                loginPage.getMessage(),
                                "Invalid credentials.");
        }
}