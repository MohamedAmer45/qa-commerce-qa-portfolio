# QA Commerce Lab — Registration Test Cases

## Document Information

**Module:** Registration
**Application:** QA Commerce Lab
**Execution Status:** Not Run

---

## TC-REG-001 — Valid registration

**Scenario:** SCN-REG-001
**Requirement:** REQ-REG-001
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

* Email has not previously been registered.

### Test Data

```text
First Name: Mohamed
Last Name: Tester
Email: qa.new01@example.com
Password: TestPass123!
Confirm Password: TestPass123!
Terms: Accepted
```

### Steps

1. Open `/register`.
2. Enter valid first name.
3. Enter valid last name.
4. Enter unique valid email.
5. Enter valid password.
6. Enter matching confirmation.
7. Accept Terms.
8. Select **Create account**.

### Expected Result

* Registration succeeds.
* User becomes authenticated.
* User is taken to Account.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-002 — Empty first name

**Scenario:** SCN-REG-002
**Requirement:** REQ-REG-002
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

First Name: empty
All other information: valid.

### Expected Result

Registration is rejected and names-required feedback appears.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-003 — Empty last name

**Scenario:** SCN-REG-002
**Requirement:** REQ-REG-003
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Registration is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-004 — Both names empty

**Scenario:** SCN-REG-002
**Requirement:** REQ-REG-002, REQ-REG-003
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Account is not created.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-005 — First name exactly 40 characters

**Scenario:** SCN-REG-003
**Requirement:** REQ-REG-004
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

```text
First Name: A × 40
```

### Expected Result

The full 40-character value is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-006 — First name attempts 41 characters

**Scenario:** SCN-REG-003
**Requirement:** REQ-REG-004
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

```text
First Name: A × 41
```

### Expected Result

The field does not accept more than 40 characters.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-007 — Last name exactly 40 characters

**Scenario:** SCN-REG-004
**Requirement:** REQ-REG-004
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

40 characters are accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-008 — Last name attempts 41 characters

**Scenario:** SCN-REG-004
**Requirement:** REQ-REG-004
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

The field is capped at 40 characters.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-009 — Empty email

**Scenario:** SCN-REG-005
**Requirement:** REQ-REG-005
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Registration is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-010 — Email without @

**Scenario:** SCN-REG-006
**Requirement:** REQ-REG-005
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
qa.userexample.com
```

### Expected Result

Invalid email is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-011 — Email without domain

**Scenario:** SCN-REG-006
**Requirement:** REQ-REG-005
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
qa.user@
```

### Expected Result

Invalid email is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-012 — Valid normal email

**Scenario:** SCN-REG-006
**Requirement:** REQ-REG-005
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

```text
qa.valid02@example.com
```

### Expected Result

Email format is accepted when all other fields are valid.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-013 — Duplicate seeded email

**Scenario:** SCN-REG-008
**Requirement:** REQ-REG-007
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
qa.user@example.com
```

### Expected Result

* Registration is rejected.
* `Email already exists.` feedback is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-014 — Duplicate email with different capitalization

**Scenario:** SCN-REG-009
**Requirement:** REQ-REG-008
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
QA.USER@EXAMPLE.COM
```

### Expected Result

Registration is rejected as duplicate.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-015 — Password with 7 characters

**Scenario:** SCN-REG-010
**Requirement:** REQ-REG-009
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

Use a 7-character value that otherwise attempts to satisfy complexity.

### Expected Result

Registration is rejected due to password length.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-016 — Password with exactly 8 characters

**Scenario:** SCN-REG-010
**Requirement:** REQ-REG-009
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

```text
Qa1!abcd
```

### Expected Result

Password is accepted when all other registration data is valid.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-017 — Password exactly 64 characters

**Scenario:** SCN-REG-010
**Requirement:** REQ-REG-009
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

64-character password satisfying all complexity rules.

### Expected Result

64 characters are accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-018 — Attempt password longer than 64 characters

**Scenario:** SCN-REG-010
**Requirement:** REQ-REG-009
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Steps

1. Attempt to enter a 65-character password.

### Expected Result

Password input does not retain more than 64 characters.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-019 — Password missing uppercase character

**Scenario:** SCN-REG-011
**Requirement:** REQ-REG-010
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
password123!
```

### Expected Result

Registration is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-020 — Password missing lowercase character

**Scenario:** SCN-REG-011
**Requirement:** REQ-REG-011
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
PASSWORD123!
```

### Expected Result

Registration is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-021 — Password missing number

**Scenario:** SCN-REG-011
**Requirement:** REQ-REG-012
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
PasswordTest!
```

### Expected Result

Registration is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-022 — Password missing special character

**Scenario:** SCN-REG-011
**Requirement:** REQ-REG-013
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Password123
```

### Expected Result

Registration is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-023 — Password confirmation mismatch

**Scenario:** SCN-REG-012
**Requirement:** REQ-REG-014
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Password: Password123!
Confirmation: Password124!
```

### Expected Result

* Registration fails.
* Password-mismatch feedback is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-024 — Empty password confirmation

**Scenario:** SCN-REG-012
**Requirement:** REQ-REG-014
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Registration fails because confirmation does not match the password.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-025 — Terms not accepted

**Scenario:** SCN-REG-013
**Requirement:** REQ-REG-015
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Preconditions

All registration information is otherwise valid.

### Steps

1. Complete registration fields.
2. Leave Terms unchecked.
3. Submit.

### Expected Result

Account creation is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-026 — Automatic authentication after registration

**Scenario:** SCN-REG-014
**Requirement:** REQ-REG-016
**Priority:** High
**Type:** Integration
**Automation Candidate:** Yes

### Steps

1. Register a unique valid account.
2. Observe the application after submission.

### Expected Result

* User is immediately authenticated.
* No additional login is required.
* Navigation displays the user's first name.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-027 — Redirect to Account after registration

**Scenario:** SCN-REG-015
**Requirement:** REQ-REG-017
**Priority:** High
**Type:** Navigation
**Automation Candidate:** Yes

### Expected Result

After successful account creation:

```text
URL: /account
```

Account details are displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-028 — Unicode first and last name

**Scenario:** SCN-REG-016
**Requirement:** REQ-REG-001, REQ-REG-004
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

Example:

```text
First Name: محمد
Last Name: أحمد
```

### Expected Result

If the input is within documented length limits, the application handles the Unicode text without breaking the registration workflow or interface.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-029 — Names with surrounding spaces

**Scenario:** SCN-REG-016
**Requirement:** REQ-REG-001
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

```text
First Name: "   Mohamed   "
Last Name: "   Tester   "
```

### Expected Result

Registration successfully handles the values and stores/displays the normalized names without unintended surrounding whitespace.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-REG-030 — Special characters in name

**Scenario:** SCN-REG-016
**Requirement:** REQ-REG-001
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

```text
First Name: Anne-Marie
Last Name: O'Connor
```

### Expected Result

Common human-name punctuation does not cause an application failure.

**Status:** Not Run
**Actual Result:** —
**Defect:** —
