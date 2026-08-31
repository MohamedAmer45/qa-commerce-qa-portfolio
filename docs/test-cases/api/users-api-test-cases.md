# QA Commerce Lab — Users API Test Cases

**Endpoint:** `/api/users`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Valid Creation Payload

```json
{
  "firstName": "API",
  "lastName": "Tester",
  "email": "api.user01@example.com",
  "password": "Password123!"
}
```

## Standard Execution Procedure

1. Prepare the required HTTP method and request body.
2. Send the request.
3. Record the response status.
4. Inspect response headers.
5. Parse the response body.
6. Verify all case-specific assertions.

| ID             | Scenario / Requirement            | Method / Test Data                                  | Expected Result                                                     | Priority |
| -------------- | --------------------------------- | --------------------------------------------------- | ------------------------------------------------------------------- | -------- |
| TC-API-USR-001 | SCN-API-USR-001 / REQ-API-USR-001 | POST valid user payload                             | HTTP 201; user is created and user data is returned.                | Critical |
| TC-API-USR-002 | SCN-API-USR-002 / REQ-API-USR-002 | POST with empty `firstName`                         | HTTP 422; first-name validation is returned.                        | High     |
| TC-API-USR-003 | SCN-API-USR-002 / REQ-API-USR-002 | POST with empty `lastName`                          | HTTP 422; last-name validation is returned.                         | High     |
| TC-API-USR-004 | SCN-API-USR-002 / REQ-API-USR-002 | First name exactly 40 characters                    | Accepted when all other fields are valid.                           | High     |
| TC-API-USR-005 | SCN-API-USR-002 / REQ-API-USR-002 | First name 41 characters                            | HTTP 422.                                                           | High     |
| TC-API-USR-006 | SCN-API-USR-002 / REQ-API-USR-002 | Last name 41 characters                             | HTTP 422.                                                           | High     |
| TC-API-USR-007 | SCN-API-USR-003 / REQ-API-USR-003 | Invalid email format                                | HTTP 422; email validation is returned.                             | High     |
| TC-API-USR-008 | SCN-API-USR-004 / REQ-API-USR-004 | Password with 7 characters                          | HTTP 422.                                                           | High     |
| TC-API-USR-009 | SCN-API-USR-004 / REQ-API-USR-004 | Valid 8-character complex password                  | Password is accepted.                                               | High     |
| TC-API-USR-010 | SCN-API-USR-004 / REQ-API-USR-004 | Password missing uppercase character                | HTTP 422.                                                           | High     |
| TC-API-USR-011 | SCN-API-USR-004 / REQ-API-USR-004 | Password missing lowercase character                | HTTP 422.                                                           | High     |
| TC-API-USR-012 | SCN-API-USR-004 / REQ-API-USR-004 | Password missing number                             | HTTP 422.                                                           | High     |
| TC-API-USR-013 | SCN-API-USR-004 / REQ-API-USR-004 | Password missing special character                  | HTTP 422.                                                           | High     |
| TC-API-USR-014 | SCN-API-USR-006 / REQ-API-USR-006 | POST using `qa.user@example.com`                    | HTTP 409; duplicate seeded email is rejected.                       | High     |
| TC-API-USR-015 | SCN-API-USR-007 / REQ-API-USR-007 | Valid POST                                          | HTTP 201; response does not expose password.                        | Critical |
| TC-API-USR-016 | SCN-API-USR-008 / REQ-API-USR-008 | PUT `{}`                                            | HTTP 400; user ID is required.                                      | High     |
| TC-API-USR-017 | SCN-API-USR-008 / REQ-API-USR-008 | PUT `{"id":"usr_test","firstName":"Updated"}`       | HTTP 200; ID remains unchanged and updated information is returned. | High     |
| TC-API-USR-018 | SCN-API-USR-009 / REQ-API-USR-009 | DELETE without ID                                   | HTTP 400; user ID is required.                                      | Critical |
| TC-API-USR-019 | SCN-API-USR-009 / REQ-API-USR-010 | DELETE with ID but missing confirmation             | HTTP 422; delete confirmation is required.                          | Critical |
| TC-API-USR-020 | SCN-API-USR-009 / REQ-API-USR-010 | Confirmation `delete`                               | HTTP 422 because confirmation must exactly equal `DELETE`.          | Critical |
| TC-API-USR-021 | SCN-API-USR-010 / REQ-API-USR-011 | DELETE seeded user with confirmation `DELETE`       | HTTP 409; seeded account remains protected.                         | High     |
| TC-API-USR-022 | SCN-API-USR-011 / REQ-API-USR-012 | DELETE dynamic test user with confirmation `DELETE` | HTTP 200; deletion succeeds and deleted user ID is returned.        | High     |

## Execution Fields

For each case during execution, record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
