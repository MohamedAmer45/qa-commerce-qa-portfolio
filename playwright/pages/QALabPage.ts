import { expect, Locator, Page } from "@playwright/test";

export interface QALabOutput {
  status: number;
  elapsedMs?: number;
  retryAfter?: string | null;
  body?: unknown;
}

export class QALabPage {
  readonly page: Page;

  readonly heading: Locator;
  readonly output: Locator;

  readonly delayedDomButton: Locator;
  readonly dynamicResult: Locator;

  readonly modalButton: Locator;
  readonly dialog: Locator;
  readonly cancelButton: Locator;
  readonly confirmButton: Locator;

  constructor(page: Page) {
    this.page = page;

    this.heading = page.getByRole("heading", {
      name: "Deterministic failure modes",
    });

    this.output = page.locator("#out");

    this.delayedDomButton = page.locator("#dy");

    this.dynamicResult = page.getByTestId("dynamic-result");

    this.modalButton = page.locator("#mo");

    this.dialog = page.locator("#dlg");

    this.cancelButton = page.locator("#cl");

    this.confirmButton = page.locator("#cf");
  }

  async open(): Promise<void> {
    await this.page.goto("/qa-lab");

    await expect(this.heading).toBeVisible();
  }

  scenarioButton(testId: string): Locator {
    return this.page.getByTestId(testId);
  }

  async triggerScenario(testId: string): Promise<void> {
    await this.scenarioButton(testId).click();

    /*
     * Every deterministic API result includes
     * a status property when completed.
     */
    await expect(this.output).toContainText('"status"');
  }

  async getOutputText(): Promise<string> {
    return (await this.output.textContent())?.trim() ?? "";
  }

  async getOutput(): Promise<QALabOutput> {
    const text = await this.getOutputText();

    return JSON.parse(text) as QALabOutput;
  }

  async triggerDelayedDom(): Promise<void> {
    await this.delayedDomButton.click();
  }

  async openModal(): Promise<void> {
    await this.modalButton.click();

    await expect(this.dialog).toBeVisible();
  }

  async cancelModal(): Promise<void> {
    await this.cancelButton.click();

    await expect(this.dialog).toBeHidden();
  }

  async confirmModal(): Promise<void> {
    await this.confirmButton.click();

    await expect(this.dialog).toBeHidden();
  }
}
