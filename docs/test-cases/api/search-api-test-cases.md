# QA Commerce Lab — Search API Test Cases

**Endpoint:** `GET /api/search?q=...`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Standard Execution Procedure

1. Prepare the specified query parameter.
2. Send the request.
3. Record the response status.
4. Inspect response headers.
5. Parse the response body.
6. Verify all case-specific assertions.

| ID              | Scenario / Requirement              | Request / Test Data               | Expected Result                                                                                  | Priority |
| --------------- | ----------------------------------- | --------------------------------- | ------------------------------------------------------------------------------------------------ | -------- |
| TC-API-SRCH-001 | SCN-API-SRCH-001 / REQ-API-SRCH-001 | `?q=keyboard`                     | HTTP 200; keyboard product is returned.                                                          | High     |
| TC-API-SRCH-002 | SCN-API-SRCH-002 / REQ-API-SRCH-002 | Search by valid brand value       | HTTP 200; products matching the brand are returned.                                              | High     |
| TC-API-SRCH-003 | SCN-API-SRCH-002 / REQ-API-SRCH-002 | `?q=Accessories`                  | Products belonging to the Accessories category are returned.                                     | High     |
| TC-API-SRCH-004 | SCN-API-SRCH-006 / REQ-API-SRCH-006 | `?q=KEYBOARD`                     | Search is case-insensitive and returns the same relevant result as lowercase `keyboard`.         | Medium   |
| TC-API-SRCH-005 | SCN-API-SRCH-001 / REQ-API-SRCH-001 | `?q=%20%20keyboard%20%20`         | Surrounding whitespace is ignored and the expected product is returned.                          | Medium   |
| TC-API-SRCH-006 | SCN-API-SRCH-001 / REQ-API-SRCH-001 | `?q=zzzz-does-not-exist`          | HTTP 200; count is 0 and the data array is empty.                                                | High     |
| TC-API-SRCH-007 | SCN-API-SRCH-003 / REQ-API-SRCH-003 | `/search` without `q`             | HTTP 400; missing-query validation is returned.                                                  | High     |
| TC-API-SRCH-008 | SCN-API-SRCH-004 / REQ-API-SRCH-004 | `?q=`                             | HTTP 400; empty-query validation is returned.                                                    | High     |
| TC-API-SRCH-009 | SCN-API-SRCH-004 / REQ-API-SRCH-004 | `q` contains whitespace only      | HTTP 400; query is treated as empty after trimming.                                              | High     |
| TC-API-SRCH-010 | SCN-API-SRCH-005 / REQ-API-SRCH-005 | Query exactly 100 characters long | Request is accepted because the maximum length is not exceeded.                                  | Medium   |
| TC-API-SRCH-011 | SCN-API-SRCH-005 / REQ-API-SRCH-005 | Query 101 characters long         | HTTP 400; query-length validation is returned.                                                   | Medium   |
| TC-API-SRCH-012 | SCN-API-SRCH-001 / REQ-API-SRCH-001 | `?q=إصدار`                        | Unicode search input is processed successfully and matching Unicode product content is returned. | Medium   |

## Execution Fields

For each case during execution, record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
