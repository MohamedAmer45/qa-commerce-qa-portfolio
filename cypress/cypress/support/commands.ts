declare global {
  namespace Cypress {
    interface Chainable {
      loginSeedUser(): Chainable<void>;
    }
  }
}

Cypress.Commands.add("loginSeedUser", () => {
  const email = "qa.user@example.com";
  const password = "Password123!";

  cy.session(
    ["seed-user", email],
    () => {
      cy.visit("/login");

      cy.get('[data-testid="login-email"]').should("be.visible").type(email);

      cy.get('[data-testid="login-password"]').type(password, {
        log: false,
      });

      cy.get('[data-testid="login-submit"]').click();

      cy.url().should("include", "/account");

      cy.get('[data-testid="nav-account"]').should("contain.text", "QA");
    },
    {
      validate() {
        cy.visit("/account");

        cy.get('[data-testid="nav-account"]').should("contain.text", "QA");
      },

      cacheAcrossSpecs: true,
    },
  );
});

export {};
