package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.AccountPage;
import com.qacommercelab.pages.LoginPage;
import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("QA Commerce Lab")
@Feature("Account Management")
public class AccountTests extends BaseTest {

    private void loginAsSeedUser() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                ConfigReader.get("seedEmail"),
                ConfigReader.get("seedPassword"));

        Assert.assertTrue(
                loginPage.isAccountPageDisplayed(),
                "Seed user login failed during account test setup.");
    }

    private void createDynamicUserSession() {

        driver.get(ConfigReader.get("baseUrl"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = """
                const user = {
                    id: 987654321,
                    first: 'Delete',
                    last: 'Tester',
                    email: 'delete.user@example.com',
                    pass: 'Password123!'
                };

                localStorage.setItem(
                    'users',
                    JSON.stringify([user])
                );

                localStorage.setItem(
                    'user',
                    JSON.stringify(user)
                );
                """;

        js.executeScript(script);
    }

    @Test(groups = { "smoke", "account" }, description = "Account page displays authenticated seed user details")
    public void accountDisplaysSeedUserDetails() {

        loginAsSeedUser();

        AccountPage accountPage = new AccountPage(driver);

        Assert.assertEquals(
                accountPage.getFullName(),
                "QA Tester");

        Assert.assertEquals(
                accountPage.getEmail(),
                "qa.user@example.com");

        Assert.assertEquals(
                accountPage.getNavAccountText(),
                "QA");
    }

    @Test(groups = { "smoke", "account", "authentication" }, description = "Logout clears authenticated session")
    public void logoutClearsSession() {

        loginAsSeedUser();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.logout();

        Assert.assertTrue(
                accountPage.getCurrentUrl().endsWith("/"),
                "User was not returned to the home page after logout.");

        Assert.assertEquals(
                accountPage.getNavAccountText(),
                "Sign in",
                "Navigation should display Sign in after logout.");

        accountPage.open();

        Assert.assertTrue(
                accountPage.isSignedOutDisplayed(),
                "Direct account access should show signed-out state.");
    }

    @Test(groups = { "regression", "account" }, description = "Empty delete confirmation is rejected")
    public void emptyDeleteConfirmationIsRejected() {

        loginAsSeedUser();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.deleteAccount("");

        Assert.assertEquals(
                accountPage.getMessage(),
                "Confirmation must equal DELETE.");

        Assert.assertTrue(
                accountPage.getCurrentUrl().endsWith("/account"));
    }

    @Test(groups = { "regression", "account" }, description = "Lowercase delete confirmation is rejected")
    public void lowercaseDeleteConfirmationIsRejected() {

        loginAsSeedUser();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.deleteAccount("delete");

        Assert.assertEquals(
                accountPage.getMessage(),
                "Confirmation must equal DELETE.");

        Assert.assertEquals(
                accountPage.getNavAccountText(),
                "QA");
    }

    @Test(groups = { "regression", "account" }, description = "Delete confirmation with whitespace is rejected")
    public void deleteConfirmationWithWhitespaceIsRejected() {

        loginAsSeedUser();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.deleteAccount(" DELETE ");

        Assert.assertEquals(
                accountPage.getMessage(),
                "Confirmation must equal DELETE.");

        Assert.assertTrue(
                accountPage.getCurrentUrl().endsWith("/account"));
    }

    @Test(groups = { "regression", "account",
            "authentication" }, description = "Seed account cannot be permanently deleted")
    public void seedAccountRemainsReusableAfterDeleteAction() {

        loginAsSeedUser();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.deleteAccount("DELETE");

        Assert.assertTrue(
                accountPage.getCurrentUrl().endsWith("/"),
                "Seed delete action should end the current session.");

        Assert.assertEquals(
                accountPage.getNavAccountText(),
                "Sign in");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                ConfigReader.get("seedEmail"),
                ConfigReader.get("seedPassword"));

        Assert.assertTrue(
                loginPage.isAccountPageDisplayed(),
                "Seed account should remain available after delete action.");
    }

    @Test(groups = { "regression",
            "account" }, description = "Dynamic account is deleted using exact DELETE confirmation")
    public void dynamicAccountCanBeDeleted() {

        createDynamicUserSession();

        AccountPage accountPage = new AccountPage(driver);

        accountPage.open();

        Assert.assertEquals(
                accountPage.getFullName(),
                "Delete Tester");

        Assert.assertEquals(
                accountPage.getEmail(),
                "delete.user@example.com");

        accountPage.deleteAccount("DELETE");

        Assert.assertTrue(
                accountPage.getCurrentUrl().endsWith("/"),
                "User should return home after account deletion.");

        Assert.assertEquals(
                accountPage.getNavAccountText(),
                "Sign in");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                "delete.user@example.com",
                "Password123!");

        Assert.assertEquals(
                loginPage.getMessage(),
                "Invalid credentials.",
                "Deleted dynamic account should no longer authenticate.");
    }
}