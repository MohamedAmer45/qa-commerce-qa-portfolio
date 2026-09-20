package com.qacommercelab.cucumber.support;

import com.qacommercelab.base.DriverFactory;
import com.qacommercelab.pages.AccountPage;
import com.qacommercelab.pages.CartPage;
import com.qacommercelab.pages.CheckoutPage;
import com.qacommercelab.pages.ContactPage;
import com.qacommercelab.pages.HomePage;
import com.qacommercelab.pages.LoginPage;
import com.qacommercelab.pages.ProductsPage;
import com.qacommercelab.pages.QALabPage;
import com.qacommercelab.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;

/**
 * Creates the shared Selenium page objects on demand.
 *
 * Cucumber builds step classes before the @Before hook has started the browser,
 * so the driver must never be read in a constructor. Page objects hold no state,
 * which makes creating them per step safe.
 */
public class Pages {

    public WebDriver driver() {
        return DriverFactory.getDriver();
    }

    public HomePage home() {
        return new HomePage(driver());
    }

    public LoginPage login() {
        return new LoginPage(driver());
    }

    public RegistrationPage registration() {
        return new RegistrationPage(driver());
    }

    public AccountPage account() {
        return new AccountPage(driver());
    }

    public ProductsPage products() {
        return new ProductsPage(driver());
    }

    public CartPage cart() {
        return new CartPage(driver());
    }

    public CheckoutPage checkout() {
        return new CheckoutPage(driver());
    }

    public ContactPage contact() {
        return new ContactPage(driver());
    }

    public QALabPage qaLab() {
        return new QALabPage(driver());
    }
}
