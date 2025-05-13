package stepDefinitions.SchedulingandTaskManagement;

import io.cucumber.java.en.*;
import Zahi.Chef;
import Zahi.Task;
import org.junit.Assert;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskAssignmentSteps {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dueTime = LocalDateTime.parse("2025-05-15 10:00", formatter);
    private List<Chef> chefs = new ArrayList<>();
    private Task assignedTask;
    private Chef assignedChef;
    private String taskDescription;
    private String expectedNotification;

    @Given("the kitchen manager has a list of chefs with their workload and expertise")
    public void the_kitchen_manager_has_a_list_of_chefs_with_their_workload_and_expertise() {
        chefs.add(new Chef("Mario", "Italian cuisine", 2));
        chefs.add(new Chef("Lina", "Grilling", 1));
        chefs.add(new Chef("Ahmed", "Baking", 0));
    }

    @When("the kitchen manager assigns a {string} to a chef")
    public void the_kitchen_manager_assigns_a_task_to_a_chef(String task) {
        this.taskDescription = task;
        assignedTask = new Task(task, dueTime, "Chef John"); // pass LocalDateTime here
    }

    @Then("the task {string} should be assigned to a chef with {string} expertise and low workload")
    public void the_task_should_be_assigned_to_a_chef_with_expertise_and_low_workload(String task, String expertise) {
        Optional<Chef> chefOpt = chefs.stream()
                .filter(c -> c.getExpertise().equalsIgnoreCase(expertise))
                .min(Comparator.comparingInt(Chef::getWorkload));

        Assert.assertTrue("No suitable chef found", chefOpt.isPresent());

        assignedChef = chefOpt.get();
        assignedChef.assignTask(assignedTask);

        Assert.assertEquals("Task mismatch", task, assignedTask.getDescription());
        Assert.assertEquals("Chef expertise mismatch", expertise, assignedChef.getExpertise());
    }

    @And("the chef should receive a notification about the assigned task: {string}")
    public void the_chef_should_receive_a_notification_about_the_assigned_task(String expectedMessage) {
        // Ensure the message includes the expected prefix
        String actualMessage = assignedChef.getLastNotification();
        Assert.assertEquals("You have been assigned to: " + taskDescription, actualMessage);
    }

    @Given("a chef has been assigned a {string}")
    public void a_chef_has_been_assigned_a_task(String task) {
        assignedChef = new Chef("Noura", "General", 1);
        assignedTask = new Task(task, dueTime, "Chef John"); // pass LocalDateTime here
        assignedChef.assignTask(assignedTask);
        expectedNotification = "You have been assigned to: " + task;
    }


    @When("the system sends a notification to the chef")
    public void the_system_sends_a_notification_to_the_chef() {
        // Already sent in assignTask
    }

    @Then("the chef should receive a notification: {string}")
    public void the_chef_should_receive_a_notification(String expected) {
        String actual = assignedChef.getLastNotification();
        Assert.assertEquals(expected, actual);
    }
}
