@db
Feature: User Management in Database
  Scenario: Create and Check User Record
    Given a new user entity is created with the following user details
      | username  | testusername1          |
      | password  | testpassword1          |
      | firstname | TestFirstName1         |
      | lastname  | TestLastName1          |
      | email     | ion.agachi1@endava.COM |

    When the user is inserted in the database
    And the user is queried by its username
    Then the user details should be retrieved from the users table

  Scenario: Add multiple users in users DB table
    Given the following user entities are prepared with specific details
      | username | password | firstname   | lastname   | email              |
      | user1    | user!23  | FirstName1  | LastName1  | user1@example.com  |
      | user2    | user!23  | FirstName2  | LastName2  | user2@example.com  |
      | user3    | user!23  | FirstName3  | LastName3  | user3@example.com  |
      | user4    | user!23  | FirstName4  | LastName4  | user4@example.com  |
      | user5    | user!23  | FirstName5  | LastName5  | user5@example.com  |
      | user6    | user!23  | FirstName6  | LastName6  | user6@example.com  |
      | user7    | user!23  | FirstName7  | LastName7  | user7@example.com  |
      | user8    | user!23  | FirstName8  | LastName8  | user8@example.com  |
      | user9    | user!23  | FirstName9  | LastName9  | user9@example.com  |
      | user10   | user!23  | FirstName10 | LastName10 | user10@example.com |
    When the user entities are inserted into the database
    Then all the users should be successfully added in the users table
