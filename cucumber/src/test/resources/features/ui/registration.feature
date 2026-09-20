@ui @registration
Feature: Registration
  As a new customer
  I want to create an account
  So that I can shop and track my orders

  Background:
    Given I open the registration page

  @smoke
  Scenario: Valid registration creates and authenticates the account
    When I register "Automation" "Tester" with email "cucumber.user@example.com" and password "Password123!"
    Then I should be on my account page

  @regression
  Scenario: A newly registered user can sign in again after the session ends
    When I register "Cucumber" "Tester" with email "cucumber.login@example.com" and password "Password123!"
    Then I should be on my account page
    When I end the session but keep the registered account
    And I open the login page
    And I log in with email "cucumber.login@example.com" and password "Password123!"
    Then I should be on my account page

  @regression
  Scenario Outline: Invalid registration details are rejected
    When I enter registration details "<first>", "<last>", "<email>", "<password>" and "<confirmation>"
    And the terms and conditions checkbox is "<terms>"
    And I submit the registration form
    Then the registration message should be "<message>"

    Examples: Required fields and formats
      | first | last | email                   | password     | confirmation | terms     | message                  |
      |       |      | new.user@example.com    | Password123! | Password123! | checked   | Names required.          |
      | New   | User | invalid-email           | Password123! | Password123! | checked   | Valid email required.    |
      | New   | User | QA.USER@EXAMPLE.COM     | Password123! | Password123! | checked   | Email already exists.    |
      | New   | User | mismatch@example.com    | Password123! | Different1!  | checked   | Passwords do not match.  |
      | New   | User | terms@example.com       | Password123! | Password123! | unchecked | Accept terms.            |

    Examples: Password strength rules
      | first | last | email                   | password | confirmation | terms   | message        |
      | New   | User | short@example.com       | Aa1!abc  | Aa1!abc      | checked | Weak password. |
      | New   | User | upper@example.com       | password123! | password123! | checked | Weak password. |
      | New   | User | lower@example.com       | PASSWORD123! | PASSWORD123! | checked | Weak password. |
      | New   | User | digit@example.com       | Password!    | Password!    | checked | Weak password. |
      | New   | User | special@example.com     | Password123  | Password123  | checked | Weak password. |

  @regression
  Scenario Outline: Input length limits are enforced
    When I type <typed> characters into the "<field>" registration field
    Then the "<field>" registration field should contain <allowed> characters

    Examples:
      | field      | typed | allowed |
      | first name | 41    | 40      |
      | last name  | 41    | 40      |
      | password   | 65    | 64      |
