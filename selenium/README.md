# QA Commerce Lab — Selenium UI Automation

Comprehensive Selenium UI automation framework and execution documentation for the **QA Commerce Lab** QA portfolio project.

This document combines:

- Framework setup and architecture
- Test execution instructions
- Automated coverage
- Cross-browser configuration
- Test isolation strategy
- Failure screenshot handling
- Allure reporting
- Known defect tracking
- Execution summary
- Final validation and repository workflow

---

# 1. Application Under Test

## Production Application

`https://qa-commerce-lab.vercel.app`

## API Base URL

`https://qa-commerce-lab.vercel.app/api`

QA Commerce Lab is a purpose-built e-commerce application used to demonstrate manual testing, UI automation, API automation, defect reporting, CI/CD, and QA engineering practices.

---

# 2. Technology Stack

The Selenium automation framework uses:

- Java 21
- Selenium WebDriver 4.48.0
- TestNG 7.12.0
- Maven 3.9+
- Allure TestNG 2.35.4
- Page Object Model
- Selenium Manager
- Chrome
- Firefox
- Microsoft Edge

---

# 3. Framework Goals

The framework demonstrates:

- End-to-end UI automation
- Page Object Model architecture
- Reusable browser components
- Positive testing
- Negative testing
- Boundary-value testing
- Authentication testing
- Registration testing
- Product catalog testing
- Cart testing
- Coupon testing
- Checkout testing
- Account lifecycle testing
- Contact form testing
- File-upload testing
- Unicode testing
- Dynamic DOM testing
- Modal testing
- Cross-browser execution
- Headless execution
- Test isolation
- Explicit waits
- TestNG grouping
- Automatic failure screenshots
- Allure reporting
- Automated defect verification
- Portfolio-ready test reporting

---

# 4. Project Structure

```text
selenium/
├── pom.xml
├── testng.xml
├── README.md
└── src/
    └── test/
        ├── java/
        │   └── com/qacommercelab/
        │       ├── base/
        │       │   ├── BaseTest.java
        │       │   └── DriverFactory.java
        │       │
        │       ├── listeners/
        │       │   └── TestListener.java
        │       │
        │       ├── pages/
        │       │   ├── AccountPage.java
        │       │   ├── CartPage.java
        │       │   ├── CheckoutPage.java
        │       │   ├── ContactPage.java
        │       │   ├── HomePage.java
        │       │   ├── LoginPage.java
        │       │   ├── ProductsPage.java
        │       │   ├── QALabPage.java
        │       │   └── RegistrationPage.java
        │       │
        │       ├── tests/
        │       │   ├── AccountTests.java
        │       │   ├── CartTests.java
        │       │   ├── CheckoutTests.java
        │       │   ├── ContactTests.java
        │       │   ├── CouponTests.java
        │       │   ├── FrameworkSmokeTest.java
        │       │   ├── LoginTests.java
        │       │   ├── ProductsTests.java
        │       │   ├── QALabTests.java
        │       │   └── RegistrationTests.java
        │       │
        │       └── utils/
        │           ├── AllureEnvironment.java
        │           └── ConfigReader.java
        │
        └── resources/
            └── config.properties
```

Repository-level Selenium evidence is stored under:

```text
reports/
└── selenium/
    ├── screenshots/
    └── allure-report/
```

---

# 5. Configuration

Configuration file:

```text
src/test/resources/config.properties
```

Expected configuration:

```properties
baseUrl=https://qa-commerce-lab.vercel.app
browser=chrome
headless=false
explicitWait=10
pageLoadTimeout=30
seedEmail=qa.user@example.com
seedPassword=Password123!
```

Configuration values can be overridden through Maven system properties.

Example:

```powershell
mvn test -Dbrowser=firefox
```

Example:

```powershell
mvn test -Dheadless=true
```

Example:

```powershell
mvn test -Dbrowser=edge -Dheadless=true
```

---

# 6. Seed Test Account

Reusable seed account:

```text
Email: qa.user@example.com
Password: Password123!
```

The application intentionally protects the seed account from permanent deletion so it can be reused during repeated test execution.

---

# 7. Prerequisites

Required tools:

- Java 21
- Maven 3.9+
- Chrome, Firefox, or Edge
- Allure CLI for local Allure report viewing

Verify Java:

```powershell
java -version
```

Verify Maven:

```powershell
mvn -version
```

Verify Allure:

```powershell
allure --version
```

---

# 8. Compile the Framework

Navigate to the Selenium directory:

```powershell
cd C:\Projects\qa-commerce-qa-portfolio\selenium
```

Compile:

```powershell
mvn clean test-compile
```

Expected result:

```text
BUILD SUCCESS
```

---

# 9. Run the Complete Selenium Suite

Run:

```powershell
mvn test
```

The complete suite may report failures while confirmed defects remain open.

Known-defect tests intentionally continue to assert the correct expected business behavior instead of changing assertions to match defective application behavior.

---

# 10. TestNG Groups

Tests are grouped so individual areas can be executed independently.

## Smoke

```powershell
mvn test -Dgroups=smoke
```

## Regression

```powershell
mvn test -Dgroups=regression
```

## Authentication

```powershell
mvn test -Dgroups=authentication
```

## Registration

```powershell
mvn test -Dgroups=registration
```

## Products

```powershell
mvn test -Dgroups=products
```

## Cart

```powershell
mvn test -Dgroups=cart
```

## Checkout

```powershell
mvn test -Dgroups=checkout
```

## Account

```powershell
mvn test -Dgroups=account
```

## Contact

```powershell
mvn test -Dgroups=contact
```

## QA Lab

```powershell
mvn test -Dgroups=qa-lab
```

## Confirmed Defect Tests

```powershell
mvn test -Dgroups=defect-candidate
```

---

# 11. Browser Execution

The framework supports Chrome, Firefox, and Microsoft Edge.

## Chrome

```powershell
mvn test -Dbrowser=chrome
```

## Firefox

```powershell
mvn test -Dbrowser=firefox
```

## Edge

```powershell
mvn test -Dbrowser=edge
```

---

# 12. Headless Execution

## Chrome Headless

```powershell
mvn test -Dbrowser=chrome -Dheadless=true
```

## Firefox Headless

```powershell
mvn test -Dbrowser=firefox -Dheadless=true
```

## Edge Headless

```powershell
mvn test -Dbrowser=edge -Dheadless=true
```

---

# 13. Selenium Manager

The framework uses Selenium Manager instead of manually configured browser-driver paths.

Drivers are initialized using:

```java
new ChromeDriver();
new FirefoxDriver();
new EdgeDriver();
```

Selenium automatically resolves compatible drivers for installed browsers.

---

# 14. Page Object Model

The framework follows the Page Object Model.

Page objects contain:

- Locators
- User interactions
- Page-specific waits
- Reusable application actions

Test classes contain:

- Test scenarios
- Test data
- Assertions
- Expected behavior

Current page objects:

- `HomePage`
- `LoginPage`
- `RegistrationPage`
- `ProductsPage`
- `CartPage`
- `CheckoutPage`
- `AccountPage`
- `ContactPage`
- `QALabPage`

This keeps tests readable and reduces locator duplication.

---

# 15. Test Isolation Strategy

Every test starts from a clean browser state.

Before each test:

1. WebDriver is created.
2. Base URL is opened.
3. Browser cookies are cleared.
4. `localStorage` is cleared.
5. `sessionStorage` is cleared.
6. Application is refreshed.

This prevents tests from depending on state created by previous tests.

Example behavior:

```java
driver.get(ConfigReader.get("baseUrl"));

driver.manage().deleteAllCookies();

((JavascriptExecutor) driver).executeScript(
        "window.localStorage.clear();" +
        "window.sessionStorage.clear();"
);

driver.navigate().refresh();
```

---

# 16. Waiting Strategy

The framework primarily uses explicit waits through:

```java
WebDriverWait
ExpectedConditions
```

Examples:

```java
ExpectedConditions.visibilityOfElementLocated(...)
```

```java
ExpectedConditions.elementToBeClickable(...)
```

```java
ExpectedConditions.urlContains(...)
```

```java
ExpectedConditions.stalenessOf(...)
```

Hard-coded waits such as:

```java
Thread.sleep(...)
```

should generally be avoided.

---

# 17. Cart DOM Rerender Handling

The cart quantity editor rerenders its DOM after quantity changes.

Calling:

```java
element.clear();
```

can trigger the application's `onchange` behavior before Selenium finishes entering a new quantity.

This can cause:

```text
StaleElementReferenceException
```

The framework therefore selects the existing value using:

```java
Keys.chord(Keys.CONTROL, "a")
```

then enters the replacement quantity.

After the DOM rerenders, Selenium reacquires the new input element.

This provides more stable cart automation.

---

# 18. Automated Coverage

## Core Navigation

Coverage includes:

- Home page loading
- Base URL validation
- Product navigation

---

## Authentication

Coverage includes:

- Valid seed login
- Invalid password
- Invalid email format
- Empty credentials
- Case-insensitive email
- Whitespace trimming
- Case-sensitive password

---

## Registration

Coverage includes:

- Required names
- Invalid email
- Duplicate email
- Duplicate email case-insensitivity
- Minimum password length
- Uppercase requirement
- Lowercase requirement
- Digit requirement
- Special-character requirement
- Password confirmation
- Terms acceptance
- First-name maximum length
- Last-name maximum length
- Password maximum length
- Successful registration
- Login using newly created account

---

## Products

Coverage includes:

- Catalog contains expected products
- Search
- Case-insensitive search
- Search whitespace handling
- Empty search results
- Category filtering
- Price ascending
- Price descending
- Unicode product names
- Out-of-stock products
- Zero-price product
- Add to Cart behavior

---

## Cart

Coverage includes:

- Empty cart
- Added product subtotal
- Quantity changes
- Cart count
- Product removal
- Cart persistence
- Invalid zero quantity
- Stock-limit validation
- Multiple products
- Raw-price rounding
- Guest checkout authentication
- Product-specific quantity rules

---

## Coupons

Coverage includes:

- `SAVE10`
- `FREESHIP`
- `MIN100`
- Minimum subtotal requirement
- Expired coupons
- Invalid coupons
- Lowercase coupon input
- Whitespace trimming
- Automatic free shipping above subtotal threshold

---

## Checkout

Coverage includes:

- Account information prefill
- Required shipping fields
- Address minimum length
- Card validation
- CVV minimum
- CVV maximum
- Expiry format validation
- Declined payment
- Insufficient funds
- Card numbers containing spaces
- Successful checkout
- Duplicate submission prevention
- Cart clearing
- Persisted cart clearing
- Invalid expiry-month validation

---

## Account

Coverage includes:

- Account name
- Account email
- Navigation account state
- Logout
- Logged-out account access
- Empty deletion confirmation
- Case-sensitive `DELETE`
- Whitespace around delete confirmation
- Protected seed account
- Dynamic user deletion

---

## Contact

Coverage includes:

- Valid request
- Minimum name length
- Invalid email
- Required subject
- Supported subjects
- Minimum message length
- Maximum message length
- Unicode data
- Valid file upload
- Unsupported file type
- File above 2 MB

Supported attachment types:

```text
PNG
JPG
JPEG
PDF
```

Maximum attachment size:

```text
2 MB
```

---

## QA Lab

The QA Lab provides deterministic edge cases.

Covered scenarios include:

```text
Slow response
204 No Content
400 Bad Request
401 Unauthorized
404 Not Found
409 Conflict
422 Validation Error
429 Rate Limit
500 Server Error
Large response
Delayed DOM
Modal interactions
```

The delayed DOM element appears after approximately:

```text
900 ms
```

The slow response intentionally takes approximately:

```text
1500 ms
```

The rate-limit response verifies:

```text
Retry-After: 5
```

---

# 19. Automated Test Count

Current approximate test count:

| Test Class | Tests |
|---|---:|
| FrameworkSmokeTest | 1 |
| LoginTests | 7 |
| ProductsTests | 12 |
| CartTests | 11 |
| CouponTests | 9 |
| CheckoutTests | 15 |
| AccountTests | 7 |
| RegistrationTests | 15 |
| ContactTests | 13 |
| QALabTests | 13 |
| **Total** | **103** |

The latest TestNG/Surefire execution output should be treated as the authoritative count if the suite changes.

---

# 20. Dynamic User Strategy

Some account tests create a temporary dynamic user directly inside browser `localStorage`.

This isolates Account behavior from Registration behavior.

Example concept:

```javascript
const user = {
    id: 987654321,
    first: "Delete",
    last: "Tester",
    email: "delete.user@example.com",
    pass: "Password123!"
};

localStorage.setItem(
    "users",
    JSON.stringify([user])
);

localStorage.setItem(
    "user",
    JSON.stringify(user)
);
```

This allows account-deletion behavior to be tested independently.

---

# 21. File Upload Testing

Contact tests create temporary files during execution.

Examples include:

- Valid `.png`
- Invalid `.txt`
- `.pdf` larger than 2 MB

Temporary test files are deleted after execution.

---

# 22. Automatic Failure Screenshots

A custom TestNG listener automatically captures browser screenshots whenever a test fails.

Listener:

```text
TestListener.java
```

Screenshots are stored under:

```text
reports/selenium/screenshots/
```

Example structure:

```text
reports/
└── selenium/
    └── screenshots/
        ├── CartTests-stickerPackQuantityCannotExceed25-....png
        ├── CheckoutTests-impossibleExpiryMonthIsRejected-....png
        └── CheckoutTests-successfulCheckoutClearsCartAndUpdatesCounter-....png
```

Screenshots are also attached to Allure test results.

---

# 23. Allure Reporting

Allure TestNG integration provides:

- Overview
- Suites
- Behaviors
- Features
- Severity
- Test descriptions
- Stack traces
- Environment information
- Screenshot attachments
- Defect metadata

Raw Allure results:

```text
target/allure-results/
```

---

# 24. Allure Environment Information

The framework generates:

```text
target/allure-results/environment.properties
```

The report includes:

- Application
- Base URL
- Browser
- Headless mode
- Java version
- Operating system
- Selenium version
- TestNG version

Example:

```text
Application=QA Commerce Lab
Base URL=https://qa-commerce-lab.vercel.app
Browser=chrome
Headless=false
Java=21
Operating System=Windows 11
Automation Framework=Selenium 4.48.0
Test Framework=TestNG 7.12.0
```

---

# 25. Allure Test Organization

Test classes use:

```java
@Epic("QA Commerce Lab")
@Feature("Authentication")
```

Example feature categories:

| Test Class | Allure Feature |
|---|---|
| FrameworkSmokeTest | Core Navigation |
| LoginTests | Authentication |
| RegistrationTests | Registration |
| ProductsTests | Product Catalog |
| CartTests | Shopping Cart |
| CouponTests | Coupons |
| CheckoutTests | Checkout |
| AccountTests | Account Management |
| ContactTests | Contact Support |
| QALabTests | QA Lab |

Known defect tests additionally use:

```java
@Issue("BUG-ID")
```

and:

```java
@Severity(...)
```

---

# 26. View Temporary Allure Report

Run:

```powershell
allure serve .\target\allure-results
```

Allure automatically generates and opens a temporary report.

---

# 27. Generate Permanent Allure Report

Run:

```powershell
allure generate `
.\target\allure-results `
--clean `
-o ..\reports\selenium\allure-report
```

Permanent report location:

```text
reports/selenium/allure-report/
```

Open it:

```powershell
allure open ..\reports\selenium\allure-report
```

---

# 28. Confirmed Selenium UI Defects

The Selenium phase confirmed three UI defects.

---

## BUG-UI-CHK-001 — Cart Counter Does Not Update After Successful Checkout

### Module

Checkout / Cart

### Severity

S3 — Medium

### Priority

P2 — High

### Related Automated Test

```text
CheckoutTests.successfulCheckoutClearsCartAndUpdatesCounter
```

### Summary

After a successful checkout, cart data is removed from browser storage but the navigation cart counter remains stale.

### Expected Result

Immediately after checkout:

```text
Cart count = 0
```

### Actual Result

The visible counter still displays the previous quantity.

Refreshing the page updates it to:

```text
0
```

### Technical Observation

Checkout clears persisted cart data but does not rerender the navigation/header component.

---

## BUG-UI-CHK-002 — Checkout Accepts Impossible Expiry Month

### Module

Checkout / Payment Validation

### Severity

S2 — High

### Priority

P2 — High

### Related Automated Test

```text
CheckoutTests.impossibleExpiryMonthIsRejected
```

### Test Data

```text
Card: 4242424242424242
Expiry: 13/30
CVV: 123
```

### Expected Result

Month `13` should be rejected.

Valid month range:

```text
01–12
```

### Actual Result

Checkout accepts:

```text
13/30
```

and allows the order to proceed.

### Technical Observation

Expiry validation appears to verify only the format:

```text
NN/NN
```

without validating the semantic month range.

---

## BUG-UI-CART-001 — Sticker Pack Quantity Limit Can Be Bypassed

### Module

Shopping Cart

### Severity

S2 — High

### Priority

P2 — High

### Related Automated Test

```text
CartTests.stickerPackQuantityCannotExceed25
```

### Product

```text
QA Sticker Pack
Product ID: 7
Price: $0.00
Stock: 500
Special maximum quantity: 25
```

### Expected Result

The cart should not allow quantity greater than:

```text
25
```

### Actual Result

The cart quantity editor accepts:

```text
26
```

and potentially values up to physical stock.

### Technical Observation

Add to Cart enforces the special `25` limit, while the cart editor validates only against physical stock `500`.

---

# 29. Known-Failure Strategy

Tests representing confirmed defects intentionally continue to assert correct expected behavior.

They are not rewritten to pass against incorrect application behavior.

Therefore:

```text
Functional tests
→ Expected to pass

Confirmed open-defect tests
→ Expected to fail until the application is fixed
```

This makes the automated tests executable defect evidence.

---

# 30. Expected Full-Suite Behavior

Run:

```powershell
mvn clean test
```

The full Maven execution may end with:

```text
BUILD FAILURE
```

while confirmed defect assertions remain failing.

This is acceptable only when the failures correspond to the documented known defects.

The expected important condition is:

```text
Errors: 0
```

and no unrelated test failures.

---

# 31. Selenium Execution Summary

The Selenium automation phase covers:

- Authentication
- Registration
- Product discovery
- Sorting
- Filtering
- Cart operations
- Coupon logic
- Checkout
- Payment validation
- Account lifecycle
- Contact support
- File uploads
- Unicode behavior
- Dynamic DOM
- HTTP edge cases
- Modal behavior
- Browser state handling

The implementation uses:

- Page Object Model
- TestNG
- Maven
- Selenium Manager
- Explicit waits
- Browser-storage isolation
- Cross-browser execution
- Headless execution
- Failure screenshots
- Allure reporting

---

# 32. Maven / Surefire Reports

TestNG/Surefire output is stored under:

```text
target/surefire-reports/
```

Inspect:

```powershell
Get-ChildItem .\target\surefire-reports
```

These reports provide the exact execution counts and test results.

---

# 33. Final Validation Commands

Compile:

```powershell
mvn clean test-compile
```

Run all tests:

```powershell
mvn clean test
```

Run smoke tests:

```powershell
mvn test -Dgroups=smoke
```

Run regression:

```powershell
mvn test -Dgroups=regression
```

Run defect tests:

```powershell
mvn test -Dgroups=defect-candidate
```

Run headless:

```powershell
mvn test -Dheadless=true
```

Inspect Surefire:

```powershell
Get-ChildItem .\target\surefire-reports
```

Inspect Allure results:

```powershell
Get-ChildItem .\target\allure-results
```

Inspect screenshots:

```powershell
Get-ChildItem `
"C:\Projects\qa-commerce-qa-portfolio\reports\selenium\screenshots"
```

Check repository changes:

```powershell
git status
```

---

# 34. Repository Output Rules

Maven build output should not be committed.

Ignore:

```text
selenium/target/
```

Recommended `.gitignore` entry:

```gitignore
selenium/target/
```

Portfolio evidence may be retained under:

```text
reports/selenium/screenshots/
reports/selenium/allure-report/
```

---

# 35. Useful Commands Reference

## Compile

```powershell
mvn clean test-compile
```

## Run All

```powershell
mvn test
```

## Smoke

```powershell
mvn test -Dgroups=smoke
```

## Regression

```powershell
mvn test -Dgroups=regression
```

## Authentication

```powershell
mvn test -Dgroups=authentication
```

## Registration

```powershell
mvn test -Dgroups=registration
```

## Products

```powershell
mvn test -Dgroups=products
```

## Cart

```powershell
mvn test -Dgroups=cart
```

## Checkout

```powershell
mvn test -Dgroups=checkout
```

## Account

```powershell
mvn test -Dgroups=account
```

## Contact

```powershell
mvn test -Dgroups=contact
```

## QA Lab

```powershell
mvn test -Dgroups=qa-lab
```

## Confirmed Defects

```powershell
mvn test -Dgroups=defect-candidate
```

## Chrome

```powershell
mvn test -Dbrowser=chrome
```

## Firefox

```powershell
mvn test -Dbrowser=firefox
```

## Edge

```powershell
mvn test -Dbrowser=edge
```

## Chrome Headless

```powershell
mvn test -Dbrowser=chrome -Dheadless=true
```

## Firefox Headless

```powershell
mvn test -Dbrowser=firefox -Dheadless=true
```

## Edge Headless

```powershell
mvn test -Dbrowser=edge -Dheadless=true
```

## Temporary Allure Report

```powershell
allure serve .\target\allure-results
```

## Generate Permanent Allure Report

```powershell
allure generate `
.\target\allure-results `
--clean `
-o ..\reports\selenium\allure-report
```

## Open Permanent Allure Report

```powershell
allure open ..\reports\selenium\allure-report
```

---

# 36. Framework Strengths

The Selenium framework demonstrates practical QA automation skills including:

- Maintainable test architecture
- Page Object Model
- Java automation
- TestNG lifecycle management
- Maven dependency management
- Explicit waiting strategies
- Cross-browser execution
- Headless execution
- Browser state management
- Positive and negative testing
- Boundary-value analysis
- Business-rule validation
- Authentication workflows
- Payment-validation scenarios
- File-upload testing
- Dynamic DOM handling
- Selenium stale-element handling
- Test grouping
- Failure evidence
- Allure reporting
- Automated defect detection
- Defect-to-test traceability

---

# 37. Selenium Phase Status

The Selenium automation phase is considered complete when:

- Framework compiles successfully.
- Functional tests pass.
- Only documented open-defect tests fail.
- No unexpected Selenium errors occur.
- Failure screenshots are generated.
- Allure results are generated.
- Permanent Allure report is available.
- Selenium documentation is committed.
- Maven `target/` output is excluded from Git.

Confirmed defects currently covered by automation:

```text
BUG-UI-CHK-001
BUG-UI-CHK-002
BUG-UI-CART-001
```

---

# 38. Final Git Workflow

After validating the framework:

```powershell
git status
```

Review all changes.

Add files:

```powershell
git add .
```

Commit:

```powershell
git commit -m "Add Selenium UI automation framework and reporting"
```

Push:

```powershell
git push origin feature/selenium-framework
```

Then:

1. Open the GitHub repository.
2. Create a pull request from `feature/selenium-framework` into `main`.
3. Review the changed files.
4. Merge the pull request.
5. Delete the remote feature branch if desired.

Return locally:

```powershell
git checkout main
```

Pull the merged changes:

```powershell
git pull origin main
```

Delete the local feature branch:

```powershell
git branch -d feature/selenium-framework
```

---

# 39. Conclusion

The Selenium phase provides broad end-to-end automated UI coverage for QA Commerce Lab.

The framework combines:

- Maintainable Page Object Model architecture
- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Cross-browser support
- Headless execution
- Isolated browser state
- Dynamic UI handling
- Negative and boundary testing
- Automatic screenshot capture
- Allure reporting
- Defect-linked automated assertions

Confirmed application defects remain represented as failing automated assertions rather than being hidden by changing expected results.

This provides both functional regression coverage and executable defect evidence as part of the QA portfolio.