# BUG-UI-CHK-001 — Cart Counter Does Not Update After Successful Checkout

## Defect Information

**Defect ID:** BUG-UI-CHK-001
**Module:** Checkout / Cart
**Severity:** S3 — Medium
**Priority:** P2 — High
**Status:** Open
**Detected During:** Selenium UI Automation
**Related Test:** `CheckoutTests.successfulCheckoutClearsCartAndUpdatesCounter`

---

## Summary

After a successful checkout, the application correctly clears the persisted cart data, but the cart counter displayed in the navigation bar does not update immediately.

The navigation continues to display the previous cart quantity until the page is refreshed or another application render occurs.

---

## Environment

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Browser:** Chrome
**Automation:** Selenium + Java + TestNG

---

## Preconditions

1. User is authenticated.
2. Cart contains at least one product.

---

## Steps to Reproduce

1. Sign in using the seeded QA account.
2. Add one product to the cart.
3. Navigate to checkout.
4. Complete all required shipping fields.
5. Enter valid payment details:

   * Card: `4242424242424242`
   * Expiry: `12/30`
   * CVV: `123`
6. Click **Place order**.
7. Wait for the `Order confirmed` message.
8. Observe the cart counter in the navigation bar.

---

## Expected Result

After the order is successfully created:

* The cart is cleared.
* The cart counter immediately changes from `1` to `0`.

---

## Actual Result

The order is successfully created and the persisted cart is cleared.

However, the navigation cart counter remains:

`1`

After manually refreshing the page, the counter changes to:

`0`

---

## Reproducibility

**100%**

---

## Business Impact

The user receives inconsistent UI feedback after completing an order.

Although the underlying cart state is cleared, the stale counter may make the user believe that:

* The cart was not cleared.
* The purchased item is still in the cart.
* The checkout operation did not fully complete.

This may cause unnecessary navigation or repeated checkout attempts.

---

## Technical Observation

The checkout success logic clears the persisted cart:

`localStorage cart → []`

but the navigation component is not re-rendered afterward.

The cart counter therefore continues displaying the value generated before checkout.

A refresh causes the page to re-read the cart state and correctly display `0`.

---

## Automated Evidence

The following Selenium test intentionally remains failing while the defect exists:

`CheckoutTests.successfulCheckoutClearsCartAndUpdatesCounter`

Expected:

`0`

Actual:

`1`

A separate automated test confirms the persisted cart was successfully cleared:

`CheckoutTests.successfulCheckoutClearsPersistedCart`

Result:

`PASS`

---

## Suggested Fix

After successfully clearing the cart during checkout:

1. Update the cart state.
2. Trigger a navigation/header re-render.
3. Ensure the cart badge reads the updated cart state immediately.

The displayed counter should remain synchronized with persisted cart state without requiring a page refresh.

---

## Defect Status

**Open**
