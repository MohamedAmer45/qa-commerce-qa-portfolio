import { test, expect } from "@playwright/test";

test.describe("Home Page", () => {
  test("@smoke home page loads successfully", async ({ page }) => {
    await page.goto("/");

    await expect(page).toHaveTitle(/QA Commerce Lab/i);

    await expect(page.getByTestId("hero-shop")).toBeVisible();
  });

  test("@smoke user can navigate from home to products", async ({ page }) => {
    await page.goto("/");

    await page.getByTestId("hero-shop").click();

    await expect(page).toHaveURL(/\/products$/);

    await expect(page.getByTestId("product-search")).toBeVisible();
  });
});
