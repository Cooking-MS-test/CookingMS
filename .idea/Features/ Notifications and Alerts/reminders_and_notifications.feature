Feature: Send Reminders for Upcoming Orders and Deliveries

  As a customer,
  I want to receive reminders for my upcoming meal deliveries
  So that I can be prepared to receive them.

  As a chef,
  I want to get notified of scheduled cooking tasks
  So that I can prepare meals on time.

  Scenario Outline: Customer receives a reminder for an upcoming meal delivery
    Given the customer has an upcoming meal delivery for "<meal>" on "<delivery_date>"
    When the system sends a reminder for the delivery
    Then the customer should receive a reminder message: "Reminder: Your <meal> will be delivered on <delivery_date>"

    Examples:
      | meal          | delivery_date  |
      | Vegetarian Pizza | 2023-12-25   |
      | Grilled Salmon   | 2023-12-26   |
      | Chocolate Cake   | 2023-12-27   |

  Scenario Outline: Chef receives a notification for a scheduled cooking task
    Given the chef has a scheduled cooking task for "<meal>" on "<cooking_date>"
    When the system sends a notification for the cooking task
    Then the chef should receive a notification message: "Cooking Task: Prepare <meal> on <cooking_date>"

    Examples:
      | meal          | cooking_date  |
      | Vegetarian Pizza | 2023-12-25   |
      | Grilled Salmon   | 2023-12-26   |
      | Chocolate Cake   | 2023-12-27   |