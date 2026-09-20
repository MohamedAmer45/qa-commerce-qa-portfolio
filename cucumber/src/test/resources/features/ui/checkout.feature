@ui @checkout
Feature: Checkout
  As a signed-in customer
  I want to pay for my basket
  So that my order is placed

  Background:
    Given I am logged in as the seed user
    And I have added product 10 to the cart
    And I am on the checkout page

  @smoke
  Scenario: Checkout pre-fills the account details
    Then the checkout form should be pre-filled with first name "QA", last name "Tester" and email "qa.user@example.com"

  @smoke
  Scenario: A valid checkout creates an order
    When I fill in valid shipping details
    And I fill in valid payment details
    And I place the order
    Then the order should be confirmed
    And the order confirmation should contain "Order confirmed"
    And the order confirmation should contain "ORD-QA-1001"

  @regression
  Scenario: A missing required shipping field is rejected
    When I fill in valid shipping details
    And I clear the shipping city
    And I fill in valid payment details
    And I place the order
    Then the checkout message should be "Complete valid shipping fields."

  @regression
  Scenario: A short address is rejected
    When I fill in valid shipping details
    And I set the shipping address to "1234"
    And I fill in valid payment details
    And I place the order
    Then the checkout message should be "Address too short."

  @regression
  Scenario Outline: Invalid or unsuccessful payments show the right message
    When I fill in valid shipping details
    And I pay with card "<card>", expiry "<expiry>" and CVV "<cvv>"
    And I place the order
    Then the checkout message should be "<message>"

    Examples: Card and expiry validation
      | card             | expiry | cvv   | message                |
      | 1234567890123456 | 12/30  | 123   | Card failed validation.|
      | 4242424242424242 | 12/30  | 12    | Expiry/CVV invalid.    |
      | 4242424242424242 | 12/30  | 12345 | Expiry/CVV invalid.    |
      | 4242424242424242 | 1230   | 123   | Expiry/CVV invalid.    |

    Examples: Payment provider responses
      | card             | expiry | cvv | message             |
      | 4000000000000002 | 12/30  | 123 | Payment declined.   |
      | 4000000000009995 | 12/30  | 123 | Insufficient funds. |

  @regression
  Scenario: A card number containing spaces is accepted
    When I fill in valid shipping details
    And I pay with card "4242 4242 4242 4242", expiry "12/30" and CVV "123"
    And I place the order
    Then the order should be confirmed

  @regression
  Scenario: The place order button is disabled while the order is processing
    When I fill in valid shipping details
    And I fill in valid payment details
    And I place the order
    Then the place order button should be disabled and read "Processing…"
    And the order should be confirmed

  @regression @cart
  Scenario: A successful checkout clears the persisted cart
    Given the cart counter should show 1
    When I fill in valid shipping details
    And I fill in valid payment details
    And I place the order
    Then the order should be confirmed
    When I refresh the current page
    Then the cart counter should show 0

  @regression @cart @known-defect @BUG-UI-CHK-001
  Scenario: BUG-UI-CHK-001 - The cart counter updates immediately after checkout
    Given the cart counter should show 1
    When I fill in valid shipping details
    And I fill in valid payment details
    And I place the order
    Then the order should be confirmed
    And the cart counter should show 0

  @regression @known-defect @defect-candidate @BUG-UI-CHK-002
  Scenario: BUG-UI-CHK-002 - Checkout rejects an impossible expiry month
    When I fill in valid shipping details
    And I pay with card "4242424242424242", expiry "13/30" and CVV "123"
    And I place the order
    Then the order should not be confirmed
    And the checkout message should be "Expiry/CVV invalid."
