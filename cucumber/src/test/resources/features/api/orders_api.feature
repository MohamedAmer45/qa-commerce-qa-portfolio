@api @orders
Feature: Orders API
  As an API consumer
  I want to place orders for products
  So that customers can buy what they have selected

  Rows omitted or left blank in "I place an order with these details" are not sent,
  which is how incomplete orders are described.

  @smoke
  Scenario: A valid order is created
    When I place an order for product 10 with quantity 2 using card "4242424242424242"
    Then the response status should be 201
    And the JSON field "data.id" should equal "ORD-API-0001"
    And the JSON field "data.status" should equal "Confirmed"
    And the JSON field "data.currency" should equal "USD"
    And the JSON field "data.subtotal" should equal "79.8"

  @regression
  Scenario Outline: The order subtotal is calculated from current prices
    When I place an order with these details:
      | items   | <items>             |
      | email   | qa.user@example.com |
      | address | 123 QA Street       |
      | card    | 4242424242424242    |
    Then the response status should be 201
    And the JSON field "data.status" should equal "Confirmed"
    And the JSON field "data.subtotal" should equal "<subtotal>"

    Examples:
      | items                                    | subtotal | note                        |
      | [{"id":10,"qty":1}]                      | 39.9     | single unit                 |
      | [{"id":8,"qty":1}]                       | 20       | raw price 19.999 is rounded |
      | [{"id":10,"qty":2},{"id":11,"qty":1}]    | 168.8    | multiple products           |

  @regression
  Scenario: A card number containing spaces is accepted
    When I place an order for product 10 with quantity 1 using card "4242 4242 4242 4242"
    Then the response status should be 201
    And the JSON field "data.status" should equal "Confirmed"

  @regression
  Scenario Outline: Incomplete or malformed orders are rejected with a field error
    When I place an order with these details:
      | items   | <items>   |
      | email   | <email>   |
      | address | <address> |
      | card    | <card>    |
    Then the response status should be 422
    And the JSON field "error" should equal "VALIDATION_ERROR"
    And the JSON field "fields.<field>" should equal "<message>"

    Examples: Items
      | items               | email               | address       | card             | field | message                    |
      | []                  | qa.user@example.com | 123 QA Street | 4242424242424242 | items | At least one item required |
      |                     | qa.user@example.com | 123 QA Street | 4242424242424242 | items | At least one item required |

    Examples: Shipping
      | items               | email               | address       | card             | field    | message                 |
      | [{"id":10,"qty":1}] |                     |               | 4242424242424242 | shipping | Valid shipping required |
      | [{"id":10,"qty":1}] |                     | 123 QA Street | 4242424242424242 | shipping | Valid shipping required |
      | [{"id":10,"qty":1}] | invalid-email       | 123 QA Street | 4242424242424242 | shipping | Valid shipping required |
      | [{"id":10,"qty":1}] | qa.user@example.com |               | 4242424242424242 | shipping | Valid shipping required |

    Examples: Payment
      | items               | email               | address       | card             | field   | message                  |
      | [{"id":10,"qty":1}] | qa.user@example.com | 123 QA Street |                  | payment | Valid test card required |
      | [{"id":10,"qty":1}] | qa.user@example.com | 123 QA Street | 1234567890123456 | payment | Valid test card required |

  @regression
  Scenario Outline: Invalid quantities are rejected
    When I place an order for product 10 with quantity <quantity> using card "4242424242424242"
    Then the response status should be 422
    And the JSON field "error" should equal "INVALID_QUANTITY"

    Examples:
      | quantity | note             |
      | 0        | zero             |
      | -1       | negative         |
      | 1.5      | decimal quantity |

  @regression
  Scenario: An unknown product returns 404
    When I place an order for product 99999 with quantity 1 using card "4242424242424242"
    Then the response status should be 404
    And the JSON field "error" should equal "PRODUCT_NOT_FOUND"
    And the JSON field "productId" should equal "99999"

  @regression
  Scenario Outline: Insufficient stock is reported with the available quantity
    When I place an order for product <product> with quantity <quantity> using card "4242424242424242"
    Then the response status should be 409
    And the JSON field "error" should equal "INSUFFICIENT_STOCK"
    And the JSON field "productId" should equal "<product>"
    And the JSON field "available" should equal "<available>"

    Examples:
      | product | quantity | available | note                 |
      | 2       | 2        | 1         | more than the stock  |
      | 3       | 1        | 0         | out-of-stock product |

  @regression
  Scenario Outline: Payment failures return 402 with a specific error
    When I place an order for product 10 with quantity 1 using card "<card>"
    Then the response status should be 402
    And the JSON field "error" should equal "<error>"

    Examples:
      | card             | error              |
      | 4000000000000002 | CARD_DECLINED      |
      | 4000000000009995 | INSUFFICIENT_FUNDS |

  @regression @known-defect @defect-candidate @BUG-API-ORD-001
  Scenario: BUG-API-ORD-001 - Duplicate product lines are validated against combined stock
    When I place an order with these details:
      | items   | [{"id":2,"qty":1},{"id":2,"qty":1}] |
      | email   | qa.user@example.com                 |
      | address | 123 QA Street                       |
      | card    | 4242424242424242                    |
    Then the response status should be 409
    And the JSON field "error" should equal "INSUFFICIENT_STOCK"
    And the JSON field "productId" should equal "2"
    And the JSON field "available" should equal "1"
