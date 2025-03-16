Feature: Billing System

  Scenario Outline: Customer receives an invoice
    Given the customer has placed an order with ID "<order_id>"
    When the system generates an invoice for order "<order_id>"
    Then the customer should receive the invoice for order "<order_id>"

    Examples:
      | order_id  |
      | 12345     |
      | 67890     |
      | 54321     |

  Scenario Outline: System administrator generates financial reports
    Given the system has order data for the period "<period>"
    When the system generates a financial report for "<period>"
    Then the system administrator should receive the report for "<period>"

    Examples:
      | period    |
      | Q1 2024   |
      | Q2 2024   |
      | 2023 Year |
