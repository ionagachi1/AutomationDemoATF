@ui
Feature: Login functionality
  Scenario Outline: User successfully logs in with valid credentials
    Given the user is on the Login page
    When the user provides valid login credentials: "<username>" and "<password>"
    And the user submits the login form
    Then the SidePanel should be displayed

    Examples:
      | username | password |
      | Admin    | admin123 |





