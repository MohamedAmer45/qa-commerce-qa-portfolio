@api @health
Feature: Health check and general API behavior
  As an API consumer
  I want a reliable health endpoint and predictable error handling
  So that I can monitor the service and handle mistakes gracefully

  @smoke
  Scenario: The health endpoint reports the service is up
    When I send a GET request to "/api/health"
    Then the response status should be 200
    And the response header "Content-Type" should contain "application/json"
    And the JSON field "status" should equal "ok"
    And the JSON field "service" should equal "qa-commerce-api"
    And the JSON field "environment" should equal "test"
    And the JSON field "version" should be a non-empty string
    And the JSON field "timestamp" should be a valid timestamp

  @regression
  Scenario: An unsupported HTTP method is rejected with an Allow header
    When I send a POST request to "/api/health" with body:
      """
      {}
      """
    Then the response status should be 405
    And the response header "Allow" should be "GET"
    And the JSON field "error" should equal "METHOD_NOT_ALLOWED"

  @regression
  Scenario: A CORS preflight request is answered without a body
    When I send a OPTIONS request to "/api/health"
    Then the response status should be 204
    And the response header "Access-Control-Allow-Origin" should be "*"
    And the response should have no body

  @regression
  Scenario: Responses expose CORS headers
    When I send a GET request to "/api/health"
    Then the response header "Access-Control-Allow-Origin" should be "*"

  @regression
  Scenario: An unknown endpoint returns a structured 404
    When I send a GET request to "/api/this-endpoint-does-not-exist"
    Then the response status should be 404
    And the JSON field "error" should equal "ENDPOINT_NOT_FOUND"
