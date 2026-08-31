# QA Commerce Lab — Contact Test Cases

## TC-CON-001 — Submit valid request without attachment

**Scenario:** SCN-CON-001
**Requirement:** REQ-CON-001
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

Name: QA Tester
Email: [qa@example.com](mailto:qa@example.com)
Subject: Technical problem
Message: `This is a valid support request for QA testing.`

### Expected Result

Support request is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-002 — Submit valid request with attachment

**Scenario:** SCN-CON-002
**Requirement:** REQ-CON-001, REQ-CON-007
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

Valid PNG/JPG/PDF file under 2 MB.

### Expected Result

Support request with attachment is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-003 — Name with 1 character

**Scenario:** SCN-CON-003
**Requirement:** REQ-CON-002
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`A`

### Expected Result

Name is rejected as too short.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-004 — Name with 2 characters

**Scenario:** SCN-CON-003
**Requirement:** REQ-CON-002
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`QA`

### Expected Result

Name satisfies the minimum-length rule.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-005 — Invalid email

**Scenario:** SCN-CON-004
**Requirement:** REQ-CON-003
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`qaexample.com`

### Expected Result

Submission is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-006 — Empty subject

**Scenario:** SCN-CON-005
**Requirement:** REQ-CON-004
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

A subject selection is required.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-007 — Validate supported subjects

**Scenario:** SCN-CON-006
**Requirement:** REQ-CON-005
**Priority:** Medium
**Type:** Data Driven
**Automation Candidate:** Yes

### Test Data

Execute with:

* Order issue
* Product question
* Technical problem
* Other

### Expected Result

Each documented subject is accepted with otherwise valid form data.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-008 — Message with 19 characters

**Scenario:** SCN-CON-007
**Requirement:** REQ-CON-006
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Message is rejected as too short.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-009 — Message with exactly 20 characters

**Scenario:** SCN-CON-007
**Requirement:** REQ-CON-006
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Message is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-010 — Message with exactly 1000 characters

**Scenario:** SCN-CON-008
**Requirement:** REQ-CON-006
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

1000 characters are accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-011 — Attempt message longer than 1000 characters

**Scenario:** SCN-CON-008
**Requirement:** REQ-CON-006
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

The input does not retain more than the documented maximum.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-012 — PNG attachment

**Scenario:** SCN-CON-009
**Requirement:** REQ-CON-008
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Expected Result

PNG attachment is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-013 — JPG/JPEG attachment

**Scenario:** SCN-CON-009
**Requirement:** REQ-CON-008
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Expected Result

JPG/JPEG attachments are accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-014 — PDF attachment

**Scenario:** SCN-CON-009
**Requirement:** REQ-CON-008
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Expected Result

PDF attachment is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-015 — Unsupported attachment

**Scenario:** SCN-CON-010
**Requirement:** REQ-CON-008
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`test.exe`

### Expected Result

File is rejected as unsupported.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-016 — Attachment just below 2 MB

**Scenario:** SCN-CON-011
**Requirement:** REQ-CON-009
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Supported file below the maximum size is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-017 — Attachment exactly 2 MB

**Scenario:** SCN-CON-011
**Requirement:** REQ-CON-009
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

2 MB attachment is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-018 — Attachment above 2 MB

**Scenario:** SCN-CON-011
**Requirement:** REQ-CON-009
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Attachment is rejected as too large.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CON-019 — Successful submission feedback

**Scenario:** SCN-CON-012
**Requirement:** REQ-CON-010, REQ-NFR-006
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Expected Result

`Support request accepted.` is displayed after valid submission.

**Status:** Not Run
**Actual Result:** —
**Defect:** —
