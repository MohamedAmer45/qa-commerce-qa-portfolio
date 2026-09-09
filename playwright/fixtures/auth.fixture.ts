import { test as base, expect } from "@playwright/test";

import { seedAuthState } from "../test-data/auth-state";

export const test = base.extend({
  storageState: async ({}, use) => {
    await use(seedAuthState);
  },
});

export { expect };
