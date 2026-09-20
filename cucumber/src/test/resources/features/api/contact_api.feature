@api @contact
Feature: Contact API
  As an API consumer
  I want to submit support requests
  So that customers can reach the support team

  @smoke
  Scenario: A valid support request is accepted
    When I send a POST request to "/api/contact" with body:
      """
      {
        "name": "QA Tester",
        "email": "qa@example.com",
        "subject": "Technical problem",
        "message": "This is a valid API support request."
      }
      """
    Then the response status should be 202
    And the JSON field "accepted" should equal "true"

  @regression
  Scenario Outline: Name and message length boundaries
    When I send a POST request to "/api/contact" with body:
      """
      {
        "name": "<name>",
        "email": "qa@example.com",
        "subject": "Technical problem",
        "message": "<message>"
      }
      """
    Then the response status should be <status>

    Examples:
      | name | message              | status | note                    |
      | Q    | AAAAAAAAAAAAAAAAAAAA | 422    | one character name      |
      | QA   | AAAAAAAAAAAAAAAAAAAA | 202    | two character name      |
      | QA   | AAAAAAAAAAAAAAAAAAA  | 422    | 19 character message    |
      | QA   | AAAAAAAAAAAAAAAAAAAA | 202    | 20 character message    |
