@ui
Feature: Admin functionalities
  Scenario: User with Admin rights views Top Bar Menu items on the Admin page
    Given an user with Admin rights is logged in
    And the user navigates to the Admin page using URL
    When the Admin page is displayed
    Then the Top bar Menu should include the following listed items
      | User Management    |
      | Job                |
      | Organization       |
      | Qualifications     |
      | Nationalities      |
      | Corporate Branding |
      | Configuration      |


  Scenario: Admin adds a new user and verifies user presence
    Given an user with Admin rights is logged in
    And the user navigates to the Admin page using URL
    And the user initiates the employee creation
    When user submits the Add User form with the following valid employee details
      | Role             | Admin         |
      | Status           | Enabled       |
      | EmployeeName     | Ram           |
      | EmployeeUsername | TestUsername  |
      | Password         | TestPassword1 |
      | ConfirmPassword  | TestPassword1 |
    And user searches for the newly created employee
    Then the employee should be found in the Employees table



