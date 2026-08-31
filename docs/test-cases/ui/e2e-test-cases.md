# QA Commerce Lab — End-to-End Test Cases

## Document Information

**Module:** End-to-End Business Flows
**Execution Status:** Not Run

---

## TC-E2E-001 — Register to successful order

**Scenario:** SCN-E2E-001
**Requirement:** REQ-REG-001, REQ-CART-001, REQ-CHK-020
**Priority:** Critical
**Type:** End-to-End
**Automation Candidate:** Yes

### Preconditions

* Use a unique registration email.
* Cart is empty.

### Steps

1. Open Registration.
2. Create a valid new account.
3. Verify automatic authentication.
4. Open Products.
5. Add an available product.
6. Open Cart.
7. Proceed to Checkout.
8. Enter valid shipping details.
9. Enter successful payment details.
10. Place the order.

### Expected Result

* Registration succeeds.
* User remains authenticated.
* Product is added successfully.
* Checkout succeeds.
* Order confirmation is displayed.
* Cart is cleared.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-E2E-002 — Login, search, coupon, purchase

**Scenario:** SCN-E2E-002
**Requirement:** REQ-AUTH-001, REQ-PROD-008, REQ-CPN-004, REQ-CHK-020
**Priority:** Critical
**Type:** End-to-End
**Automation Candidate:** Yes

### Steps

1. Login using seeded account.
2. Search for an available product.
3. Add it to Cart.
4. Open Cart.
5. Apply SAVE10.
6. Verify recalculated total.
7. Proceed to Checkout.
8. Complete valid payment.

### Expected Result

Entire workflow completes successfully with the expected discount.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-E2E-003 — Multiple-product cart management to checkout

**Scenario:** SCN-E2E-003
**Requirement:** REQ-CART-001, REQ-CART-010, REQ-CART-012, REQ-CHK-020
**Priority:** Critical
**Type:** End-to-End
**Automation Candidate:** Yes

### Steps

1. Login.
2. Add three different available products.
3. Open Cart.
4. Increase one quantity.
5. Remove another product.
6. Verify subtotal.
7. Proceed to Checkout.
8. Complete purchase.

### Expected Result

Order uses the final cart state and completes successfully.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-E2E-004 — Full account lifecycle

**Scenario:** SCN-E2E-004
**Requirement:** REQ-REG-001, REQ-ACC-003, REQ-ACC-007
**Priority:** Critical
**Type:** End-to-End
**Automation Candidate:** Yes

### Steps

1. Register a unique account.
2. Logout.
3. Login using the same account.
4. Open Account.
5. Delete account using exact confirmation.
6. Attempt login again.

### Expected Result

* Registration succeeds.
* Logout succeeds.
* Login succeeds before deletion.
* Deletion succeeds.
* Login fails after deletion.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-E2E-005 — Guest cart to authenticated checkout

**Scenario:** SCN-E2E-005
**Requirement:** REQ-CART-016, REQ-CHK-001, REQ-AUTH-001
**Priority:** Critical
**Type:** End-to-End / State
**Automation Candidate:** Yes

### Steps

1. While logged out, add a product to Cart.
2. Attempt to access Checkout.
3. Verify authentication is required.
4. Navigate to Login.
5. Login successfully.
6. Return to Cart.
7. Proceed to Checkout.

### Expected Result

* Cart survives the authentication transition.
* User can continue checkout after login.

**Status:** Not Run
**Actual Result:** —
**Defect:** —

---

## TC-E2E-006 — API setup with UI execution and API cleanup

**Scenario:** SCN-E2E-006
**Requirement:** REQ-API-USR-001, REQ-AUTH-001, REQ-CHK-020, REQ-API-USR-012
**Priority:** High
**Type:** Integration
**Automation Candidate:** Yes

### Steps

1. Create a test user using the REST API.
2. Login to the web application using that user's credentials.
3. Add a product.
4. Complete a successful checkout.
5. Delete the test user using the REST API.

### Expected Result

* API setup succeeds.
* UI recognizes the created user where supported by the integrated environment.
* Customer workflow succeeds.
* Cleanup completes without leaving unnecessary test data.

**Status:** Not Run
**Actual Result:** —
**Defect:** —
