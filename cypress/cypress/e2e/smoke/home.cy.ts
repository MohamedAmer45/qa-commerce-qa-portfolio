describe("Home Page", () => {
  it("loads successfully", () => {
    cy.visit("/");

    cy.get('[data-testid="hero-shop"]').should("be.visible");
  });

  it("navigates to products", () => {
    cy.visit("/");

    cy.get('[data-testid="hero-shop"]').click();

    cy.url().should("include", "/products");

    cy.get('[data-testid="product-search"]').should("be.visible");
  });
});
