# QA Commerce Lab — Manual Regression Cycle 01

## Execution Information

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**URL:** https://qa-commerce-lab.vercel.app
**Execution Type:** Manual Regression Testing
**Cycle:** Regression Cycle 01
**Execution Status:** Completed
**Overall Status:** COMPLETED WITH DEFECTS

---

# Objective

Verify that the major QA Commerce Lab features continue to behave correctly after the successful smoke cycle.

Regression Cycle 01 focuses on:

* Authentication
* Registration
* Products
* Cart
* Coupons
* Checkout
* Account
* Contact
* QA Lab
* Navigation and state persistence
* Important negative and boundary conditions

---

# Environment

**Operating System:** Windows 11
**Primary Browser:** Chromium / Google Chrome-compatible browser

## Seed Account

**Email:** `qa.user@example.com`

**Password:** `Password123!`

---

# Regression Test Suite

## Authentication

| Test Case   | Description                     | Priority | Result | Defect |
| ----------- | ------------------------------- | -------: | ------ | ------ |
| TC-AUTH-001 | Valid login                     | Critical | PASS   | —      |
| TC-AUTH-003 | Uppercase email login           |     High | PASS   | —      |
| TC-AUTH-004 | Leading email whitespace        |   Medium | PASS   | —      |
| TC-AUTH-006 | Wrong password capitalization   |     High | PASS   | —      |
| TC-AUTH-007 | Empty email                     |     High | PASS   | —      |
| TC-AUTH-008 | Empty password                  |     High | PASS   | —      |
| TC-AUTH-010 | Email missing @                 |     High | PASS   | —      |
| TC-AUTH-013 | Incorrect email                 |     High | PASS   | —      |
| TC-AUTH-014 | Valid email with wrong password | Critical | PASS   | —      |
| TC-AUTH-018 | Session survives refresh        |     High | PASS   | —      |

### Authentication Summary

**Executed:** 10
**Passed:** 10
**Failed:** 0
**Blocked:** 0

---

## Registration

| Test Case  | Description                        | Priority | Result | Defect |
| ---------- | ---------------------------------- | -------: | ------ | ------ |
| TC-REG-001 | Valid registration                 | Critical | PASS   | —      |
| TC-REG-002 | Empty first name                   |     High | PASS   | —      |
| TC-REG-003 | Empty last name                    |     High | PASS   | —      |
| TC-REG-013 | Duplicate email                    |     High | PASS   | —      |
| TC-REG-014 | Duplicate email case variation     |     High | PASS   | —      |
| TC-REG-015 | Password length 7                  |     High | PASS   | —      |
| TC-REG-016 | Password length exactly 8          |     High | PASS   | —      |
| TC-REG-019 | Password missing uppercase         |     High | PASS   | —      |
| TC-REG-020 | Password missing lowercase         |     High | PASS   | —      |
| TC-REG-021 | Password missing number            |     High | PASS   | —      |
| TC-REG-022 | Password missing special character |     High | PASS   | —      |
| TC-REG-023 | Password confirmation mismatch     |     High | PASS   | —      |
| TC-REG-025 | Terms not accepted                 |     High | PASS   | —      |
| TC-REG-028 | Unicode names                      |   Medium | PASS   | —      |

### Registration Summary

**Executed:** 14
**Passed:** 14
**Failed:** 0
**Blocked:** 0

---

## Products

| Test Case   | Description                  | Priority | Result | Defect |
| ----------- | ---------------------------- | -------: | ------ | ------ |
| TC-PROD-001 | Complete product catalog     | Critical | PASS   | —      |
| TC-PROD-004 | Three-decimal price rounding |     High | PASS   | —      |
| TC-PROD-006 | Partial-name search          |     High | PASS   | —      |
| TC-PROD-008 | Case-insensitive search      |   Medium | PASS   | —      |
| TC-PROD-009 | Search whitespace trimming   |   Medium | PASS   | —      |
| TC-PROD-010 | No-match search              |     High | PASS   | —      |
| TC-PROD-011 | Category filter              |     High | PASS   | —      |
| TC-PROD-014 | Price ascending              |   Medium | PASS   | —      |
| TC-PROD-015 | Price descending             |   Medium | PASS   | —      |
| TC-PROD-016 | Out-of-stock product         | Critical | PASS   | —      |
| TC-PROD-018 | Zero-price product           |   Medium | PASS   | —      |
| TC-PROD-019 | High-value product           |   Medium | PASS   | —      |
| TC-PROD-020 | Unicode product name         |   Medium | PASS   | —      |
| TC-PROD-022 | Long product name            |   Medium | PASS   | —      |

### Products Summary

**Executed:** 14
**Passed:** 14
**Failed:** 0
**Blocked:** 0

---

## Cart

| Test Case   | Description                     | Priority | Result | Defect       |
| ----------- | ------------------------------- | -------: | ------ | ------------ |
| TC-CART-001 | Add product                     | Critical | PASS   | —            |
| TC-CART-003 | Add same product twice          |     High | PASS   | —            |
| TC-CART-004 | Cannot add out-of-stock product | Critical | PASS   | —            |
| TC-CART-006 | Quantity 0                      | Critical | PASS   | —            |
| TC-CART-007 | Negative quantity               | Critical | PASS   | —            |
| TC-CART-008 | Decimal quantity                | Critical | PASS   | —            |
| TC-CART-009 | Quantity equals stock           | Critical | PASS   | —            |
| TC-CART-010 | Quantity greater than stock     | Critical | PASS   | —            |
| TC-CART-012 | Sticker Pack quantity 25        |   Medium | PASS   | —            |
| TC-CART-013 | Sticker Pack quantity 26        |   Medium | FAIL   | BUG-CART-002 |
| TC-CART-015 | Line total calculation          |     High | PASS   | —            |
| TC-CART-020 | Multiple-product subtotal       | Critical | PASS   | —            |
| TC-CART-023 | Cart persists after refresh     |     High | PASS   | —            |

### Cart Summary

**Executed:** 13
**Passed:** 12
**Failed:** 1
**Blocked:** 0

### Cart Failure

`TC-CART-013` failed because the QA Sticker Pack quantity can be changed to `26` from the Cart even though its intended maximum quantity is `25`.

**Defect:** `BUG-CART-002`

---

## Coupons

| Test Case  | Description                   | Priority | Result  | Defect |
| ---------- | ----------------------------- | -------: | ------- | ------ |
| TC-CPN-001 | SAVE10                        |     High | PASS    | —      |
| TC-CPN-002 | Lowercase SAVE10              |   Medium | PASS    | —      |
| TC-CPN-004 | Whitespace around coupon      |   Medium | PASS    | —      |
| TC-CPN-005 | FREESHIP                      |     High | PASS    | —      |
| TC-CPN-008 | MIN100 below threshold        |     High | PASS    | —      |
| TC-CPN-009 | EXPIRED coupon                |   Medium | PASS    | —      |
| TC-CPN-010 | Unknown coupon                |     High | PASS    | —      |
| TC-CPN-012 | Free shipping at exactly $150 |     High | BLOCKED | —      |
| TC-CPN-014 | Shipping below $150           |     High | PASS    | —      |

### Coupons Summary

**Planned:** 9
**Executed:** 8
**Passed:** 8
**Failed:** 0
**Blocked:** 1

### Blocked Test — TC-CPN-012

**Test:** Automatic free shipping at exactly `$150.00`

**Status:** BLOCKED

**Reason:**

The current catalog prices and valid integer stock quantities cannot produce an exact `$150.00` subtotal.

The required boundary therefore cannot be reached using the currently available application test data.

This is a **test-data limitation**, not an application defect.

---

## Checkout

| Test Case  | Description                 | Priority | Result | Defect |
| ---------- | --------------------------- | -------: | ------ | ------ |
| TC-CHK-001 | Unauthenticated checkout    | Critical | PASS   | —      |
| TC-CHK-002 | Empty-cart checkout         | Critical | PASS   | —      |
| TC-CHK-009 | Address length 4            |   Medium | PASS   | —      |
| TC-CHK-010 | Address length 5            |   Medium | PASS   | —      |
| TC-CHK-014 | Card below minimum length   | Critical | PASS   | —      |
| TC-CHK-017 | Card above maximum length   | Critical | PASS   | —      |
| TC-CHK-018 | Valid Luhn card             | Critical | PASS   | —      |
| TC-CHK-019 | Invalid Luhn card           | Critical | PASS   | —      |
| TC-CHK-020 | Card spaces                 |     High | PASS   | —      |
| TC-CHK-023 | Valid expiry format         |     High | PASS   | —      |
| TC-CHK-024 | Invalid expiry format       |     High | PASS   | —      |
| TC-CHK-026 | CVV length 2                | Critical | PASS   | —      |
| TC-CHK-027 | CVV length 3                | Critical | PASS   | —      |
| TC-CHK-028 | CVV length 4                | Critical | PASS   | —      |
| TC-CHK-029 | CVV length 5                | Critical | PASS   | —      |
| TC-CHK-031 | Declined card               |     High | PASS   | —      |
| TC-CHK-032 | Insufficient funds          |     High | PASS   | —      |
| TC-CHK-033 | Successful checkout         | Critical | PASS   | —      |
| TC-CHK-034 | Duplicate order submission  | Critical | PASS   | —      |
| TC-CHK-036 | Cart cleared after checkout | Critical | PASS   | —      |

### Checkout Summary

**Executed:** 20
**Passed:** 20
**Failed:** 0
**Blocked:** 0

### Additional Exploratory Finding

Although all selected scripted Checkout regression cases passed, exploratory testing identified invalid semantic expiry-date validation.

See:

`BUG-CHK-001 — Checkout Accepts Invalid and Expired Card Expiry Dates`

---

## Account

| Test Case  | Description                   | Priority | Result | Defect |
| ---------- | ----------------------------- | -------: | ------ | ------ |
| TC-ACC-003 | Logout                        | Critical | PASS   | —      |
| TC-ACC-005 | Exact DELETE confirmation     | Critical | PASS   | —      |
| TC-ACC-006 | Lowercase delete              | Critical | PASS   | —      |
| TC-ACC-007 | Delete with whitespace        | Critical | PASS   | —      |
| TC-ACC-010 | Delete dynamic user           | Critical | PASS   | —      |
| TC-ACC-012 | Deleted account cannot login  | Critical | PASS   | —      |
| TC-ACC-013 | Seed account remains reusable |     High | PASS   | —      |

### Account Summary

**Executed:** 7
**Passed:** 7
**Failed:** 0
**Blocked:** 0

---

## Contact

| Test Case  | Description            | Priority | Result | Defect |
| ---------- | ---------------------- | -------: | ------ | ------ |
| TC-CON-001 | Valid support request  |     High | PASS   | —      |
| TC-CON-003 | One-character name     |   Medium | PASS   | —      |
| TC-CON-004 | Two-character name     |   Medium | PASS   | —      |
| TC-CON-005 | Invalid email          |     High | PASS   | —      |
| TC-CON-006 | Empty subject          |     High | PASS   | —      |
| TC-CON-008 | Message length 19      |     High | PASS   | —      |
| TC-CON-009 | Message length 20      |     High | PASS   | —      |
| TC-CON-015 | Unsupported attachment |     High | PASS   | —      |
| TC-CON-018 | Attachment above 2 MB  |     High | PASS   | —      |

### Contact Summary

**Executed:** 9
**Passed:** 9
**Failed:** 0
**Blocked:** 0

---

## QA Lab

| Test Case  | Description         | Priority | Result | Defect |
| ---------- | ------------------- | -------: | ------ | ------ |
| TC-LAB-001 | Slow API response   |     High | PASS   | —      |
| TC-LAB-002 | HTTP 204            |     High | PASS   | —      |
| TC-LAB-003 | HTTP 400            |     High | PASS   | —      |
| TC-LAB-004 | HTTP 401            |     High | PASS   | —      |
| TC-LAB-005 | HTTP 404            |     High | PASS   | —      |
| TC-LAB-006 | HTTP 409            |     High | PASS   | —      |
| TC-LAB-007 | HTTP 422            |     High | PASS   | —      |
| TC-LAB-008 | HTTP 429            |     High | PASS   | —      |
| TC-LAB-009 | Retry-After header  |   Medium | PASS   | —      |
| TC-LAB-010 | HTTP 500            |     High | PASS   | —      |
| TC-LAB-011 | Large response      |   Medium | PASS   | —      |
| TC-LAB-012 | Delayed DOM element |     High | PASS   | —      |
| TC-LAB-013 | Modal Cancel        |   Medium | PASS   | —      |
| TC-LAB-014 | Modal Confirm       |   Medium | PASS   | —      |

### QA Lab Summary

**Executed:** 14
**Passed:** 14
**Failed:** 0
**Blocked:** 0

---

# QA Lab Production API Verification

The QA Lab API edge conditions were additionally verified against the deployed production API.

The following responses were confirmed:

| Mode         | Expected | Actual | Result |
| ------------ | -------: | -----: | ------ |
| slow         |      200 |    200 | PASS   |
| empty        |      204 |    204 | PASS   |
| bad-request  |      400 |    400 | PASS   |
| unauthorized |      401 |    401 | PASS   |
| not-found    |      404 |    404 | PASS   |
| conflict     |      409 |    409 | PASS   |
| validation   |      422 |    422 | PASS   |
| rate-limit   |      429 |    429 | PASS   |
| server-error |      500 |    500 | PASS   |
| large        |      200 |    200 | PASS   |

The rate-limit response returned:

`Retry-After: 5`

The large-response mode returned:

`250 records`

The server-error response returned a controlled JSON error rather than an uncontrolled application crash.

---

# Exploratory Testing Results

## EXP-001 — Cart Counter After Successful Checkout

### Objective

Investigate the cart-counter observation identified during Smoke Cycle 01.

### Steps

1. Add two units of an available product.
2. Complete a successful checkout.
3. Remain on the confirmation state.
4. Inspect the navigation Cart counter.

### Expected Result

Immediately after successful checkout:

* Cart storage should be cleared.
* Navigation Cart counter should update to `0`.

### Actual Result

The underlying Cart is cleared correctly.

However, while remaining on the Order Confirmation view, the navigation continues displaying the previous quantity.

Example:

`Cart 2`

After another navigation event occurs, the navigation re-renders and correctly displays:

`Cart 0`

### Status

**FAIL**

### Defect

`BUG-CART-001`

---

## EXP-002 — Sticker Pack Special Quantity Limit

### Objective

Investigate consistency between Add-to-Cart quantity restrictions and Cart quantity editing.

### Product

**QA Sticker Pack**

**Stock:** `500`

**Intended maximum quantity:** `25`

### Steps

1. Add the QA Sticker Pack to Cart.
2. Open Cart.
3. Change the quantity to `25`.
4. Verify it is accepted.
5. Change quantity to `26`.

### Expected Result

Quantity `26` should be rejected because the business maximum is `25`.

### Actual Result

The Cart accepts quantity:

`26`

The Cart quantity editor is validating against stock `500` instead of the special maximum quantity `25`.

### Status

**FAIL**

### Defect

`BUG-CART-002`

### Related Scripted Test

`TC-CART-013`

---

## EXP-003 — Expiry-Date Validation

### Objective

Determine whether Checkout validates the actual card expiration date or only its `MM/YY` format.

### Values Tested

* `00/30`
* `13/30`
* `01/20`
* `99/99`

### Expected Result

Checkout should reject:

* Month `00`
* Month greater than `12`
* Expired dates
* Semantically invalid expiry dates

### Actual Result

All tested values were accepted as long as they matched the two-digit/two-digit format.

The checkout reached:

`Order confirmed`

for invalid and expired values.

### Status

**FAIL**

### Defect

`BUG-CHK-001`

---

# Defects Identified

| Defect ID    | Title                                                               | Severity    | Priority    | Related Tests        | Status |
| ------------ | ------------------------------------------------------------------- | ----------- | ----------- | -------------------- | ------ |
| BUG-CART-001 | Cart counter does not refresh immediately after successful checkout | S3 — Medium | P3 — Medium | EXP-001              | Open   |
| BUG-CART-002 | Sticker Pack maximum quantity of 25 can be bypassed from Cart       | S2 — High   | P2 — High   | TC-CART-013, EXP-002 | Open   |
| BUG-CHK-001  | Checkout accepts invalid and expired card expiry dates              | S2 — High   | P2 — High   | EXP-003              | Open   |

---

# Defect Evidence

Regression defect evidence is stored under:

`manual-testing/evidence/regression-cycle-01/`

Evidence files:

* `BUG-CART-001-stale-counter-after-checkout.png`
* `BUG-CART-002-sticker-quantity-26.png`
* `BUG-CHK-001-invalid-expiry-accepted.png`

---

# Execution Status Definitions

**PASS** — Actual behavior matches expected behavior.

**FAIL** — Actual behavior differs from expected behavior and requires defect evaluation.

**BLOCKED** — Test execution cannot be completed because a prerequisite, environment condition, or required test data is unavailable.

**SKIPPED** — Test was deliberately excluded from execution.

**NOT RUN** — Test has not yet been executed.

---

# Regression Execution Summary

| Metric               | Result |
| -------------------- | -----: |
| Total Planned Checks |    113 |
| Executed             |    112 |
| Passed               |    108 |
| Failed               |      4 |
| Blocked              |      1 |
| Skipped              |      0 |
| Unique Defects       |      3 |
| Executed Pass Rate   |  96.4% |

---

# Module Summary

| Module                    | Passed | Failed | Blocked |
| ------------------------- | -----: | -----: | ------: |
| Authentication            |     10 |      0 |       0 |
| Registration              |     14 |      0 |       0 |
| Products                  |     14 |      0 |       0 |
| Cart                      |     12 |      1 |       0 |
| Coupons                   |      8 |      0 |       1 |
| Checkout                  |     20 |      0 |       0 |
| Account                   |      7 |      0 |       0 |
| Contact                   |      9 |      0 |       0 |
| QA Lab                    |     14 |      0 |       0 |
| Scripted Regression Total |    108 |      1 |       1 |
| Exploratory               |      0 |      3 |       0 |
| Overall                   |    108 |      4 |       1 |

---

# Blocked Test

## TC-CPN-012 — Automatic Free Shipping at Exactly $150

**Status:** BLOCKED

### Reason

The current product catalog, product prices, stock restrictions, and valid integer quantities do not provide a combination that creates an exact:

`$150.00`

subtotal.

Therefore, the exact free-shipping threshold cannot currently be tested through normal application behavior.

This is considered a **test-data limitation** rather than an application defect.

### Recommendation

Provide controlled test data or a product/cart configuration capable of reaching the exact `$150.00` boundary during a future regression cycle.

---

# Exit Criteria Evaluation

| Exit Criterion                              | Status                       |
| ------------------------------------------- | ---------------------------- |
| All Critical regression tests executed      | PASS                         |
| High-priority regression coverage completed | PASS                         |
| Major authentication workflow operational   | PASS                         |
| Registration workflow operational           | PASS                         |
| Product catalog operational                 | PASS                         |
| Cart core workflow operational              | PASS WITH DEFECT             |
| Coupon workflow operational                 | PASS WITH 1 BLOCKED BOUNDARY |
| Checkout core workflow operational          | PASS WITH DEFECT             |
| Account lifecycle operational               | PASS                         |
| Contact workflow operational                | PASS                         |
| QA Lab operational                          | PASS                         |
| Exploratory testing completed               | PASS                         |
| Discovered defects documented               | PASS                         |
| Regression evidence captured                | PASS                         |
| No unresolved Critical defect               | PASS                         |

---

# Risk Assessment

No blocker or Critical-severity defect was identified.

However, two High-severity defects remain open:

### BUG-CART-002

The QA Sticker Pack quantity restriction can be bypassed from Cart.

This creates inconsistent enforcement of a business rule.

### BUG-CHK-001

Invalid and expired card expiry values can pass checkout validation.

This represents insufficient payment-data validation.

A Medium-severity UI consistency defect also remains:

### BUG-CART-001

The navigation Cart counter remains stale immediately after successful checkout.

---

# Overall Regression Status

## COMPLETED WITH DEFECTS

Regression Cycle 01 has been completed.

### Final Results

**Total Planned Checks:** 113
**Executed:** 112
**Passed:** 108
**Failed:** 4
**Blocked:** 1
**Unique Defects:** 3
**Executed Pass Rate:** 96.4%

The major application workflows remain operational.

Three unique defects were identified:

1. `BUG-CART-001` — Navigation Cart counter remains stale immediately after successful checkout.
2. `BUG-CART-002` — QA Sticker Pack maximum quantity of 25 can be bypassed through Cart quantity editing.
3. `BUG-CHK-001` — Checkout accepts invalid and expired card expiry values.

Two defects are classified as High severity and should be addressed before Cart and Checkout business-rule validation can be considered fully regression-safe.

One test remains blocked because the current application test data cannot produce the exact `$150.00` subtotal required for the free-shipping boundary test.
