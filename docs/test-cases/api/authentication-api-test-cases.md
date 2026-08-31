# QA Commerce Lab — Authentication API Test Cases

**Endpoint:** `POST /api/auth`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Valid Payload

```json
{
  "email": "qa.user@example.com",
  "password": "Password123!"
}
```

## Standard Execution Procedure

1. Prepare the request body.
2. Send `POST /api/auth`.
3. Record the response status.
4. Inspect response headers.
5. Parse the JSON response.
6. Verify all case-specific assertions.

| ID              | Scenario / Requirement              | Test Data                                              | Expected Result                                                       | Priority |
| --------------- | ----------------------------------- | ------------------------------------------------------ | --------------------------------------------------------------------- | -------- |
| TC-API-AUTH-001 | SCN-API-AUTH-001 / REQ-API-AUTH-001 | Valid seeded credentials                               | HTTP 200; authentication succeeds.                                    | Critical |
| TC-API-AUTH-002 | SCN-API-AUTH-005 / REQ-API-AUTH-005 | Valid seeded credentials                               | HTTP status is exactly 200.                                           | Critical |
| TC-API-AUTH-003 | SCN-API-AUTH-006 / REQ-API-AUTH-006 | Valid seeded credentials                               | A non-empty authentication token is returned.                         | Critical |
| TC-API-AUTH-004 | SCN-API-AUTH-007 / REQ-API-AUTH-007 | Valid seeded credentials                               | User ID, first name, last name, and email are returned.               | High     |
| TC-API-AUTH-005 | SCN-API-AUTH-008 / REQ-API-AUTH-007 | Valid seeded credentials                               | Response does not expose the user's password.                         | Critical |
| TC-API-AUTH-006 | SCN-API-AUTH-001 / REQ-API-AUTH-001 | Email `QA.USER@EXAMPLE.COM`                            | Authentication succeeds because email comparison is case-insensitive. | High     |
| TC-API-AUTH-007 | SCN-API-AUTH-001 / REQ-API-AUTH-001 | Email `"   qa.user@example.com   "`                    | Surrounding whitespace is removed and authentication succeeds.        | Medium   |
| TC-API-AUTH-008 | SCN-API-AUTH-002 / REQ-API-AUTH-002 | Email missing; password supplied                       | HTTP 400; missing-credentials validation is returned.                 | High     |
| TC-API-AUTH-009 | SCN-API-AUTH-002 / REQ-API-AUTH-002 | Password missing; email supplied                       | HTTP 400; missing-credentials validation is returned.                 | High     |
| TC-API-AUTH-010 | SCN-API-AUTH-002 / REQ-API-AUTH-002 | Empty body `{}`                                        | HTTP 400; missing-credentials validation is returned.                 | High     |
| TC-API-AUTH-011 | SCN-API-AUTH-003 / REQ-API-AUTH-003 | `email: invalid-email` with valid password             | HTTP 422; invalid-email validation is returned.                       | High     |
| TC-API-AUTH-012 | SCN-API-AUTH-004 / REQ-API-AUTH-004 | Correct email with incorrect password                  | HTTP 401; invalid-credentials response is returned.                   | Critical |
| TC-API-AUTH-013 | SCN-API-AUTH-004 / REQ-API-AUTH-004 | Unknown valid-format email with valid-looking password | HTTP 401; invalid-credentials response is returned.                   | Critical |
| TC-API-AUTH-014 | SCN-API-AUTH-004 / REQ-API-AUTH-004 | Password `password123!`                                | Authentication fails because password comparison is case-sensitive.   | High     |

## Execution Fields

For each case during execution, record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
