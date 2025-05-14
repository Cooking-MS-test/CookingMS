Feature: Ingredient Stock Tracking and Restocking Suggestions

  As a kitchen manager,
  I want to track ingredient stock levels in real time
  So that I can prevent shortages and ensure continuous operations.

  As a system,
  I want to automatically suggest restocking when ingredients are low
  So that kitchen managers can take action promptly.

  Scenario Outline: Track ingredient stock levels and suggest restocking
    Given the kitchen manager is monitoring ingredient stock levels
    When the stock level of "<ingredient>" falls below "<threshold>" units
    Then the system should suggest restocking for "<ingredient>"
    And the kitchen manager should see a notification: "Restock <ingredient>: Current stock is <current_stock> units"

    Examples:
      | ingredient   | threshold | current_stock |
      | Flour        | 10        | 8             |
      | Sugar        | 5         | 3             |
      | Olive Oil    | 15        | 13            |

  Scenario Outline: No restocking suggestion when stock is above threshold
    Given the kitchen manager is monitoring ingredient stock levels
    When the stock level of "<ingredient>" is "<current_stock>" units
    And the threshold for "<ingredient>" is "<threshold>" units
    Then the system should not suggest restocking for "<ingredient>"
    And the kitchen manager should not see a restocking notification

    Examples:
      | ingredient   | threshold | current_stock |
      | Flour        | 10        | 12            |
      | Sugar        | 5         | 7             |
      | Olive Oil    | 15        | 18            |