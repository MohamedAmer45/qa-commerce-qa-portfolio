@ui @coupon
Feature: Coupons and shipping
  As a shopper
  I want to apply discount codes to my basket
  So that I pay the correct total

  Note: Standard shipping is $9.99. Orders with a subtotal of $150 or more ship free.

  @smoke
  Scenario: SAVE10 applies a 10 percent discount
    Given I have added product 2 to the cart
    And I open the cart page
    And the cart subtotal should be 129.99
    When I apply the coupon "SAVE10"
    Then the coupon message should be "Coupon applied."
    And the cart total should become 126.98

  @regression
  Scenario Outline: Valid coupons update the order total
    Given I have added product <product> to the cart
    And I open the cart page
    And the cart total should be <total before>
    When I apply the coupon "<coupon>"
    Then the coupon message should be "Coupon applied."
    And the cart total should become <total after>

    Examples:
      | coupon   | product | total before | total after | effect                                 |
      | FREESHIP | 10      | 49.89        | 39.90       | removes the 9.99 shipping charge       |
      | MIN100   | 2       | 139.98       | 124.98      | takes 15.00 off a subtotal of 129.99   |

  @regression
  Scenario: Coupon codes are case insensitive
    Given I have added product 10 to the cart
    And I open the cart page
    When I apply the coupon "freeship"
    Then the coupon message should be "Coupon applied."
    And the cart total should become 39.90

  @regression
  Scenario: Coupon whitespace is trimmed
    Given I have added product 10 to the cart
    And I open the cart page
    When I apply the coupon "   FREESHIP   "
    Then the coupon message should be "Coupon applied."
    And the cart total should become 39.90

  @regression
  Scenario Outline: Invalid coupons are rejected and the total is unchanged
    Given I have added product 10 to the cart
    And I open the cart page
    When I apply the coupon "<coupon>"
    Then the coupon message should be "<message>"
    And the cart total should be 49.89

    Examples:
      | coupon  | message                |
      | MIN100  | Minimum $100 required. |
      | EXPIRED | Expired coupon.        |
      | NOTREAL | Coupon not found.      |

  @regression @cart
  Scenario: Orders of 150 or more receive free shipping automatically
    Given I have added product 2 to the cart
    And I have added product 10 to the cart
    When I open the cart page
    Then the cart subtotal should be 169.89
    And the cart total should be 169.89
