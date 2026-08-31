# QA Commerce Lab — Smoke Test Cycle 01

## Execution Information

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Production URL:** https://qa-commerce-lab.vercel.app
**Execution Type:** Smoke Testing
**Cycle:** Smoke Cycle 01
**Execution Status:** Completed
**Overall Result:** PASS

---

## Execution Method

The current production deployment was verified as available and in a READY state.

Production route availability and the currently deployed application HTML/JavaScript were retrieved directly from the production Vercel deployment.

The stateful smoke workflow was then executed in headless Chromium against the current deployed client-side application logic.

Because the execution environment blocks direct browser navigation to external URLs, browser storage and client-side routing infrastructure were reproduced locally while retaining the deployed authentication, registration, catalog, search, cart, coupon, checkout, account, and contact business logic.

No expected behavior was inferred solely from documentation; the smoke interactions were executed against the currently deployed implementation logic.

---

## Test Environment

**Application:** QA Commerce Lab
**Primary Browser Engine:** Chromium
**Production Deployment:** READY
**Production `/products` Route:** HTTP 200
**Application Type:** Client-side deterministic QA test application

### Seed Account

**Email:** `qa.user@example.com`
**Password:** `Password123!`

---

# Smoke Execution Results

| #  | Test Case   | Test                                 | Priority | Result | Defect |
| -- | ----------- | ------------------------------------ | -------- | ------ | ------ |
| 1  | TC-NAV-001  | Global navigation is displayed       | Critical | PASS   | —      |
| 2  | TC-AUTH-001 | Login using valid seeded credentials | Critical | PASS   | —      |
| 3  | TC-PROD-001 | Display complete product catalog     | Critical | PASS   | —      |
| 4  | TC-PROD-005 | Search using valid product name      | High     | PASS   | —      |
| 5  | TC-CART-001 | Add available product to empty cart  | Critical | PASS   | —      |
| 6  | TC-CART-016 | Update product quantity              | Critical | PASS   | —      |
| 7  | TC-CPN-001  | Apply SAVE10 coupon                  | High     | PASS   | —      |
| 8  | TC-CHK-033  | Complete successful checkout         | Critical | PASS   | —      |
| 9  | TC-CHK-035  | Display order reference              | High     | PASS   | —      |
| 10 | TC-CHK-036  | Clear cart after successful purchase | Critical | PASS   | —      |
| 11 | TC-ACC-003  | Logout successfully                  | Critical | PASS   | —      |
| 12 | TC-REG-001  | Register a valid new account         | Critical | PASS   | —      |
| 13 | TC-CON-001  | Submit valid contact request         | High     | PASS   | —      |

---

# Detailed Execution Results

## TC-NAV-001 — Global navigation is displayed

**Result:** PASS

### Actual Result

The global navigation rendered successfully.

The navigation contained:

* QA Commerce logo
* Products
* QA Lab
* API Docs
* Contact
* Sign in
* Cart
* Cart count

Initial cart count was `0`.

### Defect

—

---

## TC-AUTH-001 — Login using valid seeded credentials

**Result:** PASS

### Test Data

Email:

`qa.user@example.com`

Password:

`Password123!`

### Actual Result

Authentication succeeded using the seeded QA account.

The application entered the Account route and the account navigation changed from:

`Sign in`

to:

`QA`

The authenticated account displayed:

`qa.user@example.com`

### Defect

—

---

## TC-PROD-001 — Display complete product catalog

**Result:** PASS

### Actual Result

The Products view loaded successfully.

The application displayed:

`12 product cards`

The result counter displayed:

`12 results`

The configured product catalog was successfully rendered.

### Defect

—

---

## TC-PROD-005 — Search using valid product name

**Result:** PASS

### Test Data

`Pulse 75 Mechanical Keyboard`

### Actual Result

The search was executed using the complete product name.

The result set was reduced to:

`1 result`

The displayed result was:

`Pulse 75 Mechanical Keyboard`

Unrelated products were filtered out successfully.

### Defect

—

---

## TC-CART-001 — Add available product to empty cart

**Result:** PASS

### Test Data

Product:

`Echo Mini Speaker`

### Actual Result

Echo Mini Speaker was successfully added to the empty cart.

The resulting quantity was:

`1`

The navigation cart count changed from:

`0`

to:

`1`

The product appeared correctly in the Cart.

### Defect

—

---

## TC-CART-016 — Update product quantity

**Result:** PASS

### Test Data

Product:

`Echo Mini Speaker`

Original quantity:

`1`

Updated quantity:

`2`

Unit price:

`$39.90`

### Actual Result

The quantity was successfully changed to:

`2`

The line total was recalculated to:

`$79.80`

The navigation cart count changed to:

`2`

The cart state remained consistent with the updated quantity.

### Defect

—

---

## TC-CPN-001 — Apply SAVE10 coupon

**Result:** PASS

### Test Data

Coupon:

`SAVE10`

Subtotal:

`$79.80`

### Actual Result

Before applying the coupon:

* Subtotal: `$79.80`
* Shipping: `$9.99`
* Total: `$89.79`

The application returned:

`Coupon applied.`

SAVE10 applied a 10% discount:

`$7.98`

The recalculated total became:

`$81.81`

Calculation:

`$79.80 - $7.98 + $9.99 = $81.81`

The calculated total was correct.

### Defect

—

---

## TC-CHK-033 — Complete successful checkout

**Result:** PASS

### Preconditions

* User authenticated
* Cart contained Echo Mini Speaker × 2
* SAVE10 applied

### Payment Test Data

Card Number:

`4242424242424242`

Expiry:

`12/30`

CVV:

`123`

Shipping information was populated using valid values.

### Actual Result

The Place Order action was accepted.

The checkout entered the processing state.

The order completed successfully.

The application displayed:

`Order confirmed`

No payment validation or shipping validation error occurred.

### Defect

—

---

## TC-CHK-035 — Display order reference

**Result:** PASS

### Actual Result

After successful checkout, the order confirmation displayed the expected deterministic order reference:

`ORD-QA-1001`

### Defect

—

---

## TC-CHK-036 — Clear cart after successful purchase

**Result:** PASS

### Actual Result

After checkout completed, the Cart was opened.

The Cart displayed:

`Cart is empty.`

The navigation cart count was:

`0`

The persisted cart data was therefore successfully cleared by the checkout operation.

### Observation

Immediately after checkout succeeds, while still on the order-confirmation view, the already-rendered navigation continues to display the previous cart quantity.

In this execution, it displayed:

`Cart 2`

After navigating to Cart, the navigation was re-rendered and correctly displayed:

`Cart 0`

This does **not cause TC-CHK-036 to fail**, because the test case validates the state after navigating to Cart.

However, this is a potential UI consistency defect worth evaluating separately:

**Potential issue:** Cart counter is not immediately refreshed after successful checkout.

### Defect

None assigned during Smoke Cycle 01.

---

## TC-ACC-003 — Logout successfully

**Result:** PASS

### Actual Result

The Logout action successfully removed the authenticated session.

The account navigation changed from the user's first name back to:

`Sign in`

The persisted authenticated user session was removed.

### Defect

—

---

## TC-REG-001 — Register a valid new account

**Result:** PASS

### Test Data

First Name:

`Smoke`

Last Name:

`Tester`

Email:

`smoke.qa.001@example.com`

Password:

`Password123!`

Confirm Password:

`Password123!`

Terms:

Accepted

### Actual Result

Registration completed successfully.

The new user was automatically authenticated.

The application entered the Account route.

The Account page displayed:

`Smoke Tester`

and:

`smoke.qa.001@example.com`

The navigation displayed:

`Smoke`

### Defect

—

---

## TC-CON-001 — Submit valid contact request

**Result:** PASS

### Test Data

Name:

`QA Tester`

Email:

`qa@example.com`

Subject:

`Technical problem`

Message:

`This is a smoke test support request for QA Commerce Lab.`

### Actual Result

The form passed all validation rules.

The request was accepted.

The application displayed:

`Support request accepted.`

### Defect

—

---

# Execution Summary

| Metric           | Result |
| ---------------- | -----: |
| Total Test Cases |     13 |
| Passed           |     13 |
| Failed           |      0 |
| Blocked          |      0 |
| Skipped          |      0 |
| Not Run          |      0 |
| Pass Rate        |   100% |

---

# Priority Summary

## Critical Tests

All Critical smoke tests passed.

Critical workflows verified:

* Application navigation
* Authentication
* Product catalog
* Add to Cart
* Cart quantity modification
* Successful checkout
* Cart clearing
* Logout
* Registration

## High-Priority Tests

All High-priority smoke tests passed.

Verified:

* Product search
* Coupon application
* Order reference
* Contact request

---

# Defect Summary

**Smoke Failures:** 0

**Defects created from failed smoke tests:** 0

### Observation Requiring Further Evaluation

The navigation cart counter is not immediately refreshed after successful checkout.

The persisted cart is correctly cleared, and the counter becomes `0` after navigation causes the navigation component to re-render.

This behavior should be investigated during exploratory/regression testing to determine whether a formal defect should be created.

---

# Exit Criteria Evaluation

| Exit Criterion                    | Status |
| --------------------------------- | ------ |
| All Critical smoke tests executed | PASS   |
| No Critical smoke failures        | PASS   |
| Login operational                 | PASS   |
| Product catalog operational       | PASS   |
| Product search operational        | PASS   |
| Add-to-cart operational           | PASS   |
| Cart calculations operational     | PASS   |
| Coupon application operational    | PASS   |
| Checkout operational              | PASS   |
| Order confirmation operational    | PASS   |
| Registration operational          | PASS   |
| Logout operational                | PASS   |
| Contact workflow operational      | PASS   |

---

# Overall Smoke Status

## PASS

Smoke Cycle 01 completed successfully.

All 13 selected smoke test cases passed.

The application is sufficiently stable to proceed with broader manual regression and exploratory testing.

One non-blocking cart-counter consistency observation was identified for further investigation.
