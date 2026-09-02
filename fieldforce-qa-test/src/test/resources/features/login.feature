Feature: Login Journey on FieldForceConnect

  Background:
    Given the user is on the FieldForceConnect login page

  Scenario Outline: Login with valid and invalid credentials
    When the user enters email "<email>" and password "<password>"
    And clicks the login button
    Then the login result should be "<expectedResult>"

    Examples:
      | email                          | password             | expectedResult |
      | your_valid_email@example.com   | your_valid_password  | valid          |
      | invalid_user@example.com       | WrongPass123         | invalid        |
      | your_valid_email@example.com   | WrongPassword         | invalid        |
