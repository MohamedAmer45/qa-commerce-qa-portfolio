import { test, expect, Page } from "@playwright/test";
import { CartPage } from "../../pages/CartPage";
import { ProductsPage } from "../../pages/ProductsPage";

async function addProduct(page: Page, productId: number): Promise<void> {
  const productsPage = new ProductsPage(page);

  await productsPage.open();
  await productsPage.addProduct(productId);
}

test.describe("Coupons", () => {
  test("@regression @coupon SAVE10 applies ten percent discount", async ({
    page,
  }) => {
    /*
     * Product 2 subtotal:
     * $129.99
     *
     * 10% discount:
     * $12.999
     *
     * Shipping:
     * $9.99
     *
     * Displayed total:
     * $126.98
     */
    await addProduct(page, 2);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.expectSubtotal("129.99");

    await cartPage.applyCoupon("SAVE10");

    await cartPage.expectTotal("126.98");
  });

  test("@regression @coupon FREESHIP removes shipping cost", async ({
    page,
  }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.expectSubtotal("39.90");

    // $39.90 + $9.99 shipping
    await cartPage.expectTotal("49.89");

    await cartPage.applyCoupon("FREESHIP");

    await cartPage.expectTotal("39.90");
  });

  test("@regression @coupon MIN100 applies fifteen dollar discount", async ({
    page,
  }) => {
    await addProduct(page, 2);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.applyCoupon("MIN100");

    /*
     * $129.99
     * - $15.00
     * + $9.99 shipping
     * = $124.98
     */
    await cartPage.expectTotal("124.98");
  });

  test("@regression @coupon MIN100 rejects subtotal below minimum", async ({
    page,
  }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.applyCoupon("MIN100");

    await cartPage.expectCouponMessage("Minimum $100 required.");

    await cartPage.expectTotal("49.89");
  });

  test("@regression @coupon expired coupon is rejected", async ({ page }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.applyCoupon("EXPIRED");

    await cartPage.expectCouponMessage("Expired coupon.");

    await cartPage.expectTotal("49.89");
  });

  test("@regression @coupon invalid coupon is rejected", async ({ page }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.applyCoupon("NOTREAL");

    await cartPage.expectCouponMessage("Coupon not found.");

    await cartPage.expectTotal("49.89");
  });

  test("@regression @coupon coupon code is case insensitive", async ({
    page,
  }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.applyCoupon("freeship");

    await cartPage.expectTotal("39.90");
  });

  test("@regression @coupon coupon code trims whitespace", async ({ page }) => {
    await addProduct(page, 10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    await cartPage.applyCoupon("   FREESHIP   ");

    await cartPage.expectTotal("39.90");
  });

  test("@regression @coupon orders above 150 receive free shipping automatically", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.open();

    await productsPage.addProduct(2);
    await productsPage.addProduct(10);

    const cartPage = new CartPage(page);

    await cartPage.open();

    /*
     * $129.99 + $39.90
     * = $169.89
     *
     * >= $150 means free shipping.
     */
    await cartPage.expectSubtotal("169.89");
    await cartPage.expectTotal("169.89");

    expect(await cartPage.getTotal()).toBe(169.89);
  });
});
