Feature: Order and Menu Customization

  Scenario Outline: Customer creates a custom meal request
    Given the customer wants to customize their meal
    When they select ingredients "<ingredients>"
    Then the system should save their custom meal request

    Examples:
      | ingredients                |
      | Chicken, Rice, Broccoli    |
      | Beef, Potatoes, Carrots    |
      | Tofu, Quinoa, Spinach      |

  Scenario Outline: System validates ingredient combinations
    Given the customer selects ingredients "<ingredients>"
    When the system checks the ingredient combination
    Then the system should notify the customer of an invalid selection

    Examples:
      | ingredients                |
      | Salmon, Cheese, Chocolate  |
      | Milk, Lemon, Pickles       |

  Scenario Outline: Customer selects unavailable ingredients
    Given the customer selects ingredients "<ingredients>"
    When the system verifies ingredient availability
    Then the system should notify the customer about unavailable ingredients

    Examples:
      | ingredients         |
      | Lobster, Avocado    |
      | Truffle, Bluefin Tuna |
