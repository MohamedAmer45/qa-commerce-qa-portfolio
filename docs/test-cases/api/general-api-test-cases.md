# QA Commerce Lab — General REST API Test Cases

**Base URL:** https://qa-commerce-lab.vercel.app/api
**Status:** Not Run

| ID             | Scenario / Requirement        | Request / Test Data        | Expected Result                                                                           | Priority |
| -------------- | ----------------------------- | -------------------------- | ----------------------------------------------------------------------------------------- | -------- |
| TC-API-GEN-001 | SCN-API-GEN-001 / REQ-API-001 | GET `/health`              | 200; response Content-Type is JSON; body is valid JSON.                                   | High     |
| TC-API-GEN-002 | SCN-API-GEN-002 / REQ-API-002 | GET `/health`              | `Access-Control-Allow-Origin: *` is present.                                              | Medium   |
| TC-API-GEN-003 | SCN-API-GEN-002 / REQ-API-002 | GET `/health`              | `Access-Control-Allow-Headers` includes `Content-Type`, `Authorization`, and `X-Test-Id`. | Medium   |
| TC-API-GEN-004 | SCN-API-GEN-003 / REQ-API-003 | POST `/health`             | 405 Method Not Allowed.                                                                   | High     |
| TC-API-GEN-005 | SCN-API-GEN-004 / REQ-API-004 | POST `/health`             | 405 response contains `Allow: GET`.                                                       | Medium   |
| TC-API-GEN-006 | SCN-API-GEN-003 / REQ-API-003 | GET `/auth`                | 405 because Auth supports POST.                                                           | High     |
| TC-API-GEN-007 | SCN-API-GEN-005 / REQ-API-005 | OPTIONS `/products`        | 204; preflight completes successfully.                                                    | Medium   |
| TC-API-GEN-008 | SCN-API-GEN-005 / REQ-API-005 | OPTIONS `/products`        | `Access-Control-Allow-Methods` includes GET, POST, PUT, DELETE and OPTIONS.               | Medium   |
| TC-API-GEN-009 | SCN-API-GEN-006 / REQ-API-006 | GET `/does-not-exist`      | 404; body contains `error: ENDPOINT_NOT_FOUND`.                                           | High     |
| TC-API-GEN-010 | SCN-API-GEN-006 / REQ-API-006 | GET `/nested/unknown/path` | Controlled 404 response rather than unhandled server error.                               | High     |
