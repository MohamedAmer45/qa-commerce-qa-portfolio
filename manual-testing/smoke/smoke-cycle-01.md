# QA Commerce Lab — Smoke Test Cycle 01

## Execution Information

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Base URL:** https://qa-commerce-lab.vercel.app
**Execution Type:** Manual Smoke Testing
**Cycle:** Smoke Cycle 01
**Execution Status:** Not Started

---

## Objective

Verify that the most critical QA Commerce Lab business workflows are operational before executing the broader regression suite.

The smoke suite covers:

* Application availability
* Navigation
* Login
* Registration
* Product browsing
* Product search
* Cart
* Coupons
* Checkout
* Account logout
* Contact submission

---

## Test Environment

**Operating System:** Windows 11
**Primary Browser:** Google Chrome
**Application URL:** https://qa-commerce-lab.vercel.app

### Seed Test Account

**Email:** `qa.user@example.com`
**Password:** `Password123!`

---

## Test Data Rules

For registration tests, use a unique email address.

Example:

`smoke.qa.001@example.com`

If the account already exists, increment the number:

`smoke.qa.002@example.com`

Do not use the seeded QA account for destructive account tests.

---

# Smoke Test Suite

| #  | Test Case ID | Test                                 | Priority | Result  | Actual Result | Defect |
| -- | ------------ | ------------------------------------ | -------- | ------- | ------------- | ------ |
| 1  | TC-NAV-001   | Global navigation is displayed       | Critical | Not Run | —             | —      |
| 2  | TC-AUTH-001  | Login using valid seeded credentials | Critical | Not Run | —             | —      |
| 3  | TC-PROD-001  | Display complete product catalog     | Critical | Not Run | —             | —      |
| 4  | TC-PROD-005  | Search using valid product name      | High     | Not Run | —             | —      |
| 5  | TC-CART-001  | Add available product to empty cart  | Critical | Not Run | —             | —      |
| 6  | TC-CART-016  | Update product quantity              | Critical | Not Run | —             | —      |
| 7  | TC-CPN-001   | Apply SAVE10 coupon                  | High     | Not Run | —             | —      |
| 8  | TC-CHK-033   | Complete successful checkout         | Critical | Not Run | —             | —      |
| 9  | TC-CHK-035   | Display order reference              | High     | Not Run | —             | —      |
| 10 | TC-CHK-036   | Clear cart after successful purchase | Critical | Not Run | —             | —      |
| 11 | TC-ACC-003   | Logout successfully                  | Critical | Not Run | —             | —      |
| 12 | TC-REG-001   | Register a valid new account         | Critical | Not Run | —             | —      |
| 13 | TC-CON-001   | Submit valid contact request         | High     | Not Run | —             | —      |

---

# Execution Order

## SMOKE-01 — Navigation

Execute:

`TC-NAV-001`

### Verify

* Application loads.
* Navigation bar appears.
* Main navigation controls are visible.
* No blank page or fatal application error occurs.

---

## SMOKE-02 — Authentication

Execute:

`TC-AUTH-001`

### Test Data

Email:

`qa.user@example.com`

Password:

`Password123!`

### Verify

* Login succeeds.
* User enters authenticated state.
* Account/user navigation becomes available.

---

## SMOKE-03 — Product Catalog

Execute:

`TC-PROD-001`

### Verify

* Products page loads.
* Product cards are displayed.
* Catalog contains the expected seeded products.
* Page does not display a fatal loading error.

---

## SMOKE-04 — Product Search

Execute:

`TC-PROD-005`

### Test Data

`Pulse 75 Mechanical Keyboard`

### Verify

* Search returns the expected keyboard product.
* Unrelated products are filtered out.
* Search remains responsive.

---

## SMOKE-05 — Add to Cart

Execute:

`TC-CART-001`

### Test Data

Use an available product such as:

`Echo Mini Speaker`

### Verify

* Product is added.
* Quantity starts at 1.
* Cart indicator updates.
* Product appears in Cart.

---

## SMOKE-06 — Update Cart Quantity

Execute:

`TC-CART-016`

### Steps

1. Open Cart.
2. Change quantity from 1 to 2.

### Verify

* Quantity updates successfully.
* Line total updates.
* Cart total updates.
* Cart counter remains consistent.

---

## SMOKE-07 — Apply Coupon

Execute:

`TC-CPN-001`

### Test Data

`SAVE10`

### Verify

* Coupon is accepted.
* 10% discount is displayed.
* Order total is recalculated correctly.

---

## SMOKE-08 — Successful Checkout

Execute:

`TC-CHK-033`

### Test Data

Card:

`4242424242424242`

Expiry:

`12/30`

CVV:

`123`

Use valid values for all required shipping fields.

### Verify

* Place Order can be selected.
* Processing state is displayed.
* Order completes successfully.
* Confirmation page/state is displayed.

---

## SMOKE-09 — Order Reference

Execute:

`TC-CHK-035`

### Verify

Successful checkout displays:

`ORD-QA-1001`

---

## SMOKE-10 — Cart Cleared

Execute:

`TC-CHK-036`

### Verify

After successful checkout:

* Cart is empty.
* Cart indicator returns to 0.

---

## SMOKE-11 — Logout

Execute:

`TC-ACC-003`

### Verify

* Logout succeeds.
* User becomes unauthenticated.
* Navigation displays Sign In again.

---

## SMOKE-12 — Registration

Execute:

`TC-REG-001`

### Example Test Data

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

Accept Terms:

`Yes`

### Verify

* Registration succeeds.
* User is automatically authenticated.
* User is redirected to Account.

---

## SMOKE-13 — Contact Request

Execute:

`TC-CON-001`

### Test Data

Name:

`QA Tester`

Email:

`qa@example.com`

Subject:

`Technical problem`

Message:

`This is a smoke test support request for QA Commerce Lab.`

### Verify

Submission succeeds and the application displays:

`Support request accepted.`

---

# Result Definitions

## Pass

Actual behavior matches the expected result.

## Fail

Actual behavior differs from the expected result.

A defect should be created when appropriate.

## Blocked

The case cannot be executed because another problem prevents execution.

Example:

Checkout cannot be tested because Login is completely unavailable.

## Skipped

The case was intentionally excluded from the current execution cycle.

---

# Evidence Rules

Store screenshots under:

`manual-testing/evidence/smoke-cycle-01/`

Recommended filename format:

`<test-case-id>-<short-description>.png`

Examples:

`TC-AUTH-001-valid-login.png`

`TC-CPN-001-save10-applied.png`

`TC-CHK-033-successful-checkout.png`

For failed cases, capture evidence whenever possible.

Example:

`TC-CART-016-quantity-failure.png`

---

# Defect Rules

When a smoke test fails:

1. Do not change the expected result.
2. Set the test result to `Fail`.
3. Record the observed behavior under Actual Result.
4. Create a defect report.
5. Add the defect ID to the test execution table.
6. Capture evidence when useful.

Example:

**Result:** Fail
**Actual Result:** Cart quantity changed to 2, but subtotal remained calculated for quantity 1.
**Defect:** BUG-CART-001

---

# Smoke Exit Criteria

Smoke Cycle 01 passes when:

* All Critical smoke tests have been executed.
* No Critical smoke test remains Failed without evaluation.
* Login works.
* Products can be browsed.
* Products can be added to Cart.
* Cart calculations function.
* Checkout can complete successfully.
* Successful orders produce confirmation.
* No blocker prevents the primary purchase journey.

---

# Execution Summary

**Total Smoke Cases:** 13

**Passed:** 0
**Failed:** 0
**Blocked:** 0
**Skipped:** 0
**Not Run:** 13

## Overall Smoke Status

`NOT STARTED`

## Notes

Update this section after the execution cycle is complete.
