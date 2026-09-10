function waitForScenarioRequest(testId: string): void {
  cy.intercept("**/api/**").as("qaLabRequest");

  cy.get(`[data-testid="${testId}"]`).should("be.visible").click();

  cy.wait("@qaLabRequest");
}

function getOutput(): Cypress.Chainable<string> {
  return cy
    .get("#out")
    .should("be.visible")
    .invoke("text")
    .then((text) => String(text));
}

function expectOutputStatus(status: number): void {
  getOutput().then((text) => {
    expect(text).to.include(`"status": ${status}`);
  });
}

describe("QA Lab", () => {
  beforeEach(() => {
    cy.visit("/qa-lab");

    cy.contains("h1", "Deterministic failure modes").should("be.visible");

    cy.get("#out").should("be.visible");
  });

  it("displays delayed DOM element after trigger", () => {
    cy.get("#dy").should("be.visible").click();

    cy.get('[data-testid="dynamic-result"]')
      .should("be.visible")
      .and("have.text", "Dynamic element appeared after 900 ms.");
  });

  it("handles slow 200 response", () => {
    waitForScenarioRequest("lab-async");

    expectOutputStatus(200);

    getOutput().then((text) => {
      const match = text.match(/"elapsedMs"\s*:\s*(\d+)/);

      expect(match, "elapsedMs should exist").to.not.be.null;

      const elapsed = Number(match?.[1]);

      expect(elapsed).to.be.at.least(1400);
    });
  });

  it("handles 204 no content response", () => {
    waitForScenarioRequest("lab-204");

    expectOutputStatus(204);

    getOutput().then((text) => {
      expect(text).to.include('"body": null');
    });
  });

  it("handles 400 bad request response", () => {
    waitForScenarioRequest("lab-400");

    expectOutputStatus(400);
  });

  it("handles 401 unauthorized response", () => {
    waitForScenarioRequest("lab-401");

    expectOutputStatus(401);
  });

  it("handles 404 not found response", () => {
    waitForScenarioRequest("lab-404");

    expectOutputStatus(404);
  });

  it("handles 409 conflict response", () => {
    waitForScenarioRequest("lab-409");

    expectOutputStatus(409);
  });

  it("handles 422 validation response", () => {
    waitForScenarioRequest("lab-422");

    expectOutputStatus(422);
  });

  it("handles 429 rate limit and Retry-After header", () => {
    cy.intercept("**/api/**").as("rateLimitRequest");

    cy.get('[data-testid="lab-429"]').click();

    cy.wait("@rateLimitRequest").then((interception) => {
      expect(interception.response?.statusCode).to.equal(429);

      expect(interception.response?.headers["retry-after"]).to.equal("5");
    });

    expectOutputStatus(429);

    getOutput().then((text) => {
      expect(text).to.match(/"retryAfter"\s*:\s*"5"/);
    });
  });

  it("handles controlled 500 server error without exposing stack information", () => {
    waitForScenarioRequest("lab-500");

    expectOutputStatus(500);

    getOutput().then((text) => {
      expect(text.toLowerCase()).not.to.include("node_modules");
    });
  });

  it("handles large response successfully", () => {
    waitForScenarioRequest("lab-big");

    expectOutputStatus(200);

    getOutput().then((text) => {
      expect(text.length).to.be.greaterThan(1000);
    });
  });

  it("opens and cancels modal", () => {
    cy.get("#mo").should("be.visible").click();

    cy.get("#dlg").should("be.visible");

    cy.get("#cl").should("be.visible").click();

    cy.get("#dlg").should("not.be.visible");
  });

  it("opens and confirms modal", () => {
    cy.get("#mo").should("be.visible").click();

    cy.get("#dlg").should("be.visible");

    cy.get("#cf").should("be.visible").click();

    cy.get("#dlg").should("not.be.visible");
  });
});
