import users from "../../fixtures/users.json";

describe("Authentication", () => {
  beforeEach(() => {
    cy.visit("/login");

    cy.get('[data-testid="login-email"]').should("be.visible");

    cy.get('[data-testid="login-password"]').should("be.visible");

    cy.get('[data-testid="login-submit"]').should("be.visible");
  });

  it("valid seed user can log in", () => {
    cy.get('[data-testid="login-email"]').type(users.seed.email);

    cy.get('[data-testid="login-password"]').type(users.seed.password, {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.url().should("include", "/account");

    cy.get('[data-testid="nav-account"]').should("contain.text", "QA");
  });

  it("invalid password is rejected", () => {
    cy.get('[data-testid="login-email"]').type(users.seed.email);

    cy.get('[data-testid="login-password"]').type("WrongPassword123!", {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.get("#m").should("have.text", "Invalid credentials.");

    cy.url().should("include", "/login");
  });

  it("malformed email is rejected", () => {
    cy.get('[data-testid="login-email"]').type("invalid-email");

    cy.get('[data-testid="login-password"]').type(users.seed.password, {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.get("#m").should("have.text", "Invalid email format.");
  });

  it("empty credentials are rejected", () => {
    cy.get('[data-testid="login-submit"]').click();

    cy.get("#m").should("have.text", "Email and password required.");
  });

  it("email is case insensitive", () => {
    cy.get('[data-testid="login-email"]').type("QA.USER@EXAMPLE.COM");

    cy.get('[data-testid="login-password"]').type(users.seed.password, {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.url().should("include", "/account");
  });

  it("email whitespace is trimmed", () => {
    cy.get('[data-testid="login-email"]').type("   qa.user@example.com   ");

    cy.get('[data-testid="login-password"]').type(users.seed.password, {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.url().should("include", "/account");
  });

  it("password is case sensitive", () => {
    cy.get('[data-testid="login-email"]').type(users.seed.email);

    cy.get('[data-testid="login-password"]').type("password123!", {
      log: false,
    });

    cy.get('[data-testid="login-submit"]').click();

    cy.get("#m").should("have.text", "Invalid credentials.");

    cy.url().should("include", "/login");
  });
});
