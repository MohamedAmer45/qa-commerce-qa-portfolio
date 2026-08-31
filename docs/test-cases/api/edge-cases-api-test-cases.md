# QA Commerce Lab — Edge-Case API Test Cases

**Endpoint:** `GET /api/edge-cases?mode=...`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Standard Execution Procedure

1. Send the request using the specified `mode`.
2. Record response time.
3. Record the HTTP status.
4. Inspect response headers.
5. Parse the response body when one is expected.
6. Verify the case-specific assertions.

| ID              | Scenario / Requirement              | Mode / Test Data          | Expected Result                                                                                              | Priority |
| --------------- | ----------------------------------- | ------------------------- | ------------------------------------------------------------------------------------------------------------ | -------- |
| TC-API-EDGE-001 | SCN-API-EDGE-001 / REQ-API-EDGE-001 | `mode=slow`               | HTTP 200; controlled successful response occurs after approximately 1500 ms.                                 | High     |
| TC-API-EDGE-002 | SCN-API-EDGE-001 / REQ-API-EDGE-001 | `mode=slow`               | Response is not returned immediately; measured duration reflects the configured delay.                       | High     |
| TC-API-EDGE-003 | SCN-API-EDGE-002 / REQ-API-EDGE-002 | `mode=empty`              | HTTP 204.                                                                                                    | High     |
| TC-API-EDGE-004 | SCN-API-EDGE-002 / REQ-API-EDGE-002 | `mode=empty`              | No unexpected response body is returned.                                                                     | High     |
| TC-API-EDGE-005 | SCN-API-EDGE-003 / REQ-API-EDGE-003 | `mode=bad-request`        | HTTP 400; controlled bad-request response is returned.                                                       | High     |
| TC-API-EDGE-006 | SCN-API-EDGE-004 / REQ-API-EDGE-004 | `mode=unauthorized`       | HTTP 401; controlled unauthorized response is returned.                                                      | High     |
| TC-API-EDGE-007 | SCN-API-EDGE-005 / REQ-API-EDGE-005 | `mode=not-found`          | HTTP 404; controlled not-found response is returned.                                                         | High     |
| TC-API-EDGE-008 | SCN-API-EDGE-006 / REQ-API-EDGE-006 | `mode=conflict`           | HTTP 409; controlled conflict response is returned.                                                          | High     |
| TC-API-EDGE-009 | SCN-API-EDGE-007 / REQ-API-EDGE-007 | `mode=validation`         | HTTP 422; validation-error response is returned.                                                             | High     |
| TC-API-EDGE-010 | SCN-API-EDGE-007 / REQ-API-EDGE-007 | `mode=validation`         | Response contains structured validation details.                                                             | High     |
| TC-API-EDGE-011 | SCN-API-EDGE-008 / REQ-API-EDGE-008 | `mode=rate-limit`         | HTTP 429.                                                                                                    | High     |
| TC-API-EDGE-012 | SCN-API-EDGE-009 / REQ-API-EDGE-009 | `mode=rate-limit`         | `Retry-After` response header equals `5`.                                                                    | Medium   |
| TC-API-EDGE-013 | SCN-API-EDGE-008 / REQ-API-EDGE-008 | `mode=rate-limit`         | Response body contains retry information consistent with 5 seconds.                                          | Medium   |
| TC-API-EDGE-014 | SCN-API-EDGE-010 / REQ-API-EDGE-010 | `mode=server-error`       | HTTP 500; controlled JSON error response is returned.                                                        | High     |
| TC-API-EDGE-015 | SCN-API-EDGE-010 / REQ-API-EDGE-010 | `mode=server-error`       | Response does not expose an unformatted server stack trace.                                                  | High     |
| TC-API-EDGE-016 | SCN-API-EDGE-011 / REQ-API-EDGE-011 | `mode=large`              | HTTP 200.                                                                                                    | Medium   |
| TC-API-EDGE-017 | SCN-API-EDGE-011 / REQ-API-EDGE-011 | `mode=large`              | Response contains exactly 250 records.                                                                       | Medium   |
| TC-API-EDGE-018 | SCN-API-EDGE-011 / REQ-API-EDGE-011 | `mode=large`              | Returned records have a consistent deterministic structure.                                                  | Medium   |
| TC-API-EDGE-019 | SCN-API-EDGE-011 / REQ-API-EDGE-011 | `mode=large`              | First and last records can be parsed without malformed data.                                                 | Medium   |
| TC-API-EDGE-020 | SCN-API-EDGE-012 / REQ-API-EDGE-012 | `mode=unknown-mode`       | Controlled client-error response is returned rather than HTTP 500.                                           | Medium   |
| TC-API-EDGE-021 | SCN-API-EDGE-012 / REQ-API-EDGE-012 | Missing `mode` parameter  | Controlled validation response is returned.                                                                  | Medium   |
| TC-API-EDGE-022 | SCN-API-EDGE-012 / REQ-API-EDGE-012 | `mode=`                   | Empty mode is rejected in a controlled manner.                                                               | Medium   |
| TC-API-EDGE-023 | SCN-API-EDGE-012 / REQ-API-EDGE-012 | `mode=SERVER-ERROR`       | Case handling is deterministic; either supported normalization or controlled unknown-mode error is returned. | Medium   |
| TC-API-EDGE-024 | SCN-API-EDGE-011 / REQ-API-EDGE-011 | Repeated `large` requests | Dataset count and structure remain deterministic across repeated calls.                                      | Medium   |
| TC-API-EDGE-025 | SCN-API-EDGE-001 / REQ-API-EDGE-001 | Repeated `slow` requests  | Each request completes successfully without intermittent uncontrolled failures.                              | Medium   |

## Execution Fields

For each executed test case record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
