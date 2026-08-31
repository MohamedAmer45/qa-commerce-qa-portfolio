# QA Commerce Lab — Contact API Test Cases

**Endpoint:** `POST /api/contact`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Valid Payload

```json
{
  "name": "QA Tester",
  "email": "qa@example.com",
  "subject": "Technical problem",
  "message": "This is a valid API support request."
}
```

## Standard Execution Procedure

1. Prepare the contact request body.
2. Send `POST /api/contact`.
3. Record the HTTP status.
4. Parse the response body.
5. Verify validation or acceptance details.

| ID             | Scenario / Requirement            | Test Data                                             | Expected Result                                                              | Priority |
| -------------- | --------------------------------- | ----------------------------------------------------- | ---------------------------------------------------------------------------- | -------- |
| TC-API-CON-001 | SCN-API-CON-001 / REQ-API-CON-001 | Valid payload                                         | HTTP 202; support request is accepted.                                       | High     |
| TC-API-CON-002 | SCN-API-CON-002 / REQ-API-CON-002 | Name length 1                                         | HTTP 422; name validation is returned.                                       | Medium   |
| TC-API-CON-003 | SCN-API-CON-002 / REQ-API-CON-002 | Name length 2                                         | Request is accepted when all other fields are valid.                         | Medium   |
| TC-API-CON-004 | SCN-API-CON-003 / REQ-API-CON-003 | Email `qaexample.com`                                 | HTTP 422; invalid-email validation is returned.                              | High     |
| TC-API-CON-005 | SCN-API-CON-003 / REQ-API-CON-003 | Email `qa@`                                           | HTTP 422.                                                                    | High     |
| TC-API-CON-006 | SCN-API-CON-004 / REQ-API-CON-004 | Missing `subject`                                     | HTTP 422; subject validation is returned.                                    | High     |
| TC-API-CON-007 | SCN-API-CON-004 / REQ-API-CON-004 | Unsupported subject `Billing`                         | HTTP 422; unsupported subject is rejected.                                   | High     |
| TC-API-CON-008 | SCN-API-CON-004 / REQ-API-CON-004 | Subject `Order issue`                                 | Accepted when all other fields are valid.                                    | High     |
| TC-API-CON-009 | SCN-API-CON-004 / REQ-API-CON-004 | Subject `Product question`                            | Accepted when all other fields are valid.                                    | High     |
| TC-API-CON-010 | SCN-API-CON-004 / REQ-API-CON-004 | Subject `Technical problem`                           | Accepted when all other fields are valid.                                    | High     |
| TC-API-CON-011 | SCN-API-CON-004 / REQ-API-CON-004 | Subject `Other`                                       | Accepted when all other fields are valid.                                    | High     |
| TC-API-CON-012 | SCN-API-CON-005 / REQ-API-CON-005 | Message length 19 after trimming                      | HTTP 422; message is rejected as too short.                                  | High     |
| TC-API-CON-013 | SCN-API-CON-005 / REQ-API-CON-005 | Message length exactly 20                             | Accepted.                                                                    | High     |
| TC-API-CON-014 | SCN-API-CON-005 / REQ-API-CON-005 | Message length exactly 1000                           | Accepted.                                                                    | High     |
| TC-API-CON-015 | SCN-API-CON-005 / REQ-API-CON-005 | Message length 1001                                   | HTTP 422; maximum-length validation is returned.                             | High     |
| TC-API-CON-016 | SCN-API-CON-006 / REQ-API-CON-006 | Empty body `{}`                                       | HTTP 422; controlled validation response identifies invalid required fields. | High     |
| TC-API-CON-017 | SCN-API-CON-006 / REQ-API-CON-006 | Missing `name` only                                   | HTTP 422; name validation is returned without uncontrolled failure.          | High     |
| TC-API-CON-018 | SCN-API-CON-006 / REQ-API-CON-006 | Missing `email` only                                  | HTTP 422; email validation is returned.                                      | High     |
| TC-API-CON-019 | SCN-API-CON-006 / REQ-API-CON-006 | Missing `message` only                                | HTTP 422; message validation is returned.                                    | High     |
| TC-API-CON-020 | SCN-API-CON-007 / REQ-API-CON-007 | Valid payload                                         | HTTP 202; response indicates the request was accepted.                       | High     |
| TC-API-CON-021 | SCN-API-CON-007 / REQ-API-CON-007 | Valid payload                                         | Response contains a support ticket/reference identifier.                     | High     |
| TC-API-CON-022 | SCN-API-CON-008 / REQ-API-CON-008 | Valid payload plus `"extraField":"QA"`                | Unknown additional field does not cause an uncontrolled HTTP 500 error.      | Medium   |
| TC-API-CON-023 | SCN-API-CON-008 / REQ-API-CON-008 | Valid payload containing Unicode text in name/message | Unicode input is processed without corruption or server failure.             | Medium   |
| TC-API-CON-024 | SCN-API-CON-006 / REQ-API-CON-006 | Message contains only spaces                          | HTTP 422 because trimmed message does not meet minimum length.               | High     |

## Execution Fields

For each executed test case record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
