function addProduct(productId: number): void {
  cy.visit("/products");

  cy.get(`[data-testid="add-cart-${productId}"]`).click();

  cy.visit("/cart");
}

describe("Coupons", () => {
  it("SAVE10 applies ten percent discount", () => {
    addProduct(2);

    cy.get("#sub").should("have.text", "$129.99");

    cy.get("#cp").type("SAVE10");

    cy.get("#ap").click();

    cy.get("#tot").should("have.text", "$126.98");
  });

  it("FREESHIP removes shipping cost", () => {
    addProduct(10);

    cy.get("#tot").should("have.text", "$49.89");

    cy.get("#cp").type("FREESHIP");

    cy.get("#ap").click();

    cy.get("#tot").should("have.text", "$39.90");
  });

  it("MIN100 applies fifteen dollar discount", () => {
    addProduct(2);

    cy.get("#cp").type("MIN100");

    cy.get("#ap").click();

    cy.get("#tot").should("have.text", "$124.98");
  });

  it("MIN100 rejects subtotal below minimum", () => {
    addProduct(10);

    cy.get("#cp").type("MIN100");

    cy.get("#ap").click();

    cy.get("#cm").should("have.text", "Minimum $100 required.");

    cy.get("#tot").should("have.text", "$49.89");
  });

  it("rejects expired coupon", () => {
    addProduct(10);

    cy.get("#cp").type("EXPIRED");

    cy.get("#ap").click();

    cy.get("#cm").should("have.text", "Expired coupon.");

    cy.get("#tot").should("have.text", "$49.89");
  });

  it("rejects unknown coupon", () => {
    addProduct(10);

    cy.get("#cp").type("NOTREAL");

    cy.get("#ap").click();

    cy.get("#cm").should("have.text", "Coupon not found.");
  });

  it("coupon code is case insensitive", () => {
    addProduct(10);

    cy.get("#cp").type("freeship");

    cy.get("#ap").click();

    cy.get("#tot").should("have.text", "$39.90");
  });

  it("coupon code trims whitespace", () => {
    addProduct(10);

    cy.get("#cp").type("   FREESHIP   ");

    cy.get("#ap").click();

    cy.get("#tot").should("have.text", "$39.90");
  });

  it("automatically applies free shipping above 150 subtotal", () => {
    cy.visit("/products");

    cy.get('[data-testid="add-cart-2"]').click();

    cy.get('[data-testid="add-cart-10"]').click();

    cy.visit("/cart");

    cy.get("#sub").should("have.text", "$169.89");

    cy.get("#tot").should("have.text", "$169.89");
  });
});
