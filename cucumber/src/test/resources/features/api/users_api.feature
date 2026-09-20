@api @users
Feature: Users API
  As an API consumer
  I want to create, update and delete users
  So that customer accounts can be managed programmatically

  @smoke
  Scenario: A valid user is created without exposing the password
    When I send a POST request to "/api/users" with body:
      """
      { "firstName": "API", "lastName": "Tester", "email": "api.user01@example.com", "password": "Password123!" }
      """
    Then the response status should be 201
    And the JSON field "data.id" should be a non-empty string
    And the JSON field "data.firstName" should equal "API"
    And the JSON field "data.lastName" should equal "Tester"
    And the JSON field "data.email" should equal "api.user01@example.com"
    And the JSON field "data.password" should not exist

  @regression
  Scenario Outline: Invalid user details are rejected with a field error
    When I send a POST request to "/api/users" with body:
      """
      { "firstName": "<first>", "lastName": "<last>", "email": "<email>", "password": "<password>" }
      """
    Then the response status should be 422
    And the JSON field "error" should equal "VALIDATION_ERROR"
    And the JSON field "fields.<field>" should equal "<message>"

    Examples: Names
      | first                                     | last                                      | email                | password     | field     | message           |
      |                                           | Tester                                    | api.user02@example.com | Password123! | firstName | Required, max 40  |
      | API                                       |                                           | api.user03@example.com | Password123! | lastName  | Required, max 40  |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA | Tester                                    | api.user05@example.com | Password123! | firstName | Required, max 40  |
      | API                                       | BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB | api.user06@example.com | Password123! | lastName  | Required, max 40  |

    Examples: Email
      | first | last   | email         | password     | field | message              |
      | API   | Tester | invalid-email | Password123! | email | Valid email required |

    Examples: Password strength
      | first | last   | email                  | password     | field    | message       |
      | API   | Tester | api.user08@example.com | Aa1!abc      | password | Weak password |
      | API   | Tester | api.user10@example.com | password123! | password | Weak password |
      | API   | Tester | api.user11@example.com | PASSWORD123! | password | Weak password |
      | API   | Tester | api.user12@example.com | Password!    | password | Weak password |
      | API   | Tester | api.user13@example.com | Password123  | password | Weak password |

  @regression
  Scenario Outline: Boundary values are accepted
    When I send a POST request to "/api/users" with body:
      """
      { "firstName": "<first>", "lastName": "Tester", "email": "<email>", "password": "<password>" }
      """
    Then the response status should be 201

    Examples:
      | first                                    | email                  | password | note                        |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA | api.user04@example.com | Password123! | first name of 40 characters |
      | API                                      | api.user09@example.com | Aa1!abcd     | 8 character password        |

  @regression
  Scenario: The seeded email cannot be registered again
    When I send a POST request to "/api/users" with body:
      """
      { "firstName": "API", "lastName": "Tester", "email": "qa.user@example.com", "password": "Password123!" }
      """
    Then the response status should be 409
    And the JSON field "error" should equal "EMAIL_EXISTS"

  @regression
  Scenario: Updating a user requires an ID
    When I send a PUT request to "/api/users" with body:
      """
      {}
      """
    Then the response status should be 400
    And the JSON field "error" should equal "MISSING_ID"

  @regression
  Scenario: A user can be updated and keeps the same ID
    When I send a PUT request to "/api/users" with body:
      """
      { "id": "usr_test", "firstName": "Updated" }
      """
    Then the response status should be 200
    And the JSON field "data.id" should equal "usr_test"
    And the JSON field "data.firstName" should equal "Updated"

  @regression
  Scenario: Deleting a user requires an ID
    When I send a DELETE request to "/api/users" with body:
      """
      {}
      """
    Then the response status should be 400
    And the JSON field "error" should equal "MISSING_ID"

  @regression
  Scenario Outline: Deleting a user requires the exact confirmation text
    When I send a DELETE request to "/api/users" with body:
      """
      <body>
      """
    Then the response status should be 422
    And the JSON field "error" should equal "CONFIRMATION_REQUIRED"

    Examples:
      | body                                    | note                   |
      | { "id": "usr_test" }                    | confirmation missing   |
      | { "id": "usr_test", "confirm": "delete" } | lowercase confirmation |

  @regression
  Scenario: The seeded user is protected from deletion
    When I send a DELETE request to "/api/users" with body:
      """
      { "id": "usr_seed_001", "confirm": "DELETE" }
      """
    Then the response status should be 409
    And the JSON field "error" should equal "SEED_USER_PROTECTED"

  @regression
  Scenario: A dynamic user can be deleted
    When I send a DELETE request to "/api/users" with body:
      """
      { "id": "usr_test", "confirm": "DELETE" }
      """
    Then the response status should be 200
    And the JSON field "deleted" should equal "true"
    And the JSON field "id" should equal "usr_test"
