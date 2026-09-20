# QA Commerce Lab — Cucumber BDD Automation

Behavior-driven tests for the **QA Commerce Lab** application, written in Gherkin and executed with Cucumber JVM.
The suite covers the storefront UI (through Selenium) and the REST API (through Java's HTTP client), so every
functionality already covered by the Selenium, Playwright, Cypress and Postman suites also has a readable,
business-language specification.

Application under test: `https://qa-commerce-lab.vercel.app`

## Technology stack

- Java 21, Maven 3.9+
- Cucumber JVM 7.34.8 (`cucumber-java`, `cucumber-testng`, `cucumber-picocontainer`)
- Selenium WebDriver 4.48.0 with the existing Page Object Model
- TestNG 7.12.0 as the runner
- Jackson for JSON assertions on API responses
- Allure (`allure-cucumber7-jvm`) plus the Cucumber HTML and JSON reports

## Project structure

```text
cucumber/
├── pom.xml
└── src/test
    ├── java/com/qacommercelab/cucumber
    │   ├── runner/RunCucumberTest.java     # TestNG entry point
    │   ├── hooks/Hooks.java                # browser lifecycle, failure screenshots, Allure environment
    │   ├── support/Pages.java              # creates the shared page objects on demand
    │   ├── support/ApiClient.java          # HTTP client + JSON path lookup for API scenarios
    │   └── steps
    │       ├── ui/                         # one step class per functional area
    │       └── api/ApiSteps.java           # generic, reusable REST steps
    └── resources/features
        ├── ui/                             # 10 feature files
        └── api/                            # 10 feature files
```

### Reuse of the Selenium framework

The page objects, `DriverFactory` and `ConfigReader` are **not copied**. `pom.xml` adds
`../selenium/src/test/java` as a test source root and restricts compilation to
`pages`, `utils/ConfigReader` and `base/DriverFactory` (see `testIncludes`). The Selenium TestNG
tests and listener are not compiled here. `config.properties` is shared the same way, so
`baseUrl`, `browser`, `headless` and the seed credentials have a single source of truth.

## Coverage

| Area | Feature file | Type |
| --- | --- | --- |
| Home and navigation | `ui/home_navigation.feature` | UI |
| Login | `ui/authentication.feature` | UI |
| Registration | `ui/registration.feature` | UI |
| Account, logout, deletion | `ui/account.feature` | UI |
| Product catalog, search, filter, sort | `ui/products.feature` | UI |
| Cart | `ui/cart.feature` | UI |
| Coupons and shipping | `ui/coupons.feature` | UI |
| Checkout and payment | `ui/checkout.feature` | UI |
| Contact form and attachments | `ui/contact.feature` | UI |
| QA Lab failure modes | `ui/qa_lab.feature` | UI |
| Health and general API behavior | `api/health_and_general.feature` | API |
| Authentication | `api/authentication_api.feature` | API |
| Products | `api/products_api.feature` | API |
| Search | `api/search_api.feature` | API |
| Coupons | `api/coupons_api.feature` | API |
| Orders | `api/orders_api.feature` | API |
| Users | `api/users_api.feature` | API |
| Reviews | `api/reviews_api.feature` | API |
| Contact | `api/contact_api.feature` | API |
| Edge cases and echo | `api/edge_cases_and_echo_api.feature` | API |

Scenario Outlines are used wherever the same behavior is checked with different data, so the
suite expands to more executed scenarios than there are scenario definitions.

## Tags

| Tag | Meaning |
| --- | --- |
| `@ui` / `@api` | Layer. Only `@ui` scenarios start a browser. |
| `@smoke` / `@regression` | Suite depth |
| `@authentication`, `@products`, `@cart`, `@coupon`, `@checkout`, `@contact`, `@qa-lab`, ... | Functional area |
| `@known-defect` | Confirmed application defect. The scenario asserts the **correct** behavior and is expected to fail until the defect is fixed. |
| `@BUG-...` | The defect ID from `manual-testing/bug-reports` |

Known-defect scenarios (`BUG-UI-CART-001`, `BUG-UI-CHK-001`, `BUG-UI-CHK-002`, `BUG-API-ORD-001`)
are excluded from CI, the same way the Selenium suite excludes its `known-defect` group.

## Running the tests

Run everything from the `cucumber` directory.

```bash
# Full functional suite (what CI runs)
mvn test -Dcucumber.filter.tags="not @known-defect" -Dheadless=true

# Smoke only
mvn test -Dcucumber.filter.tags="@smoke" -Dheadless=true

# API only (no browser is launched)
mvn test -Dcucumber.filter.tags="@api"

# One feature area
mvn test -Dcucumber.filter.tags="@checkout and not @known-defect" -Dheadless=true

# Confirmed defects (expected to fail)
mvn test -Dcucumber.filter.tags="@known-defect" -Dheadless=true

# Another browser
mvn test -Dbrowser=firefox -Dheadless=true
```

> **Windows PowerShell:** PowerShell splits `-Dcucumber.filter.tags=...` at the dot, which makes
> Maven fail with `Unknown lifecycle phase ".filter.tags=..."`. Quote the whole argument:
> `mvn test "-Dcucumber.filter.tags=not @known-defect" "-Dheadless=true"`.
> Git Bash, macOS/Linux shells and CI are not affected.

| Property | Default | Purpose |
| --- | --- | --- |
| `cucumber.filter.tags` | none (everything) | Tag expression |
| `browser` | `chrome` | `chrome`, `firefox` or `edge` |
| `headless` | `false` | Run without a visible browser |
| `baseUrl` | `https://qa-commerce-lab.vercel.app` | Application under test |

## Reports

| Report | Location |
| --- | --- |
| Cucumber HTML | `target/cucumber-reports/cucumber.html` |
| Cucumber JSON | `target/cucumber-reports/cucumber.json` |
| Surefire | `target/surefire-reports` |
| Allure results | `target/allure-results` (`allure serve target/allure-results`) |
| Failure screenshots | `reports/cucumber/screenshots` (also embedded in the Cucumber and Allure reports) |

## Continuous integration

`.github/workflows/cucumber.yml` runs on every push and pull request to `main`, and on demand.
It sets up Java 21 and Chrome, compiles the framework, runs the suite with
`not @known-defect`, and uploads the Cucumber, Surefire and Allure reports and any failure
screenshots. When started manually (`workflow_dispatch`) it accepts a custom tag expression.

## Writing new scenarios

1. Reuse an existing step where possible. Step wording is shared across features.
2. UI steps belong in the step class for that functional area and use `Pages` to reach the
   page objects. Never read the WebDriver in a step-class constructor, because the browser
   starts in the `@Before` hook that runs after the object is built.
3. API scenarios need no new Java for typical checks: send a request, assert the status, then
   assert JSON fields with dotted paths such as `data.0.name`.
4. Gherkin trims whitespace inside table cells. To test leading or trailing whitespace, write
   the value in the step text or a docstring instead of an Examples table.
