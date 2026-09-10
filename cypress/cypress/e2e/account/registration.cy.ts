interface RegistrationData {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  confirmPassword: string;
  acceptTerms: boolean;
}

const validRegistration: RegistrationData = {
  firstName: "Cypress",
  lastName: "Tester",
  email: "cypress.user@example.com",
  password: "Password123!",
  confirmPassword: "Password123!",
  acceptTerms: true,
};

function fillRegistration(overrides: Partial<RegistrationData> = {}): void {
  const data = {
    ...validRegistration,
    ...overrides,
  };

  if (data.firstName) {
    cy.get("#a").type(data.firstName);
  }

  if (data.lastName) {
    cy.get("#b").type(data.lastName);
  }

  if (data.email) {
    cy.get("#e").type(data.email);
  }

  if (data.password) {
    cy.get("#p").type(data.password, {
      log: false,
    });
  }

  if (data.confirmPassword) {
    cy.get("#q").type(data.confirmPassword, {
      log: false,
    });
  }

  if (data.acceptTerms) {
    cy.get("#z").check();
  }
}

function submitRegistration(): void {
  cy.get("form#r button.btn.p").click();
}

describe("Registration", () => {
  beforeEach(() => {
    cy.visit("/register");

    cy.get("#a").should("be.visible");

    cy.get("form#r button.btn.p").should("be.visible");
  });

  it("registers a new user successfully", () => {
    const email = `cypress.${Date.now()}@example.com`;

    fillRegistration({
      email,
    });

    submitRegistration();

    cy.url().should("include", "/account");

    cy.get("main.wrap h1").should("contain.text", "Cypress Tester");

    cy.get("main.wrap h1 + p").should("contain.text", email);
  });

  it("requires first name", () => {
    fillRegistration({
      firstName: "",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("requires last name", () => {
    fillRegistration({
      lastName: "",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("rejects invalid email format", () => {
    fillRegistration({
      email: "invalid-email",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("rejects duplicate seed email case-insensitively", () => {
    fillRegistration({
      email: "QA.USER@EXAMPLE.COM",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("rejects password shorter than 8 characters", () => {
    fillRegistration({
      password: "Pass1!",
      confirmPassword: "Pass1!",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("requires uppercase character in password", () => {
    fillRegistration({
      password: "password123!",
      confirmPassword: "password123!",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("requires lowercase character in password", () => {
    fillRegistration({
      password: "PASSWORD123!",
      confirmPassword: "PASSWORD123!",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("requires digit in password", () => {
    fillRegistration({
      password: "Password!",
      confirmPassword: "Password!",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("requires special character in password", () => {
    fillRegistration({
      password: "Password123",
      confirmPassword: "Password123",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("requires matching password confirmation", () => {
    fillRegistration({
      confirmPassword: "Different123!",
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("requires terms acceptance", () => {
    fillRegistration({
      acceptTerms: false,
    });

    submitRegistration();

    cy.url().should("include", "/register");

    cy.get("#m").should("be.visible").and("not.be.empty");
  });

  it("limits first name to 40 characters", () => {
    cy.get("#a").should("have.attr", "maxlength", "40");

    cy.get("#a").type("A".repeat(41));

    cy.get("#a")
      .invoke("val")
      .then((value) => {
        expect(String(value)).to.have.length(40);
      });
  });

  it("limits last name to 40 characters", () => {
    cy.get("#b").should("have.attr", "maxlength", "40");

    cy.get("#b").type("B".repeat(41));

    cy.get("#b")
      .invoke("val")
      .then((value) => {
        expect(String(value)).to.have.length(40);
      });
  });

  it("limits email to 120 characters", () => {
    cy.get("#e").should("have.attr", "maxlength", "120");

    cy.get("#e").type("a".repeat(121));

    cy.get("#e")
      .invoke("val")
      .then((value) => {
        expect(String(value)).to.have.length(120);
      });
  });

  it("limits password to 64 characters", () => {
    cy.get("#p").should("have.attr", "maxlength", "64");

    cy.get("#p").type("A".repeat(65), {
      log: false,
    });

    cy.get("#p")
      .invoke("val")
      .then((value) => {
        expect(String(value)).to.have.length(64);
      });
  });
});
