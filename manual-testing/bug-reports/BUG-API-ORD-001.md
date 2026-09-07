# BUG-API-ORD-001 — Orders API Does Not Aggregate Duplicate Product Quantities Before Stock Validation

## Defect Information

**Defect ID:** BUG-API-ORD-001
**Module:** Orders API
**Endpoint:** `POST /api/orders`
**Severity:** S2 — High
**Priority:** P2 — High
**Status:** Open
**Detected During:** Postman API Automation
**Related Test:** TC-API-ORD — Duplicate product lines exceeding available stock

---

## Summary

The Orders API validates stock independently for each item line instead of validating the combined requested quantity when the same product appears multiple times in the order.

As a result, an order can exceed the available stock by splitting the requested quantity across duplicate item entries.

---

## Environment

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**Endpoint:** `POST /api/orders`

---

## Product Data

**Product ID:** `2`

**Available Stock:** `1`

---

## Request Body

```json
{
  "items": [
    {
      "id": 2,
      "qty": 1
    },
    {
      "id": 2,
      "qty": 1
    }
  ],
  "shipping": {
    "email": "qa.user@example.com",
    "address": "123 QA Street"
  },
  "payment": {
    "cardNumber": "4242424242424242"
  }
}
```

---

## Steps to Reproduce

1. Send `POST /api/orders`.
2. Include product ID `2` twice in the `items` array.
3. Set each item quantity to `1`.
4. Use valid shipping information.
5. Use valid payment card `4242424242424242`.
6. Send the request.
7. Inspect the HTTP status and response.

---

## Expected Result

The API should calculate the combined requested quantity:

`1 + 1 = 2`

Available stock:

`1`

Because the combined requested quantity exceeds available stock, the API should reject the order.

Expected response:

**HTTP 409 Conflict**

with an error such as:

`INSUFFICIENT_STOCK`

---

## Actual Result

The API returns:

**HTTP 201 Created**

and successfully creates the order.

Each individual item line is validated against stock separately:

* First line: quantity `1` <= stock `1`
* Second line: quantity `1` <= stock `1`

The API does not aggregate both entries before validating stock.

---

## Reproducibility

**100%**

---

## Business Impact

A customer can order more units than are actually available by splitting the same product across multiple item lines.

This can lead to:

* Overselling inventory.
* Incorrect stock validation.
* Orders that cannot be fulfilled.
* Inventory inconsistencies between ordering and fulfillment systems.

---

## Technical Observation

Stock validation appears to be performed independently for each element in the `items` array.

Before validating stock, the API should group items by product ID and calculate the total requested quantity per product.

Example:

```text
Product 2:
Line 1 quantity = 1
Line 2 quantity = 1

Combined quantity = 2
Available stock = 1

Result = reject order
```

---

## Suggested Fix

Before performing stock validation:

1. Group order items by product ID.
2. Sum the quantities for duplicate products.
3. Compare the combined quantity against available stock.
4. Reject the request with `409 INSUFFICIENT_STOCK` if the combined quantity exceeds stock.

Stock validation should also be performed server-side immediately before order creation.

---

## Evidence

Postman execution showing:

`Expected status 409 but received 201`
