package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.HomePage;
import com.qacommercelab.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.time.Duration;

@Epic("QA Commerce Lab")
@Feature("Core Navigation")
public class FrameworkSmokeTest extends BaseTest {

    @Test
    public void homePageLoadsAndNavigatesToProducts() {

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.isLoaded(),
                "Home page did not load correctly.");

        Assert.assertEquals(
                homePage.getCurrentUrl(),
                ConfigReader.get("baseUrl") + "/",
                "Unexpected home page URL.");

        homePage.openProducts();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(
                        ConfigReader.getInt("explicitWait")));

        wait.until(
                ExpectedConditions.urlContains("/products"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/products"),
                "Products page was not opened.");
    }
}