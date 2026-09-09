import { expect, Locator, Page } from "@playwright/test";

export class ProductsPage {
  readonly page: Page;

  readonly searchInput: Locator;
  readonly categorySelect: Locator;
  readonly sortSelect: Locator;
  readonly resultCount: Locator;
  readonly emptyProducts: Locator;
  readonly cartCount: Locator;
  readonly productCards: Locator;

  constructor(page: Page) {
    this.page = page;

    this.searchInput = page.getByTestId("product-search");
    this.categorySelect = page.locator("#c");
    this.sortSelect = page.locator("#o");
    this.resultCount = page.locator("#cnt");

    this.emptyProducts = page.getByTestId("empty-products");
    this.cartCount = page.getByTestId("cart-count");

    this.productCards = page.locator("[data-testid^='product-card-']");
  }

  async open(): Promise<void> {
    await this.page.goto("/products");

    await expect(this.searchInput).toBeVisible();
  }

  productCard(productId: number): Locator {
    return this.page.getByTestId(`product-card-${productId}`);
  }

  addButton(productId: number): Locator {
    return this.page.getByTestId(`add-cart-${productId}`);
  }

  async search(value: string): Promise<void> {
    await this.searchInput.fill(value);
  }

  async selectCategory(category: string): Promise<void> {
    await this.categorySelect.selectOption({
      label: category,
    });
  }

  async selectSort(option: string): Promise<void> {
    await this.sortSelect.selectOption({
      label: option,
    });
  }

  async getVisibleProductCount(): Promise<number> {
    return this.productCards.count();
  }

  async expectProductVisible(productId: number): Promise<void> {
    await expect(this.productCard(productId)).toBeVisible();
  }

  async expectProductHidden(productId: number): Promise<void> {
    await expect(this.productCard(productId)).toBeHidden();
  }

  async getProductText(productId: number): Promise<string> {
    return (await this.productCard(productId).textContent())?.trim() ?? "";
  }

  async addProduct(productId: number): Promise<void> {
    await this.addButton(productId).click();
  }

  async getCartCount(): Promise<number> {
    const text = (await this.cartCount.textContent())?.trim() ?? "0";

    return Number(text);
  }

  async getVisiblePrices(): Promise<number[]> {
    const cards = this.productCards;

    const count = await cards.count();

    const prices: number[] = [];

    for (let index = 0; index < count; index++) {
      const text = (await cards.nth(index).textContent()) ?? "";

      const match = text.match(/\$([\d,]+(?:\.\d{1,2})?)/);

      if (!match) {
        throw new Error(`Price not found in product card: ${text}`);
      }

      prices.push(Number(match[1].replace(/,/g, "")));
    }

    return prices;
  }
}
