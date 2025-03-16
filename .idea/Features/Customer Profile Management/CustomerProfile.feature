Feature: Customer Profile Management

  Scenario: Customer inputs dietary preferences and allergies
    Given the customer is on the profile settings page
    When they enter dietary preferences "Vegetarian" and allergies "Peanuts"
    Then the system saves the information successfully

  Scenario: Customer updates dietary preferences
    Given the customer has saved dietary preferences "Vegetarian" and allergies "Peanuts"
    When they update preferences to "Vegan" and allergies to "None"
    Then the system updates the information successfully

  Scenario: Chef views multiple customers' dietary preferences
    Given multiple customers with dietary preferences
    When the chef accesses a customer's profile
    Then the chef sees the correct dietary preferences and allergies



