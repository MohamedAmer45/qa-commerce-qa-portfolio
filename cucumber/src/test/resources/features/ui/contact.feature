@ui @contact
Feature: Contact support
  As a customer
  I want to send a support request
  So that the store can help me with a problem

  Background:
    Given I open the contact page

  @smoke
  Scenario: A valid support request is accepted
    When I fill in the contact form with valid details
    And I submit the contact form
    Then the contact result should be "Support request accepted."

  @regression
  Scenario Outline: Contact form field validation
    When I complete the contact form with name "<name>", email "<email>" and subject "<subject>"
    And I submit the contact form
    Then the contact result should be "<result>"

    Examples: Name, email and subject rules
      | name      | email               | subject           | result                    |
      | A         | qa@example.com      | Technical problem | Name too short.           |
      | QA        | qa@example.com      | Technical problem | Support request accepted. |
      | QA Tester | invalid-email       | Technical problem | Invalid email.            |
      | QA Tester | qa@example.com      |                   | Select subject.           |

  @regression
  Scenario Outline: The message must be between 20 and 1000 characters
    When I complete the contact form with a message of <length> characters
    And I submit the contact form
    Then the contact result should be "<result>"

    Examples:
      | length | result                    |
      | 19     | Message too short.        |
      | 20     | Support request accepted. |

  @regression
  Scenario: The message field enforces a maximum of one thousand characters
    When I type 1001 characters into the contact message field
    Then the contact message field should contain 1000 characters

  @regression
  Scenario Outline: Every supported subject can be selected
    When I select the contact subject "<subject>"
    Then the selected contact subject should be "<subject>"

    Examples:
      | subject          |
      | Order issue      |
      | Product question |
      | Technical problem|
      | Other            |

  @regression
  Scenario: Unicode contact data is accepted
    When I complete the contact form with the name "محمد عامر" and the message "هذه رسالة اختبار تحتوي على نص عربي وتتجاوز عشرين حرفاً."
    And I submit the contact form
    Then the contact result should be "Support request accepted."

  @regression
  Scenario Outline: File attachments are validated by type and size
    When I fill in the contact form with valid details
    And I attach a temporary "<extension>" file of <bytes> bytes
    And I submit the contact form
    Then the contact result should be "<result>"

    Examples:
      | extension | bytes   | result                    | reason                    |
      | png       | 18      | Support request accepted. | supported type, small     |
      | txt       | 25      | File type not allowed.    | unsupported extension     |
      | pdf       | 2097153 | File too large.           | one byte above 2 MB limit |
