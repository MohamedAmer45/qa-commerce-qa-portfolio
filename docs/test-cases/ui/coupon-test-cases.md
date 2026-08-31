# QA Commerce Lab — Checkout Test Cases

## Document Information

**Module:** Checkout
**Execution Status:** Not Run

---

## TC-CHK-001 — Unauthenticated checkout

**Scenario:** SCN-CHK-001
**Requirement:** REQ-CHK-001
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Preconditions

* User logged out.
* Cart contains a product.

### Steps

1. Navigate directly to `/checkout`.

### Expected Result

* Checkout form is not displayed.
* Sign-in requirement is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-002 — Checkout with empty cart

**Scenario:** SCN-CHK-002
**Requirement:** REQ-CHK-002
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Preconditions

* User is authenticated.
* Cart is empty.

### Expected Result

Empty-cart state prevents checkout.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-003 — Pre-populated account data

**Scenario:** SCN-CHK-003
**Requirement:** REQ-CHK-003
**Priority:** Medium
**Type:** Integration
**Automation Candidate:** Yes

### Test Data

Seed account.

### Expected Result

Checkout automatically populates:

* First Name: QA
* Last Name: Tester
* Email: [qa.user@example.com](mailto:qa.user@example.com)

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-004 — Empty first name

**Scenario:** SCN-CHK-004
**Requirement:** REQ-CHK-004
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Order submission is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-005 — Empty last name

**Scenario:** SCN-CHK-004
**Requirement:** REQ-CHK-005
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Order submission is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-006 — Empty email

**Scenario:** SCN-CHK-004
**Requirement:** REQ-CHK-006
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Order submission is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-007 — Invalid checkout email

**Scenario:** SCN-CHK-005
**Requirement:** REQ-CHK-006
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`invalid-email`

### Expected Result

Invalid email is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-008 — Empty address

**Scenario:** SCN-CHK-004
**Requirement:** REQ-CHK-007
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Checkout does not proceed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-009 — Address with 4 characters

**Scenario:** SCN-CHK-006
**Requirement:** REQ-CHK-008
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`1234`

### Expected Result

Address is rejected as too short.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-010 — Address with 5 characters

**Scenario:** SCN-CHK-006
**Requirement:** REQ-CHK-008
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`12345`

### Expected Result

Address satisfies minimum-length validation.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-011 — Empty city

**Scenario:** SCN-CHK-004
**Requirement:** REQ-CHK-009
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Checkout is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-012 — Empty postal code

**Scenario:** SCN-CHK-004
**Requirement:** REQ-CHK-010
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Checkout is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-013 — Empty cardholder name

**Scenario:** SCN-CHK-007
**Requirement:** REQ-CHK-011
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Expected Result

Checkout is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-014 — 12-digit card

**Scenario:** SCN-CHK-008
**Requirement:** REQ-CHK-012
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Card is rejected because it contains fewer than 13 digits.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-015 — 13-digit structurally valid card

**Scenario:** SCN-CHK-008
**Requirement:** REQ-CHK-012, REQ-CHK-013
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

A 13-digit value is allowed only when it also passes Luhn validation.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-016 — 19-digit structurally valid card

**Scenario:** SCN-CHK-008
**Requirement:** REQ-CHK-012, REQ-CHK-013
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

A valid 19-digit Luhn number passes length validation.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-017 — 20-digit card

**Scenario:** SCN-CHK-008
**Requirement:** REQ-CHK-012
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Card is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-018 — Valid Luhn card

**Scenario:** SCN-CHK-009
**Requirement:** REQ-CHK-013
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

`4242424242424242`

### Expected Result

Card passes card-number validation.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-019 — Invalid Luhn card

**Scenario:** SCN-CHK-010
**Requirement:** REQ-CHK-013
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`4242424242424241`

### Expected Result

Card is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-020 — Card number containing spaces

**Scenario:** SCN-CHK-011
**Requirement:** REQ-CHK-012
**Priority:** High
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

`4242 4242 4242 4242`

### Expected Result

Spaces are removed before validation and the valid card is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-021 — Card number containing letters

**Scenario:** SCN-CHK-012
**Requirement:** REQ-CHK-012
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`4242ABCD42424242`

### Expected Result

Card is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-022 — Card number containing special characters

**Scenario:** SCN-CHK-012
**Requirement:** REQ-CHK-012
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`4242-4242-4242-4242`

### Expected Result

Card is rejected because only spaces are normalized.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-023 — Valid expiry format

**Scenario:** SCN-CHK-013
**Requirement:** REQ-CHK-014
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

`12/30`

### Expected Result

Value matches expected MM/YY format.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-024 — Invalid expiry format without slash

**Scenario:** SCN-CHK-013
**Requirement:** REQ-CHK-014
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`1230`

### Expected Result

Expiry value is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-025 — Invalid expiry using four-digit year

**Scenario:** SCN-CHK-013
**Requirement:** REQ-CHK-014
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`12/2030`

### Expected Result

Value is rejected because expected format is MM/YY.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-026 — CVV with 2 digits

**Scenario:** SCN-CHK-014
**Requirement:** REQ-CHK-015
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`12`

### Expected Result

CVV is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-027 — CVV with 3 digits

**Scenario:** SCN-CHK-014
**Requirement:** REQ-CHK-015
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`123`

### Expected Result

CVV is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-028 — CVV with 4 digits

**Scenario:** SCN-CHK-014
**Requirement:** REQ-CHK-015
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`1234`

### Expected Result

CVV is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-029 — CVV with 5 digits

**Scenario:** SCN-CHK-014
**Requirement:** REQ-CHK-015
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

CVV is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-030 — Non-numeric CVV

**Scenario:** SCN-CHK-014
**Requirement:** REQ-CHK-015
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`12A`

### Expected Result

CVV is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-031 — Declined payment

**Scenario:** SCN-CHK-015
**Requirement:** REQ-CHK-016
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

Card: `4000000000000002`

### Expected Result

* Checkout is rejected.
* `Payment declined.` feedback is shown.
* Cart remains intact.
* No confirmation is generated.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-032 — Insufficient funds

**Scenario:** SCN-CHK-016
**Requirement:** REQ-CHK-017
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

Card: `4000000000009995`

### Expected Result

* Checkout is rejected.
* `Insufficient funds.` is displayed.
* Cart remains intact.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-033 — Successful checkout

**Scenario:** SCN-CHK-017, SCN-CHK-019
**Requirement:** REQ-CHK-018, REQ-CHK-020
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

* Logged in.
* Cart contains an available product.

### Test Data

Card: `4242424242424242`
Expiry: `12/30`
CVV: `123`

### Steps

1. Enter valid shipping information.
2. Enter valid payment information.
3. Select Place Order.
4. Wait for processing to complete.

### Expected Result

Order confirmation is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-034 — Duplicate order submission

**Scenario:** SCN-CHK-018
**Requirement:** REQ-CHK-019
**Priority:** Critical
**Type:** State / Concurrency
**Automation Candidate:** Yes

### Steps

1. Complete valid checkout information.
2. Select Place Order.
3. Immediately attempt to select it again.

### Expected Result

* Button becomes disabled while processing.
* Duplicate submission is prevented.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-035 — Order reference

**Scenario:** SCN-CHK-020
**Requirement:** REQ-CHK-021
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Expected Result

Successful order displays:

`ORD-QA-1001`

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-036 — Cart cleared after successful purchase

**Scenario:** SCN-CHK-021
**Requirement:** REQ-CHK-022
**Priority:** Critical
**Type:** Integration
**Automation Candidate:** Yes

### Steps

1. Complete a successful checkout.
2. Navigate to Cart.

### Expected Result

* Cart is empty.
* Navigation cart counter is `0`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-037 — Cart retained after failed payment

**Scenario:** SCN-CHK-015, SCN-CHK-016
**Requirement:** REQ-CHK-016, REQ-CHK-017
**Priority:** Critical
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Add product.
2. Attempt checkout using declined or insufficient-funds card.
3. Return to Cart.

### Expected Result

Original cart contents remain available.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-038 — Quantity changed immediately before checkout

**Scenario:** SCN-CHK-023
**Requirement:** REQ-CART-010, REQ-CHK-002
**Priority:** High
**Type:** Integration
**Automation Candidate:** Yes

### Steps

1. Add product.
2. Change quantity in Cart.
3. Immediately navigate to Checkout.
4. Complete order.

### Expected Result

Checkout operates using the latest valid cart state.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-039 — Complete product-to-order flow

**Scenario:** SCN-CHK-022
**Requirement:** REQ-CART-001, REQ-CHK-018, REQ-CHK-020
**Priority:** Critical
**Type:** End-to-End
**Automation Candidate:** Yes

### Steps

1. Log in.
2. Browse Products.
3. Add an available product.
4. Open Cart.
5. Proceed to Checkout.
6. Enter valid shipping information.
7. Enter successful payment information.
8. Place Order.

### Expected Result

Entire customer journey succeeds and an order confirmation is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CHK-040 — Browser Back during checkout

**Scenario:** SCN-CHK-024
**Requirement:** REQ-NAV-008
**Priority:** Medium
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Enter Checkout.
2. Use browser Back to return to Cart.
3. Use browser Forward.

### Expected Result

Application remains usable and cart state is not unexpectedly lost.

**Status:** Not Run
**Actual Result:** —
**Defect:** —