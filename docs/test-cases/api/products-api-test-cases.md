# QA Commerce Lab — Products API Test Cases

**Execution Status:** Not Run
**Automation Candidate:** Yes

## Product Collection

**Endpoint:** `GET /api/products`

| ID              | Scenario / Requirement              | Request / Test Data                           | Expected Result                                                                    | Priority |
| --------------- | ----------------------------------- | --------------------------------------------- | ---------------------------------------------------------------------------------- | -------- |
| TC-API-PROD-001 | SCN-API-PROD-001 / REQ-API-PROD-001 | GET `/products`                               | 200; `data` array returned.                                                        | Critical |
| TC-API-PROD-002 | SCN-API-PROD-007 / REQ-API-PROD-008 | GET `/products` with no pagination parameters | Defaults to page 1 and limit 10; total is 12; first response contains 10 products. | High     |
| TC-API-PROD-003 | SCN-API-PROD-007 / REQ-API-PROD-008 | `?page=2&limit=10`                            | 200; second page contains remaining 2 products.                                    | High     |
| TC-API-PROD-004 | SCN-API-PROD-002 / REQ-API-PROD-002 | `?category=Audio`                             | Only Audio products returned.                                                      | High     |
| TC-API-PROD-005 | SCN-API-PROD-002 / REQ-API-PROD-002 | `?category=audio`                             | Category filtering is case-insensitive.                                            | High     |
| TC-API-PROD-006 | SCN-API-PROD-003 / REQ-API-PROD-003 | `?inStock=true`                               | Only products with stock greater than 0 returned.                                  | High     |
| TC-API-PROD-007 | SCN-API-PROD-004 / REQ-API-PROD-004 | `?sort=price_asc`                             | Prices are ordered lowest → highest. `$0` product appears first.                   | Medium   |
| TC-API-PROD-008 | SCN-API-PROD-004 / REQ-API-PROD-005 | `?sort=price_desc`                            | Prices are ordered highest → lowest.                                               | Medium   |
| TC-API-PROD-009 | SCN-API-PROD-005 / REQ-API-PROD-006 | `?sort=rating`                                | Ratings are sorted descending.                                                     | Medium   |
| TC-API-PROD-010 | SCN-API-PROD-006 / REQ-API-PROD-007 | `?sort=invalid`                               | 400; `error: INVALID_SORT`.                                                        | High     |
| TC-API-PROD-011 | SCN-API-PROD-008 / REQ-API-PROD-009 | `?page=1`                                     | Page 1 accepted.                                                                   | High     |
| TC-API-PROD-012 | SCN-API-PROD-008 / REQ-API-PROD-009 | `?page=0`                                     | 400; `INVALID_PAGE`.                                                               | High     |
| TC-API-PROD-013 | SCN-API-PROD-008 / REQ-API-PROD-009 | `?page=-1`                                    | 400; `INVALID_PAGE`.                                                               | High     |
| TC-API-PROD-014 | SCN-API-PROD-008 / REQ-API-PROD-009 | `?page=1.5`                                   | 400; page must be an integer.                                                      | High     |
| TC-API-PROD-015 | SCN-API-PROD-009 / REQ-API-PROD-010 | `?limit=1`                                    | 200; one product returned.                                                         | High     |
| TC-API-PROD-016 | SCN-API-PROD-009 / REQ-API-PROD-010 | `?limit=100`                                  | 200; upper boundary accepted.                                                      | High     |
| TC-API-PROD-017 | SCN-API-PROD-009 / REQ-API-PROD-010 | `?limit=0`                                    | 400; `INVALID_LIMIT`.                                                              | High     |
| TC-API-PROD-018 | SCN-API-PROD-009 / REQ-API-PROD-010 | `?limit=101`                                  | 400; `INVALID_LIMIT`.                                                              | High     |

## Single Product

**Endpoint:** `GET /api/product`

| ID              | Scenario / Requirement              | Request / Test Data | Expected Result             | Priority |
| --------------- | ----------------------------------- | ------------------- | --------------------------- | -------- |
| TC-API-PROD-019 | SCN-API-PROD-010 / REQ-API-PROD-011 | `/product?id=1`     | 200; product ID 1 returned. | Critical |
| TC-API-PROD-020 | SCN-API-PROD-011 / REQ-API-PROD-012 | `/product?id=0`     | 400; `INVALID_ID`.          | High     |
| TC-API-PROD-021 | SCN-API-PROD-011 / REQ-API-PROD-012 | `/product?id=abc`   | 400; `INVALID_ID`.          | High     |
| TC-API-PROD-022 | SCN-API-PROD-012 / REQ-API-PROD-013 | `/product?id=99999` | 404; `PRODUCT_NOT_FOUND`.   | High     |
