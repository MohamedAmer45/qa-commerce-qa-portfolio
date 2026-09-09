import { test, expect } from "@playwright/test";
import { LoginPage } from "../../pages/LoginPage";
import { users } from "../../test-data/users";

test.describe("Authentication", () => {
  test("@smoke @authentication valid seed user can log in", async ({
    page,
  }) => {
    const loginPage = new LoginPage(page);

    await test.step("Open the login page", async () => {
      await loginPage.open();
    });

    await test.step("Login using valid seed credentials", async () => {
      await loginPage.login(users.seed.email, users.seed.password);
    });

    await test.step("Verify account page is displayed", async () => {
      await loginPage.expectSuccessfulLogin();

      await expect(loginPage.accountNavigation).toHaveText("QA");
    });
  });

  test("@regression @authentication invalid password is rejected", async ({
    page,
  }) => {
    const loginPage = new LoginPage(page);

    await loginPage.open();

    await loginPage.login(users.seed.email, "WrongPassword123!");

    await loginPage.expectMessage("Invalid credentials.");

    await expect(page).toHaveURL(/\/login$/);
  });

  test("@regression @authentication malformed email is rejected", async ({
    page,
  }) => {
    const loginPage = new LoginPage(page);

    await loginPage.open();

    await loginPage.login("invalid-email", users.seed.password);

    await loginPage.expectMessage("Invalid email format.");

    await expect(page).toHaveURL(/\/login$/);
  });

  test("@regression @authentication empty credentials are rejected", async ({
    page,
  }) => {
    const loginPage = new LoginPage(page);

    await loginPage.open();

    await loginPage.login("", "");

    await loginPage.expectMessage("Email and password required.");
  });

  test("@regression @authentication email is case insensitive", async ({
    page,
  }) => {
    const loginPage = new LoginPage(page);

    await loginPage.open();

    await loginPage.login("QA.USER@EXAMPLE.COM", users.seed.password);

    await loginPage.expectSuccessfulLogin();
  });

  test("@regression @authentication email whitespace is trimmed", async ({
    page,
  }) => {
    const loginPage = new LoginPage(page);

    await loginPage.open();

    await loginPage.login("   qa.user@example.com   ", users.seed.password);

    await loginPage.expectSuccessfulLogin();
  });

  test("@regression @authentication password is case sensitive", async ({
    page,
  }) => {
    const loginPage = new LoginPage(page);

    await loginPage.open();

    await loginPage.login(users.seed.email, "password123!");

    await loginPage.expectMessage("Invalid credentials.");

    await expect(page).toHaveURL(/\/login$/);
  });
});
