import { expect, Locator, Page } from "@playwright/test";

export class CartPage {
  readonly page: Page;

  readonly heading: Locator;
  readonly emptyCart: Locator;
  readonly cartRows: Locator;
  readonly subtotal: Locator;
  readonly total: Locator;
  readonly cartCount: Locator;
  readonly checkoutLink: Locator;
  readonly toast: Locator;

  readonly couponInput: Locator;
  readonly applyCouponButton: Locator;
  readonly couponMessage: Locator;

  readonly signInRequired: Locator;

  constructor(page: Page) {
    this.page = page;

    this.heading = page.getByRole("heading", {
      name: "Your basket",
    });

    this.emptyCart = page.getByTestId("empty-cart");

    this.cartRows = page.locator(".cartrow");

    this.subtotal = page.locator("#sub");
    this.total = page.locator("#tot");

    this.cartCount = page.getByTestId("cart-count");

    this.checkoutLink = page.locator("a[href='/checkout']");

    this.toast = page.locator(".toast");

    this.couponInput = page.locator("#cp");
    this.applyCouponButton = page.locator("#ap");
    this.couponMessage = page.locator("#cm");

    this.signInRequired = page.getByText(/sign in required/i);
  }

  async open(): Promise<void> {
    await this.page.goto("/cart");

    await expect(this.heading).toBeVisible();
  }

  quantityInput(productId: number): Locator {
    return this.page.locator(`input.cq[data-id="${productId}"]`);
  }

  removeButton(productId: number): Locator {
    return this.page.locator(`button.rm[data-id="${productId}"]`);
  }

  async changeQuantity(productId: number, quantity: number): Promise<void> {
    const input = this.quantityInput(productId);

    await input.fill(String(quantity));

    /*
     * Quantity validation runs when the field loses focus.
     * Clicking the heading triggers blur/change.
     *
     * Playwright Locators automatically reacquire the
     * element if the cart row is rerendered.
     */
    await this.heading.click();
  }

  async removeProduct(productId: number): Promise<void> {
    await this.removeButton(productId).click();
  }

  async getCartCount(): Promise<number> {
    const text = (await this.cartCount.textContent())?.trim() ?? "0";

    return Number(text);
  }

  async getRowCount(): Promise<number> {
    return this.cartRows.count();
  }

  async getSubtotal(): Promise<number> {
    return this.parseMoney(await this.subtotal.textContent());
  }

  async getTotal(): Promise<number> {
    return this.parseMoney(await this.total.textContent());
  }

  async applyCoupon(code: string): Promise<void> {
    await this.couponInput.fill(code);
    await this.applyCouponButton.click();
  }

  async expectSubtotal(expected: string): Promise<void> {
    await expect(this.subtotal).toHaveText(`$${expected}`);
  }

  async expectTotal(expected: string): Promise<void> {
    await expect(this.total).toHaveText(`$${expected}`);
  }

  async expectCouponMessage(expected: string): Promise<void> {
    await expect(this.couponMessage).toHaveText(expected);
  }

  async checkout(): Promise<void> {
    await this.checkoutLink.click();
  }

  private parseMoney(value: string | null): number {
    if (!value) {
      return 0;
    }

    return Number(value.replace("$", "").replace(/,/g, "").trim());
  }
}
