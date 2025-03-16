Feature: Ingredient Price Fetching and Automatic Purchase Order

  As a kitchen manager,
  I want the system to fetch real-time ingredient prices from suppliers
  So that I can make cost-effective purchasing decisions.

  As a system,
  I want to automatically generate purchase orders when stock levels are critically low
  So that supplies are replenished without manual intervention.

  Scenario Outline: Fetch real-time ingredient prices from suppliers
    Given the kitchen manager needs to check the price of "<ingredient>"
    When the system requests the latest price from the supplier API
    Then the system should display the current price of "<ingredient>" as "<price>"

    Examples:
      | ingredient | price  |
      | Flour      | $2.50  |
      | Sugar      | $1.80  |
      | Butter     | $4.20  |

  Scenario Outline: Automatic purchase order when stock is critically low
    Given the stock level of "<ingredient>" is <stock> units
    And the critical threshold for "<ingredient>" is <threshold> units
    When the system detects that stock level is below the critical threshold
    Then the system should generate a purchase order for "<ingredient>"
    And notify the kitchen manager with a message "Purchase Order Placed for <ingredient>"

    Examples:
      | ingredient | stock | threshold |
      | Flour      | 3     | 5         |
      | Sugar      | 2     | 4         |
      | Butter     | 1     | 3         |
