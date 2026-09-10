# QA Commerce Lab — Cypress Automation Framework

Cypress + TypeScript automation framework for the **QA Commerce Lab** portfolio project.

This framework demonstrates production-style web UI automation, REST API automation, negative and boundary testing, test isolation, reusable commands, session management, reporting, failure evidence, known-defect verification, and CI-ready execution.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Application Under Test](#application-under-test)
- [Framework Objectives](#framework-objectives)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Test Account](#test-account)
- [NPM Scripts](#npm-scripts)
- [Running the Tests](#running-the-tests)
- [UI Automation Coverage](#ui-automation-coverage)
- [API Automation Coverage](#api-automation-coverage)
- [Known Defect Strategy](#known-defect-strategy)
- [Confirmed Known Defects](#confirmed-known-defects)
- [Test Isolation](#test-isolation)
- [Session Management](#session-management)
- [REST API Testing](#rest-api-testing)
- [Network Testing](#network-testing)
- [Fixtures and Test Data](#fixtures-and-test-data)
- [Selectors](#selectors)
- [Assertions](#assertions)
- [Retries](#retries)
- [Screenshots](#screenshots)
- [Videos](#videos)
- [Mochawesome Reporting](#mochawesome-reporting)
- [CI Execution Strategy](#ci-execution-strategy)
- [Current Automated Test Count](#current-automated-test-count)
- [Test Design Approach](#test-design-approach)
- [Naming and Organization](#naming-and-organization)
- [Failure Investigation](#failure-investigation)
- [Troubleshooting](#troubleshooting)
- [Generated Artifacts](#generated-artifacts)
- [Portfolio Context](#portfolio-context)

---

# Project Overview

The Cypress framework is one part of a larger Software Quality Engineering portfolio built around a purpose-designed e-commerce application called **QA Commerce Lab**.

The project contains intentionally testable business rules and deterministic edge cases across:

- Authentication
- Registration
- Product catalog
- Search
- Shopping cart
- Coupons
- Checkout
- Account management
- Contact form
- REST APIs
- Stock validation
- Payment simulations
- Dynamic UI behavior
- Controlled API failures
- Known application defects

The Cypress implementation covers both:

1. **Frontend UI automation**
2. **Backend REST API automation**

This allows the framework to validate behavior at multiple layers without depending exclusively on end-to-end browser flows.

---

# Application Under Test

Production application:

```text
https://qa-commerce-lab.vercel.app
```

REST API root:

```text
https://qa-commerce-lab.vercel.app/api
```

Important application routes include:

```text
/
 /products
 /login
 /register
 /cart
 /checkout
 /account
 /contact
 /qa-lab
 /api-docs
```

---

# Framework Objectives

The framework demonstrates:

- Cypress automation architecture
- TypeScript-based automated testing
- End-to-end UI automation
- REST API automation
- Positive testing
- Negative testing
- Boundary-value testing
- Business-rule validation
- Authentication testing
- Registration testing
- Search testing
- Product catalog testing
- Shopping cart testing
- Coupon testing
- Checkout testing
- Account lifecycle testing
- Contact form testing
- File upload testing
- Unicode testing
- Dynamic DOM testing
- Modal testing
- Network interception
- HTTP response validation
- Stock validation
- Payment simulation testing
- Monetary calculation validation
- Session reuse
- Test isolation
- Cross-browser execution
- Automatic failure screenshots
- Execution video recording
- HTML reporting
- Known-defect automation
- CI-ready test execution
- Maintainable test organization

---

# Technology Stack

The Cypress framework uses:

- Cypress
- TypeScript
- Node.js
- npm
- `cy.request()`
- `cy.intercept()`
- `cy.session()`
- Cypress fixtures
- Custom Cypress commands
- Chrome
- Microsoft Edge
- Mochawesome
- mochawesome-merge
- mochawesome-report-generator
- Git
- GitHub
- GitHub Actions

Exact dependency versions are maintained in:

```text
package.json
package-lock.json
```

---

# Project Structure

```text
cypress/
├── README.md
├── package.json
├── package-lock.json
├── tsconfig.json
├── cypress.config.ts
│
└── cypress/
    ├── e2e/
    │   │
    │   ├── smoke/
    │   │   └── home.cy.ts
    │   │
    │   ├── authentication/
    │   │   └── login.cy.ts
    │   │
    │   ├── products/
    │   │   └── products.cy.ts
    │   │
    │   ├── cart/
    │   │   ├── cart.cy.ts
    │   │   └── coupons.cy.ts
    │   │
    │   ├── checkout/
    │   │   └── checkout.cy.ts
    │   │
    │   ├── account/
    │   │   ├── registration.cy.ts
    │   │   └── account.cy.ts
    │   │
    │   ├── contact/
    │   │   └── contact.cy.ts
    │   │
    │   ├── qa-lab/
    │   │   └── qa-lab.cy.ts
    │   │
    │   ├── api/
    │   │   ├── core-api.cy.ts
    │   │   ├── products-api.cy.ts
    │   │   ├── search-api.cy.ts
    │   │   ├── coupons-api.cy.ts
    │   │   └── orders-api.cy.ts
    │   │
    │   └── known-defects/
    │       ├── known-defects.cy.ts
    │       └── api-known-defects.cy.ts
    │
    ├── fixtures/
    │   └── users.json
    │
    └── support/
        ├── commands.ts
        └── e2e.ts
```

Generated Cypress evidence is stored outside the framework directory:

```text
reports/
└── cypress/
    ├── screenshots/
    ├── videos/
    ├── downloads/
    ├── mochawesome/
    ├── mochawesome.json
    └── html/
        └── mochawesome.html
```

---

# Prerequisites

Install:

- Node.js
- npm
- Google Chrome
- Microsoft Edge if Edge execution is required
- Git

Verify Node.js:

```powershell
node --version
```

Verify npm:

```powershell
npm --version
```

Verify Git:

```powershell
git --version
```

---

# Installation

Navigate to the Cypress framework directory:

```powershell
cd C:\Projects\qa-commerce-qa-portfolio\cypress
```

Install dependencies:

```powershell
npm install
```

The framework can then be validated with:

```powershell
npm run typecheck
```

---

# Configuration

The Cypress configuration is stored in:

```text
cypress.config.ts
```

The framework uses the production application as its base URL:

```text
https://qa-commerce-lab.vercel.app
```

Main configuration behavior includes:

```text
Spec pattern:
cypress/e2e/**/*.cy.ts

Support file:
cypress/support/e2e.ts

Fixtures:
cypress/fixtures/

Command timeout:
10 seconds

Request timeout:
10 seconds

Response timeout:
30 seconds

Page load timeout:
30 seconds
```

Retry configuration:

```text
Run mode:  1 retry
Open mode: 0 retries
```

Failure screenshots are enabled.

Execution videos are enabled.

---

# Test Account

A deterministic seed account is available for test automation:

```text
Email:    qa.user@example.com
Password: Password123!
```

This is a dedicated test account used by QA Commerce Lab.

Authentication tests intentionally validate behavior such as:

- Correct credentials
- Invalid credentials
- Malformed email
- Email case normalization
- Email whitespace trimming
- Password case sensitivity

---

# NPM Scripts

The framework exposes scripts for local execution, API execution, known defects, reporting, and CI validation.

View all configured scripts:

```powershell
npm pkg get scripts
```

Main scripts include:

| Script                    | Purpose                                         |
| ------------------------- | ----------------------------------------------- |
| `npm run typecheck`       | Run TypeScript static validation                |
| `npm run cy:open`         | Open Cypress interactive mode                   |
| `npm run cy:run`          | Run all discovered Cypress specs                |
| `npm run cy:chrome`       | Run all specs using Chrome                      |
| `npm run cy:edge`         | Run all specs using Microsoft Edge              |
| `npm run cy:functional`   | Run normal functional UI tests                  |
| `npm run cy:api`          | Run normal REST API tests                       |
| `npm run cy:defects`      | Run confirmed known-defect tests                |
| `npm run cy:ci`           | Run TypeScript + functional UI + API validation |
| `npm run cy:report:merge` | Merge Mochawesome JSON results                  |
| `npm run cy:report:html`  | Generate the Mochawesome HTML report            |
| `npm run cy:report`       | Merge results and generate HTML                 |

---

# Running the Tests

## TypeScript Validation

Run before executing the suite:

```powershell
npm run typecheck
```

This performs TypeScript static checking without emitting compiled JavaScript.

---

## Cypress Interactive Mode

Open Cypress:

```powershell
npm run cy:open
```

This is useful for:

- Developing tests
- Debugging selectors
- Watching browser execution
- Investigating failures
- Running individual specs

---

## Run All Cypress Specs

```powershell
npm run cy:run
```

Important:

This discovers **all** Cypress specs, including confirmed known-defect tests.

Therefore the run may contain intentional failures while known defects remain open.

For normal validation, use:

```powershell
npm run cy:ci
```

---

## Run Functional UI Tests

```powershell
npm run cy:functional
```

This excludes the known-defect directory.

The functional UI suite should remain green.

---

## Run API Tests

```powershell
npm run cy:api
```

This runs:

```text
core-api.cy.ts
products-api.cy.ts
search-api.cy.ts
coupons-api.cy.ts
orders-api.cy.ts
```

Confirmed API defects are kept outside this command.

---

## Run Known Defects

```powershell
npm run cy:defects
```

Known-defect tests intentionally assert the correct expected behavior.

They may therefore fail while the corresponding application bugs remain unresolved.

---

## Run CI Validation Locally

```powershell
npm run cy:ci
```

The CI command performs:

```text
1. TypeScript validation
2. Functional UI automation
3. REST API automation
```

Equivalent logical flow:

```text
typecheck
    ↓
functional UI suite
    ↓
API suite
```

Known defects are excluded.

---

## Chrome Execution

```powershell
npm run cy:chrome
```

---

## Microsoft Edge Execution

```powershell
npm run cy:edge
```

---

# UI Automation Coverage

## Smoke / Home

File:

```text
cypress/e2e/smoke/home.cy.ts
```

Coverage includes:

- Home page loads successfully
- Main shop navigation works

Current logical tests:

```text
2
```

---

## Authentication

File:

```text
cypress/e2e/authentication/login.cy.ts
```

Coverage includes:

- Valid seed login
- Invalid password
- Malformed email
- Empty credentials
- Case-insensitive email
- Email whitespace trimming
- Password case sensitivity

Current logical tests:

```text
7
```

---

## Products

File:

```text
cypress/e2e/products/products.cy.ts
```

Coverage includes:

- Product catalog count
- Product search
- Case-insensitive search
- Search whitespace handling
- Empty-result behavior
- Category filtering
- Price sorting ascending
- Price sorting descending
- Unicode product names
- Out-of-stock state
- Free product behavior
- Add-to-cart behavior

Current logical tests:

```text
12
```

---

## Cart

File:

```text
cypress/e2e/cart/cart.cy.ts
```

Coverage includes:

- Empty cart
- Subtotal
- Shipping
- Quantity updates
- Product removal
- Cart persistence
- Invalid quantity
- Stock maximum
- Multi-product cart
- Monetary rounding
- Guest checkout authentication requirement

Current logical tests:

```text
10
```

---

## Coupon UI

File:

```text
cypress/e2e/cart/coupons.cy.ts
```

Coverage includes:

- `SAVE10`
- `FREESHIP`
- `MIN100`
- Minimum-order boundary
- Expired coupon
- Unknown coupon
- Case-insensitive coupon entry
- Whitespace normalization
- Automatic free-shipping threshold

Current logical tests:

```text
9
```

---

## Checkout

File:

```text
cypress/e2e/checkout/checkout.cy.ts
```

Coverage includes:

- Authenticated checkout
- User-data prefill
- Missing shipping data
- Minimum address length
- Invalid card number
- CVV validation
- Invalid expiry formatting
- Declined-card simulation
- Insufficient-funds simulation
- Card-number whitespace normalization
- Successful checkout
- Duplicate submission prevention
- Cart clearing after successful checkout

Current normal logical tests:

```text
13
```

Confirmed checkout defects are stored separately.

---

## Registration

File:

```text
cypress/e2e/account/registration.cy.ts
```

Coverage includes:

- Successful registration
- Required first name
- Required last name
- Email validation
- Duplicate email
- Password minimum length
- Uppercase requirement
- Lowercase requirement
- Numeric requirement
- Special-character requirement
- Password confirmation
- Terms acceptance
- First-name maximum length
- Last-name maximum length
- Email maximum length
- Password maximum length

Current logical tests:

```text
16
```

---

## Account

File:

```text
cypress/e2e/account/account.cy.ts
```

Coverage includes:

- Account details
- Logout
- Unauthorized account access
- Incorrect delete confirmation
- Empty delete confirmation
- Dynamic-account deletion
- Login rejection after account deletion
- Seed-account persistence

Current logical tests:

```text
8
```

---

## Contact

File:

```text
cypress/e2e/contact/contact.cy.ts
```

Coverage includes:

- Successful support request
- Minimum name length
- Invalid email
- Required subject
- Supported subject options
- Message minimum length
- Message boundary values
- Maximum message length
- Unicode data
- Valid file upload
- Unsupported file type
- Maximum file size

Current logical tests:

```text
13
```

---

## QA Lab

File:

```text
cypress/e2e/qa-lab/qa-lab.cy.ts
```

Coverage includes:

- Delayed DOM element
- Slow API response
- HTTP 204
- HTTP 400
- HTTP 401
- HTTP 404
- HTTP 409
- HTTP 422
- HTTP 429
- `Retry-After` validation
- HTTP 500
- Large-response handling
- Modal cancel
- Modal confirmation

Current logical tests:

```text
13
```

---

# API Automation Coverage

API automation uses Cypress directly instead of relying on browser interactions.

Primary mechanism:

```typescript
cy.request();
```

The API tests validate:

- HTTP methods
- Status codes
- Request payloads
- Response bodies
- Error handling
- Validation rules
- Business rules
- Data normalization
- Pagination
- Filtering
- Sorting
- Search
- Authentication
- Coupon calculations
- Product stock
- Payment simulations
- Monetary rounding
- Order calculations

---

## Core API

File:

```text
cypress/e2e/api/core-api.cy.ts
```

Coverage includes:

### Health

- `/api/health`
- HTTP 200

### Authentication

- Valid credentials
- Authentication token
- Password not exposed
- Case-insensitive email
- Trimmed email
- Missing credentials
- Malformed email
- Invalid password
- Password case sensitivity

### Contact API

- Valid support request
- Minimum name length
- Message minimum length
- Valid boundaries

Current logical tests:

```text
13
```

---

## Products API

File:

```text
cypress/e2e/api/products-api.cy.ts
```

Coverage includes:

- Default products page
- Pagination
- Page beyond final page
- Category filtering
- In-stock filtering
- Price ascending
- Price descending
- Product lookup by ID
- Raw decimal-price data
- Zero-price product

Current logical tests:

```text
10
```

---

## Search API

File:

```text
cypress/e2e/api/search-api.cy.ts
```

Coverage includes:

- Search by product name
- Search by brand
- Search by category
- Case-insensitive search
- Whitespace trimming
- No-match behavior
- Long query handling
- Unicode search

Current logical tests:

```text
8
```

---

## Coupons API

File:

```text
cypress/e2e/api/coupons-api.cy.ts
```

Coverage includes:

- `SAVE10`
- 10% discount calculation
- Decimal rounding
- `FREESHIP`
- `MIN100`
- Minimum boundary exactly at `$100`
- Above-minimum subtotal
- Below-minimum rejection
- Unknown coupon
- Case-insensitive code
- Mixed-case code
- Whitespace trimming
- Zero subtotal

Current logical tests:

```text
11
```

---

## Orders API

File:

```text
cypress/e2e/api/orders-api.cy.ts
```

Coverage includes:

- Valid order
- Empty items
- Missing items
- Missing shipping
- Missing shipping email
- Invalid shipping email
- Missing shipping address
- Missing payment
- Invalid Luhn card
- Product not found
- Zero quantity
- Negative quantity
- Decimal quantity
- Quantity boundary of one
- Insufficient stock
- Out-of-stock product
- Declined card
- Insufficient funds
- Successful-order details
- Subtotal calculation
- Decimal monetary rounding
- Multiple-product totals
- USD currency validation
- Card-number whitespace normalization

Current logical tests:

```text
24
```

---

# Known Defect Strategy

Confirmed defects are deliberately separated from normal functional regression tests.

Directory:

```text
cypress/e2e/known-defects/
```

The strategy is:

```text
Normal functional tests
        ↓
Expected to pass

Normal API tests
        ↓
Expected to pass

Known-defect tests
        ↓
Assert the correct required behavior
        ↓
Expected to fail while the bug remains open
```

The automation is **not modified to accept incorrect application behavior**.

For example, if the correct requirement is:

```text
HTTP 409
```

but the defective application returns:

```text
HTTP 201
```

the automated test continues asserting:

```typescript
expect(response.status).to.equal(409);
```

This makes the test executable evidence of the defect.

When the defect is fixed:

```text
Known defect test starts passing
        ↓
Fix is verified
        ↓
Test can move into the normal regression suite
```

---

# Confirmed Known Defects

## BUG-UI-CART-001 — Sticker Pack Quantity Limit Can Be Bypassed

Product:

```text
QA Sticker Pack
```

Product ID:

```text
7
```

Physical stock:

```text
500
```

Business maximum purchase quantity:

```text
25
```

The normal Add to Cart logic enforces the maximum of `25`.

However, the cart quantity editor validates against physical stock instead of the special purchase limit.

This allows quantities above `25`.

Correct behavior:

```text
Quantity must not exceed 25
```

Known defective behavior:

```text
Quantity editor may allow values up to physical stock
```

---

## BUG-UI-CHK-001 — Cart Counter Does Not Update After Successful Checkout

After successful checkout:

```text
localStorage cart -> cleared
visible cart counter -> stale
```

Refreshing the application updates the counter correctly.

Correct behavior:

```text
The navigation cart counter should immediately display 0.
```

---

## BUG-UI-CHK-002 — Impossible Expiry Month Is Accepted

Example:

```text
13/30
```

The checkout validates the input format:

```text
MM/YY
```

but does not properly validate that the month is between:

```text
01
```

and:

```text
12
```

Correct behavior:

```text
13/30 should be rejected.
```

---

## BUG-API-ORD-001 — Duplicate Product Lines Bypass Aggregated Stock Validation

Product ID:

```text
2
```

Available stock:

```text
1
```

Request:

```json
{
  "items": [
    {
      "id": 2,
      "qty": 1
    },
    {
      "id": 2,
      "qty": 1
    }
  ]
}
```

Combined requested quantity:

```text
2
```

Available quantity:

```text
1
```

Correct behavior:

```text
HTTP 409
INSUFFICIENT_STOCK
```

Known defective behavior:

The API validates each order line independently.

Each individual line requests:

```text
qty = 1
```

which appears valid against stock `1`.

The duplicate lines are not aggregated before stock validation, allowing a combined requested quantity greater than available stock.

The known-defect Cypress test continues asserting the correct expected behavior.

---

# Test Isolation

Test isolation is important to prevent one automated test from affecting another.

Global setup is located in:

```text
cypress/support/e2e.ts
```

Before each test the framework clears:

- Cookies
- `localStorage`
- `sessionStorage`

This helps prevent:

- Cart leakage
- Authentication leakage
- Registration state leakage
- Previous-test dependencies
- False positives caused by persisted state

Tests should be independently executable whenever possible.

---

# Session Management

Authenticated tests can reuse the deterministic seed user through:

```typescript
cy.session();
```

Custom command:

```typescript
cy.loginSeedUser();
```

The session is created using:

```text
qa.user@example.com
Password123!
```

Session validation confirms that authenticated account access is still valid.

This reduces unnecessary login repetition while preserving deterministic authentication behavior.

---

# REST API Testing

Cypress API automation uses:

```typescript
cy.request();
```

Example pattern:

```typescript
cy.request({
  method: "POST",
  url: "/api/auth",
  body: {
    email: "qa.user@example.com",
    password: "Password123!",
  },
}).then((response) => {
  expect(response.status).to.equal(200);
});
```

For expected non-2xx responses:

```typescript
cy.request({
  method: "POST",
  url: "/api/auth",
  body: {},
  failOnStatusCode: false,
}).then((response) => {
  expect(response.status).to.equal(400);
  expect(response.body.error).to.equal("MISSING_CREDENTIALS");
});
```

This allows negative API tests to validate the returned error instead of Cypress terminating the test automatically.

---

# Network Testing

UI tests can observe backend communication using:

```typescript
cy.intercept();
```

The QA Lab uses deterministic response modes for testing:

```text
200
204
400
401
404
409
422
429
500
```

Additional behavior includes:

- Slow responses
- Rate limiting
- `Retry-After`
- Large response payloads
- Delayed DOM rendering

Example:

```typescript
cy.intercept("GET", "**/api/**").as("apiRequest");
```

This allows validation of both:

```text
Browser behavior
+
Network behavior
```

---

# Fixtures and Test Data

Fixtures are stored under:

```text
cypress/fixtures/
```

Current user fixture:

```text
users.json
```

Fixtures are used for deterministic reusable test data such as:

- Seed users
- Invalid credentials
- Authentication data

Dynamic users can also be created where lifecycle tests require unique registration data.

---

# Selectors

The application provides stable automation selectors using:

```text
data-testid
```

Examples include:

```text
nav-account
nav-cart
cart-count
hero-shop
hero-qa-lab
product-search
product-card-{id}
add-cart-{id}
login-email
login-password
login-submit
empty-products
empty-cart
card-number
place-order
order-success
dynamic-result
```

Stable selectors are preferred over fragile selectors based on:

- Visual position
- DOM hierarchy
- Styling
- Generated classes

When a stable `data-testid` does not exist, semantic or stable ID-based selectors are used.

---

# Assertions

The suite validates more than page visibility.

Assertions cover:

- URLs
- Element visibility
- Element state
- Text
- Error messages
- HTTP status codes
- JSON properties
- Response structure
- Monetary calculations
- Product counts
- Stock values
- Currency
- Authentication tokens
- Business rules
- Cart persistence
- Session state
- Order confirmation

Example UI assertion:

```typescript
cy.get('[data-testid="order-success"]').should("be.visible");
```

Example API assertion:

```typescript
expect(response.status).to.equal(409);

expect(response.body.error).to.equal("INSUFFICIENT_STOCK");
```

---

# Retries

Cypress retries are configured differently for local interactive development and execution mode.

Current behavior:

```text
Open mode:
0 retries

Run mode:
1 retry
```

Retries are useful for identifying potentially intermittent failures, but should not be used to hide unstable tests.

If a test only passes because of repeated retries, the underlying issue should still be investigated.

Possible causes include:

- Incorrect synchronization
- Unstable selectors
- Shared state
- Network timing
- Application defects
- Test data collisions

---

# Screenshots

Failure screenshots are enabled.

Location:

```text
reports/cypress/screenshots/
```

Cypress automatically captures screenshots when a test fails in run mode.

Screenshots provide evidence of:

- UI failures
- Unexpected validation behavior
- Known defects
- Regression failures

---

# Videos

Execution video recording is enabled.

Location:

```text
reports/cypress/videos/
```

Videos are useful for:

- CI failure investigation
- Timing issues
- Reproducing unexpected test behavior
- Reviewing automated flows

---

# Mochawesome Reporting

The framework uses Mochawesome for test reporting.

Packages:

```text
mochawesome
mochawesome-merge
mochawesome-report-generator
```

Raw JSON results are written to:

```text
reports/cypress/mochawesome/
```

---

## Merge Mochawesome JSON Files

```powershell
npm run cy:report:merge
```

Merged result:

```text
reports/cypress/mochawesome.json
```

---

## Generate HTML Report

```powershell
npm run cy:report:html
```

HTML report:

```text
reports/cypress/html/mochawesome.html
```

---

## Merge and Generate Report

Run both steps:

```powershell
npm run cy:report
```

---

## Open the HTML Report

From the Cypress directory:

```powershell
Start-Process (
  Resolve-Path "..\reports\cypress\html\mochawesome.html"
)
```

Or:

```powershell
Invoke-Item "..\reports\cypress\html\mochawesome.html"
```

---

# CI Execution Strategy

The normal CI pipeline should execute:

```text
TypeScript validation
        ↓
Functional UI tests
        ↓
REST API tests
        ↓
Upload Cypress evidence
```

Primary CI command:

```powershell
npm run cy:ci
```

Equivalent:

```text
npm run typecheck
npm run cy:functional
npm run cy:api
```

Confirmed defect tests are deliberately excluded.

They are maintained separately so an acknowledged open defect does not make the normal CI pipeline permanently red.

The Cypress GitHub Actions workflow is maintained under the repository's:

```text
.github/workflows/
```

once CI integration is enabled.

---

# Current Automated Test Count

Current logical Cypress coverage:

| Suite                           |   Tests |
| ------------------------------- | ------: |
| Home / Smoke                    |       2 |
| Authentication UI               |       7 |
| Products UI                     |      12 |
| Cart UI                         |      10 |
| Coupons UI                      |       9 |
| Checkout UI                     |      13 |
| Registration UI                 |      16 |
| Account UI                      |       8 |
| Contact UI                      |      13 |
| QA Lab UI                       |      13 |
| Core API                        |      13 |
| Products API                    |      10 |
| Search API                      |       8 |
| Coupons API                     |      11 |
| Orders API                      |      24 |
| Known Defects                   |       4 |
| **Total logical Cypress tests** | **173** |

Normal expected-green coverage:

```text
UI functional tests: 103
API tests:            66
--------------------------------
Normal suite:        169
```

Known confirmed defect tests:

```text
4
```

Total logical Cypress coverage:

```text
173
```

The Cypress execution output is the authoritative source if tests are added or removed later.

Cross-browser runs may execute the same logical test multiple times.

---

# Test Design Approach

Coverage is designed using multiple techniques rather than only happy-path testing.

The suite includes:

### Positive testing

Examples:

```text
Valid login
Valid checkout
Valid coupon
Valid registration
Valid API order
```

### Negative testing

Examples:

```text
Invalid credentials
Malformed email
Invalid card
Unknown coupon
Out-of-stock product
```

### Boundary-value testing

Examples:

```text
Minimum password length
Maximum field lengths
Message length 19 vs 20
MIN100 subtotal 99.99 vs 100.00
Quantity 0 vs 1
```

### Equivalence partitioning

Examples:

```text
Valid vs invalid email
Valid vs invalid coupon
In-stock vs out-of-stock product
Valid vs invalid card number
```

### Business-rule testing

Examples:

```text
Stock limits
Coupon minimums
Shipping thresholds
Card decline simulation
Cart persistence
Special purchase limits
```

### Data-quality testing

Examples:

```text
Unicode product names
Decimal prices
Price rounding
Search normalization
Case-insensitive matching
Whitespace trimming
```

---

# Naming and Organization

Test files use the pattern:

```text
<feature>.cy.ts
```

Examples:

```text
login.cy.ts
cart.cy.ts
orders-api.cy.ts
```

Test descriptions are written as readable behavioral expectations.

Example:

```typescript
it("rejects a quantity greater than available stock", () => {
  // ...
});
```

Known defects include their defect IDs directly in the test name.

Example:

```typescript
it("BUG-API-ORD-001 - duplicate product lines must use aggregated stock validation", () => {
  // ...
});
```

This makes automated failures easier to correlate with defect documentation.

---

# Failure Investigation

When an automated test fails:

1. Read the assertion failure.
2. Confirm whether the HTTP status or UI behavior is unexpected.
3. Review screenshots.
4. Review execution video if available.
5. Review the Mochawesome report.
6. Re-run the individual spec.
7. Check whether the failure is reproducible manually.
8. Verify test data and environment state.
9. Confirm the expected result against the documented requirement.
10. Only change the test if the test expectation is incorrect.

Do not automatically modify expected results simply because the current application behaves differently.

A mismatch may represent a genuine defect.

---

# Troubleshooting

## TypeScript Errors

Run:

```powershell
npm run typecheck
```

Resolve TypeScript errors before running CI.

---

## Cypress Does Not Find a Spec

Verify the file is under:

```text
cypress/e2e/
```

and uses:

```text
.cy.ts
```

Example:

```text
orders-api.cy.ts
```

---

## API Negative Tests Fail Immediately

Expected non-success responses require:

```typescript
failOnStatusCode: false;
```

Example:

```typescript
cy.request({
  method: "POST",
  url: "/api/orders",
  body: requestBody,
  failOnStatusCode: false,
});
```

---

## Report File Does Not Exist

First check raw Mochawesome output:

```powershell
Get-ChildItem ..\reports\cypress\mochawesome
```

Then merge:

```powershell
npm run cy:report:merge
```

Confirm:

```powershell
Test-Path ..\reports\cypress\mochawesome.json
```

Generate HTML:

```powershell
npm run cy:report:html
```

Confirm:

```powershell
Test-Path ..\reports\cypress\html\mochawesome.html
```

Then open:

```powershell
Start-Process (
  Resolve-Path "..\reports\cypress\html\mochawesome.html"
)
```

---

## Known Defect Tests Fail

This may be expected.

Run:

```powershell
npm run cy:defects
```

The known-defect suite asserts correct requirements against currently defective behavior.

A failure therefore provides evidence that the defect remains reproducible.

---

## All Tests Run When Only Green Tests Were Intended

Do not use:

```powershell
npm run cy:run
```

for the normal CI-style validation if known defects are still open.

Instead use:

```powershell
npm run cy:ci
```

---

## Authentication State Is Unexpected

The framework uses:

```typescript
cy.session();
```

alongside test-state cleanup.

When debugging authentication problems, verify:

- Seed credentials
- `/login`
- `/account`
- Session validation
- Cookies
- `localStorage`
- `sessionStorage`

---

# Generated Artifacts

Cypress execution can generate:

```text
reports/cypress/screenshots/
reports/cypress/videos/
reports/cypress/downloads/
reports/cypress/mochawesome/
reports/cypress/mochawesome.json
reports/cypress/html/
```

These are generated test artifacts rather than framework source code.

They are intended to provide:

- Failure evidence
- Execution evidence
- Debugging information
- Portfolio reporting

Generated artifacts can be excluded from normal source tracking where appropriate and produced again through test execution.

---

# Quality Principles Used in the Framework

The Cypress implementation follows several practical QA automation principles.

### Tests should be deterministic

Tests should produce the same result when the application state and requirements are unchanged.

### Tests should be isolated

A test should not depend on another test executing first.

### Tests should validate business behavior

Automation should verify user and API requirements, not only DOM implementation details.

### Known bugs should not redefine expected behavior

If the application is wrong, the test expectation should remain aligned with the requirement.

### Stable selectors should be preferred

`data-testid`, semantic locators, and stable IDs are preferred over fragile CSS hierarchy.

### API tests should avoid unnecessary browser dependency

REST endpoints are validated directly with `cy.request()` when UI interaction is not required.

### Failure evidence should be preserved

Screenshots, videos, reports, and defect-linked tests help diagnose regressions.

### CI should provide actionable feedback

Expected known failures are separated from the primary regression pipeline.

---

# Portfolio Context

This Cypress framework is part of the larger:

```text
QA Commerce Lab QA Portfolio
```

The complete portfolio demonstrates:

- Requirements analysis
- Test planning
- Test scenario design
- Detailed test-case design
- Traceability
- Manual testing
- Smoke testing
- Regression testing
- Exploratory testing
- Edge-case testing
- Defect reporting
- Test evidence
- Selenium automation
- Playwright automation
- Cypress automation
- REST API automation
- Postman
- Newman
- CI/CD
- GitHub Actions
- Automated reporting
- Cross-browser testing
- Known-defect management

The project is designed to demonstrate an end-to-end Software Quality Engineering workflow rather than only isolated automated test scripts.

---

# Quick Start

Clone the repository and enter the Cypress directory.

```powershell
cd cypress
```

Install dependencies:

```powershell
npm install
```

Validate TypeScript:

```powershell
npm run typecheck
```

Run the normal Cypress validation:

```powershell
npm run cy:ci
```

Generate the report:

```powershell
npm run cy:report
```

Open the report:

```powershell
Start-Process (
  Resolve-Path "..\reports\cypress\html\mochawesome.html"
)
```

Run known defects separately:

```powershell
npm run cy:defects
```

---

# Execution Summary

Normal validation:

```text
npm run typecheck
        ↓
npm run cy:functional
        ↓
npm run cy:api
        ↓
169 logical tests expected green
```

Known defect verification:

```text
npm run cy:defects
        ↓
4 confirmed defect tests
        ↓
Failures expected while defects remain open
```

Reporting:

```text
npm run cy:report
        ↓
reports/cypress/html/mochawesome.html
```

---

## QA Commerce Lab

**Cypress + TypeScript UI and API automation framework built as part of a complete Software Quality Engineering portfolio.**
