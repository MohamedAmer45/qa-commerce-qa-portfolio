@ui @cart
Feature: Shopping cart
  As a shopper
  I want to manage the items in my basket
  So that I can review what I am about to buy

  @smoke
  Scenario: An empty cart shows the empty state
    When I open the cart page
    Then the cart should show the empty state
    And the cart counter should show 0

  @smoke
  Scenario: An added product appears in the cart
    Given I have added product 10 to the cart
    When I open the cart page
    Then the cart should contain 1 product line
    And the quantity of product 10 in the cart should be 1
    And the cart subtotal should be 39.90

  @regression
  Scenario: Updating the quantity recalculates the subtotal
    Given I have added product 10 to the cart
    And I open the cart page
    When I change the quantity of product 10 to 2
    Then the quantity of product 10 in the cart should be 2
    And the cart subtotal should be 79.80
    And the cart counter should show 2

  @regression
  Scenario: A product can be removed from the cart
    Given I have added product 10 to the cart
    And I open the cart page
    When I remove product 10 from the cart
    Then the cart should contain 0 product lines
    And the cart should show the empty state
    And the cart counter should show 0

  @regression
  Scenario: The cart persists after a browser refresh
    Given I have added product 10 to the cart
    And I open the cart page
    When I refresh the cart page
    Then the cart should contain 1 product line
    And the quantity of product 10 in the cart should be 1
    And the cart subtotal should be 39.90

  @regression
  Scenario Outline: Invalid quantities are rejected and not saved
    Given I have added product <product> to the cart
    And I open the cart page
    When I try to change the quantity of product <product> to <quantity>
    Then a cart notification should say "Invalid quantity"
    And the quantity of product <product> in the cart should be 1

    Examples:
      | product | quantity | reason                          |
      | 10      | 0        | zero is not a valid quantity    |
      | 2       | 2        | product 2 only has 1 unit stock |

  @regression
  Scenario: Multiple products produce the correct subtotal
    Given I have added product 10 to the cart
    And I have added product 10 to the cart
    And I have added product 11 to the cart
    When I open the cart page
    Then the cart should contain 2 product lines
    And the cart counter should show 3
    And the cart subtotal should be 168.80

  @regression
  Scenario: A decimal product price is rounded to two decimals
    Given I have added product 8 to the cart
    When I open the cart page
    Then the cart subtotal should be 20.00
    And the cart subtotal text should be "$20.00"

  @smoke @checkout
  Scenario: A guest is asked to sign in at checkout
    Given I have added product 10 to the cart
    And I open the cart page
    When I proceed to checkout from the cart
    Then I should be on the checkout page
    And I should be asked to sign in

  @regression @known-defect @defect-candidate @BUG-UI-CART-001
  Scenario: BUG-UI-CART-001 - Sticker Pack quantity cannot exceed 25
    Given I have added product 7 to the cart
    And I open the cart page
    And the quantity of product 7 in the cart should be 1
    When I try to change the quantity of product 7 to 26
    Then the quantity of product 7 in the cart should be 1
