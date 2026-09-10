function prepareAuthenticatedCheckout(): void {
  cy.loginSeedUser();

  cy.visit("/products");

  cy.get('[data-testid="add-cart-10"]').click();

  cy.visit("/checkout");

  cy.get("#a").clear().type("QA");
  cy.get("#b").clear().type("Tester");
  cy.get("#e").clear().type("qa.user@example.com");

  cy.get("#ad").clear().type("123 QA Street");

  cy.get("#ci").clear().type("Cairo");

  cy.get("#po").clear().type("12345");

  cy.get("#na").clear().type("QA Tester");

  cy.get('[data-testid="card-number"]').clear().type("4242424242424242");

  cy.get("#cv").clear().type("123");
}

describe("Known UI Defects", () => {
  it("BUG-UI-CART-001 - Sticker Pack quantity cannot exceed 25", () => {
    cy.visit("/products");

    cy.get('[data-testid="add-cart-7"]').click();

    cy.visit("/cart");

    cy.get('input.cq[data-id="7"]').type("{selectall}26").blur();

    /*
     * Correct business behavior:
     * Sticker Pack quantity must never exceed 25.
     *
     * This currently fails because the cart editor
     * validates against stock=500 instead.
     */
    cy.get('input.cq[data-id="7"]')
      .invoke("val")
      .then((value) => {
        expect(Number(value)).to.be.at.most(25);
      });
  });

  it("BUG-UI-CHK-002 - impossible expiry month is rejected", () => {
    prepareAuthenticatedCheckout();

    cy.get("#ex").clear().type("13/30");

    cy.get('[data-testid="place-order"]').click();

    /*
     * Correct behavior:
     * 13 is not a valid month.
     */
    cy.get("#m").should("have.text", "Expiry/CVV invalid.");

    cy.get('[data-testid="order-success"]').should("not.exist");
  });

  it("BUG-UI-CHK-001 - cart counter updates immediately after successful checkout", () => {
    prepareAuthenticatedCheckout();

    cy.get("#ex").clear().type("12/30");

    cy.get('[data-testid="cart-count"]').should("have.text", "1");

    cy.get('[data-testid="place-order"]').click();

    cy.get('[data-testid="order-success"]').should("be.visible");

    /*
     * Correct behavior:
     * persisted cart AND visible navigation counter
     * should both update immediately.
     *
     * Currently the stored cart is cleared but the
     * header remains stale until refresh.
     */
    cy.get('[data-testid="cart-count"]').should("have.text", "0");
  });
});
