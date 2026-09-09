import { test, expect } from "@playwright/test";
import { ContactPage } from "../../pages/ContactPage";

test.describe("Contact Support", () => {
  test("@smoke @contact valid support request is accepted", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();
    await contact.fillValidForm();
    await contact.submit();

    await contact.expectResult("Support request accepted.");
  });

  test("@regression @contact one character name is rejected", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();

    await contact.setName("A");
    await contact.setEmail("qa@example.com");

    await contact.selectSubject("Technical problem");

    await contact.setMessage(
      "This message contains more than twenty characters.",
    );

    await contact.submit();

    await contact.expectResult("Name too short.");
  });

  test("@regression @contact two character name is accepted", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();

    await contact.setName("QA");
    await contact.setEmail("qa@example.com");

    await contact.selectSubject("Technical problem");

    await contact.setMessage(
      "This message contains more than twenty characters.",
    );

    await contact.submit();

    await contact.expectResult("Support request accepted.");
  });

  test("@regression @contact invalid email is rejected", async ({ page }) => {
    const contact = new ContactPage(page);

    await contact.open();

    await contact.setName("QA Tester");
    await contact.setEmail("invalid-email");

    await contact.selectSubject("Technical problem");

    await contact.setMessage(
      "This message contains more than twenty characters.",
    );

    await contact.submit();

    await contact.expectResult("Invalid email.");
  });

  test("@regression @contact subject is required", async ({ page }) => {
    const contact = new ContactPage(page);

    await contact.open();

    await contact.setName("QA Tester");
    await contact.setEmail("qa@example.com");

    await contact.setMessage(
      "This message contains more than twenty characters.",
    );

    await contact.submit();

    await contact.expectResult("Select subject.");
  });

  test("@regression @contact all supported subjects are available", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    const subjects = [
      "Order issue",
      "Product question",
      "Technical problem",
      "Other",
    ];

    await contact.open();

    for (const subject of subjects) {
      await contact.selectSubject(subject);

      await expect(contact.subjectSelect.locator("option:checked")).toHaveText(
        subject,
      );
    }
  });

  test("@regression @contact nineteen character message is rejected", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();

    await contact.setName("QA Tester");
    await contact.setEmail("qa@example.com");

    await contact.selectSubject("Technical problem");

    await contact.setMessage("A".repeat(19));

    await contact.submit();

    await contact.expectResult("Message too short.");
  });

  test("@regression @contact twenty character message is accepted", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();

    await contact.setName("QA Tester");
    await contact.setEmail("qa@example.com");

    await contact.selectSubject("Technical problem");

    await contact.setMessage("A".repeat(20));

    await contact.submit();

    await contact.expectResult("Support request accepted.");
  });

  test("@regression @contact message input enforces maximum of 1000 characters", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();

    /*
     * pressSequentially simulates actual keyboard input
     * so we verify the browser maxlength behavior.
     */
    await contact.messageInput.fill("A".repeat(1001));

    await expect(contact.messageInput).toHaveValue("A".repeat(1000));
  });

  test("@regression @contact Unicode content is accepted", async ({ page }) => {
    const contact = new ContactPage(page);

    await contact.open();

    await contact.setName("محمد عامر");
    await contact.setEmail("qa@example.com");

    await contact.selectSubject("Technical problem");

    await contact.setMessage(
      "هذه رسالة اختبار باستخدام Playwright وتحتوي على أكثر من عشرين حرفاً.",
    );

    await contact.submit();

    await contact.expectResult("Support request accepted.");
  });

  test("@regression @contact valid PNG attachment is accepted", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();
    await contact.fillValidForm();

    await contact.fileInput.setInputFiles({
      name: "evidence.png",
      mimeType: "image/png",
      buffer: Buffer.from("QA Commerce Lab Playwright evidence"),
    });

    await contact.submit();

    await contact.expectResult("Support request accepted.");
  });

  test("@regression @contact unsupported attachment type is rejected", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();
    await contact.fillValidForm();

    await contact.fileInput.setInputFiles({
      name: "unsupported.txt",
      mimeType: "text/plain",
      buffer: Buffer.from("Unsupported attachment"),
    });

    await contact.submit();

    await contact.expectResult("File type not allowed.");
  });

  test("@regression @contact attachment above two megabytes is rejected", async ({
    page,
  }) => {
    const contact = new ContactPage(page);

    await contact.open();
    await contact.fillValidForm();

    const largeFile = Buffer.alloc(2 * 1024 * 1024 + 1);

    await contact.fileInput.setInputFiles({
      name: "large-evidence.pdf",
      mimeType: "application/pdf",
      buffer: largeFile,
    });

    await contact.submit();

    await contact.expectResult("File too large.");
  });
});
