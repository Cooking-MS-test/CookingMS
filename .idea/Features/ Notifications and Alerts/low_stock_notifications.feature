Feature: Notify users of low-stock ingredients

  Background:
    Given the system monitors stock levels for ingredients
    And kitchen managers need alerts when stock is low to reorder in time

  Scenario Outline: Stock level check and notification
    Given the stock level of "<ingredient>" is <stock> units with a critical threshold of <threshold> units
    When the system checks the stock levels
    Then the system should notify the kitchen manager about "<ingredient>"
    And the notification message should be "Alert: Stock for <ingredient> is low. Only <stock> units remaining!"
    And the system should display "Notification sent successfully."

    Examples:
      | ingredient | stock | threshold |
      | Flour      | 3     | 5         |
      | Sugar      | 2     | 4         |
      | Butter     | 1     | 3         |

  Scenario: No notification for ingredients above threshold
    Given the stock level of "Milk" is 10 units with a critical threshold of 8 units
    When the system checks the stock levels
    Then the system should not notify the kitchen manager about "Milk"
    And the notification message should be "No notification sent"
    And the system should display "No action needed."
