# BUG-UI-CHK-002 — Checkout Accepts Invalid Expiry Month

## Defect Information

**Defect ID:** BUG-UI-CHK-002
**Module:** Checkout / Payment Validation
**Severity:** S2 — High
**Priority:** P2 — High
**Status:** Open
**Detected During:** Selenium UI Automation
**Related Test:** `CheckoutTests.impossibleExpiryMonthIsRejected`

---

## Summary

The checkout form accepts a payment-card expiry value containing an impossible month, such as `13/30`.

The application validates the expiry field format but does not validate whether the entered month is within the valid range `01–12`.

As a result, an order can be successfully submitted using an invalid card expiry month.

---

## Environment

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Browser:** Chrome
**Automation:** Selenium + Java + TestNG

---

## Preconditions

1. User is authenticated.
2. Cart contains at least one available product.
3. User has navigated to the checkout page.

---

## Test Data

**Card Number:** `4242424242424242`
**Expiry:** `13/30`
**CVV:** `123`

---

## Steps to Reproduce

1. Sign in using a valid account.
2. Add an available product to the cart.
3. Navigate to checkout.
4. Complete all required shipping fields.
5. Enter valid card number `4242424242424242`.
6. Enter expiry `13/30`.
7. Enter valid CVV `123`.
8. Click **Place order**.
9. Observe the result.

---

## Expected Result

The expiry value should be rejected because month `13` does not exist.

The checkout should remain on the form and display a validation message such as:

`Expiry/CVV invalid.`

No order should be created.

---

## Actual Result

The application accepts the expiry value `13/30`.

The checkout proceeds and displays a successful order confirmation.

---

## Reproducibility

**100%**

---

## Business Impact

Invalid payment-card data can pass client-side checkout validation.

This creates inaccurate validation behavior and could allow invalid payment information to proceed further into a real payment-processing workflow.

---

## Technical Observation

The expiry validation appears to verify only the pattern:

`NN/NN`

rather than validating the month portion semantically.

For example:

* `01/30` → valid
* `12/30` → valid
* `13/30` → should be invalid
* `99/30` → should be invalid

---

## Automated Evidence

Selenium test:

`CheckoutTests.impossibleExpiryMonthIsRejected`

Expected:

`Order must not succeed`

Actual:

`Order success confirmation displayed`

The automated assertion should remain failing until the application validation is corrected.

---

## Suggested Fix

Validate the expiry month independently after confirming the `MM/YY` format.

The month should satisfy:

`01 <= MM <= 12`

The implementation should also ideally validate that the expiry date is not already in the past.

---

## Defect Status

**Open**
