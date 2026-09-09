# QA Commerce Lab — Playwright + TypeScript Automation

[![Playwright Tests](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/playwright.yml/badge.svg)](https://github.com/<YOUR-GITHUB-USERNAME>/qa-commerce-qa-portfolio/actions/workflows/playwright.yml)

Comprehensive Playwright automation framework for the **QA Commerce Lab** QA portfolio project.

The framework demonstrates modern browser automation using TypeScript, Page Object Model architecture, isolated browser contexts, reusable authentication state, network interception, API mocking, cross-browser execution, reporting, and executable known-defect verification.

---

# 1. Application Under Test

Production:

`https://qa-commerce-lab.vercel.app`

API:

`https://qa-commerce-lab.vercel.app/api`

---

# 2. Technology Stack

- Playwright Test
- TypeScript
- Node.js
- Page Object Model
- HTML Reporter
- JSON Reporter
- JUnit Reporter
- Google Chrome
- Microsoft Edge
- Chromium
- Firefox
- WebKit

---

# 3. Browser Strategy

## Local Windows Execution

Windows Security blocks some Playwright-downloaded browser binaries on the development machine.

Local execution therefore uses installed and trusted browsers:

```text
Google Chrome
Microsoft Edge
```

Playwright projects:

```text
chrome
edge
```

## CI Execution

GitHub Actions will use:

```text
Chromium
Firefox
WebKit
```

This allows full Playwright cross-browser coverage without weakening local Windows security settings.

---

# 4. Project Structure

```text
playwright/
├── fixtures/
│   └── auth.fixture.ts
│
├── pages/
│   ├── AccountPage.ts
│   ├── CartPage.ts
│   ├── CheckoutPage.ts
│   ├── ContactPage.ts
│   ├── HomePage.ts
│   ├── LoginPage.ts
│   ├── ProductsPage.ts
│   ├── QALabPage.ts
│   └── RegistrationPage.ts
│
├── test-data/
│   ├── auth-state.ts
│   └── users.ts
│
├── tests/
│   ├── account/
│   │   └── registration-account.spec.ts
│   ├── authentication/
│   │   └── login.spec.ts
│   ├── cart/
│   │   ├── cart.spec.ts
│   │   └── coupons.spec.ts
│   ├── checkout/
│   │   └── checkout.spec.ts
│   ├── contact/
│   │   └── contact.spec.ts
│   ├── known-defects/
│   │   └── known-defects.spec.ts
│   ├── network/
│   │   └── network.spec.ts
│   ├── products/
│   │   └── products.spec.ts
│   ├── qa-lab/
│   │   └── qa-lab.spec.ts
│   └── smoke/
│       └── home.spec.ts
│
├── utils/
│   └── network.ts
│
├── package.json
├── package-lock.json
├── playwright.config.ts
├── tsconfig.json
└── README.md
```

---

# 5. Installation

Install dependencies:

```powershell
npm install
```

Type-check:

```powershell
npm run typecheck
```

---

# 6. TypeScript Configuration

The framework uses strict TypeScript configuration.

Important settings include:

```json
{
  "target": "ES2022",
  "module": "NodeNext",
  "moduleResolution": "NodeNext",
  "strict": true,
  "noEmit": true
}
```

This allows TypeScript to detect invalid types during development without generating compiled JavaScript output.

---

# 7. Run All Tests

```powershell
npm test
```

Local execution runs against:

```text
Chrome
Edge
```

---

# 8. Functional Suite

Run all tests except known defects:

```powershell
npm run test:functional
```

This is the preferred command when validating normal application functionality.

---

# 9. Smoke Suite

```powershell
npm run test:smoke
```

---

# 10. Regression Suite

```powershell
npm run test:regression
```

---

# 11. Chrome

```powershell
npm run test:chrome
```

---

# 12. Edge

```powershell
npm run test:edge
```

---

# 13. Known Defects

```powershell
npm run test:defects
```

Known-defect tests use Playwright's:

```typescript
test.fail(...)
```

The assertion continues to represent the correct expected business behavior.

While the defect remains open, failure is expected.

If the defect is fixed and the test unexpectedly passes, Playwright reports that the expected failure did not occur.

This acts as a signal to:

```text
Verify the fix
Close the defect
Remove test.fail()
Move the test into normal regression coverage
```

---

# 14. Network Tests

```powershell
npm run test:network
```

These tests demonstrate Playwright-specific browser networking capabilities including:

```text
page.waitForRequest()
page.waitForResponse()
page.route()
route.fulfill()
HTTP status inspection
HTTP header inspection
API response mocking
Test report attachments
```

---

# 15. Test Tags

Tests use tags in their titles.

Examples:

```text
@smoke
@regression
@authentication
@registration
@products
@cart
@coupon
@checkout
@account
@contact
@qa-lab
@network
@known-defect
```

PowerShell commands using `@` should quote the grep value.

Example:

```powershell
npx playwright test --grep "@checkout"
```

Chrome only:

```powershell
npx playwright test --grep "@checkout" --project=chrome
```

---

# 16. Page Object Model

The framework separates page interaction from test assertions.

Page objects provide:

```text
Locators
Navigation
Reusable actions
Page-specific behavior
```

Tests provide:

```text
Test scenarios
Assertions
Expected behavior
Business-rule validation
```

Playwright `Locator` objects are used rather than repeatedly locating elements manually.

---

# 17. Locator Strategy

Stable semantic locators are preferred.

Examples:

```typescript
page.getByTestId("login-email");
```

```typescript
page.getByRole("heading", {
  name: "Your basket",
});
```

```typescript
page.getByText("Signed out.", {
  exact: true,
});
```

CSS selectors are used primarily where the application does not expose a stable semantic locator.

---

# 18. Auto-Waiting

Playwright automatically waits for elements to become actionable.

This reduces the need for explicit waiting logic compared with traditional Selenium automation.

Assertions such as:

```typescript
await expect(locator).toBeVisible();
```

automatically retry until the configured timeout expires.

---

# 19. Authentication Fixture

Authenticated tests can use:

```text
fixtures/auth.fixture.ts
```

The fixture initializes the browser context with the seed user's authentication state.

This avoids repeating:

```text
Open login
Enter email
Enter password
Submit
Wait for account
```

before every authenticated business test.

Authentication-specific tests still use the actual Login UI.

---

# 20. Storage State

The seed authentication state is stored in:

```text
test-data/auth-state.ts
```

Only the session information required by the application is provided.

The password does not need to be stored in the authenticated browser state.

Each test still receives a new isolated browser context.

---

# 21. Test Isolation

Every Playwright test receives its own browser context.

This prevents:

```text
Cookies
localStorage
sessionStorage
Cart state
Authentication changes
Coupon state
```

from leaking between tests.

---

# 22. Automated Functional Coverage

The framework covers:

```text
Home navigation
Authentication
Registration
Products
Search
Category filtering
Sorting
Stock behavior
Cart
Cart quantities
Cart persistence
Coupons
Checkout
Shipping validation
Payment validation
Account management
Logout
Account deletion
Contact support
File uploads
Unicode input
QA Lab
Dynamic DOM
Modal interactions
Deterministic HTTP failures
Network requests
Network responses
API mocking
Known defects
```

---

# 23. Approximate Logical Test Count

```text
Home                 2
Authentication       7
Products            12
Cart                 10
Coupons               9
Checkout             13
Registration         15
Account               8
Contact              13
QA Lab               13
Network               4
Known Defects         3
------------------------
Total               109
```

The latest Playwright execution output is the authoritative source if tests are added or changed.

With the two local browser projects, this represents approximately:

```text
109 × 2 = 218 browser executions
```

---

# 24. Authentication Coverage

Tests include:

```text
Valid login
Invalid password
Malformed email
Empty credentials
Case-insensitive email
Email whitespace trimming
Case-sensitive password
```

---

# 25. Registration Coverage

Tests include:

```text
Required names
Email validation
Duplicate email
Password minimum
Uppercase requirement
Lowercase requirement
Digit requirement
Special-character requirement
Password confirmation
Terms acceptance
First-name maxlength
Last-name maxlength
Password maxlength
Successful registration
Login after registration
```

---

# 26. Product Coverage

Tests include:

```text
Full product catalog
Search
Case-insensitive search
Whitespace trimming
Empty search results
Category filtering
Ascending price
Descending price
Unicode product names
Out-of-stock products
Zero-price products
Add to Cart
```

---

# 27. Cart Coverage

Tests include:

```text
Empty cart
Subtotal
Quantity editing
Removal
Persistence
Invalid quantity
Stock validation
Multiple products
Monetary rounding
Guest checkout
```

---

# 28. Coupon Coverage

Tests include:

```text
SAVE10
FREESHIP
MIN100
Minimum subtotal requirement
Expired coupon
Unknown coupon
Case-insensitive codes
Whitespace trimming
Automatic free shipping threshold
```

---

# 29. Checkout Coverage

Tests include:

```text
Authenticated checkout
Prefilled account information
Required shipping fields
Address validation
Luhn card validation
CVV boundaries
Expiry format
Declined payment
Insufficient funds
Card-number spaces
Successful checkout
Duplicate submission prevention
Persisted cart clearing
```

---

# 30. Account Coverage

Tests include:

```text
Account details
Logout
Signed-out account access
Delete confirmation
Case-sensitive DELETE
Whitespace handling
Protected seed account
Dynamic account deletion
```

---

# 31. Contact Coverage

Tests include:

```text
Valid request
Minimum name length
Email validation
Required subject
Supported subjects
Message minimum
Message maximum
Unicode data
PNG attachment
Unsupported file type
Attachment above 2 MB
```

---

# 32. QA Lab Coverage

Deterministic scenarios include:

```text
Slow response
204
400
401
404
409
422
429
500
Large response
Delayed DOM
Modal cancel
Modal confirm
```

The 429 test verifies:

```text
Retry-After: 5
```

---

# 33. Browser Network Testing

Playwright can inspect browser traffic directly.

Examples:

```typescript
page.waitForRequest(...)
```

```typescript
page.waitForResponse(...)
```

This allows tests to validate the actual HTTP layer used by the UI.

The framework verifies:

```text
Request method
Request URL
Response status
Response headers
```

---

# 34. API Mocking

Playwright intercepts browser requests using:

```typescript
page.route(...)
```

Synthetic responses are returned using:

```typescript
route.fulfill(...)
```

This allows frontend behavior to be tested against controlled backend failures without modifying the actual production API.

Examples include synthetic:

```text
418
503
```

responses.

---

# 35. Network Evidence

Network tests attach request and response metadata using:

```typescript
test.info().attach(...)
```

These attachments appear in Playwright reports.

---

# 36. Confirmed UI Defects

## BUG-UI-CHK-001

Cart counter does not update immediately after successful checkout.

Persisted cart data is cleared, but the visible header counter remains stale until refresh.

---

## BUG-UI-CHK-002

Checkout accepts impossible expiry month.

Example:

```text
13/30
```

The application validates format but does not properly validate month range.

---

## BUG-UI-CART-001

QA Sticker Pack quantity limit can be bypassed from the cart editor.

Business maximum:

```text
25
```

Physical stock:

```text
500
```

The cart editor validates against stock rather than the special business maximum.

---

# 37. Known-Defect Strategy

Known defects use:

```typescript
test.fail(...)
```

This allows the automated test to retain the correct expected assertion while documenting that the current application is known to violate it.

A fixed defect causes an unexpected pass, signaling that the test should be promoted back into normal regression coverage.

---

# 38. Failure Evidence

Playwright automatically retains failure artifacts according to configuration.

Current configuration includes:

```text
Screenshot: only on failure
Video: retain on failure
Trace: on first retry
```

Artifacts are stored under:

```text
reports/playwright/test-results/
```

---

# 39. HTML Report

Generated under:

```text
reports/playwright/html-report/
```

Open:

```powershell
npm run report
```

or:

```powershell
npx playwright show-report ../reports/playwright/html-report
```

---

# 40. JSON Report

Generated under:

```text
reports/playwright/results.json
```

The JSON output can be used for programmatic analysis or portfolio reporting.

---

# 41. JUnit Report

Generated under:

```text
reports/playwright/junit-results.xml
```

JUnit output will also be useful for CI integrations.

---

# 42. Type Checking

Run:

```powershell
npm run typecheck
```

Equivalent:

```powershell
npx tsc --noEmit
```

The framework should have no TypeScript errors before test execution.

---

# 43. Useful Commands

Type-check:

```powershell
npm run typecheck
```

All tests:

```powershell
npm test
```

Functional tests:

```powershell
npm run test:functional
```

Smoke:

```powershell
npm run test:smoke
```

Regression:

```powershell
npm run test:regression
```

Chrome:

```powershell
npm run test:chrome
```

Edge:

```powershell
npm run test:edge
```

Known defects:

```powershell
npm run test:defects
```

Network:

```powershell
npm run test:network
```

Report:

```powershell
npm run report
```

---

# 44. Final Local Validation

Run:

```powershell
npm run typecheck
```

Then:

```powershell
npm run test:functional
```

Then:

```powershell
npm run test:defects
```

Then:

```powershell
npm run test:network
```

Finally:

```powershell
npm test
```

The full run should complete successfully when all normal tests pass and known defects fail only in the expected `test.fail()` manner.

---

# 45. Reporting Outputs

Expected repository-level output:

```text
reports/
└── playwright/
    ├── html-report/
    ├── test-results/
    ├── junit-results.xml
    └── results.json
```

---

# 46. Framework Strengths

The Playwright framework demonstrates:

```text
TypeScript
Strict typing
Page Object Model
Playwright Locators
Auto-waiting
Browser contexts
Storage state
Custom fixtures
Test tags
Parallel execution
Chrome testing
Edge testing
Cross-browser CI readiness
Screenshots
Video
Tracing
HTML reports
JSON reports
JUnit reports
Request inspection
Response inspection
HTTP header assertions
Network interception
API mocking
Known-defect verification
```

---

# 47. Playwright Phase Status

The Playwright framework is ready for CI when:

```text
TypeScript passes
Functional suite passes
Chrome passes
Edge passes
Known defects reproduce as expected
Network tests pass
HTML report generates
JUnit report generates
JSON report generates
```

The next phase adds GitHub Actions execution using:

```text
Chromium
Firefox
WebKit
```

on Linux.

## Continuous Integration

The Playwright framework runs automatically in GitHub Actions on:

- Chromium
- Firefox
- WebKit

CI runs on Linux and includes:

- Dependency installation with `npm ci`
- TypeScript validation
- Playwright browser installation
- Cross-browser test execution
- HTML report generation
- JUnit report generation
- JSON report generation
- Failure screenshots, videos, and traces when applicable

Workflow:

`.github/workflows/playwright.yml`

Local Windows execution uses installed Google Chrome and Microsoft Edge, while GitHub Actions provides Chromium, Firefox, and WebKit coverage.
