@api @authentication
Feature: Authentication API
  As an API consumer
  I want to exchange credentials for a token
  So that I can act on behalf of a user

  @smoke
  Scenario: Valid seeded credentials return a token
    When I send a POST request to "/api/auth" with body:
      """
      { "email": "qa.user@example.com", "password": "Password123!" }
      """
    Then the response status should be 200
    And the JSON field "token" should be a non-empty string
    And the JSON field "password" should not exist

  @regression
  Scenario: Email matching is case insensitive
    When I send a POST request to "/api/auth" with body:
      """
      { "email": "QA.USER@EXAMPLE.COM", "password": "Password123!" }
      """
    Then the response status should be 200
    And the JSON field "token" should be a non-empty string

  @regression
  Scenario: Leading and trailing email whitespace is trimmed
    When I send a POST request to "/api/auth" with body:
      """
      { "email": "   qa.user@example.com   ", "password": "Password123!" }
      """
    Then the response status should be 200

  @regression
  Scenario Outline: Invalid credentials are rejected
    When I send a POST request to "/api/auth" with body:
      """
      <body>
      """
    Then the response status should be <status>
    And the JSON field "error" should equal "<error>"

    Examples:
      | body                                                                | status | error               |
      | {}                                                                  | 400    | MISSING_CREDENTIALS |
      | { "email": "invalid-email", "password": "Password123!" }            | 422    | INVALID_EMAIL       |
      | { "email": "qa.user@example.com", "password": "WrongPassword123!" } | 401    | INVALID_CREDENTIALS |
      | { "email": "qa.user@example.com", "password": "password123!" }      | 401    | INVALID_CREDENTIALS |
      | { "email": "unknown.user@example.com", "password": "Password123!" } | 401    | INVALID_CREDENTIALS |
