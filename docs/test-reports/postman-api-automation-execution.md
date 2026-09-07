# QA Commerce Lab — Postman API Automation Execution Report

## Test Run Information

**Application:** QA Commerce Lab
**Environment:** Production Test Environment
**API Base URL:** `https://qa-commerce-lab.vercel.app/api`
**Tool:** Postman + Newman
**Collection:** `QA Commerce Lab API`
**Execution Type:** Automated REST API Regression
**Execution Status:** Completed

---

## Scope

The automated API regression suite covers:

* General API behavior.
* Health checks.
* Product retrieval and filtering.
* Search.
* Authentication.
* User management.
* Coupons.
* Orders.
* Reviews.
* Contact requests.
* API edge cases.
* Echo and request reflection behavior.

The suite includes positive, negative, boundary, validation, error-handling, security, and business-rule coverage.

---

## Execution Summary

| Metric              |                            Result |
| ------------------- | --------------------------------: |
| Collection Folders  |                                12 |
| Requests Executed   |   194 |
| Assertions Executed | 641 |
| Passed Assertions   |             640 |
| Failed Assertions   |                               `1` |
| Known Defects       |                               `1` |

---

## Result

The API automation suite completed successfully with one expected failing assertion associated with a confirmed application defect.

All remaining automated API checks passed.

---

## Known Failure

### BUG-API-ORD-001

**Endpoint:** `POST /api/orders`

**Test:** Duplicate product lines exceeding available stock.

The request contains the same product twice:

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
  ]
}
```

Product ID `2` has available stock of `1`.

### Expected Result

The API should aggregate duplicate product quantities:

`1 + 1 = 2`

Because the combined requested quantity exceeds available stock, the API should return:

`HTTP 409 Conflict`

with:

`INSUFFICIENT_STOCK`

### Actual Result

The API returns:

`HTTP 201 Created`

and creates the order successfully.

### Defect

`BUG-API-ORD-001`

### Status

Open.

---

## Automation Result Interpretation

The failing assertion is intentionally retained because it represents a confirmed application defect.

The automated assertion has not been changed to match the incorrect application behavior.

This ensures the automation suite continues to detect the defect until the underlying business logic is corrected.

---

## Reports

Generated Newman reports:

* `reports/postman/postman-report.html`
* `reports/postman/postman-report.json`

Postman source files:

* `api-testing/postman/QA-Commerce-Lab-API.postman_collection.json`
* `api-testing/postman/QA-Commerce-Lab-Production.postman_environment.json`

---

## Conclusion

The Postman/Newman automated REST API regression suite is operational and covers the major API functionality, validation rules, negative cases, edge conditions, and business-critical workflows.

The test run identified one reproducible high-impact stock-validation defect in the Orders API. All other automated checks completed successfully.
