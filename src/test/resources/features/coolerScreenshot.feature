@Test
Feature: Take Screenshot of Cooler Screen
  Scenario: Screenshot of each Cooler Screen should be taken successfully
    Given I am on the Planogram Browser homepage
    When I click on Get Coolers button
    And I navigate to each of them and take a screenshot
    Then screenshots should be stored in a folder