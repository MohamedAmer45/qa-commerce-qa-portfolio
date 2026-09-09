import { test, expect } from "@playwright/test";
import { QALabPage } from "../../pages/QALabPage";

test.describe("QA Lab", () => {
  test("@smoke @qa-lab delayed DOM element appears", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();
    await lab.triggerDelayedDom();

    await expect(lab.dynamicResult).toBeVisible();

    await expect(lab.dynamicResult).toHaveText(
      "Dynamic element appeared after 900 ms.",
    );
  });

  test("@regression @qa-lab slow response completes successfully", async ({
    page,
  }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-async");

    const result = await lab.getOutput();

    expect(result.status).toBe(200);

    expect(result.elapsedMs).toBeGreaterThanOrEqual(1400);
  });

  test("@regression @qa-lab 204 response is handled", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-204");

    const result = await lab.getOutput();

    expect(result.status).toBe(204);
    expect(result.body).toBeNull();
  });

  test("@regression @qa-lab 400 response is handled", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-400");

    expect((await lab.getOutput()).status).toBe(400);
  });

  test("@regression @qa-lab 401 response is handled", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-401");

    expect((await lab.getOutput()).status).toBe(401);
  });

  test("@regression @qa-lab 404 response is handled", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-404");

    expect((await lab.getOutput()).status).toBe(404);
  });

  test("@regression @qa-lab 409 response is handled", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-409");

    expect((await lab.getOutput()).status).toBe(409);
  });

  test("@regression @qa-lab 422 response is handled", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-422");

    expect((await lab.getOutput()).status).toBe(422);
  });

  test("@regression @qa-lab 429 response exposes Retry-After", async ({
    page,
  }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-429");

    const result = await lab.getOutput();

    expect(result.status).toBe(429);

    expect(result.retryAfter).toBe("5");
  });

  test("@regression @qa-lab controlled server error returns 500", async ({
    page,
  }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-500");

    const result = await lab.getOutput();

    expect(result.status).toBe(500);

    const rawOutput = await lab.getOutputText();

    expect(rawOutput.toLowerCase()).not.toContain("node_modules");
  });

  test("@regression @qa-lab large response is displayed", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await lab.triggerScenario("lab-big");

    const result = await lab.getOutput();

    expect(result.status).toBe(200);

    expect((await lab.getOutputText()).length).toBeGreaterThan(1000);
  });

  test("@smoke @qa-lab modal can be cancelled", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();
    await lab.openModal();

    await expect(lab.dialog).toBeVisible();

    await lab.cancelModal();

    await expect(lab.dialog).toBeHidden();
  });

  test("@regression @qa-lab modal can be confirmed", async ({ page }) => {
    const lab = new QALabPage(page);

    await lab.open();
    await lab.openModal();

    await expect(lab.dialog).toBeVisible();

    await lab.confirmModal();

    await expect(lab.dialog).toBeHidden();
  });
});
