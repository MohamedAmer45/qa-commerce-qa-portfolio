import type { Page } from "@playwright/test";

import { test, expect } from "../../fixtures/auth.fixture";

import { ProductsPage } from "../../pages/ProductsPage";
import { CheckoutPage } from "../../pages/CheckoutPage";
async function prepareCheckout(page: Page): Promise<CheckoutPage> {
  const productsPage = new ProductsPage(page);

  await productsPage.open();

  // Echo Mini Speaker — $39.90
  await productsPage.addProduct(10);

  const checkoutPage = new CheckoutPage(page);

  await checkoutPage.open();

  return checkoutPage;
}

test.describe("Checkout", () => {
  test("@smoke @checkout authenticated account information is prefilled", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await expect(checkoutPage.firstNameInput).toHaveValue("QA");

    await expect(checkoutPage.lastNameInput).toHaveValue("Tester");

await expect(
  checkoutPage.emailInput
).toHaveValue(
  'qa.user@example.com'
);  });

  test("@regression @checkout missing required shipping field is rejected", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping({
      city: "",
    });

    await checkoutPage.fillValidPayment();

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Complete valid shipping fields.");
  });

  test("@regression @checkout short address is rejected", async ({ page }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping({
      address: "1234",
    });

    await checkoutPage.fillValidPayment();

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Address too short.");
  });

  test("@regression @checkout invalid card number fails validation", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("1234567890123456", "12/30", "123");

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Card failed validation.");
  });

  test("@regression @checkout two digit CVV is rejected", async ({ page }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("4242424242424242", "12/30", "12");

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Expiry/CVV invalid.");
  });

  test("@regression @checkout five digit CVV is rejected", async ({ page }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("4242424242424242", "12/30", "12345");

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Expiry/CVV invalid.");
  });

  test("@regression @checkout invalid expiry format is rejected", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("4242424242424242", "1230", "123");

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Expiry/CVV invalid.");
  });

  test("@regression @checkout declined payment displays decline message", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("4000000000000002", "12/30", "123");

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Payment declined.");
  });

  test("@regression @checkout insufficient funds payment is rejected", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("4000000000009995", "12/30", "123");

    await checkoutPage.submitOrder();

    await checkoutPage.expectMessage("Insufficient funds.");
  });

  test("@regression @checkout card number containing spaces is accepted", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("4242 4242 4242 4242", "12/30", "123");

    await checkoutPage.submitOrder();

    await checkoutPage.expectOrderSuccess();

    await expect(checkoutPage.orderSuccess).toContainText("Order confirmed");
  });

  test("@smoke @checkout valid checkout succeeds", async ({ page }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();
    await checkoutPage.fillValidPayment();

    await checkoutPage.submitOrder();

    await checkoutPage.expectOrderSuccess();

    await expect(checkoutPage.orderSuccess).toContainText("Order confirmed");

    await expect(checkoutPage.orderSuccess).toContainText("ORD-QA-1001");

    const confirmation = await checkoutPage.getOrderSuccessText();

    expect(confirmation).toContain("ORD-QA-1001");
  });

  test("@regression @checkout duplicate submission is prevented while processing", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();
    await checkoutPage.fillValidPayment();

    await checkoutPage.submitOrder();

    /*
     * Processing lasts approximately 700 ms.
     * The button should become disabled immediately
     * after the first submission.
     */
    await expect(checkoutPage.placeOrderButton).toBeDisabled();

    await expect(checkoutPage.placeOrderButton).toHaveText("Processing…");

    await checkoutPage.expectOrderSuccess();
  });

  test("@regression @checkout @cart successful checkout clears persisted cart", async ({
    page,
  }) => {
    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();
    await checkoutPage.fillValidPayment();

    await checkoutPage.submitOrder();

    await checkoutPage.expectOrderSuccess();

    /*
     * The current application has a known defect where
     * the visible navigation counter remains stale
     * immediately after checkout.
     *
     * Refreshing forces the header to rerender and proves
     * that the persisted cart itself was actually cleared.
     */
    await page.reload();

    await expect(checkoutPage.cartCount).toHaveText("0");
  });
});
