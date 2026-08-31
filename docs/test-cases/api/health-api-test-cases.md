# QA Commerce Lab — Health API Test Cases

**Endpoint:** `GET /api/health`
**Execution Status:** Not Run

| ID             | Scenario / Requirement            | Request / Test Data | Expected Result                                                      | Priority |
| -------------- | --------------------------------- | ------------------- | -------------------------------------------------------------------- | -------- |
| TC-API-HLT-001 | SCN-API-HLT-001 / REQ-API-HLT-001 | GET `/health`       | HTTP 200.                                                            | Critical |
| TC-API-HLT-002 | SCN-API-HLT-002 / REQ-API-HLT-002 | GET `/health`       | `status` equals `ok`; `service` equals `qa-commerce-api`.            | High     |
| TC-API-HLT-003 | SCN-API-HLT-002 / REQ-API-HLT-002 | GET `/health`       | `version` and `environment` are returned; environment equals `test`. | High     |
| TC-API-HLT-004 | SCN-API-HLT-003 / REQ-API-HLT-003 | GET `/health`       | Response contains a parseable timestamp and JSON Content-Type.       | High     |
