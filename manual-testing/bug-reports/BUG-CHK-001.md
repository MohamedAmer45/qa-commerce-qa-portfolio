# BUG-CHK-001 — Checkout Accepts Invalid and Expired Card Expiry Dates

## Defect Information

**Defect ID:** BUG-CHK-001
**Module:** Checkout / Payment Validation
**Severity:** S2 — High
**Priority:** P2 — High
**Status:** Open
**Detected During:** Regression Cycle 01 — Exploratory Testing
**Related Check:** EXP-003

---

## Summary

Checkout validates only the formatting of the card expiry field as `MM/YY`.

It does not validate whether:

* The month is between 01 and 12.
* The expiry date has already passed.
* The supplied month/year combination represents a valid expiry date.

As a result, invalid or expired expiry dates can successfully complete checkout.

---

## Environment

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Browser:** Chromium
**URL:** https://qa-commerce-lab.vercel.app

---

## Preconditions

* User is authenticated.
* Cart contains an available product.
* All shipping information is valid.

---

## Payment Test Data

**Card Number:**

`4242424242424242`

**CVV:**

`123`

The following expiry values were tested:

* `00/30`
* `13/30`
* `01/20`
* `99/99`

---

## Steps to Reproduce

1. Login.
2. Add an available product to Cart.
3. Proceed to Checkout.
4. Enter valid shipping information.
5. Enter card number `4242424242424242`.
6. Enter CVV `123`.
7. Enter one of the invalid expiry values.
8. Select **Place order**.

Repeat using each expiry value listed above.

---

## Expected Result

Checkout should reject:

### Invalid Month

`00/30`

because month 00 does not exist.

### Invalid Month

`13/30`

because month 13 does not exist.

### Expired Date

`01/20`

because the card expiration date is in the past.

### Invalid Month

`99/99`

because month 99 does not exist.

Appropriate validation feedback should be displayed.

---

## Actual Result

All tested values were accepted:

`00/30`

`13/30`

`01/20`

`99/99`

Each request reached the successful Order Confirmed state.

---

## Reproducibility

**100%**

---

## User / Business Impact

The checkout accepts card information that should be considered invalid.

In a real payment workflow this could:

* Allow invalid payment data to progress unnecessarily.
* Produce avoidable payment-gateway failures.
* Create inconsistent frontend and backend validation.
* Reduce user confidence in payment validation.

---

## Technical Observation

The checkout currently validates expiry using only a format expression equivalent to:

`two digits / two digits`

This validates the structure but not the semantic value of the date.

---

## Suggested Fix

Expiry validation should verify:

1. Month is between `01` and `12`.
2. Year is interpreted correctly.
3. The expiry month has not already passed.
4. Invalid dates are rejected before order processing.

Example valid rule:

* `12/30` → accepted
* `00/30` → rejected
* `13/30` → rejected
* expired date → rejected

---

## Evidence

`BUG-CHK-001-invalid-expiry-accepted.png`
