import { expect, Locator, Page } from "@playwright/test";

export class ContactPage {
  readonly page: Page;

  readonly nameInput: Locator;
  readonly emailInput: Locator;
  readonly subjectSelect: Locator;
  readonly messageInput: Locator;
  readonly fileInput: Locator;
  readonly submitButton: Locator;
  readonly resultMessage: Locator;

  constructor(page: Page) {
    this.page = page;

    this.nameInput = page.locator("#na");
    this.emailInput = page.locator("#em");
    this.subjectSelect = page.locator("#su");
    this.messageInput = page.locator("#me");
    this.fileInput = page.locator("#fi");

    this.submitButton = page.locator("form#f button.btn.p");

    this.resultMessage = page.locator("#m");
  }

  async open(): Promise<void> {
    await this.page.goto("/contact");

    await expect(this.nameInput).toBeVisible();
  }

  async setName(value: string): Promise<void> {
    await this.nameInput.fill(value);
  }

  async setEmail(value: string): Promise<void> {
    await this.emailInput.fill(value);
  }

  async selectSubject(subject: string): Promise<void> {
    await this.subjectSelect.selectOption({
      label: subject,
    });
  }

  async setMessage(value: string): Promise<void> {
    await this.messageInput.fill(value);
  }

  async submit(): Promise<void> {
    await this.submitButton.click();
  }

  async fillValidForm(): Promise<void> {
    await this.setName("QA Tester");
    await this.setEmail("qa@example.com");

    await this.selectSubject("Technical problem");

    await this.setMessage("This is a valid Playwright support request.");
  }

  async expectResult(expected: string): Promise<void> {
    await expect(this.resultMessage).toHaveText(expected);
  }
}
