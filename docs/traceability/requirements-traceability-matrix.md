# QA Commerce Lab — Requirements Traceability Matrix

## Document Information

**Project:** QA Commerce Lab
**Document:** Requirements Traceability Matrix
**Environment:** Production Test Environment
**Application URL:** https://qa-commerce-lab.vercel.app
**Status:** In Progress

---

# Purpose

The Requirements Traceability Matrix maps application requirements to:

* Test scenarios
* Detailed test cases
* Execution results
* Identified defects

The purpose of the RTM is to ensure that application requirements have appropriate test coverage and that test failures can be traced back to the requirement they affect.

---

# Traceability Flow

`Requirement → Test Scenario → Test Case → Execution Result → Defect`

---

# Status Definitions

**PASS** — Requirement has been tested and related executed test cases passed.

**FAIL** — At least one related test failed because the implementation does not satisfy the requirement.

**BLOCKED** — Required test coverage could not be executed because of test-data or environment limitations.

**PARTIAL** — Some related test coverage has been executed while additional related test cases remain unexecuted.

**NOT EXECUTED** — Test cases exist but have not yet been executed.

---

# Navigation Requirements

| Requirement | Scenario    | Related Test Case(s)      | Execution    | Defect |
| ----------- | ----------- | ------------------------- | ------------ | ------ |
| REQ-NAV-001 | NAV-001 | TC-NAV-001                | PASS         | —      |
| REQ-NAV-002 | NAV-002 | Navigation detailed cases | NOT EXECUTED | —      |
| REQ-NAV-003 | NAV-003 | Navigation detailed cases | NOT EXECUTED | —      |
| REQ-NAV-004 | NAV-004 | Navigation detailed cases | NOT EXECUTED | —      |
| REQ-NAV-005 | NAV-005 | Navigation detailed cases | NOT EXECUTED | —      |
| REQ-NAV-006 | NAV-006 | Navigation detailed cases | NOT EXECUTED | —      |
| REQ-NAV-007 | NAV-007 | Navigation detailed cases | NOT EXECUTED | —      |
| REQ-NAV-008 | NAV-008 | Navigation detailed cases | NOT EXECUTED | —      |

---

# Authentication Requirements

| Requirement  | Scenario     | Related Test Case(s)          | Execution    | Defect |
| ------------ | ------------ | ----------------------------- | ------------ | ------ |
| REQ-AUTH-001 | AUTH-001 | TC-AUTH-001                   | PASS         | —      |
| REQ-AUTH-002 | AUTH-002 | TC-AUTH-003, TC-AUTH-004      | PASS         | —      |
| REQ-AUTH-003 | AUTH-003 | TC-AUTH-006                   | PASS         | —      |
| REQ-AUTH-004 | AUTH-004 | TC-AUTH-007, TC-AUTH-008      | PASS         | —      |
| REQ-AUTH-005 | AUTH-005 | TC-AUTH-010                   | PASS         | —      |
| REQ-AUTH-006 | AUTH-006 | TC-AUTH-013                   | PASS         | —      |
| REQ-AUTH-007 | AUTH-007 | TC-AUTH-014                   | PASS         | —      |
| REQ-AUTH-008 | AUTH-008 | Authentication detailed cases | PARTIAL      | —      |
| REQ-AUTH-009 | AUTH-009 | Authentication detailed cases | PARTIAL      | —      |
| REQ-AUTH-010 | AUTH-010 | TC-AUTH-018                   | PASS         | —      |
| REQ-AUTH-011 | AUTH-011 | Authentication detailed cases | NOT EXECUTED | —      |
| REQ-AUTH-012 | AUTH-012 | Authentication detailed cases | NOT EXECUTED | —      |

---

# Registration Requirements

| Requirement | Scenario    | Related Test Case(s)        | Execution    | Defect |
| ----------- | ----------- | --------------------------- | ------------ | ------ |
| REQ-REG-001 | REG-001 | TC-REG-001                  | PASS         | —      |
| REQ-REG-002 | REG-002 | TC-REG-002                  | PASS         | —      |
| REQ-REG-003 | REG-003 | TC-REG-003                  | PASS         | —      |
| REQ-REG-004 | REG-004 | Registration detailed cases | PARTIAL      | —      |
| REQ-REG-005 | REG-005 | Registration detailed cases | PARTIAL      | —      |
| REQ-REG-006 | REG-006 | Registration detailed cases | NOT EXECUTED | —      |
| REQ-REG-007 | REG-007 | TC-REG-013, TC-REG-014      | PASS         | —      |
| REQ-REG-008 | REG-008 | TC-REG-015, TC-REG-016      | PASS         | —      |
| REQ-REG-009 | REG-009 | TC-REG-019                  | PASS         | —      |
| REQ-REG-010 | REG-010 | TC-REG-020                  | PASS         | —      |
| REQ-REG-011 | REG-011 | TC-REG-021                  | PASS         | —      |
| REQ-REG-012 | REG-012 | TC-REG-022                  | PASS         | —      |
| REQ-REG-013 | REG-013 | TC-REG-023                  | PASS         | —      |
| REQ-REG-014 | REG-014 | TC-REG-025                  | PASS         | —      |
| REQ-REG-015 | REG-015 | TC-REG-028                  | PASS         | —      |
| REQ-REG-016 | REG-016 | Registration detailed cases | PARTIAL      | —      |
| REQ-REG-017 | REG-016 | Registration detailed cases | PARTIAL      | —      |

---

# Product Requirements

| Requirement  | Scenario     | Related Test Case(s)   | Execution | Defect |
| ------------ | ------------ | ---------------------- | --------- | ------ |
| REQ-PROD-001 | PROD-001 | TC-PROD-001            | PASS      | —      |
| REQ-PROD-002 | PROD-002 | Product detailed cases | PARTIAL   | —      |
| REQ-PROD-003 | PROD-003 | Product detailed cases | PARTIAL   | —      |
| REQ-PROD-004 | PROD-004 | TC-PROD-004            | PASS      | —      |
| REQ-PROD-005 | PROD-005 | TC-PROD-006            | PASS      | —      |
| REQ-PROD-006 | PROD-006 | TC-PROD-008            | PASS      | —      |
| REQ-PROD-007 | PROD-007 | TC-PROD-009            | PASS      | —      |
| REQ-PROD-008 | PROD-008 | TC-PROD-010            | PASS      | —      |
| REQ-PROD-009 | PROD-009 | TC-PROD-011            | PASS      | —      |
| REQ-PROD-010 | PROD-010 | Product detailed cases | PARTIAL   | —      |
| REQ-PROD-011 | PROD-011 | TC-PROD-014            | PASS      | —      |
| REQ-PROD-012 | PROD-012 | TC-PROD-015            | PASS      | —      |
| REQ-PROD-013 | PROD-013 | TC-PROD-016            | PASS      | —      |
| REQ-PROD-014 | PROD-014 | Product detailed cases | PARTIAL   | —      |
| REQ-PROD-015 | PROD-015 | TC-PROD-018            | PASS      | —      |
| REQ-PROD-016 | PROD-016 | TC-PROD-019            | PASS      | —      |
| REQ-PROD-017 | PROD-017 | TC-PROD-020            | PASS      | —      |
| REQ-PROD-018 | PROD-018 | TC-PROD-022            | PASS      | —      |
| REQ-PROD-019 | PROD-018 | Product detailed cases | PARTIAL   | —      |
| REQ-PROD-020 | PROD-018 | Product detailed cases | PARTIAL   | —      |
| REQ-PROD-021 | PROD-018 | Product detailed cases | PARTIAL   | —      |

---

# Cart Requirements

| Requirement  | Scenario     | Related Test Case(s)     | Execution | Defect       |
| ------------ | ------------ | ------------------------ | --------- | ------------ |
| REQ-CART-001 | CART-001 | TC-CART-001              | PASS      | —            |
| REQ-CART-002 | CART-002 | TC-CART-003              | PASS      | —            |
| REQ-CART-003 | CART-003 | TC-CART-004              | PASS      | —            |
| REQ-CART-004 | CART-004 | TC-CART-006              | PASS      | —            |
| REQ-CART-005 | CART-005 | TC-CART-007              | PASS      | —            |
| REQ-CART-006 | CART-006 | TC-CART-008              | PASS      | —            |
| REQ-CART-007 | CART-007 | TC-CART-009              | PASS      | —            |
| REQ-CART-008 | CART-008 | TC-CART-010              | PASS      | —            |
| REQ-CART-009 | CART-009 | TC-CART-012, TC-CART-013 | FAIL      | BUG-CART-002 |
| REQ-CART-010 | CART-010 | TC-CART-015              | PASS      | —            |
| REQ-CART-011 | CART-011 | TC-CART-020              | PASS      | —            |
| REQ-CART-012 | CART-012 | TC-CART-023              | PASS      | —            |
| REQ-CART-013 | CART-013 | EXP-001                  | FAIL      | BUG-CART-001 |
| REQ-CART-014 | CART-014 | Cart detailed cases      | PARTIAL   | —            |
| REQ-CART-015 | CART-015 | Cart detailed cases      | PARTIAL   | —            |
| REQ-CART-016 | CART-016 | Cart detailed cases      | PARTIAL   | —            |

---

# Coupon Requirements

| Requirement | Scenario    | Related Test Case(s)  | Execution | Defect |
| ----------- | ----------- | --------------------- | --------- | ------ |
| REQ-CPN-001 | CPN-001 | TC-CPN-001            | PASS      | —      |
| REQ-CPN-002 | CPN-002 | TC-CPN-002            | PASS      | —      |
| REQ-CPN-003 | CPN-003 | TC-CPN-004            | PASS      | —      |
| REQ-CPN-004 | CPN-004 | TC-CPN-005            | PASS      | —      |
| REQ-CPN-005 | CPN-005 | TC-CPN-008            | PASS      | —      |
| REQ-CPN-006 | CPN-006 | TC-CPN-009            | PASS      | —      |
| REQ-CPN-007 | CPN-007 | TC-CPN-010            | PASS      | —      |
| REQ-CPN-008 | CPN-008 | TC-CPN-012            | BLOCKED   | —      |
| REQ-CPN-009 | CPN-009 | TC-CPN-014            | PASS      | —      |
| REQ-CPN-010 | CPN-010 | Coupon detailed cases | PARTIAL   | —      |
| REQ-CPN-011 | CPN-011 | Coupon detailed cases | PARTIAL   | —      |

---

# Checkout Requirements

| Requirement | Scenario    | Related Test Case(s)                           | Execution | Defect      |
| ----------- | ----------- | ---------------------------------------------- | --------- | ----------- |
| REQ-CHK-001 | CHK-001 | TC-CHK-001                                     | PASS      | —           |
| REQ-CHK-002 | CHK-002 | TC-CHK-002                                     | PASS      | —           |
| REQ-CHK-003 | CHK-003 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-004 | CHK-004 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-005 | CHK-005 | TC-CHK-009, TC-CHK-010                         | PASS      | —           |
| REQ-CHK-006 | CHK-006 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-007 | CHK-007 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-008 | CHK-008 | TC-CHK-014, TC-CHK-017                         | PASS      | —           |
| REQ-CHK-009 | CHK-009 | TC-CHK-018, TC-CHK-019                         | PASS      | —           |
| REQ-CHK-010 | CHK-010 | TC-CHK-020                                     | PASS      | —           |
| REQ-CHK-011 | CHK-011 | TC-CHK-023, TC-CHK-024, EXP-003                | FAIL      | BUG-CHK-001 |
| REQ-CHK-012 | CHK-012 | TC-CHK-026, TC-CHK-027, TC-CHK-028, TC-CHK-029 | PASS      | —           |
| REQ-CHK-013 | CHK-013 | TC-CHK-031                                     | PASS      | —           |
| REQ-CHK-014 | CHK-014 | TC-CHK-032                                     | PASS      | —           |
| REQ-CHK-015 | CHK-015 | TC-CHK-033                                     | PASS      | —           |
| REQ-CHK-016 | CHK-016 | TC-CHK-034                                     | PASS      | —           |
| REQ-CHK-017 | CHK-017 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-018 | CHK-018 | TC-CHK-036                                     | PASS      | —           |
| REQ-CHK-019 | CHK-019 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-020 | CHK-020 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-021 | CHK-021 | Checkout detailed cases                        | PARTIAL   | —           |
| REQ-CHK-022 | CHK-022 | Checkout detailed cases                        | PARTIAL   | —           |

---

# Account Requirements

| Requirement | Scenario    | Related Test Case(s)   | Execution | Defect |
| ----------- | ----------- | ---------------------- | --------- | ------ |
| REQ-ACC-001 | ACC-001 | Account detailed cases | PARTIAL   | —      |
| REQ-ACC-002 | ACC-002 | Account detailed cases | PARTIAL   | —      |
| REQ-ACC-003 | ACC-003 | TC-ACC-003             | PASS      | —      |
| REQ-ACC-004 | ACC-004 | TC-ACC-005             | PASS      | —      |
| REQ-ACC-005 | ACC-005 | TC-ACC-006, TC-ACC-007 | PASS      | —      |
| REQ-ACC-006 | ACC-006 | TC-ACC-010             | PASS      | —      |
| REQ-ACC-007 | ACC-007 | TC-ACC-012             | PASS      | —      |
| REQ-ACC-008 | ACC-008 | TC-ACC-013             | PASS      | —      |
| REQ-ACC-009 | ACC-009 | Account detailed cases | PARTIAL   | —      |

---

# Contact Requirements

| Requirement | Scenario    | Related Test Case(s)   | Execution | Defect |
| ----------- | ----------- | ---------------------- | --------- | ------ |
| REQ-CON-001 | CON-001 | TC-CON-001             | PASS      | —      |
| REQ-CON-002 | CON-002 | TC-CON-003, TC-CON-004 | PASS      | —      |
| REQ-CON-003 | CON-003 | TC-CON-005             | PASS      | —      |
| REQ-CON-004 | CON-004 | TC-CON-006             | PASS      | —      |
| REQ-CON-005 | CON-005 | TC-CON-008, TC-CON-009 | PASS      | —      |
| REQ-CON-006 | CON-006 | Contact detailed cases | PARTIAL   | —      |
| REQ-CON-007 | CON-007 | TC-CON-015             | PASS      | —      |
| REQ-CON-008 | CON-008 | TC-CON-018             | PASS      | —      |
| REQ-CON-009 | CON-009 | Contact detailed cases | PARTIAL   | —      |
| REQ-CON-010 | CON-010 | Contact detailed cases | PARTIAL   | —      |

---

# QA Lab Requirements

| Requirement | Scenario    | Related Test Case(s)  | Execution | Defect |
| ----------- | ----------- | --------------------- | --------- | ------ |
| REQ-LAB-001 | LAB-001 | TC-LAB-001            | PASS      | —      |
| REQ-LAB-002 | LAB-002 | TC-LAB-002            | PASS      | —      |
| REQ-LAB-003 | LAB-003 | TC-LAB-003            | PASS      | —      |
| REQ-LAB-004 | LAB-004 | TC-LAB-004            | PASS      | —      |
| REQ-LAB-005 | LAB-005 | TC-LAB-005            | PASS      | —      |
| REQ-LAB-006 | LAB-006 | TC-LAB-006            | PASS      | —      |
| REQ-LAB-007 | LAB-007 | TC-LAB-007            | PASS      | —      |
| REQ-LAB-008 | LAB-008 | TC-LAB-008            | PASS      | —      |
| REQ-LAB-009 | LAB-009 | TC-LAB-009            | PASS      | —      |
| REQ-LAB-010 | LAB-010 | TC-LAB-010            | PASS      | —      |
| REQ-LAB-011 | LAB-011 | TC-LAB-011            | PASS      | —      |
| REQ-LAB-012 | LAB-012 | TC-LAB-012            | PASS      | —      |
| REQ-LAB-013 | LAB-013 | TC-LAB-013            | PASS      | —      |
| REQ-LAB-014 | LAB-014 | TC-LAB-014            | PASS      | —      |
| REQ-LAB-015 | LAB-014 | QA Lab detailed cases | PARTIAL   | —      |

---

# REST API Requirements

REST API detailed test cases have been designed but full Postman/Newman execution has not yet been completed.

Therefore, API requirements are currently considered covered by test design but not executed.

## General API

| Requirement Group         | Detailed Test Coverage    | Execution    |
| ------------------------- | ------------------------- | ------------ |
| REQ-API-001 — REQ-API-006 | general-api-test-cases.md | NOT EXECUTED |

## Health API

| Requirement Group                 | Detailed Test Coverage   | Execution    |
| --------------------------------- | ------------------------ | ------------ |
| REQ-API-HLT-001 — REQ-API-HLT-003 | health-api-test-cases.md | NOT EXECUTED |

## Products API

| Requirement Group                   | Detailed Test Coverage     | Execution    |
| ----------------------------------- | -------------------------- | ------------ |
| REQ-API-PROD-001 — REQ-API-PROD-013 | products-api-test-cases.md | NOT EXECUTED |

## Search API

| Requirement Group                   | Detailed Test Coverage   | Execution    |
| ----------------------------------- | ------------------------ | ------------ |
| REQ-API-SRCH-001 — REQ-API-SRCH-006 | search-api-test-cases.md | NOT EXECUTED |

## Authentication API

| Requirement Group                   | Detailed Test Coverage           | Execution    |
| ----------------------------------- | -------------------------------- | ------------ |
| REQ-API-AUTH-001 — REQ-API-AUTH-007 | authentication-api-test-cases.md | NOT EXECUTED |

## Users API

| Requirement Group                 | Detailed Test Coverage  | Execution    |
| --------------------------------- | ----------------------- | ------------ |
| REQ-API-USR-001 — REQ-API-USR-012 | users-api-test-cases.md | NOT EXECUTED |

## Coupons API

| Requirement Group                 | Detailed Test Coverage    | Execution    |
| --------------------------------- | ------------------------- | ------------ |
| REQ-API-CPN-001 — REQ-API-CPN-008 | coupons-api-test-cases.md | NOT EXECUTED |

## Orders API

| Requirement Group                 | Detailed Test Coverage   | Execution    |
| --------------------------------- | ------------------------ | ------------ |
| REQ-API-ORD-001 — REQ-API-ORD-013 | orders-api-test-cases.md | NOT EXECUTED |

## Reviews API

| Requirement Group                 | Detailed Test Coverage    | Execution    |
| --------------------------------- | ------------------------- | ------------ |
| REQ-API-REV-001 — REQ-API-REV-007 | reviews-api-test-cases.md | NOT EXECUTED |

## Contact API

| Requirement Group                 | Detailed Test Coverage    | Execution    |
| --------------------------------- | ------------------------- | ------------ |
| REQ-API-CON-001 — REQ-API-CON-008 | contact-api-test-cases.md | NOT EXECUTED |

## Edge Case API

The edge-case endpoint was exercised as part of manual QA Lab regression testing.

| Requirement Group                   | Detailed Test Coverage       | Execution |
| ----------------------------------- | ---------------------------- | --------- |
| REQ-API-EDGE-001 — REQ-API-EDGE-012 | edge-cases-api-test-cases.md | PARTIAL   |

Verified production behaviors included:

* HTTP 200
* HTTP 204
* HTTP 400
* HTTP 401
* HTTP 404
* HTTP 409
* HTTP 422
* HTTP 429
* Retry-After header
* HTTP 500
* Slow response
* Large 250-record response

Formal API test-case execution will be performed during the API automation phase.

## Echo API

| Requirement Group                   | Detailed Test Coverage | Execution    |
| ----------------------------------- | ---------------------- | ------------ |
| REQ-API-ECHO-001 — REQ-API-ECHO-006 | echo-api-test-cases.md | NOT EXECUTED |

---

# Non-Functional Requirements

| Requirement | Coverage                        | Execution    |
| ----------- | ------------------------------- | ------------ |
| REQ-NFR-001 | Compatibility test cases        | NOT EXECUTED |
| REQ-NFR-002 | Responsive test cases           | NOT EXECUTED |
| REQ-NFR-003 | Unicode/layout checks           | PARTIAL      |
| REQ-NFR-004 | Cross-browser checks            | NOT EXECUTED |
| REQ-NFR-005 | Deterministic behavior checks   | PARTIAL      |
| REQ-NFR-006 | Stability/error-handling checks | PARTIAL      |

---

# End-to-End Coverage

| Scenario    | Flow                                                | Execution    |
| ----------- | --------------------------------------------------- | ------------ |
| E2E-001 | Register → Browse → Cart → Checkout                 | PARTIAL      |
| E2E-002 | Login → Search → SAVE10 → Checkout                  | PASS         |
| E2E-003 | Multiple products → Quantity → Remove → Checkout    | PARTIAL      |
| E2E-004 | Register → Logout → Login → Delete → Login rejected | PARTIAL      |
| E2E-005 | Guest Cart → Checkout → Login → Resume              | PARTIAL      |
| E2E-006 | API Create User → UI Login/Checkout → API Delete    | NOT EXECUTED |

---

# Defect Traceability

| Defect       | Affected Area   | Related Test         | Requirement Impact                | Status |
| ------------ | --------------- | -------------------- | --------------------------------- | ------ |
| BUG-CART-001 | Cart / Checkout | EXP-001              | Cart state/navigation consistency | Open   |
| BUG-CART-002 | Cart            | TC-CART-013, EXP-002 | REQ-CART-009                      | Open   |
| BUG-CHK-001  | Checkout        | EXP-003              | REQ-CHK-011                       | Open   |

---

# Blocked Coverage

## TC-CPN-012

**Requirement Area:** Coupon / Shipping Threshold

**Condition:** Automatic free shipping at exactly `$150.00`

**Status:** BLOCKED

### Reason

The current application catalog and permitted stock quantities do not provide a valid product combination that produces an exact `$150.00` subtotal.

This is considered a test-data limitation rather than an application defect.

---

# Current Traceability Assessment

The project currently provides traceability between:

* Requirements
* Test scenarios
* Detailed manual test cases
* Smoke execution
* Regression execution
* Exploratory testing
* Defect reports

API requirements also have detailed test cases designed, but most have not yet been formally executed.

The traceability matrix will therefore continue to be updated as:

* Postman tests are executed.
* Newman automation is added.
* Selenium tests are executed.
* Playwright tests are executed.
* Cypress tests are executed.
* Cross-browser testing is completed.
* CI/CD results become available.

---

# Current Quality Findings

Three unique defects are currently open:

### BUG-CART-001

Cart counter remains stale immediately after successful checkout.

**Severity:** Medium

---

### BUG-CART-002

QA Sticker Pack maximum quantity can be bypassed from Cart.

**Severity:** High

---

### BUG-CHK-001

Checkout accepts invalid and expired card expiry values.

**Severity:** High

---

# Traceability Status

## IN PROGRESS

Manual smoke, regression, exploratory testing, and defect traceability have been added.

API and automation execution results will be added during later project phases.

