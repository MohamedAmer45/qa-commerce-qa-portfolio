# QA Commerce Lab — Navigation Test Cases

## Document Information

**Module:** Navigation
**Application:** QA Commerce Lab
**Base URL:** https://qa-commerce-lab.vercel.app
**Status:** Baseline
**Execution Status:** Not Run

---

## TC-NAV-001 — Global navigation is displayed

**Scenario:** SCN-NAV-001
**Requirement:** REQ-NAV-001
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Preconditions

* Application is available.

### Test Data

None.

### Steps

1. Open the QA Commerce Lab home page.
2. Observe the top navigation bar.
3. Navigate to Products.
4. Observe the navigation bar.
5. Navigate to QA Lab.
6. Observe the navigation bar.
7. Navigate to Contact.

### Expected Result

* Global navigation is visible on each supported page.
* Navigation does not disappear unexpectedly.
* Navigation remains usable.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-002 — Main navigation destinations

**Scenario:** SCN-NAV-002
**Requirement:** REQ-NAV-002
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Preconditions

* Application is available.

### Steps

1. Open the home page.
2. Select **Products**.
3. Verify the Products page opens.
4. Select **QA Lab**.
5. Verify the QA Lab page opens.
6. Select **API Docs**.
7. Verify the API Docs page opens.
8. Select **Contact**.
9. Verify the Contact page opens.

### Expected Result

Each navigation item opens its correct destination.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-003 — Logo navigates to home

**Scenario:** SCN-NAV-003
**Requirement:** REQ-NAV-003
**Priority:** Medium
**Type:** Functional
**Automation Candidate:** Yes

### Preconditions

* User is on a page other than Home.

### Steps

1. Open `/products`.
2. Select the **QA Commerce** logo.

### Expected Result

* User is navigated to `/`.
* Home-page content is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-004 — Sign In displayed for unauthenticated user

**Scenario:** SCN-NAV-004
**Requirement:** REQ-NAV-004
**Priority:** High
**Type:** State
**Automation Candidate:** Yes

### Preconditions

* Clear application browser storage.
* User is logged out.

### Steps

1. Open the home page.
2. Observe the account area of navigation.

### Expected Result

* **Sign in** is displayed.
* No previous user's name is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-005 — User name displayed after login

**Scenario:** SCN-NAV-005
**Requirement:** REQ-NAV-005
**Priority:** Medium
**Type:** State
**Automation Candidate:** Yes

### Preconditions

* User is logged out.

### Test Data

```text
Email: qa.user@example.com
Password: Password123!
```

### Steps

1. Open Sign In.
2. Enter valid credentials.
3. Submit the login form.
4. Observe the navigation.

### Expected Result

* Login succeeds.
* **QA** is displayed in the account-navigation area instead of **Sign in**.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-006 — Cart indicator updates

**Scenario:** SCN-NAV-006
**Requirement:** REQ-NAV-006
**Priority:** High
**Type:** Integration
**Automation Candidate:** Yes

### Preconditions

* Cart is empty.

### Steps

1. Verify the cart indicator shows `0`.
2. Open Products.
3. Add one available product.
4. Observe the cart indicator.
5. Add the same product again.
6. Observe the indicator.
7. Open Cart.
8. Remove the product.

### Expected Result

* Initial indicator is `0`.
* After the first add it becomes `1`.
* After the second add it becomes `2`.
* After removing the product it returns to `0`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-007 — Unknown route displays 404

**Scenario:** SCN-NAV-007
**Requirement:** REQ-NAV-007
**Priority:** Medium
**Type:** Negative
**Automation Candidate:** Yes

### Steps

1. Navigate directly to:

`https://qa-commerce-lab.vercel.app/this-page-does-not-exist`

### Expected Result

* A controlled 404 state is displayed.
* Application does not display a blank page.
* User can navigate back to Home.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-008 — Browser Back navigation

**Scenario:** SCN-NAV-008
**Requirement:** REQ-NAV-008
**Priority:** Medium
**Type:** Navigation
**Automation Candidate:** Yes

### Steps

1. Open Home.
2. Navigate to Products.
3. Navigate to Contact.
4. Use the browser Back button once.
5. Use Back again.

### Expected Result

Navigation sequence is:

```text
Contact
↓ Back
Products
↓ Back
Home
```

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NAV-009 — Browser Forward navigation

**Scenario:** SCN-NAV-008
**Requirement:** REQ-NAV-008
**Priority:** Medium
**Type:** Navigation
**Automation Candidate:** Yes

### Preconditions

TC-NAV-008 navigation sequence has been performed.

### Steps

1. From Home, use browser Forward.
2. Verify Products opens.
3. Use Forward again.

### Expected Result

Navigation sequence is:

```text
Home
↓ Forward
Products
↓ Forward
Contact
```

**Status:** Not Run
**Actual Result:** —
**Defect:** —
