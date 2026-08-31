# QA Commerce Lab — Orders API Test Cases

**Endpoint:** `POST /api/orders`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Valid Request

```json
{
  "items": [
    {
      "id": 10,
      "qty": 2
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

## Standard Execution Procedure

1. Prepare the specified order payload.
2. Send `POST /api/orders`.
3. Record the HTTP status.
4. Parse the response body.
5. Verify inventory, payment, calculation, and order assertions.

| ID             | Scenario / Requirement                             | Test Data / Variation                                          | Expected Result                                                   | Priority |
| -------------- | -------------------------------------------------- | -------------------------------------------------------------- | ----------------------------------------------------------------- | -------- |
| TC-API-ORD-001 | SCN-API-ORD-001 / REQ-API-ORD-001                  | Valid request                                                  | HTTP 201; order is created successfully.                          | Critical |
| TC-API-ORD-002 | SCN-API-ORD-002 / REQ-API-ORD-002                  | `"items":[]`                                                   | HTTP 422; order requires at least one item.                       | Critical |
| TC-API-ORD-003 | SCN-API-ORD-002 / REQ-API-ORD-002                  | Missing `items`                                                | HTTP 422.                                                         | Critical |
| TC-API-ORD-004 | SCN-API-ORD-003 / REQ-API-ORD-003                  | Missing shipping object                                        | HTTP 422; shipping validation is returned.                        | Critical |
| TC-API-ORD-005 | SCN-API-ORD-003 / REQ-API-ORD-003                  | Shipping missing email                                         | HTTP 422.                                                         | Critical |
| TC-API-ORD-006 | SCN-API-ORD-003 / REQ-API-ORD-003                  | Invalid shipping email                                         | HTTP 422.                                                         | Critical |
| TC-API-ORD-007 | SCN-API-ORD-003 / REQ-API-ORD-003                  | Shipping missing address                                       | HTTP 422.                                                         | Critical |
| TC-API-ORD-008 | SCN-API-ORD-004 / REQ-API-ORD-004                  | Missing payment object                                         | HTTP 422.                                                         | Critical |
| TC-API-ORD-009 | SCN-API-ORD-004 / REQ-API-ORD-004                  | Invalid Luhn card                                              | HTTP 422; payment validation fails.                               | Critical |
| TC-API-ORD-010 | SCN-API-ORD-005 / REQ-API-ORD-005                  | Product ID `99999`                                             | HTTP 404; product-not-found response returned.                    | High     |
| TC-API-ORD-011 | SCN-API-ORD-006 / REQ-API-ORD-006                  | Quantity `0`                                                   | HTTP 422; quantity rejected.                                      | Critical |
| TC-API-ORD-012 | SCN-API-ORD-006 / REQ-API-ORD-006                  | Quantity `-1`                                                  | HTTP 422.                                                         | Critical |
| TC-API-ORD-013 | SCN-API-ORD-006 / REQ-API-ORD-006                  | Quantity `1.5`                                                 | HTTP 422 because quantity must be an integer.                     | Critical |
| TC-API-ORD-014 | SCN-API-ORD-006 / REQ-API-ORD-006                  | Quantity `1`                                                   | Accepted when product has stock.                                  | Critical |
| TC-API-ORD-015 | SCN-API-ORD-007 / REQ-API-ORD-007                  | Product stock 1, requested quantity 2                          | HTTP 409; insufficient-stock response returned.                   | Critical |
| TC-API-ORD-016 | SCN-API-ORD-007 / REQ-API-ORD-007                  | Out-of-stock product, quantity 1                               | HTTP 409; order is rejected.                                      | Critical |
| TC-API-ORD-017 | SCN-API-ORD-008 / REQ-API-ORD-008                  | Card `4000000000000002`                                        | HTTP 402; payment is declined.                                    | High     |
| TC-API-ORD-018 | SCN-API-ORD-009 / REQ-API-ORD-009                  | Card `4000000000009995`                                        | HTTP 402; insufficient-funds response returned.                   | High     |
| TC-API-ORD-019 | SCN-API-ORD-010 / REQ-API-ORD-010, REQ-API-ORD-011 | Successful order                                               | HTTP 201; order ID and confirmation status returned.              | Critical |
| TC-API-ORD-020 | SCN-API-ORD-011 / REQ-API-ORD-012                  | Product 10 × 2 at `$39.90`                                     | Subtotal equals `$79.80`.                                         | High     |
| TC-API-ORD-021 | SCN-API-ORD-011 / REQ-API-ORD-012                  | Product with stored price `19.999`                             | Monetary subtotal is correctly rounded to two decimal places.     | High     |
| TC-API-ORD-022 | SCN-API-ORD-011 / REQ-API-ORD-012                  | Multiple products                                              | Subtotal equals sum of price × quantity for all line items.       | High     |
| TC-API-ORD-023 | SCN-API-ORD-012 / REQ-API-ORD-013                  | Successful order                                               | Currency equals `USD`.                                            | Medium   |
| TC-API-ORD-024 | SCN-API-ORD-001 / REQ-API-ORD-001                  | Card `4242 4242 4242 4242`                                     | Card spaces are normalized and valid payment can succeed.         | Medium   |
| TC-API-ORD-025 | SCN-API-ORD-007 / REQ-API-ORD-007                  | Same product appears twice and combined quantity exceeds stock | Order must be rejected; combined quantity must respect inventory. | Critical |

## Special Defect Check

`TC-API-ORD-025` specifically checks whether inventory validation is performed across duplicate order lines.

If two individually valid lines can collectively exceed available inventory, record a defect.

## Execution Fields

For each executed test case record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
