# QA Commerce Lab — Echo API Test Cases

**Endpoint:** `/api/echo`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Standard Execution Procedure

1. Prepare the specified HTTP method.
2. Add the required query parameters.
3. Add the specified request headers.
4. Add a request body where applicable.
5. Send the request.
6. Record the status.
7. Verify the returned request information.

| ID              | Scenario / Requirement              | Request / Test Data                                           | Expected Result                                                                  | Priority |
| --------------- | ----------------------------------- | ------------------------------------------------------------- | -------------------------------------------------------------------------------- | -------- |
| TC-API-ECHO-001 | SCN-API-ECHO-001 / REQ-API-ECHO-001 | GET `/echo`                                                   | HTTP 200; deterministic request information is returned.                         | Medium   |
| TC-API-ECHO-002 | SCN-API-ECHO-002 / REQ-API-ECHO-002 | GET `/echo`                                                   | Response identifies request method as `GET`.                                     | Medium   |
| TC-API-ECHO-003 | SCN-API-ECHO-002 / REQ-API-ECHO-002 | POST `/echo` with JSON body                                   | HTTP 200; response identifies method as `POST`.                                  | Medium   |
| TC-API-ECHO-004 | SCN-API-ECHO-002 / REQ-API-ECHO-002 | PUT `/echo` with JSON body                                    | HTTP 200; response identifies method as `PUT`.                                   | Medium   |
| TC-API-ECHO-005 | SCN-API-ECHO-002 / REQ-API-ECHO-002 | DELETE `/echo`                                                | HTTP 200; response identifies method as `DELETE`.                                | Medium   |
| TC-API-ECHO-006 | SCN-API-ECHO-003 / REQ-API-ECHO-003 | GET `/echo?foo=bar`                                           | Query parameter `foo=bar` is represented correctly.                              | Medium   |
| TC-API-ECHO-007 | SCN-API-ECHO-003 / REQ-API-ECHO-003 | GET `/echo?foo=bar&number=123`                                | Multiple query parameters are handled correctly.                                 | Medium   |
| TC-API-ECHO-008 | SCN-API-ECHO-003 / REQ-API-ECHO-003 | Query containing spaces and Unicode                           | Query content is decoded and handled without corruption.                         | Medium   |
| TC-API-ECHO-009 | SCN-API-ECHO-004 / REQ-API-ECHO-004 | Header `X-Test-Id: TC-API-ECHO-009`                           | Custom test header is processed/surfaced correctly.                              | Medium   |
| TC-API-ECHO-010 | SCN-API-ECHO-004 / REQ-API-ECHO-004 | Header `Content-Type: application/json`                       | Content-Type is handled correctly.                                               | Medium   |
| TC-API-ECHO-011 | SCN-API-ECHO-004 / REQ-API-ECHO-004 | Header `Authorization: Bearer qa-test-token`                  | Header is handled without causing an unexpected server error.                    | Medium   |
| TC-API-ECHO-012 | SCN-API-ECHO-005 / REQ-API-ECHO-005 | POST body `{"name":"QA","active":true,"count":10}`            | JSON values are represented correctly without type corruption.                   | Medium   |
| TC-API-ECHO-013 | SCN-API-ECHO-005 / REQ-API-ECHO-005 | POST body containing nested object                            | Nested JSON content is processed correctly.                                      | Medium   |
| TC-API-ECHO-014 | SCN-API-ECHO-005 / REQ-API-ECHO-005 | POST body containing array                                    | Array content is processed correctly.                                            | Medium   |
| TC-API-ECHO-015 | SCN-API-ECHO-005 / REQ-API-ECHO-005 | POST body containing Unicode text                             | Unicode body content is handled without corruption.                              | Medium   |
| TC-API-ECHO-016 | SCN-API-ECHO-006 / REQ-API-ECHO-005 | POST body `{}`                                                | Empty JSON object is handled successfully.                                       | Medium   |
| TC-API-ECHO-017 | SCN-API-ECHO-006 / REQ-API-ECHO-005 | POST with no body                                             | Endpoint returns a controlled response rather than an uncontrolled server error. | Medium   |
| TC-API-ECHO-018 | SCN-API-ECHO-006 / REQ-API-ECHO-006 | Malformed JSON body                                           | Controlled 4xx response is returned instead of HTTP 500.                         | Medium   |
| TC-API-ECHO-019 | SCN-API-ECHO-006 / REQ-API-ECHO-006 | Unexpected content type                                       | Endpoint handles or rejects request in a controlled manner.                      | Medium   |
| TC-API-ECHO-020 | SCN-API-ECHO-003 / REQ-API-ECHO-003 | Duplicate query key such as `?tag=a&tag=b`                    | Endpoint handles duplicate query parameters deterministically.                   | Medium   |
| TC-API-ECHO-021 | SCN-API-ECHO-004 / REQ-API-ECHO-004 | Very long custom header value                                 | Endpoint handles request without uncontrolled server failure.                    | Medium   |
| TC-API-ECHO-022 | SCN-API-ECHO-005 / REQ-API-ECHO-005 | JSON values containing `null`, `false`, `0`, and empty string | Values retain their intended types/values.                                       | Medium   |
| TC-API-ECHO-023 | SCN-API-ECHO-002 / REQ-API-ECHO-002 | OPTIONS `/echo`                                               | CORS/preflight request is handled consistently with API rules.                   | Medium   |
| TC-API-ECHO-024 | SCN-API-ECHO-006 / REQ-API-ECHO-006 | Repeated identical request                                    | Responses remain deterministic across repeated execution.                        | Medium   |

## Execution Fields

For each executed test case record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
