# QA Commerce Lab — Account Test Cases

## Document Information

**Module:** Account
**Execution Status:** Not Run

---

## TC-ACC-001 — Access Account while authenticated

**Scenario:** SCN-ACC-001
**Requirement:** REQ-ACC-001
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

* User is logged in.

### Steps

1. Navigate to `/account`.

### Expected Result

* Account page is displayed.
* User is not redirected to Login.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-002 — Display account details

**Scenario:** SCN-ACC-002
**Requirement:** REQ-ACC-002
**Priority:** Medium
**Type:** Functional
**Automation Candidate:** Yes

### Test Data

Seed account:

* First Name: QA
* Last Name: Tester
* Email: [qa.user@example.com](mailto:qa.user@example.com)

### Expected Result

The account page displays:

* QA Tester
* [qa.user@example.com](mailto:qa.user@example.com)

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-003 — Logout

**Scenario:** SCN-ACC-003
**Requirement:** REQ-ACC-003
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Steps

1. Login.
2. Open Account.
3. Select **Log out**.

### Expected Result

* Logout succeeds.
* User is returned to an unauthenticated state.
* Navigation displays `Sign in`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-004 — Account state removed after logout

**Scenario:** SCN-ACC-004
**Requirement:** REQ-ACC-004
**Priority:** Critical
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Login.
2. Logout.
3. Navigate directly to `/account`.

### Expected Result

Authenticated account information is no longer available.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-005 — Delete confirmation exact value

**Scenario:** SCN-ACC-005
**Requirement:** REQ-ACC-005
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

* Use a dynamically registered account.

### Test Data

`DELETE`

### Steps

1. Open Account.
2. Enter `DELETE`.
3. Select Delete Account.

### Expected Result

Deletion proceeds.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-006 — Lowercase delete confirmation

**Scenario:** SCN-ACC-006
**Requirement:** REQ-ACC-006
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`delete`

### Expected Result

Account is not deleted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-007 — Delete confirmation with leading space

**Scenario:** SCN-ACC-006
**Requirement:** REQ-ACC-006
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

` DELETE`

### Expected Result

Account is not deleted because the confirmation is not an exact match.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-008 — Delete confirmation with trailing space

**Scenario:** SCN-ACC-006
**Requirement:** REQ-ACC-006
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`DELETE `

### Expected Result

Account is not deleted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-009 — Empty delete confirmation

**Scenario:** SCN-ACC-006
**Requirement:** REQ-ACC-006
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

* Account remains active.
* Validation feedback is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-010 — Delete dynamically registered account

**Scenario:** SCN-ACC-007
**Requirement:** REQ-ACC-007
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

Register a unique account.

### Steps

1. Open Account.
2. Enter `DELETE`.
3. Delete the account.

### Expected Result

The dynamically created account is removed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-011 — Logout after account deletion

**Scenario:** SCN-ACC-008
**Requirement:** REQ-ACC-008
**Priority:** High
**Type:** State
**Automation Candidate:** Yes

### Expected Result

After account deletion:

* User is unauthenticated.
* Account navigation no longer displays the deleted user's first name.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-012 — Deleted account cannot login

**Scenario:** SCN-ACC-009
**Requirement:** REQ-ACC-007
**Priority:** Critical
**Type:** Integration
**Automation Candidate:** Yes

### Preconditions

A dynamically created account has been deleted.

### Steps

1. Open Login.
2. Enter the deleted account's email and password.
3. Submit.

### Expected Result

Login is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-ACC-013 — Seed account remains reusable

**Scenario:** SCN-ACC-010
**Requirement:** REQ-ACC-009
**Priority:** High
**Type:** Testability
**Automation Candidate:** Yes

### Test Data

[qa.user@example.com](mailto:qa.user@example.com) / Password123!

### Steps

1. Exercise the Account deletion screen using the seeded account.
2. Login again using seeded credentials.

### Expected Result

The seeded account remains available for future testing.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

# FILE: contact-test-cases.md

# QA Commerce Lab — Contact Test Cases

## Document Information

**Module:** Contact
**Execution Status:** Not Run