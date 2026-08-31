# QA Commerce Lab — Reviews API Test Cases

**Endpoint:** `POST /api/reviews`
**Execution Status:** Not Run
**Automation Candidate:** Yes

## Valid Payload

```json
{
  "productId": 1,
  "rating": 5,
  "title": "Excellent",
  "body": "This product worked very well during QA testing.",
  "email": "qa@example.com"
}
```

## Standard Execution Procedure

1. Prepare the review payload.
2. Send `POST /api/reviews`.
3. Record the HTTP status.
4. Parse the response.
5. Verify field boundaries and response assertions.

| ID             | Scenario / Requirement            | Test Data                    | Expected Result                                | Priority |
| -------------- | --------------------------------- | ---------------------------- | ---------------------------------------------- | -------- |
| TC-API-REV-001 | SCN-API-REV-001 / REQ-API-REV-001 | Valid payload                | HTTP 201; review is created.                   | Medium   |
| TC-API-REV-002 | SCN-API-REV-002 / REQ-API-REV-002 | Product ID `99999`           | HTTP 404; product-not-found response returned. | High     |
| TC-API-REV-003 | SCN-API-REV-003 / REQ-API-REV-003 | Rating `1`                   | Accepted.                                      | High     |
| TC-API-REV-004 | SCN-API-REV-003 / REQ-API-REV-003 | Rating `5`                   | Accepted.                                      | High     |
| TC-API-REV-005 | SCN-API-REV-003 / REQ-API-REV-003 | Rating `0`                   | HTTP 422.                                      | High     |
| TC-API-REV-006 | SCN-API-REV-003 / REQ-API-REV-003 | Rating `6`                   | HTTP 422.                                      | High     |
| TC-API-REV-007 | SCN-API-REV-003 / REQ-API-REV-003 | Rating `1.5`                 | HTTP 422 because rating must be an integer.    | High     |
| TC-API-REV-008 | SCN-API-REV-004 / REQ-API-REV-004 | Title length 2               | HTTP 422.                                      | Medium   |
| TC-API-REV-009 | SCN-API-REV-004 / REQ-API-REV-004 | Title length 3               | Accepted.                                      | Medium   |
| TC-API-REV-010 | SCN-API-REV-005 / REQ-API-REV-005 | Body length 9 after trimming | HTTP 422.                                      | Medium   |
| TC-API-REV-011 | SCN-API-REV-005 / REQ-API-REV-005 | Body length 10               | Accepted.                                      | Medium   |
| TC-API-REV-012 | SCN-API-REV-005 / REQ-API-REV-005 | Body length 500              | Accepted.                                      | Medium   |
| TC-API-REV-013 | SCN-API-REV-005 / REQ-API-REV-005 | Body length 501              | HTTP 422.                                      | Medium   |
| TC-API-REV-014 | SCN-API-REV-006 / REQ-API-REV-006 | Invalid email                | HTTP 422.                                      | High     |
| TC-API-REV-015 | SCN-API-REV-007 / REQ-API-REV-007 | Valid review                 | HTTP 201 and review ID is returned.            | Medium   |

## Execution Fields

For each executed test case record:

* **Status:** Not Run / Pass / Fail / Blocked / Skipped
* **Actual Result:** —
* **Defect:** —
