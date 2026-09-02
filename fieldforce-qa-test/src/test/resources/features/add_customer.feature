Feature: Add Customer on FieldForceConnect

  Background:
    Given the user is logged in with valid credentials

  Scenario Outline: Add a new customer from the dashboard
    When the user adds a customer named "<name>" with mobile "<mobile>" email "<email>" and address "<address>"
    Then a success confirmation should be displayed

    Examples:
      | name          | mobile     | email                       | address              |
      | Rahul Sharma  | 9876543210 | rahul.sharma@example.com    | 12 MG Road, Pune     |
      | Priya Verma   | 9123456780 | priya.verma@example.com     | 45 Park Street, Mumbai |
