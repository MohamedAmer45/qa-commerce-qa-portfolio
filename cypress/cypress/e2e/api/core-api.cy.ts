const seedUser = {
  email: "qa.user@example.com",
  password: "Password123!",
};

const validContact = {
  name: "QA Tester",
  email: "qa@example.com",
  subject: "Technical problem",
  message: "This is a valid API support request.",
};

describe("Core REST API", () => {
  describe("Health", () => {
    it("returns HTTP 200", () => {
      cy.request("/api/health").then((response) => {
        expect(response.status).to.equal(200);
      });
    });
  });

  describe("Authentication", () => {
    it("authenticates valid seed credentials", () => {
      cy.request({
        method: "POST",
        url: "/api/auth",
        body: seedUser,
      }).then((response) => {
        expect(response.status).to.equal(200);

        expect(response.body).to.have.property("token");

        expect(response.body.token).to.be.a("string").and.not.be.empty;

        expect(JSON.stringify(response.body)).not.to.include('"password"');
      });
    });

    it("treats email as case insensitive", () => {
      cy.request({
        method: "POST",
        url: "/api/auth",
        body: {
          ...seedUser,
          email: "QA.USER@EXAMPLE.COM",
        },
      }).then((response) => {
        expect(response.status).to.equal(200);
      });
    });

    it("trims email whitespace", () => {
      cy.request({
        method: "POST",
        url: "/api/auth",
        body: {
          ...seedUser,
          email: "   qa.user@example.com   ",
        },
      }).then((response) => {
        expect(response.status).to.equal(200);
      });
    });

    it("returns 400 for missing credentials", () => {
      cy.request({
        method: "POST",
        url: "/api/auth",
        body: {},
        failOnStatusCode: false,
      }).then((response) => {
        expect(response.status).to.equal(400);

        expect(response.body.error).to.equal("MISSING_CREDENTIALS");
      });
    });

    it("returns 422 for malformed email", () => {
      cy.request({
        method: "POST",
        url: "/api/auth",
        body: {
          email: "invalid-email",
          password: seedUser.password,
        },
        failOnStatusCode: false,
      }).then((response) => {
        expect(response.status).to.equal(422);

        expect(response.body.error).to.equal("INVALID_EMAIL");
      });
    });

    it("returns 401 for invalid password", () => {
      cy.request({
        method: "POST",
        url: "/api/auth",
        body: {
          email: seedUser.email,
          password: "WrongPassword123!",
        },
        failOnStatusCode: false,
      }).then((response) => {
        expect(response.status).to.equal(401);

        expect(response.body.error).to.equal("INVALID_CREDENTIALS");
      });
    });

    it("treats passwords as case sensitive", () => {
      cy.request({
        method: "POST",
        url: "/api/auth",
        body: {
          email: seedUser.email,
          password: "password123!",
        },
        failOnStatusCode: false,
      }).then((response) => {
        expect(response.status).to.equal(401);

        expect(response.body.error).to.equal("INVALID_CREDENTIALS");
      });
    });
  });

  describe("Contact", () => {
    it("accepts a valid support request", () => {
      cy.request({
        method: "POST",
        url: "/api/contact",
        body: validContact,
      }).then((response) => {
        expect(response.status).to.equal(202);

        expect(response.body.accepted).to.equal(true);
      });
    });

    it("rejects a one-character name", () => {
      cy.request({
        method: "POST",
        url: "/api/contact",
        body: {
          ...validContact,
          name: "Q",
        },
        failOnStatusCode: false,
      }).then((response) => {
        expect(response.status).to.equal(422);
      });
    });

    it("accepts a two-character name", () => {
      cy.request({
        method: "POST",
        url: "/api/contact",
        body: {
          ...validContact,
          name: "QA",
        },
      }).then((response) => {
        expect(response.status).to.equal(202);
      });
    });

    it("rejects a 19-character message", () => {
      cy.request({
        method: "POST",
        url: "/api/contact",
        body: {
          ...validContact,
          message: "A".repeat(19),
        },
        failOnStatusCode: false,
      }).then((response) => {
        expect(response.status).to.equal(422);
      });
    });

    it("accepts a 20-character message", () => {
      cy.request({
        method: "POST",
        url: "/api/contact",
        body: {
          ...validContact,
          message: "A".repeat(20),
        },
      }).then((response) => {
        expect(response.status).to.equal(202);

        expect(response.body.accepted).to.equal(true);
      });
    });
  });
});
