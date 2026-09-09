import { test, expect } from "@playwright/test";

import { QALabPage } from "../../pages/QALabPage";

import {
  getRequestSummary,
  getResponseSummary,
  isApiRequest,
  isApiResponse,
} from "../../utils/network";

test.describe("Browser Network Testing", () => {
  test("@network @regression 429 response exposes Retry-After header", async ({
    page,
  }) => {
    const lab = new QALabPage(page);

    await lab.open();

    const [response] = await Promise.all([
      page.waitForResponse(
        (response) => isApiResponse(response) && response.status() === 429,
      ),

      lab.triggerScenario("lab-429"),
    ]);

    expect(response.status()).toBe(429);

    const headers = response.headers();

    expect(headers["retry-after"]).toBe("5");

    /*
     * Attach the real browser network response
     * metadata to the Playwright report.
     */
    await test.info().attach("429 Network Response", {
      body: Buffer.from(JSON.stringify(getResponseSummary(response), null, 2)),

      contentType: "application/json",
    });
  });

  test("@network @regression QA Lab sends browser request to API", async ({
    page,
  }) => {
    const lab = new QALabPage(page);

    await lab.open();

    const [request, response] = await Promise.all([
      page.waitForRequest(
        (request) => isApiRequest(request) && request.method() === "GET",
      ),

      page.waitForResponse(
        (response) => isApiResponse(response) && response.status() === 422,
      ),

      lab.triggerScenario("lab-422"),
    ]);

    expect(request.method()).toBe("GET");

    const requestUrl = new URL(request.url());

    expect(requestUrl.pathname).toMatch(/^\/api\//);

    expect(response.status()).toBe(422);

    /*
     * The response we received should belong
     * to an API request as well.
     */
    expect(isApiRequest(response.request())).toBeTruthy();

    await test.info().attach("API Request", {
      body: Buffer.from(JSON.stringify(getRequestSummary(request), null, 2)),

      contentType: "application/json",
    });

    await test.info().attach("API Response", {
      body: Buffer.from(JSON.stringify(getResponseSummary(response), null, 2)),

      contentType: "application/json",
    });
  });

  test("@network API response can be mocked from the browser layer", async ({
    page,
  }) => {
    const lab = new QALabPage(page);

    await lab.open();

    let intercepted = false;

    await page.route("**/api/**", async (route) => {
      intercepted = true;

      await route.fulfill({
        status: 503,

        contentType: "application/json",

        headers: {
          "x-mocked-by": "playwright",
        },

        body: JSON.stringify({
          error: "Mocked upstream service unavailable",
        }),
      });
    });

    /*
     * The actual backend behavior of this
     * scenario does not matter now because
     * Playwright intercepts the browser request.
     */
    await lab.triggerScenario("lab-500");

    expect(intercepted).toBeTruthy();

    const output = await lab.getOutput();

    expect(output.status).toBe(503);

    expect(await lab.getOutputText()).toContain(
      "Mocked upstream service unavailable",
    );
  });

  test("@network mocked response prevents real backend response from reaching UI", async ({
    page,
  }) => {
    const lab = new QALabPage(page);

    await lab.open();

    await page.route("**/api/**", async (route) => {
      await route.fulfill({
        status: 418,

        contentType: "application/json",

        headers: {
          "x-test-source": "playwright-route",
        },

        body: JSON.stringify({
          error: "Synthetic Playwright response",
        }),
      });
    });

    await lab.triggerScenario("lab-400");

    const result = await lab.getOutput();

    /*
     * The real lab-400 endpoint would return 400.
     *
     * Receiving 418 proves that page.route()
     * intercepted the browser request before
     * the real backend response reached the UI.
     */
    expect(result.status).toBe(418);

    expect(await lab.getOutputText()).toContain(
      "Synthetic Playwright response",
    );
  });
});
