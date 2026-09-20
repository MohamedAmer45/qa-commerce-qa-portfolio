@api @search
Feature: Search API
  As an API consumer
  I want to search the catalog by name, brand or category
  So that shoppers can find products quickly

  @smoke
  Scenario: Search by product name
    When I send a GET request to "/api/search?q=keyboard"
    Then the response status should be 200
    And the JSON field "query" should equal "keyboard"
    And the JSON field "count" should equal "1"
    And the JSON field "data.0.name" should equal "Pulse 75 Mechanical Keyboard"

  @regression
  Scenario: Search by brand
    When I send a GET request to "/api/search?q=Northstar"
    Then the response status should be 200
    And the JSON array "data" should have at least 1 item
    And the JSON field "data.0.brand" should equal "Northstar"

  @regression
  Scenario: Search by category
    When I send a GET request to "/api/search?q=Accessories"
    Then the response status should be 200
    And the JSON field "count" should equal "3"
    And every item in "data" should have "category" equal to "Accessories"

  @regression
  Scenario Outline: Search ignores letter case and surrounding whitespace
    When I send a GET request to "/api/search?q=<query>"
    Then the response status should be 200
    And the JSON field "count" should equal "1"
    And the JSON field "data.0.id" should equal "2"

    Examples: Query is URL encoded
      | query          |
      | KEYBOARD       |
      | %20keyboard%20 |

  @regression
  Scenario: A query with no matches returns an empty list
    When I send a GET request to "/api/search?q=zzzz-no-match"
    Then the response status should be 200
    And the JSON field "count" should equal "0"
    And the JSON array "data" should have 0 items

  @regression
  Scenario: A 100 character query is supported
    When I search the API for a query of 100 "a" characters
    Then the response status should be 200
    And the JSON field "count" should equal "0"

  @regression
  Scenario: Unicode queries are supported
    When I send a GET request to "/api/search?q=Caf%C3%A9"
    Then the response status should be 200
    And the JSON field "count" should equal "1"
    And the JSON field "data.0.id" should equal "4"
