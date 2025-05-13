package Zahi;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.DietaryRestriction;
import Notification_And_Alerts.Meal;
import Zahi.Task;
import models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private String name;
    private String contactInfo;

    private Ingredient originalIngredient;
    private Ingredient alternativeIngredient;
    private String expertise;
    private String lastNotification;
    private int currentWorkload;
    private int maxWorkload;


    private List<Task> assignedTasks;
    private List<Meal> assignedMeals;
    public Chef(String name, String expertise, int currentWorkload) {
        this.name = name;
        this.expertise = expertise;

        this.assignedTasks = new ArrayList<>();
        this.assignedMeals = new ArrayList<>();
        this.currentWorkload = currentWorkload;
        this.maxWorkload = 5;
        this.contactInfo = "";

    }

    public Chef() {

    }


    public String createChefAlert(Ingredient original, Ingredient alternative) {
        this.originalIngredient = original;
        this.alternativeIngredient = alternative;

        return "A substitution has been applied: " + original.getName() +
                " replaced with " + alternative.getName();
    }

    public boolean canApproveRecipe(Ingredient original, Ingredient alternative) {
        // Check 1: Alternative must be available
        if (!alternative.isAvailable()) {
            System.out.println("Alternative ingredient is not available.");
            return false;
        }

        // Check 2: Alternative must not need restocking
        if (alternative.needsRestocking()) {
            System.out.println("Alternative ingredient needs restocking.");
            return false;
        }

        // Check 3: Dietary restrictions from original should be satisfied
        if (original.getRestrictions() != null && alternative.getRestrictions() != null) {
            for (DietaryRestriction restriction : original.getRestrictions()) {
                if (!alternative.getRestrictions().contains(restriction)) {
                    System.out.println("Alternative ingredient does not meet dietary restriction: " + restriction);
                    return false;
                }
            }
        }

        return true;
    }

    public String getLastNotification() {
        return lastNotification;
    }

    public boolean canTakeTask() {
        // This method should check if the chef has a low enough workload or other criteria to take the task
        return this.currentWorkload < this.maxWorkload; // Example condition
    }

    public void assignTask(Task task) {
        this.assignedTasks.add(task);
        this.currentWorkload++;
        this.lastNotification = "You have been assigned to: " + task.getDescription();
    }
    public boolean hasExpertise(String requiredExpertise) {
        return this.expertise.equalsIgnoreCase(requiredExpertise);
    }
    public void addCookingTask(Meal meal) {
        assignedMeals.add(meal);
    }
    public void viewCustomerDietaryInfo(Customer customer) {
        System.out.println("Customer Dietary Info:\n" + customer.getDietarySummary());
    }
    public String getExpertise() {
        return expertise;
    }

    public int getWorkload() {
        return currentWorkload;
    }
    public String getName() {
        return name;
    }

    public int getCurrentWorkload() {
        return currentWorkload;
    }

    public Object getContactInfo() {
        return contactInfo;
    }
}
