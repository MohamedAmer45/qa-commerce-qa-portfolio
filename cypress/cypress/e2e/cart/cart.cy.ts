function addProduct(productId: number, times = 1): void {
  cy.visit("/products");

  for (let i = 0; i < times; i++) {
    cy.get(`[data-testid="add-cart-${productId}"]`).click();
  }
}

describe("Shopping Cart", () => {
  it("displays empty cart state", () => {
    cy.visit("/cart");

    cy.get('[data-testid="empty-cart"]').should("be.visible");

    cy.get(".cartrow").should("have.length", 0);

    cy.get('[data-testid="cart-count"]').should("have.text", "0");
  });

  it("displays correct subtotal for added product", () => {
    addProduct(10);

    cy.visit("/cart");

    cy.get(".cartrow").should("have.length", 1);

    cy.get("#sub").should("have.text", "$39.90");

    cy.get("#tot").should("have.text", "$49.89");
  });

  it("updates product quantity and totals", () => {
    addProduct(10);

    cy.visit("/cart");

    cy.get('input.cq[data-id="10"]').type("{selectall}2").blur();

    /*
     * The cart row rerenders after onchange,
     * so reacquire the element instead of chaining
     * assertions onto the original input.
     */
    cy.get('input.cq[data-id="10"]').should("have.value", "2");

    cy.get('[data-testid="cart-count"]').should("have.text", "2");

    cy.get("#sub").should("have.text", "$79.80");
  });

  it("removes product from cart", () => {
    addProduct(10);

    cy.visit("/cart");

    cy.get(".cartrow").should("have.length", 1);

    cy.get('button.rm[data-id="10"]').click();

    cy.get('[data-testid="empty-cart"]').should("be.visible");

    cy.get(".cartrow").should("have.length", 0);

    cy.get('[data-testid="cart-count"]').should("have.text", "0");
  });

  it("persists cart after refresh", () => {
    addProduct(10);

    cy.visit("/cart");

    cy.get(".cartrow").should("have.length", 1);

    cy.reload();

    cy.get(".cartrow").should("have.length", 1);

    cy.get('[data-testid="cart-count"]').should("have.text", "1");

    cy.get("#sub").should("have.text", "$39.90");
  });

  it("rejects zero quantity", () => {
    addProduct(10);

    cy.visit("/cart");

    cy.get('input.cq[data-id="10"]').type("{selectall}0").blur();

    cy.get(".toast").should("contain.text", "Invalid quantity");

    cy.get('input.cq[data-id="10"]').should("have.value", "1");

    cy.get('[data-testid="cart-count"]').should("have.text", "1");
  });

  it("rejects quantity above stock", () => {
    /*
     * Product 2 stock = 1.
     */
    addProduct(2);

    cy.visit("/cart");

    cy.get('input.cq[data-id="2"]')
      .should("have.value", "1")
      .type("{selectall}2")
      .blur();

    cy.get('input.cq[data-id="2"]').should("have.value", "1");

    cy.get('[data-testid="cart-count"]').should("have.text", "1");
  });

  it("calculates multi-product subtotal correctly", () => {
    /*
     * Product 10:
     * 2 × $39.90 = $79.80
     *
     * Product 11:
     * 1 × $89.00 = $89.00
     *
     * Total = $168.80
     */
    addProduct(10, 2);

    cy.visit("/products");

    cy.get('[data-testid="add-cart-11"]').click();

    cy.visit("/cart");

    cy.get(".cartrow").should("have.length", 2);

    cy.get('[data-testid="cart-count"]').should("have.text", "3");

    cy.get("#sub").should("have.text", "$168.80");

    /*
     * Shipping is free when subtotal >= $150.
     */
    cy.get("#tot").should("have.text", "$168.80");
  });

  it("rounds raw decimal monetary value correctly", () => {
    /*
     * Orbit Smart Lamp raw price = 19.999.
     */
    addProduct(8);

    cy.visit("/cart");

    cy.get("#sub").should("have.text", "$20.00");
  });

  it("requires authentication for guest checkout", () => {
    addProduct(10);

    cy.visit("/cart");

    cy.get("a[href='/checkout']").click();

    cy.url().should("include", "/checkout");

    cy.contains(/sign in required/i).should("be.visible");
  });
});
