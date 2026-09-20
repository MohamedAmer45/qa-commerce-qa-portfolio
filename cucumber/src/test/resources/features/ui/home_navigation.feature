@ui @home
Feature: Home page and navigation
  As a shopper
  I want the store home page to load and lead me to the catalog
  So that I can start browsing products

  @smoke
  Scenario: Home page loads and navigates to the products page
    Given I am on the home page
    Then the home page should be displayed
    When I open the products page from the home page
    Then I should be on the products page
