import { test, expect, Page } from "@playwright/test";

import { LoginPage } from "../../pages/LoginPage";
import { ProductsPage } from "../../pages/ProductsPage";
import { CartPage } from "../../pages/CartPage";
import { CheckoutPage } from "../../pages/CheckoutPage";
import { users } from "../../test-data/users";

async function prepareCheckout(page: Page): Promise<CheckoutPage> {
  const loginPage = new LoginPage(page);

  await loginPage.open();

  await loginPage.login(users.seed.email, users.seed.password);

  await loginPage.expectSuccessfulLogin();

  const productsPage = new ProductsPage(page);

  await productsPage.open();

  await productsPage.addProduct(10);

  const checkoutPage = new CheckoutPage(page);

  await checkoutPage.open();

  return checkoutPage;
}

test.describe("Known UI Defects", () => {
  test("@known-defect BUG-UI-CHK-001 cart counter updates immediately after successful checkout", async ({
    page,
  }) => {
    test.info().annotations.push({
      type: "issue",
      description: "BUG-UI-CHK-001",
    });

    /*
     * The test is expected to fail while the
     * confirmed application defect remains open.
     */
    test.fail(true, "BUG-UI-CHK-001 is currently open");

    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();
    await checkoutPage.fillValidPayment();

    await checkoutPage.submitOrder();

    await checkoutPage.expectOrderSuccess();

    /*
     * Correct expected behavior:
     *
     * After checkout succeeds, the visible
     * navigation cart counter should immediately
     * update to zero.
     *
     * Current defect:
     * The counter remains stale until refresh.
     */
    await expect(checkoutPage.cartCount).toHaveText("0");
  });

  test("@known-defect BUG-UI-CHK-002 impossible expiry month is rejected", async ({
    page,
  }) => {
    test.info().annotations.push({
      type: "issue",
      description: "BUG-UI-CHK-002",
    });

    test.fail(true, "BUG-UI-CHK-002 is currently open");

    const checkoutPage = await prepareCheckout(page);

    await checkoutPage.fillShipping();

    await checkoutPage.fillPayment("4242424242424242", "13/30", "123");

    await checkoutPage.submitOrder();

    /*
     * Correct expected behavior:
     * month 13 must not result in a successful order.
     */
    await expect(checkoutPage.orderSuccess).toBeHidden();

    await checkoutPage.expectMessage("Expiry/CVV invalid.");
  });

  test("@known-defect BUG-UI-CART-001 Sticker Pack quantity cannot exceed 25", async ({
    page,
  }) => {
    test.info().annotations.push({
      type: "issue",
      description: "BUG-UI-CART-001",
    });

    test.fail(true, "BUG-UI-CART-001 is currently open");

    const productsPage = new ProductsPage(page);

    await productsPage.open();

    /*
     * Product 7:
     * QA Sticker Pack
     *
     * Physical stock = 500
     * Business maximum = 25
     */
    await productsPage.addProduct(7);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await expect(cartPage.quantityInput(7)).toHaveValue("1");

    await cartPage.changeQuantity(7, 26);

    const quantity = Number(await cartPage.quantityInput(7).inputValue());

    /*
     * We deliberately assert the business rule
     * rather than a specific correction strategy.
     *
     * Both of these would be valid fixes:
     *
     * - Reject 26 and keep quantity at 1
     * - Clamp quantity to 25
     */
    expect(quantity).toBeLessThanOrEqual(25);
  });
});
