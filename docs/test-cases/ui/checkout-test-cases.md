# QA Commerce Lab — Coupon Test Cases

## Document Information

**Module:** Coupons
**Execution Status:** Not Run

---

## TC-CPN-001 — Apply SAVE10

**Scenario:** SCN-CPN-001, SCN-CPN-004
**Requirement:** REQ-CPN-001, REQ-CPN-004
**Priority:** High
**Type:** Positive / Calculation
**Automation Candidate:** Yes

### Preconditions

Cart contains products.

### Test Data

Coupon: `SAVE10`

### Steps

1. Open Cart.
2. Record subtotal.
3. Enter SAVE10.
4. Select Apply.

### Expected Result

* Coupon is accepted.
* Discount equals 10% of subtotal.
* Total is recalculated.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-002 — Lowercase coupon

**Scenario:** SCN-CPN-002
**Requirement:** REQ-CPN-002
**Priority:** Medium
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

`save10`

### Expected Result

Coupon is accepted as SAVE10.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-003 — Mixed-case coupon

**Scenario:** SCN-CPN-002
**Requirement:** REQ-CPN-002
**Priority:** Medium
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

`SaVe10`

### Expected Result

Coupon is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-004 — Coupon with surrounding whitespace

**Scenario:** SCN-CPN-003
**Requirement:** REQ-CPN-003
**Priority:** Medium
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

`   SAVE10   `

### Expected Result

Whitespace is ignored and coupon applies normally.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-005 — FREESHIP

**Scenario:** SCN-CPN-005
**Requirement:** REQ-CPN-005
**Priority:** High
**Type:** Calculation
**Automation Candidate:** Yes

### Preconditions

Subtotal is below `$150`.

### Expected Result

Shipping becomes `$0.00`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-006 — MIN100 with subtotal exactly $100

**Scenario:** SCN-CPN-006
**Requirement:** REQ-CPN-006
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Preconditions

Prepare subtotal equal to `$100.00`.

### Expected Result

MIN100 is accepted and `$15` discount is applied.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-007 — MIN100 above $100

**Scenario:** SCN-CPN-006
**Requirement:** REQ-CPN-006
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Preconditions

Subtotal is greater than `$100`.

### Expected Result

Coupon applies `$15` discount.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-008 — MIN100 below $100

**Scenario:** SCN-CPN-007
**Requirement:** REQ-CPN-007
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Preconditions

Subtotal is `$99.99` or otherwise below `$100`.

### Expected Result

Coupon is rejected with minimum-order feedback.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-009 — Expired coupon

**Scenario:** SCN-CPN-008
**Requirement:** REQ-CPN-008
**Priority:** Medium
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`EXPIRED`

### Expected Result

Coupon is rejected as expired.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-010 — Unknown coupon

**Scenario:** SCN-CPN-009
**Requirement:** REQ-CPN-009
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`UNKNOWN123`

### Expected Result

Coupon is rejected with `Coupon not found.` feedback.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-011 — Empty coupon

**Scenario:** SCN-CPN-009
**Requirement:** REQ-CPN-009
**Priority:** Medium
**Type:** Negative
**Automation Candidate:** Yes

### Steps

1. Leave Coupon empty.
2. Select Apply.

### Expected Result

Empty value is not treated as a valid coupon.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-012 — Automatic free shipping at $150

**Scenario:** SCN-CPN-010
**Requirement:** REQ-CPN-010
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Preconditions

Subtotal is exactly `$150`.

### Expected Result

Shipping charge is `$0.00`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-013 — Automatic free shipping above $150

**Scenario:** SCN-CPN-010
**Requirement:** REQ-CPN-010
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Orders above `$150` receive free shipping.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-014 — Shipping below $150

**Scenario:** SCN-CPN-011
**Requirement:** REQ-CPN-011
**Priority:** High
**Type:** Calculation
**Automation Candidate:** Yes

### Preconditions

Subtotal is below `$150`.
No FREESHIP coupon is applied.

### Expected Result

Shipping charge equals `$9.99`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CPN-015 — Total after SAVE10

**Scenario:** SCN-CPN-012
**Requirement:** REQ-CPN-004, REQ-CPN-011
**Priority:** Critical
**Type:** Calculation
**Automation Candidate:** Yes

### Test Data

Subtotal: `$100.00`
SAVE10 discount: `$10.00`
Shipping: `$9.99`

### Expected Result

Final total:

`$99.99`

**Status:** Not Run
**Actual Result:** —
**Defect:** —