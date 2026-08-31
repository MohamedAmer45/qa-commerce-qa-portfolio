# QA Commerce Lab — Authentication Test Cases

## Document Information

**Module:** Authentication
**Application:** QA Commerce Lab
**Execution Status:** Not Run

---

## TC-AUTH-001 — Login with valid seeded account

**Scenario:** SCN-AUTH-001
**Requirement:** REQ-AUTH-001
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

* User is logged out.

### Test Data

```text
Email: qa.user@example.com
Password: Password123!
```

### Steps

1. Open `/login`.
2. Enter the valid email.
3. Enter the valid password.
4. Select **Sign in**.

### Expected Result

* Login succeeds.
* Account page is displayed.
* Navigation displays `QA`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-002 — Login using registered dynamic account

**Scenario:** SCN-AUTH-002
**Requirement:** REQ-AUTH-001
**Priority:** Critical
**Type:** Integration
**Automation Candidate:** Yes

### Preconditions

Create a valid account using Registration.

### Test Data

Example:

```text
Email: qa.dynamic01@example.com
Password: Dynamic123!
```

### Steps

1. Log out after registration.
2. Open Login.
3. Enter the newly registered email.
4. Enter its password.
5. Submit.

### Expected Result

The newly created account successfully authenticates.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-003 — Email is case-insensitive

**Scenario:** SCN-AUTH-003
**Requirement:** REQ-AUTH-002
**Priority:** High
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

```text
Email: QA.USER@EXAMPLE.COM
Password: Password123!
```

### Steps

1. Open Login.
2. Enter the uppercase email.
3. Enter the correct password.
4. Submit.

### Expected Result

Login succeeds.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-004 — Leading email whitespace

**Scenario:** SCN-AUTH-004
**Requirement:** REQ-AUTH-003
**Priority:** Medium
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

```text
Email: "   qa.user@example.com"
Password: Password123!
```

### Steps

1. Enter the email with leading spaces.
2. Enter the valid password.
3. Submit.

### Expected Result

Leading spaces are ignored and login succeeds.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-005 — Trailing email whitespace

**Scenario:** SCN-AUTH-004
**Requirement:** REQ-AUTH-003
**Priority:** Medium
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

```text
Email: "qa.user@example.com   "
Password: Password123!
```

### Expected Result

Trailing spaces are ignored and login succeeds.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-006 — Incorrect password capitalization

**Scenario:** SCN-AUTH-005
**Requirement:** REQ-AUTH-004
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Email: qa.user@example.com
Password: password123!
```

### Steps

1. Open Login.
2. Enter the valid email.
3. Enter the incorrectly capitalized password.
4. Submit.

### Expected Result

* Login fails.
* User remains unauthenticated.
* Invalid-credentials feedback is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-007 — Empty email

**Scenario:** SCN-AUTH-006
**Requirement:** REQ-AUTH-005
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Email: empty
Password: Password123!
```

### Expected Result

Login is rejected and required-credentials feedback is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-008 — Empty password

**Scenario:** SCN-AUTH-006
**Requirement:** REQ-AUTH-006
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Email: qa.user@example.com
Password: empty
```

### Expected Result

Login is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-009 — Both credentials empty

**Scenario:** SCN-AUTH-006
**Requirement:** REQ-AUTH-007
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Steps

1. Leave Email empty.
2. Leave Password empty.
3. Submit.

### Expected Result

* Login is rejected.
* User remains unauthenticated.
* Required-credentials message is shown.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-010 — Email missing @

**Scenario:** SCN-AUTH-007
**Requirement:** REQ-AUTH-008
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
qa.userexample.com
```

### Expected Result

Invalid email format is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-011 — Email missing domain

**Scenario:** SCN-AUTH-007
**Requirement:** REQ-AUTH-008
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
qa.user@
```

### Expected Result

Invalid email format is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-012 — Email missing top-level domain

**Scenario:** SCN-AUTH-007
**Requirement:** REQ-AUTH-008
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
qa.user@example
```

### Expected Result

Invalid email format is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-013 — Incorrect email with valid password

**Scenario:** SCN-AUTH-008
**Requirement:** REQ-AUTH-009
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Email: nonexistent@example.com
Password: Password123!
```

### Expected Result

* Login fails.
* Invalid credentials message is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-014 — Valid email with incorrect password

**Scenario:** SCN-AUTH-008
**Requirement:** REQ-AUTH-009
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Email: qa.user@example.com
Password: WrongPassword123!
```

### Expected Result

Login fails.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-015 — Both credentials incorrect

**Scenario:** SCN-AUTH-008
**Requirement:** REQ-AUTH-009
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

```text
Email: wrong@example.com
Password: WrongPassword123!
```

### Expected Result

Login fails and no authenticated state is created.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-016 — Authenticated state established

**Scenario:** SCN-AUTH-009
**Requirement:** REQ-AUTH-010
**Priority:** Critical
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Log in successfully.
2. Navigate to Products.
3. Navigate to Cart.
4. Navigate back to Account.

### Expected Result

User remains authenticated across application navigation.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-017 — Successful login redirects to Account

**Scenario:** SCN-AUTH-010
**Requirement:** REQ-AUTH-011
**Priority:** High
**Type:** Navigation
**Automation Candidate:** Yes

### Steps

1. Open `/login`.
2. Enter valid credentials.
3. Submit.

### Expected Result

URL becomes:

```text
/account
```

and the Account page is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-018 — Authentication survives page refresh

**Scenario:** SCN-AUTH-012
**Requirement:** REQ-AUTH-012
**Priority:** High
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Login successfully.
2. Refresh the Account page.

### Expected Result

* User remains authenticated.
* Account details remain visible.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-AUTH-019 — Authentication survives direct navigation

**Scenario:** SCN-AUTH-011
**Requirement:** REQ-AUTH-012
**Priority:** High
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Login successfully.
2. Enter `/products` directly in the browser.
3. Navigate directly to `/account`.

### Expected Result

The authenticated user state persists.

**Status:** Not Run
**Actual Result:** —
**Defect:** —
