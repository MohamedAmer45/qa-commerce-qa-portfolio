# QA Commerce Lab — Test Scenario Catalog

## 1. Document Information

**Project:** QA Commerce Lab QA Portfolio
**Application:** QA Commerce Lab
**Application URL:** https://qa-commerce-lab.vercel.app
**Document Type:** Test Scenario Catalog
**Version:** 1.0
**Status:** Baseline

---

## 2. Purpose

This document defines the high-level test scenarios used to validate QA Commerce Lab.

Every scenario should trace to one or more requirements defined in:

`docs/requirements/requirements-catalog.md`

Detailed test cases will be derived from these scenarios in the next test-design phase.

---

## 3. Scenario ID Convention

Scenario identifiers use:

`SCN-[MODULE]-[NUMBER]`

Examples:

* SCN-AUTH-001
* SCN-REG-001
* SCN-PROD-001
* SCN-CART-001
* SCN-CHK-001
* SCN-API-PROD-001

---

# 4. Navigation Scenarios

| ID          | Scenario                                                             | Type        | Priority | Requirement |
| ----------- | -------------------------------------------------------------------- | ----------- | -------- | ----------- |
| SCN-NAV-001 | Validate global navigation is available throughout the application.  | Functional  | High     | REQ-NAV-001 |
| SCN-NAV-002 | Validate navigation to Products, QA Lab, API Docs, and Contact.      | Functional  | High     | REQ-NAV-002 |
| SCN-NAV-003 | Validate the QA Commerce logo returns the user to the home page.     | Functional  | Medium   | REQ-NAV-003 |
| SCN-NAV-004 | Validate unauthenticated navigation displays Sign In.                | State       | High     | REQ-NAV-004 |
| SCN-NAV-005 | Validate authenticated navigation displays the user's first name.    | State       | Medium   | REQ-NAV-005 |
| SCN-NAV-006 | Validate the cart navigation indicator reflects total item quantity. | Integration | High     | REQ-NAV-006 |
| SCN-NAV-007 | Validate unknown application routes display a 404 state.             | Negative    | Medium   | REQ-NAV-007 |
| SCN-NAV-008 | Validate browser Back and Forward navigation.                        | Navigation  | Medium   | REQ-NAV-008 |

---

# 5. Authentication Scenarios

| ID           | Scenario                                                                          | Type        | Priority | Requirement                              |
| ------------ | --------------------------------------------------------------------------------- | ----------- | -------- | ---------------------------------------- |
| SCN-AUTH-001 | Validate successful login using the seeded valid account.                         | Positive    | Critical | REQ-AUTH-001                             |
| SCN-AUTH-002 | Validate successful login using a newly registered valid account.                 | Integration | Critical | REQ-AUTH-001                             |
| SCN-AUTH-003 | Validate email comparison is case-insensitive.                                    | Validation  | High     | REQ-AUTH-002                             |
| SCN-AUTH-004 | Validate surrounding whitespace in login email is ignored.                        | Validation  | Medium   | REQ-AUTH-003                             |
| SCN-AUTH-005 | Validate password comparison remains case-sensitive.                              | Negative    | High     | REQ-AUTH-004                             |
| SCN-AUTH-006 | Validate login when required credentials are missing.                             | Negative    | High     | REQ-AUTH-005, REQ-AUTH-006, REQ-AUTH-007 |
| SCN-AUTH-007 | Validate login with malformed email values.                                       | Negative    | High     | REQ-AUTH-008                             |
| SCN-AUTH-008 | Validate login with incorrect credentials.                                        | Negative    | Critical | REQ-AUTH-009                             |
| SCN-AUTH-009 | Validate successful login establishes authenticated state.                        | State       | Critical | REQ-AUTH-010                             |
| SCN-AUTH-010 | Validate successful login redirects to the Account page.                          | Navigation  | High     | REQ-AUTH-011                             |
| SCN-AUTH-011 | Validate authenticated state persists during normal browser navigation.           | State       | High     | REQ-AUTH-012                             |
| SCN-AUTH-012 | Validate authentication state after refreshing an authenticated application page. | State       | High     | REQ-AUTH-012                             |

---

# 6. Registration Scenarios

| ID          | Scenario                                                                                 | Type        | Priority | Requirement                                        |
| ----------- | ---------------------------------------------------------------------------------------- | ----------- | -------- | -------------------------------------------------- |
| SCN-REG-001 | Validate account creation using fully valid registration information.                    | Positive    | Critical | REQ-REG-001                                        |
| SCN-REG-002 | Validate first-name and last-name required-field behavior.                               | Negative    | High     | REQ-REG-002, REQ-REG-003                           |
| SCN-REG-003 | Validate first-name length boundaries.                                                   | Boundary    | Medium   | REQ-REG-004                                        |
| SCN-REG-004 | Validate last-name length boundaries.                                                    | Boundary    | Medium   | REQ-REG-004                                        |
| SCN-REG-005 | Validate email is required.                                                              | Negative    | High     | REQ-REG-005                                        |
| SCN-REG-006 | Validate malformed registration email formats.                                           | Negative    | High     | REQ-REG-005                                        |
| SCN-REG-007 | Validate registration email maximum-length behavior.                                     | Boundary    | Medium   | REQ-REG-006                                        |
| SCN-REG-008 | Validate registration rejects duplicate email addresses.                                 | Negative    | Critical | REQ-REG-007                                        |
| SCN-REG-009 | Validate duplicate email comparison is case-insensitive.                                 | Negative    | High     | REQ-REG-008                                        |
| SCN-REG-010 | Validate password minimum and maximum length boundaries.                                 | Boundary    | High     | REQ-REG-009                                        |
| SCN-REG-011 | Validate password complexity requirements.                                               | Negative    | High     | REQ-REG-010, REQ-REG-011, REQ-REG-012, REQ-REG-013 |
| SCN-REG-012 | Validate password-confirmation mismatch handling.                                        | Negative    | High     | REQ-REG-014                                        |
| SCN-REG-013 | Validate Terms acceptance is required.                                                   | Negative    | High     | REQ-REG-015                                        |
| SCN-REG-014 | Validate successful registration automatically authenticates the user.                   | Integration | High     | REQ-REG-016                                        |
| SCN-REG-015 | Validate successful registration navigates to Account.                                   | Navigation  | High     | REQ-REG-017                                        |
| SCN-REG-016 | Validate registration fields with Unicode, whitespace, and unusual-but-valid text input. | Edge        | Medium   | REQ-REG-001, REQ-REG-004                           |

---

# 7. Product Catalog Scenarios

| ID           | Scenario                                                                             | Type       | Priority | Requirement                                            |
| ------------ | ------------------------------------------------------------------------------------ | ---------- | -------- | ------------------------------------------------------ |
| SCN-PROD-001 | Validate the complete product catalog loads without filters.                         | Positive   | Critical | REQ-PROD-001                                           |
| SCN-PROD-002 | Validate product names, categories, prices, and stock information are displayed.     | Functional | High     | REQ-PROD-002, REQ-PROD-003, REQ-PROD-004, REQ-PROD-007 |
| SCN-PROD-003 | Validate displayed prices use USD currency formatting.                               | Functional | High     | REQ-PROD-005                                           |
| SCN-PROD-004 | Validate three-decimal product prices are displayed using correct monetary rounding. | Boundary   | High     | REQ-PROD-006                                           |
| SCN-PROD-005 | Validate search by complete product name.                                            | Positive   | High     | REQ-PROD-008                                           |
| SCN-PROD-006 | Validate search by partial product name.                                             | Positive   | High     | REQ-PROD-008                                           |
| SCN-PROD-007 | Validate search using category text.                                                 | Positive   | Medium   | REQ-PROD-009                                           |
| SCN-PROD-008 | Validate product search is case-insensitive.                                         | Validation | Medium   | REQ-PROD-010                                           |
| SCN-PROD-009 | Validate search ignores surrounding whitespace.                                      | Validation | Medium   | REQ-PROD-011                                           |
| SCN-PROD-010 | Validate search with no matching products.                                           | Negative   | High     | REQ-PROD-012                                           |
| SCN-PROD-011 | Validate product filtering by every available category.                              | Functional | High     | REQ-PROD-013                                           |
| SCN-PROD-012 | Validate product sorting by ascending price.                                         | Functional | Medium   | REQ-PROD-014                                           |
| SCN-PROD-013 | Validate product sorting by descending price.                                        | Functional | Medium   | REQ-PROD-015                                           |
| SCN-PROD-014 | Validate out-of-stock product presentation and cart restriction.                     | Boundary   | Critical | REQ-PROD-016, REQ-PROD-017                             |
| SCN-PROD-015 | Validate zero-price product behavior.                                                | Edge       | Medium   | REQ-PROD-020                                           |
| SCN-PROD-016 | Validate high-value product price behavior.                                          | Edge       | Medium   | REQ-PROD-021                                           |
| SCN-PROD-017 | Validate Unicode and multilingual product names.                                     | Edge       | Medium   | REQ-PROD-018                                           |
| SCN-PROD-018 | Validate long product names do not make the catalog unusable.                        | UI         | Medium   | REQ-PROD-019, REQ-NFR-004                              |

---

# 8. Shopping Cart Scenarios

| ID           | Scenario                                                                | Type        | Priority | Requirement                |
| ------------ | ----------------------------------------------------------------------- | ----------- | -------- | -------------------------- |
| SCN-CART-001 | Validate adding an available product to an empty cart.                  | Positive    | Critical | REQ-CART-001               |
| SCN-CART-002 | Validate adding multiple different products.                            | Positive    | Critical | REQ-CART-001               |
| SCN-CART-003 | Validate adding the same product multiple times increases quantity.     | Functional  | High     | REQ-CART-002               |
| SCN-CART-004 | Validate out-of-stock products cannot be added.                         | Negative    | Critical | REQ-CART-003               |
| SCN-CART-005 | Validate cart quantity accepts only positive whole numbers.             | Boundary    | Critical | REQ-CART-004               |
| SCN-CART-006 | Validate cart quantity cannot exceed available inventory.               | Boundary    | Critical | REQ-CART-005               |
| SCN-CART-007 | Validate QA Sticker Pack maximum cart-add quantity of 25.               | Boundary    | Medium   | REQ-CART-006               |
| SCN-CART-008 | Validate cart displays product information and quantity correctly.      | Functional  | High     | REQ-CART-007, REQ-CART-008 |
| SCN-CART-009 | Validate individual product line-total calculation.                     | Calculation | High     | REQ-CART-009               |
| SCN-CART-010 | Validate updating a valid quantity from the cart.                       | Positive    | Critical | REQ-CART-010               |
| SCN-CART-011 | Validate invalid cart quantity changes are rejected.                    | Negative    | Critical | REQ-CART-011               |
| SCN-CART-012 | Validate removing a product from the cart.                              | Positive    | Critical | REQ-CART-012               |
| SCN-CART-013 | Validate cart navigation count after add, quantity change, and removal. | Integration | High     | REQ-CART-013               |
| SCN-CART-014 | Validate subtotal calculation with one and multiple products.           | Calculation | Critical | REQ-CART-014               |
| SCN-CART-015 | Validate empty-cart presentation.                                       | State       | High     | REQ-CART-015               |
| SCN-CART-016 | Validate cart contents persist during browser navigation and refresh.   | State       | High     | REQ-CART-016               |

---

# 9. Coupon Scenarios

| ID          | Scenario                                                                  | Type        | Priority | Requirement                           |
| ----------- | ------------------------------------------------------------------------- | ----------- | -------- | ------------------------------------- |
| SCN-CPN-001 | Validate coupon submission from the cart.                                 | Positive    | High     | REQ-CPN-001                           |
| SCN-CPN-002 | Validate coupon codes are case-insensitive.                               | Validation  | Medium   | REQ-CPN-002                           |
| SCN-CPN-003 | Validate coupon surrounding whitespace is ignored.                        | Validation  | Medium   | REQ-CPN-003                           |
| SCN-CPN-004 | Validate SAVE10 applies a 10% subtotal discount.                          | Calculation | High     | REQ-CPN-004                           |
| SCN-CPN-005 | Validate FREESHIP removes shipping cost.                                  | Calculation | High     | REQ-CPN-005                           |
| SCN-CPN-006 | Validate MIN100 discount when subtotal equals or exceeds $100.            | Boundary    | High     | REQ-CPN-006                           |
| SCN-CPN-007 | Validate MIN100 is rejected when subtotal is below $100.                  | Boundary    | High     | REQ-CPN-007                           |
| SCN-CPN-008 | Validate EXPIRED is rejected.                                             | Negative    | Medium   | REQ-CPN-008                           |
| SCN-CPN-009 | Validate unknown coupon codes are rejected.                               | Negative    | High     | REQ-CPN-009                           |
| SCN-CPN-010 | Validate automatic free shipping at exactly and above the $150 threshold. | Boundary    | High     | REQ-CPN-010                           |
| SCN-CPN-011 | Validate shipping cost below the free-shipping threshold.                 | Calculation | High     | REQ-CPN-011                           |
| SCN-CPN-012 | Validate total recalculation when a coupon changes the order value.       | Integration | Critical | REQ-CPN-004, REQ-CPN-005, REQ-CPN-006 |

---

# 10. Checkout Scenarios

| ID          | Scenario                                                                                | Type        | Priority | Requirement                                                                  |
| ----------- | --------------------------------------------------------------------------------------- | ----------- | -------- | ---------------------------------------------------------------------------- |
| SCN-CHK-001 | Validate unauthenticated users cannot complete checkout.                                | Negative    | Critical | REQ-CHK-001                                                                  |
| SCN-CHK-002 | Validate checkout cannot proceed with an empty cart.                                    | Negative    | Critical | REQ-CHK-002                                                                  |
| SCN-CHK-003 | Validate checkout pre-populates account name and email.                                 | Integration | Medium   | REQ-CHK-003                                                                  |
| SCN-CHK-004 | Validate required shipping fields.                                                      | Negative    | High     | REQ-CHK-004, REQ-CHK-005, REQ-CHK-006, REQ-CHK-007, REQ-CHK-009, REQ-CHK-010 |
| SCN-CHK-005 | Validate checkout email format.                                                         | Negative    | High     | REQ-CHK-006                                                                  |
| SCN-CHK-006 | Validate shipping-address minimum-length boundary.                                      | Boundary    | Medium   | REQ-CHK-008                                                                  |
| SCN-CHK-007 | Validate cardholder name requirement.                                                   | Negative    | High     | REQ-CHK-011                                                                  |
| SCN-CHK-008 | Validate card-number length boundaries.                                                 | Boundary    | Critical | REQ-CHK-012                                                                  |
| SCN-CHK-009 | Validate valid Luhn card numbers.                                                       | Positive    | Critical | REQ-CHK-013                                                                  |
| SCN-CHK-010 | Validate invalid Luhn card numbers are rejected.                                        | Negative    | Critical | REQ-CHK-013                                                                  |
| SCN-CHK-011 | Validate spaces are handled correctly in card numbers.                                  | Validation  | High     | REQ-CHK-012                                                                  |
| SCN-CHK-012 | Validate invalid card-number character types.                                           | Negative    | Critical | REQ-CHK-012                                                                  |
| SCN-CHK-013 | Validate card expiry MM/YY format.                                                      | Validation  | High     | REQ-CHK-014                                                                  |
| SCN-CHK-014 | Validate CVV accepts only three or four digits.                                         | Boundary    | Critical | REQ-CHK-015                                                                  |
| SCN-CHK-015 | Validate declined-payment simulation.                                                   | Negative    | High     | REQ-CHK-016                                                                  |
| SCN-CHK-016 | Validate insufficient-funds simulation.                                                 | Negative    | High     | REQ-CHK-017                                                                  |
| SCN-CHK-017 | Validate successful payment using the configured success card.                          | Positive    | Critical | REQ-CHK-018                                                                  |
| SCN-CHK-018 | Validate repeated Place Order actions do not create duplicate submissions.              | State       | Critical | REQ-CHK-019                                                                  |
| SCN-CHK-019 | Validate successful checkout displays confirmation.                                     | Positive    | Critical | REQ-CHK-020                                                                  |
| SCN-CHK-020 | Validate successful checkout displays the expected order reference.                     | Functional  | High     | REQ-CHK-021                                                                  |
| SCN-CHK-021 | Validate successful checkout clears the cart.                                           | Integration | Critical | REQ-CHK-022                                                                  |
| SCN-CHK-022 | Validate complete product-to-order customer journey.                                    | End-to-End  | Critical | REQ-CART-001, REQ-CHK-018, REQ-CHK-020                                       |
| SCN-CHK-023 | Validate checkout behavior after changing cart quantities immediately before checkout.  | Integration | High     | REQ-CART-010, REQ-CHK-002                                                    |
| SCN-CHK-024 | Validate checkout behavior after navigating Back and Forward through the purchase flow. | State       | Medium   | REQ-NAV-008, REQ-CHK-020                                                     |

---

# 11. Account Scenarios

| ID          | Scenario                                                                   | Type        | Priority | Requirement |
| ----------- | -------------------------------------------------------------------------- | ----------- | -------- | ----------- |
| SCN-ACC-001 | Validate authenticated access to Account.                                  | Positive    | High     | REQ-ACC-001 |
| SCN-ACC-002 | Validate displayed account name and email.                                 | Functional  | Medium   | REQ-ACC-002 |
| SCN-ACC-003 | Validate logout from Account.                                              | Positive    | Critical | REQ-ACC-003 |
| SCN-ACC-004 | Validate logout removes authenticated state.                               | State       | Critical | REQ-ACC-004 |
| SCN-ACC-005 | Validate exact DELETE confirmation is required for deletion.               | Negative    | Critical | REQ-ACC-005 |
| SCN-ACC-006 | Validate incorrect account-deletion confirmation values.                   | Negative    | Critical | REQ-ACC-006 |
| SCN-ACC-007 | Validate deletion of a dynamically registered account.                     | Positive    | Critical | REQ-ACC-007 |
| SCN-ACC-008 | Validate deleted user is automatically logged out.                         | State       | High     | REQ-ACC-008 |
| SCN-ACC-009 | Validate deleted dynamic account can no longer authenticate.               | Integration | Critical | REQ-ACC-007 |
| SCN-ACC-010 | Validate seeded QA account remains reusable following delete-flow testing. | Testability | High     | REQ-ACC-009 |

---

# 12. Contact Form Scenarios

| ID          | Scenario                                                         | Type       | Priority | Requirement              |
| ----------- | ---------------------------------------------------------------- | ---------- | -------- | ------------------------ |
| SCN-CON-001 | Validate successful support request without attachment.          | Positive   | High     | REQ-CON-001              |
| SCN-CON-002 | Validate successful support request with a supported attachment. | Positive   | High     | REQ-CON-001, REQ-CON-007 |
| SCN-CON-003 | Validate contact-name minimum-length boundary.                   | Boundary   | Medium   | REQ-CON-002              |
| SCN-CON-004 | Validate contact email format.                                   | Negative   | High     | REQ-CON-003              |
| SCN-CON-005 | Validate support subject is required.                            | Negative   | High     | REQ-CON-004              |
| SCN-CON-006 | Validate each supported subject option.                          | Positive   | Medium   | REQ-CON-005              |
| SCN-CON-007 | Validate message minimum-length boundary.                        | Boundary   | High     | REQ-CON-006              |
| SCN-CON-008 | Validate message maximum-length boundary.                        | Boundary   | High     | REQ-CON-006              |
| SCN-CON-009 | Validate accepted attachment file extensions.                    | Positive   | High     | REQ-CON-008              |
| SCN-CON-010 | Validate unsupported attachment extensions are rejected.         | Negative   | High     | REQ-CON-008              |
| SCN-CON-011 | Validate attachment 2 MB size boundary.                          | Boundary   | High     | REQ-CON-009              |
| SCN-CON-012 | Validate success feedback after a valid support submission.      | Functional | High     | REQ-CON-010, REQ-NFR-006 |

---

# 13. QA Lab UI Scenarios

| ID          | Scenario                                                       | Type           | Priority | Requirement              |
| ----------- | -------------------------------------------------------------- | -------------- | -------- | ------------------------ |
| SCN-LAB-001 | Validate controlled delayed successful API behavior.           | Async          | High     | REQ-LAB-002              |
| SCN-LAB-002 | Validate HTTP 204 scenario.                                    | Error Handling | High     | REQ-LAB-003              |
| SCN-LAB-003 | Validate HTTP 400 scenario.                                    | Error Handling | High     | REQ-LAB-004              |
| SCN-LAB-004 | Validate HTTP 401 scenario.                                    | Error Handling | High     | REQ-LAB-005              |
| SCN-LAB-005 | Validate HTTP 404 scenario.                                    | Error Handling | High     | REQ-LAB-006              |
| SCN-LAB-006 | Validate HTTP 409 scenario.                                    | Error Handling | High     | REQ-LAB-007              |
| SCN-LAB-007 | Validate HTTP 422 scenario and validation information.         | Error Handling | High     | REQ-LAB-008              |
| SCN-LAB-008 | Validate HTTP 429 scenario.                                    | Error Handling | High     | REQ-LAB-009              |
| SCN-LAB-009 | Validate Retry-After header is surfaced for rate limiting.     | Headers        | Medium   | REQ-LAB-010              |
| SCN-LAB-010 | Validate HTTP 500 scenario.                                    | Error Handling | High     | REQ-LAB-011              |
| SCN-LAB-011 | Validate large-response scenario.                              | Edge           | Medium   | REQ-LAB-012              |
| SCN-LAB-012 | Validate delayed DOM element appears after asynchronous delay. | Async          | High     | REQ-LAB-013, REQ-LAB-014 |
| SCN-LAB-013 | Validate modal Cancel behavior.                                | UI             | Medium   | REQ-LAB-015              |
| SCN-LAB-014 | Validate modal Confirm behavior.                               | UI             | Medium   | REQ-LAB-015              |

---

# 14. Compatibility and Responsive Scenarios

| ID          | Scenario                                                              | Type          | Priority | Requirement              |
| ----------- | --------------------------------------------------------------------- | ------------- | -------- | ------------------------ |
| SCN-NFR-001 | Validate critical customer workflow in Chrome.                        | Compatibility | High     | REQ-NFR-001              |
| SCN-NFR-002 | Validate critical customer workflow in Firefox.                       | Compatibility | High     | REQ-NFR-001              |
| SCN-NFR-003 | Validate critical customer workflow in Edge.                          | Compatibility | High     | REQ-NFR-001              |
| SCN-NFR-004 | Validate application layout at desktop viewport size.                 | Responsive    | High     | REQ-NFR-002              |
| SCN-NFR-005 | Validate application layout at tablet viewport size.                  | Responsive    | High     | REQ-NFR-002              |
| SCN-NFR-006 | Validate application layout at mobile viewport size.                  | Responsive    | High     | REQ-NFR-002, REQ-NFR-003 |
| SCN-NFR-007 | Validate long product content does not hide critical controls.        | UI            | Medium   | REQ-NFR-004              |
| SCN-NFR-008 | Validate Unicode content renders correctly across supported browsers. | Compatibility | Medium   | REQ-NFR-005              |

---

# 15. General REST API Scenarios

| ID              | Scenario                                                          | Type     | Priority | Requirement |
| --------------- | ----------------------------------------------------------------- | -------- | -------- | ----------- |
| SCN-API-GEN-001 | Validate API responses use JSON when a response body is expected. | Contract | High     | REQ-API-001 |
| SCN-API-GEN-002 | Validate API CORS behavior.                                       | Headers  | Medium   | REQ-API-002 |
| SCN-API-GEN-003 | Validate unsupported HTTP methods return HTTP 405.                | Negative | High     | REQ-API-003 |
| SCN-API-GEN-004 | Validate HTTP 405 response identifies supported methods.          | Headers  | Medium   | REQ-API-004 |
| SCN-API-GEN-005 | Validate OPTIONS preflight behavior.                              | CORS     | Medium   | REQ-API-005 |
| SCN-API-GEN-006 | Validate unknown API routes return HTTP 404.                      | Negative | High     | REQ-API-006 |

---

# 16. Health API Scenarios

| ID              | Scenario                                                        | Type     | Priority | Requirement     |
| --------------- | --------------------------------------------------------------- | -------- | -------- | --------------- |
| SCN-API-HLT-001 | Validate GET /api/health returns HTTP 200.                      | Positive | Critical | REQ-API-HLT-001 |
| SCN-API-HLT-002 | Validate health response contains expected service information. | Contract | High     | REQ-API-HLT-002 |
| SCN-API-HLT-003 | Validate health response Content-Type and JSON structure.       | Contract | High     | REQ-API-HLT-003 |

---

# 17. Products API Scenarios

| ID               | Scenario                                                  | Type       | Priority | Requirement                        |
| ---------------- | --------------------------------------------------------- | ---------- | -------- | ---------------------------------- |
| SCN-API-PROD-001 | Validate GET /api/products returns products successfully. | Positive   | Critical | REQ-API-PROD-001                   |
| SCN-API-PROD-002 | Validate product filtering by category.                   | Functional | High     | REQ-API-PROD-002                   |
| SCN-API-PROD-003 | Validate product filtering by stock state.                | Functional | High     | REQ-API-PROD-003                   |
| SCN-API-PROD-004 | Validate ascending and descending price sorting.          | Functional | Medium   | REQ-API-PROD-004, REQ-API-PROD-005 |
| SCN-API-PROD-005 | Validate rating sorting.                                  | Functional | Medium   | REQ-API-PROD-006                   |
| SCN-API-PROD-006 | Validate invalid sort values.                             | Negative   | High     | REQ-API-PROD-007                   |
| SCN-API-PROD-007 | Validate product pagination.                              | Functional | High     | REQ-API-PROD-008                   |
| SCN-API-PROD-008 | Validate page-number lower boundary and invalid values.   | Boundary   | High     | REQ-API-PROD-009                   |
| SCN-API-PROD-009 | Validate limit lower and upper boundaries.                | Boundary   | High     | REQ-API-PROD-010                   |
| SCN-API-PROD-010 | Validate GET /api/product using a valid existing ID.      | Positive   | Critical | REQ-API-PROD-011                   |
| SCN-API-PROD-011 | Validate malformed product IDs.                           | Negative   | High     | REQ-API-PROD-012                   |
| SCN-API-PROD-012 | Validate nonexistent product IDs.                         | Negative   | High     | REQ-API-PROD-013                   |

---

# 18. Search API Scenarios

| ID               | Scenario                                                  | Type       | Priority | Requirement      |
| ---------------- | --------------------------------------------------------- | ---------- | -------- | ---------------- |
| SCN-API-SRCH-001 | Validate product search using a valid product-name query. | Positive   | High     | REQ-API-SRCH-001 |
| SCN-API-SRCH-002 | Validate API search across names, brands, and categories. | Functional | High     | REQ-API-SRCH-002 |
| SCN-API-SRCH-003 | Validate missing q parameter.                             | Negative   | High     | REQ-API-SRCH-003 |
| SCN-API-SRCH-004 | Validate empty q parameter.                               | Negative   | High     | REQ-API-SRCH-004 |
| SCN-API-SRCH-005 | Validate query-length maximum boundary.                   | Boundary   | Medium   | REQ-API-SRCH-005 |
| SCN-API-SRCH-006 | Validate API search case-insensitivity.                   | Validation | Medium   | REQ-API-SRCH-006 |

---

# 19. Authentication API Scenarios

| ID               | Scenario                                                               | Type     | Priority | Requirement      |
| ---------------- | ---------------------------------------------------------------------- | -------- | -------- | ---------------- |
| SCN-API-AUTH-001 | Validate authentication using valid credentials.                       | Positive | Critical | REQ-API-AUTH-001 |
| SCN-API-AUTH-002 | Validate authentication when credentials are missing.                  | Negative | High     | REQ-API-AUTH-002 |
| SCN-API-AUTH-003 | Validate authentication using malformed email.                         | Negative | High     | REQ-API-AUTH-003 |
| SCN-API-AUTH-004 | Validate authentication using incorrect credentials.                   | Negative | Critical | REQ-API-AUTH-004 |
| SCN-API-AUTH-005 | Validate successful authentication HTTP status.                        | Contract | Critical | REQ-API-AUTH-005 |
| SCN-API-AUTH-006 | Validate successful authentication returns token information.          | Contract | Critical | REQ-API-AUTH-006 |
| SCN-API-AUTH-007 | Validate successful authentication returns expected user data.         | Contract | High     | REQ-API-AUTH-007 |
| SCN-API-AUTH-008 | Validate authentication response does not expose password information. | Security | Critical | REQ-API-AUTH-007 |

---

# 20. Users API Scenarios

| ID              | Scenario                                                       | Type     | Priority | Requirement                      |
| --------------- | -------------------------------------------------------------- | -------- | -------- | -------------------------------- |
| SCN-API-USR-001 | Validate valid user creation.                                  | Positive | Critical | REQ-API-USR-001                  |
| SCN-API-USR-002 | Validate user first-name and last-name boundaries.             | Boundary | High     | REQ-API-USR-002                  |
| SCN-API-USR-003 | Validate user email format.                                    | Negative | High     | REQ-API-USR-003                  |
| SCN-API-USR-004 | Validate user password complexity.                             | Boundary | High     | REQ-API-USR-004                  |
| SCN-API-USR-005 | Validate user validation failures return HTTP 422.             | Contract | High     | REQ-API-USR-005                  |
| SCN-API-USR-006 | Validate duplicate seeded-user creation returns HTTP 409.      | Negative | High     | REQ-API-USR-006                  |
| SCN-API-USR-007 | Validate successful user creation returns HTTP 201.            | Contract | Critical | REQ-API-USR-007                  |
| SCN-API-USR-008 | Validate user update requires an ID.                           | Negative | High     | REQ-API-USR-008                  |
| SCN-API-USR-009 | Validate user deletion requires an ID and DELETE confirmation. | Negative | Critical | REQ-API-USR-009, REQ-API-USR-010 |
| SCN-API-USR-010 | Validate attempts to delete the seeded account are rejected.   | Negative | High     | REQ-API-USR-011                  |
| SCN-API-USR-011 | Validate successful dynamic-user deletion.                     | Positive | High     | REQ-API-USR-012                  |

---

# 21. Coupons API Scenarios

| ID              | Scenario                                                     | Type       | Priority | Requirement     |
| --------------- | ------------------------------------------------------------ | ---------- | -------- | --------------- |
| SCN-API-CPN-001 | Validate SAVE10 coupon API behavior.                         | Positive   | High     | REQ-API-CPN-002 |
| SCN-API-CPN-002 | Validate FREESHIP coupon API behavior.                       | Positive   | High     | REQ-API-CPN-003 |
| SCN-API-CPN-003 | Validate MIN100 above, at, and below its subtotal threshold. | Boundary   | High     | REQ-API-CPN-004 |
| SCN-API-CPN-004 | Validate EXPIRED coupon rejection.                           | Negative   | Medium   | REQ-API-CPN-005 |
| SCN-API-CPN-005 | Validate unknown coupon rejection.                           | Negative   | High     | REQ-API-CPN-006 |
| SCN-API-CPN-006 | Validate missing coupon information.                         | Negative   | High     | REQ-API-CPN-007 |
| SCN-API-CPN-007 | Validate coupon code case handling.                          | Validation | Medium   | REQ-API-CPN-008 |
| SCN-API-CPN-008 | Validate coupon whitespace normalization.                    | Validation | Medium   | REQ-API-CPN-008 |

---

# 22. Orders API Scenarios

| ID              | Scenario                                                                  | Type        | Priority | Requirement                      |
| --------------- | ------------------------------------------------------------------------- | ----------- | -------- | -------------------------------- |
| SCN-API-ORD-001 | Validate order creation using valid products, shipping, and payment data. | Positive    | Critical | REQ-API-ORD-001                  |
| SCN-API-ORD-002 | Validate order with an empty item collection.                             | Negative    | Critical | REQ-API-ORD-002                  |
| SCN-API-ORD-003 | Validate missing or invalid shipping information.                         | Negative    | Critical | REQ-API-ORD-003                  |
| SCN-API-ORD-004 | Validate missing or invalid payment information.                          | Negative    | Critical | REQ-API-ORD-004                  |
| SCN-API-ORD-005 | Validate order containing an unknown product ID.                          | Negative    | High     | REQ-API-ORD-005                  |
| SCN-API-ORD-006 | Validate order item quantity boundaries.                                  | Boundary    | Critical | REQ-API-ORD-006                  |
| SCN-API-ORD-007 | Validate order quantity exceeding available inventory.                    | Negative    | Critical | REQ-API-ORD-007                  |
| SCN-API-ORD-008 | Validate declined-card API behavior.                                      | Negative    | High     | REQ-API-ORD-008                  |
| SCN-API-ORD-009 | Validate insufficient-funds API behavior.                                 | Negative    | High     | REQ-API-ORD-009                  |
| SCN-API-ORD-010 | Validate successful order returns HTTP 201 and an order ID.               | Contract    | Critical | REQ-API-ORD-010, REQ-API-ORD-011 |
| SCN-API-ORD-011 | Validate returned order subtotal calculation.                             | Calculation | High     | REQ-API-ORD-012                  |
| SCN-API-ORD-012 | Validate returned order currency is USD.                                  | Contract    | Medium   | REQ-API-ORD-013                  |

---

# 23. Reviews API Scenarios

| ID              | Scenario                                                      | Type     | Priority | Requirement     |
| --------------- | ------------------------------------------------------------- | -------- | -------- | --------------- |
| SCN-API-REV-001 | Validate valid product-review creation.                       | Positive | Medium   | REQ-API-REV-001 |
| SCN-API-REV-002 | Validate review using unknown product ID.                     | Negative | High     | REQ-API-REV-002 |
| SCN-API-REV-003 | Validate rating boundaries from 1 through 5 and outside them. | Boundary | High     | REQ-API-REV-003 |
| SCN-API-REV-004 | Validate review-title minimum boundary.                       | Boundary | Medium   | REQ-API-REV-004 |
| SCN-API-REV-005 | Validate review-body minimum and maximum boundaries.          | Boundary | Medium   | REQ-API-REV-005 |
| SCN-API-REV-006 | Validate review email format.                                 | Negative | High     | REQ-API-REV-006 |
| SCN-API-REV-007 | Validate successful review returns HTTP 201.                  | Contract | Medium   | REQ-API-REV-007 |

---

# 24. Contact API Scenarios

| ID              | Scenario                                                                                 | Type     | Priority | Requirement     |
| --------------- | ---------------------------------------------------------------------------------------- | -------- | -------- | --------------- |
| SCN-API-CON-001 | Validate valid contact-request creation.                                                 | Positive | High     | REQ-API-CON-001 |
| SCN-API-CON-002 | Validate invalid and boundary contact-name values.                                       | Boundary | Medium   | REQ-API-CON-002 |
| SCN-API-CON-003 | Validate malformed contact email.                                                        | Negative | High     | REQ-API-CON-003 |
| SCN-API-CON-004 | Validate missing or unsupported contact subject.                                         | Negative | High     | REQ-API-CON-004 |
| SCN-API-CON-005 | Validate contact-message length boundaries.                                              | Boundary | High     | REQ-API-CON-005 |
| SCN-API-CON-006 | Validate invalid contact payload returns controlled validation response.                 | Negative | High     | REQ-API-CON-006 |
| SCN-API-CON-007 | Validate successful contact response.                                                    | Contract | High     | REQ-API-CON-007 |
| SCN-API-CON-008 | Validate additional unexpected properties do not produce an uncontrolled server failure. | Edge     | Medium   | REQ-API-CON-008 |

---

# 25. Edge-Case API Scenarios

| ID               | Scenario                                                                  | Type     | Priority | Requirement      |
| ---------------- | ------------------------------------------------------------------------- | -------- | -------- | ---------------- |
| SCN-API-EDGE-001 | Validate slow-mode response and synchronization behavior.                 | Async    | High     | REQ-API-EDGE-001 |
| SCN-API-EDGE-002 | Validate empty mode returns HTTP 204 without an unexpected response body. | Contract | High     | REQ-API-EDGE-002 |
| SCN-API-EDGE-003 | Validate bad-request mode returns HTTP 400.                               | Negative | High     | REQ-API-EDGE-003 |
| SCN-API-EDGE-004 | Validate unauthorized mode returns HTTP 401.                              | Negative | High     | REQ-API-EDGE-004 |
| SCN-API-EDGE-005 | Validate not-found mode returns HTTP 404.                                 | Negative | High     | REQ-API-EDGE-005 |
| SCN-API-EDGE-006 | Validate conflict mode returns HTTP 409.                                  | Negative | High     | REQ-API-EDGE-006 |
| SCN-API-EDGE-007 | Validate validation mode returns HTTP 422.                                | Negative | High     | REQ-API-EDGE-007 |
| SCN-API-EDGE-008 | Validate rate-limit mode returns HTTP 429.                                | Negative | High     | REQ-API-EDGE-008 |
| SCN-API-EDGE-009 | Validate rate-limit response includes Retry-After: 5.                     | Headers  | Medium   | REQ-API-EDGE-009 |
| SCN-API-EDGE-010 | Validate server-error mode returns HTTP 500.                              | Negative | High     | REQ-API-EDGE-010 |
| SCN-API-EDGE-011 | Validate large-response dataset integrity.                                | Edge     | Medium   | REQ-API-EDGE-011 |
| SCN-API-EDGE-012 | Validate unsupported edge-case modes receive controlled responses.        | Negative | Medium   | REQ-API-EDGE-012 |

---

# 26. Echo API Scenarios

| ID               | Scenario                                                                            | Type     | Priority | Requirement      |
| ---------------- | ----------------------------------------------------------------------------------- | -------- | -------- | ---------------- |
| SCN-API-ECHO-001 | Validate basic request to the echo endpoint.                                        | Positive | Medium   | REQ-API-ECHO-001 |
| SCN-API-ECHO-002 | Validate supported HTTP methods against the echo endpoint.                          | Method   | Medium   | REQ-API-ECHO-002 |
| SCN-API-ECHO-003 | Validate query parameters sent to the echo endpoint.                                | Contract | Medium   | REQ-API-ECHO-003 |
| SCN-API-ECHO-004 | Validate custom request headers sent to the echo endpoint.                          | Headers  | Medium   | REQ-API-ECHO-004 |
| SCN-API-ECHO-005 | Validate JSON request bodies sent to the echo endpoint.                             | Contract | Medium   | REQ-API-ECHO-005 |
| SCN-API-ECHO-006 | Validate empty request bodies.                                                      | Edge     | Medium   | REQ-API-ECHO-005 |
| SCN-API-ECHO-007 | Validate malformed or unexpected request content is handled in a controlled manner. | Negative | Medium   | REQ-API-ECHO-006 |

---

# 27. End-to-End Business Scenarios

| ID          | Scenario                                                                                  | Type        | Priority | Requirement                                                 |
| ----------- | ----------------------------------------------------------------------------------------- | ----------- | -------- | ----------------------------------------------------------- |
| SCN-E2E-001 | Register → Browse → Add Product → Checkout → Successful Order.                            | End-to-End  | Critical | REQ-REG-001, REQ-CART-001, REQ-CHK-020                      |
| SCN-E2E-002 | Login → Search → Add Product → Apply SAVE10 → Checkout → Successful Order.                | End-to-End  | Critical | REQ-AUTH-001, REQ-PROD-008, REQ-CPN-004, REQ-CHK-020        |
| SCN-E2E-003 | Login → Add Multiple Products → Change Quantities → Remove Product → Checkout.            | End-to-End  | Critical | REQ-CART-001, REQ-CART-010, REQ-CART-012, REQ-CHK-020       |
| SCN-E2E-004 | Register → Logout → Login → Account Delete → Verify Login Rejected.                       | End-to-End  | Critical | REQ-REG-001, REQ-ACC-003, REQ-ACC-007                       |
| SCN-E2E-005 | Browse → Add Cart Item → Attempt Checkout Unauthenticated → Login → Resume Checkout.      | End-to-End  | Critical | REQ-CART-016, REQ-CHK-001, REQ-AUTH-001                     |
| SCN-E2E-006 | Create user through API → Login through UI → Complete checkout → Delete user through API. | Integration | High     | REQ-API-USR-001, REQ-AUTH-001, REQ-CHK-020, REQ-API-USR-012 |

---

# 28. Scenario Coverage Categories

The catalog includes scenarios covering:

* Positive testing
* Negative testing
* Boundary-value analysis
* Equivalence partitioning
* Functional testing
* Integration testing
* End-to-end testing
* State-transition testing
* Error handling
* Asynchronous behavior
* API contract testing
* HTTP status testing
* Response-header testing
* Calculation validation
* Cross-browser testing
* Responsive testing
* Unicode content
* Long content
* File validation
* Inventory boundaries
* Financial boundaries
* Session behavior

---

# 29. Automation Planning

UI scenarios will later be evaluated for implementation using:

* Selenium
* Playwright
* Cypress

API scenarios will later be evaluated for implementation using:

* Postman/Newman
* Cypress

Not every scenario must necessarily be automated.

Automation eligibility will be recorded in the detailed test-case and traceability phases.

---

# 30. Next Phase

The next phase converts these high-level scenarios into detailed test cases containing:

* Test Case ID
* Scenario ID
* Requirement ID
* Title
* Preconditions
* Test Data
* Detailed Steps
* Expected Result
* Priority
* Test Type
* Automation Candidate
* Execution Status
* Actual Result
* Defect Reference
