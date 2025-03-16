Feature: Ingredient Substitutions Based on Dietary Restrictions

  As a customer,
  I want the system to suggest alternative ingredients if an ingredient is unavailable or does not fit my dietary restrictions
  So that I can enjoy my meal without compromising my health.

  As a chef,
  I want to receive an alert when an ingredient substitution is applied
  So that I can approve or adjust the final recipe.

  Scenario Outline: Customer requests ingredient substitution due to dietary restrictions
    Given I am on the meal customization page
    When I select "<ingredient>" as an ingredient
    And "<ingredient>" does not fit my dietary restrictions
    Then the system should suggest alternative ingredients: "<alternatives>"
    And I should see a message "Here are some alternatives for <ingredient>: <alternatives>"

    Examples:
      | ingredient   | alternatives                          |
      | dairy cheese | vegan cheese, cashew cheese          |
      | peanuts      | almonds, sunflower seeds             |
      | gluten pasta| rice pasta, quinoa pasta             |

  Scenario Outline: Customer requests ingredient substitution due to unavailability
    Given I am on the meal customization page
    When I select "<ingredient>" as an ingredient
    And "<ingredient>" is currently unavailable
    Then the system should suggest alternative ingredients: "<alternatives>"
    And I should see a message "Here are some alternatives for <ingredient>: <alternatives>"

    Examples:
      | ingredient   | alternatives                          |
      | salmon       | trout, cod                           |
      | avocado      | hummus, guacamole                    |
      | quinoa       | couscous, bulgur wheat               |

  Scenario: Chef receives an alert when an ingredient substitution is applied
    Given a customer has requested a substitution for "<ingredient>"
    When the system suggests "<alternatives>" as alternatives
    And the customer selects "<selected_alternative>"
    Then the chef should receive an alert "A substitution has been applied: <ingredient> replaced with <selected_alternative>"
    And the chef should be able to approve or adjust the final recipe

    Examples:
      | ingredient   | alternatives                          | selected_alternative |
      | dairy cheese | vegan cheese, cashew cheese          | vegan cheese         |
      | peanuts      | almonds, sunflower seeds             | almonds              |
      | gluten pasta| rice pasta, quinoa pasta             | rice pasta           |