# QA Commerce Lab — Shopping Cart Test Cases

## Document Information

**Module:** Shopping Cart
**Execution Status:** Not Run

---

## TC-CART-001 — Add available product to empty cart

**Scenario:** SCN-CART-001
**Requirement:** REQ-CART-001
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

* Cart is empty.

### Steps

1. Open Products.
2. Add ApexBook Pro 14.
3. Open Cart.

### Expected Result

* Product is added.
* Quantity is `1`.
* Product appears in Cart.
* Cart indicator becomes `1`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-002 — Add multiple different products

**Scenario:** SCN-CART-002
**Requirement:** REQ-CART-001
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Steps

1. Add ApexBook Pro 14.
2. Add Echo Mini Speaker.
3. Open Cart.

### Expected Result

Both products appear with quantity `1`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-003 — Add same product twice

**Scenario:** SCN-CART-003
**Requirement:** REQ-CART-002
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Steps

1. Add Echo Mini Speaker.
2. Add Echo Mini Speaker again.
3. Open Cart.

### Expected Result

* Only one line exists for the product.
* Quantity is `2`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-004 — Cannot add zero-stock product

**Scenario:** SCN-CART-004
**Requirement:** REQ-CART-003
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

Nimbus ANC Headphones

### Expected Result

Product cannot be added to cart.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-005 — Quantity of 1

**Scenario:** SCN-CART-005
**Requirement:** REQ-CART-004
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Steps

1. Add an available product.
2. Set quantity to `1`.

### Expected Result

Quantity `1` is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-006 — Quantity of 0

**Scenario:** SCN-CART-005
**Requirement:** REQ-CART-004
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Steps

1. Add a product.
2. Change quantity to `0`.

### Expected Result

* Quantity is rejected.
* Cart does not retain `0`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-007 — Negative quantity

**Scenario:** SCN-CART-005
**Requirement:** REQ-CART-004
**Priority:** Critical
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`-1`

### Expected Result

Negative quantity is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-008 — Decimal quantity

**Scenario:** SCN-CART-005
**Requirement:** REQ-CART-004
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

`1.5`

### Expected Result

Decimal quantity is rejected because quantity must be a whole number.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-009 — Quantity equal to stock

**Scenario:** SCN-CART-006
**Requirement:** REQ-CART-005
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

Nomad USB-C Hub
Stock: `6`

### Steps

1. Add the product.
2. Set quantity to `6`.

### Expected Result

Quantity `6` is accepted.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-010 — Quantity stock + 1

**Scenario:** SCN-CART-006
**Requirement:** REQ-CART-005
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

Nomad USB-C Hub
Stock: `6`
Attempt: `7`

### Expected Result

Quantity `7` is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-011 — Single-stock product cannot exceed 1

**Scenario:** SCN-CART-006
**Requirement:** REQ-CART-005
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

Pulse 75 Mechanical Keyboard
Stock: `1`

### Steps

1. Add the keyboard.
2. Attempt to add it again.

### Expected Result

Cart quantity does not exceed `1`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-012 — Sticker Pack quantity 25

**Scenario:** SCN-CART-007
**Requirement:** REQ-CART-006
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

QA Sticker Pack
Business maximum: `25`

### Expected Result

Quantity up to 25 is allowed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-013 — Sticker Pack quantity 26

**Scenario:** SCN-CART-007
**Requirement:** REQ-CART-006
**Priority:** Medium
**Type:** Boundary
**Automation Candidate:** Yes

### Expected Result

Quantity greater than 25 is rejected.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-014 — Display cart product information

**Scenario:** SCN-CART-008
**Requirement:** REQ-CART-007, REQ-CART-008
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Expected Result

Cart row displays:

* Product name
* Quantity
* Line total
* Remove control

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-015 — Single-product line total

**Scenario:** SCN-CART-009
**Requirement:** REQ-CART-009
**Priority:** High
**Type:** Calculation
**Automation Candidate:** Yes

### Test Data

Echo Mini Speaker
Price: `$39.90`
Quantity: `2`

### Expected Result

Line total equals:

`$79.80`

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-016 — Update quantity

**Scenario:** SCN-CART-010
**Requirement:** REQ-CART-010
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Steps

1. Add Echo Mini Speaker.
2. Open Cart.
3. Change quantity from `1` to `3`.

### Expected Result

* Quantity updates to `3`.
* Line total recalculates.
* Cart count updates.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-017 — Remove one of multiple products

**Scenario:** SCN-CART-012
**Requirement:** REQ-CART-012
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

Two different products are in the cart.

### Steps

1. Remove one product.

### Expected Result

* Selected product disappears.
* Other product remains.
* Totals are recalculated.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-018 — Remove final product

**Scenario:** SCN-CART-012, SCN-CART-015
**Requirement:** REQ-CART-012, REQ-CART-015
**Priority:** Critical
**Type:** State
**Automation Candidate:** Yes

### Expected Result

After the final item is removed:

* Cart becomes empty.
* Empty-cart state is displayed.
* Navigation count becomes `0`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-019 — Cart subtotal with one product

**Scenario:** SCN-CART-014
**Requirement:** REQ-CART-014
**Priority:** Critical
**Type:** Calculation
**Automation Candidate:** Yes

### Test Data

Echo Mini Speaker
Price: `$39.90`
Quantity: `2`

### Expected Result

Subtotal equals `$79.80`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-020 — Cart subtotal with multiple products

**Scenario:** SCN-CART-014
**Requirement:** REQ-CART-014
**Priority:** Critical
**Type:** Calculation
**Automation Candidate:** Yes

### Test Data

Echo Mini Speaker × 2 = `$79.80`
PixelGrip Cable Clip × 3 = `$2.97`

### Expected Result

Subtotal equals:

`$82.77`

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-021 — Zero-price product in subtotal

**Scenario:** SCN-CART-014
**Requirement:** REQ-CART-014
**Priority:** High
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

QA Sticker Pack × 5 = `$0.00`

### Expected Result

Subtotal calculation remains correct and does not produce errors or negative values.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-022 — Empty cart presentation

**Scenario:** SCN-CART-015
**Requirement:** REQ-CART-015
**Priority:** High
**Type:** State
**Automation Candidate:** Yes

### Preconditions

Cart is empty.

### Expected Result

* `Cart is empty` state is displayed.
* Browse Products navigation is available.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-023 — Cart persists after page refresh

**Scenario:** SCN-CART-016
**Requirement:** REQ-CART-016
**Priority:** High
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Add product.
2. Open Cart.
3. Refresh browser.

### Expected Result

Cart contents remain present after refresh.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-CART-024 — Cart persists during navigation

**Scenario:** SCN-CART-016
**Requirement:** REQ-CART-016
**Priority:** High
**Type:** State
**Automation Candidate:** Yes

### Steps

1. Add product.
2. Navigate Home.
3. Navigate Contact.
4. Return to Cart.

### Expected Result

Previously added items remain in the cart.

**Status:** Not Run
**Actual Result:** —
**Defect:** —
