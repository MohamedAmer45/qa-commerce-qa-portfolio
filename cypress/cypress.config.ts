import { defineConfig } from "cypress";

export default defineConfig({
  reporterOptions: {
    reportDir: "../reports/cypress/mochawesome",
    overwrite: false,
    html: false,
    json: true,
  },
  e2e: {
    baseUrl: "https://qa-commerce-lab.vercel.app",

    specPattern: "cypress/e2e/**/*.cy.ts",

    supportFile: "cypress/support/e2e.ts",

    fixturesFolder: "cypress/fixtures",

    screenshotsFolder: "../reports/cypress/screenshots",

    videosFolder: "../reports/cypress/videos",

    downloadsFolder: "../reports/cypress/downloads",

    video: true,

    screenshotOnRunFailure: true,

    defaultCommandTimeout: 10_000,

    requestTimeout: 10_000,

    responseTimeout: 30_000,

    pageLoadTimeout: 30_000,

    retries: {
      runMode: 1,
      openMode: 0,
    },

    setupNodeEvents(on, config) {
      return config;
    },
    reporter: "mochawesome",
  },
});
