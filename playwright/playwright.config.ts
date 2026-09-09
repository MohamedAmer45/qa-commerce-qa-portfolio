import { defineConfig, devices } from "@playwright/test";

const baseURL = "https://qa-commerce-lab.vercel.app";

const localProjects = [
  {
    name: "chrome",
    use: {
      ...devices["Desktop Chrome"],
      channel: "chrome",
    },
  },
  {
    name: "edge",
    use: {
      ...devices["Desktop Chrome"],
      channel: "msedge",
    },
  },
];

const ciProjects = [
  {
    name: "chromium",
    use: {
      ...devices["Desktop Chrome"],
    },
  },
  {
    name: "firefox",
    use: {
      ...devices["Desktop Firefox"],
    },
  },
  {
    name: "webkit",
    use: {
      ...devices["Desktop Safari"],
    },
  },
];

export default defineConfig({
  testDir: "./tests",

  fullyParallel: true,

  forbidOnly: !!process.env.CI,

  retries: process.env.CI ? 2 : 0,

  workers: process.env.CI ? 2 : undefined,

  timeout: 30_000,

  expect: {
    timeout: 5_000,
  },

  reporter: [
    ["list"],

    [
      "html",
      {
        outputFolder: "../reports/playwright/html-report",
        open: "never",
      },
    ],

    [
      "junit",
      {
        outputFile: "../reports/playwright/junit-results.xml",
      },
    ],

    [
      "json",
      {
        outputFile: "../reports/playwright/results.json",
      },
    ],
  ],

  outputDir: "../reports/playwright/test-results",

  use: {
    baseURL,

    headless: true,

    actionTimeout: 10_000,

    navigationTimeout: 30_000,

    screenshot: "only-on-failure",

    video: "retain-on-failure",

    trace: "on-first-retry",
  },

  projects: process.env.CI ? ciProjects : localProjects,
});
