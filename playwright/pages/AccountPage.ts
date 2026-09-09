import { expect, Locator, Page } from "@playwright/test";

export class AccountPage {
  readonly page: Page;

  readonly heading: Locator;
  readonly email: Locator;
  readonly logoutButton: Locator;

  readonly deleteConfirmationInput: Locator;
  readonly deleteAccountButton: Locator;
  readonly message: Locator;

  readonly accountNavigation: Locator;
  readonly signedOutMessage: Locator;

  constructor(page: Page) {
    this.page = page;

    this.heading = page.locator("main.wrap h1");

    this.email = page.locator("main.wrap h1 + p");

    this.logoutButton = page.locator("#lo");

    this.deleteConfirmationInput = page.locator("#dc");

    this.deleteAccountButton = page.locator("#de");

    this.message = page.locator("#m");

    this.accountNavigation = page.getByTestId("nav-account");

    this.signedOutMessage = page.getByText("Signed out.", {
      exact: true,
    });
  }

  async open(): Promise<void> {
    await this.page.goto("/account");
  }

  async logout(): Promise<void> {
    await this.logoutButton.click();

    await expect(this.page).toHaveURL("/");
  }

  async deleteAccount(confirmation: string): Promise<void> {
    await this.deleteConfirmationInput.fill(confirmation);

    await this.deleteAccountButton.click();
  }

  async expectAccountDetails(name: string, email: string): Promise<void> {
    await expect(this.heading).toHaveText(name);
    await expect(this.email).toHaveText(email);
  }

  async expectDeleteMessage(expected: string): Promise<void> {
    await expect(this.message).toHaveText(expected);
  }
}
