# Selenium UI Automation Execution Report

## 1. Overview

This report summarizes the Selenium UI automation implementation and execution for QA Commerce Lab.

The framework was implemented using:

- Java 21
- Selenium WebDriver 4.48.0
- TestNG 7.12.0
- Maven
- Page Object Model
- Allure reporting
- Automatic failure screenshot capture

Application under test:

`https://qa-commerce-lab.vercel.app`

---

## 2. Automation Scope

The Selenium suite covers the following application areas:

| Module | Coverage |
|---|---|
| Core Navigation | Home page and main navigation |
| Authentication | Login positive and negative scenarios |
| Registration | Validation, password rules, duplicate accounts and successful registration |
| Products | Search, filtering, sorting, stock and cart actions |
| Cart | Quantity management, persistence, totals and removal |
| Coupons | Valid, invalid, expired and threshold-based coupons |
| Checkout | Shipping validation, payment validation and successful checkout |
| Account | Account details, logout and deletion |
| Contact | Validation, Unicode input and attachment handling |
| QA Lab | Delays, dynamic DOM, modal behavior and deterministic HTTP failures |

---

## 3. Approximate Automated Test Count

| Test Class | Tests |
|---|---:|
| FrameworkSmokeTest | 1 |
| LoginTests | 7 |
| ProductsTests | 12 |
| CartTests | 11 |
| CouponTests | 9 |
| CheckoutTests | 15 |
| AccountTests | 7 |
| RegistrationTests | 15 |
| ContactTests | 13 |
| QALabTests | 13 |
| **Total** | **103** |

The final TestNG execution result should be treated as the authoritative count if the suite changes.

---

## 4. Test Design

The framework uses the Page Object Model to separate:

- Test logic
- Element locators
- Browser interaction
- Configuration
- Driver management
- Reporting
- Failure evidence

Page objects include:

- HomePage
- LoginPage
- RegistrationPage
- ProductsPage
- CartPage
- CheckoutPage
- AccountPage
- ContactPage
- QALabPage

---

## 5. Browser Configuration

The framework supports:

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

Browser selection can be overridden from Maven.

Example:

`mvn test -Dbrowser=firefox`

Headless execution is also supported:

`mvn test -Dheadless=true`

---

## 6. Test Isolation

Each test starts with a clean browser state.

Before every test:

- Cookies are cleared.
- localStorage is cleared.
- sessionStorage is cleared.
- The application is refreshed.

This prevents tests from depending on state created by previous tests.

---

## 7. Test Groups

TestNG groups are used to allow targeted execution.

Examples include:

- smoke
- regression
- authentication
- registration
- products
- cart
- checkout
- account
- contact
- qa-lab
- defect-candidate

Example:

`mvn test -Dgroups=smoke`

Example:

`mvn test -Dgroups=checkout`

---

## 8. Failure Evidence

A custom TestNG listener captures a screenshot automatically whenever an automated test fails.

Screenshots are stored under:

`reports/selenium/screenshots/`

Failure screenshots are also attached to Allure results.

This provides visual evidence for failed assertions and confirmed defects.

---

## 9. Allure Reporting

Allure is integrated with TestNG.

The report includes:

- Test suites
- Behaviors
- Features
- Failure stack traces
- Test descriptions
- Severity metadata
- Defect IDs
- Environment information
- Failure screenshots

Allure results are produced in:

`selenium/target/allure-results/`

Permanent HTML reports can be generated under:

`reports/selenium/allure-report/`

---

## 10. Confirmed Defects Detected by Selenium

### BUG-UI-CHK-001

**Title:** Cart counter does not update immediately after successful checkout.

After checkout succeeds, persisted cart data is cleared but the visible navigation cart counter remains stale until the page is refreshed.

Related automated test:

`CheckoutTests.successfulCheckoutClearsCartAndUpdatesCounter`

---

### BUG-UI-CHK-002

**Title:** Checkout accepts impossible expiry month.

The checkout accepts expiry values such as:

`13/30`

The validation checks the expiry format but does not properly validate the month range.

Related automated test:

`CheckoutTests.impossibleExpiryMonthIsRejected`

---

### BUG-UI-CART-001

**Title:** QA Sticker Pack quantity can exceed special maximum through cart editor.

Product ID 7 has a business maximum of 25 units.

The Add to Cart logic applies the limit, but the cart quantity editor allows values above 25 because it validates against stock instead.

Related automated test:

`CartTests.stickerPackQuantityCannotExceed25`

---

## 11. Known-Failure Strategy

Tests representing confirmed defects intentionally continue to assert the correct expected business behavior.

The tests are not modified to accept the defective application behavior.

Therefore the full regression suite may report failures while these defects remain open.

This preserves the automated tests as executable defect evidence and prevents regressions from being hidden.

---

## 12. Example Execution Commands

Compile:

`mvn clean test-compile`

Run complete suite:

`mvn test`

Run smoke suite:

`mvn test -Dgroups=smoke`

Run regression suite:

`mvn test -Dgroups=regression`

Run Chrome headless:

`mvn test -Dheadless=true`

Run Firefox:

`mvn test -Dbrowser=firefox`

Run Edge:

`mvn test -Dbrowser=edge`

Run known defect tests:

`mvn test -Dgroups=defect-candidate`

---

## 13. Execution Result

The suite includes three automated tests associated with confirmed open UI defects.

Expected known failures:

- BUG-UI-CHK-001
- BUG-UI-CHK-002
- BUG-UI-CART-001

All unrelated automated tests are expected to pass.

The exact final numbers should be taken from the latest Maven/TestNG execution report.

---

## 14. Conclusion

The Selenium automation phase provides broad end-to-end UI coverage for QA Commerce Lab.

The framework demonstrates:

- Page Object Model architecture
- Reusable Selenium components
- Positive and negative testing
- Boundary-value testing
- Authentication and state handling
- Cross-browser support
- Headless execution
- Dynamic waits
- Test isolation
- TestNG grouping
- Automated defect detection
- Screenshot evidence
- Allure reporting
- Maintainable test organization

The suite intentionally preserves failing assertions for confirmed defects rather than changing expected results to match incorrect application behavior.