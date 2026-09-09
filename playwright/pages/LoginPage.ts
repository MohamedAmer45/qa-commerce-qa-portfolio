import { expect, Locator, Page } from "@playwright/test";

export class LoginPage {
  readonly page: Page;

  readonly emailInput: Locator;
  readonly passwordInput: Locator;
  readonly loginButton: Locator;
  readonly message: Locator;
  readonly accountNavigation: Locator;

  constructor(page: Page) {
    this.page = page;

    this.emailInput = page.getByTestId("login-email");
    this.passwordInput = page.getByTestId("login-password");
    this.loginButton = page.getByTestId("login-submit");

    this.message = page.locator("#m");
    this.accountNavigation = page.getByTestId("nav-account");
  }

  async open(): Promise<void> {
    await this.page.goto("/login");

    await expect(this.emailInput).toBeVisible();
    await expect(this.passwordInput).toBeVisible();
  }

  async enterEmail(email: string): Promise<void> {
    await this.emailInput.fill(email);
  }

  async enterPassword(password: string): Promise<void> {
    await this.passwordInput.fill(password);
  }

  async submit(): Promise<void> {
    await this.loginButton.click();
  }

  async login(email: string, password: string): Promise<void> {
    await this.enterEmail(email);
    await this.enterPassword(password);
    await this.submit();
  }

  async expectSuccessfulLogin(): Promise<void> {
    await expect(this.page).toHaveURL(/\/account$/);
    await expect(this.accountNavigation).toBeVisible();
  }

  async expectMessage(expectedMessage: string): Promise<void> {
    await expect(this.message).toHaveText(expectedMessage);
  }

  async getAccountNavigationText(): Promise<string> {
    return (await this.accountNavigation.textContent())?.trim() ?? "";
  }
}
