# BUG-UI-CART-001 — QA Sticker Pack Quantity Can Exceed Special Maximum Through Cart Editor

## Defect Information

**Defect ID:** BUG-UI-CART-001
**Module:** Cart / Quantity Validation
**Severity:** S2 — High
**Priority:** P2 — High
**Status:** Open
**Detected During:** Selenium UI Automation
**Related Test:** `CartTests.stickerPackQuantityCannotExceed25`

---

## Summary

The QA Sticker Pack has a business-rule maximum quantity of `25`, but the cart quantity editor allows the quantity to be increased above that limit.

The Add to Cart logic correctly enforces the special maximum, while the cart quantity editor validates only against the product's physical stock.

---

## Environment

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Browser:** Chrome
**Automation:** Selenium + Java + TestNG

---

## Product Data

**Product:** QA Sticker Pack
**Product ID:** `7`
**Price:** `$0.00`
**Available Stock:** `500`
**Business Maximum:** `25`

---

## Preconditions

1. User has opened the Products page.
2. QA Sticker Pack is available.
3. Cart is initially empty.

---

## Steps to Reproduce

1. Add QA Sticker Pack to the cart.
2. Navigate to the cart.
3. Confirm initial quantity is `1`.
4. Change the quantity field to `26`.
5. Move focus away from the quantity field.
6. Observe the resulting quantity.

---

## Expected Result

The application should reject quantity `26`.

The quantity should remain at the last valid value.

The maximum allowed quantity should remain:

`25`

---

## Actual Result

The cart accepts:

`26`

because the cart quantity editor validates against physical stock `500` rather than the special product maximum `25`.

---

## Reproducibility

**100%**

---

## Business Impact

Users can bypass product-specific purchase restrictions after adding the product to the cart.

This creates inconsistent quantity validation between the product catalog and cart and could allow restricted purchase limits to be bypassed.

---

## Technical Observation

The Add to Cart logic applies:

`min(stock, special maximum 25)`

for Product ID `7`.

However, the cart quantity editor appears to validate only:

`quantity <= product.stock`

For QA Sticker Pack:

`26 <= 500`

therefore the invalid quantity is accepted.

---

## Automated Evidence

Selenium test:

`CartTests.stickerPackQuantityCannotExceed25`

Expected:

`Quantity remains 1`

Actual:

`Quantity becomes 26`

The automated assertion should remain failing until the business rule is consistently enforced.

---

## Suggested Fix

Centralize quantity-limit calculation and use the same rule in both:

* Add to Cart.
* Cart quantity editing.

For Product ID `7`, the effective maximum should always be:

`25`

regardless of the physical stock value.

---

## Defect Status

**Open**
