# QA Commerce Lab — QA Lab Test Cases

## Document Information

**Module:** QA Lab
**Execution Status:** Not Run

---

## TC-LAB-001 — Slow response

**Scenario:** SCN-LAB-001
**Requirement:** REQ-LAB-002
**Priority:** High
**Type:** Async
**Automation Candidate:** Yes

### Steps

1. Open QA Lab.
2. Trigger `ASYNC`.
3. Observe loading behavior and response.

### Expected Result

* Loading state appears.
* Response completes successfully after approximately 1500 ms.
* Interface remains responsive.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-002 — HTTP 204

**Scenario:** SCN-LAB-002
**Requirement:** REQ-LAB-003
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

QA Lab surfaces status `204` without crashing on the empty body.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-003 — HTTP 400

**Scenario:** SCN-LAB-003
**Requirement:** REQ-LAB-004
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

Status `400` and controlled error content are displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-004 — HTTP 401

**Scenario:** SCN-LAB-004
**Requirement:** REQ-LAB-005
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

HTTP 401 result is surfaced without an uncontrolled UI failure.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-005 — HTTP 404

**Scenario:** SCN-LAB-005
**Requirement:** REQ-LAB-006
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

HTTP 404 result is surfaced.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-006 — HTTP 409

**Scenario:** SCN-LAB-006
**Requirement:** REQ-LAB-007
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

HTTP 409 conflict response is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-007 — HTTP 422

**Scenario:** SCN-LAB-007
**Requirement:** REQ-LAB-008
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

* Status is 422.
* Validation details are surfaced.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-008 — HTTP 429

**Scenario:** SCN-LAB-008
**Requirement:** REQ-LAB-009
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

HTTP 429 rate-limit state is surfaced.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-009 — Retry-After header

**Scenario:** SCN-LAB-009
**Requirement:** REQ-LAB-010
**Priority:** Medium
**Type:** Headers
**Automation Candidate:** Yes

### Expected Result

Displayed response information contains:

`Retry-After: 5`

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-010 — HTTP 500

**Scenario:** SCN-LAB-010
**Requirement:** REQ-LAB-011
**Priority:** High
**Type:** Error Handling
**Automation Candidate:** Yes

### Expected Result

* HTTP 500 response is displayed.
* Application does not crash or navigate to a blank page.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-011 — Large response

**Scenario:** SCN-LAB-011
**Requirement:** REQ-LAB-012
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Expected Result

* Response completes successfully.
* Large dataset is handled without breaking the UI.
* API dataset contains 250 expected records.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-012 — Delayed DOM element

**Scenario:** SCN-LAB-012
**Requirement:** REQ-LAB-013, REQ-LAB-014
**Priority:** High
**Type:** Async
**Automation Candidate:** Yes

### Steps

1. Trigger Delayed DOM.
2. Immediately check the dynamic area.
3. Wait for the controlled delay.

### Expected Result

* Element is initially absent.
* Element appears after approximately 900 ms.
* Text indicates that the dynamic element appeared.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-013 — Modal Cancel

**Scenario:** SCN-LAB-013
**Requirement:** REQ-LAB-015
**Priority:** Medium
**Type:** UI
**Automation Candidate:** Yes

### Steps

1. Open the dialog.
2. Select Cancel.

### Expected Result

Dialog closes cleanly.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-LAB-014 — Modal Confirm

**Scenario:** SCN-LAB-014
**Requirement:** REQ-LAB-015
**Priority:** Medium
**Type:** UI
**Automation Candidate:** Yes

### Steps

1. Open dialog.
2. Select Confirm.

### Expected Result

Dialog closes successfully.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

# FILE: compatibility-responsive-test-cases.md

# QA Commerce Lab — Compatibility & Responsive Test Cases

## Document Information

**Module:** Compatibility / Responsive
**Execution Status:** Not Run
