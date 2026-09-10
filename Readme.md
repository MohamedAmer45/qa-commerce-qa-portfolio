# QA Commerce Lab — Software Quality Engineering Portfolio

[![Selenium Tests](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/selenium.yml/badge.svg)](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/selenium.yml)
[![Playwright Tests](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/playwright.yml/badge.svg)](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/playwright.yml)
[![Cypress Tests](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/cypress.yml/badge.svg)](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/cypress.yml)
[![Postman Tests](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/postman.yml/badge.svg)](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/actions/workflows/postman.yml)
[![Release](https://img.shields.io/github/v/release/MohamedAmer45/qa-commerce-qa-portfolio)](https://github.com/MohamedAmer45/qa-commerce-qa-portfolio/releases)
[![License](https://img.shields.io/badge/license-Portfolio-blue)](#)

A comprehensive **Software Quality Engineering portfolio** demonstrating the complete QA lifecycle against a purpose-built e-commerce application.

### Live Application

**QA Commerce Lab:**  
https://qa-commerce-lab.vercel.app

### What This Project Demonstrates

- 400+ documented UI and REST API test cases
- Manual, smoke, regression, exploratory and edge-case testing
- Selenium + Java + TestNG + Maven + Allure
- Playwright + TypeScript
- Cypress + TypeScript + Mochawesome
- Postman + Newman REST API automation
- UI and backend API automation
- Chrome, Firefox, Edge and WebKit coverage
- GitHub Actions CI/CD
- Automated screenshots, videos, traces and reports
- Requirements-to-test traceability
- Real defect reports with reproducible automated known-defect tests

### Automation at a Glance

| Framework | Primary Purpose | Coverage |
|---|---|---:|
| Selenium | Cross-browser UI automation | 100+ tests |
| Playwright | Modern UI and network automation | 100+ logical tests |
| Cypress | UI + REST API automation | 173 logical tests |
| Postman / Newman | Comprehensive REST API automation | Extensive API suite |
| Manual QA | Functional, regression, exploratory and edge cases | 400+ documented cases |

### Confirmed Defects

The project currently includes four documented and automated defects:

- `BUG-UI-CART-001` — Sticker Pack quantity limit bypass
- `BUG-UI-CHK-001` — Cart counter remains stale after checkout
- `BUG-UI-CHK-002` — Impossible card expiry month accepted
- `BUG-API-ORD-001` — Duplicate order lines bypass aggregated stock validation

Known-defect tests intentionally continue asserting the **correct requirement** rather than accepting defective application behavior.
---

## Table of Contents

- [Project Overview](#project-overview)
- [Application Under Test](#application-under-test)
- [Portfolio Objectives](#portfolio-objectives)
- [Technology Stack](#technology-stack)
- [Repository Structure](#repository-structure)
- [Testing Architecture](#testing-architecture)
- [Manual QA Coverage](#manual-qa-coverage)
- [API Testing](#api-testing)
- [Selenium Automation](#selenium-automation)
- [Playwright Automation](#playwright-automation)
- [Cypress Automation](#cypress-automation)
- [Postman and Newman](#postman-and-newman)
- [CI/CD](#cicd)
- [Known Defect Strategy](#known-defect-strategy)
- [Confirmed Defects](#confirmed-defects)
- [Reporting and Evidence](#reporting-and-evidence)
- [Test Design Techniques](#test-design-techniques)
- [Cross-Browser Testing](#cross-browser-testing)
- [Quality Engineering Principles](#quality-engineering-principles)
- [Quick Start](#quick-start)
- [Framework Commands](#framework-commands)
- [Portfolio Highlights](#portfolio-highlights)

---

# Project Overview

**QA Commerce Lab** is an e-commerce application created specifically to demonstrate practical Software Quality Engineering skills.

Instead of testing only simple happy paths, the application includes:

- Authentication rules
- Registration validation
- Search and filtering
- Product stock states
- Cart persistence
- Quantity boundaries
- Coupon rules
- Shipping calculations
- Checkout validation
- Payment simulations
- Account lifecycle behavior
- File uploads
- Unicode content
- Decimal monetary values
- Dynamic DOM behavior
- Slow API responses
- Controlled HTTP failures
- Rate limiting
- Large responses
- Known UI and API defects

The repository combines documentation, manual testing, automated testing, evidence, reporting, and CI into a single QA portfolio.

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

Main application routes:

```text
/
├── /products
├── /login
├── /register
├── /cart
├── /checkout
├── /account
├── /contact
├── /qa-lab
└── /api-docs
```

---

# Portfolio Objectives

This repository demonstrates the ability to take a product from requirements through a complete QA workflow.

The project covers:

### Requirements

- Requirement identification
- Functional requirements
- Validation rules
- Business rules
- API behavior
- Boundary conditions
- Expected error handling

### Test Design

- Test plans
- Test scenarios
- Detailed test cases
- Positive testing
- Negative testing
- Boundary-value analysis
- Equivalence partitioning
- Edge-case design
- Risk-based prioritization

### Test Execution

- Smoke testing
- Regression testing
- Exploratory testing
- UI testing
- API testing
- Cross-browser testing

### Defect Management

- Reproduction steps
- Expected result
- Actual result
- Severity
- Priority
- Evidence
- Known-defect automation

### Automation

- Selenium
- Playwright
- Cypress
- Postman/Newman
- UI automation
- REST API automation
- Network testing
- CI execution

### Reporting

- Allure
- Playwright HTML reports
- Cypress Mochawesome reports
- Newman HTML/JSON reports
- Failure screenshots
- Execution videos
- GitHub Actions artifacts

---

# Technology Stack

## Manual and API Testing

- Manual Testing
- Exploratory Testing
- Regression Testing
- Smoke Testing
- REST API Testing
- Postman
- Newman

## Selenium

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model
- Allure
- Selenium Manager

## Playwright

- TypeScript
- Playwright Test
- Chromium
- Firefox
- WebKit
- HTML reporting
- Trace Viewer
- Network interception

## Cypress

- TypeScript
- Cypress
- `cy.request()`
- `cy.intercept()`
- `cy.session()`
- Mochawesome
- Chrome
- Microsoft Edge

## DevOps

- Git
- GitHub
- GitHub Actions
- CI/CD
- npm
- Maven

---

# Repository Structure

```text
qa-commerce-qa-portfolio/
│
├── README.md
├── .gitignore
├── .gitattributes
│
├── docs/
│   ├── requirements/
│   ├── test-plan/
│   ├── test-scenarios/
│   ├── test-cases/
│   ├── traceability/
│   └── test-reports/
│
├── manual-testing/
│   ├── smoke/
│   ├── regression/
│   ├── exploratory/
│   ├── edge-cases/
│   ├── bug-reports/
│   └── evidence/
│
├── api-testing/
│   ├── documentation/
│   ├── test-cases/
│   └── postman/
│
├── selenium/
│   ├── README.md
│   ├── pom.xml
│   ├── testng.xml
│   └── src/
│
├── playwright/
│   ├── README.md
│   ├── package.json
│   ├── playwright.config.ts
│   └── tests/
│
├── cypress/
│   ├── README.md
│   ├── package.json
│   ├── cypress.config.ts
│   ├── tsconfig.json
│   └── cypress/
│
├── test-data/
│
├── reports/
│   ├── selenium/
│   ├── playwright/
│   ├── cypress/
│   └── postman/
│
└── .github/
    └── workflows/
        ├── selenium.yml
        ├── playwright.yml
        ├── postman.yml
        └── cypress.yml
```

---

# Testing Architecture

The portfolio validates the same product through several independent layers.

```text
                  QA Commerce Lab
                         │
        ┌────────────────┼────────────────┐
        │                │                │
        ▼                ▼                ▼
   Manual QA         UI Automation     REST APIs
        │                │                │
        │        ┌───────┼────────┐       │
        │        ▼       ▼        ▼       │
        │    Selenium Playwright Cypress  │
        │                                 │
        └────────────────┬────────────────┘
                         ▼
                  API Automation
                 ┌───────┴───────┐
                 ▼               ▼
              Postman          Cypress
              Newman          cy.request()
                 │               │
                 └───────┬───────┘
                         ▼
                    GitHub Actions
                         │
                         ▼
                  Reports / Evidence
```

This avoids depending on a single testing tool and demonstrates different automation strategies for different testing layers.

---

# Manual QA Coverage

The manual testing portion contains structured coverage across the complete application.

Documentation includes:

- Requirements
- Test plan
- Test scenarios
- Detailed UI test cases
- Detailed API test cases
- Traceability
- Smoke testing
- Regression testing
- Exploratory testing
- Edge cases
- Defect reports
- Execution evidence

The project contains **400+ documented UI and API test cases**.

Core coverage includes:

| Feature        | Coverage                                          |
| -------------- | ------------------------------------------------- |
| Navigation     | Routing and global navigation                     |
| Authentication | Login, validation, normalization                  |
| Registration   | Required fields, password complexity, boundaries  |
| Products       | Catalog, search, filtering, sorting               |
| Cart           | Quantity, stock, totals, persistence              |
| Coupons        | Discounts, thresholds, invalid coupons            |
| Checkout       | Shipping, cards, payment outcomes                 |
| Account        | Authentication, logout, deletion                  |
| Contact        | Validation, Unicode, file uploads                 |
| QA Lab         | Controlled failures and dynamic behavior          |
| REST API       | Positive, negative, validation and business rules |

---

# API Testing

REST API testing covers:

- Health
- Products
- Product lookup
- Search
- Authentication
- Users
- Coupons
- Orders
- Reviews
- Contact
- Edge cases
- Echo

Testing validates:

- HTTP methods
- Status codes
- Headers
- JSON bodies
- Validation
- Authentication
- Pagination
- Filtering
- Sorting
- Search normalization
- Business rules
- Product availability
- Payment simulation
- Monetary calculations
- Error handling
- Boundary values
- CORS behavior
- Rate limiting

---

# Selenium Automation

Directory:

```text
selenium/
```

Framework documentation:

```text
selenium/README.md
```

Technology:

```text
Java 21
Selenium WebDriver
TestNG
Maven
Page Object Model
Allure
```

Main automated areas:

- Authentication
- Registration
- Products
- Cart
- Coupons
- Checkout
- Account
- Contact
- QA Lab

Approximate logical automated coverage:

```text
103 Selenium tests
```

Supported browsers:

```text
Chrome
Firefox
Microsoft Edge
```

Run functional Selenium tests:

```powershell
cd selenium

mvn clean test `
  -DexcludedGroups=known-defect `
  -Dheadless=true `
  -Dbrowser=chrome
```

Run known defects:

```powershell
mvn test `
  -Dgroups=known-defect `
  -Dheadless=true `
  -Dbrowser=chrome
```

Allure results are generated for execution analysis and failure evidence.

---

# Playwright Automation

Directory:

```text
playwright/
```

Technology:

```text
TypeScript
Playwright Test
Chromium
Firefox
WebKit
```

The Playwright framework demonstrates:

- UI automation
- Page Object Model
- Fixtures
- Authentication state
- Network interception
- Request/response validation
- Automatic waiting
- Parallel execution
- Cross-browser testing
- Known-defect handling
- Screenshots
- Video
- Trace capture
- HTML reporting

Coverage includes:

- Smoke
- Authentication
- Products
- Cart
- Coupons
- Checkout
- Account
- Contact
- QA Lab
- Network behavior
- Known defects

The framework contains approximately:

```text
100+ logical Playwright tests
```

Because the same logical tests can execute against multiple browser engines, the physical execution count may be considerably higher.

Typical functional execution:

```powershell
cd playwright
npm install
npm run test:functional
```

---

# Cypress Automation

Directory:

```text
cypress/
```

Framework documentation:

```text
cypress/README.md
```

Technology:

```text
Cypress
TypeScript
Node.js
Mochawesome
```

The Cypress framework combines:

```text
UI automation
+
REST API automation
```

Normal UI coverage:

```text
103 tests
```

Normal API coverage:

```text
66 tests
```

Known-defect coverage:

```text
4 tests
```

Total logical Cypress coverage:

```text
173 tests
```

Run normal Cypress validation:

```powershell
cd cypress
npm install
npm run cy:ci
```

Run known defects:

```powershell
npm run cy:defects
```

Generate Mochawesome report:

```powershell
npm run cy:report
```

---

# Postman and Newman

Directory:

```text
api-testing/postman/
```

Main functional collection:

```text
QA-Commerce-Lab-API.postman_collection.json
```

Production environment:

```text
QA-Commerce-Lab-Production.postman_environment.json
```

Known defects are separated into:

```text
QA-Commerce-Lab-Known-Defects.postman_collection.json
```

This allows the primary API automation collection to stay green while confirmed defects remain independently reproducible.

Run the normal collection using the configured npm scripts from:

```powershell
cd api-testing/postman
npm install
npm run test:ci
```

Run known API defects:

```powershell
npm run test:defects
```

Newman reporting includes:

```text
HTML
JSON
CLI output
```

---

# CI/CD

GitHub Actions automatically executes the automated frameworks.

Workflows:

```text
.github/workflows/
├── selenium.yml
├── playwright.yml
├── postman.yml
└── cypress.yml
```

CI validates:

```text
Code pushed / pull request
            │
            ▼
    Automated QA checks
            │
     ┌──────┼──────┬──────┐
     ▼      ▼      ▼      ▼
 Selenium Playwright Cypress Postman
     │      │      │      │
     └──────┴──────┴──────┘
            │
            ▼
      Test artifacts
            │
            ▼
 Reports / Screenshots / Videos
```

Known confirmed defects are excluded from the primary green pipelines.

They are executed separately as defect-verification suites.

---

# Known Defect Strategy

A key design decision in this portfolio is that **automated tests are not changed to accept defective application behavior**.

Instead:

```text
Expected requirement
       │
       ▼
Automated assertion
       │
       ▼
Application behaves incorrectly
       │
       ▼
Test fails
       │
       ▼
Known defect documented
```

Confirmed defect tests are isolated from normal regression execution.

```text
Functional regression
        ↓
Expected green

Known defects
        ↓
Expected failure while bugs remain open
```

When a defect is fixed:

```text
Known-defect test begins passing
        ↓
Fix is verified
        ↓
Test moves into normal regression
```

---

# Confirmed Defects

## BUG-UI-CART-001

### Sticker Pack Quantity Limit Can Be Bypassed

The QA Sticker Pack has:

```text
Physical stock: 500
Business purchase limit: 25
```

The cart quantity editor validates against stock instead of the special purchase limit.

Correct behavior:

```text
Maximum quantity = 25
```

---

## BUG-UI-CHK-001

### Cart Counter Remains Stale After Successful Checkout

After checkout:

```text
Persisted cart:
cleared

Navigation cart counter:
still displays previous quantity
```

Refreshing the page updates the counter.

---

## BUG-UI-CHK-002

### Impossible Expiry Month Accepted

Example:

```text
13/30
```

The checkout validates the format but not the valid month range.

Correct values should use:

```text
01–12
```

---

## BUG-API-ORD-001

### Duplicate Order Lines Bypass Aggregated Stock Validation

Available stock:

```text
Product 2 = 1
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

Combined quantity:

```text
2
```

Correct response:

```text
HTTP 409
INSUFFICIENT_STOCK
```

The defective implementation validates each line separately instead of aggregating quantities before validating stock.

---

# Reporting and Evidence

The portfolio produces several forms of automated and manual test evidence.

## Manual Evidence

Stored under:

```text
manual-testing/evidence/
```

Examples include:

- Smoke-test screenshots
- Checkout evidence
- Authentication evidence
- Cart evidence
- Registration evidence
- Contact evidence

---

## Selenium

```text
reports/selenium/
```

Includes:

- Allure results
- Allure reports
- Failure screenshots

---

## Playwright

```text
reports/playwright/
```

Includes:

- HTML reports
- JSON reports
- JUnit output
- Screenshots
- Videos
- Traces

---

## Cypress

```text
reports/cypress/
```

Includes:

- Mochawesome JSON
- Mochawesome HTML
- Screenshots
- Videos

---

## Postman / Newman

```text
reports/postman/
```

Includes:

- HTML report
- JSON report
- Known-defect execution reports

Generated reports can be recreated at any time from the automated suites.

---

# Test Design Techniques

The project intentionally applies multiple test-design techniques.

## Positive Testing

Examples:

```text
Valid login
Successful registration
Successful checkout
Valid API order
Valid coupon
```

## Negative Testing

Examples:

```text
Wrong password
Malformed email
Invalid card
Unknown product
Expired coupon
```

## Boundary Value Analysis

Examples:

```text
Password minimum length
Field maximum length
Message 19 / 20 characters
MIN100 at 99.99 / 100.00
Quantity 0 / 1
```

## Equivalence Partitioning

Examples:

```text
Valid / invalid email
Valid / invalid payment
In-stock / out-of-stock products
Valid / invalid coupons
```

## State-Based Testing

Examples:

```text
Logged in / logged out
Empty / populated cart
Existing / deleted account
Before / after checkout
```

## Business Rule Testing

Examples:

```text
Stock constraints
Coupon thresholds
Shipping rules
Payment outcomes
Special product quantity limits
```

## Data Quality Testing

Examples:

```text
Unicode
Whitespace
Case sensitivity
Decimal prices
Monetary rounding
Long strings
```

---

# Cross-Browser Testing

Browser coverage is intentionally spread across the automation frameworks.

## Selenium

```text
Chrome
Firefox
Microsoft Edge
```

## Playwright

```text
Chromium
Firefox
WebKit
```

## Cypress

```text
Chrome
Microsoft Edge
```

This helps validate browser-specific behavior while demonstrating cross-browser automation using different ecosystems.

---

# Quality Engineering Principles

The portfolio follows several practical automation principles.

## Test the requirement, not the bug

Incorrect application behavior does not redefine expected results.

## Keep tests isolated

Tests should not rely on execution order.

## Prefer stable selectors

`data-testid`, semantic selectors, and stable IDs are preferred over fragile DOM hierarchy.

## Use the correct testing layer

API behavior is tested directly through APIs when browser interaction is unnecessary.

## Keep known defects separate

Acknowledged defects should remain reproducible without permanently breaking the normal CI pipeline.

## Preserve evidence

Reports, screenshots, videos, traces, and defect-linked tests make failures easier to investigate.

## Keep automation maintainable

Reusable components, page objects, fixtures, commands, and configuration reduce duplication.

## Make CI actionable

A red functional pipeline should represent an unexpected regression, not a permanently acknowledged bug.

---

# Quick Start

Clone the repository:

```powershell
git clone <repository-url>
cd qa-commerce-qa-portfolio
```

---

## Selenium

```powershell
cd selenium

mvn clean test `
  -DexcludedGroups=known-defect `
  -Dheadless=true `
  -Dbrowser=chrome
```

---

## Playwright

```powershell
cd playwright

npm install
npm run test:functional
```

---

## Cypress

```powershell
cd cypress

npm install
npm run cy:ci
```

---

## Postman / Newman

```powershell
cd api-testing\postman

npm install
npm run test:ci
```

---

# Framework Commands

## Selenium

Functional suite:

```powershell
mvn clean test -DexcludedGroups=known-defect
```

Known defects:

```powershell
mvn test -Dgroups=known-defect
```

Chrome:

```powershell
mvn test -Dbrowser=chrome
```

Firefox:

```powershell
mvn test -Dbrowser=firefox
```

Edge:

```powershell
mvn test -Dbrowser=edge
```

---

## Playwright

Functional tests:

```powershell
npm run test:functional
```

Smoke tests:

```powershell
npm run test:smoke
```

Regression:

```powershell
npm run test:regression
```

Known defects:

```powershell
npm run test:defects
```

TypeScript validation:

```powershell
npm run typecheck
```

---

## Cypress

Functional UI:

```powershell
npm run cy:functional
```

REST API:

```powershell
npm run cy:api
```

CI validation:

```powershell
npm run cy:ci
```

Known defects:

```powershell
npm run cy:defects
```

Reporting:

```powershell
npm run cy:report
```

---

## Postman

Functional API automation:

```powershell
npm run test:ci
```

Known API defects:

```powershell
npm run test:defects
```

---

# Portfolio Highlights

This repository demonstrates more than individual automation scripts.

It shows an end-to-end QA workflow:

```text
Requirements
     ↓
Test Planning
     ↓
Scenario Design
     ↓
Test Cases
     ↓
Manual Execution
     ↓
Exploratory Testing
     ↓
Defect Discovery
     ↓
Automation
     ↓
API Validation
     ↓
Cross-Browser Testing
     ↓
CI/CD
     ↓
Reports and Evidence
```

Key highlights include:

- 400+ documented UI and API test cases
- 100+ Selenium automated tests
- 100+ Playwright logical automated tests
- 173 Cypress logical automated tests
- Extensive Postman/Newman API automation
- UI and REST API coverage
- Positive, negative and boundary testing
- Real documented defects
- Automated known-defect verification
- Chrome, Firefox, Edge and WebKit coverage
- Selenium Page Object Model
- Playwright fixtures and network testing
- Cypress session management and API testing
- Allure reporting
- Playwright reporting and traces
- Mochawesome reporting
- Newman HTML/JSON reporting
- Automated GitHub Actions pipelines
- Reproducible execution evidence

---

# Project Status

```text
Requirements                    Complete
Test Plan                       Complete
Test Scenarios                  Complete
Detailed Test Cases             Complete
Manual Testing                  Complete
Defect Documentation            Complete
Postman API Automation          Complete
Selenium Automation             Complete
Playwright Automation           Complete
Cypress Automation              Complete
Automated Reporting             Complete
GitHub Actions CI               Complete
```

---

# Conclusion

QA Commerce Lab is designed as a practical demonstration of **Software Quality Engineering across the complete testing lifecycle**.

The portfolio combines:

```text
Manual QA
+
API Testing
+
UI Automation
+
Cross-Browser Testing
+
Defect Management
+
CI/CD
+
Reporting
```

into one maintainable project.

The goal is not simply to show that automated tests can be written, but to demonstrate how requirements, risk, test design, execution, defects, automation, and delivery pipelines work together to support software quality.
