@ui @qa-lab
Feature: QA Lab failure modes
  As a QA engineer
  I want deterministic slow, failing and dynamic behaviors
  So that I can verify how automation copes with them

  Background:
    Given I open the QA Lab page

  @smoke
  Scenario: A delayed DOM element appears after the trigger
    When I trigger the delayed DOM element
    Then the dynamic element should appear with the text "Dynamic element appeared after 900 ms."

  @regression
  Scenario: The slow scenario succeeds after an intentional delay
    When I run the QA Lab scenario "lab-async"
    Then the QA Lab status should be 200
    And the QA Lab response should have taken at least 1400 milliseconds

  @regression
  Scenario Outline: Deterministic HTTP status scenarios
    When I run the QA Lab scenario "<scenario>"
    Then the QA Lab status should be <status>

    Examples:
      | scenario | status | meaning          |
      | lab-400  | 400    | bad request      |
      | lab-401  | 401    | unauthorized     |
      | lab-404  | 404    | not found        |
      | lab-409  | 409    | conflict         |
      | lab-422  | 422    | validation error |

  @regression
  Scenario: The empty scenario returns 204 without a body
    When I run the QA Lab scenario "lab-204"
    Then the QA Lab status should be 204
    And the QA Lab response should have no body

  @regression
  Scenario: The rate limit scenario returns 429 with a Retry-After value
    When I run the QA Lab scenario "lab-429"
    Then the QA Lab status should be 429
    And the QA Lab Retry-After value should be "5"

  @regression
  Scenario: The server error scenario is controlled and leaks no internals
    When I run the QA Lab scenario "lab-500"
    Then the QA Lab status should be 500
    And the QA Lab response should not expose server internals

  @regression
  Scenario: The large response scenario succeeds
    When I run the QA Lab scenario "lab-big"
    Then the QA Lab status should be 200
    And the QA Lab response should be larger than 1000 characters

  @smoke
  Scenario: A modal can be opened and cancelled
    When I open the QA Lab modal
    Then the QA Lab modal should be displayed
    When I cancel the QA Lab modal
    Then the QA Lab modal should be closed

  @regression
  Scenario: A modal can be opened and confirmed
    When I open the QA Lab modal
    Then the QA Lab modal should be displayed
    When I confirm the QA Lab modal
    Then the QA Lab modal should be closed
