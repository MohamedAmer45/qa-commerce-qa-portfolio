package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.CheckoutPage;
import com.qacommercelab.pages.LoginPage;
import com.qacommercelab.pages.ProductsPage;
import com.qacommercelab.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("QA Commerce Lab")
@Feature("Checkout")
public class CheckoutTests extends BaseTest {

        private CheckoutPage prepareCheckout() {

                LoginPage loginPage = new LoginPage(driver);

                loginPage.open();

                loginPage.login(
                                ConfigReader.get("seedEmail"),
                                ConfigReader.get("seedPassword"));

                Assert.assertTrue(
                                loginPage.isAccountPageDisplayed(),
                                "Login failed during checkout setup.");

                ProductsPage productsPage = new ProductsPage(driver);

                productsPage.open();
                productsPage.addProductToCart(10);

                CheckoutPage checkoutPage = new CheckoutPage(driver);

                checkoutPage.open();

                return checkoutPage;
        }

        @Test(groups = { "smoke", "checkout" }, description = "Authenticated checkout pre-fills account data")
        public void checkoutPrefillsAccountInformation() {

                CheckoutPage checkoutPage = prepareCheckout();

                Assert.assertEquals(
                                checkoutPage.getFirstName(),
                                "QA");

                Assert.assertEquals(
                                checkoutPage.getLastName(),
                                "Tester");

                Assert.assertEquals(
                                checkoutPage.getEmail(),
                                "qa.user@example.com");
        }

        @Test(groups = { "regression", "checkout" }, description = "Missing required shipping field is rejected")
        public void missingRequiredShippingFieldIsRejected() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();
                checkoutPage.setCity("");

                checkoutPage.fillValidPayment();
                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Complete valid shipping fields.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Short address is rejected")
        public void shortAddressIsRejected() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();
                checkoutPage.setAddress("1234");

                checkoutPage.fillValidPayment();
                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Address too short.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Invalid Luhn card is rejected")
        public void invalidCardIsRejected() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "1234567890123456",
                                "12/30",
                                "123");

                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Card failed validation.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Two digit CVV is rejected")
        public void twoDigitCvvIsRejected() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "4242424242424242",
                                "12/30",
                                "12");

                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Expiry/CVV invalid.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Five digit CVV is rejected")
        public void fiveDigitCvvIsRejected() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "4242424242424242",
                                "12/30",
                                "12345");

                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Expiry/CVV invalid.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Invalid expiry format is rejected")
        public void invalidExpiryFormatIsRejected() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "4242424242424242",
                                "1230",
                                "123");

                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Expiry/CVV invalid.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Declined payment card displays decline message")
        public void declinedCardShowsPaymentDeclined() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "4000000000000002",
                                "12/30",
                                "123");

                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Payment declined.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Insufficient funds card displays correct message")
        public void insufficientFundsCardShowsError() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "4000000000009995",
                                "12/30",
                                "123");

                checkoutPage.submitOrder();

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Insufficient funds.");
        }

        @Test(groups = { "regression", "checkout" }, description = "Card number containing spaces is accepted")
        public void cardNumberWithSpacesIsAccepted() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "4242 4242 4242 4242",
                                "12/30",
                                "123");

                checkoutPage.submitOrder();

                Assert.assertTrue(
                                checkoutPage.waitForOrderSuccess(),
                                "Order should succeed with spaces in card number.");
        }

        @Test(groups = { "smoke", "checkout" }, description = "Valid checkout creates order successfully")
        public void validCheckoutCreatesOrder() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();
                checkoutPage.fillValidPayment();

                checkoutPage.submitOrder();

                Assert.assertTrue(
                                checkoutPage.waitForOrderSuccess(),
                                "Order success confirmation was not displayed.");

                Assert.assertTrue(
                                checkoutPage
                                                .getOrderSuccessText()
                                                .contains("Order confirmed"));

                Assert.assertTrue(
                                checkoutPage
                                                .getOrderSuccessText()
                                                .contains("ORD-QA-1001"));
        }

        @Test(groups = { "regression", "checkout" }, description = "Place order button is disabled during processing")
        public void duplicateSubmissionIsPrevented() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();
                checkoutPage.fillValidPayment();

                checkoutPage.submitOrder();

                Assert.assertTrue(
                                checkoutPage.isPlaceOrderDisabled(),
                                "Place order button should be disabled while processing.");

                Assert.assertEquals(
                                checkoutPage.getPlaceOrderButtonText(),
                                "Processing…");

                Assert.assertTrue(
                                checkoutPage.waitForOrderSuccess(),
                                "Order confirmation was not displayed.");
        }

        @Test(groups = {
                        "regression",
                        "checkout",
                        "cart",
                        "known-defect"
        }, description = "BUG-UI-CHK-001 — Cart counter updates immediately after checkout")
        @Issue("BUG-UI-CHK-001")
        @Severity(SeverityLevel.NORMAL)
        public void successfulCheckoutClearsCartAndUpdatesCounter() {

                CheckoutPage checkoutPage = prepareCheckout();

                Assert.assertEquals(
                                checkoutPage.getCartCount(),
                                1,
                                "Cart should contain one product before checkout.");

                checkoutPage.fillValidShipping();
                checkoutPage.fillValidPayment();

                checkoutPage.submitOrder();

                Assert.assertTrue(
                                checkoutPage.waitForOrderSuccess(),
                                "Order confirmation was not displayed.");

                /*
                 * Known defect candidate: BUG-UI-CHK-001
                 *
                 * The persisted cart is cleared successfully, but the navigation
                 * cart counter is not re-rendered immediately after checkout.
                 *
                 * Expected: 0
                 * Current application behavior: 1
                 *
                 * Keep this assertion failing while the application defect exists.
                 */
                Assert.assertEquals(
                                checkoutPage.getCartCount(),
                                0,
                                "Cart counter should update to 0 immediately after successful checkout.");
        }

        @Test(groups = { "regression", "checkout",
                        "cart" }, description = "Successful checkout clears persisted cart state")
        public void successfulCheckoutClearsPersistedCart() {

                CheckoutPage checkoutPage = prepareCheckout();

                Assert.assertEquals(
                                checkoutPage.getCartCount(),
                                1,
                                "Cart should contain one product before checkout.");

                checkoutPage.fillValidShipping();
                checkoutPage.fillValidPayment();

                checkoutPage.submitOrder();

                Assert.assertTrue(
                                checkoutPage.waitForOrderSuccess(),
                                "Order confirmation was not displayed.");

                /*
                 * Refresh forces the navigation to re-render from localStorage.
                 * If the checkout actually cleared the persisted cart,
                 * the counter should now show zero.
                 */
                driver.navigate().refresh();

                Assert.assertEquals(
                                checkoutPage.getCartCount(),
                                0,
                                "Persisted cart was not cleared after successful checkout.");
        }

        @Test(groups = {
                        "regression",
                        "checkout",
                        "defect-candidate",
                        "known-defect"
        }, description = "BUG-UI-CHK-002 — Checkout rejects impossible expiry month")
        @Issue("BUG-UI-CHK-002")
        @Severity(SeverityLevel.CRITICAL)
        public void impossibleExpiryMonthIsRejected() {

                CheckoutPage checkoutPage = prepareCheckout();

                checkoutPage.fillValidShipping();

                checkoutPage.fillPayment(
                                "4242424242424242",
                                "13/30",
                                "123");

                checkoutPage.submitOrder();

                boolean orderSucceeded = checkoutPage.isOrderSuccessVisibleWithin(2);

                Assert.assertFalse(
                                orderSucceeded,
                                "Checkout accepted invalid expiry month 13.");

                Assert.assertEquals(
                                checkoutPage.getMessage(),
                                "Expiry/CVV invalid.");
        }
}