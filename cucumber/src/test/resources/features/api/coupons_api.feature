@api @coupon
Feature: Coupons API
  As an API consumer
  I want to validate coupon codes against a basket subtotal
  So that the storefront can show the correct discount

  @smoke
  Scenario: SAVE10 returns a 10 percent discount
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "SAVE10", "subtotal": 100 }
      """
    Then the response status should be 200
    And the JSON field "valid" should equal "true"
    And the JSON field "code" should equal "SAVE10"
    And the JSON field "discount" should equal "10"
    And the JSON field "freeShipping" should equal "false"

  @regression
  Scenario: SAVE10 rounds the discount for a decimal subtotal
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "SAVE10", "subtotal": 33.33 }
      """
    Then the response status should be 200
    And the JSON field "discount" should equal "3.33"
    And the JSON field "freeShipping" should equal "false"

  @regression
  Scenario: SAVE10 on a zero subtotal gives no discount
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "SAVE10", "subtotal": 0 }
      """
    Then the response status should be 200
    And the JSON field "valid" should equal "true"
    And the JSON field "discount" should equal "0"

  @regression
  Scenario: FREESHIP grants free shipping without a monetary discount
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "FREESHIP", "subtotal": 50 }
      """
    Then the response status should be 200
    And the JSON field "code" should equal "FREESHIP"
    And the JSON field "discount" should equal "0"
    And the JSON field "freeShipping" should equal "true"

  @regression
  Scenario Outline: MIN100 applies at and above the minimum subtotal
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "MIN100", "subtotal": <subtotal> }
      """
    Then the response status should be 200
    And the JSON field "valid" should equal "true"
    And the JSON field "discount" should equal "15"

    Examples:
      | subtotal | note                       |
      | 100      | exactly at the minimum     |
      | 100.01   | just above the minimum     |

  @regression
  Scenario: MIN100 is rejected below the minimum subtotal
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "MIN100", "subtotal": 99.99 }
      """
    Then the response status should be 422
    And the JSON field "error" should equal "MINIMUM_NOT_MET"
    And the JSON field "minimum" should equal "100"

  @regression
  Scenario: An unknown coupon returns 404
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "UNKNOWN", "subtotal": 100 }
      """
    Then the response status should be 404
    And the JSON field "error" should equal "COUPON_NOT_FOUND"

  @regression
  Scenario Outline: Coupon codes are case insensitive
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "<code>", "subtotal": 100 }
      """
    Then the response status should be 200
    And the JSON field "code" should equal "SAVE10"
    And the JSON field "discount" should equal "10"

    Examples:
      | code   |
      | save10 |
      | SaVe10 |

  @regression
  Scenario: Whitespace around a coupon code is trimmed
    When I send a POST request to "/api/coupons" with body:
      """
      { "code": "   SAVE10   ", "subtotal": 100 }
      """
    Then the response status should be 200
    And the JSON field "code" should equal "SAVE10"
    And the JSON field "discount" should equal "10"
