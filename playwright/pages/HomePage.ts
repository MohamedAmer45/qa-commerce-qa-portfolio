import { expect, Locator, Page } from "@playwright/test";

export class HomePage {
  readonly page: Page;
  readonly shopButton: Locator;
  readonly qaLabButton: Locator;
  readonly accountNavigation: Locator;
  readonly cartNavigation: Locator;

  constructor(page: Page) {
    this.page = page;

    this.shopButton = page.getByTestId("hero-shop");
    this.qaLabButton = page.getByTestId("hero-qa-lab");
    this.accountNavigation = page.getByTestId("nav-account");
    this.cartNavigation = page.getByTestId("nav-cart");
  }

  async open(): Promise<void> {
    await this.page.goto("/");
  }

  async expectLoaded(): Promise<void> {
    await expect(this.shopButton).toBeVisible();
  }

  async openProducts(): Promise<void> {
    await this.shopButton.click();
    await expect(this.page).toHaveURL(/\/products$/);
  }

  async openQALab(): Promise<void> {
    await this.qaLabButton.click();
    await expect(this.page).toHaveURL(/\/qa-lab$/);
  }

  async getAccountNavigationText(): Promise<string> {
    return (await this.accountNavigation.textContent())?.trim() ?? "";
  }
}
