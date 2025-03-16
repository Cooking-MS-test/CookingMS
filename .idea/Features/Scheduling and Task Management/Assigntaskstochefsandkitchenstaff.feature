Feature: Task Assignment to Chefs

  As a kitchen manager,
  I want to assign tasks to chefs based on their workload and expertise
  So that I can ensure balanced workloads and efficiency.

  As a chef,
  I want to receive notifications about my assigned cooking tasks
  So that I can prepare meals on time.

  Scenario Outline: Kitchen manager assigns tasks to chefs based on workload and expertise
    Given the kitchen manager has a list of chefs with their workload and expertise
    When the kitchen manager assigns a "<task>" to a chef
    Then the task "<task>" should be assigned to a chef with "<expertise>" expertise and low workload
    And the chef should receive a notification about the assigned task: "<task>"

    Examples:
      | task                | expertise        |
      | Prepare pasta       | Italian cuisine  |
      | Grill steak         | Grilling         |
      | Bake cake           | Baking           |

  Scenario Outline: Chef receives notifications about assigned tasks
    Given a chef has been assigned a "<task>"
    When the system sends a notification to the chef
    Then the chef should receive a notification: "You have been assigned to: <task>"

    Examples:
      | task                |
      | Prepare pasta       |
      | Grill steak         |
      | Bake cake           |