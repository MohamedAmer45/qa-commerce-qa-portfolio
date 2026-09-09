import { test, expect, Page } from "@playwright/test";
import { RegistrationPage } from "../../pages/RegistrationPage";
import { AccountPage } from "../../pages/AccountPage";
import { LoginPage } from "../../pages/LoginPage";
import { users } from "../../test-data/users";

const validPassword = "Password123!";

test.describe("Registration", () => {
  test("@regression @registration names are required", async ({ page }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "",
      lastName: "",
      email: "new.user@example.com",
      password: validPassword,
    });

    await registration.expectMessage("Names required.");
  });

  test("@regression @registration invalid email is rejected", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "invalid-email",
      password: validPassword,
    });

    await registration.expectMessage("Valid email required.");
  });

  test("@regression @registration duplicate seed email is rejected case insensitively", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "Duplicate",
      lastName: "User",
      email: "QA.USER@EXAMPLE.COM",
      password: validPassword,
    });

    await registration.expectMessage("Email already exists.");
  });

  test("@regression @registration password shorter than eight characters is rejected", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "short@example.com",
      password: "Aa1!abc",
    });

    await registration.expectMessage("Weak password.");
  });

  test("@regression @registration password requires uppercase character", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "uppercase@example.com",
      password: "password123!",
    });

    await registration.expectMessage("Weak password.");
  });

  test("@regression @registration password requires lowercase character", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "lowercase@example.com",
      password: "PASSWORD123!",
    });

    await registration.expectMessage("Weak password.");
  });

  test("@regression @registration password requires digit", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "digit@example.com",
      password: "Password!",
    });

    await registration.expectMessage("Weak password.");
  });

  test("@regression @registration password requires special character", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "special@example.com",
      password: "Password123",
    });

    await registration.expectMessage("Weak password.");
  });

  test("@regression @registration password confirmation must match", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "mismatch@example.com",
      password: validPassword,
      confirmPassword: "Different123!",
    });

    await registration.expectMessage("Passwords do not match.");
  });

  test("@regression @registration terms must be accepted", async ({ page }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "New",
      lastName: "User",
      email: "terms@example.com",
      password: validPassword,
      acceptTerms: false,
    });

    await registration.expectMessage("Accept terms.");
  });

  test("@regression @registration first name maximum is 40 characters", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.firstNameInput.fill("A".repeat(41));

    await expect(registration.firstNameInput).toHaveValue("A".repeat(40));
  });

  test("@regression @registration last name maximum is 40 characters", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.lastNameInput.fill("B".repeat(41));

    await expect(registration.lastNameInput).toHaveValue("B".repeat(40));
  });

  test("@regression @registration password maximum is 64 characters", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.passwordInput.fill("A".repeat(65));

    await expect(registration.passwordInput).toHaveValue("A".repeat(64));
  });

  test("@smoke @registration valid account can be registered", async ({
    page,
  }) => {
    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "Playwright",
      lastName: "Tester",
      email: "playwright.user@example.com",
      password: validPassword,
    });

    await registration.expectRegistrationSuccess();

    const account = new AccountPage(page);

    await account.expectAccountDetails(
      "Playwright Tester",
      "playwright.user@example.com",
    );
  });

  test("@regression @registration @authentication registered user can log in again", async ({
    page,
  }) => {
    const email = "playwright.login@example.com";

    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "Playwright",
      lastName: "Login",
      email,
      password: validPassword,
    });

    await registration.expectRegistrationSuccess();

    /*
     * Keep the registered users list,
     * but remove the active session.
     */
    await page.evaluate(() => {
      localStorage.removeItem("user");
    });

    const login = new LoginPage(page);

    await login.open();

    await login.login(email, validPassword);

    await login.expectSuccessfulLogin();
  });
});

test.describe("Account Management", () => {
  async function loginSeed(page: Page): Promise<void> {
    const login = new LoginPage(page);

    await login.open();

    await login.login(users.seed.email, users.seed.password);

    await login.expectSuccessfulLogin();
  }

  test("@smoke @account seed account details are displayed", async ({
    page,
  }) => {
    await loginSeed(page);

    const account = new AccountPage(page);

    await account.expectAccountDetails("QA Tester", users.seed.email);

    await expect(account.accountNavigation).toHaveText("QA");
  });

  test("@smoke @account @authentication logout ends session", async ({
    page,
  }) => {
    await loginSeed(page);

    const account = new AccountPage(page);

    await account.logout();

    await expect(account.accountNavigation).toHaveText("Sign in");
  });

  test("@regression @account signed out user cannot view account details", async ({
    page,
  }) => {
    await loginSeed(page);

    const account = new AccountPage(page);

    await account.logout();

    await account.open();

    await expect(account.signedOutMessage).toBeVisible();
  });

  test("@regression @account empty delete confirmation is rejected", async ({
    page,
  }) => {
    await loginSeed(page);

    const account = new AccountPage(page);

    await account.deleteAccount("");

    await account.expectDeleteMessage("Confirmation must equal DELETE.");

    await expect(page).toHaveURL(/\/account$/);
  });

  test("@regression @account lowercase delete confirmation is rejected", async ({
    page,
  }) => {
    await loginSeed(page);

    const account = new AccountPage(page);

    await account.deleteAccount("delete");

    await account.expectDeleteMessage("Confirmation must equal DELETE.");
  });

  test("@regression @account delete confirmation does not trim whitespace", async ({
    page,
  }) => {
    await loginSeed(page);

    const account = new AccountPage(page);

    await account.deleteAccount(" DELETE ");

    await account.expectDeleteMessage("Confirmation must equal DELETE.");
  });

  test("@regression @account seed account remains reusable after delete action", async ({
    page,
  }) => {
    await loginSeed(page);

    const account = new AccountPage(page);

    await account.deleteAccount("DELETE");

    await expect(page).toHaveURL("/");

    await expect(account.accountNavigation).toHaveText("Sign in");

    const login = new LoginPage(page);

    await login.open();

    await login.login(users.seed.email, users.seed.password);

    await login.expectSuccessfulLogin();
  });

  test("@regression @account dynamic account can be permanently deleted", async ({
    page,
  }) => {
    const email = "delete.playwright@example.com";

    const registration = new RegistrationPage(page);

    await registration.open();

    await registration.register({
      firstName: "Delete",
      lastName: "Tester",
      email,
      password: validPassword,
    });

    await registration.expectRegistrationSuccess();

    const account = new AccountPage(page);

    await account.expectAccountDetails("Delete Tester", email);

    await account.deleteAccount("DELETE");

    await expect(page).toHaveURL("/");

    const login = new LoginPage(page);

    await login.open();

    await login.login(email, validPassword);

    await login.expectMessage("Invalid credentials.");
  });
});
