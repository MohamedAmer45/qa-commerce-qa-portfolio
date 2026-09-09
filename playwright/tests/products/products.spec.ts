import { test, expect } from "@playwright/test";
import { ProductsPage } from "../../pages/ProductsPage";

test.describe("Product Catalog", () => {
  test.beforeEach(async ({ page }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.open();
  });

  test("@smoke @products catalog displays all 12 products", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await expect(productsPage.productCards).toHaveCount(12);
  });

  test("@regression @products search finds Mechanical Keyboard", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.search("keyboard");

    await expect(productsPage.productCards).toHaveCount(1);

    await productsPage.expectProductVisible(2);

    await expect(productsPage.productCard(2)).toContainText(
      "Pulse 75 Mechanical Keyboard",
    );
  });

  test("@regression @products search is case insensitive", async ({ page }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.search("KEYBOARD");

    await expect(productsPage.productCards).toHaveCount(1);

    await productsPage.expectProductVisible(2);
  });

  test("@regression @products search trims surrounding whitespace", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.search("   keyboard   ");

    await expect(productsPage.productCards).toHaveCount(1);

    await productsPage.expectProductVisible(2);
  });

  test("@regression @products no matching search displays empty state", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.search("product-that-does-not-exist");

    await expect(productsPage.productCards).toHaveCount(0);

    await expect(productsPage.emptyProducts).toBeVisible();
  });

  test("@regression @products Accessories category displays three products", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.selectCategory("Accessories");

    await expect(productsPage.productCards).toHaveCount(3);

    await productsPage.expectProductVisible(2);
    await productsPage.expectProductVisible(5);
    await productsPage.expectProductVisible(11);
  });

  test("@regression @products products sort by price ascending", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.selectSort("Price ↑");

    const prices = await productsPage.getVisiblePrices();

    const sortedPrices = [...prices].sort((a, b) => a - b);

    expect(prices).toEqual(sortedPrices);
  });

  test("@regression @products products sort by price descending", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.selectSort("Price ↓");

    const prices = await productsPage.getVisiblePrices();

    const sortedPrices = [...prices].sort((a, b) => b - a);

    expect(prices).toEqual(sortedPrices);
  });

  test("@regression @products Unicode product renders correctly", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.search("Café Élan");

    await productsPage.expectProductVisible(4);

    await expect(productsPage.productCard(4)).toContainText(
      "Café Élan Travel Mug",
    );

    await expect(productsPage.productCard(4)).toContainText("إصدار محدود");
  });

  test("@regression @products out-of-stock product cannot be added to cart", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.expectProductVisible(3);

    await expect(productsPage.addButton(3)).toBeDisabled();
  });

  test("@regression @products zero-price product displays correctly", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await productsPage.expectProductVisible(7);

    const text = await productsPage.getProductText(7);

    expect(text).toContain("QA Sticker Pack");

    expect(text).toContain("$0.00");
  });

  test("@smoke @products @cart available product can be added to cart", async ({
    page,
  }) => {
    const productsPage = new ProductsPage(page);

    await expect(productsPage.cartCount).toHaveText("0");

    await productsPage.addProduct(10);

    await expect(productsPage.cartCount).toHaveText("1");

    expect(await productsPage.getCartCount()).toBe(1);
  });
});
