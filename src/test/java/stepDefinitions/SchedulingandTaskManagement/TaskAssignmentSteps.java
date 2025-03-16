package stepDefinitions.SchedulingandTaskManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class TaskAssignmentSteps {

    private Map<String, Chef> chefs = new HashMap<>();
    private String assignedTask;
    private String assignedChef;
    private String notification;

    // Step Definitions for Kitchen Manager Assigns Tasks

    @Given("the kitchen manager has a list of chefs with their workload and expertise")
    public void the_kitchen_manager_has_a_list_of_chefs_with_their_workload_and_expertise() {
        // Simulate a list of chefs with their workload and expertise
        chefs.put("Chef John", new Chef("Chef John", "Italian cuisine", 2));
        chefs.put("Chef Alice", new Chef("Chef Alice", "Grilling", 1));
        chefs.put("Chef Bob", new Chef("Chef Bob", "Baking", 2)); // Updated workload to 2
    }

    @When("the kitchen manager assigns a {string} to a chef")
    public void the_kitchen_manager_assigns_a_to_a_chef(String task) {
        this.assignedTask = task;

        // Logic to assign the task to a chef based on expertise and workload
        String requiredExpertise = getRequiredExpertise(task);
        Chef bestChef = null;

        for (Map.Entry<String, Chef> entry : chefs.entrySet()) {
            Chef chef = entry.getValue();
            if (chef.getExpertise().equals(requiredExpertise)) {
                if (bestChef == null || chef.getWorkload() < bestChef.getWorkload()) {
                    bestChef = chef;
                }
            }
        }

        if (bestChef != null) {
            this.assignedChef = bestChef.getName();
        } else {
            throw new RuntimeException("No suitable chef found for task: " + task);
        }
    }

    @Then("the task {string} should be assigned to a chef with {string} expertise and low workload")
    public void the_task_should_be_assigned_to_a_chef_with_expertise_and_low_workload(String task, String expertise) {
        Assert.assertNotNull("No chef was assigned to the task: " + task, assignedChef);
        Chef assignedChef = chefs.get(this.assignedChef);
        Assert.assertEquals("Chef expertise does not match", expertise, assignedChef.getExpertise());
        Assert.assertTrue("Chef workload is not low", assignedChef.getWorkload() < 3);
    }

    @Then("the chef should receive a notification about the assigned task: {string}")
    public void the_chef_should_receive_a_notification_about_the_assigned_task(String task) {
        this.notification = "You have been assigned to: " + task;
        Assert.assertEquals("Notification does not match", notification, getChefNotification(assignedChef));
    }

    // Step Definitions for Chef Receives Notifications

    @Given("a chef has been assigned a {string}")
    public void a_chef_has_been_assigned_a(String task) {
        this.assignedTask = task;
        this.assignedChef = "Chef Bob"; // Simulate assigning the task to a chef
    }

    @When("the system sends a notification to the chef")
    public void the_system_sends_a_notification_to_the_chef() {
        this.notification = "You have been assigned to: " + assignedTask;
    }

    @Then("the chef should receive a notification: {string}")
    public void the_chef_should_receive_a_notification(String expectedNotification) {
        Assert.assertEquals("Notification does not match", expectedNotification, notification);
    }

    // Helper Methods

    private String getRequiredExpertise(String task) {
        // Logic to determine the required expertise for a task
        switch (task) {
            case "Prepare pasta":
                return "Italian cuisine";
            case "Grill steak":
                return "Grilling";
            case "Bake cake":
                return "Baking";
            default:
                throw new IllegalArgumentException("No expertise found for task: " + task);
        }
    }

    private String getChefNotification(String chefName) {
        // Logic to retrieve the notification for the assigned chef
        return "You have been assigned to: " + assignedTask;
    }

    // Chef Class to Represent Chef Details
    private static class Chef {
        private String name;
        private String expertise;
        private int workload;

        public Chef(String name, String expertise, int workload) {
            this.name = name;
            this.expertise = expertise;
            this.workload = workload;
        }

        public String getName() {
            return name;
        }

        public String getExpertise() {
            return expertise;
        }

        public int getWorkload() {
            return workload;
        }
    }
}