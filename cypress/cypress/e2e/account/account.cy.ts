function registerDynamicUser(email: string): void {
  cy.visit("/register");

  cy.get("#a").type("Dynamic");

  cy.get("#b").type("User");

  cy.get("#e").type(email);

  cy.get("#p").type("Password123!", {
    log: false,
  });

  cy.get("#q").type("Password123!", {
    log: false,
  });

  cy.get("#z").check();

  cy.get("form#r button.btn.p").click();

  cy.url().should("include", "/account");
}

describe("Account", () => {
  it("displays seed user account information", () => {
    cy.loginSeedUser();

    cy.visit("/account");

    cy.get("main.wrap h1").should("contain.text", "QA Tester");

    cy.get("main.wrap h1 + p").should("contain.text", "qa.user@example.com");

    cy.get('[data-testid="nav-account"]').should("contain.text", "QA");
  });

  it("logs out successfully", () => {
    cy.loginSeedUser();

    cy.visit("/account");

    cy.get("#lo").should("be.visible").click();

    cy.url().should("eq", `${Cypress.config("baseUrl")}/`);

    cy.get('[data-testid="nav-account"]').should("exist");
  });

  it("shows signed out state when account page is opened without authentication", () => {
    cy.visit("/account");

    cy.contains("Signed out.").should("be.visible");
  });

  it("rejects incorrect delete confirmation", () => {
    const email = `delete-test.${Date.now()}@example.com`;

    registerDynamicUser(email);

    cy.get("#dc").should("be.visible").type("delete");

    cy.get("#de").click();

    /*
     * Correct confirmation must be exactly DELETE.
     */
    cy.get("#m").should("be.visible").and("not.be.empty");

    cy.url().should("include", "/account");

    cy.get("main.wrap h1 + p").should("contain.text", email);
  });

  it("rejects empty delete confirmation", () => {
    const email = `empty-delete.${Date.now()}@example.com`;

    registerDynamicUser(email);

    cy.get("#de").click();

    cy.get("#m").should("be.visible").and("not.be.empty");

    cy.url().should("include", "/account");
  });

  it("deletes a dynamically registered account with exact DELETE confirmation", () => {
    const email = `deleted.${Date.now()}@example.com`;

    registerDynamicUser(email);

    cy.get("main.wrap h1 + p").should("contain.text", email);

    cy.get("#dc").type("DELETE");

    cy.get("#de").click();

    cy.url().should("eq", `${Cypress.config("baseUrl")}/`);

    cy.visit("/account");

    cy.contains("Signed out.").should("be.visible");
  });

  it("deleted dynamic account can no longer log in", () => {
    const email = `removed.${Date.now()}@example.com`;

    registerDynamicUser(email);

    cy.get("#dc").type("DELETE");

    cy.get("#de").click();

    cy.url().should("eq", `${Cypress.config("baseUrl")}/`);

    cy.visit("/login");

    cy.get('[data-testid="login-email"]').type(email);

    cy.get('[data-testid="login-password"]').type("Password123!", {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.get("#m").should("have.text", "Invalid credentials.");

    cy.url().should("include", "/login");
  });

  it("seed account remains available after deletion attempt", () => {
    cy.loginSeedUser();

    cy.visit("/account");

    cy.get("#dc").type("DELETE");

    cy.get("#de").click();

    cy.url().should("eq", `${Cypress.config("baseUrl")}/`);

    /*
     * The deletion action logs the seed user out,
     * but the protected seed account is not removed.
     */
    cy.visit("/login");

    cy.get('[data-testid="login-email"]').type("qa.user@example.com");

    cy.get('[data-testid="login-password"]').type("Password123!", {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.url().should("include", "/account");

    cy.get("main.wrap h1").should("contain.text", "QA Tester");
  });
});
