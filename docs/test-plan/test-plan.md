# QA Commerce Lab — Test Plan

## 1. Document Information

**Project:** QA Commerce Lab QA Portfolio
**Application Under Test:** QA Commerce Lab
**Application URL:** https://qa-commerce-lab.vercel.app
**Document Type:** Software Test Plan
**Version:** 1.0
**Status:** Baseline
**Test Environment:** Public QA Environment

---

## 2. Purpose

This document defines the overall testing strategy, scope, objectives, resources, environments, test types, entry and exit criteria, defect management approach, automation strategy, risks, deliverables, and completion criteria for QA Commerce Lab.

The purpose of this project is to validate the functionality, reliability, usability, compatibility, and API behavior of the application while also demonstrating a complete professional QA workflow.

The project includes both manual and automated testing.

Automation will be implemented using:

* Selenium WebDriver with Java
* Playwright with TypeScript
* Cypress with TypeScript
* Postman
* Newman
* Cypress API testing
* GitHub Actions

---

## 3. Application Overview

QA Commerce Lab is a purpose-built e-commerce application designed for software quality assurance practice.

The application contains realistic customer workflows together with deterministic edge cases intended for manual testing, UI automation, API testing, negative testing, boundary-value testing, and synchronization testing.

Major application areas include:

* Navigation
* User registration
* Authentication
* Account management
* Product catalog
* Product search
* Product filtering
* Product sorting
* Shopping cart
* Coupons
* Checkout
* Payment processing simulation
* Contact form
* REST APIs
* QA Lab edge-case scenarios

---

## 4. Test Objectives

The primary objectives of testing are to:

1. Verify that all documented functional requirements behave as expected.
2. Identify functional defects before automated coverage is implemented.
3. Validate positive and negative user workflows.
4. Validate input boundaries and invalid values.
5. Verify product search, filtering, sorting, and inventory behavior.
6. Verify shopping cart calculations and state management.
7. Verify coupon and shipping calculations.
8. Verify checkout validation and payment simulations.
9. Verify account lifecycle behavior.
10. Validate REST API status codes, response bodies, headers, and validation rules.
11. Validate application behavior under controlled error conditions.
12. Verify application behavior across supported browsers.
13. Build stable automated regression coverage.
14. Integrate automated tests into CI/CD.
15. Maintain traceability between requirements, scenarios, test cases, automation, and defects.

---

## 5. Scope

### 5.1 In Scope

The following areas are included in testing.

#### Navigation

* Home navigation
* Product navigation
* QA Lab navigation
* API documentation navigation
* Contact navigation
* Account navigation
* Cart navigation
* Browser Back and Forward behavior
* Unknown route handling

#### Authentication

* Valid login
* Invalid login
* Empty credentials
* Invalid email format
* Email normalization
* Password case sensitivity
* Session persistence
* Logout

#### Registration

* Valid account creation
* Required fields
* Name boundaries
* Email validation
* Duplicate email handling
* Password complexity
* Password confirmation
* Terms acceptance
* Automatic authentication after registration

#### Products

* Product display
* Product name
* Product category
* Product price
* Currency formatting
* Price rounding
* Stock display
* Search
* Search normalization
* Category filtering
* Price sorting
* Empty search results
* Unicode product names
* Long product names
* Zero-price products
* High-price products
* Out-of-stock products

#### Shopping Cart

* Add to cart
* Multiple product quantities
* Existing product quantity increase
* Quantity modification
* Quantity boundaries
* Inventory limits
* Product removal
* Empty cart
* Cart subtotal
* Cart persistence
* Cart indicator

#### Coupons

* Valid coupons
* Invalid coupons
* Expired coupon
* Coupon case handling
* Coupon whitespace handling
* Minimum-order coupon
* Percentage discount
* Fixed discount
* Free shipping
* Automatic free-shipping threshold

#### Checkout

* Authentication requirement
* Empty-cart checkout protection
* Pre-populated user information
* Shipping validation
* Address validation
* Email validation
* Cardholder validation
* Card number validation
* Luhn validation
* Expiry validation
* CVV validation
* Payment success
* Payment decline
* Insufficient funds
* Duplicate submission prevention
* Order confirmation
* Cart clearing after purchase

#### Account

* Account information display
* Logout
* Account deletion
* Delete confirmation
* Invalid delete confirmation
* Preservation of seeded QA account

#### Contact Form

* Name validation
* Email validation
* Subject selection
* Message boundaries
* Attachment validation
* Attachment size validation
* Successful submission

#### QA Lab

* Delayed API response
* HTTP 204
* HTTP 400
* HTTP 401
* HTTP 404
* HTTP 409
* HTTP 422
* HTTP 429
* HTTP 500
* Retry-After header
* Large API response
* Delayed DOM rendering
* Modal interactions

#### REST API

* Health endpoint
* Product collection
* Single product
* Product filtering
* Product sorting
* Pagination
* Product search
* Authentication
* User creation
* User update
* User deletion
* Coupon validation
* Order creation
* Review creation
* Contact request
* Edge-case endpoints
* Echo endpoint
* Unsupported methods
* Unknown routes
* CORS
* Response headers
* Error responses

---

## 5.2 Out of Scope

The following are not part of the initial test scope:

* Real payment gateway integration
* Real banking transactions
* Real email delivery
* SMS delivery
* Production database validation
* Third-party identity providers
* Real shipping-provider integration
* Full penetration testing
* Distributed load testing
* Stress testing at production scale
* Native mobile applications
* Browser versions outside the supported test matrix

These areas may be added in future project phases if required.

---

## 6. Requirements Source

The primary requirements baseline is maintained in:

`docs/requirements/requirements-catalog.md`

All test scenarios and test cases should reference one or more requirements from this document.

Example:

`REQ-AUTH-001`

may map to:

`SCN-AUTH-001`

and later:

`TC-AUTH-001`

---

## 7. Testing Types

### 7.1 Functional Testing

Verify that application functionality behaves according to documented requirements.

Examples:

* Login
* Registration
* Search
* Cart
* Checkout

---

### 7.2 Positive Testing

Validate expected behavior using valid user input and supported workflows.

Example:

Valid login credentials should authenticate the user.

---

### 7.3 Negative Testing

Validate behavior when invalid, incomplete, or unsupported input is provided.

Examples:

* Incorrect password
* Invalid email
* Invalid coupon
* Invalid payment details

---

### 7.4 Boundary Value Analysis

Validate values immediately below, at, and immediately above documented boundaries.

Examples:

Password:

* 7 characters
* 8 characters
* 64 characters
* 65 characters

Message:

* 19 characters
* 20 characters
* 1000 characters
* 1001 characters

---

### 7.5 Equivalence Partitioning

Input domains will be divided into valid and invalid partitions.

Example:

Card quantity:

Valid:

* Positive whole numbers within available stock

Invalid:

* Zero
* Negative numbers
* Decimal values
* Values above stock

---

### 7.6 Exploratory Testing

Unscripted investigation will be conducted to identify:

* Unexpected workflows
* UI inconsistencies
* State-management problems
* Browser behavior
* Validation gaps
* Integration issues

Exploratory findings will be documented separately.

---

### 7.7 Smoke Testing

A small critical-path suite will verify that the application is stable enough for deeper testing.

The smoke suite will include major workflows such as:

* Application loads
* Login
* Product catalog loads
* Product can be added to cart
* Cart loads
* Checkout can be reached
* Successful purchase
* API health endpoint responds

---

### 7.8 Regression Testing

Regression testing will validate previously working features after project changes.

The regression suite will eventually include all stable automatable critical and high-priority workflows.

---

### 7.9 Integration Testing

Integration between application areas will be validated.

Examples:

* Login → Checkout
* Product → Cart
* Cart → Coupon
* Cart → Checkout
* Registration → Account
* Order → Cart clearing

---

### 7.10 API Testing

REST API testing will validate:

* HTTP methods
* Status codes
* Request payloads
* Response payloads
* Response headers
* Validation
* Pagination
* Filtering
* Sorting
* Error responses
* Authentication
* Business logic

API tests will be implemented using Postman/Newman and Cypress.

---

### 7.11 Cross-Browser Testing

Critical workflows will be executed across supported browsers.

Supported browsers:

* Google Chrome
* Mozilla Firefox
* Microsoft Edge

Playwright testing will additionally cover:

* Chromium
* Firefox
* WebKit

---

### 7.12 Responsive Testing

The application will be reviewed at multiple viewport sizes.

Target categories:

* Desktop
* Tablet
* Mobile

Testing will focus on:

* Navigation
* Layout
* Product cards
* Forms
* Cart
* Checkout
* QA Lab

---

### 7.13 Usability Testing

Basic usability checks will include:

* Form clarity
* Error-message clarity
* Navigation consistency
* Button labeling
* Feedback after user actions
* Empty-state usability

---

### 7.14 Basic Security-Oriented Validation

The project will include safe validation checks such as:

* HTML-like input
* JavaScript-like input
* SQL-like strings
* Special characters
* Very long input
* Unexpected data types

The purpose is to verify safe application handling and validation behavior.

This project does not include offensive security testing or penetration testing.

---

## 8. Test Levels

Testing will cover:

### UI Level

Manual and automated browser workflows.

### API Level

Direct REST API validation independent of the user interface.

### End-to-End Level

Complete user journeys spanning several components.

Example:

Login → Search → Add Product → Apply Coupon → Checkout → Payment → Confirmation

---

## 9. Test Environment

### Application

**Environment:** Public QA environment

**Base URL:**

https://qa-commerce-lab.vercel.app

### API Base URL

https://qa-commerce-lab.vercel.app/api

### Seed Test Account

Email:

`qa.user@example.com`

Password:

`Password123!`

The seeded account must remain reusable for future test execution.

---

## 10. Supported Platforms

Primary manual testing platform:

* Windows 11

Primary automation environment:

* Windows 11 local execution
* Ubuntu GitHub Actions runners

---

## 11. Browser Matrix

| Browser | Manual | Selenium |     Playwright |  Cypress |
| ------- | -----: | -------: | -------------: | -------: |
| Chrome  |    Yes |      Yes |       Chromium |      Yes |
| Edge    |    Yes |      Yes | Chromium-based | Optional |
| Firefox |    Yes |      Yes |            Yes |      Yes |
| WebKit  |     No |       No |            Yes |       No |

The final automation matrix may be adjusted according to framework support and execution cost.

---

## 12. Test Data Strategy

Testing will use deterministic and reusable test data where possible.

Test data categories include:

### Authentication

* Seeded valid user
* Invalid email
* Invalid password
* Dynamically created users

### Products

The catalog intentionally contains test-focused product data including:

* Zero-price product
* Low-price product
* High-price product
* Three-decimal price
* Out-of-stock product
* Single-stock product
* High-stock product
* Unicode product name
* Long product name

### Coupons

Valid:

* SAVE10
* FREESHIP
* MIN100

Controlled invalid:

* EXPIRED

### Payment Cards

Successful:

`4242424242424242`

Declined:

`4000000000000002`

Insufficient funds:

`4000000000009995`

Test data used by automation should be centralized rather than duplicated throughout test files.

---

## 13. Test Case Priorities

### Critical

Failure blocks a major customer workflow or causes severe business impact.

Examples:

* Login
* Cart
* Checkout
* Payment
* Order creation

### High

Important functionality with substantial impact.

Examples:

* Validation
* Search
* Coupons
* Account management

### Medium

Secondary or supporting behavior.

Examples:

* Layout edge cases
* Some usability behavior
* Additional sorting behavior

### Low

Minor cosmetic or low-impact behavior.

Low-priority cases may be deferred if project time is limited.

---

## 14. Defect Severity

### Severity 1 — Critical

Application or major workflow is unusable.

Examples:

* Application unavailable
* Checkout completely broken
* Successful payment impossible

### Severity 2 — High

Major functionality is broken but the application remains usable.

Examples:

* Login fails for valid users
* Cart total is incorrect
* Coupon calculation is incorrect

### Severity 3 — Medium

Functional problem with limited impact or available workaround.

Examples:

* Search normalization failure
* Incorrect secondary validation message

### Severity 4 — Low

Minor issue with little functional impact.

Examples:

* Cosmetic layout defect
* Small text inconsistency

---

## 15. Defect Priority

Priority indicates how quickly a defect should be addressed.

### P1 — Immediate

Must be corrected before testing can reasonably continue or before release.

### P2 — High

Should be fixed before release.

### P3 — Medium

Should be addressed if time permits before release.

### P4 — Low

Can be deferred to a later release.

Severity and priority are independent.

For example, a visually obvious typo on the home page may have:

* Severity: Low
* Priority: High

while an obscure functional defect may have:

* Severity: High
* Priority: Medium

depending on business impact and frequency.

---

## 16. Test Case Statuses

The following statuses will be used:

* Not Run
* Pass
* Fail
* Blocked
* Skipped
* Not Applicable

---

## 17. Automation Statuses

The following statuses will be used:

* Manual Only
* Planned
* Automated
* Not Automatable
* Deferred

---

## 18. Automation Strategy

Automation will be introduced after manual test scenarios and detailed test cases are defined.

The same core business flows will be implemented across multiple automation frameworks for portfolio demonstration.

### Selenium

Technology:

* Java 21
* Selenium WebDriver
* TestNG
* Maven
* Page Object Model
* Allure

Focus:

* UI functional automation
* Cross-browser testing
* Data-driven testing
* Explicit waits
* Parallel execution
* Failure screenshots

---

### Playwright

Technology:

* TypeScript
* Playwright Test
* Page Objects
* Fixtures

Focus:

* UI automation
* Browser isolation
* Parallel execution
* Chromium
* Firefox
* WebKit
* Screenshots
* Videos
* Tracing

---

### Cypress

Technology:

* TypeScript
* Cypress

Focus:

* UI automation
* API automation
* Network interception
* Fixtures
* Custom commands

---

### Postman

Focus:

* REST API functional testing
* Environment variables
* Request chaining
* Assertions
* Negative API testing

---

### Newman

Newman will execute Postman collections from the command line and CI environment.

---

## 19. Automation Selection Criteria

A test is a good automation candidate when it is:

* Repeatable
* Deterministic
* Frequently executed
* Part of smoke or regression testing
* Business critical
* Data-driven
* Stable enough to maintain

Tests may remain manual when they require:

* Subjective visual judgment
* Exploratory investigation
* Human usability judgment
* Unstable external behavior
* One-time validation

The objective is not to automate every possible test simply because automation is technically possible.

---

## 20. Locator Strategy

Automation should prefer stable selectors.

Preferred order:

1. `data-testid`
2. Accessible role/name
3. Stable ID
4. Stable CSS selector
5. XPath only when necessary

Tests should avoid fragile selectors based on:

* DOM position
* Deep CSS hierarchy
* Styling classes
* Generated values

---

## 21. Synchronization Strategy

Automation must avoid unnecessary fixed delays.

Examples of discouraged practices:

`Thread.sleep()`

or arbitrary Cypress waits such as:

`cy.wait(5000)`

Instead, tests should wait for meaningful conditions such as:

* Element visibility
* Element enabled state
* URL state
* Network response
* API completion
* DOM state

QA Lab provides controlled delayed responses specifically for synchronization testing.

---

## 22. API Automation Strategy

API automation will validate:

* Successful requests
* Invalid requests
* Missing fields
* Invalid types
* Boundary values
* Authentication
* Status codes
* Response schemas
* Response headers
* Error bodies
* Response times where appropriate
* Pagination
* Sorting
* Filtering
* CRUD-style workflows

Postman/Newman and Cypress will provide separate implementations.

---

## 23. CI/CD Strategy

GitHub Actions will eventually execute automation automatically.

Planned workflows include:

* Selenium
* Playwright
* Cypress
* Postman/Newman

Automation may execute on:

* Pull requests
* Pushes to main
* Manual workflow dispatch

The CI pipeline should fail when critical automated tests fail.

---

## 24. Reporting Strategy

### Manual Testing

Results will be documented in:

* Test-case files
* Bug reports
* Test summary report

### Selenium

* TestNG output
* Allure
* Failure screenshots

### Playwright

* HTML report
* Screenshots
* Videos where appropriate
* Traces on failure

### Cypress

* Test reports
* Screenshots
* Videos where configured

### Postman

* Newman CLI reports

---

## 25. Entry Criteria

Formal testing may begin when:

* QA environment is accessible.
* Requirements baseline exists.
* Major application functionality is deployed.
* Required test accounts are available.
* Required browsers are installed.
* Test data is available.
* Test environment is sufficiently stable.

---

## 26. Exit Criteria

Testing may be considered complete when:

* 100% of Critical test cases have been executed.
* 100% of High-priority test cases have been executed.
* Smoke testing passes.
* Regression testing is complete.
* No unresolved Severity 1 defects remain.
* No unacceptable Severity 2 defects remain.
* Required automated suites execute successfully.
* API regression testing is complete.
* Requirements traceability has been reviewed.
* Test Summary Report has been completed.

---

## 27. Suspension Criteria

Testing may be suspended when:

* QA environment is unavailable.
* A blocking defect prevents execution of multiple critical workflows.
* Required services are unavailable.
* Application instability makes results unreliable.
* Test data cannot be prepared or restored.

---

## 28. Resumption Criteria

Testing may resume when:

* Blocking environment issues are resolved.
* Critical defects preventing execution are corrected.
* Application stability has been restored.
* Required test data is available.
* A new deployable build is available.

Relevant smoke tests should be executed before full testing resumes.

---

## 29. Risks

### Environment Availability

**Risk:** Public QA environment becomes unavailable.

**Mitigation:** Confirm application health before automated test execution.

---

### Shared Test State

**Risk:** Tests may interfere with one another through shared browser state or test data.

**Mitigation:**

* Reset browser state where appropriate.
* Use independent users where possible.
* Avoid test-order dependencies.
* Use API setup and cleanup where practical.

---

### Automation Flakiness

**Risk:** Timing, asynchronous UI behavior, or unstable selectors may cause false failures.

**Mitigation:**

* Use deterministic selectors.
* Use condition-based waits.
* Avoid arbitrary sleeps.
* Keep tests independent.
* Analyze flaky tests rather than blindly retrying them.

---

### Duplicate Automation Maintenance

**Risk:** Implementing similar tests across Selenium, Playwright, and Cypress increases maintenance effort.

**Mitigation:**

The duplication is intentional for portfolio comparison. Shared business coverage and test IDs will be maintained through the traceability matrix.

---

### Browser Differences

**Risk:** Application behavior may differ across browser engines.

**Mitigation:**

Execute critical workflows across multiple browsers.

---

### Test Data Pollution

**Risk:** Repeated account creation and test execution may create inconsistent state.

**Mitigation:**

Use deterministic seed data, unique user values, and cleanup processes.

---

## 30. Test Deliverables

The project will produce:

* Requirements Catalog
* Test Plan
* Test Scenario Catalog
* Detailed Test Cases
* Smoke Test Suite
* Regression Test Suite
* Exploratory Testing Notes
* Edge-Case Catalog
* Bug Reports
* Requirements Traceability Matrix
* API Test Cases
* Postman Collection
* Newman Execution
* Selenium Framework
* Playwright Framework
* Cypress Framework
* Cypress API Tests
* Test Reports
* GitHub Actions Workflows
* Test Summary Report
* Final Project README

---

## 31. Test Repository Structure

Relevant project areas include:

```text
docs/
├── requirements/
├── test-plan/
├── test-scenarios/
├── test-cases/
├── traceability/
└── test-reports/

manual-testing/
├── smoke/
├── regression/
├── exploratory/
├── edge-cases/
├── bug-reports/
└── evidence/

api-testing/
├── documentation/
├── test-cases/
└── postman/

selenium/

playwright/

cypress/

test-data/

reports/

.github/
└── workflows/
```

---

## 32. Traceability Strategy

Every detailed test case should reference a requirement ID.

Example:

Requirement:

`REQ-AUTH-001`

Scenario:

`SCN-AUTH-001`

Test case:

`TC-AUTH-001`

Automation implementations may use the same test-case identifier.

Example:

```text
TC-AUTH-001

Manual       PASS
Selenium     Automated
Playwright   Automated
Cypress      Automated
```

This approach will allow the project to demonstrate measurable requirements coverage.

---

## 33. Test Completion

The project is considered complete when:

* Requirements are documented.
* Test scenarios cover all documented requirements.
* Detailed test cases cover positive, negative, boundary, and relevant edge conditions.
* Critical functionality has automated regression coverage.
* REST API functionality is automated.
* CI/CD execution is configured.
* Defects and exploratory findings are documented.
* Requirements traceability is complete.
* The final test summary is published.
* The GitHub repository clearly communicates project scope, design, execution, results, and tooling.
