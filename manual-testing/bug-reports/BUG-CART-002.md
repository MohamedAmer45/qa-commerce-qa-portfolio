# BUG-CART-002 — Sticker Pack Maximum Quantity of 25 Can Be Bypassed from Cart

## Defect Information

**Defect ID:** BUG-CART-002
**Module:** Cart
**Severity:** S2 — High
**Priority:** P2 — High
**Status:** Open
**Detected During:** Regression Cycle 01
**Related Test Cases:** TC-CART-013, EXP-002

---

## Summary

The QA Sticker Pack has a documented maximum quantity of 25 units.

The Add-to-Cart logic correctly enforces this maximum, but the Cart quantity input allows the quantity to be changed above 25.

---

## Environment

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Browser:** Chromium
**URL:** https://qa-commerce-lab.vercel.app

---

## Preconditions

* QA Sticker Pack is present in the cart.

---

## Product Information

**Product:** QA Sticker Pack
**Product ID:** 7
**Price:** `$0.00`
**Stock:** `500`
**Business Maximum:** `25`

---

## Steps to Reproduce

1. Add QA Sticker Pack to the cart.
2. Open Cart.
3. Set the quantity to `25`.
4. Verify that quantity 25 is accepted.
5. Change the quantity from `25` to `26`.

---

## Expected Result

Quantity `26` should be rejected.

The maximum permitted quantity should remain:

`25`

The same business rule should be applied regardless of whether the quantity is changed during Add to Cart or directly from Cart.

---

## Actual Result

The Cart accepts:

`26`

The quantity remains set to 26.

The cart quantity validation uses the product's stock value of `500` instead of the special Sticker Pack maximum of `25`.

---

## Reproducibility

**100%**

---

## User / Business Impact

A customer can bypass the intended per-order purchase limit.

The same issue potentially allows quantities from 26 up to the full stock value of 500.

This creates inconsistent business-rule enforcement between Product and Cart workflows.

---

## Technical Observation

The Add-to-Cart logic calculates the maximum using the special rule:

`25` for product ID 7.

The Cart quantity input instead uses:

`product.stock`

as its maximum.

For the Sticker Pack this results in:

`max = 500`

instead of:

`max = 25`

---

## Suggested Fix

Use one shared maximum-quantity rule for:

* Add to Cart
* Cart quantity changes
* Checkout validation

For product ID 7, all flows should enforce:

`maximum quantity = 25`

The restriction should also be validated in business logic rather than relying only on the HTML `max` attribute.

---

## Evidence

`BUG-CART-002-sticker-quantity-26.png`
