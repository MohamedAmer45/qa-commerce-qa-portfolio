import { test, expect, Page } from "@playwright/test";
import { CartPage } from "../../pages/CartPage";
import { ProductsPage } from "../../pages/ProductsPage";

async function addProduct(
  page: Page,
  productId: number,
  times = 1,
): Promise<void> {
  const productsPage = new ProductsPage(page);

  await productsPage.open();

  for (let i = 0; i < times; i++) {
    await productsPage.addProduct(productId);
  }
}

test.describe("Shopping Cart", () => {
  test("@smoke @cart empty cart displays empty state", async ({ page }) => {
    const cartPage = new CartPage(page);

    await cartPage.open();

    await expect(cartPage.emptyCart).toBeVisible();

    await expect(cartPage.cartRows).toHaveCount(0);

    await expect(cartPage.cartCount).toHaveText("0");
  });

  test("@smoke @cart added product displays correct subtotal", async ({
    page,
  }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await expect(cartPage.cartRows).toHaveCount(1);

    await cartPage.expectSubtotal("39.90");

    expect(await cartPage.getSubtotal()).toBe(39.9);

    await cartPage.expectTotal("49.89");
  });

  test("@regression @cart quantity can be increased", async ({ page }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.changeQuantity(10, 2);

    await expect(cartPage.quantityInput(10)).toHaveValue("2");

    await expect(cartPage.cartCount).toHaveText("2");

    await cartPage.expectSubtotal("79.80");

    expect(await cartPage.getCartCount()).toBe(2);
  });

  test("@regression @cart product can be removed", async ({ page }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await expect(cartPage.cartRows).toHaveCount(1);

    await cartPage.removeProduct(10);

    await expect(cartPage.emptyCart).toBeVisible();

    await expect(cartPage.cartRows).toHaveCount(0);

    await expect(cartPage.cartCount).toHaveText("0");
  });

  test("@regression @cart cart persists after page refresh", async ({
    page,
  }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await expect(cartPage.cartRows).toHaveCount(1);

    await page.reload();

    await expect(cartPage.cartRows).toHaveCount(1);

    await expect(cartPage.cartCount).toHaveText("1");

    await cartPage.expectSubtotal("39.90");
  });

  test("@regression @cart zero quantity is rejected", async ({ page }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.changeQuantity(10, 0);

    await expect(cartPage.toast).toContainText("Invalid quantity");

    await expect(cartPage.quantityInput(10)).toHaveValue("1");

    await expect(cartPage.cartCount).toHaveText("1");
  });

  test("@regression @cart quantity above available stock is rejected", async ({
    page,
  }) => {
    // Product 2 has stock = 1.
    await addProduct(page, 2);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await expect(cartPage.quantityInput(2)).toHaveValue("1");

    await cartPage.changeQuantity(2, 2);

    await expect(cartPage.quantityInput(2)).toHaveValue("1");

    await expect(cartPage.cartCount).toHaveText("1");
  });

  test("@regression @cart multiple products calculate subtotal correctly", async ({
    page,
  }) => {
    /*
     * Echo Mini Speaker:
     * 2 × $39.90 = $79.80
     *
     * Nomad USB-C Hub:
     * 1 × $89.00 = $89.00
     *
     * Expected subtotal = $168.80
     */
    await addProduct(page, 10, 2);
    await addProduct(page, 11);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await expect(cartPage.cartRows).toHaveCount(2);

    await expect(cartPage.cartCount).toHaveText("3");

    await cartPage.expectSubtotal("168.80");

    /*
     * Subtotal >= $150,
     * therefore shipping is free.
     */
    await cartPage.expectTotal("168.80");
  });

  test("@regression @cart raw decimal price renders correct monetary value", async ({
    page,
  }) => {
    /*
     * Orbit Smart Lamp raw price:
     * 19.999
     *
     * UI should display monetary value:
     * $20.00
     */
    await addProduct(page, 8);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.expectSubtotal("20.00");
  });

  test("@regression @cart guest checkout requires authentication", async ({
    page,
  }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.checkout();

    await expect(page).toHaveURL(/\/checkout$/);

    await expect(cartPage.signInRequired).toBeVisible();
  });
});
