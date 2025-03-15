Feature: Order History Management

  Scenario: Customer views past orders
    Given the customer has previous orders
    When they check their order history
    Then they see a list of past orders

  Scenario: Customer reorders a previous meal
    Given the customer has previous orders
    When they reorder "Grilled Chicken"
    Then the system confirms the reorder was successful

  Scenario: Chef accesses different customers’ order histories
    Given multiple customers have order histories
    When the chef checks a specific customer’s history
    Then the chef sees their past orders

  Scenario: Admin retrieves and analyzes order trends
    Given the system administrator wants to analyze trends
    When they retrieve customer order history
    Then they get insights on customer preferences
