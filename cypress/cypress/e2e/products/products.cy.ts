describe("Product Catalog", () => {
  beforeEach(() => {
    cy.visit("/products");

    cy.get('[data-testid="product-search"]').should("be.visible");
  });

  it("displays all 12 products", () => {
    cy.get('[data-testid^="product-card-"]').should("have.length", 12);
  });

  it("search finds Mechanical Keyboard", () => {
    cy.get('[data-testid="product-search"]').type("keyboard");

    cy.get('[data-testid^="product-card-"]').should("have.length", 1);

    cy.get('[data-testid="product-card-2"]')
      .should("be.visible")
      .and("contain.text", "Pulse 75 Mechanical Keyboard");
  });

  it("search is case insensitive", () => {
    cy.get('[data-testid="product-search"]').type("KEYBOARD");

    cy.get('[data-testid^="product-card-"]').should("have.length", 1);

    cy.get('[data-testid="product-card-2"]').should("be.visible");
  });

  it("search trims whitespace", () => {
    cy.get('[data-testid="product-search"]').type("   keyboard   ");

    cy.get('[data-testid^="product-card-"]').should("have.length", 1);

    cy.get('[data-testid="product-card-2"]').should("be.visible");
  });

  it("displays empty state when no products match", () => {
    cy.get('[data-testid="product-search"]').type(
      "this-product-does-not-exist",
    );

    cy.get('[data-testid^="product-card-"]').should("have.length", 0);

    cy.get('[data-testid="empty-products"]').should("be.visible");
  });

  it("filters Accessories category", () => {
    cy.get("#c").select("Accessories");

    cy.get('[data-testid^="product-card-"]').should("have.length", 3);

    cy.get('[data-testid="product-card-2"]').should("be.visible");

    cy.get('[data-testid="product-card-5"]').should("be.visible");

    cy.get('[data-testid="product-card-11"]').should("be.visible");
  });

  it("sorts products by price ascending", () => {
    cy.get("#o").select("Price ↑");

    cy.get('[data-testid^="product-card-"]').then(($cards) => {
      const prices = [...$cards].map((card) => {
        const text = card.textContent ?? "";

        const match = text.match(/\$([\d,]+(?:\.\d{1,2})?)/);

        if (!match) {
          throw new Error(`Price not found in card: ${text}`);
        }

        return Number(match[1].replace(/,/g, ""));
      });

      const sorted = [...prices].sort((a, b) => a - b);

      expect(prices).to.deep.equal(sorted);
    });
  });

  it("sorts products by price descending", () => {
    cy.get("#o").select("Price ↓");

    cy.get('[data-testid^="product-card-"]').then(($cards) => {
      const prices = [...$cards].map((card) => {
        const text = card.textContent ?? "";

        const match = text.match(/\$([\d,]+(?:\.\d{1,2})?)/);

        if (!match) {
          throw new Error(`Price not found in card: ${text}`);
        }

        return Number(match[1].replace(/,/g, ""));
      });

      const sorted = [...prices].sort((a, b) => b - a);

      expect(prices).to.deep.equal(sorted);
    });
  });

  it("renders Unicode product correctly", () => {
    cy.get('[data-testid="product-search"]').type("Café Élan");

    cy.get('[data-testid="product-card-4"]')
      .should("contain.text", "Café Élan Travel Mug")
      .and("contain.text", "إصدار محدود");
  });

  it("disables Add to Cart for out-of-stock product", () => {
    cy.get('[data-testid="add-cart-3"]').should("be.disabled");
  });

  it("renders zero-price product correctly", () => {
    cy.get('[data-testid="product-card-7"]')
      .should("contain.text", "QA Sticker Pack")
      .and("contain.text", "$0.00");
  });

  it("adds available product to cart", () => {
    cy.get('[data-testid="cart-count"]').should("have.text", "0");

    cy.get('[data-testid="add-cart-10"]').click();

    cy.get('[data-testid="cart-count"]').should("have.text", "1");
  });
});
