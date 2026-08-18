# QA Commerce Lab — Requirements Catalog

## Document Information

**Project:** QA Commerce Lab QA Portfolio  
**Application:** QA Commerce Lab  
**Application URL:** https://qa-commerce-lab.vercel.app  
**Document Type:** Requirements Baseline  
**Status:** Baseline v1.0  

## Purpose

This document defines the functional and test-support requirements of QA Commerce Lab.

All test scenarios and test cases should reference one or more requirement IDs from this catalog.

## Requirement ID Convention

REQ-[MODULE]-[NUMBER]

Examples:

- REQ-AUTH-001
- REQ-PROD-001
- REQ-CART-001
- REQ-CHK-001
- REQ-API-001

## Feature Inventory

| Module | Description |
|---|---|
| Navigation | Global navigation and route handling |
| Authentication | Login and logout |
| Registration | User account creation |
| Products | Product catalog, search, filtering and sorting |
| Cart | Shopping cart management |
| Coupons | Discounts and shipping promotions |
| Checkout | Shipping and payment processing |
| Account | Account/session management |
| Contact | Customer support form |
| QA Lab | Controlled UI/API edge cases |
| REST API | Backend endpoints for API testing |


## Navigation Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-NAV-001 | The application shall display global navigation on application pages. | High |
| REQ-NAV-002 | The navigation shall provide access to Products, QA Lab, API Docs and Contact. | High |
| REQ-NAV-003 | Selecting the QA Commerce logo shall navigate to the home page. | Medium |
| REQ-NAV-004 | Unauthenticated users shall see a Sign In navigation option. | High |
| REQ-NAV-005 | Authenticated users shall see their first name instead of Sign In. | Medium |
| REQ-NAV-006 | The cart indicator shall display the total quantity of items currently in the cart. | High |
| REQ-NAV-007 | Unknown application routes shall display a 404 state. | Medium |
| REQ-NAV-008 | Browser Back and Forward navigation shall preserve appropriate route navigation behavior. | Medium |

## Authentication Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-AUTH-001 | A registered user shall be able to log in using a valid email and password. | Critical |
| REQ-AUTH-002 | Email comparison during login shall be case-insensitive. | High |
| REQ-AUTH-003 | Leading and trailing whitespace shall be removed from the submitted login email. | Medium |
| REQ-AUTH-004 | Password comparison shall remain case-sensitive. | High |
| REQ-AUTH-005 | Login shall be rejected when the email field is empty. | High |
| REQ-AUTH-006 | Login shall be rejected when the password field is empty. | High |
| REQ-AUTH-007 | Login shall be rejected when both credentials are empty. | High |
| REQ-AUTH-008 | Login shall be rejected when the email format is invalid. | High |
| REQ-AUTH-009 | Login shall be rejected when credentials are incorrect. | Critical |
| REQ-AUTH-010 | Successful login shall establish an authenticated user session. | Critical |
| REQ-AUTH-011 | Successful login shall navigate the user to the Account page. | High |
| REQ-AUTH-012 | Authentication state shall persist during browser navigation within the same stored browser state. | High |

## Registration Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-REG-001 | A user shall be able to create an account using valid registration information. | Critical |
| REQ-REG-002 | First name shall be required. | High |
| REQ-REG-003 | Last name shall be required. | High |
| REQ-REG-004 | First and last names shall support a maximum of 40 characters each. | Medium |
| REQ-REG-005 | Email shall be required and must have a valid email format. | High |
| REQ-REG-006 | Email shall support a maximum of 120 characters. | Medium |
| REQ-REG-007 | Registration shall reject an email address that already exists. | Critical |
| REQ-REG-008 | Duplicate email comparison shall be case-insensitive. | High |
| REQ-REG-009 | Password length shall be between 8 and 64 characters. | High |
| REQ-REG-010 | Password shall contain at least one uppercase character. | High |
| REQ-REG-011 | Password shall contain at least one lowercase character. | High |
| REQ-REG-012 | Password shall contain at least one numeric character. | High |
| REQ-REG-013 | Password shall contain at least one special character. | High |
| REQ-REG-014 | Password confirmation shall exactly match the password. | High |
| REQ-REG-015 | Registration shall require acceptance of the Terms condition. | High |
| REQ-REG-016 | A successfully registered user shall automatically become authenticated. | High |
| REQ-REG-017 | A successfully registered user shall be redirected to their Account page. | High |

## Product Catalog Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-PROD-001 | The product catalog shall display all configured products when no filter is applied. | Critical |
| REQ-PROD-002 | Each product shall display its name. | High |
| REQ-PROD-003 | Each product shall display its category. | Medium |
| REQ-PROD-004 | Each product shall display its price. | Critical |
| REQ-PROD-005 | Product prices displayed to customers shall be formatted as USD currency. | High |
| REQ-PROD-006 | Product price display shall round monetary values to two decimal places. | High |
| REQ-PROD-007 | Each product shall display its stock availability. | Critical |
| REQ-PROD-008 | Users shall be able to search products by product name. | High |
| REQ-PROD-009 | Users shall be able to search products by category text. | Medium |
| REQ-PROD-010 | Product search shall be case-insensitive. | Medium |
| REQ-PROD-011 | Leading and trailing search whitespace shall be ignored. | Medium |
| REQ-PROD-012 | The catalog shall display an empty-result state when no products match. | High |
| REQ-PROD-013 | Users shall be able to filter products by category. | High |
| REQ-PROD-014 | Users shall be able to sort products by price ascending. | Medium |
| REQ-PROD-015 | Users shall be able to sort products by price descending. | Medium |
| REQ-PROD-016 | Products with zero stock shall be identified as out of stock. | Critical |
| REQ-PROD-017 | Products with zero stock shall not be addable to the cart. | Critical |
| REQ-PROD-018 | The catalog shall support Unicode product names. | Medium |
| REQ-PROD-019 | Long product names shall remain usable without breaking the product layout. | Medium |
| REQ-PROD-020 | The catalog shall support zero-price products. | Medium |
| REQ-PROD-021 | The catalog shall support high-value product prices. | Medium |

## Shopping Cart Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-CART-001 | A user shall be able to add an available product to the cart. | Critical |
| REQ-CART-002 | Adding an existing cart product again shall increase its quantity. | High |
| REQ-CART-003 | An out-of-stock product shall not be added to the cart. | Critical |
| REQ-CART-004 | Product quantity shall be a positive whole number. | Critical |
| REQ-CART-005 | Product quantity shall not exceed available inventory. | Critical |
| REQ-CART-006 | The QA Sticker Pack shall have a maximum cart-add quantity of 25 despite its larger stock quantity. | Medium |
| REQ-CART-007 | The cart shall display each added product. | Critical |
| REQ-CART-008 | The cart shall display product quantity. | High |
| REQ-CART-009 | The cart shall display the calculated line total for each product. | High |
| REQ-CART-010 | Users shall be able to change product quantities in the cart. | Critical |
| REQ-CART-011 | Invalid quantities shall be rejected. | Critical |
| REQ-CART-012 | Users shall be able to remove products from the cart. | Critical |
| REQ-CART-013 | Removing a product shall update the cart indicator. | High |
| REQ-CART-014 | The cart subtotal shall equal the sum of price multiplied by quantity for all cart products. | Critical |
| REQ-CART-015 | An empty cart shall display an empty-cart state. | High |
| REQ-CART-016 | Cart state shall persist using the browser's stored application state. | High |

## Coupon Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-CPN-001 | Users shall be able to submit a coupon code from the cart. | High |
| REQ-CPN-002 | Coupon codes shall be processed case-insensitively. | Medium |
| REQ-CPN-003 | Leading and trailing whitespace around coupon codes shall be ignored. | Medium |
| REQ-CPN-004 | SAVE10 shall apply a 10% subtotal discount. | High |
| REQ-CPN-005 | FREESHIP shall reduce shipping cost to zero. | High |
| REQ-CPN-006 | MIN100 shall apply a $15 discount when subtotal is at least $100. | High |
| REQ-CPN-007 | MIN100 shall be rejected when subtotal is below $100. | High |
| REQ-CPN-008 | EXPIRED shall be rejected as an expired coupon. | Medium |
| REQ-CPN-009 | Unknown coupon codes shall be rejected. | High |
| REQ-CPN-010 | Orders with subtotal of at least $150 shall receive free shipping without requiring FREESHIP. | High |
| REQ-CPN-011 | Orders below the free-shipping threshold shall incur a $9.99 shipping charge unless FREESHIP applies. | High |

## Checkout Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-CHK-001 | Checkout shall require an authenticated user. | Critical |
| REQ-CHK-002 | Checkout shall require at least one cart item. | Critical |
| REQ-CHK-003 | First name, last name and email shall be pre-populated using account information. | Medium |
| REQ-CHK-004 | First name shall be required. | High |
| REQ-CHK-005 | Last name shall be required. | High |
| REQ-CHK-006 | Email shall be required and valid. | High |
| REQ-CHK-007 | Shipping address shall be required. | Critical |
| REQ-CHK-008 | Shipping address shall contain at least five characters. | Medium |
| REQ-CHK-009 | City shall be required. | High |
| REQ-CHK-010 | Postal code shall be required. | High |
| REQ-CHK-011 | Cardholder name shall be required. | High |
| REQ-CHK-012 | Card number shall contain between 13 and 19 digits after spaces are removed. | Critical |
| REQ-CHK-013 | Card number shall pass Luhn validation. | Critical |
| REQ-CHK-014 | Expiry value shall follow MM/YY format. | High |
| REQ-CHK-015 | CVV shall contain three or four digits. | Critical |
| REQ-CHK-016 | Card 4000000000000002 shall simulate a declined payment. | High |
| REQ-CHK-017 | Card 4000000000009995 shall simulate insufficient funds. | High |
| REQ-CHK-018 | Card 4242424242424242 shall support the successful payment flow when all other information is valid. | Critical |
| REQ-CHK-019 | The Place Order button shall prevent duplicate submission while an order is processing. | Critical |
| REQ-CHK-020 | Successful checkout shall display an order confirmation. | Critical |
| REQ-CHK-021 | Successful checkout shall generate order reference ORD-QA-1001. | High |
| REQ-CHK-022 | Successful checkout shall clear the shopping cart. | Critical |

## Account Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-ACC-001 | Authenticated users shall be able to access the Account page. | High |
| REQ-ACC-002 | The Account page shall display the authenticated user's name and email. | Medium |
| REQ-ACC-003 | Authenticated users shall be able to log out. | Critical |
| REQ-ACC-004 | Logging out shall remove the authenticated session. | Critical |
| REQ-ACC-005 | Account deletion shall require the exact confirmation value DELETE. | Critical |
| REQ-ACC-006 | Incorrect deletion confirmation shall not delete the account. | Critical |
| REQ-ACC-007 | Successful deletion of a created account shall remove the account from stored users. | Critical |
| REQ-ACC-008 | Successful account deletion shall log the user out. | High |
| REQ-ACC-009 | The seeded QA account shall remain reusable for future test execution. | High |

## Contact Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-CON-001 | Users shall be able to submit a support request. | High |
| REQ-CON-002 | Name shall contain at least two characters. | Medium |
| REQ-CON-003 | A valid email address shall be required. | High |
| REQ-CON-004 | A support subject shall be required. | High |
| REQ-CON-005 | Supported subjects shall include Order issue, Product question, Technical problem and Other. | Medium |
| REQ-CON-006 | Message length shall be between 20 and 1000 characters. | High |
| REQ-CON-007 | Attachments shall be optional. | Medium |
| REQ-CON-008 | Supported attachment extensions shall be PNG, JPG, JPEG and PDF. | High |
| REQ-CON-009 | Attachment size shall not exceed 2 MB. | High |
| REQ-CON-010 | Successful submission shall display an acceptance message. | High |

## QA Lab Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-LAB-001 | QA Lab shall provide deterministic test conditions for automation practice. | High |
| REQ-LAB-002 | The slow scenario shall return successfully after approximately 1500 ms. | High |
| REQ-LAB-003 | The empty scenario shall produce HTTP 204. | High |
| REQ-LAB-004 | The bad-request scenario shall produce HTTP 400. | High |
| REQ-LAB-005 | The unauthorized scenario shall produce HTTP 401. | High |
| REQ-LAB-006 | The not-found scenario shall produce HTTP 404. | High |
| REQ-LAB-007 | The conflict scenario shall produce HTTP 409. | High |
| REQ-LAB-008 | The validation scenario shall produce HTTP 422 with validation details. | High |
| REQ-LAB-009 | The rate-limit scenario shall produce HTTP 429. | High |
| REQ-LAB-010 | The rate-limit response shall include Retry-After: 5. | Medium |
| REQ-LAB-011 | The server-error scenario shall produce HTTP 500. | High |
| REQ-LAB-012 | The large-response scenario shall return 250 records. | Medium |
| REQ-LAB-013 | QA Lab shall support a delayed DOM element. | High |
| REQ-LAB-014 | The delayed DOM element shall appear after approximately 900 ms. | Medium |
| REQ-LAB-015 | QA Lab shall provide a modal dialog with Cancel and Confirm actions. | Medium |


## QA Lab Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-LAB-001 | QA Lab shall provide deterministic test conditions for automation practice. | High |
| REQ-LAB-002 | The slow scenario shall return successfully after approximately 1500 ms. | High |
| REQ-LAB-003 | The empty scenario shall produce HTTP 204. | High |
| REQ-LAB-004 | The bad-request scenario shall produce HTTP 400. | High |
| REQ-LAB-005 | The unauthorized scenario shall produce HTTP 401. | High |
| REQ-LAB-006 | The not-found scenario shall produce HTTP 404. | High |
| REQ-LAB-007 | The conflict scenario shall produce HTTP 409. | High |
| REQ-LAB-008 | The validation scenario shall produce HTTP 422 with validation details. | High |
| REQ-LAB-009 | The rate-limit scenario shall produce HTTP 429. | High |
| REQ-LAB-010 | The rate-limit response shall include Retry-After: 5. | Medium |
| REQ-LAB-011 | The server-error scenario shall produce HTTP 500. | High |
| REQ-LAB-012 | The large-response scenario shall return 250 records. | Medium |
| REQ-LAB-013 | QA Lab shall support a delayed DOM element. | High |
| REQ-LAB-014 | The delayed DOM element shall appear after approximately 900 ms. | Medium |
| REQ-LAB-015 | QA Lab shall provide a modal dialog with Cancel and Confirm actions. | Medium |


# REST API Requirements

| ID | Requirement | Priority |
|---|---|---|
| REQ-API-001 | API responses shall use JSON where a response body is returned. | High |
| REQ-API-002 | The API shall support CORS requests. | Medium |
| REQ-API-003 | Unsupported HTTP methods shall return HTTP 405. | High |
| REQ-API-004 | HTTP 405 responses shall identify allowed methods. | Medium |
| REQ-API-005 | OPTIONS requests shall support CORS preflight handling. | Medium |
| REQ-API-006 | Unknown API endpoints shall return HTTP 404. | High |

## Products API

| ID | Requirement | Priority |
|---|---|---|
| REQ-API-PROD-001 | GET /api/products shall return the product collection. | Critical |
| REQ-API-PROD-002 | Products shall support category filtering. | High |
| REQ-API-PROD-003 | Products shall support in-stock filtering. | High |
| REQ-API-PROD-004 | Products shall support price ascending sorting. | Medium |
| REQ-API-PROD-005 | Products shall support price descending sorting. | Medium |
| REQ-API-PROD-006 | Products shall support rating sorting. | Medium |
| REQ-API-PROD-007 | Invalid sort values shall return HTTP 400. | High |
| REQ-API-PROD-008 | Product results shall support pagination. | High |
| REQ-API-PROD-009 | Page shall be an integer of at least 1. | High |
| REQ-API-PROD-010 | Limit shall be between 1 and 100. | High |
| REQ-API-PROD-011 | GET /api/product shall return a product for a valid existing ID. | Critical |
| REQ-API-PROD-012 | Invalid product IDs shall return HTTP 400. | High |
| REQ-API-PROD-013 | Nonexistent product IDs shall return HTTP 404. | High |


## Search API

| ID | Requirement | Priority |
|---|---|---|
| REQ-API-SRCH-001 | GET /api/search shall search products using query parameter q. | High |
| REQ-API-SRCH-002 | Search shall support product names, brands and categories. | High |
| REQ-API-SRCH-003 | Missing q shall return HTTP 400. | High |
| REQ-API-SRCH-004 | Empty q shall return HTTP 400. | High |
| REQ-API-SRCH-005 | Search input longer than 100 characters shall return HTTP 400. | Medium |
| REQ-API-SRCH-006 | API search shall be case-insensitive. | Medium |


## Authentication API

| ID | Requirement | Priority |
|---|---|---|
| REQ-API-AUTH-001 | POST /api/auth shall authenticate valid seeded credentials. | Critical |
| REQ-API-AUTH-002 | Missing credentials shall return HTTP 400. | High |
| REQ-API-AUTH-003 | Invalid email format shall return HTTP 422. | High |
| REQ-API-AUTH-004 | Incorrect credentials shall return HTTP 401. | Critical |
| REQ-API-AUTH-005 | Successful authentication shall return HTTP 200. | Critical |
| REQ-API-AUTH-006 | Successful authentication shall return a token. | Critical |
| REQ-API-AUTH-007 | Successful authentication shall return user information without returning the user's password. | Critical |


## Users API

| ID | Requirement | Priority |
|---|---|---|
| REQ-API-USR-001 | POST /api/users shall create a user when valid data is supplied. | Critical |
| REQ-API-USR-002 | User first name and last name shall be required and limited to 40 characters. | High |
| REQ-API-USR-003 | User email shall have a valid email format. | High |
| REQ-API-USR-004 | User password shall meet the configured password complexity requirements. | High |
| REQ-API-USR-005 | Validation failures shall return HTTP 422. | High |
| REQ-API-USR-006 | Attempting to create the seeded email shall return HTTP 409. | High |
| REQ-API-USR-007 | Successful creation shall return HTTP 201. | Critical |
| REQ-API-USR-008 | PUT /api/users shall require a user ID. | High |
| REQ-API-USR-009 | DELETE /api/users shall require a user ID. | High |
| REQ-API-USR-010 | User deletion shall require confirmation value DELETE. | Critical |
| REQ-API-USR-011 | Attempting to delete the seeded user shall return HTTP 409. | High |
| REQ-API-USR-012 | Successful deletion shall return confirmation of the deleted user ID. | High |


## Orders API

| ID | Requirement | Priority |
|---|---|---|
| REQ-API-ORD-001 | POST /api/orders shall create an order when valid order data is supplied. | Critical |
| REQ-API-ORD-002 | An order shall contain at least one item. | Critical |
| REQ-API-ORD-003 | Valid shipping information shall be required. | Critical |
| REQ-API-ORD-004 | Valid payment information shall be required. | Critical |
| REQ-API-ORD-005 | Unknown product IDs shall return HTTP 404. | High |
| REQ-API-ORD-006 | Item quantity shall be a positive integer. | Critical |
| REQ-API-ORD-007 | Quantity exceeding available inventory shall return HTTP 409. | Critical |
| REQ-API-ORD-008 | Declined card simulation shall return HTTP 402. | High |
| REQ-API-ORD-009 | Insufficient-funds simulation shall return HTTP 402. | High |
| REQ-API-ORD-010 | Successful order creation shall return HTTP 201. | Critical |
| REQ-API-ORD-011 | Successful orders shall return an order ID. | Critical |
| REQ-API-ORD-012 | Successful orders shall return the calculated subtotal. | High |
| REQ-API-ORD-013 | Order currency shall be USD. | Medium |


## Reviews API

| ID | Requirement | Priority |
|---|---|---|
| REQ-API-REV-001 | POST /api/reviews shall allow creation of a valid product review. | Medium |
| REQ-API-REV-002 | Product ID shall reference an existing product. | High |
| REQ-API-REV-003 | Rating shall be an integer between 1 and 5. | High |
| REQ-API-REV-004 | Review title shall contain at least 3 characters. | Medium |
| REQ-API-REV-005 | Review body shall contain between 10 and 500 characters. | Medium |
| REQ-API-REV-006 | Review email shall have a valid email format. | High |
| REQ-API-REV-007 | Successful review creation shall return HTTP 201. | Medium |


## Priority Definitions

### Critical

Failure prevents a major user journey or creates incorrect core business behavior.

Examples:

- Login
- Cart
- Checkout
- Payment
- Order creation

### High

Important functionality where failure significantly affects users or test coverage.

Examples:

- Validation
- Search
- Coupons
- Authentication errors

### Medium

Functionality that improves usability, compatibility, testability or secondary workflows.