# QA Commerce Compatibility/Responsive Test Cases

## TC-NFR-001 — Chrome critical workflow

**Scenario:** SCN-NFR-001
**Requirement:** REQ-NFR-001
**Priority:** High
**Type:** Compatibility
**Automation Candidate:** Yes

### Environment

Google Chrome

### Steps

1. Login.
2. Browse Products.
3. Add a product.
4. Open Cart.
5. Complete checkout.

### Expected Result

Critical workflow functions correctly in Chrome.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-002 — Firefox critical workflow

**Scenario:** SCN-NFR-002
**Requirement:** REQ-NFR-001
**Priority:** High
**Type:** Compatibility
**Automation Candidate:** Yes

### Environment

Mozilla Firefox

### Expected Result

Critical workflow functions correctly in Firefox.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-003 — Edge critical workflow

**Scenario:** SCN-NFR-003
**Requirement:** REQ-NFR-001
**Priority:** High
**Type:** Compatibility
**Automation Candidate:** Yes

### Environment

Microsoft Edge

### Expected Result

Critical workflow functions correctly in Edge.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-004 — Desktop viewport

**Scenario:** SCN-NFR-004
**Requirement:** REQ-NFR-002
**Priority:** High
**Type:** Responsive
**Automation Candidate:** Partially

### Example Viewport

1920 × 1080

### Expected Result

* Navigation is accessible.
* Product grid renders correctly.
* Forms remain usable.
* No important content overlaps.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-005 — Tablet viewport

**Scenario:** SCN-NFR-005
**Requirement:** REQ-NFR-002
**Priority:** High
**Type:** Responsive
**Automation Candidate:** Partially

### Example Viewport

768 × 1024

### Expected Result

Application adapts without making critical controls inaccessible.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-006 — Mobile viewport

**Scenario:** SCN-NFR-006
**Requirement:** REQ-NFR-002, REQ-NFR-003
**Priority:** High
**Type:** Responsive
**Automation Candidate:** Partially

### Example Viewport

390 × 844

### Expected Result

* Main functionality remains usable.
* Product cards stack correctly.
* Cart remains usable.
* Forms remain usable.
* Critical controls are accessible.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-007 — Small mobile width

**Scenario:** SCN-NFR-006
**Requirement:** REQ-NFR-003
**Priority:** High
**Type:** Boundary / Responsive
**Automation Candidate:** Partially

### Example Viewport

320 × 568

### Expected Result

No critical workflow is blocked due to viewport width.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-008 — Long product content responsiveness

**Scenario:** SCN-NFR-007
**Requirement:** REQ-NFR-004
**Priority:** Medium
**Type:** UI
**Automation Candidate:** Partially

### Test Data

Zenith Ergonomic Chair long product title.

### Expected Result

At desktop, tablet, and mobile widths:

* Price remains reachable.
* Stock remains reachable.
* Add button remains reachable.
* Product card does not overlap neighboring content.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-009 — Unicode content in Chrome

**Scenario:** SCN-NFR-008
**Requirement:** REQ-NFR-005
**Priority:** Medium
**Type:** Compatibility
**Automation Candidate:** Yes

### Test Data

Café Élan Travel Mug — إصدار محدود

### Expected Result

Text renders without character corruption.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-010 — Unicode content in Firefox

**Scenario:** SCN-NFR-008
**Requirement:** REQ-NFR-005
**Priority:** Medium
**Type:** Compatibility
**Automation Candidate:** Yes

### Expected Result

Unicode content renders consistently.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-NFR-011 — Unicode content in Edge

**Scenario:** SCN-NFR-008
**Requirement:** REQ-NFR-005
**Priority:** Medium
**Type:** Compatibility
**Automation Candidate:** Yes

### Expected Result

Unicode content renders consistently.

**Status:** Not Run
**Actual Result:** —
**Defect:** —
