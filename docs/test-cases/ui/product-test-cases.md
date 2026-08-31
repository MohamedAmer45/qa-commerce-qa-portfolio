# QA Commerce Lab — Product Test Cases

## Document Information

**Module:** Products
**Application:** QA Commerce Lab
**Execution Status:** Not Run

---

## TC-PROD-001 — Display complete product catalog

**Scenario:** SCN-PROD-001
**Requirement:** REQ-PROD-001
**Priority:** Critical
**Type:** Positive
**Automation Candidate:** Yes

### Preconditions

* Application is available.

### Steps

1. Open `/products`.
2. Do not enter a search value.
3. Leave Category as `All`.
4. Leave sorting as `Featured`.
5. Count the displayed product cards.

### Expected Result

* Product catalog loads successfully.
* 12 configured products are displayed.
* Result counter displays `12 results`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-002 — Display product information

**Scenario:** SCN-PROD-002
**Requirement:** REQ-PROD-002, REQ-PROD-003, REQ-PROD-004, REQ-PROD-007
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Steps

1. Open Products.
2. Inspect several product cards.

### Expected Result

Each product card displays:

* Product name
* Category
* Price
* Stock status
* Add-to-cart or unavailable state

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-003 — USD price formatting

**Scenario:** SCN-PROD-003
**Requirement:** REQ-PROD-005
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Steps

1. Open Products.
2. Inspect prices for multiple products.

### Expected Result

Prices are displayed in USD currency format such as:

* `$129.99`
* `$1,299.00`
* `$0.99`

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-004 — Three-decimal price rounding

**Scenario:** SCN-PROD-004
**Requirement:** REQ-PROD-006
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

Product: `Orbit Smart Lamp`
Stored price: `19.999`

### Steps

1. Open Products.
2. Locate Orbit Smart Lamp.
3. Observe displayed price.

### Expected Result

Displayed monetary value is rounded to:

`$20.00`

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-005 — Search using complete product name

**Scenario:** SCN-PROD-005
**Requirement:** REQ-PROD-008
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

`Pulse 75 Mechanical Keyboard`

### Steps

1. Open Products.
2. Enter the complete product name in Search.

### Expected Result

* Matching product is displayed.
* Unrelated products are removed.
* Result counter displays the correct count.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-006 — Search using partial product name

**Scenario:** SCN-PROD-006
**Requirement:** REQ-PROD-008
**Priority:** High
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

`keyboard`

### Steps

1. Enter `keyboard` in Search.

### Expected Result

Pulse 75 Mechanical Keyboard is returned.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-007 — Search using category text

**Scenario:** SCN-PROD-007
**Requirement:** REQ-PROD-009
**Priority:** Medium
**Type:** Positive
**Automation Candidate:** Yes

### Test Data

`Audio`

### Steps

1. Enter `Audio` into Search.

### Expected Result

Products belonging to the Audio category are displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-008 — Case-insensitive search

**Scenario:** SCN-PROD-008
**Requirement:** REQ-PROD-010
**Priority:** Medium
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

`KEYBOARD`

### Expected Result

Search returns the same relevant product as lowercase `keyboard`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-009 — Search with surrounding whitespace

**Scenario:** SCN-PROD-009
**Requirement:** REQ-PROD-011
**Priority:** Medium
**Type:** Validation
**Automation Candidate:** Yes

### Test Data

`   keyboard   `

### Expected Result

Surrounding spaces are ignored and the expected product is returned.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-010 — Search with no matches

**Scenario:** SCN-PROD-010
**Requirement:** REQ-PROD-012
**Priority:** High
**Type:** Negative
**Automation Candidate:** Yes

### Test Data

`product-that-does-not-exist`

### Expected Result

* No product cards are shown.
* Empty state appears.
* Result count is `0 results`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-011 — Filter by Accessories

**Scenario:** SCN-PROD-011
**Requirement:** REQ-PROD-013
**Priority:** High
**Type:** Functional
**Automation Candidate:** Yes

### Steps

1. Set Category to `Accessories`.

### Expected Result

Only Accessories products are displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-012 — Filter all categories

**Scenario:** SCN-PROD-011
**Requirement:** REQ-PROD-013
**Priority:** High
**Type:** Data Driven
**Automation Candidate:** Yes

### Test Data

Run using:

* Accessories
* Audio
* Computers
* Displays
* Furniture
* Home
* Laptops
* Merch

### Expected Result

Every selected category displays only products belonging to that category.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-013 — Search and category filter together

**Scenario:** SCN-PROD-011
**Requirement:** REQ-PROD-008, REQ-PROD-013
**Priority:** High
**Type:** Integration
**Automation Candidate:** Yes

### Test Data

Search: `USB`
Category: `Accessories`

### Expected Result

Only products satisfying both criteria are displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-014 — Sort price ascending

**Scenario:** SCN-PROD-012
**Requirement:** REQ-PROD-014
**Priority:** Medium
**Type:** Functional
**Automation Candidate:** Yes

### Steps

1. Open Products.
2. Select `Price ↑`.

### Expected Result

Products are ordered from lowest to highest price.

The first product should include the `$0.00` product.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-015 — Sort price descending

**Scenario:** SCN-PROD-013
**Requirement:** REQ-PROD-015
**Priority:** Medium
**Type:** Functional
**Automation Candidate:** Yes

### Steps

1. Select `Price ↓`.

### Expected Result

Products are ordered from highest to lowest price.

Titan Studio Workstation should appear at or near the beginning with `$99,999.99`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-016 — Out-of-stock product

**Scenario:** SCN-PROD-014
**Requirement:** REQ-PROD-016, REQ-PROD-017
**Priority:** Critical
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

Nimbus ANC Headphones
Stock: `0`

### Expected Result

* Product displays `Out of stock`.
* Add button is unavailable/disabled.
* Product cannot be added to cart.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-017 — Single-stock product

**Scenario:** SCN-PROD-014
**Requirement:** REQ-PROD-007
**Priority:** High
**Type:** Boundary
**Automation Candidate:** Yes

### Test Data

Pulse 75 Mechanical Keyboard
Stock: `1`

### Expected Result

Product correctly displays `1 in stock`.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-018 — Zero-price product

**Scenario:** SCN-PROD-015
**Requirement:** REQ-PROD-020
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

QA Sticker Pack
Price: `0`

### Expected Result

* Product displays `$0.00`.
* Product remains addable to cart.
* No negative or malformed price is displayed.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-019 — High-value product

**Scenario:** SCN-PROD-016
**Requirement:** REQ-PROD-021
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

Titan Studio Workstation
Price: `99999.99`

### Expected Result

Displayed price is:

`$99,999.99`

and the layout remains usable.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-020 — Unicode product name

**Scenario:** SCN-PROD-017
**Requirement:** REQ-PROD-018, REQ-NFR-005
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

`Café Élan Travel Mug — إصدار محدود`

### Expected Result

* Accented characters render correctly.
* Arabic text renders correctly.
* No corrupted replacement characters appear.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-021 — Search Unicode product

**Scenario:** SCN-PROD-017
**Requirement:** REQ-PROD-018
**Priority:** Medium
**Type:** Edge
**Automation Candidate:** Yes

### Test Data

`إصدار`

### Expected Result

The Unicode product is returned successfully.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-PROD-022 — Long product name

**Scenario:** SCN-PROD-018
**Requirement:** REQ-PROD-019, REQ-NFR-004
**Priority:** Medium
**Type:** UI
**Automation Candidate:** Partially

### Test Data

Zenith Ergonomic Chair — Extra Long Product Name for Layout and Truncation Validation

### Expected Result

* Product name remains readable.
* Price remains visible.
* Stock remains visible.
* Add-to-cart button remains accessible.
* Neighboring product cards remain usable.

**Status:** Not Run
**Actual Result:** —
**Defect:** —