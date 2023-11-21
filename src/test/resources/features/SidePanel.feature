@ui
Feature: SidePanel functionalities
  Scenario: User navigates to different pages using the Side Panel links
    Given an user with Admin rights is logged in
    And the SidePanel is displayed
    When the user navigates to the Admin page using side bar link
    Then the Admin page should be displayed
    And the user navigates to the PIM page using side bar link
    And the PIM page should be displayed

