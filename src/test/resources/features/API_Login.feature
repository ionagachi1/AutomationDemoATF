@api
Feature: Login API
  @run
  Scenario: Login with valid Username and Password
    Given I set the base URI
    And MY STEP
    And I set the request body with JSON
     """
    {
      "email": "ion.agachi11@gmail.com",
      "password": "iagachi1983"
    }
    """
    When I send a POST request to "/users/login"
    Then The response "API_LOGIN_RESPONSE" should be valid
    And I should receive a status code of 200 for "API_LOGIN_RESPONSE"
    And I should validate the LOGIN API response body
      | _id       | 6530e2bff5331f0013a0a444 |
      | firstName | Ion                      |
      | lastName  | Agachi                   |
      | email     | ion.agachi11@gmail.com   |
    And I receive valid token

  Scenario: Check ContactsList
    Given I set the base URI
    And I send a POST Login request to "/users/login" with JSON
     """
    {
      "email": "ion.agachi11@gmail.com",
      "password": "iagachi1983"
    }
    """
    And I receive valid token
    When I send GET request to "/contacts" with token
    Then The response "API_CONTACTS_RESPONSE" should be valid
    And I should receive a status code of 200 for "API_CONTACTS_RESPONSE"
    And I should validate the CONTACTS API response body
      | _id                      | firstName      | lastName      | birthdate  | email           | phone      | street1  | street2  | city      | stateProvince | postalCode | country | owner                    |
      | 653a213d3a63b700139e821f | TestFirstName1 | TestLastName1 | 2000-01-01 | test1@gmail.com | 12345678   | Address1 | Address2 | Chisinau  | Moldova       | 2062       | Moldova | 6530e2bff5331f0013a0a444 |
      | 653a219cecc17b0013278455 | TestFirstName2 | TestLastName2 | 2002-02-02 | test2@gmail.com | 1122334455 | Address2 | Address2 | Bucharest | Ilfov         | 12345      | Romania | 6530e2bff5331f0013a0a444 |
