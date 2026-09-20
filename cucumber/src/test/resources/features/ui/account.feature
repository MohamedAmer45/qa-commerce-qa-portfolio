@ui @account
Feature: Account management
  As a signed-in customer
  I want to view my account, sign out and delete my account
  So that I stay in control of my profile

  @smoke
  Scenario: Account page displays the authenticated user's details
    Given I am logged in as the seed user
    Then the account page should show the name "QA Tester" and email "qa.user@example.com"
    And the account navigation should show "QA"

  @smoke
  Scenario: Logout clears the authenticated session
    Given I am logged in as the seed user
    When I log out
    Then I should be returned to the home page
    And the account navigation should show "Sign in"
    When I open the account page
    Then the signed-out message should be displayed

  @regression
  Scenario Outline: Account deletion requires the exact confirmation text
    Given I am logged in as the seed user
    When I request account deletion with confirmation "<confirmation>"
    Then the account message should be "Confirmation must equal DELETE."
    And I should still be on the account page
    And the account navigation should show "QA"

    Examples: Rejected confirmations
      | confirmation |
      |              |
      | delete       |

  @regression
  Scenario: Delete confirmation surrounded by whitespace is rejected
    Given I am logged in as the seed user
    When I request account deletion with confirmation " DELETE "
    Then the account message should be "Confirmation must equal DELETE."
    And I should still be on the account page

  @regression
  Scenario: The seed account remains usable after a delete action
    Given I am logged in as the seed user
    When I request account deletion with confirmation "DELETE"
    Then I should be returned to the home page
    And the account navigation should show "Sign in"
    When I open the login page
    And I log in as the seed user
    Then I should be on my account page

  @regression
  Scenario: A dynamically created account can be deleted
    Given a dynamic user "Delete" "Tester" with email "delete.user@example.com" and password "Password123!" is signed in
    When I open the account page
    Then the account page should show the name "Delete Tester" and email "delete.user@example.com"
    When I request account deletion with confirmation "DELETE"
    Then I should be returned to the home page
    And the account navigation should show "Sign in"
    When I open the login page
    And I log in with email "delete.user@example.com" and password "Password123!"
    Then the login error should be "Invalid credentials."
