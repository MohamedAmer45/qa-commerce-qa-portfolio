import { expect, Locator, Page } from "@playwright/test";

export class RegistrationPage {
  readonly page: Page;

  readonly firstNameInput: Locator;
  readonly lastNameInput: Locator;
  readonly emailInput: Locator;
  readonly passwordInput: Locator;
  readonly confirmPasswordInput: Locator;
  readonly termsCheckbox: Locator;
  readonly createAccountButton: Locator;
  readonly message: Locator;
  readonly accountNavigation: Locator;

  constructor(page: Page) {
    this.page = page;

    this.firstNameInput = page.locator("#a");
    this.lastNameInput = page.locator("#b");
    this.emailInput = page.locator("#e");
    this.passwordInput = page.locator("#p");
    this.confirmPasswordInput = page.locator("#q");
    this.termsCheckbox = page.locator("#z");

    this.createAccountButton = page.locator("form#r button.btn.p");

    this.message = page.locator("#m");

    this.accountNavigation = page.getByTestId("nav-account");
  }

  async open(): Promise<void> {
    await this.page.goto("/register");

    await expect(this.firstNameInput).toBeVisible();
  }

  async register(options: {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    confirmPassword?: string;
    acceptTerms?: boolean;
  }): Promise<void> {
    await this.firstNameInput.fill(options.firstName);

    await this.lastNameInput.fill(options.lastName);

    await this.emailInput.fill(options.email);

    await this.passwordInput.fill(options.password);

    await this.confirmPasswordInput.fill(
      options.confirmPassword ?? options.password,
    );

    if (options.acceptTerms ?? true) {
      await this.termsCheckbox.check();
    }

    await this.createAccountButton.click();
  }

  async expectMessage(expected: string): Promise<void> {
    await expect(this.message).toHaveText(expected);
  }

  async expectRegistrationSuccess(): Promise<void> {
    await expect(this.page).toHaveURL(/\/account$/);

    await expect(this.accountNavigation).toBeVisible();
  }
}
