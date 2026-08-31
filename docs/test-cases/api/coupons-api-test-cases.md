# QA Commerce Lab — Coupons API Test Cases

**Endpoint:** `POST /api/coupons`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Standard Execution Procedure

1. Prepare the request body.
2. Send `POST /api/coupons`.
3. Record the HTTP status.
4. Parse the JSON response.
5. Verify coupon validation, discount calculation, and error details.

| ID             | Scenario / Requirement            | Request / Test Data                 | Expected Result                                                     | Priority |
| -------------- | --------------------------------- | ----------------------------------- | ------------------------------------------------------------------- | -------- |
| TC-API-CPN-001 | SCN-API-CPN-001 / REQ-API-CPN-002 | `{"code":"SAVE10","subtotal":100}`  | HTTP 200; coupon is valid; discount equals `10`.                    | High     |
| TC-API-CPN-002 | SCN-API-CPN-001 / REQ-API-CPN-002 | SAVE10 with subtotal `33.33`        | Discount equals 10% and is correctly rounded to monetary precision. | High     |
| TC-API-CPN-003 | SCN-API-CPN-002 / REQ-API-CPN-003 | `{"code":"FREESHIP","subtotal":50}` | HTTP 200; `freeShipping` is true; monetary discount remains 0.      | High     |
| TC-API-CPN-004 | SCN-API-CPN-003 / REQ-API-CPN-004 | MIN100 with subtotal `100`          | Coupon is accepted; discount equals `$15`.                          | High     |
| TC-API-CPN-005 | SCN-API-CPN-003 / REQ-API-CPN-004 | MIN100 with subtotal `100.01`       | Coupon is accepted and `$15` discount is applied.                   | High     |
| TC-API-CPN-006 | SCN-API-CPN-003 / REQ-API-CPN-004 | MIN100 with subtotal `99.99`        | HTTP 422; minimum-order validation is returned.                     | High     |
| TC-API-CPN-007 | SCN-API-CPN-004 / REQ-API-CPN-005 | EXPIRED with subtotal `100`         | Expired coupon is rejected with controlled error response.          | Medium   |
| TC-API-CPN-008 | SCN-API-CPN-005 / REQ-API-CPN-006 | `UNKNOWN123`                        | HTTP 404; unknown coupon is rejected.                               | High     |
| TC-API-CPN-009 | SCN-API-CPN-006 / REQ-API-CPN-007 | Missing `code`                      | HTTP 400; missing-code validation is returned.                      | High     |
| TC-API-CPN-010 | SCN-API-CPN-006 / REQ-API-CPN-007 | Missing `subtotal`                  | Controlled subtotal-validation error is returned.                   | High     |
| TC-API-CPN-011 | SCN-API-CPN-006 / REQ-API-CPN-007 | Subtotal `-1`                       | Negative subtotal is rejected.                                      | High     |
| TC-API-CPN-012 | SCN-API-CPN-007 / REQ-API-CPN-008 | Code `save10`                       | Code comparison is case-insensitive and coupon is accepted.         | Medium   |
| TC-API-CPN-013 | SCN-API-CPN-007 / REQ-API-CPN-008 | Code `SaVe10`                       | Mixed-case coupon is accepted.                                      | Medium   |
| TC-API-CPN-014 | SCN-API-CPN-008 / REQ-API-CPN-008 | Code `"   SAVE10   "`               | Surrounding whitespace is removed and coupon is accepted.           | Medium   |
| TC-API-CPN-015 | SCN-API-CPN-001 / REQ-API-CPN-002 | SAVE10 with subtotal `0`            | Coupon remains valid; calculated discount equals 0.                 | Medium   |

## Execution Fields

For each executed test case record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
