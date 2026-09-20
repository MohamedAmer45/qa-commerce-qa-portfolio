@ui @authentication
Feature: Login
  As a registered customer
  I want to sign in with my credentials
  So that I can access my account

  Background:
    Given I open the login page

  @smoke
  Scenario: Valid seeded credentials authenticate successfully
    When I log in as the seed user
    Then I should be on my account page
    And the account navigation should show "QA"

  @regression
  Scenario Outline: Invalid credentials are rejected with a clear message
    When I log in with email "<email>" and password "<password>"
    Then the login error should be "<message>"

    Examples: Rejected login attempts
      | email               | password          | message                         |
      | qa.user@example.com | WrongPassword123! | Invalid credentials.            |
      | qa.user@example.com | password123!      | Invalid credentials.            |
      | invalid-email       | Password123!      | Invalid email format.           |
      |                     |                   | Email and password required.    |

  @regression
  Scenario: Email comparison is case insensitive
    When I log in with email "QA.USER@EXAMPLE.COM" and password "Password123!"
    Then I should be on my account page

  @regression
  Scenario: Leading and trailing email whitespace is trimmed
    When I log in with email "   qa.user@example.com   " and password "Password123!"
    Then I should be on my account page
