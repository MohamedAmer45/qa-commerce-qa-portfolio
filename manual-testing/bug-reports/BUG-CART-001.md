# BUG-CART-001 — Cart Counter Does Not Refresh Immediately After Successful Checkout

## Defect Information

**Defect ID:** BUG-CART-001
**Module:** Cart / Checkout
**Severity:** S3 — Medium
**Priority:** P3 — Medium
**Status:** Open
**Detected During:** Regression Cycle 01 — Exploratory Testing
**Related Check:** EXP-001

---

## Summary

The navigation cart counter continues displaying the previous cart quantity immediately after a successful checkout, even though the cart has already been cleared.

The counter becomes correct only after another navigation action causes the navigation component to re-render.

---

## Environment

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Browser:** Chromium
**URL:** https://qa-commerce-lab.vercel.app

---

## Preconditions

* User is authenticated.
* Cart contains at least one product.

---

## Test Data

Product:

`Echo Mini Speaker`

Quantity:

`2`

Payment card:

`4242424242424242`

Expiry:

`12/30`

CVV:

`123`

---

## Steps to Reproduce

1. Login using a valid account.
2. Add Echo Mini Speaker to the cart.
3. Set quantity to `2`.
4. Proceed to Checkout.
5. Enter valid shipping information.
6. Enter card number `4242424242424242`.
7. Enter expiry `12/30`.
8. Enter CVV `123`.
9. Select **Place order**.
10. Wait for the Order Confirmed state.
11. Observe the Cart counter in the navigation without navigating away.

---

## Expected Result

After the successful order:

* Cart storage is cleared.
* Navigation cart counter immediately changes to `0`.

---

## Actual Result

The cart itself is successfully cleared.

However, the existing navigation still displays:

`Cart 2`

The counter changes to:

`Cart 0`

only after navigating to another page and causing the navigation to re-render.

---

## Reproducibility

**100%**

---

## User Impact

The user receives conflicting information immediately after checkout.

The order confirmation indicates that checkout succeeded, while the navigation still suggests products remain in the cart.

This can cause confusion about whether the order was completed or whether the products are still pending.

---

## Technical Observation

The successful checkout clears the cart using stored state, but the navigation component is not re-rendered afterward.

The order form is replaced with the success state without refreshing the navigation.

---

## Suggested Fix

After clearing the cart during successful checkout:

1. Update or re-render the cart-count component.
2. Ensure the displayed navigation state reflects the latest persisted cart state immediately.

---

## Evidence

`BUG-CART-001-stale-counter-after-checkout.png`
