@ui @products
Feature: Product catalog
  As a shopper
  I want to browse, search, filter and sort products
  So that I can find what I want to buy

  Background:
    Given I open the products page

  @smoke
  Scenario: The catalog displays every product
    Then I should see 12 products
    And the result count should read "12 results"

  @regression
  Scenario Outline: Search finds a product regardless of letter case
    When I search for "<term>"
    Then I should see 1 product
    And product 2 should be displayed
    And product 2 should show "Pulse 75 Mechanical Keyboard"

    Examples:
      | term     |
      | keyboard |
      | KEYBOARD |

  @regression
  Scenario: Search trims leading and trailing whitespace
    When I search for "   keyboard   "
    Then I should see 1 product
    And product 2 should be displayed

  @regression
  Scenario: A search with no matches shows the empty state
    When I search for "this-product-does-not-exist"
    Then I should see 0 products
    And the empty products state should be displayed
    And the result count should read "0 results"

  @regression
  Scenario: Filtering by category shows only matching products
    When I filter products by the "Accessories" category
    Then I should see 3 products
    And the result count should read "3 results"

  @regression
  Scenario: Products can be sorted by ascending price
    When I sort products by "Price ↑"
    Then the product prices should be sorted in ascending order
    And the first product price should be 0.00

  @regression
  Scenario: Products can be sorted by descending price
    When I sort products by "Price ↓"
    Then the product prices should be sorted in descending order
    And the first product price should be 99999.99

  @regression
  Scenario: Unicode product names render correctly
    Then product 4 should show "Café Élan Travel Mug — إصدار محدود"

  @regression
  Scenario: An out-of-stock product cannot be added to the cart
    Then product 3 should show "Out of stock"
    And the add to cart button for product 3 should be disabled

  @regression
  Scenario: A zero-price product is displayed and can be added to the cart
    Then product 7 should show "$0.00"
    And the add to cart button for product 7 should be enabled

  @smoke @cart
  Scenario: An in-stock product can be added to the cart
    Given the cart counter should show 0
    When I add product 10 to the cart from the products page
    Then the cart counter should show 1
