import { expect, Locator, Page } from "@playwright/test";

export class CheckoutPage {
  readonly page: Page;

  readonly heading: Locator;

  readonly firstNameInput: Locator;
  readonly lastNameInput: Locator;
  readonly emailInput: Locator;
  readonly addressInput: Locator;
  readonly cityInput: Locator;
  readonly postalInput: Locator;

  readonly cardholderInput: Locator;
  readonly cardNumberInput: Locator;
  readonly expiryInput: Locator;
  readonly cvvInput: Locator;

  readonly placeOrderButton: Locator;
  readonly message: Locator;
  readonly orderSuccess: Locator;
  readonly cartCount: Locator;

  constructor(page: Page) {
    this.page = page;

    this.heading = page.getByRole("heading", {
      name: "Shipping & payment",
    });

    this.firstNameInput = page.locator("#a");
    this.lastNameInput = page.locator("#b");
    this.emailInput = page.locator("#e");
    this.addressInput = page.locator("#ad");
    this.cityInput = page.locator("#ci");
    this.postalInput = page.locator("#po");

    this.cardholderInput = page.locator("#na");
    this.cardNumberInput = page.getByTestId("card-number");
    this.expiryInput = page.locator("#ex");
    this.cvvInput = page.locator("#cv");

    this.placeOrderButton = page.getByTestId("place-order");

    this.message = page.locator("#m");

    this.orderSuccess = page.getByTestId("order-success");

    this.cartCount = page.getByTestId("cart-count");
  }

  async open(): Promise<void> {
    await this.page.goto("/checkout");

    await expect(this.heading).toBeVisible();
  }

  async fillShipping(options?: {
    firstName?: string;
    lastName?: string;
    email?: string;
    address?: string;
    city?: string;
    postal?: string;
    cardholder?: string;
  }): Promise<void> {
    await this.firstNameInput.fill(options?.firstName ?? "QA");

    await this.lastNameInput.fill(options?.lastName ?? "Tester");

    await this.emailInput.fill(options?.email ?? "qa.user@example.com");

    await this.addressInput.fill(options?.address ?? "123 QA Street");

    await this.cityInput.fill(options?.city ?? "Cairo");

    await this.postalInput.fill(options?.postal ?? "12345");

    await this.cardholderInput.fill(options?.cardholder ?? "QA Tester");
  }

  async fillPayment(
    cardNumber: string,
    expiry: string,
    cvv: string,
  ): Promise<void> {
    await this.cardNumberInput.fill(cardNumber);
    await this.expiryInput.fill(expiry);
    await this.cvvInput.fill(cvv);
  }

  async fillValidPayment(): Promise<void> {
    await this.fillPayment("4242424242424242", "12/30", "123");
  }

  async submitOrder(): Promise<void> {
    await this.placeOrderButton.click();
  }

  async expectMessage(expected: string): Promise<void> {
    await expect(this.message).toHaveText(expected);
  }

  async expectOrderSuccess(): Promise<void> {
    await expect(this.orderSuccess).toBeVisible();
  }

  async getOrderSuccessText(): Promise<string> {
    return (await this.orderSuccess.textContent())?.trim() ?? "";
  }
}
