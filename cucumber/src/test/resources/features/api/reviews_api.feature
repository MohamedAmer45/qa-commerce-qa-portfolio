@api @reviews
Feature: Reviews API
  As an API consumer
  I want to submit product reviews
  So that customers can share feedback

  @smoke
  Scenario: A valid review is created
    When I send a POST request to "/api/reviews" with body:
      """
      {
        "productId": 1,
        "rating": 5,
        "title": "Excellent",
        "body": "This product worked very well during QA testing.",
        "email": "qa@example.com"
      }
      """
    Then the response status should be 201
    And the JSON field "data.id" should be a non-empty string
    And the JSON field "data.productId" should equal "1"
    And the JSON field "data.rating" should equal "5"

  @regression
  Scenario: A review for an unknown product returns 404
    When I send a POST request to "/api/reviews" with body:
      """
      {
        "productId": 99999,
        "rating": 5,
        "title": "Excellent",
        "body": "This product worked very well during QA testing.",
        "email": "qa@example.com"
      }
      """
    Then the response status should be 404
    And the JSON field "error" should equal "PRODUCT_NOT_FOUND"

  @regression
  Scenario Outline: Accepted boundary values
    When I send a POST request to "/api/reviews" with body:
      """
      {
        "productId": 1,
        "rating": <rating>,
        "title": "<title>",
        "body": "<body>",
        "email": "qa@example.com"
      }
      """
    Then the response status should be 201

    Examples:
      | rating | title | body       | note                    |
      | 1      | Good  | 1234567890 | lowest rating, 10 chars |
      | 5      | Abc   | 1234567890 | highest rating, 3 chars |

  @regression
  Scenario Outline: Invalid review fields are rejected
    When I send a POST request to "/api/reviews" with body:
      """
      {
        "productId": 1,
        "rating": <rating>,
        "title": "<title>",
        "body": "<body>",
        "email": "<email>"
      }
      """
    Then the response status should be 422
    And the JSON field "error" should equal "VALIDATION_ERROR"
    And the JSON field "fields.<field>" should equal "<message>"

    Examples: Rating
      | rating | title     | body                                              | email          | field  | message               |
      | 0      | Excellent | This product worked very well during QA testing. | qa@example.com | rating | 1-5 integer required  |
      | 6      | Excellent | This product worked very well during QA testing. | qa@example.com | rating | 1-5 integer required  |
      | 1.5    | Excellent | This product worked very well during QA testing. | qa@example.com | rating | 1-5 integer required  |

    Examples: Title, body and email
      | rating | title | body                                              | email          | field | message         |
      | 3      | Ab    | This product worked very well during QA testing. | qa@example.com | title | Minimum 3 chars |
      | 3      | Abc   | 123456789                                         | qa@example.com | body  | 10-500 chars    |
      | 3      | Abc   | This product worked very well during QA testing. | invalid-email  | email | Valid email required |
