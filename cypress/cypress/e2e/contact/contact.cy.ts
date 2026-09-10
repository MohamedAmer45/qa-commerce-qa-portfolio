interface ContactData {
  name: string;
  email: string;
  subject: string;
  message: string;
}

const validContact: ContactData = {
  name: "QA Tester",
  email: "qa@example.com",
  subject: "Technical problem",
  message: "This is a valid support request for testing.",
};

function fillContact(overrides: Partial<ContactData> = {}): void {
  const data = {
    ...validContact,
    ...overrides,
  };

  if (data.name) {
    cy.get("#na").type(data.name);
  }

  if (data.email) {
    cy.get("#em").type(data.email);
  }

  if (data.subject) {
    cy.get("#su").select(data.subject);
  }

  if (data.message) {
    cy.get("#me").type(data.message);
  }
}

function submitContact(): void {
  cy.get("form#f button.btn.p").click();
}

describe("Contact Form", () => {
  beforeEach(() => {
    cy.visit("/contact");

    cy.get("#na").should("be.visible");

    cy.get("#em").should("be.visible");

    cy.get("#su").should("be.visible");

    cy.get("#me").should("be.visible");

    cy.get("form#f button.btn.p").should("be.visible");
  });

  it("submits a valid support request", () => {
    fillContact();

    submitContact();

    cy.get("#m").should("have.text", "Support request accepted.");
  });

  it("rejects a name shorter than 2 characters", () => {
    fillContact({
      name: "Q",
    });

    submitContact();

    cy.get("#m").should("have.text", "Name too short.");
  });

  it("accepts the minimum valid name length of 2 characters", () => {
    fillContact({
      name: "QA",
    });

    submitContact();

    cy.get("#m").should("have.text", "Support request accepted.");
  });

  it("rejects invalid email format", () => {
    fillContact({
      email: "invalid-email",
    });

    submitContact();

    cy.get("#m").should("have.text", "Invalid email.");
  });

  it("requires a subject", () => {
    fillContact({
      subject: "",
    });

    submitContact();

    cy.get("#m").should("have.text", "Select subject.");
  });

  it("contains all supported subjects", () => {
    const expectedSubjects = [
      "Order issue",
      "Product question",
      "Technical problem",
      "Other",
    ];

    cy.get("#su option").then(($options) => {
      const subjects = [...$options]
        .map((option) => option.textContent?.trim())
        .filter((value): value is string => Boolean(value));

      expectedSubjects.forEach((subject) => {
        expect(subjects).to.include(subject);
      });
    });
  });

  it("rejects a message shorter than 20 characters", () => {
    fillContact({
      message: "A".repeat(19),
    });

    submitContact();

    cy.get("#m").should("have.text", "Message too short.");
  });

  it("accepts a message exactly 20 characters long", () => {
    fillContact({
      message: "A".repeat(20),
    });

    submitContact();

    cy.get("#m").should("have.text", "Support request accepted.");
  });

  it("limits the message to 1000 characters", () => {
    cy.get("#me").should("have.attr", "maxlength", "1000");

    cy.get("#me").invoke("val", "A".repeat(1001)).trigger("input");

    /*
     * Directly setting the value bypasses the browser
     * maxlength behavior, so restore normal UI input
     * and verify the real field limit.
     */
    cy.get("#me").clear().type("A".repeat(1000), {
      delay: 0,
    });

    cy.get("#me")
      .invoke("val")
      .then((value) => {
        expect(String(value)).to.have.length(1000);
      });
  });

  it("accepts Unicode content", () => {
    fillContact({
      name: "محمد QA",
      message: "Testing Unicode support: مرحباً بالعالم — Café Élan.",
    });

    submitContact();

    cy.get("#m").should("have.text", "Support request accepted.");
  });

  it("accepts an allowed PNG attachment", () => {
    fillContact();

    cy.get("#fi").selectFile({
      contents: Cypress.Buffer.from([137, 80, 78, 71, 13, 10, 26, 10]),
      fileName: "evidence.png",
      mimeType: "image/png",
      lastModified: Date.now(),
    });

    submitContact();

    cy.get("#m").should("have.text", "Support request accepted.");
  });

  it("rejects an unsupported TXT attachment", () => {
    fillContact();

    cy.get("#fi").selectFile({
      contents: Cypress.Buffer.from("QA evidence file"),
      fileName: "evidence.txt",
      mimeType: "text/plain",
      lastModified: Date.now(),
    });

    submitContact();

    cy.get("#m").should("have.text", "File type not allowed.");
  });

  it("rejects an attachment larger than 2 MB", () => {
    fillContact();

    const oversizedFile = Cypress.Buffer.alloc(2 * 1024 * 1024 + 1);

    cy.get("#fi").selectFile({
      contents: oversizedFile,
      fileName: "large.png",
      mimeType: "image/png",
      lastModified: Date.now(),
    });

    submitContact();

    cy.get("#m").should("have.text", "File too large.");
  });
});
