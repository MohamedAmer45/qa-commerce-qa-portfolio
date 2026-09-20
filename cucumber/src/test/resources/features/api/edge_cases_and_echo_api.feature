@api @edge-cases
Feature: Edge case and echo endpoints
  As an API client author
  I want deterministic slow, empty and failing responses and a request echo
  So that I can verify my client handles unusual responses

  @regression
  Scenario: The slow mode responds successfully after an intentional delay
    When I send a GET request to "/api/edge-cases?mode=slow"
    Then the response status should be 200
    And the JSON field "ok" should equal "true"
    And the JSON field "delayMs" should equal "1500"
    And the response time should be at least 1400 milliseconds

  @regression
  Scenario: The empty mode returns 204 with no body
    When I send a GET request to "/api/edge-cases?mode=empty"
    Then the response status should be 204
    And the response should have no body

  @regression
  Scenario Outline: Error modes return the documented status and error code
    When I send a GET request to "/api/edge-cases?mode=<mode>"
    Then the response status should be <status>
    And the JSON field "error" should equal "<error>"

    Examples:
      | mode         | status | error            |
      | bad-request  | 400    | BAD_REQUEST      |
      | unauthorized | 401    | UNAUTHORIZED     |
      | not-found    | 404    | NOT_FOUND        |
      | conflict     | 409    | CONFLICT         |
      | validation   | 422    | VALIDATION_ERROR |
      | rate-limit   | 429    | RATE_LIMITED     |
      | server-error | 500    | INTERNAL_ERROR   |
      | unsupported  | 400    | UNKNOWN_MODE     |

  @regression
  Scenario: Validation errors list every invalid field
    When I send a GET request to "/api/edge-cases?mode=validation"
    Then the response status should be 422
    And the JSON field "fields.email" should equal "Invalid email"
    And the JSON field "fields.quantity" should equal "Must be at least 1"

  @regression
  Scenario: The rate limit response includes Retry-After
    When I send a GET request to "/api/edge-cases?mode=rate-limit"
    Then the response status should be 429
    And the response header "Retry-After" should be "5"
    And the JSON field "retryAfterSeconds" should equal "5"

  @regression
  Scenario: A server error is controlled and exposes only a trace ID
    When I send a GET request to "/api/edge-cases?mode=server-error"
    Then the response status should be 500
    And the JSON field "traceId" should be a non-empty string
    And the response body should not contain "node_modules"

  @regression
  Scenario: The large mode returns a large structured payload
    When I send a GET request to "/api/edge-cases?mode=large"
    Then the response status should be 200
    And the JSON field "count" should equal "250"
    And the JSON array "data" should have 250 items
    And the JSON field "data.0.id" should equal "1"
    And the JSON field "data.249.id" should equal "250"

  @regression
  Scenario Outline: A missing or empty mode is rejected
    When I send a GET request to "/api/edge-cases<query>"
    Then the response status should be 400
    And the JSON field "error" should equal "UNKNOWN_MODE"

    Examples:
      | query  |
      |        |
      | ?mode= |

  @smoke @echo
  Scenario Outline: The echo endpoint reports the HTTP method
    When I send a <method> request to "/api/echo"
    Then the response status should be 200
    And the JSON field "method" should equal "<method>"

    Examples:
      | method |
      | GET    |
      | DELETE |

  @regression @echo
  Scenario Outline: The echo endpoint reflects the request body
    When I send a <method> request to "/api/echo" with body:
      """
      {"name":"QA","active":true,"count":10}
      """
    Then the response status should be 200
    And the JSON field "method" should equal "<method>"
    And the JSON field "body.name" should equal "QA"
    And the JSON field "body.active" should equal "true"
    And the JSON field "body.count" should equal "10"

    Examples:
      | method |
      | POST   |
      | PUT    |

  @regression @echo
  Scenario: The echo endpoint reflects query parameters and custom headers
    Given I set the request header "X-Test-Id" to "TC-API-ECHO-009"
    When I send a GET request to "/api/echo?foo=bar&number=123"
    Then the response status should be 200
    And the JSON field "query.foo" should equal "bar"
    And the JSON field "query.number" should equal "123"
    And the JSON field "headers.testId" should equal "TC-API-ECHO-009"

  @regression @echo
  Scenario: The echo endpoint keeps repeated query keys as a list
    When I send a GET request to "/api/echo?tag=a&tag=b"
    Then the response status should be 200
    And the JSON array "query.tag" should have 2 items

  @regression @echo
  Scenario: The echo endpoint rejects malformed JSON with a client error
    When I send a POST request to "/api/echo" with body:
      """
      {bad
      """
    Then the response status should be 400
