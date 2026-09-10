interface ShippingData {
  firstName: string;
  lastName: string;
  email: string;
  address: string;
  city: string;
  postal: string;
  cardholder: string;
}

const validShipping: ShippingData = {
  firstName: "QA",
  lastName: "Tester",
  email: "qa.user@example.com",
  address: "123 QA Street",
  city: "Cairo",
  postal: "12345",
  cardholder: "QA Tester",
};

function prepareCheckout(): void {
  cy.loginSeedUser();

  cy.visit("/products");

  cy.get('[data-testid="add-cart-10"]').click();

  cy.visit("/checkout");

  cy.contains("h1", "Shipping & payment").should("be.visible");
}

function fillShipping(overrides: Partial<ShippingData> = {}): void {
  const data = {
    ...validShipping,
    ...overrides,
  };

  cy.get("#a").clear().type(data.firstName);

  cy.get("#b").clear().type(data.lastName);

  cy.get("#e").clear().type(data.email);

  cy.get("#ad").clear().type(data.address);

  cy.get("#ci").clear();

  if (data.city) {
    cy.get("#ci").type(data.city);
  }

  cy.get("#po").clear().type(data.postal);

  cy.get("#na").clear().type(data.cardholder);
}

function fillPayment(cardNumber: string, expiry: string, cvv: string): void {
  cy.get('[data-testid="card-number"]').clear().type(cardNumber);

  cy.get("#ex").clear().type(expiry);

  cy.get("#cv").clear().type(cvv);
}

function fillValidPayment(): void {
  fillPayment("4242424242424242", "12/30", "123");
}

describe("Checkout", () => {
  beforeEach(() => {
    prepareCheckout();
  });

  it("prefills authenticated account information", () => {
    cy.get("#a").should("have.value", "QA");

    cy.get("#b").should("have.value", "Tester");

    cy.get("#e").should("have.value", "qa.user@example.com");
  });

  it("rejects missing required shipping field", () => {
    fillShipping({
      city: "",
    });

    fillValidPayment();

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Complete valid shipping fields.");
  });

  it("rejects short address", () => {
    fillShipping({
      address: "1234",
    });

    fillValidPayment();

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Address too short.");
  });

  it("rejects invalid card number", () => {
    fillShipping();

    fillPayment("1234567890123456", "12/30", "123");

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Card failed validation.");
  });

  it("rejects two digit CVV", () => {
    fillShipping();

    fillPayment("4242424242424242", "12/30", "12");

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Expiry/CVV invalid.");
  });

  it("rejects five digit CVV", () => {
    fillShipping();

    fillPayment("4242424242424242", "12/30", "12345");

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Expiry/CVV invalid.");
  });

  it("rejects invalid expiry format", () => {
    fillShipping();

    fillPayment("4242424242424242", "1230", "123");

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Expiry/CVV invalid.");
  });

  it("displays payment declined message", () => {
    fillShipping();

    fillPayment("4000000000000002", "12/30", "123");

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Payment declined.");
  });

  it("displays insufficient funds message", () => {
    fillShipping();

    fillPayment("4000000000009995", "12/30", "123");

    cy.get('[data-testid="place-order"]').click();

    cy.get("#m").should("have.text", "Insufficient funds.");
  });

  it("accepts card number containing spaces", () => {
    fillShipping();

    fillPayment("4242 4242 4242 4242", "12/30", "123");

    cy.get('[data-testid="place-order"]').click();

    cy.get('[data-testid="order-success"]')
      .should("be.visible")
      .and("contain.text", "Order confirmed");
  });

  it("completes valid checkout successfully", () => {
    fillShipping();
    fillValidPayment();

    cy.get('[data-testid="place-order"]').click();

    cy.get('[data-testid="order-success"]')
      .should("be.visible")
      .and("contain.text", "Order confirmed")
      .and("contain.text", "ORD-QA-1001");
  });

  it("prevents duplicate submission while processing", () => {
    fillShipping();
    fillValidPayment();

    cy.get('[data-testid="place-order"]').click();

    cy.get('[data-testid="place-order"]')
      .should("be.disabled")
      .and("contain.text", "Processing");

    cy.get('[data-testid="order-success"]').should("be.visible");
  });

  it("clears persisted cart after successful checkout", () => {
    fillShipping();
    fillValidPayment();

    cy.get('[data-testid="place-order"]').click();

    cy.get('[data-testid="order-success"]').should("be.visible");

    /*
     * BUG-UI-CHK-001 causes the visible header count
     * to remain stale immediately after checkout.
     *
     * Reloading verifies that the actual persisted cart
     * was correctly cleared.
     */
    cy.reload();

    cy.get('[data-testid="cart-count"]').should("have.text", "0");

    cy.visit("/cart");

    cy.get('[data-testid="empty-cart"]').should("be.visible");
  });
});
